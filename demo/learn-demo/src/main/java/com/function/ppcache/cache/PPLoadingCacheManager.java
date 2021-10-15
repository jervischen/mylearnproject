package com.function.ppcache.cache;

import com.function.ppcache.bean.LoadingCacheConf;
import com.google.common.base.Preconditions;
import lombok.extern.log4j.Log4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @author Chen Xiao
 * @since 2021-10-14 15:39
 */
@Log4j
public class PPLoadingCacheManager<K, V> {

    private static PPLoadingCacheManager ppLoadingCacheManager;

    private final static Object lock = new Object();

    private final static Map<String, PPLoadingCacheProxy> loadingCacheMap = new ConcurrentHashMap<>(64);

    public PPLoadingCacheProxy<K, V> createLoadingCache(String cacheName, LoadingCacheConf loadingCacheConf, Function<K, V> function) {
        PPLoadingCacheProxy<K, V> proxy = PPLoadingCacheFactory.getInstance().createSizePPLoadingfCache(cacheName, loadingCacheConf, function);
        loadingCacheMap.put(cacheName, proxy);

        return proxy;
    }

    private PPLoadingCacheManager() {

    }

    public PPLoadingCacheProxy getLoadingCacheProxy(String cacheName) {
        return loadingCacheMap.get(cacheName);
    }

    public static PPLoadingCacheManager getInstance() {
        if (ppLoadingCacheManager == null) {
            synchronized (lock) {
                if (ppLoadingCacheManager == null) {
                    ppLoadingCacheManager = new PPLoadingCacheManager();
                }
            }
        }

        return ppLoadingCacheManager;
    }

    public static PPLoadingCacheProxy getCacheByName(String cacheName) {
        PPLoadingCacheProxy proxy = loadingCacheMap.get(cacheName);
        Preconditions.checkArgument(proxy != null, String.format("cacheName[%s] not init..", cacheName));

        return proxy;
    }

    public static void allCacheStats() {
        for (PPLoadingCacheProxy proxy : loadingCacheMap.values()) {
            proxy.stats();
        }
    }
}
