package com.jamp.reto2.sinUsar.rest;


/**
 * Jersey REST client generated for REST resource:ProductREST [product]<br>
 * USAGE:
 * <pre>
 *        ProductRESTClient client = new ProductRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author 2dam
 */
public class ProductRESTClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/JampServerSide/webresources";

    public ProductRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("product");
    }

    public void deleteProduct(Integer idProduct) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("idProducto/{0}", new Object[]{idProduct})).request().delete();
    }

    public void updateProduct(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void createProduct(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public <T> T findProductByIdByTxoko(Class<T> responseType, String idProduct, String idTxoko) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("idProduct/{0}/txoko/{1}", new Object[]{idProduct, Integer.parseInt(idTxoko)}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findProductByName(GenericType<T> responseType, String name, String idTxoko) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("name/{0}/idTxoko/{1}", new Object[]{name, Integer.parseInt(idTxoko)}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findAllProducts(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findAllProductsByTxoko(GenericType<T> responseType, String idTxoko) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("txoko/{0}", new Object[]{Integer.parseInt(idTxoko)}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public ProductBean findProductById(Class<ProductBean> responseType, String idProduct) {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("idProducto/{0}", new Object[]{idProduct}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.close();
    }

}