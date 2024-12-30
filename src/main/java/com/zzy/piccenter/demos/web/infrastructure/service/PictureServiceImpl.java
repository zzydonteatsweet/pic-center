package com.zzy.piccenter.demos.web.infrastructure.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.utils.IOUtils;
import com.zzy.piccenter.demos.web.app.assembler.PictureAssembler;
import com.zzy.piccenter.demos.web.app.dto.PictureInfoDTO;
import com.zzy.piccenter.demos.web.app.dto.UserInfoDTO;
import com.zzy.piccenter.demos.web.app.repository.PictureRepository;
import com.zzy.piccenter.demos.web.app.request.cmd.PictureCmd;
import com.zzy.piccenter.demos.web.app.service.PictureService;
import com.zzy.piccenter.demos.web.domain.picture.Picture;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.BusinessException;
import com.zzy.piccenter.demos.web.infrastructure.common.exception.ErrorCode;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.ThrowUtils;
import com.zzy.piccenter.demos.web.infrastructure.manager.CosManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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

    @Resource
    private CosManager cosManager;

    @Resource
    private PictureRepository pictureRepository;

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


    private String getFileName(String userAccount, String originalFileName) {
        String filePath = String.format("/public/%s", userAccount);
        String uuid = RandomUtil.randomString(16);
        return String.format("%s_%s_%s", DateUtil.formatDate(new Date()), uuid, originalFileName);
    }

    @Override
    public PictureInfoDTO uploadFile(MultipartFile multipartFile, UserInfoDTO user, PictureCmd pictureCmd) {
        throwIfFileNotValid(multipartFile);
        throwIfPicIdNotValid(pictureCmd.getId());
        String fileName = getFileName(user.getUserAccount(), multipartFile.getOriginalFilename());
        File file = null;
        PutObjectResult result = null;
        try {
            file = File.createTempFile(fileName, null);
            multipartFile.transferTo(file);
            result = cosManager.putObjectResult(fileName, file);
            String url = String.format("%s/%s", cosManager.getHost(), fileName);
            Picture picture = PictureAssembler.INSTANCE.toPicture(result.getCiUploadResult().getOriginalInfo(), url, FileUtil.size(file));
            if (!Objects.isNull(pictureCmd.getId())) {
                picture.populateId(pictureCmd.getId());
            }
            pictureRepository.addOrUpdatePicture(picture);
        } catch (Exception e) {
            log.error("file upload error, filepath = " + fileName, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        } finally {
            deleteTempFile(file);
        }
        return null;
    }

    @Override
    public void downloadFile(Long pictureId, UserInfoDTO user, HttpServletResponse response) {
        throwIfPicIdNotValid(pictureId);
        Picture picture = pictureRepository.queryPictureById(pictureId);
        String fileName = getFileName(user.getUserAccount(), picture.getName());
        cosManager.downloadFile(fileName, response);
    }


    private void throwIfPicIdNotValid(Long picId) {
        ThrowUtils.throwIf(Objects.isNull(picId), new BusinessException(ErrorCode.OPERATION_ERROR, "没有提供id"));
        ThrowUtils.throwIf(Objects.isNull(pictureRepository.queryPictureById(picId)), new BusinessException(ErrorCode.OPERATION_ERROR, String.format("没有id为%lld的图片", picId)));
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
