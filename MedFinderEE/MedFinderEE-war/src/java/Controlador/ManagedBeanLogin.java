/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Utilidades.Utilidades;
import bc.UsuarioFacadeLocal;
import be.Usuario;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author EdHam
 */
@ManagedBean
@SessionScoped
public class ManagedBeanLogin {
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private Usuario objUsuario;
    

    public ManagedBeanLogin() {
      objUsuario=null;
      
       
    }
    

    public Usuario getObjUsuario() {
        if(objUsuario==null)        {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();    
            objUsuario = usuarioFacade.buscarXString(request.getRemoteUser(),"usuario");
            if(objUsuario==null)
            {
                try {
                    
                    request.logout();
                    FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
                    Utilidades.Error("USTED NO TIENE PERMISO. COMUNICATE CON EL ADMINISTRADOR");
                 
                } catch (ServletException ex) {
                    System.out.println("ServletException "+ex);
                } catch (IOException ex) {
                     System.out.println("IOException "+ex);
                }
            }
            else
            {
                 Utilidades.Info("BIENVENIDO "+objUsuario.getPersona().getApellidoPaterno()+" "+objUsuario.getPersona().getApellidoMaterno()+" "+objUsuario.getPersona().getNombre());
            }
        }
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }
    
     public void logout() {
        try{
           
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();    
            request.logout();
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
            Utilidades.Info("HASTA LUEGO");
        } catch (ServletException ex) {
            System.out.println("ServletException "+ex);
        } catch (IOException ex) {
            System.out.println("IOException "+ex);
        }
    }

//     public void logoutListener() throws IOException {
//         Utilidades.Warn("Se ha cerrado la sesión. Hasta luego");
//         FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//         FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
//
//    }
}
