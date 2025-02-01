package com.zzy.piccenter.demos.web.infrastructure.service;

import com.zzy.piccenter.demos.web.app.assembler.ReviewAssembler;
import com.zzy.piccenter.demos.web.app.repository.PictureRepository;
import com.zzy.piccenter.demos.web.app.repository.ReviewRepository;
import com.zzy.piccenter.demos.web.app.repository.UserRepository;
import com.zzy.piccenter.demos.web.app.request.cmd.ReviewPicCmd;
import com.zzy.piccenter.demos.web.app.response.UserInfoDTO;
import com.zzy.piccenter.demos.web.app.service.ReviewService;
import com.zzy.piccenter.demos.web.domain.picture.Picture;
import com.zzy.piccenter.demos.web.domain.review.ReviewInfo;
import com.zzy.piccenter.demos.web.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-31 00:26
 **/
@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private ReviewRepository reviewRepository;

    @Resource
    private PictureRepository pictureRepository;

    @Override
    public void reviewPicture(ReviewPicCmd cmd, UserInfoDTO userInfo) {
        User user = userRepository.queryUserByUserAccount(userInfo.getUserAccount());
        ReviewInfo reviewInfo = ReviewAssembler.INSTANCE.toReviewInfo(cmd, user);
        reviewRepository.addReviewRecord(reviewInfo);
        Picture picture = Picture.builder()
                .id(reviewInfo.getPicId())
                .reviewerId(user.getId())
                .reviewStatus(reviewInfo.getReviewStatus())
                .reviewMessage(reviewInfo.getReviewMessage())
                .build();
        pictureRepository.addOrUpdatePicture(picture);
    }
}
