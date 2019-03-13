package com.med.finder.doctor.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.github.chrisbanes.photoview.PhotoView;
import com.med.finder.doctor.BuildConfig;
import com.med.finder.doctor.R;
import com.med.finder.doctor.activity.MainActivity;
import com.med.finder.doctor.dao.clsEspecialidadDAO;
import com.med.finder.doctor.dao.clsPacienteDAO;
import com.med.finder.doctor.entidades.clsEspecialidad;
import com.med.finder.doctor.entidades.clsPaciente;
import com.med.finder.doctor.entidades.clsPreguntaPaciente;
import com.med.finder.doctor.utilidades.CustomFontTextView;
import com.med.finder.doctor.utilidades.ImagenArchivo;
import com.med.finder.doctor.utilidades.Utilidades;

import java.util.List;

public class RealizarConsultasFragment extends Fragment {

    private Spinner ComboEspecialidad;
    private Spinner ComboPaciente;
    private Button btnTomarFoto;
    private ImageView imgFoto;
    private View viewImgFoto;
    private EditText txtSintomas;
    private EditText txtAsunto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_realizar_consulta, container, false);
        txtSintomas = (EditText)view.findViewById(R.id.txtSintomas);
        txtAsunto = (EditText)view.findViewById(R.id.txtAsunto);
        viewImgFoto = (View) view.findViewById(R.id.viewImgFoto);
        imgFoto = (ImageView) view.findViewById(R.id.imgFoto);
        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFoto();
            }
        });
        btnTomarFoto = (Button) view.findViewById(R.id.btnTomarFoto);
        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTomarFoto();
            }
        });

        Button btnAceptar = (Button) view.findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAceptar();
            }
        });
        Button btnCancelar = (Button) view.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCancelar();
            }
        });



        ComboEspecialidad = (Spinner)view.findViewById(R.id.ComboEspecialidad);
        EspecialidadDDL();
        ComboPaciente = (Spinner)view.findViewById(R.id.ComboPaciente);
        ComboPacienteDDL();
        ImagenArchivo.getDelete();
        viewImgFoto.setVisibility(View.GONE);

        ((MainActivity) getActivity()).verTitulo(0);
        return view;
    }


    public void EspecialidadDDL (){
        List<clsEspecialidad> lista= clsEspecialidadDAO.Listar(this.getActivity());
        lista.add(0,new clsEspecialidad(0,getString(R.string.str_seleccione_especialidad)));

        ArrayAdapter<clsEspecialidad> adapter = new ArrayAdapter<clsEspecialidad>(this.getActivity(),R.layout.spinner,lista);
        adapter.setDropDownViewResource(R.layout.spinner_vista);
        ComboEspecialidad.setAdapter(adapter);
        ComboEspecialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
        ComboEspecialidad.setSelection(0);
    }

    public void ComboPacienteDDL(){

        List<clsPaciente> lista = clsPacienteDAO.Listar(this.getActivity());
        lista.add(0,new clsPaciente(0,getString(R.string.str_seleccione_paciente)));

        ArrayAdapter<clsPaciente> adapter = new ArrayAdapter<clsPaciente>(this.getActivity(),R.layout.spinner,lista);
        adapter.setDropDownViewResource(R.layout.spinner_vista);
        ComboPaciente.setAdapter(adapter);
        ComboPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
        ComboPaciente.setSelection(0);
    }

    public void btnTomarFoto()
    {

        if ( Utilidades.checkPermissions(this.getActivity())){
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uriSavedImage =   FileProvider.getUriForFile(this.getContext(), BuildConfig.APPLICATION_ID + ".provider", ImagenArchivo.SetImage());
                i.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                ((MainActivity) getActivity()).startActivityForResult(i, 0);

        }

    }

    public void cargarFoto()
    {
        viewImgFoto.setVisibility(View.VISIBLE);
        imgFoto.setImageBitmap(ImagenArchivo.getImagen());
    }
    public void imgFoto()
    {
        final Dialog dialog = new Dialog(this.getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_foto);
        final PhotoView imageView =dialog.findViewById(R.id.imageView);
        imageView.setImageBitmap(ImagenArchivo.getImagen());


        Button btnCerrar = (Button) dialog.findViewById(R.id.btnCerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button btnEliminar = (Button) dialog.findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialogInterno = new Dialog(RealizarConsultasFragment.this.getActivity());
                dialogInterno.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogInterno.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialogInterno.setContentView(R.layout.dialog);

                CustomFontTextView lblMensaje = (CustomFontTextView) dialogInterno.findViewById(R.id.lblMensaje);
                lblMensaje.setText(getString(R.string.alert_imagen));
                FloatingActionButton btnAceptar = (FloatingActionButton) dialogInterno.findViewById(R.id.btnAceptar);
                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(ImagenArchivo.getDelete())
                        {
                           imgFoto.setImageBitmap(null);
                            dialogInterno.dismiss();
                            dialog.dismiss();
                            viewImgFoto.setVisibility(View.GONE);
                        }

                    }
                });
                FloatingActionButton btnCancelar = (FloatingActionButton) dialogInterno.findViewById(R.id.btnCancelar);
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogInterno.dismiss();
                    }
                });
                dialogInterno.show();

            }
        });


        dialog.show();

    }

    public void btnAceptar()
    {
        if(ComboPaciente.getSelectedItemPosition()>0)
        {
            if(ComboEspecialidad.getSelectedItemPosition()>0)
            {
                if(!txtSintomas.getText().toString().equals("") &&  txtSintomas.getText().toString()!=null )
                {
                    if(!txtAsunto.getText().toString().equals("") &&  txtAsunto.getText().toString()!=null )
                    {
                        clsPreguntaPaciente entidad = new clsPreguntaPaciente();
                        entidad.setObjPaciente((clsPaciente)ComboPaciente.getSelectedItem());
                        entidad.setObjEspecialidad((clsEspecialidad)ComboEspecialidad.getSelectedItem());
                        entidad.setStr_asunto(txtAsunto.getText().toString());
                        entidad.setStr_paciente_detalle(txtSintomas.getText().toString());
                        /*
                        if(bp!=null)
                            entidad.setByte_imagen(Funciones.getByte(bp));

                        String cadena= http.insertarPreguntaPacuente(entidad);
                        if(!cadena.trim().equals("0"))
                        {
                            entidad.setInt_id_pregunta_paciente(Integer.parseInt(cadena));
                            clsPreguntaPacienteDAO.Agregar(this.getActivity(), entidad);
                            //Toast.makeText(this,"Su consulta se envio Satisfactoriamente", Toast.LENGTH_SHORT).show();

                        }
                        */
                    }
                    else
                        Utilidades.alert(this.getActivity(), getString(R.string.str_ingrese_asunto));
                }
                else
                    Utilidades.alert(this.getActivity(), getString(R.string.str_ingrese_sintomas));
            }
            else
                Utilidades.alert(this.getActivity(), getString(R.string.str_seleccione_especialidad));
        }
        else
            Utilidades.alert(this.getActivity(), getString(R.string.str_seleccione_paciente));
    }

    public void btnCancelar()
    {
        cancelar();
    }

    void cancelar()
    {
        switch (((MainActivity) getActivity()).posConsulta)
        {
            case 1:
                ((MainActivity) getActivity()).setFragment(new InicioFragment());
                break;
            case 2:
                ((MainActivity) getActivity()).setFragment(new ConsultasFragment());
                break;
            case 3:
             //   ((MainActivity) getActivity()).setFragment(new ClinicasFragment());
                break;
            case 4:
                ((MainActivity) getActivity()).setFragment(new FamiliaresFragment());
                break;
            case 5:
                ((MainActivity) getActivity()).setFragment(new CitasFragment());
                break;
            case 6:
                ((MainActivity) getActivity()).setFragment(new CasosSaludFragment());
                break;
            case 7:
                ((MainActivity) getActivity()).setFragment(new EspecialidadesFragment());
                break;
        }
    }
}
