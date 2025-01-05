package com.zzy.piccenter.demos.web.infrastructure.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-05 11:06
 **/
public class PageBeanUtils {
    /**
     * 基于已有的 Page 参数，构造新的 Page 对象并填充新的数据。
     *
     * @param list 新的列表数据
     * @param page 已有的 Page 参数
     * @param <T>  数据类型
     * @return 新的 Page 对象
     */
    public static <T> PageInfo<T> rebuildPage(List<T> list, PageInfo<?> page) {
        // 创建一个新的 Page 对象
        PageInfo<T> newPage = new PageInfo<>(list);
        newPage.setPageNum(page.getPageNum()); // 保留原页码
        newPage.setPageSize(page.getPageSize()); // 保留原页面大小
        newPage.setTotal(page.getTotal()); // 保留原总记录数
        newPage.setPages(page.getPages()); // 保留原总页数
        return newPage;
    }
}
