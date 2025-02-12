package com.zzy.piccenter.demos.web.infrastructure.common.converter;

import com.zzy.piccenter.demos.web.domain.review.ReviewInfo;
import com.zzy.piccenter.demos.web.infrastructure.po.ReviewInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewConverter {
    ReviewConverter INSTANCE = Mappers.getMapper(ReviewConverter.class);

    ReviewInfoPO toReviewInfoPO(ReviewInfo reviewInfo);
}
