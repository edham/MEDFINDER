package com.med.finder.doctor.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.med.finder.doctor.R;
import com.med.finder.doctor.activity.MainActivity;
import com.med.finder.doctor.dao.clsCitaPacienteDAO;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.dao.clsEspecialidadDAO;
import com.med.finder.doctor.entidades.clsCitaPaciente;
import com.med.finder.doctor.entidades.clsDoctor;
import com.med.finder.doctor.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CitasFragment extends Fragment {

    private List<clsCitaPaciente> listCasosTemp;
    private List<clsCitaPaciente> listCasos;
    private Adaptador adaptador;
    private ListView list;
    private EditText txtFiltro;
    private  clsDoctor doctor;
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

            TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText("Dr. "+doctor.getStr_apellido_paterno()+" "+doctor.getStr_apellido_materno()+" "+doctor.getStr_nombres());

            TextView lblEspecialidad = (TextView)item.findViewById(R.id.lblEspecialidad);
            lblEspecialidad.setText(clsEspecialidadDAO.Buscar(context,doctor.getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());

            TextView lblTipo = (TextView)item.findViewById(R.id.lblTipo);
            if(listCasosTemp.get(position).getInt_estado()==0)
                lblTipo.setText("CITA ACTIVADO");
            else if(listCasosTemp.get(position).getInt_estado()==1)
            {
                lblTipo.setText("CITA PENDIENTE");

                if(listCasosTemp.get(position).getDat_atencion().getTime()<new Date().getTime())
                {
                    lblTipo.setText("CITA FINALIZADA");
                    View view = (View)item.findViewById(R.id.view);
                    view.setVisibility(View.GONE);
                }
            }
            TextView lblDetalle = (TextView)item.findViewById(R.id.lblDetalle);
            lblDetalle.setText(listCasosTemp.get(position).getStr_detalle());

            ImageView image = (ImageView)item.findViewById(R.id.image);
            if(doctor.getByte_foto()!=null) {
                image.setImageBitmap(Utilidades.getBitmap(doctor.getByte_foto()));

            }
//
            TextView lblFecha = (TextView)item.findViewById(R.id.lblFecha);
            lblFecha.setText(Utilidades.dateFormatter.format(listCasosTemp.get(position).getDat_atencion()));

            TextView lblHora = (TextView)item.findViewById(R.id.lblHora);
            lblHora.setText(Utilidades.hourFormatter.format(listCasosTemp.get(position).getDat_atencion()));



            Button btnCancelar = (Button)item.findViewById(R.id.btnCancelar);
            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cancelar(listCasosTemp.get(posicion).getInt_id_cita_paciente());
//
                }
            });


            return(item);
        }
    }

    private void Cancelar(int id)
    {
        final int IdCita=id;
        /*
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("CANCELAR CITA");
        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String cadena= http.cancelarCitaPaciente(IdCita);
                if(!cadena.trim().equals("0"))
                {
                    clsCitaPacienteDAO.BorrarXId(CitasActivity.this, IdCita);
                    Buscar();

                }

            }});
        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }});
        alert.show();
*/

    }
}
