package com.med.finder.cliente.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.med.finder.cliente.BuildConfig;
import com.med.finder.cliente.R;
import com.med.finder.cliente.activity.MainActivity;
import com.med.finder.cliente.conexion.InsertarPreguntaPacienteHTTP;
import com.med.finder.cliente.dao.clsEspecialidadDAO;
import com.med.finder.cliente.dao.clsPacienteDAO;
import com.med.finder.cliente.dao.clsPreguntaPacienteDAO;
import com.med.finder.cliente.entidades.clsEspecialidad;
import com.med.finder.cliente.entidades.clsPaciente;
import com.med.finder.cliente.entidades.clsPreguntaPaciente;
import com.med.finder.cliente.utilidades.CustomFontTextView;
import com.med.finder.cliente.utilidades.ImagenArchivo;
import com.med.finder.cliente.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RealizarConsultasFragment extends Fragment {

    private Spinner ComboEspecialidad;
    private Spinner ComboPaciente;
    private Button btnTomarFoto;
    private ImageView imgFoto;
    private View viewImgFoto;
    private EditText txtSintomas;
    private EditText txtAsunto;
    private Bitmap bitmap;
    private ProgressDialog pd;
    private clsPreguntaPaciente entidad;


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
        bitmap=null;
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
        bitmap=ImagenArchivo.getImagen();
        imgFoto.setImageBitmap(bitmap);
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
                        entidad = new clsPreguntaPaciente();
                        entidad.setObjPaciente((clsPaciente)ComboPaciente.getSelectedItem());
                        entidad.setObjEspecialidad((clsEspecialidad)ComboEspecialidad.getSelectedItem());
                        entidad.setStr_asunto(txtAsunto.getText().toString());
                        entidad.setStr_paciente_detalle(txtSintomas.getText().toString());
                        entidad.setByte_imagen(ImagenArchivo.getImagenByte(bitmap));
                        registrar();
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
                ((MainActivity) getActivity()).setFragment(new ClinicasFragment());
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



    public void registrar(){
        if ( Utilidades.checkPermissions(this.getActivity())) {
            pd = new ProgressDialog(this.getActivity());
            pd.setTitle("Cargando Datos");
            pd.setMessage("Espere un momento");
            pd.setCancelable(false);
            pd.show();
            new Thread() {
                public void run() {
                    Message message = handlerCargar.obtainMessage();
                    Bundle bundle = new Bundle();
                    int rpta=0;
                    try {

                        InsertarPreguntaPacienteHTTP http = new InsertarPreguntaPacienteHTTP();

                        http.execute(entidad);
                        String result = http.get();
                        if (!result.equals("")) {
                            JSONObject entidadJSON = new JSONObject(result);
                            if (entidadJSON.getInt("rpta") == 1) {
                                rpta=1;
                                entidad.setInt_id_pregunta_paciente(entidadJSON.getInt("preguntaPacienteId"));
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    bundle.putInt("rpta",rpta);
                    message.setData(bundle);
                    handlerCargar.sendMessage(message);
                }
            }.start();
        }
    }

    final Handler handlerCargar=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            Bundle bundle = msg.getData();
            if(bundle.getInt("rpta")==1)
            {
                Utilidades.alert(RealizarConsultasFragment.this.getActivity(), getString(R.string. str_registro_correcto));
                clsPreguntaPacienteDAO.Agregar(RealizarConsultasFragment.this.getActivity(), entidad);
                cancelar();

            }else
            {
                Utilidades.alert(RealizarConsultasFragment.this.getActivity(), getString(R.string. str_error_registrar));

            }
        }
    };

}
