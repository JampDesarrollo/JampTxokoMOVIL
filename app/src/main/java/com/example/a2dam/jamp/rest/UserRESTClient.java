/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.rest;

import java.util.ResourceBundle;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:UserREST [user]<br>
 * USAGE:
 * <pre>
 *        UserRESTClient client = new UserRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Ander
 */
public class UserRESTClient {

    private WebTarget webTarget;
    private Client client;
    /**
     * Get URI from properties' values file.
     */
    private static final String BASE_URI = ResourceBundle.getBundle("jampclientside.rest.config")
            .getString("URI");

    /**
     * Construct a UserRESTClient. It creates a RESTful web client and
     * establishes the path of the WebTarget object associated to the client.
     */
    public UserRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("user");
    }

    /**
     * Get login from the user RESTful web service and return a boolean as a
     * generic type object.
     *
     * @param responseType The Class object of the returning instance.
     * @param login Login of the user to find.
     * @return The object containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findUserForgotPassword(Class<T> responseType, String login) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{login}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    /**
     * Get a users entities' idtxoko and return a list of users as a generic
     * type object.
     *
     * @param responseType The Class object of the returning instance.
     * @param idTxoko IdTxoko of the users to be found.
     * @return A generic type, normally a list, containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findAllTxokoUsers(GenericType<T> responseType, String idTxoko) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("txoko/{0}", new Object[]{idTxoko}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Get a user entity XML representation from the user RESTful web service
     * and return it as a generic type object.
     *
     * @param responseType The Class object of the returning instance.
     * @param login Login of the user.
     * @param password Password of the user.
     * @return The object containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findUserByLoginPasswPC(Class<T> responseType, String login, String password) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("PC/{0}/{1}", new Object[]{login, password}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Send a request to the user RESTful web service to delete a user
     * identified by its idUser.
     *
     * @param idUser The id of the user entity to be deleted.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public void deleteUser(String idUser) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("user/{0}", new Object[]{idUser})).request().delete();
    }

    /**
     * Get a User entity XML representation from the user RESTful web service
     * and return it as a generic type object.
     *
     * @param responseType The object containing data to be updated.
     * @param idUser Id of the user.
     * @return The object containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findUserById(Class<T> responseType, String idUser) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("user/{0}", new Object[]{idUser}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Create an user's entity XML representation and send it as a request to
     * update it to the user RESTful web service.
     *
     * @param requestEntity The object containing data to be updated.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public void updateUser(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Get a User entity XML representation from the user RESTful web service to
     * change the users password.
     *
     * @param responseType The Class object of the returning instance.
     * @param idUser Id of the user.
     * @param oldPassw Users old password.
     * @param newPassw Users new password.
     * @return The object containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findUserChangePasswMov(Class<T> responseType, String idUser, String oldPassw, String newPassw) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("MovChangePassw/{0}/{1}/{2}", new Object[]{idUser, oldPassw, newPassw}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }

    /**
     * Create an user's entity XML representation and send it as a request to
     * create it to the user RESTful web service.
     *
     * @param requestEntity The object containing data to be created.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public void createUser(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    /**
     * Get a list of user's entities XML representation from the user RESTful
     * web service and return it as a generic type object.
     *
     * @param responseType The Class object of the returning instance.
     * @return A generic type, normally a list, containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findAllUsers(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("users");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Get a user entity XML representation from the user RESTful web service
     * and return it as a generic type object.
     *
     * @param responseType The Class object of the returning instance.
     * @param login Users login
     * @param password Users password.
     * @return The object containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findUserByLoginPasswMov(Class<T> responseType, String login, String password) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Mov/{0}/{1}", new Object[]{login, password}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * Close RESTful web service client.
     */
    public void close() {
        client.close();
    }

}
