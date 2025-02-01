package com.zzy.piccenter.demos.web.app.assembler;

import com.zzy.piccenter.demos.web.app.request.cmd.ReviewPicCmd;
import com.zzy.piccenter.demos.web.domain.review.ReviewInfo;
import com.zzy.piccenter.demos.web.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewAssembler {
    ReviewAssembler INSTANCE = Mappers.getMapper(ReviewAssembler.class);

    ReviewInfo toReviewInfo(ReviewPicCmd cmd);

    default ReviewInfo toReviewInfo(ReviewPicCmd cmd, User user) {
        ReviewInfo reviewInfo = toReviewInfo(cmd);
        reviewInfo.populateReviewerId(user.getId());
        return reviewInfo;
    }

    ;
}
