package com.zzy.piccenter.demos.web.domain.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author oldmomsimith
 */
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInfo {
    private Long id;

    private Long picId;

    private Long reviewerId;

    /**
     * 0-未审核,1-审核通过,2-审核不通过
     */
    private Integer reviewStatus;

    private String reviewMessage;

    private Date createTime;

    private Date modifiedTime;

    public void populateReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }
}