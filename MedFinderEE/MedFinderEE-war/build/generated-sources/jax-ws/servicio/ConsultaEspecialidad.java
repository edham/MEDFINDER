
package servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultaEspecialidad complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultaEspecialidad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cmp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaEspecialidad", propOrder = {
    "cmp"
})
public class ConsultaEspecialidad {

    protected String cmp;

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

}
