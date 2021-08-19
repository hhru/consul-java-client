package ru.hh.consul.cache;

import java.util.Map;
import ru.hh.consul.model.kv.Value;

/**
 *
 */
final class StubListener implements ConsulCache.Listener<String, Value> {
    private int callCount = 0;
    private Map<String, Value> lastValues;


    @Override
    public void notify(Map<String, Value> newValues) {
        callCount++;
        lastValues = newValues;
    }

    public int getCallCount() {
        return callCount;
    }

    public Map<String, Value> getLastValues() {
        return lastValues;
    }
}
