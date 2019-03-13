package com.med.finder.cliente.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

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
import com.med.finder.cliente.dao.clsCitaPacienteDAO;
import com.med.finder.cliente.dao.clsDoctorDAO;
import com.med.finder.cliente.dao.clsEspecialidadDAO;
import com.med.finder.cliente.dao.clsPacienteDAO;
import com.med.finder.cliente.entidades.clsCitaPaciente;
import com.med.finder.cliente.entidades.clsDoctor;
import com.med.finder.cliente.entidades.clsPaciente;
import com.med.finder.cliente.utilidades.CustomFontTextView;
import com.med.finder.cliente.utilidades.Utilidades;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorInfoFragment extends Fragment{

    private MenuItem someMenuItem;
    private CustomFontTextView lblDatos;
    private View viewDatos;

    private CustomFontTextView lblUbicacion;
    private View viewUbicacion;


    private clsDoctor objDoctor = null;
    private int Id;
    MapView mapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_info, container, false);

        Id=getArguments().getInt("id");

        objDoctor= clsDoctorDAO.Buscar(this.getActivity(), Id);
        CircleImageView image = (CircleImageView)view.findViewById(R.id.image);
        if(objDoctor.getByte_foto()!=null)
            image.setImageBitmap(Utilidades.getBitmap(objDoctor.getByte_foto()));

        CustomFontTextView lblNombre = (CustomFontTextView)view.findViewById(R.id.lblNombre);
        lblNombre.setText(objDoctor.getStr_apellido_paterno()+" "+objDoctor.getStr_apellido_materno()+" "+objDoctor.getStr_nombres());

        CustomFontTextView lblColegiatura = (CustomFontTextView)view.findViewById(R.id.lblColegiatura);
        lblColegiatura.setText(objDoctor.getStr_codigo_colegiatura());

        CustomFontTextView lblDNI = (CustomFontTextView)view.findViewById(R.id.lblDNI);
        lblDNI.setText(objDoctor.getStr_dni());

        CustomFontTextView lblTelefono = (CustomFontTextView)view.findViewById(R.id.lblTelefono);
        lblTelefono.setText(objDoctor.getStr_telefono());

        CustomFontTextView lblDireccion = (CustomFontTextView)view.findViewById(R.id.lblDireccion);
        lblDireccion.setText(objDoctor.getStr_direccion());

        CustomFontTextView lblDetalle = (CustomFontTextView)view.findViewById(R.id.lblDetalle);
        lblDetalle.setText(objDoctor.getStr_direccion_detalle());


        RatingBar rtbPuntos = (RatingBar)view.findViewById(R.id.rtbPuntos);
        rtbPuntos.setRating(objDoctor.getInt_puntuje());



        CustomFontTextView lblEspecializacion = (CustomFontTextView)view.findViewById(R.id.lblEspecializacion);
        lblEspecializacion.setText(clsEspecialidadDAO.Buscar(this.getActivity(), objDoctor.getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());

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
                    LatLng cordenada=new LatLng(objDoctor.getDou_latitud(), objDoctor.getDou_longitud());
                    googleMap.addMarker(new MarkerOptions().icon(bitmapDescriptor).position(cordenada));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cordenada, 16));

                }
            });


        }
        setHasOptionsMenu(true);

/*
        if(objDoctor.isBol_favorito())
        {
            btn_Favorito.setBackgroundResource(R.drawable.favorito_on);

            titulo="Quitar de Favoritos";
        }
        else
        {
            btn_Favorito.setBackgroundResource(R.drawable.favorito_off);
            titulo="Agregar a Favoritos";
        }*/
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.doctor, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_citas:
                // do s.th.
                    btnCita();
                return true;
            case R.id.action_favoritos:
                final Dialog dialog = new Dialog(this.getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog);
                CustomFontTextView lblMensaje = (CustomFontTextView)dialog.findViewById(R.id.lblMensaje);
                if(objDoctor.isBol_favorito()) {
                    lblMensaje.setText(getString(R.string.str_favorito_off));
                }else
                {
                    lblMensaje.setText(getString(R.string.str_favorito_on));
                }

                FloatingActionButton btnAceptar = (FloatingActionButton) dialog.findViewById(R.id.btnAceptar);
                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(objDoctor.isBol_favorito()) {
                            someMenuItem.setIcon(R.drawable.action_favorito_off);
                            objDoctor.setBol_favorito(false);
                        }
                        else {
                            someMenuItem.setIcon(R.drawable.action_favorito_on);
                            objDoctor.setBol_favorito(true);
                        }
                        dialog.dismiss();
                    }
                });
                FloatingActionButton btnCancelar = (FloatingActionButton) dialog.findViewById(R.id.btnCancelar);
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        someMenuItem = menu.findItem(R.id.action_favoritos);
        if(objDoctor.isBol_favorito())
            someMenuItem.setIcon(R.drawable.action_favorito_on);
        else
            someMenuItem.setIcon(R.drawable.action_favorito_off);
    }


    public void btnCita()
    {

        if(clsCitaPacienteDAO.BuscarXEstado(this.getActivity())==null)
        {
            final Dialog dialog = new Dialog(this.getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_solicitar_cita);
            dialog.setCancelable(false);
            final EditText txtDetalle = (EditText) dialog.findViewById(R.id.txtDetalle);
            txtDetalle.setText("");

            Spinner ComboPaciente = (Spinner)dialog.findViewById(R.id.ComboPaciente);
            final List<clsPaciente> lista = clsPacienteDAO.Listar(this.getActivity());
            lista.add(0,new clsPaciente(0,getString(R.string.str_seleccione_paciente)));

            ArrayAdapter<clsPaciente> adapter = new ArrayAdapter<clsPaciente>(this.getActivity(),R.layout.spinner,lista);
            adapter.setDropDownViewResource(R.layout.spinner_vista);
            ComboPaciente.setAdapter(adapter);
            ComboPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //objPaciente=lista.get(position);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                    //User selected same item. Nothing to do.
                }
            });
            ComboPaciente.setSelection(0);
//
            FloatingActionButton btnAceptar = (FloatingActionButton) dialog.findViewById(R.id.btnAceptar);
            btnAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!txtDetalle.getText().toString().equals("")) {
                        clsCitaPaciente entidad = new clsCitaPaciente();
                        //entidad.setObjPaciente(objPaciente);
                        entidad.setObjDoctor(objDoctor);
                        entidad.setStr_detalle(txtDetalle.getText().toString());

/*
                        String id = http.insertarCitaPaciente(entidad);
                        if (!id.trim().equals("0")) {
                            entidad.setInt_id_cita_paciente(Integer.parseInt(id));
                            clsCitaPacienteDAO.Agregar(InfDoctorTabActivity.this, entidad);
                            Toast.makeText(InfDoctorTabActivity.this, "Se Registro Correctamente", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(InfDoctorTabActivity.this, MenuActivity.class);
                            startActivity(i);
                        } else
                        {
                            //Toast.makeText(InfDoctorTabActivity.this, "Error al Insertar intentelo mas tarde", Toast.LENGTH_SHORT).show();
                        }
                        */
                        dialog.dismiss();
                    }
                    else {
                      //  Toast.makeText(InfDoctorTabActivity.this, "Ingrese un Motivo", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            FloatingActionButton btnCancelar = (FloatingActionButton) dialog.findViewById(R.id.btnCancelar);
            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();

        }
        else {
          //  Toast.makeText(this, "Tiene una Cita Esperando en espera de respuesta", Toast.LENGTH_SHORT).show();
        }




    }

}
