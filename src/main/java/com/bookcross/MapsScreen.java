package com.bookcross;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.bookcross.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsScreen extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng helo_cruise = new LatLng(53.24991507744624, 45.01487152712071);
        mMap.addMarker(new MarkerOptions().position(helo_cruise).title("HELIOPARK Cruise").snippet("2 этаж в зоне ресепшн"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(helo_cruise));

        LatLng helo_res = new LatLng(53.19162178578689, 45.01971653274292);
        mMap.addMarker(new MarkerOptions().position(helo_res).title("HELIOPARK Residence").snippet("1 этаж в зоне ресепшн"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(helo_res));

        LatLng anticafe1 = new LatLng(53.20100890011625, 45.01899579779215);
        mMap.addMarker(new MarkerOptions().position(anticafe1).title("Антикафе Крылья").snippet("Второй этаж, " +
                "полка на стеллаже прямо от входа"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(anticafe1));

        LatLng anticafe2 = new LatLng(53.18621098037059, 45.0139297615496);
        mMap.addMarker(new MarkerOptions().position(anticafe2).title("Антикафе Чердак").snippet("Слева от входа"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(anticafe2));

        LatLng bezPol = new LatLng(53.18225024037387, 45.01447081128518);
        mMap.addMarker(new MarkerOptions().position(bezPol).title("Безопасная полка"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bezPol));

        LatLng cityLid = new LatLng(53.22219550309377, 44.93980098720748);
        mMap.addMarker(new MarkerOptions().position(cityLid).title("Городская библиотека №17").snippet("Прямо от входа"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cityLid));

        LatLng home = new LatLng(53.19230724657142, 45.01996545623978);
        mMap.addMarker(new MarkerOptions().position(home).title("Дом молодёжи").snippet("Центральный вход"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(home));

        LatLng labirint = new LatLng(53.208130530439796, 44.9956903110006);
        mMap.addMarker(new MarkerOptions().position(labirint).title("Лабиринт").snippet("Бизнес-центр Сокол"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(labirint));

        LatLng labirint2 = new LatLng(53.18899857762263, 45.01555498712542);
        mMap.addMarker(new MarkerOptions().position(labirint2).title("Лабиринт").snippet("Бизнес-центр Гермес"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(labirint2));

        LatLng san_mart = new LatLng(53.20036619297247, 45.01172338571275);
        mMap.addMarker(new MarkerOptions().position(san_mart).title("Сан и Март").snippet("Полка находится " +
                "напротив входа со стороны ул.Бакунина"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(san_mart));

        LatLng tipgraf = new LatLng(53.226782453430125, 45.01611692179001);
        mMap.addMarker(new MarkerOptions().position(tipgraf).title("Типография №1").snippet("Книжная " +
                "полка в офисе Типографии"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tipgraf));

        LatLng tc_summer = new LatLng(53.22227485041697, 44.91564274012236);
        mMap.addMarker(new MarkerOptions().position(tc_summer).title("ТЦ Лето"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tc_summer));

        LatLng tc_mart = new LatLng(53.215527219132376, 44.970162655463426);
        mMap.addMarker(new MarkerOptions().position(tc_mart).title("ТЦ Март").snippet("Безопасная полка напротив входа"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tc_mart));
    }
}
