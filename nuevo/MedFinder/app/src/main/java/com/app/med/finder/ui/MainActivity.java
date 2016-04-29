package com.app.med.finder.ui;

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
import com.app.med.finder.dao.clsUsuarioDAO;
import com.app.med.finder.entidades.clsUsuario;
import com.app.med.finder.fragment.*;
import com.app.med.finder.utilidades.clsUtilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public boolean drawerCar=true;
    public boolean drawerInstitucion=true;
    public NavigationView navigationView;
    private String drawerTitle;
    private TextView lblUsuario;
    private CircleImageView imgUsuario;
    private clsUsuario entidad;
    private static int tomarfoto=0;
    private int total=0;
    boolean validador = true;
    public ProgressDialog pdCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entidad= clsUsuarioDAO.Buscar(this);
        setToolbar(); // Setear Toolbar como action bar
        lblUsuario = (TextView) findViewById(R.id.lblUsuario);
        lblUsuario.setText(entidad.getStr_nombres()+" "+entidad.getStr_apellido_paterno()+" "+entidad.getStr_apellido_materno());

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
            selectItem(R.id.nav_busqueda);
        }

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
            case R.id.nav_consultas:
                setFragment(new ConsultasFragment());

                break;
            case R.id.nav_busqueda:
                setFragment(new InicioFragment());

                break;
            case R.id.nav_clinicas:


                break;
            case R.id.nav_familares:


                break;
            case R.id.nav_citas:


                break;
            case R.id.nav_favoritos:

                break;
            case R.id.nav_casos:
                setFragment(new CasosSaludFragment());
                break;
            case R.id.nav_informacion:
                setFragment(new EspecialidadesFragment());
                break;

            case R.id.nav_ayuda:
                break;

            case R.id.nav_cerrar:

                break;

        }

        drawerLayout.closeDrawers();

    }


    public void setFragment( Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction() .replace(R.id.main_content, fragment).commit();
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
            Fragment currentFrag =  getSupportFragmentManager().findFragmentById(R.id.main_content);
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
                            setFragment(new InicioFragment());
                        break;
                    case "DoctorInfoFragment":
                            setFragment(new InicioFragment());
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


    public void btnRealizarConsulta(View v)
    {
        clsUtilidades.alert(this,"XD");
    }
}
