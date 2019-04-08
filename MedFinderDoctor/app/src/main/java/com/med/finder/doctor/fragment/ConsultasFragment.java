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
import android.widget.RatingBar;

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
        ((MainActivity) getActivity()).setTitle(getString(R.string.nav_consulta));
        Buscar();
        return view;
    }

    public void Buscar()
    {
        listPreguntaPaciente= clsPreguntaPacienteDAO.Listar(this.getActivity());

        adaptador = new Adaptador(this.getActivity());

        list.setAdapter(adaptador);
    }


    class Adaptador extends ArrayAdapter {

        Activity context;

        Adaptador(Activity context) {
            super(context, R.layout.list_consultas, listPreguntaPaciente);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
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


            clsPaciente paciente= clsPacienteDAO.Buscar(context, listPreguntaPaciente.get(position).getObjPaciente().getInt_id_paciente());

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

            CustomFontTextView lblFecha = (CustomFontTextView)item.findViewById(R.id.lblFecha);
            lblFecha.setText(Utilidades.dateFormatter.format(listPreguntaPaciente.get(position).getDat_inicio()));

            CustomFontTextView lblHora = (CustomFontTextView)item.findViewById(R.id.lblHora);
            lblHora.setText(Utilidades.hourFormatter.format(listPreguntaPaciente.get(position).getDat_inicio()));

                CustomFontTextView  lblRespuesta = (CustomFontTextView)item.findViewById(R.id.lblRespuesta);
                lblRespuesta.setText(listPreguntaPaciente.get(position).getStr_respuesta());
                View viewCalificacion = (View)item.findViewById(R.id.viewCalificacion);
                if(listPreguntaPaciente.get(position).getInt_puntos()>0)
                {
                    viewCalificacion.setVisibility(View.VISIBLE);
                    RatingBar rtbPuntos = (RatingBar)item.findViewById(R.id.rtbPuntos);
                    rtbPuntos.setRating( listPreguntaPaciente.get(position).getInt_puntos());
                }



            return(item);
        }
    }

}
