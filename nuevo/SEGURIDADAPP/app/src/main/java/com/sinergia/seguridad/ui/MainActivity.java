package com.sinergia.seguridad.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.PolylineOptions;
import com.sinergia.seguridad.conexion.postDataHTTP;
import com.sinergia.seguridad.dao.clsCuadranteDAO;
import com.sinergia.seguridad.dao.clsEntidadDAO;
import com.sinergia.seguridad.dao.clsPersonalDAO;
import com.sinergia.seguridad.dao.clsSesionVigilanciaDAO;
import com.sinergia.seguridad.dao.clsTrackDAO;
import com.sinergia.seguridad.dao.clsVehiculoDAO;
import com.sinergia.seguridad.entidades.clsSesionVigilancia;
import com.sinergia.seguridad.entidades.clsTrack;
import com.sinergia.seguridad.fragment.*;
import com.sinergia.seguridad.servicio.clsGPSTrackService;
import com.sinergia.seguridad.utilidades.clsUtilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    private String drawerTitle;
    private TextView lblUsuario;
    private CircleImageView imgUsuario;
    private clsSesionVigilancia entidad;
    private static int tomarfoto=0;
    private int total=0;
    boolean validador = true;
    private  ProgressDialog pd ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entidad= clsSesionVigilanciaDAO.Buscar(this);

        setToolbar(); // Setear Toolbar como action bar
        lblUsuario = (TextView) findViewById(R.id.lblUsuario);
      //  lblUsuario.setText(entidad.getStr_nombres()+" "+entidad.getStr_apellido_paterno()+" "+entidad.getStr_apellido_materno());

        Intent svc = new Intent(this, clsGPSTrackService.class);
        this.startService(svc);


        imgUsuario = (CircleImageView) findViewById(R.id.imgUsuario);
        /*
        if(entidad.getFoto()!=null)
            imgUsuario.setImageBitmap(clsUtilidades.getBitmap(entidad.getFoto()));}
            */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
         navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        if (savedInstanceState == null) {
            selectItem(R.id.nav_inicio);
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction() .replace(R.id.main_mapa, new InicioFragment()).commit();
        setTitle(getString(R.string.inicio_item));
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        menuItem.setChecked(true);
                        String title = menuItem.getTitle().toString();
                        setTitle(title);

                        selectItem(menuItem.getItemId());
                        return true;
                    }
                }
        );
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void selectItem(int id) {
        Bundle arguments = new Bundle();
        android.support.v7.app.AlertDialog.Builder alert=null;
       switch (id)
        {

            case R.id.nav_inicio:
                setFragment(new DataFragment());

                break;

            case R.id.nav_ayuda:
                break;

            case R.id.nav_cerrar:

                alert = new android.support.v7.app.AlertDialog.Builder(this);
                alert.setTitle(getString(R.string.str_cerrar_sesion));
                alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        CerrarSesion();
                    }
                });
                alert.setNegativeButton(getString(R.string.btn_cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();


        }

        drawerLayout.closeDrawers();

    }


    public void setFragment( Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction() .replace(R.id.main_data, fragment).commit();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == tomarfoto && resultCode == RESULT_OK) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            if (fragments != null) {
            /*    for (Fragment fragment : fragments) {
                    if(fragment instanceof CarNuevoFragment) {
                        ((CarNuevoFragment) fragment).cargarGaleria();
                    }
                    else if(fragment instanceof FichaVariableFragment) {
                        ((FichaVariableFragment) fragment).cargarGaleria();
                    }
                    else if(fragment instanceof FichaEstandarFragment) {
                        ((FichaEstandarFragment) fragment).cargarGaleria();
                    }
                    else if(fragment instanceof SupervisionFragment) {
                        ((SupervisionFragment) fragment).cargarGaleria();
                    }

                }*/
            }

        }

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            AlertDialog.Builder alert;
            Fragment currentFrag =  getSupportFragmentManager().findFragmentById(R.id.main_data);
            if (currentFrag!=null) {
                switch (currentFrag.getClass().getSimpleName())
                {
                    case "InicioFragment":
                    case "EspecialidadesFragment":
                    case "ConsultasFragment":
                    case "CasosSaludFragment":

                        alert = new android.support.v7.app.AlertDialog.Builder(this);
                        alert.setTitle(getString(R.string.str_salir));
                        alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity.this.finish();
                            }
                        });
                        alert.setNegativeButton(getString(R.string.btn_cancelar), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });
                        alert.show();

                        break;
                    case "ClinicaInfoFragment":
                            setFragment(new DataFragment());
                        break;
                    case "DoctorInfoFragment":
                            setFragment(new DataFragment());
                        break;

                    case "RespuestaConsultaFragment":
                      //  setFragment(new ConsultasFragment());
                        break;

                    case "RespuestaCasosSaludFragment":
                      //  setFragment(new CasosSaludFragment());
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


    public void btnRealizarConsulta(View v)
    {
        clsUtilidades.alert(this,"XD");
    }



    public void CerrarSesion()
    {
        pd = new ProgressDialog(this);
        pd.setTitle("Cargando Datos");
        pd.setMessage("Espere un momento");
        pd.show();
        new Thread() {
            public void run() {
                try {
                    validador = false;
                    JSONObject loginJson = new JSONObject();
                    loginJson.put("idSesion",entidad.getInt_id());
                    postDataHTTP login = new postDataHTTP();
                    login.execute(loginJson.toString(),"SesionCerrar");
                    JSONObject entidadJSON = new JSONObject(login.get());
                    if(entidadJSON.getInt("rpta")==1) {
                        validador = true;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }




                handlerSesion.sendEmptyMessage(0);
            }
        }.start();





    }
    final Handler handlerSesion =new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(validador) {

                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                if (fragments != null) {
                    for (Fragment fragment : fragments) {
                        if(fragment instanceof InicioFragment) {
                            ((InicioFragment) fragment).stoptimertask();
                        }
                    }
                }
                clsSesionVigilanciaDAO.Borrar(MainActivity.this);
                clsTrackDAO.Borrar(MainActivity.this);
                clsVehiculoDAO.Borrar(MainActivity.this);
                clsCuadranteDAO.Borrar(MainActivity.this);
                clsEntidadDAO.Borrar(MainActivity.this);
                clsPersonalDAO.Borrar(MainActivity.this);
                Intent svc = new Intent(MainActivity.this, clsGPSTrackService.class);
                stopService(svc);
                pd.dismiss();
                finish();
            }


        }
    };
}
