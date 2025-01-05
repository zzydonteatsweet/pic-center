package com.zzy.piccenter.demos.web.app.assembler;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson2.JSON;
import com.qcloud.cos.model.ciModel.persistence.ImageInfo;
import com.qcloud.cos.model.ciModel.persistence.OriginalInfo;
import com.zzy.piccenter.demos.web.app.dto.UserInfoDTO;
import com.zzy.piccenter.demos.web.app.response.PictureBriefDTO;
import com.zzy.piccenter.demos.web.domain.picture.Picture;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PictureAssembler {
    PictureAssembler INSTANCE = Mappers.getMapper(PictureAssembler.class);

    default Picture toPicture(OriginalInfo originalInfo, String url, long size, UserInfoDTO user) {
        ImageInfo imageInfo = originalInfo.getImageInfo();
        int picWidth = imageInfo.getWidth();
        int picHeight = imageInfo.getHeight();
        double scale = NumberUtil.round(picWidth * 1.0 / picHeight, 2).doubleValue();
        return Picture.builder()
                .name(originalInfo.getKey())
                .tags(JSON.parseArray(originalInfo.getEtag(),String.class))
                .url(url)
                .picFormat(imageInfo.getFormat())
                .picSize(size)
                .picHeight(imageInfo.getHeight())
                .picWidth(imageInfo.getWidth())
                .picScale(scale)
                .userAccount(user.getUserAccount())
                .build();
    };

    PictureBriefDTO toPictureBriefDTO(Picture picture);

    List<PictureBriefDTO> toPictureBriefDTO(List<Picture> pictures);
}
