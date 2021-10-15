package com.function.ppcache.cache;

import com.function.ppcache.bean.LoadingCacheConf;
import com.function.ppcache.bean.ThreadPoolConf;
import com.function.ppcache.executor.ExecutorFactory;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * @author Chen Xiao
 * @since 2021-10-13 12:08
 */
@Slf4j
public abstract class AbstractPPLoadingCache<K, V> implements PPLoadingCache<K, V> {

    String cacheName;

    LoadingCacheConf loadingCacheConf;

    /**
     * 业务方法
     */
    Function<K, V> function;

    LoadingCache<K, V> loadingCache;

    /**
     * 线程池：异步刷新本地缓存
     */
    ListeningExecutorService refreshLocalCachePool;


    void build() {
        initExecutor();
        createLoadingCache();
    }

    void initExecutor() {
        refreshLocalCachePool = MoreExecutors
                .listeningDecorator(ExecutorFactory.getInstance().create(new ThreadPoolConf().setCapacity(1)));
    }

    abstract void createLoadingCache();

    @Override
    public void put(K key, V value) {
        loadingCache.put(key, value);
    }

    @Override
    public V getUnchecked(K key) {
        return loadingCache.getUnchecked(key);
    }

    @Override
    public V get(K key) throws ExecutionException {
        return loadingCache.get(key);
    }

    @Override
    public void refresh(K key) {
        loadingCache.refresh(key);
    }

    @Override
    public void invalidate(K key) {
        loadingCache.invalidate(key);
    }

    @Override
    public void invalidateAll() {
        loadingCache.invalidateAll();
    }

    @Override
    public void stats() {
        CacheStats stats = loadingCache.stats();
        log.info("cacheName[{}] hitRate[{}] missRate[{}]", cacheName, stats.hitRate(), stats.missRate());
    }
}
