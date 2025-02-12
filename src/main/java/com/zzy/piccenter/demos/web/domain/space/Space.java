package com.zzy.piccenter.demos.web.domain.space;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-02-07 23:32
 **/
@SuperBuilder
@Getter
public class Space {
    private Long id;

    private String spaceName;

    private Integer spaceLevel;

    private Integer spaceType;

    private Long maxSize;

    private Long maxCount;

    private Long totalSize;

    private Long totalCount;

    private Long userId;

    private Date createTime;

    private Date editTime;

    private Date updateTime;

    private Byte isDelete;
}
