
package servicio;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servicio package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConsultaDetalle_QNAME = new QName("http://servicio/", "consultaDetalle");
    private final static QName _ConsultaDoctor_QNAME = new QName("http://servicio/", "consultaDoctor");
    private final static QName _ConsultaDoctorResponse_QNAME = new QName("http://servicio/", "consultaDoctorResponse");
    private final static QName _ConsultaDetalleResponse_QNAME = new QName("http://servicio/", "consultaDetalleResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servicio
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaDetalle }
     * 
     */
    public ConsultaDetalle createConsultaDetalle() {
        return new ConsultaDetalle();
    }

    /**
     * Create an instance of {@link ConsultaDoctor }
     * 
     */
    public ConsultaDoctor createConsultaDoctor() {
        return new ConsultaDoctor();
    }

    /**
     * Create an instance of {@link ConsultaDoctorResponse }
     * 
     */
    public ConsultaDoctorResponse createConsultaDoctorResponse() {
        return new ConsultaDoctorResponse();
    }

    /**
     * Create an instance of {@link ConsultaDetalleResponse }
     * 
     */
    public ConsultaDetalleResponse createConsultaDetalleResponse() {
        return new ConsultaDetalleResponse();
    }

    /**
     * Create an instance of {@link WsEspecialidad }
     * 
     */
    public WsEspecialidad createWsEspecialidad() {
        return new WsEspecialidad();
    }

    /**
     * Create an instance of {@link WsDetalle }
     * 
     */
    public WsDetalle createWsDetalle() {
        return new WsDetalle();
    }

    /**
     * Create an instance of {@link WsDoctor }
     * 
     */
    public WsDoctor createWsDoctor() {
        return new WsDoctor();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDetalle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio/", name = "consultaDetalle")
    public JAXBElement<ConsultaDetalle> createConsultaDetalle(ConsultaDetalle value) {
        return new JAXBElement<ConsultaDetalle>(_ConsultaDetalle_QNAME, ConsultaDetalle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDoctor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio/", name = "consultaDoctor")
    public JAXBElement<ConsultaDoctor> createConsultaDoctor(ConsultaDoctor value) {
        return new JAXBElement<ConsultaDoctor>(_ConsultaDoctor_QNAME, ConsultaDoctor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDoctorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio/", name = "consultaDoctorResponse")
    public JAXBElement<ConsultaDoctorResponse> createConsultaDoctorResponse(ConsultaDoctorResponse value) {
        return new JAXBElement<ConsultaDoctorResponse>(_ConsultaDoctorResponse_QNAME, ConsultaDoctorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaDetalleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicio/", name = "consultaDetalleResponse")
    public JAXBElement<ConsultaDetalleResponse> createConsultaDetalleResponse(ConsultaDetalleResponse value) {
        return new JAXBElement<ConsultaDetalleResponse>(_ConsultaDetalleResponse_QNAME, ConsultaDetalleResponse.class, null, value);
    }

}
