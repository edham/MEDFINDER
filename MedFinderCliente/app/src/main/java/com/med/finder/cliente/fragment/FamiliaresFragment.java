package com.med.finder.cliente.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
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

import com.med.finder.cliente.R;
import com.med.finder.cliente.activity.MainActivity;
import com.med.finder.cliente.dao.clsPacienteDAO;
import com.med.finder.cliente.entidades.clsPaciente;
import com.med.finder.cliente.utilidades.CustomFontTextView;
import com.med.finder.cliente.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class FamiliaresFragment extends Fragment {

    private List<clsPaciente> listPacienteTemp;
    private List<clsPaciente> listPaciente;
    private Adaptador adaptador;
    private ListView listMisContactos;
    private EditText txtFiltro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_familiares, container, false);
        listMisContactos = (ListView)view.findViewById(R.id.list);
        listMisContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                Bundle args = new Bundle();
                args.putInt("idPaciente", listPacienteTemp.get(posicion).getInt_id_paciente());
                Fragment fragment = new FamiliarFragment();
                fragment.setArguments(args);
               ((MainActivity) getActivity()).setFragment(fragment);

            }
        });
        Button btnAgregar = (Button) view.findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAgregar();
            }
        });
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
        ((MainActivity) getActivity()).verTitulo(4);

        Buscar("");

        return view;
    }

    public void Buscar(String filtro)
    {
        listPaciente= clsPacienteDAO.Listar(this.getContext());
        listPacienteTemp=new ArrayList<clsPaciente>();
        for(clsPaciente entidad:listPaciente)
            if(entidad.toString().toLowerCase().indexOf(filtro.toLowerCase()) != -1)
                listPacienteTemp.add(entidad);
        adaptador = new Adaptador(this.getActivity());
        listMisContactos.setAdapter(adaptador);
    }

    class Adaptador extends ArrayAdapter {

        Activity context;

        Adaptador(Activity context) {
            super(context, R.layout.list_familiares, listPacienteTemp);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final int posicion=position;
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_familiares, null);
            CustomFontTextView lblNombre = (CustomFontTextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText(listPacienteTemp.get(position).toString());

            CustomFontTextView lblDni = (CustomFontTextView)item.findViewById(R.id.lblDni);
            lblDni.setText(listPacienteTemp.get(position).getStr_dni());

            CustomFontTextView lblSexo = (CustomFontTextView)item.findViewById(R.id.lblSexo);
            lblSexo.setText(((listPacienteTemp.get(position).isBol_sexo()) ? getText(R.string.str_hombre):getText(R.string.str_mujer)));

            CustomFontTextView lblEdad = (CustomFontTextView)item.findViewById(R.id.lblEdad);
            lblEdad.setText(""+Utilidades.getEdad(listPacienteTemp.get(position).getDat_fecha_nacimiento()));



            return(item);
        }
    }


    public void btnAgregar()
    {
        Bundle args = new Bundle();
        args.putInt("idPaciente", 0);
        Fragment fragment = new FamiliarFragment();
        fragment.setArguments(args);
        ((MainActivity) getActivity()).setFragment(fragment);
    }

}
