package ru.hh.consul.cache;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import ru.hh.consul.async.ConsulResponseCallback;
import ru.hh.consul.model.ConsulResponse;
import ru.hh.consul.model.kv.Value;

/**
 *
 */
public class StubCallbackConsumer implements ConsulCache.CallbackConsumer<Value> {

    private final List<Value> result;
    private int callCount;

    public StubCallbackConsumer(List<Value> result) {
        this.result = Collections.unmodifiableList(result);
    }

    @Override
    public void consume(BigInteger index, ConsulResponseCallback<List<Value>> callback) {
        callCount++;
        callback.onComplete(new ConsulResponse<>(result, 0, true, BigInteger.ZERO, null, null));
    }

    public int getCallCount() {
        return callCount;
    }
}
