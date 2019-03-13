package com.med.finder.cliente.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.med.finder.cliente.R;
import com.med.finder.cliente.dao.clsClinicaDAO;
import com.med.finder.cliente.dao.clsClinicaEspecialidadDAO;
import com.med.finder.cliente.dao.clsSeguroDAO;
import com.med.finder.cliente.entidades.clsClinica;
import com.med.finder.cliente.entidades.clsClinicaEspecialidad;
import com.med.finder.cliente.entidades.clsSeguro;
import com.med.finder.cliente.utilidades.CustomFontTextView;
import com.med.finder.cliente.utilidades.Utilidades;
import java.util.List;

public class ClinicaInfoFragment extends Fragment{

    private ViewGroup vgEspecialidades;
    private List<clsClinicaEspecialidad> listEspecialidades;

    private ListView listViewSeguros;
    private List<clsSeguro> listSeguro;
    private AdaptadorSeguros adaptadorSeguros;

    private CustomFontTextView lblDatos;
    private View viewDatos;

    private CustomFontTextView lblUbicacion;
    private View viewUbicacion;

    private CustomFontTextView lblEspecialidades;
    private View viewEspecialidades;
    private View viewEspecialidadesTotal;

    private CustomFontTextView lblSeguros;
    private View viewSeguros;
    private View viewSegurosTotal;



    private int Id;
    MapView mapView;
    private GoogleMap googleMap;
    private clsClinica objClinica = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clinica_info, container, false);

        Id=getArguments().getInt("id");

        objClinica = clsClinicaDAO.Buscar(this.getActivity(), Id);
        ImageView image = (ImageView)view.findViewById(R.id.image);
        if(objClinica.getByte_logo()!=null)
            image.setImageBitmap(Utilidades.getBitmap(objClinica.getByte_logo()));

        CustomFontTextView lblNombre = (CustomFontTextView)view.findViewById(R.id.lblNombre);
        lblNombre.setText(objClinica.getStr_nombre());
//
        CustomFontTextView lblSlogan = (CustomFontTextView)view.findViewById(R.id.lblSlogan);
        lblSlogan.setText(objClinica.getStr_slogan());

        CustomFontTextView lblInicio = (CustomFontTextView)view.findViewById(R.id.lblInicio);
        lblInicio.setText(Utilidades.hora.format(objClinica.getDat_hora_inicio()));

        CustomFontTextView lblFin = (CustomFontTextView)view.findViewById(R.id.lblFin);
        lblFin.setText(Utilidades.hora.format(objClinica.getDat_hora_fin()));

        CustomFontTextView lblTelefono = (CustomFontTextView)view.findViewById(R.id.lblTelefono);
        lblTelefono.setText(objClinica.getStr_telefono());

        CustomFontTextView lblDireccion = (CustomFontTextView)view.findViewById(R.id.lblDireccion);
        lblDireccion.setText(objClinica.getStr_direccion());

        CustomFontTextView lblAtencion = (CustomFontTextView)view.findViewById(R.id.lblAtencion);
        lblAtencion.setText(objClinica.getStr_detalle_atencion());

        CustomFontTextView lblDetalle = (CustomFontTextView)view.findViewById(R.id.lblDetalle);
        lblDetalle.setText(objClinica.getStr_descripcion());

        vgEspecialidades = (ViewGroup) view.findViewById(R.id.vgEspecialidades);
        listViewSeguros = (ListView)view.findViewById(R.id.listViewSeguros);

        lblDatos=(CustomFontTextView)view.findViewById(R.id.lblDatos);
        lblDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblDatos();
            }
        });
        viewDatos=(View)view.findViewById(R.id.viewDatos);

        lblUbicacion=(CustomFontTextView)view.findViewById(R.id.lblUbicacion);
        lblUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblUbicacion();
            }
        });
        viewUbicacion=(View)view.findViewById(R.id.viewUbicacion);

        lblEspecialidades=(CustomFontTextView)view.findViewById(R.id.lblEspecialidades);
        lblEspecialidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblEspecialidades();
            }
        });
        viewEspecialidades=(View)view.findViewById(R.id.viewEspecialidades);
        viewEspecialidadesTotal=(View)view.findViewById(R.id.viewEspecialidadesTotal);

        lblSeguros=(CustomFontTextView)view.findViewById(R.id.lblSeguros);

        viewSeguros=(View)view.findViewById(R.id.viewSeguros);

        viewSegurosTotal=(View)view.findViewById(R.id.viewSegurosTotal);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.getActivity());

        // Showing status
        if(status!= ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this.getActivity(), requestCode);
            dialog.show();

        }else { // Google Play Services are available

            // Getting reference to the SupportMapFragment of activity_main.xml
            mapView = (MapView) view.findViewById(R.id.mapview);
            mapView.onCreate(savedInstanceState);

            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    googleMap = mMap;
                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                    BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory.fromResource(R.drawable.ic_ubicacion);
                    LatLng cordenada=new LatLng(objClinica.getDou_latitud(), objClinica.getDou_longitud());
                    googleMap.addMarker(new MarkerOptions().icon(bitmapDescriptor).position(cordenada).title(objClinica.getStr_nombre()));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cordenada, 16));

                }
            });
        }
        Buscar(Id);
        return view;
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void lblSeguros()
    {
        if(viewSeguros.getVisibility()==View.GONE)
        {
            viewSeguros.setVisibility(View.VISIBLE);
            lblSeguros.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_open), null);
        }
        else
        {
            viewSeguros.setVisibility(View.GONE);
            lblSeguros.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_closed), null);
        }
    }
    public void lblEspecialidades()
    {
        if(viewEspecialidades.getVisibility()==View.GONE)
        {
            viewEspecialidades.setVisibility(View.VISIBLE);
            lblEspecialidades.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_open), null);
        }
        else
        {
            viewEspecialidades.setVisibility(View.GONE);
            lblEspecialidades.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_closed), null);
        }
    }
    public void lblUbicacion()
    {
        if(viewUbicacion.getVisibility()==View.GONE)
        {
            viewUbicacion.setVisibility(View.VISIBLE);
            lblUbicacion.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_open), null);
        }
        else
        {
            viewUbicacion.setVisibility(View.GONE);
            lblUbicacion.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_closed), null);
        }
    }

    public void lblDatos()
    {
        if(viewDatos.getVisibility()==View.GONE)
        {
            viewDatos.setVisibility(View.VISIBLE);
            lblDatos.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_open), null);
        }
        else
        {
            viewDatos.setVisibility(View.GONE);
            lblDatos.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_closed), null);
        }
    }


    public void Buscar(int id)
    {
        listEspecialidades= clsClinicaEspecialidadDAO.BuscarXClinica(this.getActivity(), id);

        if(listEspecialidades!=null && listEspecialidades.size()>0)
        {
            for(int i=0;i<listEspecialidades.size();i++)
            {
                addEspecialidades(i);
            }

        }
        else {
            viewEspecialidadesTotal.setVisibility(View.GONE);
        }

        listSeguro= clsSeguroDAO.ListarXClinica(this.getActivity(), id);

        if(listSeguro!=null && listSeguro.size()>0)
        {
            adaptadorSeguros = new AdaptadorSeguros(this.getActivity());
            listViewSeguros.setAdapter(adaptadorSeguros);
            Utilidades.setListViewHeightBasedOnChildren(listViewSeguros);
            lblSeguros.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lblSeguros();
                }
            });

        }
        else {
            viewSegurosTotal.setVisibility(View.GONE);
        }

    }




    class AdaptadorSeguros extends ArrayAdapter {

        Activity context;

        AdaptadorSeguros(Activity context) {
            super(context, R.layout.list_seguros, listSeguro);
            this.context = context;
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_seguros, null);

            CustomFontTextView lblNombre = (CustomFontTextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText(listSeguro.get(position).getStr_nombre());

            ImageView image = (ImageView)item.findViewById(R.id.image);

            if(listSeguro.get(position).getByte_logo()!=null)
                image.setImageBitmap(Utilidades.getBitmap(listSeguro.get(position).getByte_logo()));

            return(item);
        }
    }



    private void addEspecialidades(int position) {
        ViewGroup item = (ViewGroup) LayoutInflater.from(this.getContext()).inflate(
                R.layout.list_especialidades, vgEspecialidades, false);


        CustomFontTextView lblNombre = (CustomFontTextView)item.findViewById(R.id.lblNombre);
        lblNombre.setText(listEspecialidades.get(position).getObjEspecialidad().getStr_nombre());
//
        CustomFontTextView lblDetalle = (CustomFontTextView)item.findViewById(R.id.lblDetalle);
        lblDetalle.setText(listEspecialidades.get(position).getObjEspecialidad().getStr_descripcion());

        CustomFontTextView lblInicio = (CustomFontTextView)item.findViewById(R.id.lblInicio);
        lblInicio.setText(Utilidades.hora.format(listEspecialidades.get(position).getDat_hora_inicio()));

        CustomFontTextView lblFin = (CustomFontTextView)item.findViewById(R.id.lblFin);
        lblFin.setText(Utilidades.hora.format(listEspecialidades.get(position).getDat_hora_fin()));

        CustomFontTextView lblAtencion = (CustomFontTextView)item.findViewById(R.id.lblAtencion);
        lblAtencion.setText(listEspecialidades.get(position).getStr_detalle_horario());
//
        vgEspecialidades.addView(item, position);
    }


}
