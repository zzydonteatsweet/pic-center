package com.zzy.piccenter.demos.web.app.repository;

import com.zzy.piccenter.demos.web.domain.picture.Picture;

public interface PictureRepository {
    int addPicture(Picture picture);

    int addOrUpdatePicture(Picture picture);

    Picture queryPictureById(Long id);
}
