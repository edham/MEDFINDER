/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.DetalleClinicaSeguro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface DetalleClinicaSeguroFacadeLocal {

    void create(DetalleClinicaSeguro detalleClinicaSeguro);

    void edit(DetalleClinicaSeguro detalleClinicaSeguro);

    void remove(DetalleClinicaSeguro detalleClinicaSeguro);

    DetalleClinicaSeguro find(Object id);

    List<DetalleClinicaSeguro> findAll();

    List<DetalleClinicaSeguro> findRange(int[] range);
    List<DetalleClinicaSeguro> lista_activos();
    int count();
    
}
