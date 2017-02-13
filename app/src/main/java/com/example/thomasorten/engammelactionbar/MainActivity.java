package com.example.thomasorten.engammelactionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

public class MainActivity extends Activity implements ActionBar.TabListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); // Her sier vi// Her sier vi
        bar.setDisplayShowHomeEnabled(false); // Fjern app ikon
        bar.addTab(bar.newTab().setText("En").setTabListener(this));
        bar.addTab(bar.newTab().setText("To").setTabListener(this));

        SmartLocation.with(this).location().oneFix().start(new OnLocationUpdatedListener() {
            @Override
            public void onLocationUpdated(Location location) {
                Log.d("loc", location.toString());
            }
        });

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        FragmentManager fm = getFragmentManager();

        Fragment fragmentOne = new BlankFragment();
        Fragment fragmentTwo = new BlankFragmentTwo();

        if (tab.getPosition() == 0) {
            fm.beginTransaction().replace(R.id.container, fragmentOne).commit();
        }

        if (tab.getPosition() == 1) {
            fm.beginTransaction().replace(R.id.container, fragmentTwo).commit();
        }

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
