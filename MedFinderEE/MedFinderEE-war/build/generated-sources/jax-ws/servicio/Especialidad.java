
package servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para especialidad complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="especialidad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cer_Recer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rne" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "especialidad", propOrder = {
    "cerRecer",
    "nombre",
    "rne"
})
public class Especialidad {

    @XmlElement(name = "cer_Recer")
    protected String cerRecer;
    protected String nombre;
    protected String rne;

    /**
     * Obtiene el valor de la propiedad cerRecer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCerRecer() {
        return cerRecer;
    }

    /**
     * Define el valor de la propiedad cerRecer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCerRecer(String value) {
        this.cerRecer = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad rne.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRne() {
        return rne;
    }

    /**
     * Define el valor de la propiedad rne.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRne(String value) {
        this.rne = value;
    }

}
