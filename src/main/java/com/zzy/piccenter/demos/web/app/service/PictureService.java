package com.zzy.piccenter.demos.web.app.service;

import com.github.pagehelper.PageInfo;
import com.zzy.piccenter.demos.web.app.dto.PictureInfoDTO;
import com.zzy.piccenter.demos.web.app.request.cmd.PictureCmd;
import com.zzy.piccenter.demos.web.app.request.query.PictureBriefQuery;
import com.zzy.piccenter.demos.web.app.response.PictureBriefDTO;
import com.zzy.piccenter.demos.web.app.response.UserInfoDTO;
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

    PictureInfoDTO uploadFile(MultipartFile multipartFile, UserInfoDTO user, PictureCmd pictureCmd);

    void downloadFile(Long pictureId, UserInfoDTO user, HttpServletResponse response);

    PageInfo<PictureBriefDTO> queryPicture(PictureBriefQuery query, UserInfoDTO user);
}
