package com.app.med.finder.fragment;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TextView;

import com.app.med.finder.dao.clsCasosSaludDAO;
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.entidades.clsCasosSalud;
import com.app.med.finder.entidades.clsEspecialidad;
import com.app.med.finder.ui.MainActivity;
import com.app.med.finder.ui.R;
import com.app.med.finder.utilidades.clsUtilidades;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class CasosSaludFragment extends Fragment {

    private List<clsCasosSalud> listCasosTemp;
    private List<clsCasosSalud> listCasos;
    private Adaptador adaptador;
    private ListView list;
    private EditText txtFiltro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_especialidades, container, false);

        list = (ListView)view.findViewById(R.id.list);

        list = (ListView)view.findViewById(R.id.list);
        listCasos= clsCasosSaludDAO.Listar(this.getActivity());
        txtFiltro = (EditText)view.findViewById(R.id.txtFiltro);
        txtFiltro.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                Buscar(s.toString().trim());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        Buscar("");
        return view;
    }




    public void Buscar(String filtro)
    {

        listCasosTemp=new ArrayList<clsCasosSalud>();
        for(clsCasosSalud entidad:listCasos)
            if(entidad.getStr_tema().toLowerCase().indexOf(filtro.toLowerCase()) != -1)
                listCasosTemp.add(entidad);


        adaptador = new Adaptador(this.getActivity());

        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                Bundle args = new Bundle();
                args.putInt("id", listCasosTemp.get(posicion).getInt_id_casos_salud());
                Fragment fragment = new RespuestaCasosSaludFragment();
                fragment.setArguments(args);
                ((MainActivity) getActivity()).setFragment(fragment);

            }
        });

    }



    class Adaptador extends ArrayAdapter {

        Activity context;

        Adaptador(Activity context) {
            super(context, R.layout.list_casos_salud, listCasosTemp);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_casos_salud, null);

            TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText(listCasosTemp.get(position).getStr_tema());

            TextView lblInicio = (TextView)item.findViewById(R.id.lblInicio);
            lblInicio.setText(clsUtilidades.dateFormatter.format(listCasosTemp.get(position).getDat_inicio()));

            TextView lblFin = (TextView)item.findViewById(R.id.lblFin);
            lblFin.setText(clsUtilidades.dateFormatter.format(listCasosTemp.get(position).getDat_fin()));

            return(item);
        }
    }
}
