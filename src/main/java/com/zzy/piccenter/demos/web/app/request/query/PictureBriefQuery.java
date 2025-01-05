package com.zzy.piccenter.demos.web.app.request.query;

import com.zzy.piccenter.demos.web.app.request.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
public class PictureBriefQuery extends PageRequest {
    private String name;

    private String introduction;

    private String category;

    private List<String> tags;
}
