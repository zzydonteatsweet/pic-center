package com.zzy.piccenter.demos.web.app.service;

import com.zzy.piccenter.demos.web.app.dto.PictureInfoDTO;
import com.zzy.piccenter.demos.web.app.dto.UserInfoDTO;
import com.zzy.piccenter.demos.web.app.request.cmd.PictureCmd;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2024-12-29 21:32
 **/
public interface PictureService {
    String testUploadFile(@RequestPart("file") MultipartFile multipartFile);

    void testDownloadFile(String filepath, HttpServletResponse response) throws IOException;

    PictureInfoDTO uploadFile(MultipartFile multipartFile, UserInfoDTO user, PictureCmd cmd);

    void downloadFile(Long pictureId, UserInfoDTO user, HttpServletResponse response);
}
