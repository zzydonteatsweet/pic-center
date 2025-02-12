package com.zzy.piccenter.demos.web.infrastructure.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.utils.IOUtils;
import com.zzy.piccenter.demos.web.app.assembler.PictureAssembler;
import com.zzy.piccenter.demos.web.app.dto.PictureInfoDTO;
import com.zzy.piccenter.demos.web.app.manager.QueryCacheManager;
import com.zzy.piccenter.demos.web.domain.repository.PictureRepository;
import com.zzy.piccenter.demos.web.app.request.cmd.PictureCmd;
import com.zzy.piccenter.demos.web.app.request.query.PictureBriefQuery;
import com.zzy.piccenter.demos.web.app.response.PictureBriefDTO;
import com.zzy.piccenter.demos.web.app.response.UserInfoDTO;
import com.zzy.piccenter.demos.web.app.service.PictureService;
import com.zzy.piccenter.demos.web.domain.common.enums.ReviewStateEnum;
import com.zzy.piccenter.demos.web.domain.common.enums.UserRoleEnum;
import com.zzy.piccenter.demos.web.domain.picture.Picture;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.BusinessException;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.ErrorCode;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.StringUtils;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.ThrowUtils;
import com.zzy.piccenter.demos.web.infrastructure.manager.CosManager;
import com.zzy.piccenter.demos.web.infrastructure.utils.PageBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-29 21:14
 **/
@Service
@Slf4j
public class PictureServiceImpl implements PictureService {

    private final static String KEY_PREFIX = "query-picture:";

    @Resource
    private CosManager cosManager;

    @Resource
    private PictureRepository pictureRepository;

    @Resource
    List<QueryCacheManager> queryCacheManagers;

    @Resource
    @Qualifier("redisCacheManager")
    QueryCacheManager redisCacheManager;

    @Override
    public void testDownloadFile(String filepath, HttpServletResponse response) throws IOException {
        COSObjectInputStream cosObjectInput = null;
        try {
            COSObject cosObject = cosManager.testGetObject(filepath);
            cosObjectInput = cosObject.getObjectContent();
            byte[] bytes = IOUtils.toByteArray(cosObjectInput);

            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filepath);

            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (Exception e) {
            log.error("file download error, filepath = " + filepath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "下载失败");
        } finally {
            if (cosObjectInput != null) {
                cosObjectInput.close();
            }
        }
    }

    @Override
    public String testUploadFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String filePath = String.format("/test/%s", fileName);
        File file = null;

        try {
            file = File.createTempFile(filePath, null);
            multipartFile.transferTo(file);
            return JSON.toJSONString(cosManager.putObjectResult(filePath, file));
        } catch (Exception e) {
            log.error("file upload error, filepath = " + filePath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        } finally {
            deleteTempFile(file);
        }
    }


    private String getFileName(String originalFileName) {
        String uuid = RandomUtil.randomString(16);
        return String.format("%s_%s", uuid, originalFileName);
    }

    private String getFilePath(String fileName, String userAccount) {
        return String.format("/public/%s/%s", userAccount, fileName);
    }

    @Override
    @Transactional
    public PictureInfoDTO uploadFile(MultipartFile multipartFile, UserInfoDTO user, PictureCmd pictureCmd) {
        throwIfFileNotValid(multipartFile);
        throwIfPicIdNotValid(pictureCmd.getId(), user);
        String fileName = getFileName(multipartFile.getOriginalFilename());
        String filePath = getFilePath(fileName, user.getUserAccount());
        File file = null;
        PutObjectResult result = null;
        try {
            file = File.createTempFile(fileName, null);
            multipartFile.transferTo(file);
            result = cosManager.putObjectResult(filePath, file);
            String url = String.format("%s/%s", cosManager.getHost(), filePath);
            Picture picture = PictureAssembler.INSTANCE.toPicture(result.getCiUploadResult().getOriginalInfo(), url, FileUtil.size(file), user);
            if (!Objects.isNull(pictureCmd.getId())) {
                picture.populateId(pictureCmd.getId());
            }
            if (Objects.isNull(picture.getId())) {
                pictureRepository.addPicture(picture);
            } else {
                pictureRepository.updatePicture(picture);
            }
        } catch (Exception e) {
            log.error("file upload error, filepath = " + fileName, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        } finally {
            deleteTempFile(file);
        }
        return null;
    }

    @Override
    public void downloadFile(@NotNull Long pictureId, UserInfoDTO user, HttpServletResponse response) {
        throwIfPicIdNotValid(pictureId, user);
        Picture picture = pictureRepository.queryPictureById(pictureId);
        String fileName = picture.getName();
        cosManager.downloadFile(fileName, response);
    }

    @Override
    public PageInfo<PictureBriefDTO> queryPicture(PictureBriefQuery query, UserInfoDTO user) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        if (!user.getUserRole().equals(UserRoleEnum.ADMIN.getValue())) {
            query.setReviewStatus(ReviewStateEnum.PASS.getState());
        }
        List<Picture> pictures = pictureRepository.queryPictureFuzzily(query);
        PageInfo<Picture> picturePageInfo = new PageInfo<>(pictures);
        List<PictureBriefDTO> briefPictures = PictureAssembler.INSTANCE.toPictureBriefDTO(pictures);
        return PageBeanUtils.rebuildPage(briefPictures, picturePageInfo);
    }

    @Override
    public PageInfo<PictureBriefDTO> queryPictureCached(PictureBriefQuery query, UserInfoDTO user) {
        if (!user.getUserRole().equals(UserRoleEnum.ADMIN.getValue())) {
            query.setReviewStatus(ReviewStateEnum.PASS.getState());
        }

        String queryCondition = JSON.toJSONString(query);
        String hashKey = DigestUtils.md5DigestAsHex(queryCondition.getBytes());
        String key = KEY_PREFIX + hashKey;
        String rawValue = null;
        for (QueryCacheManager manager : queryCacheManagers) {
            String tmp = manager.getInfo(key);
            if (!StringUtils.isBlank(tmp)) {
                rawValue = tmp;
                break;
            }
        }
        if (StringUtils.isBlank(rawValue)) {
            PageInfo<PictureBriefDTO> res = queryPicture(query, user);
            rawValue = JSON.toJSONString(res);
            redisCacheManager.putInfo(key, JSON.toJSONString(res));
        }
        return JSON.parseObject(rawValue, new TypeReference<PageInfo<PictureBriefDTO>>() {
        });
    }


    private void throwIfPicIdNotValid(Long picId, UserInfoDTO user) {
        if (!Objects.isNull(picId)) {
            Picture picture = pictureRepository.queryPictureById(picId);
            ThrowUtils.throwIf(Objects.isNull(picture), new BusinessException(ErrorCode.OPERATION_ERROR, String.format("没有id为%d的图片", picId)));
            ThrowUtils.throwIf(picture.getUserAccount().equals(user.getUserAccount()) && !user.getUserRole().equals(UserRoleEnum.ADMIN.getValue()), new BusinessException(ErrorCode.OPERATION_ERROR, String.format("用户%s对图片%d无权限", user.getUserAccount(), picId)));
        }
    }

    private void throwIfFileNotValid(MultipartFile multipartFile) {
        ThrowUtils.throwIf(multipartFile.getSize() > 10 * 1024 * 1024, new BusinessException(ErrorCode.OPERATION_ERROR, "文件数量太大"));
    }

    private void deleteTempFile(File file) {
        if (file != null) {
            boolean isDelete = file.delete();
            if (!isDelete) {
                log.error("file delete error, filepath = {}", file.getName());
            }
        }
    }

}
