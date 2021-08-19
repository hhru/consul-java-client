package ru.hh.consul.monitoring;

import java.time.Duration;
import ru.hh.consul.cache.CacheDescriptor;

public interface ClientEventCallback {

    default void onHttpRequestSuccess(String clientName, String method, String queryString) { }

    default void onHttpRequestFailure(String clientName, String method, String queryString, Throwable throwable) { }

    default void onHttpRequestInvalid(String clientName, String method, String queryString, Throwable throwable) { }

    default void onCacheStart(String clientName, CacheDescriptor cacheDescriptor) { }

    default void onCacheStop(String clientName, CacheDescriptor cacheDescriptor) { }

    default void onCachePollingError(String clientName, CacheDescriptor cacheDescriptor, Throwable throwable) { }

    default void onCachePollingSuccess(String clientName, CacheDescriptor cacheDescriptor, boolean withNotification, Duration duration) { }
}
