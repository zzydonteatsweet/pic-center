package com.zzy.piccenter.demos.web.infrastructure.common.utils;

import com.zzy.piccenter.demos.web.domain.common.enums.BaseEnum;

import java.util.Arrays;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-02-12 00:14
 **/
public class EnumUtils {
    public static <E extends Enum<E> & BaseEnum> E getEnumById(Class<E> enumClass, Integer id) {
        if (id == null) {
            return null;  // 如果id为null，直接返回null
        }
        return Arrays.stream(enumClass.getEnumConstants())  // 获取所有枚举常量
                .filter(enumItem -> enumItem.getId().equals(id)) // 匹配id
                .findFirst()  // 获取第一个匹配的枚举项
                .orElse(null);
    }
}
