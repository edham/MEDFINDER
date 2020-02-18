package com.med.finder.doctor.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.med.finder.doctor.R;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.entidades.clsDoctor;
import com.med.finder.doctor.fragment.CasosSaludFragment;
import com.med.finder.doctor.fragment.CitasFragment;
import com.med.finder.doctor.fragment.ConsultasFragment;
import com.med.finder.doctor.fragment.InicioFragment;
import com.med.finder.doctor.utilidades.CustomFontTextView;
import com.med.finder.doctor.utilidades.Utilidades;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public clsDoctor objDoctor;
    public int posFragmnet=0;
    public int posConsulta=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utilidades.hideKeyboard(this);
        objDoctor= clsDoctorDAO.Buscar(this);
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
        if(objDoctor.getByte_foto()!=null)
        {
            perfil= Utilidades.getBitmap(objDoctor.getByte_foto());
        }
        View headerView = navigationView.getHeaderView(0);
        CircleImageView imvPerfil = (CircleImageView) headerView.findViewById(R.id.imvPerfil);
        TextView lblUsuario = (TextView) headerView.findViewById(R.id.lblUsuario);
        TextView lblNombre = (TextView) headerView.findViewById(R.id.lblNombre);
        lblUsuario.setText(objDoctor.getObjEspecialidad().getStr_nombre());
        lblNombre.setText(objDoctor.toString().toUpperCase());
        imvPerfil.setImageBitmap(perfil);

        navigationView.getMenu().getItem(0).setChecked(true);
        setTitle(navigationView.getMenu().getItem(0).getTitle());

        InicioFragment newFragment = new InicioFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("dato",getIntent().getExtras().getInt("dato"));
        newFragment.setArguments(bundle);

        setFragment(newFragment);
        Utilidades.checkPermissions(this);
        Utilidades.addListaBlanca(this);
        Utilidades.scheduleChargingReminder(this);




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
            case R.id.nav_citas:
                setFragment(new CitasFragment());
                break;
            case R.id.nav_casos:
               setFragment(new CasosSaludFragment());
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

                    case "ConsultasFragment":
                    case "CasosSaludFragment":
                    case "CitasFragment":
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
                  /*
                    if(fragment instanceof RealizarConsultasFragment) {
                        ImagenArchivo.resizedImage();
                        ((RealizarConsultasFragment) fragment).cargarFoto();
                    }
*/
                }
            }
        }
    }
}
