package com.sinergia.seguridad.entidades;

import java.util.Date;

/**
 * Created by EdHam on 19/03/2016.
 */
public class clsSesionVigilancia {
    private int int_id;
    private clsCuadrante objCuadrante;
    private Date dat_fec_ini;
    private Long dat_fec_vigilancia;
    private int int_principal;

    public clsSesionVigilancia() {
        this.dat_fec_ini = new Date();
    }

    public int getInt_id() {
        return int_id;
    }

    public void setInt_id(int int_id) {
        this.int_id = int_id;
    }

    public Date getDat_fec_ini() {
        return dat_fec_ini;
    }

    public void setDat_fec_ini(Date dat_fec_ini) {
        this.dat_fec_ini = dat_fec_ini;
    }

    public clsCuadrante getObjCuadrante() {
        return objCuadrante;
    }

    public void setObjCuadrante(clsCuadrante objCuadrante) {
        this.objCuadrante = objCuadrante;
    }

    public Long getDat_fec_vigilancia() {
        return dat_fec_vigilancia;
    }

    public void setDat_fec_vigilancia(Long dat_fec_vigilancia) {
        this.dat_fec_vigilancia = dat_fec_vigilancia;
    }

    public int getInt_principal() {
        return int_principal;
    }

    public void setInt_principal(int int_principal) {
        this.int_principal = int_principal;
    }
}
