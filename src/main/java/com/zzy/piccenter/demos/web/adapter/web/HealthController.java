package com.zzy.piccenter.demos.web.adapter.web;

import com.zzy.piccenter.demos.web.app.response.BaseResponse;
import com.zzy.piccenter.demos.web.infrastructure.common.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>pic-center</h3>
 * <p>health controller</p>
 *
 * @author : olmomsimith
 * @date : 2024-12-17 23:37
 **/
@RestController
public class HealthController {

    @GetMapping("/health")
    public BaseResponse<String> healthCheck() {
        return ResultUtils.success( "health");
    }


}
