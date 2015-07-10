/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.med.finder.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.med.finder.dao.clsClinicaDAO;
import com.app.med.finder.entidades.clsClinica;
import com.app.med.finder.utilidades.Funciones;
import java.text.SimpleDateFormat;

/**
 *
 * @author Toditos
 */
public class InfClinicaTabActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    
    private clsClinica entidad = null;
    
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here        
        setContentView(R.layout.clinica_infromacion);
        Bundle bundle=getIntent().getExtras();
        int Id=Integer.valueOf(bundle.getString("Id"));
        SimpleDateFormat hora=new SimpleDateFormat("h:mm a");
        entidad=clsClinicaDAO.Buscar(this, Id);
        
        ImageView image = (ImageView)findViewById(R.id.image);
        if(entidad.getByte_logo()!=null)
        image.setImageBitmap(Funciones.getBitmap(entidad.getByte_logo()));
//        
        TextView lblNombre = (TextView)findViewById(R.id.lblNombre);
	lblNombre.setText(entidad.getStr_nombre());
//        
        TextView lblSlogan = (TextView)findViewById(R.id.lblSlogan);
	lblSlogan.setText(entidad.getStr_slogan());
        
        TextView lblInicio = (TextView)findViewById(R.id.lblInicio);
	lblInicio.setText(hora.format(entidad.getDat_hora_inicio()));
        
        TextView lblFin = (TextView)findViewById(R.id.lblFin);
	lblFin.setText(hora.format(entidad.getDat_hora_fin()));
        
        TextView lblTelefono = (TextView)findViewById(R.id.lblTelefono);
	lblTelefono.setText(entidad.getStr_telefono());
        
        TextView lblDireccion = (TextView)findViewById(R.id.lblDireccion);
	lblDireccion.setText(entidad.getStr_direccion());
        
        TextView lblAtencion = (TextView)findViewById(R.id.lblAtencion);
	lblAtencion.setText(entidad.getStr_detalle_atencion());
 
        TextView lblDetalle = (TextView)findViewById(R.id.lblDetalle);
	lblDetalle.setText(entidad.getStr_descripcion());
       

                        
        
    }
    
     @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
   
    
    return false;

    }
    
}
