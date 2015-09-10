package com.liuliume.portal.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.liuliume.portal.common.Constants;
import com.liuliume.portal.service.SmsService;

import java.util.concurrent.TimeUnit;

/**
 * Created by clement on 9/10/15.
 */
public class SmsServiceImpl implements SmsService {

    private static LoadingCache<String, String> appLocalCache = null;

    public SmsServiceImpl(){
        appLocalCache = CacheBuilder.newBuilder()
                .refreshAfterWrite(Constants.CACHE_REFRESH_INTERVAL, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return null;
                    }
                });
    }


}
