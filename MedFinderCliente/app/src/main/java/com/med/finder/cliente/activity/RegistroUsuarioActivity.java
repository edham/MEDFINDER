/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.med.finder.cliente.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.med.finder.cliente.BuildConfig;
import com.med.finder.cliente.R;
import com.med.finder.cliente.conexion.InsertUsuarioHTTP;
import com.med.finder.cliente.dao.clsUsuarioDAO;
import com.med.finder.cliente.entidades.clsDepartamento;
import com.med.finder.cliente.entidades.clsDistrito;
import com.med.finder.cliente.entidades.clsPaciente;
import com.med.finder.cliente.entidades.clsProvincia;
import com.med.finder.cliente.entidades.clsUsuario;
import com.med.finder.cliente.utilidades.CustomFontTextView;
import com.med.finder.cliente.utilidades.ImagenArchivo;
import com.med.finder.cliente.utilidades.Ubigeo;
import com.med.finder.cliente.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 * @author ROSEMARY
 */
public class RegistroUsuarioActivity extends Activity {

    /**
     * Called when the activity is first created.
     */

    private CircleImageView imgPerfil;
    private Bitmap imgBitmap;
    private static int tomarfoto=0;
    private static int cargarfoto=1;

    private boolean sexo=true;
    private CheckBox chbHombre;
    private CheckBox chbMujer;
    private CustomFontTextView lblFecNac;
    private Date fecNac;
    private EditText txtNombres;
    private EditText txtApellidoPaterno;
    private EditText txtApellidoMaterno;
    private EditText txtDNI;
    private EditText txtTelefono;
    private EditText txtEmail;
    private EditText txtDireccion;
    private EditText txtClave;
    private EditText txtRClave;
    private EditText txtEstatura;
    
    private CheckBox chbAcepto;    
    private CheckBox chbCardioVascular;
    private CheckBox chbAlcohol;
    private CheckBox chbMusculares;
    private CheckBox chbTabaquismo;
    private CheckBox chbDigestivos;
    private CheckBox chbDrogas;
    private CheckBox chbAlergicos;
    private CheckBox chbPsicologicos;
    private clsPaciente entidad;
    public ProgressDialog pd;

    private Spinner comboDepartamento;
    private Spinner comboProvincia;
    private Spinner comboDistrito;
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here
         setContentView(R.layout.activity_registro_usuario);
        imgPerfil = (CircleImageView) findViewById(R.id.imgPerfil);
        Button btnTomarFoto = (Button) findViewById(R.id.btnTomarFoto);
        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTomarFoto();
            }
        });
        Button btnCargarFoto = (Button) findViewById(R.id.btnCargarFoto);
        btnCargarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCargarFoto();
            }
        });

        comboDepartamento = (Spinner) findViewById(R.id.comboDepartamento);
        comboDepartamento();
        comboProvincia = (Spinner) findViewById(R.id.comboProvincia);
        comboProvincia();
        comboDistrito = (Spinner)findViewById(R.id.comboDistrito);
        comboDistrito();
        Button btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCancelar();
            }
        });
        Button btnAceptar = (Button)findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAceptar();
            }
        });
        // ComboSexo = (Spinner)findViewById(R.id.ComboSexo);
        txtNombres = (EditText)findViewById(R.id.txtNombres);
        txtApellidoPaterno = (EditText)findViewById(R.id.txtApellidoPaterno);
        txtApellidoMaterno = (EditText)findViewById(R.id.txtApellidoMaterno);
      //  txtFNacimiento = (EditText)findViewById(R.id.txtFNacimiento);
        txtDNI = (EditText)findViewById(R.id.txtDNI);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);                
        txtEmail = (EditText)findViewById(R.id.txtEmail);                
        txtDireccion = (EditText)findViewById(R.id.txtDireccion);
        txtClave = (EditText)findViewById(R.id.txtClave);
        txtRClave = (EditText)findViewById(R.id.txtRClave);
        txtEstatura = (EditText)findViewById(R.id.txtEstatura);
        chbAcepto = (CheckBox)findViewById(R.id.chbAcepto);  
        
        chbCardioVascular = (CheckBox)findViewById(R.id.chbCardioVascular);  
        chbAlcohol = (CheckBox)findViewById(R.id.chbAlcohol);  
        chbMusculares = (CheckBox)findViewById(R.id.chbMusculares);  
        chbTabaquismo = (CheckBox)findViewById(R.id.chbTabaquismo);  
        chbDigestivos = (CheckBox)findViewById(R.id.chbDigestivos);  
        chbDrogas = (CheckBox)findViewById(R.id.chbDrogas);  
        chbAlergicos = (CheckBox)findViewById(R.id.chbAlergicos);  
        chbPsicologicos = (CheckBox)findViewById(R.id.chbPsicologicos);
        chbHombre = (CheckBox)findViewById(R.id.chbHombre);
        chbMujer = (CheckBox)findViewById(R.id.chbMujer);
        chbHombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox)view).isChecked();
                if(isChecked)
                {
                    chbMujer.setChecked(false);
                }else
                {
                    chbMujer.setChecked(true);
                }
            }
        });
        chbMujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox)view).isChecked();
                if(isChecked)
                {
                    chbHombre.setChecked(false);
                }else
                {
                    chbHombre.setChecked(true);
                }
            }
        });

        fecNac= new Date();
        lblFecNac = (CustomFontTextView)findViewById(R.id.lblFecNac);
        lblFecNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilidades.setCalendar(RegistroUsuarioActivity.this,lblFecNac,null,fecNac);
            }
        });
        ImagenArchivo.getDelete();
    }

    public void comboDepartamento() {
        List<clsDepartamento> listaDepartamento = Ubigeo.getListDepartamento();

        listaDepartamento.add(0, new clsDepartamento(0, getString(R.string.str_seleccionar_departamento)));

        ArrayAdapter<clsDepartamento> adapterConbo = new ArrayAdapter<clsDepartamento>(this, R.layout.spinner, listaDepartamento);

        adapterConbo.setDropDownViewResource(R.layout.spinner_vista);
        comboDepartamento.setAdapter(adapterConbo);
        comboDepartamento.setSelection(0);
        comboDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    comboProvincia();
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
    }

    public void comboProvincia()
    {
        List<clsProvincia> listaProvincia = Ubigeo.getListProvinciaXDepartamento((clsDepartamento) comboDepartamento.getSelectedItem());

        listaProvincia.add(0, new clsProvincia(0, getString(R.string.str_seleccionar_provincia)));

        ArrayAdapter<clsProvincia> adapterConbo = new ArrayAdapter<clsProvincia>(this,R.layout.spinner, listaProvincia);

        adapterConbo.setDropDownViewResource(R.layout.spinner_vista);
        comboProvincia.setAdapter(adapterConbo);
        comboProvincia.setSelection(0);
        comboProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position>0) {
                    comboDistrito();
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
    }


    public void comboDistrito()
    {
        final List<clsDistrito> listaDistrito = Ubigeo.getListDistritoXProvincia((clsProvincia) comboProvincia.getSelectedItem());

        listaDistrito.add(0, new clsDistrito(0, getString(R.string.str_seleccionar_distrito)));

        ArrayAdapter<clsDistrito> adapterConbo = new ArrayAdapter<clsDistrito>(this,R.layout.spinner, listaDistrito);

        adapterConbo.setDropDownViewResource(R.layout.spinner_vista);
        comboDistrito.setAdapter(adapterConbo);
        comboDistrito.setSelection(0);
    }






        public void btnCancelar()
     {
         Intent i=new Intent(this,LoginActivity.class);
                                    startActivity(i);
     }
     
      public void btnAceptar()
     {
          if(!txtNombres.getText().toString().equals("") &&  txtNombres.getText().toString()!=null )
          {
              if(!txtApellidoPaterno.getText().toString().equals("") &&  txtApellidoPaterno.getText().toString()!=null )
                   {
                       if(!txtApellidoMaterno.getText().toString().equals("") &&  txtApellidoMaterno.getText().toString()!=null )
                        {
                            if(Utilidades.isDate(lblFecNac.getText().toString()))
                            {
                                 if(!txtEstatura.getText().toString().equals("") &&  txtEstatura.getText().toString()!=null  )
                                 {
                                         if(txtDNI.getText()!=null && txtDNI.getText().toString().length()==8)
                                        {
                                            if(txtTelefono.getText()!=null && txtTelefono.getText().toString().length()>5)
                                            {
                                                if(Utilidades.isValidEmail(txtEmail.getText().toString()))
                                                {
                                                     if(comboDistrito.getSelectedItemPosition()>0)
                                                    {
                                                        if(!txtDireccion.getText().toString().equals("") && !txtDireccion.getText().toString().equals(null))
                                                        {
                                                            if(!txtClave.getText().toString().equals("") && !txtClave.getText().toString().equals(null))
                                                            {
                                                                if(txtRClave.getText().toString().equals(txtClave.getText().toString()))
                                                                {
                                                                    if(chbAcepto.isChecked())
                                                                    {
                                                                        clsUsuario usuario=new clsUsuario();
                                                                        usuario.setStr_dni(txtDNI.getText().toString());
                                                                        usuario.setStr_telefono(txtTelefono.getText().toString());
                                                                        usuario.setStr_email(txtEmail.getText().toString());
                                                                        usuario.setStr_direccion(txtDireccion.getText().toString());
                                                                        usuario.setStr_usuario(txtDNI.getText().toString());
                                                                        usuario.setStr_clave(txtClave.getText().toString());
                                                                        usuario.setStr_nombres(txtNombres.getText().toString());
                                                                        usuario.setStr_apellido_paterno(txtApellidoPaterno.getText().toString());
                                                                        usuario.setStr_apellido_materno(txtApellidoMaterno.getText().toString());
                                                                        usuario.setBol_sexo(sexo);
                                                                        usuario.setByte_foto(ImagenArchivo.getImagenByte(imgBitmap));

                                                                        entidad =new clsPaciente();
                                                                        entidad.setStr_nombres(txtNombres.getText().toString());
                                                                        entidad.setStr_apellido_paterno(txtApellidoPaterno.getText().toString());
                                                                        entidad.setStr_apellido_materno(txtApellidoMaterno.getText().toString());//
                                                                        entidad.setBol_sexo(sexo);
                                                                        entidad.setStr_dni(txtDNI.getText().toString());
                                                                        entidad.setDat_fecha_nacimiento(Utilidades.getDate(lblFecNac.getText().toString()));        //
                                                                        entidad.setBol_tipo(true);
                                                                        entidad.setBol_cardiovasculares(chbCardioVascular.isChecked());
                                                                        entidad.setBol_alcohol(chbAlcohol.isChecked());
                                                                        entidad.setBol_musculares(chbMusculares.isChecked());
                                                                        entidad.setBol_tabaquismo(chbTabaquismo.isChecked());
                                                                        entidad.setBol_digestivos(chbDigestivos.isChecked());
                                                                        entidad.setBol_drogas(chbDrogas.isChecked());
                                                                        entidad.setBol_alergicos(chbAlergicos.isChecked());
                                                                        entidad.setBol_psicologicos(chbPsicologicos.isChecked());
                                                                        entidad.setInt_estado(0);
                                                                        entidad.setInt_estatura(Integer.parseInt(txtEstatura.getText().toString()));
                                                                        entidad.setObjUsuario(usuario);
                                                                        entidad.setObjDistrito((clsDistrito)comboDistrito.getSelectedItem());
                                                                        registrarUsuario();
                                                                        /*
                                                                        InsertUsuarioHTTP

                                                                        String data= http.insertarUsuario(entidad);
                                                                        if(!data.trim().equals("0"))
                                                                        {

                                                                            Toast.makeText(this,"El Usuario se Registro Correctamente", Toast.LENGTH_SHORT).show();
                                                                            Intent i=new Intent(this,LoginActivity.class);
                                                                            startActivity(i);
                                                                        }
                                                                        else
                                                                            Toast.makeText(this,,getString(R.string.str_error_registrar), Toast.LENGTH_SHORT).show();
                                                                        */
                                                                    }
                                                                    else{
                                                                        Utilidades.alert(this, getString(R.string.str_terminos_condiciones));
                                                                    }
                                                                }
                                                                else{
                                                                    Utilidades.alert(this, getString(R.string.str_repita_contrasenna));
                                                                }
                                                            }
                                                            else{
                                                                Utilidades.alert(this, getString(R.string.str_ingrese_contrasenna));
                                                            }
                                                        }else{
                                                             Utilidades.alert(this, getString(R.string.str_ingrese_direccion));
                                                         }
                                                    }else{
                                                         Utilidades.alert(this, getString(R.string.str_seleccionar_distrito));
                                                     }
                                                }
                                                else{
                                                    Utilidades.alert(this, getString(R.string.str_ingrese_email));
                                                }
                                            }
                                            else{

                                                Utilidades.alert(this, getString(R.string.str_ingrese_telefono));
                                            }
                                        }
                                        else{

                                             Utilidades.alert(this, getString(R.string.str_ingrese_dni));
                                         }
                                 }
                                else{
                                     Utilidades.alert(this, getString(R.string.str_ingrese_estatura));
                                 }
                            }
                            else{
                                Utilidades.alert(this, getString(R.string.str_ingrese_fecnac));
                            }
                        }
                        else{
                           Utilidades.alert(this, getString(R.string.str_ingrese_materno));
                        }
                   }
                  else{
                    Utilidades.alert(this, getString(R.string.str_ingrese_paterno));
              }
          }
          else{
              Utilidades.alert(this, getString(R.string.str_ingrese_nombres));
          }
     }
       
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
   
    
    return false;

    }


    public void registrarUsuario(){
        if ( Utilidades.checkPermissions(this)) {
            pd = new ProgressDialog(this);
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

                        InsertUsuarioHTTP http = new InsertUsuarioHTTP();

                        http.execute(entidad);
                        String result = http.get();
                        if (!result.equals("")) {
                            JSONObject entidadJSON = new JSONObject(result);
                            if (entidadJSON.getInt("rpta") == 1) {
                                rpta=1;
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
                Utilidades.alert(RegistroUsuarioActivity.this, getString(R.string. str_registro_correcto));
                Intent i = new Intent(RegistroUsuarioActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

            }else
            {
                Utilidades.alert(RegistroUsuarioActivity.this, getString(R.string. str_error_registrar));

            }
        }
    };



    public void btnCargarFoto()
    {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, cargarfoto);

    }
    public void btnTomarFoto()
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uriSavedImage =   FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", ImagenArchivo.SetImage());
        i.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(i, tomarfoto);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        imgBitmap=null;
        if (requestCode == tomarfoto && resultCode == RESULT_OK) {
            ImagenArchivo.resizedImage();

        }
        else if (requestCode == cargarfoto && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImagenArchivo.moveImage(picturePath);
            ImagenArchivo.resizedImage();
        }
        imgBitmap=ImagenArchivo.getImagen();
        imgPerfil.setImageBitmap(imgBitmap);

    }
}
