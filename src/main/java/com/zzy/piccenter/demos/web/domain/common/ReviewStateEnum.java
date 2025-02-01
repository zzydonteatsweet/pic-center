package com.zzy.piccenter.demos.web.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author T041018
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ReviewStateEnum {
    UNREVIEWED("unreviewed", 0),
    PASS("pass", 1),
    REJECTED("rejected", 2);

    private String text;
    private Integer state;
}
