package ru.hh.consul;

import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ru.hh.consul.config.ClientConfig;
import ru.hh.consul.model.coordinate.Coordinate;
import ru.hh.consul.model.coordinate.Datacenter;
import ru.hh.consul.monitoring.ClientEventCallback;

/**
 * HTTP Client for /v1/coordinate/ endpoints.
 *
 * @see <a href="http://www.consul.io/docs/agent/http.html#agent">The Consul API Docs</a>
 */
public class CoordinateClient extends BaseClient {

    private static String CLIENT_NAME = "coordinate";

    private final Api api;

    /**
     * Constructs an instance of this class.
     *
     * @param retrofit The {@link Retrofit} to build a client from.
     */
    CoordinateClient(Retrofit retrofit, ClientConfig config, ClientEventCallback eventCallback) {
        super(CLIENT_NAME, config, eventCallback);
        this.api = retrofit.create(Api.class);
    }

    public List<Datacenter> getDatacenters() {
        return http.extract(api.getDatacenters());
    }

    public List<Coordinate> getNodes(String dc) {
        return http.extract(api.getNodes(dcQuery(dc)));
    }

    public List<Coordinate> getNodes() {
        return getNodes(null);
    }

    private Map<String, String> dcQuery(String dc) {
        return dc != null ? Map.of("dc", dc) : Map.of();
    }

    /**
     * Retrofit API interface.
     */
    interface Api {

        @GET("coordinate/datacenters")
        Call<List<Datacenter>> getDatacenters();

        @GET("coordinate/nodes")
        Call<List<Coordinate>> getNodes(@QueryMap Map<String, String> query);

    }
}
