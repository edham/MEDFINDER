package com.sinergia.seguridad.fragment;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sinergia.seguridad.dao.clsPersonalDAO;
import com.sinergia.seguridad.dao.clsSesionVigilanciaDAO;
import com.sinergia.seguridad.dao.clsVehiculoDAO;
import com.sinergia.seguridad.entidades.clsPersonal;
import com.sinergia.seguridad.entidades.clsSesionVigilancia;
import com.sinergia.seguridad.entidades.clsVehiculo;
import com.sinergia.seguridad.ui.MainActivity;
import com.sinergia.seguridad.ui.R;
import com.sinergia.seguridad.utilidades.clsUtilidades;

import java.util.List;

public class SesionFragment extends Fragment  {

    private List<clsPersonal> listPersonal;

    private MenuItem someMenuItem;
    private int idSesion=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sesion, container, false);
        idSesion = getArguments().getInt("idSesion");
        clsSesionVigilancia objSesionVigilancia= clsSesionVigilanciaDAO.BuscarId(this.getActivity(), idSesion);
        TextView lblFecha = (TextView)view.findViewById(R.id.lblFecha);
        TextView lblHora = (TextView)view.findViewById(R.id.lblHora);
        TextView lblCuadrante = (TextView)view.findViewById(R.id.lblCuadrante);
        TextView lblNVehiculo = (TextView)view.findViewById(R.id.lblNVehiculo);
        TextView lblPVehiculo = (TextView)view.findViewById(R.id.lblPVehiculo);
        ListView listVPersonal = (ListView)view.findViewById(R.id.listVPersonal);
        Button btnCerrar = (Button)view.findViewById(R.id.btnCerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setFragment(new DataFragment());
            }
        });

        lblFecha.setText(clsUtilidades.getFecha(objSesionVigilancia.getDat_fec_ini()));
        lblHora.setText(clsUtilidades.getHora(objSesionVigilancia.getDat_fec_ini()));
        lblCuadrante.setText(objSesionVigilancia.getObjCuadrante().getStr_nombre());

        clsVehiculo objVehiculo = clsVehiculoDAO.BuscarIdSesion(this.getActivity(), idSesion);
        lblNVehiculo.setText(""+objVehiculo.getInt_numero());
        lblPVehiculo.setText(objVehiculo.getStr_placa());

        listPersonal = clsPersonalDAO.ListarIdSesion(this.getActivity(), idSesion);

        AdaptadorPersonal adaptador = new AdaptadorPersonal(this.getActivity());

        listVPersonal.setAdapter(adaptador);

        setHasOptionsMenu(true);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.inicio, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        android.support.v7.app.AlertDialog.Builder alert=null;
        switch (item.getItemId()) {
            case R.id.action_mas:
                ((MainActivity)  getActivity()).zoonMapa(true);
                return true;
            case R.id.action_menos:
                ((MainActivity)  getActivity()).zoonMapa(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        someMenuItem = menu.findItem(R.id.action_mas);
       /* if(objDoctor.isBol_favorito())
            someMenuItem.setIcon(R.drawable.action_favorito_on);
        else
            someMenuItem.setIcon(R.drawable.action_favorito_off);*/
    }




    class AdaptadorPersonal extends ArrayAdapter {

        Activity context;
        AdaptadorPersonal(Activity context) {
            super(context, R.layout.list_personal, listPersonal);
            this.context = context;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_personal, null);
            TextView lblTipo = (TextView)item.findViewById(R.id.lblTipo);
            if(listPersonal.get(position).isBool_principal())
                lblTipo.setText(getString(R.string.str_conductor));
            else
                lblTipo.setText(getString(R.string.str_copiloto));

            TextView lblNombres = (TextView)item.findViewById(R.id.lblNombres);
            lblNombres.setText(listPersonal.get(position).getStr_nombres());


            TextView lblApellidos = (TextView)item.findViewById(R.id.lblApellidos);
            lblApellidos.setText(listPersonal.get(position).getStr_ape_paterno()+" "+listPersonal.get(position).getStr_ape_materno());

            TextView lblGrado = (TextView)item.findViewById(R.id.lblGrado);
            lblGrado.setText(listPersonal.get(position).getStr_cargo());

            TextView lblTelefono = (TextView)item.findViewById(R.id.lblTelefono);
            lblTelefono.setText(listPersonal.get(position).getStr_telefono());

            return item;
        }
    }
}
