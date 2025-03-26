package com.club.service.impl;

import com.club.mapper.ConfigMapper;
import com.club.service.ConfigService;
import io.swagger.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;
    @Override
    public String getConfig(String name) {
        return configMapper.getConfig(name);

    }
}
