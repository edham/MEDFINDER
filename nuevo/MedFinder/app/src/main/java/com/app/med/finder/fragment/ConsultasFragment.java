package com.app.med.finder.fragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.dao.clsPacienteDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.entidades.clsPaciente;
import com.app.med.finder.entidades.clsPreguntaPaciente;
import com.app.med.finder.ui.MainActivity;
import com.app.med.finder.ui.R;
import com.app.med.finder.utilidades.clsUtilidades;

import java.util.List;

public class ConsultasFragment extends Fragment {


    private List<clsPreguntaPaciente> listPreguntaPaciente;
    private Adaptador adaptador;
    private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consultas, container, false);

        list = (ListView)view.findViewById(R.id.list);

        Buscar();
        return view;
    }

    public void Buscar()
    {
        listPreguntaPaciente= clsPreguntaPacienteDAO.Listar(this.getActivity());


        adaptador = new Adaptador(this.getActivity());

        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {

                Bundle args = new Bundle();
                args.putInt("id", listPreguntaPaciente.get(posicion).getInt_id_pregunta_paciente());
                Fragment fragment = new RespuestaConsultaFragment();
                fragment.setArguments(args);
                ((MainActivity) getActivity()).setFragment(fragment);

            }
        });


    }


    class Adaptador extends ArrayAdapter {

        Activity context;

        Adaptador(Activity context) {
            super(context, R.layout.list_consultas, listPreguntaPaciente);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final int posicion=position;
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_consultas, null);

            TextView lblAsunto = (TextView)item.findViewById(R.id.lblAsunto);
            lblAsunto.setText(listPreguntaPaciente.get(position).getStr_asunto());

            TextView lblDetalle = (TextView)item.findViewById(R.id.lblDetalle);
            lblDetalle.setText(listPreguntaPaciente.get(position).getStr_paciente_detalle());


            TextView lblEspecialidad = (TextView)item.findViewById(R.id.lblEspecialidad);
            lblEspecialidad.setText(clsEspecialidadDAO.Buscar(context, listPreguntaPaciente.get(position).getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());

            if(listPreguntaPaciente.get(position).getInt_estado()==0)
                lblEspecialidad.setText(clsEspecialidadDAO.Buscar(context, listPreguntaPaciente.get(position).getObjEspecialidad().getInt_id_especialidad()).getStr_nombre()+" - Activo");

            TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);

            clsPaciente objPaciente= clsPacienteDAO.Buscar(context, listPreguntaPaciente.get(position).getObjPaciente().getInt_id_paciente());

            lblNombre.setText(objPaciente.getStr_apellido_paterno()
                    +" "+objPaciente.getStr_apellido_materno()+" "+
                    objPaciente.getStr_nombres());


            TextView lblFecha = (TextView)item.findViewById(R.id.lblFecha);
            lblFecha.setText(clsUtilidades.dateFormatter.format(listPreguntaPaciente.get(position).getDat_inicio()));

            TextView lblHora = (TextView)item.findViewById(R.id.lblHora);
            lblHora.setText(clsUtilidades.hora.format(listPreguntaPaciente.get(position).getDat_inicio()));


            return(item);
        }
    }

}
