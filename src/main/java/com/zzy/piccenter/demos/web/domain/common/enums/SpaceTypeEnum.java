package com.zzy.piccenter.demos.web.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpaceTypeEnum {
    PRIVATE(1, "普通空间", 1),
    TEAM(2, "白银空间", 2)
    ;
    private Integer id;
    private String name;
    private Integer spaceType;
}
