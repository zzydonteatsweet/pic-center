package com.zzy.piccenter.demos.web.app.request.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-01-31 00:23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReviewPicCmd {
    @NotNull
    private Integer reviewStatus;
    private String reviewMessage;
    private Integer picId;
}
