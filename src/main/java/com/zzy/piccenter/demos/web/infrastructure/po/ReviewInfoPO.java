package com.zzy.piccenter.demos.web.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author oldmomsimith
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReviewInfoPO {
    private Long id;

    private Long picId;

    private Long reviewerId;

    private Integer reviewStatus;

    private String reviewMessage;

    private Date createTime;

    private Date modifiedTime;
}