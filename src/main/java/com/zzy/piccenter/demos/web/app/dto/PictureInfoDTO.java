package com.zzy.piccenter.demos.web.app.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-01 10:44
 **/
@Data
public class PictureInfoDTO {
    private Long id;

    private String url;

    private String name;

    private String introduction;

    private String category;

    private List<String> tags;

    private Long picSize;

    private Integer picWidth;

    private Integer picHeight;

    private Double picScale;

    private String picFormat;

    private Long userId;

    private Integer reviewStatus;

    private Integer reviewMessage;

    private Long reviewerId;

    private Date createTime;

    private Date editeTime;
}
