package com.zzy.piccenter.demos.web.infrastructure.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum RedisEnum {

    REDIS_TTL(1, "过期时间", 3600);

    private Integer code;
    private String desc;
    private Integer value;

}
