package com.pokemon.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {

    public static final String POKEMON_DATA_CACHE= "POKEMON_DATA_CACHE";

    @Bean
    public CacheManager cacheManager() {
        List<CaffeineCache> caches = new ArrayList<>();
        caches.add(buildCache(POKEMON_DATA_CACHE,7, TimeUnit.HOURS, 1304));

        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(caches);

        return simpleCacheManager;
    }
    private static CaffeineCache buildCache(String name, long duration, TimeUnit ttUnit, long size){
        return new CaffeineCache(name,
                Caffeine.newBuilder().expireAfterWrite(duration, ttUnit).maximumSize(size).build());

    }
}
