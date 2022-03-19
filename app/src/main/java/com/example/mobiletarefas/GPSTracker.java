package com.example.mobiletarefas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPSTracker implements LocationListener {
    Context context;
    //Cria metodo publico com a atribuição ao context o valor c
    public GPSTracker(Context c){
        context = c;
    }
    //Cria metodo getLocation
    public Location getLocation(){
        //Checa se permissão foi concedida e armazena informação no context.
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            //Mostra mensagem que permissão não foi concedida.
            Toast.makeText(context, "Não foi permitir", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //Se usuario concedeu permissão, ele retorna as informações do GPS.
        if (isGPSEnabled)
        {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
            //Se não estiver coletando informação do GPS, mostra mensagem para habilitar o GPS com tempo de exibição long.
            Toast.makeText(context, "Por favor, habitar o GPS!", Toast.LENGTH_LONG).show();
        }
        //É necessario retornar um valor para o if, no caso é retornado null
        return null;
    }
    //Metodo existente na super classe, mas não é utilizado, como ela existe é necessario retornar algum valor, no caso nenhum valor em especifico.
    @Override
    public void onProviderDisabled(@NonNull String provider) {    }
    //Metodo existente na super classe, mas não é utilizado, como ela existe é necessario retornar algum valor, no caso nenhum valor em especifico.
    @Override
    public void onLocationChanged(@NonNull Location location) {    }
    //Metodo existente na super classe, mas não é utilizado, como ela existe é necessario retornar algum valor, no caso nenhum valor em especifico.
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {    }
}

