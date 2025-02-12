package com.zzy.piccenter.demos.web.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SpaceLevelEnum implements BaseEnum {
    NORMAL(1, "普通空间", 50L, 20L * 1024L * 1024L),
    SILVER(2, "白银空间", 100L, 50L * 1024L * 1024L)
    ;
    private Integer id;
    private String name;
    private Long maxCount;
    private Long maxSize;
}
