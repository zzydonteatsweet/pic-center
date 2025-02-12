package com.zzy.piccenter.demos.web.infrastructure.manager;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h3>pic-center</h3>
 *
 * @author : olmomsimith
 * @date : 2025-02-05 23:52
 **/
@Component
public class SseEmitterManager {
    private static final Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>(300);
}
