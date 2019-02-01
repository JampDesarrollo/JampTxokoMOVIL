package com.example.a2dam.jamp.rest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:EventREST [event]<br>
 * USAGE:
 * <pre>
 *        EventRESTCli client = new EventRESTCli();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author paula
 */
public class EventRESTClient {

    private static final Logger LOGGER
            = Logger.getLogger("javafxserverside----EVENT REST CLIENT");
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/JampServerSide/webresources";

    /**
     * Construct a EventRESTClient. It creates a RESTful web client and
     * establishes the path of the WebTarget object associated to the client.
     */
    public EventRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("event");
    }

    /**
     * Get a list of events's entities XML representation from the event RESTful
     * web service and return it as a generic type object.
     *
     * @param responseType The Class object of the returning instance.
     * @param idTxoko the id of our txoko
     * @return A generic type, normally a list, containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findAllEvents(GenericType<T> responseType, String idTxoko) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{idTxoko}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Get a event's entity XML representation from the event RESTful web service
     * and return it as a generic type object.
     *
     * @param responseType The Class object of the returning instance.
     * @param name The name of the event
     * @param idTxoko the id of the txoko
     * @return The object containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findEventByName(Class<T> responseType, String name, String idTxoko) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("name/{0}/txoko/{1}", new Object[]{name, idTxoko}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Create an event's entity XML representation and send it as a request to
     * create it to the event RESTful web service.
     *
     * @param requestEntity The object containing data to be created.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public void createEvent(Object requestEntity) throws ClientErrorException {
        LOGGER.log(Level.INFO, "Going to restful service {0}.");
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Update an event's entity XML representation and send it as a request to
     * create it to the event RESTful web service.
     *
     * @param requestEntity The object containing data to be created.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public void update(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Delete an event's entity XML representation and send it as a request to
     * create it
     *
     * @param idEvent the id of the event we want to delete
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public void deleteEvent(Integer idEvent) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("idEvent/{0}", new Object[]{idEvent})).request().delete();
    }

    /**
     * Get a event's entity XML representation from the event RESTful web service
     * and return it as a generic type object.
     *
     * @param responseType The Class object of the returning instance.
     * @param idEvent The id of the event
     * @return The object containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findEventById(Class<T> responseType, String idEvent) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("idEvent/{0}", new Object[]{idEvent}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }
    /**
     * Get a event's entity XML representation from the event RESTful web service and
     * return it as a generic type object.
     * @param responseType The Class object of the returning instance.
     * @return The object containing the data.
     * @throws ClientErrorException If there is an error while processing. The error is wrapped in a HTTP error response.
     */
    public <T> T findAll(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }
    /**
     * Get a event's entity XML representation from the event RESTful web service and
     * return it as a generic type object.
     * @param responseType The Class object of the returning instance.
     * @param idEvent The id of the event
     * @param idTxoko the id of the txoko
     * @return The object containing the data.
     * @throws ClientErrorException If there is an error while processing. The error is wrapped in a HTTP error response.
     */
    public <T> T findEventByIdByTxoko(Class<T> responseType, String idEvent, String idTxoko) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("idEvent/{0}/idTxoko/{1}", new Object[]{idEvent, idTxoko}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.close();
    }

}
