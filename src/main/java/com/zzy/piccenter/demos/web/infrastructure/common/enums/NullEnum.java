package com.zzy.piccenter.demos.web.infrastructure.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author T041018
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum NullEnum {
    NULL("null");
    private String value;
}
