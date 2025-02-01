package com.zzy.piccenter.demos.web.app.repository;

import com.zzy.piccenter.demos.web.app.request.query.PictureBriefQuery;
import com.zzy.piccenter.demos.web.domain.picture.Picture;

import java.util.List;

public interface PictureRepository {
    int addPicture(Picture picture);

    int updatePicture(Picture picture);

    Picture queryPictureById(Long id);

    List<Picture> queryPictureFuzzily(PictureBriefQuery query);
}
