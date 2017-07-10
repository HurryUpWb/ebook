
package com.jyglo.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "PostXmlService", targetNamespace = "http://jyglo/", wsdlLocation = "http://localhost:9001/service/PostXml?wsdl")
public class PostXmlService
    extends Service
{

    private final static URL POSTXMLSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.jyglo.client.PostXmlService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.jyglo.client.PostXmlService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:9001/service/PostXml?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:9001/service/PostXml?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        POSTXMLSERVICE_WSDL_LOCATION = url;
    }

    public PostXmlService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PostXmlService() {
        super(POSTXMLSERVICE_WSDL_LOCATION, new QName("http://jyglo/", "PostXmlService"));
    }

    /**
     * 
     * @return
     *     returns PostXml
     */
    @WebEndpoint(name = "PostXmlPort")
    public PostXml getPostXmlPort() {
        return super.getPort(new QName("http://jyglo/", "PostXmlPort"), PostXml.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PostXml
     */
    @WebEndpoint(name = "PostXmlPort")
    public PostXml getPostXmlPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://jyglo/", "PostXmlPort"), PostXml.class, features);
    }

}
