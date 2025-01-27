package com.zzy.piccenter.demos.web.infrastructure.common.utils;

import java.util.Objects;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-18 12:59
 **/
public class StringUtils {
    public static boolean isBlank(String text) {
        if (Objects.isNull(text) || text.isEmpty()) {
            return true;
        }
        return false;
    }
}
