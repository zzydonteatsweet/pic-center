package com.zzy.piccenter.demos.web.app.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-05 00:14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureBriefDTO {
    private Long id;

    private String url;

    private String name;

    private String introduction;

    private String category;

    private List<String> tags;

    private Date createTime;

    private Date editeTime;
}
