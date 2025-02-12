package com.zzy.piccenter.demos.web.domain.repository;

import com.zzy.piccenter.demos.web.domain.review.ReviewInfo;

public interface ReviewRepository {
    int addReviewRecord(ReviewInfo reviewInfo);
}
