package com.sinergia.seguridad.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sinergia.seguridad.ui.MainActivity;
import com.sinergia.seguridad.ui.R;

public class PersonalFragment extends Fragment  {


    private MenuItem someMenuItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

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


}
