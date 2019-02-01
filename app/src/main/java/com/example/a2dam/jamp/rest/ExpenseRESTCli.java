/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:ExpenseREST [expense]<br>
 * USAGE:
 * <pre>
 *        ExpenseRESTCli client = new ExpenseRESTCli();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author paula
 */
public class ExpenseRESTCli {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://192.168.20.128:8080/JampServerSide/webresources";
     /**
     * Construct a ExpenseRESTClient. It creates a RESTful web client and
     * establishes the path of the WebTarget object associated to the client.
     */
    public ExpenseRESTCli() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("expense");
    }
     /**
     * Get a list of expenses entities XML representation from the expense RESTful
     * web service and return it as a generic type object.
     *
     * @param responseType The Class object of the returning instance.
     * @param idTxoko the id of our txoko
     * @return A generic type, normally a list, containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findAllExpensesUsers(GenericType<T> responseType, String idTxoko) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{idTxoko}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }
    /**
     * Get a expense entity XML representation from the expense RESTful web service
     * and return it as a generic type object.
     *
     * @param responseType The Class object of the returning instance.
     * @param idUser The id of the user
     * @return The object containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findMonthExpensesSingleUser(Class<T> responseType, Integer idUser) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("monthUser/{0}", new Object[]{idUser}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
    }
    /**
     * Create an expens's entity XML representation and send it as a request to
     * create it to the expense RESTful web service.
     *
     * @param requestEntity The object containing data to be created.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public void createExpense(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }
    /**
     * Get a list of expenses entities XML representation from the expense RESTful
     * web service and return it as a generic type object.
     *
     * @param responseType The Class object of the returning instance.
     * @param idTxoko the id of our txoko
     * @return A generic type, normally a list, containing the data.
     * @throws ClientErrorException If there is an error while processing. The
     * error is wrapped in a HTTP error response.
     */
    public <T> T findMonthExpensesUsers(GenericType<T> responseType, String idTxoko) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("month/{0}", new Object[]{idTxoko}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
