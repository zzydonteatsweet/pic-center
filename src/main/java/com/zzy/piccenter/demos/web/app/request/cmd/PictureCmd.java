package com.zzy.piccenter.demos.web.app.request.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-01 10:38
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PictureCmd {
    private Long id;
}
