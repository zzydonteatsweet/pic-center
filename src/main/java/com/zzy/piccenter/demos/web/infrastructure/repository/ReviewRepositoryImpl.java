package com.zzy.piccenter.demos.web.infrastructure.repository;

import com.zzy.piccenter.demos.web.domain.repository.ReviewRepository;
import com.zzy.piccenter.demos.web.domain.review.ReviewInfo;
import com.zzy.piccenter.demos.web.infrastructure.common.converter.ReviewConverter;
import com.zzy.piccenter.demos.web.infrastructure.mapper.ReviewInfoMapper;
import com.zzy.piccenter.demos.web.infrastructure.po.ReviewInfoPO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-31 10:11
 **/
@Repository
public class ReviewRepositoryImpl implements ReviewRepository {
    @Resource
    private ReviewInfoMapper reviewInfoMapper;

    @Override
    public int addReviewRecord(ReviewInfo reviewInfo) {
        ReviewInfoPO reviewInfoPO = ReviewConverter.INSTANCE.toReviewInfoPO(reviewInfo);
        return reviewInfoMapper.insertSelective(reviewInfoPO);
    }
}
