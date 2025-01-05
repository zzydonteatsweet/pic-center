package com.zzy.piccenter.demos.web.adapter.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zzy.piccenter.demos.web.app.dto.PictureInfoDTO;
import com.zzy.piccenter.demos.web.app.dto.UserInfoDTO;
import com.zzy.piccenter.demos.web.app.request.cmd.PictureCmd;
import com.zzy.piccenter.demos.web.app.request.query.PictureBriefQuery;
import com.zzy.piccenter.demos.web.app.response.BaseResponse;
import com.zzy.piccenter.demos.web.app.response.PictureBriefDTO;
import com.zzy.piccenter.demos.web.app.service.PictureService;
import com.zzy.piccenter.demos.web.app.service.UserService;
import com.zzy.piccenter.demos.web.infrastructure.annotation.AuthCheck;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.ResultUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-29 21:29
 **/
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private PictureService pictureService;

    @Resource
    private UserService userService;

    @AuthCheck(requiredRole = "admin")
    @PostMapping("/test/upload")
    public BaseResponse<String> testUploadFile(@RequestPart("file") MultipartFile multipartFile) {
        return ResultUtils.success(pictureService.testUploadFile(multipartFile));
    }

    @AuthCheck(requiredRole = "admin")
    @GetMapping("/test/download")
    public void testDonwloadFile(String filePath, HttpServletResponse response) throws IOException {
        pictureService.testDownloadFile(filePath, response);
    }

    @PostMapping("/upload")
    public BaseResponse<PictureInfoDTO> uploadFile(@RequestPart("file") MultipartFile multipartFile, HttpServletRequest request, PictureCmd cmd) {
        UserInfoDTO user = userService.getLoingUser(request);
        return ResultUtils.success(pictureService.uploadFile(multipartFile, user, cmd));
    }

    @GetMapping("/download")
    public void downloadFile(@NotNull @RequestParam Long pictureId, HttpServletRequest request, HttpServletResponse response) {
        UserInfoDTO user = userService.getLoingUser(request);
        pictureService.downloadFile(pictureId, user, response);
    }

    @PostMapping("/query")
    public BaseResponse<PageInfo<PictureBriefDTO>> queryPictureInfo(HttpServletRequest request, @RequestBody(required = false) PictureBriefQuery query) {
        UserInfoDTO user = userService.getLoingUser(request);
        return ResultUtils.success(pictureService.queryPicture(query, user));
    }
}
