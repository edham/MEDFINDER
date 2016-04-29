package com.sinergia.seguridad.utilidades.route;

import com.google.android.gms.maps.model.PolylineOptions;

public interface RoutingListener {
  public void onRoutingFailure();
  public void onRoutingStart();
  public void onRoutingSuccess(PolylineOptions mPolyOptions);
}