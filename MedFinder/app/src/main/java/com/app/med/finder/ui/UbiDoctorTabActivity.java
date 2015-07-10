/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.med.finder.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import com.app.med.finder.dao.clsDoctorDAO;
import com.app.med.finder.entidades.clsDoctor;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 *
 * @author Paulo
 */
public class UbiDoctorTabActivity  extends FragmentActivity {


  private GoogleMap gMap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.tab_ubicacion);  
     Bundle bundle=getIntent().getExtras();
      int Id=Integer.valueOf(bundle.getString("Id"));
      clsDoctor entidad=clsDoctorDAO.Buscar(this, Id);
      
      LatLng pos= new LatLng(entidad.getDou_latitud(),entidad.getDou_longitud());
        
     gMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
     gMap.addMarker(new MarkerOptions().title(entidad.getStr_direccion()).position(pos));
     gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));
  }   
  
   @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
   
    
    return false;

    }
}
