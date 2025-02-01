package com.zzy.piccenter.demos.web.infrastructure.repository;

import com.zzy.piccenter.demos.web.app.repository.PictureRepository;
import com.zzy.piccenter.demos.web.app.request.query.PictureBriefQuery;
import com.zzy.piccenter.demos.web.domain.picture.Picture;
import com.zzy.piccenter.demos.web.infrastructure.converter.PictureConverter;
import com.zzy.piccenter.demos.web.infrastructure.mapper.PictureMapper;
import com.zzy.piccenter.demos.web.infrastructure.po.PicturePO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-02 12:26
 **/
@Repository
public class PictureRepositoryImpl implements PictureRepository {

    @Resource
    private PictureMapper pictureMapper;

    @Override
    public int addPicture(Picture picture) {
        PicturePO picturePO = PictureConverter.INSTANCE.toPicturePO(picture);
        return pictureMapper.insertSelective(picturePO);
    }

    @Override
    public int updatePicture(Picture picture) {
        PicturePO picturePO = PictureConverter.INSTANCE.toPicturePO(picture);
        return pictureMapper.updateByPrimaryKeySelective(picturePO);
    }

    @Override
    public Picture queryPictureById(Long id) {
        PicturePO po = pictureMapper.selectByPrimaryKey(id);
        return PictureConverter.INSTANCE.toPicture(po);
    }

    @Override
    public List<Picture> queryPictureFuzzily(PictureBriefQuery query) {
        PicturePO queryPicturePO = PictureConverter.INSTANCE.toPicturePO(query);
        List<PicturePO> picturePOList = pictureMapper.selectSelectiveFuzzily(queryPicturePO);
        return PictureConverter.INSTANCE.toPicture(picturePOList);
    }


}
