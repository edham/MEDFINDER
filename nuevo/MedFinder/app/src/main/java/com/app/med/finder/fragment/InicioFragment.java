package com.app.med.finder.fragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.med.finder.dao.clsClinicaDAO;
import com.app.med.finder.dao.clsDoctorDAO;
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.entidades.clsClinica;
import com.app.med.finder.entidades.clsDoctor;
import com.app.med.finder.entidades.clsEspecialidad;
import com.app.med.finder.ui.MainActivity;
import com.app.med.finder.ui.R;
import com.app.med.finder.utilidades.clsUtilidades;

import java.util.ArrayList;
import java.util.List;


public class InicioFragment extends Fragment {

    private List<clsDoctor> listDoctores;
    private List<clsClinica> listClinica;
    private List<clsEspecialidad> listEspecialidad;
    private AdaptadorDoctor adaptadorDoctor;
    private AdaptadorClinica adaptadorClinica;

    private ListView list;
    private Spinner ComboBusqueda;
    private Spinner ComboEspecialidad;
    private boolean busqueda=true;
    private int idEspecialidad=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        list = (ListView)view.findViewById(R.id.list);
        ComboBusqueda = (Spinner)view.findViewById(R.id.ComboBusqueda);
        ComboEspecialidad = (Spinner)view.findViewById(R.id.ComboEspecialidad);

        TipoDLL();
        EspecialidadDDL();
        Buscar("");
        return view;
    }


    public void EspecialidadDDL (){
        listEspecialidad= clsEspecialidadDAO.Listar(this.getActivity());
        listEspecialidad.add(0,new clsEspecialidad(0,"Todas las Especialidades"));

        ArrayAdapter<clsEspecialidad> adapter = new ArrayAdapter<clsEspecialidad>(this.getActivity(),
                android.R.layout.simple_spinner_item,listEspecialidad);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ComboEspecialidad.setAdapter(adapter);
        ComboEspecialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idEspecialidad=listEspecialidad.get(position).getInt_id_especialidad();
                Buscar("");
            }
            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
        ComboEspecialidad.setSelection(0);
    }

    public void TipoDLL (){

        List<String> lista =new ArrayList<String>();
        lista.add("Doctores");
        lista.add("Clinicas");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ComboBusqueda.setAdapter(adapter);
        ComboBusqueda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    busqueda=true;
                    Buscar("");
                }
                else
                {
                    busqueda=false;
                    Buscar("");
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
        ComboBusqueda.setSelection(0);
    }
    public void Buscar(String buscar)
    {

        if(busqueda)
        {
            listDoctores= clsDoctorDAO.Listar(this.getActivity(), idEspecialidad);

            if(listDoctores!=null && listDoctores.size()>0)
            {
                adaptadorDoctor = new AdaptadorDoctor(this.getActivity());

                list.setAdapter(adaptadorDoctor);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                        Bundle args = new Bundle();
                        args.putInt("id", listDoctores.get(posicion).getInt_id_doctor());
                        Fragment fragment = new DoctorInfoFragment();
                        fragment.setArguments(args);
                        ((MainActivity) getActivity()).setFragment(fragment);
                    }
                });

            }
        }
        else
        {
            listClinica= clsClinicaDAO.Listar(this.getActivity(), idEspecialidad);
            if(listClinica!=null && listClinica.size()>0)
            {
                adaptadorClinica = new AdaptadorClinica(this.getActivity());

                list.setAdapter(adaptadorClinica);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                        Bundle args = new Bundle();
                        args.putInt("id", listClinica.get(posicion).getInt_id_clinica());
                        Fragment fragment = new ClinicaInfoFragment();
                        fragment.setArguments(args);
                        ((MainActivity) getActivity()).setFragment(fragment);

                    }
                });

            }

        }
    }


    class AdaptadorDoctor extends ArrayAdapter {

        Activity context;

        AdaptadorDoctor(Activity context) {
            super(context, R.layout.list_doctores, listDoctores);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_doctores, null);

            TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText("Dr. "+listDoctores.get(position).getStr_apellido_paterno()+" "+listDoctores.get(position).getStr_apellido_materno()+" "+listDoctores.get(position).getStr_nombres());

            TextView lblEspecialidad = (TextView)item.findViewById(R.id.lblEspecialidad);
            lblEspecialidad.setText(clsEspecialidadDAO.Buscar(context,listDoctores.get(position).getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());

            RatingBar rtbPuntos = (RatingBar)item.findViewById(R.id.rtbPuntos);
            rtbPuntos.setRating(listDoctores.get(position).getInt_puntuje());

            ImageView image = (ImageView)item.findViewById(R.id.image);
            if(listDoctores.get(position).getByte_foto()!=null)
                image.setImageBitmap(clsUtilidades.getBitmap(listDoctores.get(position).getByte_foto()));

            return(item);
        }
    }


    class AdaptadorClinica extends ArrayAdapter {

        Activity context;

        AdaptadorClinica(Activity context) {
            super(context, R.layout.list_clinicas, listClinica);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_clinicas, null);

            TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText(listClinica.get(position).getStr_nombre());

            TextView lblDetalle = (TextView)item.findViewById(R.id.lblDetalle);
            lblDetalle.setText(listClinica.get(position).getStr_slogan());


            ImageView image = (ImageView)item.findViewById(R.id.image);
            if(listClinica.get(position).getByte_logo()!=null)
                image.setImageBitmap(clsUtilidades.getBitmap(listClinica.get(position).getByte_logo()));

            return(item);
        }
    }


}
