package com.med.finder.doctor.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.med.finder.doctor.R;
import com.med.finder.doctor.activity.MainActivity;
import com.med.finder.doctor.dao.clsEspecialidadDAO;
import com.med.finder.doctor.dao.clsPacienteDAO;
import com.med.finder.doctor.dao.clsPreguntaPacienteDAO;
import com.med.finder.doctor.entidades.clsPaciente;
import com.med.finder.doctor.entidades.clsPreguntaPaciente;
import com.med.finder.doctor.utilidades.CustomFontTextView;
import com.med.finder.doctor.utilidades.Utilidades;

import java.util.List;

public class ConsultasFragment extends Fragment {


    private List<clsPreguntaPaciente> listPreguntaPaciente;
    private Adaptador adaptador;
    private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_familiares, container, false);
        list = (ListView)view.findViewById(R.id.list);
        View viewButtom = (View)view.findViewById(R.id.viewButtom);
        viewButtom.setVisibility(View.GONE);
        ((MainActivity) getActivity()).verTitulo(2);
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

            CustomFontTextView lblAsunto = (CustomFontTextView)item.findViewById(R.id.lblAsunto);
            lblAsunto.setText(listPreguntaPaciente.get(position).getStr_asunto());

            CustomFontTextView lblDetalle = (CustomFontTextView)item.findViewById(R.id.lblDetalle);
            lblDetalle.setText(listPreguntaPaciente.get(position).getStr_paciente_detalle());


            CustomFontTextView lblEspecialidad = (CustomFontTextView)item.findViewById(R.id.lblEspecialidad);
            lblEspecialidad.setText(clsEspecialidadDAO.Buscar(context, listPreguntaPaciente.get(position).getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());

            if(listPreguntaPaciente.get(position).getInt_estado()==0)
                lblEspecialidad.setText(clsEspecialidadDAO.Buscar(context, listPreguntaPaciente.get(position).getObjEspecialidad().getInt_id_especialidad()).getStr_nombre()+" - Activo");

            CustomFontTextView lblNombre = (CustomFontTextView)item.findViewById(R.id.lblNombre);

            clsPaciente objPaciente= clsPacienteDAO.Buscar(context, listPreguntaPaciente.get(position).getObjPaciente().getInt_id_paciente());

            lblNombre.setText(objPaciente.getStr_apellido_paterno()
                    +" "+objPaciente.getStr_apellido_materno()+" "+
                    objPaciente.getStr_nombres());

            CustomFontTextView lblFecha = (CustomFontTextView)item.findViewById(R.id.lblFecha);
            lblFecha.setText(Utilidades.dateFormatter.format(listPreguntaPaciente.get(position).getDat_inicio()));

            CustomFontTextView lblHora = (CustomFontTextView)item.findViewById(R.id.lblHora);
            lblHora.setText(Utilidades.hora.format(listPreguntaPaciente.get(position).getDat_inicio()));


            return(item);
        }
    }

}
