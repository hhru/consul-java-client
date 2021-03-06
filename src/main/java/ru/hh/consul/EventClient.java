package ru.hh.consul;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import ru.hh.consul.async.ConsulResponseCallback;
import ru.hh.consul.async.EventResponseCallback;
import ru.hh.consul.config.ClientConfig;
import ru.hh.consul.model.ConsulResponse;
import ru.hh.consul.model.EventResponse;
import ru.hh.consul.model.ImmutableEventResponse;
import ru.hh.consul.model.event.Event;
import ru.hh.consul.monitoring.ClientEventCallback;
import ru.hh.consul.option.EventOptions;
import ru.hh.consul.option.QueryOptions;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.util.List;
import java.util.Map;

/**
 * HTTP Client for /v1/event/ endpoints.
 *
 * @see <a href="http://www.consul.io/docs/agent/http.html#event">The Consul API Docs</a>
 */
public class EventClient extends BaseClient {

    private static String CLIENT_NAME = "event";

    private final Api api;

    /**
     * Constructs an instance of this class.
     *
     * @param retrofit The {@link Retrofit} to build a client from.
     */
    EventClient(Retrofit retrofit, ClientConfig config, ClientEventCallback eventCallback) {
        super(CLIENT_NAME, config, eventCallback);
        this.api = retrofit.create(Api.class);
    }

    /**
     * Fires a Consul event.
     *
     * PUT /v1/event/fire/{name}
     *
     * @param name The name of the event.
     * @param eventOptions The event specific options to use.
     * @param payload Optional string payload.
     * @return The newly created {@link Event}.
     */
    public Event fireEvent(String name, EventOptions eventOptions, String payload) {
        return http.extract(api.fireEvent(name,
                RequestBody.create(MediaType.parse("text/plain"), payload),
                eventOptions.toQuery()));
    }

    /**
     * Fires a Consul event.
     *
     * PUT /v1/event/fire/{name}
     *
     * @param name The name of the event.
     * @return The newly created {@link Event}.
     */
    public Event fireEvent(String name) {
        return fireEvent(name, EventOptions.BLANK);
    }

    /**
     * Fires a Consul event.
     *
     * PUT /v1/event/fire/{name}
     *
     * @param name The name of the event.
     * @param eventOptions The event specific options to use.
     * @return The newly created {@link Event}.
     */
    public Event fireEvent(String name, EventOptions eventOptions) {
        return http.extract(api.fireEvent(name, eventOptions.toQuery()));
    }

    /**
     * Fires a Consul event.
     *
     * PUT /v1/event/fire/{name}
     *
     * @param name The name of the event.
     * @param payload Optional string payload.
     * @return The newly created {@link Event}.
     */
    public Event fireEvent(String name, String payload) {
        return fireEvent(name, EventOptions.BLANK, payload);
    }

    /**
     * Lists events for the Consul agent.
     *
     * GET /v1/event/list?name={name}
     *
     * @param name Event name to filter.
     * @param queryOptions The query options to use.
     * @return A {@link ConsulResponse} object containing
     *  a list of {@link Event} objects.
     */
    public EventResponse listEvents(String name, QueryOptions queryOptions) {
        final Map<String, Object> query = queryOptions.toQuery();
        if (StringUtils.isNotEmpty(name)) {
            query.put("name", name);
        }

        ConsulResponse<List<Event>> response = http.extractConsulResponse(api.listEvents(query));
        return ImmutableEventResponse.of(response.getResponse(), response.getIndex());
    }

    /**
     * Lists events for the Consul agent.
     *
     * GET /v1/event/list?name={name}
     *
     * @param name Event name to filter.
     * @return A {@link ConsulResponse} object containing
     *  a list of {@link Event} objects.
     */
    public EventResponse listEvents(String name) {
        return listEvents(name, QueryOptions.BLANK);
    }

    /**
     * Lists events for the Consul agent.
     *
     * GET /v1/event/list
     *
     * @param queryOptions The query options to use.
     * @return A {@link ConsulResponse} object containing
     *  a list of {@link Event} objects.
     */
    public EventResponse listEvents(QueryOptions queryOptions) {
        return listEvents(null, queryOptions);
    }

    /**
     * Lists events for the Consul agent.
     *
     * GET /v1/event/list
     *
     * @return A {@link ConsulResponse} object containing
     *  a list of {@link Event} objects.
     */
    public EventResponse listEvents() {
        return listEvents(null, QueryOptions.BLANK);
    }

    /**
     * Asynchronously lists events for the Consul agent.
     *
     * GET /v1/event/list?name={name}
     *
     * @param name Event name to filter.
     * @param queryOptions The query options to use.
     * @param callback The callback to asynchronously process the result.
     */
    public void listEvents(String name, QueryOptions queryOptions, EventResponseCallback callback) {
        final Map<String, Object> query = queryOptions.toQuery();
        if (StringUtils.isNotEmpty(name)) {
            query.put("name", name);
        }

        http.extractConsulResponse(api.listEvents(query), createConsulResponseCallbackWrapper(callback));
    }

    private ConsulResponseCallback<List<Event>> createConsulResponseCallbackWrapper(EventResponseCallback callback) {
        return new ConsulResponseCallback<List<Event>>() {
            @Override
            public void onComplete(ConsulResponse<List<Event>> response) {
                callback.onComplete(ImmutableEventResponse.of(response.getResponse(), response.getIndex()));
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }
        };
    }

    /**
     * Asynchronously lists events for the Consul agent.
     *
     * GET /v1/event/list
     *
     * @param queryOptions The query options to use.
     * @param callback The callback to asynchronously process the result.
     */
    public void listEvents(QueryOptions queryOptions, EventResponseCallback callback) {
        listEvents(null, queryOptions, callback);
    }

    /**
     * Asynchronously lists events for the Consul agent.
     *
     * GET /v1/event/list
     *
     * @param callback The callback to asynchronously process the result.
     */
    public void listEvents(EventResponseCallback callback) {
        listEvents(null, QueryOptions.BLANK, callback);
    }
    /**
     * Retrofit API interface.
     */
    interface Api {

        @PUT("event/fire/{name}")
        Call<Event> fireEvent(@Path("name") String name,
                              @Body RequestBody payload,
                              @QueryMap Map<String, Object> query);

        @PUT("event/fire/{name}")
        Call<Event> fireEvent(@Path("name") String name,
                              @QueryMap Map<String, Object> query);

        @GET("event/list")
        Call<List<Event>> listEvents(@QueryMap Map<String, Object> query);
    }
}
