package com.med.finder.cliente.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.med.finder.cliente.R;
import com.med.finder.cliente.conexion.InsertarEncuestaHTTP;
import com.med.finder.cliente.dao.clsUsuarioDAO;
import com.med.finder.cliente.entidades.clsUsuario;
import com.med.finder.cliente.fragment.CasosSaludFragment;
import com.med.finder.cliente.fragment.CitasFragment;
import com.med.finder.cliente.fragment.ClinicasFragment;
import com.med.finder.cliente.fragment.ConsultasFragment;
import com.med.finder.cliente.fragment.EspecialidadesFragment;
import com.med.finder.cliente.fragment.FamiliaresFragment;
import com.med.finder.cliente.fragment.InicioFragment;
import com.med.finder.cliente.fragment.RealizarConsultasFragment;
import com.med.finder.cliente.utilidades.CustomFontTextView;
import com.med.finder.cliente.utilidades.ImagenArchivo;
import com.med.finder.cliente.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public clsUsuario objUsuario;
    public int posFragmnet=0;
    public int posConsulta=0;
    private FloatingActionButton btnConsulta;
    public ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utilidades.hideKeyboard(this);
        objUsuario= clsUsuarioDAO.Buscar(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Bitmap perfil = null;
        if(objUsuario.getByte_foto()!=null)
        {
            perfil= Utilidades.getBitmap(objUsuario.getByte_foto());
        }
        View headerView = navigationView.getHeaderView(0);
        CircleImageView imvPerfil = (CircleImageView) headerView.findViewById(R.id.imvPerfil);
        TextView lblUsuario = (TextView) headerView.findViewById(R.id.lblUsuario);
        TextView lblNombre = (TextView) headerView.findViewById(R.id.lblNombre);
        lblUsuario.setText(objUsuario.getStr_usuario());
        lblNombre.setText(objUsuario.toString().toUpperCase());
        if(perfil!=null) {
            imvPerfil.setImageBitmap(perfil);
        }

        btnConsulta = (FloatingActionButton) findViewById(R.id.fab);
        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                setFragment(new RealizarConsultasFragment());
            }
        });

        navigationView.getMenu().getItem(0).setChecked(true);
       setTitle(navigationView.getMenu().getItem(0).getTitle());
        setFragment(new InicioFragment());
        Utilidades.checkPermissions(this);
        Utilidades.addListaBlanca(this);
        Utilidades.scheduleChargingReminder(this);

    }

    @SuppressLint("RestrictedApi")
    public void verTitulo(int titulo)
    {
        setTitle(getString(R.string.str_realizar_consulta));
        btnConsulta.setVisibility(View.GONE);

        if(titulo==1)
        {
            setTitle(getString(R.string.nav_inicio));
        }else if(titulo==2)
        {
            setTitle(getString(R.string.nav_consulta));
        }else if(titulo==3)
        {
            setTitle(getString(R.string.nav_clinicas));
        }else if(titulo==4)
        {
            setTitle(getString(R.string.nav_familiares));
        }else if(titulo==5)
        {
            setTitle(getString(R.string.nav_citas));
        }else if(titulo==6)
        {
            setTitle(getString(R.string.nav_casos));
        }else if(titulo==7)
        {
            setTitle(getString(R.string.nav_especialidades));
        }

        if(titulo>0)
        {
            posConsulta=titulo;
            btnConsulta.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_inicio:
                setFragment(new InicioFragment());
                break;
            case R.id.nav_consulta:
                setFragment(new ConsultasFragment());
                break;
            case R.id.nav_clinicas:
                setFragment(new ClinicasFragment());
                break;
            case R.id.nav_familiares:
                setFragment(new FamiliaresFragment());
                break;
            case R.id.nav_citas:
                setFragment(new CitasFragment());
                break;
            case R.id.nav_casos:
                setFragment(new CasosSaludFragment());
                break;
            case R.id.nav_especialidades:
                setFragment(new EspecialidadesFragment());
                break;
            case R.id.nav_cerrar:
                final Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog);
                CustomFontTextView lblMensaje = (CustomFontTextView)dialog.findViewById(R.id.lblMensaje);
                lblMensaje.setText(getString(R.string.alert_cerrar));

                FloatingActionButton btnAceptar = (FloatingActionButton) dialog.findViewById(R.id.btnAceptar);
                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utilidades.cancelAll(MainActivity.this);
                        Utilidades.BorrarDatos(MainActivity.this);
                        Intent i=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(i);
                        finish();
                        int pid = android.os.Process.myPid();
                        android.os.Process.killProcess(pid);
                        System.exit(0);
                    }
                });
                FloatingActionButton btnCancelar = (FloatingActionButton) dialog.findViewById(R.id.btnCancelar);
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment( Fragment fragment)
    {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction() .replace(R.id.main_content, fragment).commit();

        dialogoEncuesta();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {



            Fragment currentFrag =  getSupportFragmentManager().findFragmentById(R.id.main_content);
            if (currentFrag!=null) {
                switch (currentFrag.getClass().getSimpleName())
                {

                    case "InicioFragment":
                    case "ConsultasFragment":
                    case "CasosSaludFragment":
                    case "EspecialidadesFragment":
                    case "FavoritosFragment":
                    case "CitasFragment":
                    case "FamiliaresFragment":
                    case "ClinicasFragment":
                        final Dialog dialog = new Dialog(this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.setContentView(R.layout.dialog);
                        CustomFontTextView lblMensaje = (CustomFontTextView)dialog.findViewById(R.id.lblMensaje);
                        lblMensaje.setText(getString(R.string.alert_salir));
                        FloatingActionButton btnAceptar = (FloatingActionButton) dialog.findViewById(R.id.btnAceptar);
                        btnAceptar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pid = android.os.Process.myPid();
                                android.os.Process.killProcess(pid);
                                System.exit(0);
                                dialog.dismiss();
                            }
                        });
                        FloatingActionButton btnCancelar = (FloatingActionButton) dialog.findViewById(R.id.btnCancelar);
                        btnCancelar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();

                        break;

                    case "ClinicaInfoFragment":
                        if(posFragmnet==1) {

                            setFragment(new InicioFragment());
                        }
                        else if(posFragmnet==2) {
                            setFragment(new ClinicasFragment());
                        }
                        break;
                    case "DoctorInfoFragment":
                        if(posFragmnet==1) {

                            setFragment(new InicioFragment());
                        }
                        else if(posFragmnet==2) {
                            //setFragment(new FavoritosFragment());
                        }
                        else if(posFragmnet==3) {
                            setFragment(new CitasFragment());
                        }
                        break;

                    case "RespuestaConsultaFragment":
                        setFragment(new ConsultasFragment());
                        break;

                    case "RespuestaCasosSaludFragment":
                        setFragment(new CasosSaludFragment());
                        break;

                    default:
                        break;
                }
            }

            // this.finish();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    if(fragment instanceof RealizarConsultasFragment) {
                        ImagenArchivo.resizedImage();
                        ((RealizarConsultasFragment) fragment).cargarFoto();
                    }

                }
            }
        }
    }

    public void dialogoEncuesta()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_encuesta_uno);
        final CustomFontTextView lblP1 = (CustomFontTextView)dialog.findViewById(R.id.lblP1);
        final CustomFontTextView lblP2 = (CustomFontTextView)dialog.findViewById(R.id.lblP2);
        final CustomFontTextView lblP3 = (CustomFontTextView)dialog.findViewById(R.id.lblP3);

        if(inicio) {
            lblP1.setText("Cuál consideras que es tu nivel de acceso a consultas médicas sin el uso de una aplicación móvil?");
            lblP2.setText("¿Cuál consideras que es tu nivel de acceso a reserva de citas médicas sin el uso de una aplicación móvil?");
            lblP3.setText("¿Cuál consideras que es tu nivel de acceso a recomendaciones médicas sin el uso de una aplicación móvil?");
        }else
        {
            lblP1.setText("¿Cuál consideras que es tu nivel de acceso a consultas médicas con el uso de la aplicación móvil?");
            lblP2.setText("¿Cuál consideras que es tu nivel de acceso a reserva de citas médicas con el uso de la aplicación móvil?");
            lblP3.setText("¿Cuál consideras que es tu nivel de acceso a recomendaciones médicas con el uso de la aplicación móvil?");
        }

        final RadioGroup rdgGrupo1 = (RadioGroup)dialog.findViewById(R.id.rdgGrupo1);
        final RadioGroup rdgGrupo2 = (RadioGroup)dialog.findViewById(R.id.rdgGrupo2);
        final RadioGroup rdgGrupo3 = (RadioGroup)dialog.findViewById(R.id.rdgGrupo3);

        FloatingActionButton btnAceptar = (FloatingActionButton) dialog.findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p1=0;
                int checkedId1 = rdgGrupo1.getCheckedRadioButtonId();
                if (checkedId1 == R.id.rdb11){
                    p1=1;
                }else if (checkedId1 == R.id.rdb12){
                    p1=2;
                }else if (checkedId1 == R.id.rdb13){
                    p1=3;
                }else if (checkedId1 == R.id.rdb14){
                    p1=4;
                }else if (checkedId1 == R.id.rdb15){
                    p1=5;
                }

                int p2=0;
                int checkedId2 = rdgGrupo2.getCheckedRadioButtonId();
                if (checkedId2 == R.id.rdb21){
                    p2=1;
                }else if (checkedId2 == R.id.rdb22){
                    p2=2;
                }else if (checkedId2 == R.id.rdb23){
                    p2=3;
                }else if (checkedId2 == R.id.rdb24){
                    p2=4;
                }else if (checkedId2 == R.id.rdb25){
                    p2=5;
                }

                int p3=0;
                int checkedId3 = rdgGrupo3.getCheckedRadioButtonId();
                if (checkedId3 == R.id.rdb31){
                    p3=1;
                }else if (checkedId3 == R.id.rdb32){
                    p3=2;
                }else if (checkedId3 == R.id.rdb33){
                    p3=3;
                }else if (checkedId3 == R.id.rdb34){
                    p3=4;
                }else if (checkedId3 == R.id.rdb35){
                    p3=5;
                }
                dialog.dismiss();
                btnAceptar((inicio)?1:4,(inicio)?2:5,(inicio)?3:6,p1,p2,p3);
            }
        });
        FloatingActionButton btnCancelar = (FloatingActionButton) dialog.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void btnAceptar(final int p1,final int p2,final int p3,final int t1,final int t2,final int t3) {

        if ( Utilidades.checkPermissions(this) && Utilidades.addListaBlanca(this))
        {
                pd = new ProgressDialog(this);
                pd.setTitle("Cargando Datos");
                pd.setMessage("Espere un momento");
                pd.setCancelable(false);
                pd.show();
                new Thread() {
                    public void run() {
                        Message message = handlerCargar.obtainMessage();
                        Bundle bundle = new Bundle();
                        try {
                            InsertarEncuestaHTTP login = new InsertarEncuestaHTTP();

                            login.execute(objUsuario.getInt_id_persona(),p1,t1,p2,t2,p3,t3);
                            String result = login.get();
                            if(!result.equals(""))
                            {
                                bundle.putString("json", result);
                                message.setData(bundle);
                                handlerCargar.sendMessage(message);

                            }else
                                pd.dismiss();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
        }
    }


    final Handler handlerCargar=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            Bundle bundle = msg.getData();
            try {
                JSONObject objeto = new JSONObject(bundle.getString("json"));
                Log.e("--------------",objeto.toString());
                if(!objeto.isNull("rpta") && objeto.getInt("rpta")==1 )
                {

                }else
                {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

}
