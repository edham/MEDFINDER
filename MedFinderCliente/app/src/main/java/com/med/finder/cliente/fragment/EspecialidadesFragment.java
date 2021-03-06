package com.med.finder.cliente.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.med.finder.cliente.R;
import com.med.finder.cliente.activity.MainActivity;
import com.med.finder.cliente.dao.clsEspecialidadDAO;
import com.med.finder.cliente.entidades.clsEspecialidad;

import java.util.ArrayList;
import java.util.List;


public class EspecialidadesFragment extends Fragment {

    private EditText txtFiltro;
    private ListView list;
    private List<clsEspecialidad> listEspecialidades;
    private List<clsEspecialidad> listEspecialidadesTotal;
    private Adaptador adaptador;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_familiares, container, false);

        list = (ListView)view.findViewById(R.id.list);
        View viewButtom= (View)view.findViewById(R.id.viewButtom);
        viewButtom.setVisibility(View.GONE);

        listEspecialidadesTotal= clsEspecialidadDAO.Listar(this.getActivity());
        txtFiltro = (EditText) view.findViewById(R.id.txtFiltro);
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
        ((MainActivity) getActivity()).verTitulo(7);
        Buscar("");
        return view;
    }



    public void Buscar(String filtro)
    {

        listEspecialidades = new ArrayList<clsEspecialidad>();

        for(clsEspecialidad entidad:listEspecialidadesTotal)
            if(entidad.getStr_nombre().toLowerCase().indexOf(filtro.toLowerCase()) != -1)
                listEspecialidades.add(entidad);
        adaptador = new Adaptador(this.getActivity());
        list.setAdapter(adaptador);
    }


    class Adaptador extends ArrayAdapter {

        Activity context;

        Adaptador(Activity context) {
            super(context, R.layout.list_especialidades_info, listEspecialidades);
            this.context = context;
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_especialidades_info, null);
//
            TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText(listEspecialidades.get(position).getStr_nombre());
//
            TextView lblDetalle = (TextView)item.findViewById(R.id.lblDetalle);
            lblDetalle.setText(listEspecialidades.get(position).getStr_descripcion());
//

            return(item);
        }
    }


}
