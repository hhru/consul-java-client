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
public class IndexAwareStubCallbackConsumer implements ConsulCache.CallbackConsumer<Value> {

    private final List<Value> result;
    private BigInteger index;

    public IndexAwareStubCallbackConsumer(List<Value> result) {
        this.result = Collections.unmodifiableList(result);
    }

    @Override
    public void consume(BigInteger index, ConsulResponseCallback<List<Value>> callback) {
        this.index = index;
        callback.onComplete(new ConsulResponse<>(result, 0, true, BigInteger.ZERO, null, null));
    }

    public BigInteger getIndex() {
        return index;
    }
}
