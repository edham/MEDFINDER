
package servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaDoctor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaDoctor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cmp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="materno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaDoctor", propOrder = {
    "cmp",
    "paterno",
    "materno",
    "nombres"
})
public class ConsultaDoctor {

    protected String cmp;
    protected String paterno;
    protected String materno;
    protected String nombres;

    /**
     * Obtiene el valor de la propiedad cmp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmp() {
        return cmp;
    }

    /**
     * Define el valor de la propiedad cmp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmp(String value) {
        this.cmp = value;
    }

    /**
     * Obtiene el valor de la propiedad paterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaterno() {
        return paterno;
    }

    /**
     * Define el valor de la propiedad paterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaterno(String value) {
        this.paterno = value;
    }

    /**
     * Obtiene el valor de la propiedad materno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterno() {
        return materno;
    }

    /**
     * Define el valor de la propiedad materno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterno(String value) {
        this.materno = value;
    }

    /**
     * Obtiene el valor de la propiedad nombres.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Define el valor de la propiedad nombres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombres(String value) {
        this.nombres = value;
    }

}
