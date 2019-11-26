/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import fachada.CasosSaludFacadeLocal;
import modelo.CasosSalud;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author EdHam
 */
@ManagedBean
@SessionScoped
public class ManagedBeanCasosSalud implements Serializable {

    @EJB
    private CasosSaludFacadeLocal casosSaludFacade;

    private List<CasosSalud> listaObjeto;
    private CasosSalud objeto;
    private String nuevoTitulo;

    public ManagedBeanCasosSalud() {

    }

    public CasosSaludFacadeLocal getCasosSaludFacade() {
        return casosSaludFacade;
    }

    @PostConstruct
    public void init() {
        nuevoTitulo = "NUEVO";
        objeto = new CasosSalud();
        listaObjeto = casosSaludFacade.lista_estado("pKId", true, false);
    }

    public void instanciar() {
        nuevoTitulo = "NUEVO";
        objeto = new CasosSalud();
        listaObjeto = new ArrayList<CasosSalud>();
    }

    public List<CasosSalud> getListaObjeto() {
        return listaObjeto;
    }

    public void setListaObjeto(List<CasosSalud> listaObjeto) {
        this.listaObjeto = listaObjeto;
    }

    public CasosSalud getObjeto() {
        return objeto;
    }

    public void setObjeto(CasosSalud objeto) {
        this.objeto = objeto;
    }

    public String getNuevoTitulo() {
        return nuevoTitulo;
    }

    public void setNuevoTitulo(String nuevoTitulo) {
        this.nuevoTitulo = nuevoTitulo;
    }

    public List<CasosSalud> lista(boolean todos) {
        List<CasosSalud> lista = new ArrayList<CasosSalud>();
        try {
            if (todos) {
                lista = casosSaludFacade.findAll();
            } else {
                lista = casosSaludFacade.lista_activos();
            }
        } catch (Exception e) {
        }

        return lista;
    }

    public void crear() {
        System.out.println("crear objeto.getPKId() " + objeto.getPKId());
        try {
            objeto.setFechaModificacion(new Date());
            if (objeto.getPKId() == null) {
                casosSaludFacade.create(objeto);
            } else {
                casosSaludFacade.edit(objeto);
            }
            instanciar();
        } catch (Exception e) {
        }

    }

    public void actualizar(CasosSalud obejto) {
        this.nuevoTitulo = "EDITAR ID : " + obejto.getPKId();
        this.objeto = obejto;

    }

    public CasosSalud traerObjeto(String id) {
        CasosSalud objBuscar = casosSaludFacade.find(Integer.parseInt(id));
        return objBuscar;
    }
}
