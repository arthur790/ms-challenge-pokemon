package com.pokemon.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${cache.pokemon-detail.max-size}")
    private long maxSizePokemonDetail;

    @Value("${cache.pokemon-detail.duration}")
    private long durationPokemonDetail;

    @Value("${cache.pokemon-evolution.max-size}")
    private long maxSizePokemonEvolution;

    @Value("${cache.pokemon-evolution.duration}")
    private long durationPokemonEvolution;

    @Value("${cache.pokemon-detail-name.max-size}")
    private long maxSizePokemonDetailName;

    @Value("${cache.pokemon-detail-name.duration}")
    private long durationPokemonDetailName;

    public static final String POKEMON_DETAIL_DATA_CACHE= "POKEMON_DETAIL_DATA_CACHE";
    public static final String POKEMON_EVOLUTION_DATA_CACHE= "POKEMON_EVOLUTION_DATA_CACHE";
    public static final String POKEMON_DETAIL_NAME_DATA_CACHE= "POKEMON_DETAIL_NAME_DATA_CACHE";

    @Bean
    public CacheManager cacheManager() {
        List<CaffeineCache> caches = new ArrayList<>();
        caches.add(buildCache(POKEMON_DETAIL_DATA_CACHE, durationPokemonDetail, TimeUnit.MINUTES, maxSizePokemonDetail));
        caches.add(buildCache(POKEMON_EVOLUTION_DATA_CACHE, durationPokemonEvolution, TimeUnit.MINUTES, maxSizePokemonEvolution));
        caches.add(buildCache(POKEMON_DETAIL_NAME_DATA_CACHE, durationPokemonDetailName, TimeUnit.MINUTES, maxSizePokemonDetailName));

        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(caches);

        return simpleCacheManager;
    }
    private static CaffeineCache buildCache(String name, long duration, TimeUnit ttUnit, long size){
        return new CaffeineCache(name,
                Caffeine.newBuilder().expireAfterWrite(duration, ttUnit).maximumSize(size).build());

    }
}
