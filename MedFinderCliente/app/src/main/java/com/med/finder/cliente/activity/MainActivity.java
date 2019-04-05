package com.med.finder.cliente.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import com.med.finder.cliente.R;
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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public clsUsuario objUsuario;
    public int posFragmnet=0;
    public int posConsulta=0;
    private FloatingActionButton btnConsulta;
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
}
