package com.club.service;

import com.club.result.PageResult;
import io.swagger.util.Json;

public interface ConfigService {
    /***
     * 获取配置信息
     * @param name
     * @return
     */
    String getConfig(String name);
}
