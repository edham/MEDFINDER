package com.med.finder.doctor.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.med.finder.doctor.R;
import com.med.finder.doctor.activity.MainActivity;
import com.med.finder.doctor.conexion.CitaAprobarPacienteHTTP;
import com.med.finder.doctor.dao.clsCitaPacienteDAO;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.dao.clsEspecialidadDAO;
import com.med.finder.doctor.dao.clsPacienteDAO;
import com.med.finder.doctor.entidades.clsCitaPaciente;
import com.med.finder.doctor.entidades.clsDoctor;
import com.med.finder.doctor.entidades.clsPaciente;
import com.med.finder.doctor.entidades.clsRespuestaCasosSalud;
import com.med.finder.doctor.utilidades.CustomFontTextView;
import com.med.finder.doctor.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class CitasFragment extends Fragment {

    private List<clsCitaPaciente> listCasosTemp;
    private List<clsCitaPaciente> listCasos;
    private Adaptador adaptador;
    private ListView list;
    private EditText txtFiltro;
    private  clsDoctor doctor;
    private clsCitaPaciente entidad;
    public ProgressDialog pd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_familiares, container, false);
        doctor= clsDoctorDAO.Buscar(this.getContext());

        list = (ListView)view.findViewById(R.id.list);

        list = (ListView)view.findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
              /*
                Bundle args = new Bundle();
                args.putInt("id", listCasosTemp.get(posicion).getInt_id_casos_salud());
                Fragment fragment = new RespuestaCasosSaludFragment();
                fragment.setArguments(args);
                ((MainActivity) getActivity()).setFragment(fragment);
*/
            }
        });
        View viewButtom= (View)view.findViewById(R.id.viewButtom);
        viewButtom.setVisibility(View.GONE);
        listCasos= clsCitaPacienteDAO.Listar(this.getActivity());
        txtFiltro = (EditText)view.findViewById(R.id.txtFiltro);
        txtFiltro.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Buscar(s.toString().trim());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        ((MainActivity) getActivity()).setTitle(getString(R.string.nav_citas));

        Buscar("");
        return view;
    }




    public void Buscar(String filtro)
    {

        listCasosTemp=new ArrayList<clsCitaPaciente>();
        for(clsCitaPaciente entidad:listCasos)
         //   if(entidad.getStr_tema().toLowerCase().indexOf(filtro.toLowerCase()) != -1)
                listCasosTemp.add(entidad);


        adaptador = new Adaptador(this.getActivity());

        list.setAdapter(adaptador);


    }




    class Adaptador extends ArrayAdapter {

        Activity context;

        Adaptador(Activity context) {
            super(context,R.layout.list_citas, listCasosTemp);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            final int posicion=position;
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_citas, null);

            View viewCancelar = (View)item.findViewById(R.id.viewCancelar);
            TextView lblTipo = (TextView)item.findViewById(R.id.lblTipo);
            if(listCasosTemp.get(position).getInt_estado()==0)
                lblTipo.setText("CITA ACTIVADO");
            else if(listCasosTemp.get(position).getInt_estado()==1)
            {
                lblTipo.setText("CITA PENDIENTE");
                viewCancelar.setVisibility(View.VISIBLE);
                if(listCasosTemp.get(position).getDat_atencion().getTime()<new Date().getTime())
                {
                    lblTipo.setText("CITA FINALIZADA");
                    viewCancelar.setVisibility(View.GONE);
                }
            }else
            {
                lblTipo.setText("CITA CANCELADA");
            }
            TextView lblDetalle = (TextView)item.findViewById(R.id.lblDetalle);
            lblDetalle.setText(listCasosTemp.get(position).getStr_detalle());

            TextView lblRespuesta = (TextView)item.findViewById(R.id.lblRespuesta);
            lblRespuesta.setText(listCasosTemp.get(position).getStr_respuesta());



            TextView lblFecha = (TextView)item.findViewById(R.id.lblFecha);
            lblFecha.setText(Utilidades.dateFormatter.format(listCasosTemp.get(position).getDat_atencion()));

            TextView lblHora = (TextView)item.findViewById(R.id.lblHora);
            lblHora.setText(Utilidades.hourFormatter.format(listCasosTemp.get(position).getDat_atencion()));

            Button btnCancelar = (Button)item.findViewById(R.id.btnCancelar);
            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cancelar(posicion);
//
                }
            });
            clsPaciente paciente= clsPacienteDAO.Buscar(context, listCasosTemp.get(position).getObjPaciente().getInt_id_paciente());

            if(paciente!=null)
            {
                if(paciente!=null)
                {
                    CustomFontTextView lblNombre = (CustomFontTextView)item.findViewById(R.id.lblNombre);
                    lblNombre.setText(paciente.toString());

                    CustomFontTextView lblDNI = (CustomFontTextView)item.findViewById(R.id.lblDNI);
                    lblDNI.setText(paciente.getStr_dni());

                    CustomFontTextView lblSexo = (CustomFontTextView)item.findViewById(R.id.lblSexo);
                    lblSexo.setText(((paciente.isBol_sexo()) ? getText(R.string.str_hombre):getText(R.string.str_mujer)));

                    CustomFontTextView lblEdad = (CustomFontTextView)item.findViewById(R.id.lblEdad);
                    lblEdad.setText(""+Utilidades.getEdad(paciente.getDat_fecha_nacimiento()));

                    CustomFontTextView lblEstatura = (CustomFontTextView)item.findViewById(R.id.lblEstatura);
                    lblEstatura.setText(""+paciente.getInt_estatura());

                }
            }

            return(item);
        }
    }

    public void Cancelar(int pos){
        entidad=listCasosTemp.get(pos);
        final Dialog dialog = new Dialog(this.getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_responder_caso_salud);
        dialog.setCancelable(false);
        TextView lblCasoSalud = (TextView)dialog.findViewById(R.id.lblCasoSalud);
        lblCasoSalud.setText(getText(R.string.str_cancelar_cita));
        final EditText txtDetalle = (EditText) dialog.findViewById(R.id.txtDetalle);
        txtDetalle.setText("");


//
        FloatingActionButton btnAceptar = (FloatingActionButton) dialog.findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtDetalle.getText().toString().equals("")) {
                    entidad.setInt_estado(3);
                    entidad.setStr_respuesta(txtDetalle.getText().toString());
                    registrar();
                    dialog.dismiss();
                }
                else {
                    Utilidades.alert(CitasFragment.this.getContext(), getString(R.string.str_ingrese_asunto));
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
    private void registrar(){

        if ( Utilidades.checkPermissions(this.getActivity())) {
            pd = new ProgressDialog(this.getActivity());
            pd.setTitle("Cargando Datos");
            pd.setMessage("Espere un momento");
            pd.setCancelable(false);
            pd.show();
            new Thread() {
                public void run() {
                    Message message = handlerCargar.obtainMessage();
                    Bundle bundle = new Bundle();
                    int rpta=0;
                    try {

                        CitaAprobarPacienteHTTP http = new CitaAprobarPacienteHTTP();

                        http.execute(entidad);
                        String result = http.get();
                        if (!result.equals("")) {
                            JSONObject entidadJSON = new JSONObject(result);
                            rpta=entidadJSON.getInt("rpta");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    bundle.putInt("rpta",rpta);
                    message.setData(bundle);
                    handlerCargar.sendMessage(message);
                }
            }.start();
        }
    }

    final Handler handlerCargar=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            Bundle bundle = msg.getData();
            if(bundle.getInt("rpta")==1) {
                Utilidades.alert(CitasFragment.this.getActivity(), getString(R.string.str_registro_correcto));
                clsCitaPacienteDAO.Actualizar(CitasFragment.this.getActivity(), entidad);
                Buscar("");
            }else
            {
                Utilidades.alert(CitasFragment.this.getActivity(), getString(R.string. str_error_registrar));

            }
        }
    };
}
