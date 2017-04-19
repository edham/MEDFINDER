/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import fachada.CasosSaludFacadeLocal;
import fachada.RespuestaCasoSaludFacadeLocal;
import modelo.CasosSalud;
import modelo.RespuestaCasoSalud;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author EdHam
 */
@ManagedBean
@SessionScoped
public class ManagedBeanRespuestaCasosSalud implements Serializable {
    @EJB
    private RespuestaCasoSaludFacadeLocal respuestaCasoSaludFacade;
    
    private List<CasosSalud> listaobjCasosSalud;
    private RespuestaCasoSalud objRespuestaCasoSalud;
    private String editarRespuesta;
    public ManagedBeanRespuestaCasosSalud() {
       
      instanciar();
    }
    public void instanciar()    
    {
        objRespuestaCasoSalud = new RespuestaCasoSalud();
        listaobjCasosSalud = new ArrayList<CasosSalud>();
    }

   
    

    public int contar(List<RespuestaCasoSalud> lista)
    {
        int contador=0;
        try{
            if(lista!=null)
            {
                for(RespuestaCasoSalud seguro:lista)
                {
                    if(seguro.getEstado()==1)
                        contador++;
                }
            }
        }
        catch (Exception e) {
        }
        return contador;
    }
    public void selecionar(RespuestaCasoSalud objeto)
      {
        
        try
        {
            objRespuestaCasoSalud=objeto;
            if(objRespuestaCasoSalud!=null)
            {
                if(objRespuestaCasoSalud.getEstado()==0)
                    editarRespuesta="DESEA RESTAURAR";
                else
                      editarRespuesta="DESEA ELIMINAR";
            }
        }
         catch (Exception e) {
        }   
      } 

    public void editar()
    {
         try
        {
            if(objRespuestaCasoSalud!=null)
            {
                objRespuestaCasoSalud.setFechaModificacion(new Date());
                objRespuestaCasoSalud.setEstado((objRespuestaCasoSalud.getEstado()==0)?1:0);
               respuestaCasoSaludFacade.edit(objRespuestaCasoSalud);
            }
        }
         catch (Exception e) {
        }   
    }
    public String getEditarRespuesta() {
        return editarRespuesta;
    }

    public void setEditarRespuesta(String editarRespuesta) {
        this.editarRespuesta = editarRespuesta;
    }
    
}
