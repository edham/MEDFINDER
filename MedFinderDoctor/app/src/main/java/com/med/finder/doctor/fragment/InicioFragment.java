package com.med.finder.doctor.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.med.finder.doctor.R;
import com.med.finder.doctor.activity.MainActivity;
import com.med.finder.doctor.dao.clsClinicaDAO;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.dao.clsEspecialidadDAO;
import com.med.finder.doctor.entidades.clsClinica;
import com.med.finder.doctor.entidades.clsDoctor;
import com.med.finder.doctor.entidades.clsEspecialidad;
import com.med.finder.doctor.utilidades.CustomFontTextView;
import com.med.finder.doctor.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


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
    private View viewFavoritos;
    private CheckBox chbFavoritos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        list = (ListView)view.findViewById(R.id.list);
        viewFavoritos = (View)view.findViewById(R.id.viewFavoritos);
        ComboBusqueda = (Spinner)view.findViewById(R.id.ComboBusqueda);
        ComboEspecialidad = (Spinner)view.findViewById(R.id.ComboEspecialidad);
        chbFavoritos = (CheckBox)view.findViewById(R.id.chbFavoritos);
        chbFavoritos .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar("");
            }
        });
        TipoDLL();
        EspecialidadDDL();
        Buscar("");

        ((MainActivity) getActivity()).verTitulo(1);
        return view;
    }


    public void EspecialidadDDL (){
        listEspecialidad= clsEspecialidadDAO.Listar(this.getActivity());
        listEspecialidad.add(0,new clsEspecialidad(0,"Todas las Especialidades"));

        ArrayAdapter<clsEspecialidad> adapter = new ArrayAdapter<clsEspecialidad>(this.getActivity(),
                R.layout.spinner,listEspecialidad);
        adapter.setDropDownViewResource(R.layout.spinner_vista);
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


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),R.layout.spinner,lista);
        adapter.setDropDownViewResource(R.layout.spinner_vista);



        ComboBusqueda.setAdapter(adapter);
        ComboBusqueda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    viewFavoritos.setVisibility(View.VISIBLE);
                    busqueda=true;
                    Buscar("");

                }
                else
                {
                    viewFavoritos.setVisibility(View.GONE);
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
            listDoctores= clsDoctorDAO.Listar(this.getActivity(), idEspecialidad,chbFavoritos.isChecked());

                adaptadorDoctor = new AdaptadorDoctor(this.getActivity());

                list.setAdapter(adaptadorDoctor);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                        ((MainActivity) getActivity()).posFragmnet=1;
                        Bundle args = new Bundle();
                        args.putInt("id", listDoctores.get(posicion).getInt_id_doctor());
                      //  Fragment fragment = new DoctorInfoFragment();
                        //fragment.setArguments(args);
                     //   ((MainActivity) getActivity()).setFragment(fragment);
                    }
                });
        }
        else
        {
            listClinica= clsClinicaDAO.Listar(this.getActivity(), idEspecialidad);

                adaptadorClinica = new AdaptadorClinica(this.getActivity());

                list.setAdapter(adaptadorClinica);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                        ((MainActivity) getActivity()).posFragmnet=1;
                        Bundle args = new Bundle();
                        args.putInt("id", listClinica.get(posicion).getInt_id_clinica());
                   //     Fragment fragment = new ClinicaInfoFragment();
                     //   fragment.setArguments(args);
                       // ((MainActivity) getActivity()).setFragment(fragment);
                    }
                });
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

            CustomFontTextView lblNombre = (CustomFontTextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText("Dr. "+listDoctores.get(position).getStr_apellido_paterno()+" "+listDoctores.get(position).getStr_apellido_materno()+" "+listDoctores.get(position).getStr_nombres());

            CustomFontTextView lblEspecialidad = (CustomFontTextView)item.findViewById(R.id.lblEspecialidad);
            lblEspecialidad.setText(clsEspecialidadDAO.Buscar(context,listDoctores.get(position).getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());

            RatingBar rtbPuntos = (RatingBar)item.findViewById(R.id.rtbPuntos);
            rtbPuntos.setRating(listDoctores.get(position).getInt_puntuje());

            CircleImageView image = (CircleImageView)item.findViewById(R.id.image);
            if(listDoctores.get(position).getByte_foto()!=null)
                image.setImageBitmap(Utilidades.getBitmap(listDoctores.get(position).getByte_foto()));
            if(!listDoctores.get(position).isBol_favorito())
            {
                ImageView imageFavorito = (ImageView) item.findViewById(R.id.imageFavorito);
                imageFavorito.setVisibility(View.GONE);

            }

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

            CustomFontTextView lblNombre = (CustomFontTextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText(listClinica.get(position).getStr_nombre());

            CustomFontTextView lblDetalle = (CustomFontTextView)item.findViewById(R.id.lblDetalle);
            lblDetalle.setText(listClinica.get(position).getStr_slogan());


            ImageView image = (ImageView)item.findViewById(R.id.image);
            if(listClinica.get(position).getByte_logo()!=null)
                image.setImageBitmap(Utilidades.getBitmap(listClinica.get(position).getByte_logo()));

            return(item);
        }
    }


}
