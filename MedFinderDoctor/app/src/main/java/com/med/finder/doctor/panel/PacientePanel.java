package com.med.finder.doctor.panel;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.med.finder.doctor.R;
import com.med.finder.doctor.dao.clsPacienteDAO;
import com.med.finder.doctor.entidades.clsPaciente;
import com.med.finder.doctor.utilidades.CustomFontTextView;
import com.med.finder.doctor.utilidades.Utilidades;

public class PacientePanel extends Fragment {



    public static PacientePanel newInstance(int idPaciente) {
        PacientePanel newFragment = new PacientePanel();
        Bundle bundle = new Bundle();
        bundle.putInt("idPaciente", idPaciente);
        newFragment.setArguments(bundle);
        return newFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.panel_paciente, container, false);

        int idPaciente=getArguments().getInt("idPaciente");

        if(idPaciente>0)
        {
            clsPaciente entidad= clsPacienteDAO.Buscar(this.getContext(), idPaciente);
            if(entidad!=null)
            {
                CustomFontTextView lblNombre = (CustomFontTextView)view.findViewById(R.id.lblNombre);
                lblNombre.setText(entidad.toString());

                CustomFontTextView lblDNI = (CustomFontTextView)view.findViewById(R.id.lblDNI);
                lblDNI.setText(entidad.getStr_dni());

                CustomFontTextView lblSexo = (CustomFontTextView)view.findViewById(R.id.lblSexo);
                lblSexo.setText(((entidad.isBol_sexo()) ? getText(R.string.str_hombre):getText(R.string.str_mujer)));

                CustomFontTextView lblEdad = (CustomFontTextView)view.findViewById(R.id.lblEdad);
                lblEdad.setText(""+Utilidades.getEdad(entidad.getDat_fecha_nacimiento()));

                CustomFontTextView lblEstatura = (CustomFontTextView)view.findViewById(R.id.lblEstatura);
                lblEstatura.setText(""+entidad.getInt_estatura());

                if(entidad.isBol_cardiovasculares()) {
                    CustomFontTextView lblCardioVascular = (CustomFontTextView) view.findViewById(R.id.lblCardioVascular);
                    lblCardioVascular.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(android.R.drawable.checkbox_on_background),null, null, null);
                }
                if(entidad.isBol_alcohol()) {
                    CustomFontTextView lblAlcohol = (CustomFontTextView)view.findViewById(R.id.lblAlcohol);
                    lblAlcohol.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(android.R.drawable.checkbox_on_background),null, null, null);
                }
                if(entidad.isBol_musculares()) {
                    CustomFontTextView lblMusculares = (CustomFontTextView) view.findViewById(R.id.lblMusculares);
                    lblMusculares.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(android.R.drawable.checkbox_on_background), null, null, null);
                }
                if(entidad.isBol_tabaquismo()) {
                    CustomFontTextView lblTabaquismo = (CustomFontTextView)view.findViewById(R.id.lblTabaquismo);
                    lblTabaquismo.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(android.R.drawable.checkbox_on_background), null, null, null);
                }
                if(entidad.isBol_digestivos()) {
                    CustomFontTextView lblDigestivos = (CustomFontTextView)view.findViewById(R.id.lblDigestivos);
                    lblDigestivos.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(android.R.drawable.checkbox_on_background), null, null, null);
                }
                if(entidad.isBol_drogas()) {
                    CustomFontTextView lblDrogas = (CustomFontTextView)view.findViewById(R.id.lblDrogas);
                    lblDrogas.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(android.R.drawable.checkbox_on_background), null, null, null);
                }
                if(entidad.isBol_alergicos()) {
                    CustomFontTextView lblAlergicos = (CustomFontTextView)view.findViewById(R.id.lblAlergicos);
                    lblAlergicos.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(android.R.drawable.checkbox_on_background), null, null, null);
                }
                if(entidad.isBol_psicologicos()) {
                    CustomFontTextView lblPsicologicos = (CustomFontTextView)view.findViewById(R.id.lblPsicologicos);
                    lblPsicologicos.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(android.R.drawable.checkbox_on_background), null, null, null);
                }
            }
        }
        return view;
    }


}
