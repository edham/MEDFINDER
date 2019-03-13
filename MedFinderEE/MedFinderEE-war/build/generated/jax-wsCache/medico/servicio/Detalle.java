
package servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para detalle complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="detalle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="claboral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cregional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medcir" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pweb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ruta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detalle", propOrder = {
    "claboral",
    "cregional",
    "email",
    "estado",
    "medcir",
    "pweb",
    "ruta"
})
public class Detalle {

    protected String claboral;
    protected String cregional;
    protected String email;
    protected String estado;
    protected String medcir;
    protected String pweb;
    protected String ruta;

    /**
     * Obtiene el valor de la propiedad claboral.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaboral() {
        return claboral;
    }

    /**
     * Define el valor de la propiedad claboral.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaboral(String value) {
        this.claboral = value;
    }

    /**
     * Obtiene el valor de la propiedad cregional.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCregional() {
        return cregional;
    }

    /**
     * Define el valor de la propiedad cregional.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCregional(String value) {
        this.cregional = value;
    }

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad medcir.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedcir() {
        return medcir;
    }

    /**
     * Define el valor de la propiedad medcir.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedcir(String value) {
        this.medcir = value;
    }

    /**
     * Obtiene el valor de la propiedad pweb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPweb() {
        return pweb;
    }

    /**
     * Define el valor de la propiedad pweb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPweb(String value) {
        this.pweb = value;
    }

    /**
     * Obtiene el valor de la propiedad ruta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Define el valor de la propiedad ruta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuta(String value) {
        this.ruta = value;
    }

}
