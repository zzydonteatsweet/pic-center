package com.zzy.piccenter.demos.web.app.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-02-07 23:50
 **/
@Data
@SuperBuilder
public class SpaceInfoDTO {
    private Long id;

    private String spaceName;

    private Integer spaceLevel;

    private Long maxSize;

    private Long maxCount;

    private Long totalSize;

    private Long totalCount;

    private Long userId;
}
