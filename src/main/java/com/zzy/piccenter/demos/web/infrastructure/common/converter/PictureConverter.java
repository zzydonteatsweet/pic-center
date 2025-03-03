package com.zzy.piccenter.demos.web.infrastructure.common.converter;

import com.zzy.piccenter.demos.web.app.request.query.PictureBriefQuery;
import com.zzy.piccenter.demos.web.domain.picture.Picture;
import com.zzy.piccenter.demos.web.infrastructure.po.PicturePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-02 12:27
 **/
@Mapper
public interface PictureConverter {
    PictureConverter INSTANCE = Mappers.getMapper(PictureConverter.class);

    @Mapping(target = "tags", expression = "java(com.alibaba.fastjson2.JSON.toJSONString(picture.getTags()))")
    PicturePO toPicturePO(Picture picture);

    @Mapping(target = "tags", expression = "java(com.alibaba.fastjson2.JSON.parseArray(po.getTags(), String.class))")
    Picture toPicture(PicturePO po);

    @Mapping(target = "tags", expression = "java(com.alibaba.fastjson2.JSON.toJSONString(query.getTags()))")
    PicturePO toPicturePO(PictureBriefQuery query);

    List<Picture> toPicture(List<PicturePO> picturePOList);
}
