package com.zzy.piccenter.demos.web.domain.picture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-01 10:30
 **/
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    private Long id;

    private String url;

    private String name;

    private String introduction;

    private String category;

    private List<String> tags;

    private Long picSize;

    private Integer picWidth;

    private Integer picHeight;

    private Double picScale;

    private String picFormat;

    private String userAccount;

    private Date createTime;

    private Date editeTime;

    public void populateId(Long id) {
        this.id = id;
    }
}
