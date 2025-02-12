package com.zzy.piccenter.demos.web.app.request.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-02-11 23:58
 **/
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SpaceInfoCmd {
    @NotBlank
    @Size(min = 3, max = 50)
    private String spaceName;

    @NotNull
    private Integer spaceLevel;
}
