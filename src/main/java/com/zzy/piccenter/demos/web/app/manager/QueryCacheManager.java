package com.zzy.piccenter.demos.web.app.manager;

public abstract class QueryCacheManager {
    protected Integer MIN_TTL_MINUTE = 5;
    protected Integer MAX_TTL_MINUTE = 30;

    public abstract void putInfo(String key, String value);

    public abstract String getInfo(String key);
}
