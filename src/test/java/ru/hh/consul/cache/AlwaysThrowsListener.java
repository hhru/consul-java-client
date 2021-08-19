package ru.hh.consul.cache;

import java.util.Map;
import ru.hh.consul.model.kv.Value;

/**
 *
 */
final class AlwaysThrowsListener implements ConsulCache.Listener<String, Value> {
    @Override
    public void notify(Map<String, Value> newValues) {
        throw new RuntimeException("This listener always throws an exception!");
    }
}
