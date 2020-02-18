package com.med.finder.doctor.fragment;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.med.finder.doctor.R;
import com.med.finder.doctor.activity.MainActivity;
import com.med.finder.doctor.dao.clsInicioDAO;
import com.med.finder.doctor.dao.clsPacienteDAO;
import com.med.finder.doctor.entidades.clsInicio;
import com.med.finder.doctor.entidades.clsPaciente;
import com.med.finder.doctor.utilidades.CustomFontTextView;
import com.med.finder.doctor.utilidades.Utilidades;
import java.util.List;

public class InicioFragment extends Fragment {
    private List<clsInicio> listCasosTemp;
    private Adaptador adaptador;
    private ListView list;
    private CheckBox chbConsulta;
    private CheckBox chbCita;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        list = (ListView)view.findViewById(R.id.list);
        chbConsulta = (CheckBox)view.findViewById(R.id.chbConsulta);
        chbConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });
        chbCita = (CheckBox)view.findViewById(R.id.chbCita);
        chbCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });
        list = (ListView)view.findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                Bundle args = new Bundle();
                args.putInt("id", listCasosTemp.get(posicion).getInt_id());
                  if(listCasosTemp.get(posicion).getInt_tipo()==1)//CITA_PACIENTE
                  {
                      Fragment fragment = new CitasAprobarFragment();
                      fragment.setArguments(args);
                      ((MainActivity) getActivity()).setFragment(fragment);

                  }else if(listCasosTemp.get(posicion).getInt_tipo()==2)//PREGUNTA_PACIENTE
                  {
                      Fragment fragment = new ConsultasResponderFragment();
                      fragment.setArguments(args);
                      ((MainActivity) getActivity()).setFragment(fragment);
                  }


            }
        });
        if(this.getArguments()!=null) {
            int dato= getArguments().getInt("dato");
            if(dato>0)
            {
                if(dato==1){
                    chbConsulta.setChecked(false);
                }else if(dato==2){
                    chbCita.setChecked(false);
                }
            }
        }
        ((MainActivity) getActivity()).setTitle(getString(R.string.nav_inicio));
        Buscar();
        return view;
    }




    public void Buscar()
    {

        listCasosTemp=clsInicioDAO.Listar(this.getActivity(),chbConsulta.isChecked(),chbCita.isChecked());

        adaptador = new Adaptador(this.getActivity());

        list.setAdapter(adaptador);


    }




    class Adaptador extends ArrayAdapter {

        Activity context;

        Adaptador(Activity context) {
            super(context,R.layout.list_inicio, listCasosTemp);
            this.context = context;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_inicio ,null);

            ImageView imageView = (ImageView) item.findViewById(R.id.imageView);

            CustomFontTextView lblTipo = (CustomFontTextView)item.findViewById(R.id.lblTipo);
            if(listCasosTemp.get(position).getInt_tipo()==1)
            {
                lblTipo.setText(getString(R.string.str_aprobar_cita));
                imageView.setImageResource(R.drawable.ic_citas);

            }else
            {
                lblTipo.setText(getString(R.string.str_responder_consulta));
                imageView.setImageResource(R.drawable.ic_inicio);

            }



            TextView lblFecha = (TextView)item.findViewById(R.id.lblFecha);
            lblFecha.setText(Utilidades.dateFormatter.format(listCasosTemp.get(position).getDat_ini()));

            TextView lblHora = (TextView)item.findViewById(R.id.lblHora);
            lblHora.setText(Utilidades.hourFormatter.format(listCasosTemp.get(position).getDat_ini()));
            clsPaciente paciente = clsPacienteDAO.Buscar(context,listCasosTemp.get(position).getObjPaciente().getInt_id_paciente());
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

            return(item);
        }
    }

}
