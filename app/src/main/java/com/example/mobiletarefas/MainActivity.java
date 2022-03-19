package com.example.mobiletarefas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Cria a variavel da imagem e do botão
    private ImageView imageViewFoto;
    private Button btnGeo;

    //Chama a tela do activity_main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Atribui o botão criado no activity_main.xml ao btnGeo e pede permissão da (fine) location
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        //Pegar o click do botão
        btnGeo.setOnClickListener(new View.OnClickListener() {
            //Roda a classe criada GPSTracker, cria uma variavel l e pega o location do GPSTracker e atribui a ela
            @Override
            public void onClick(View view) {
                GPSTracker g = new GPSTracker(getApplication());
                Location l = g.getLocation();
                //Atribui os valores de latitude e longitude as variaveis lat e lon e mostra elas com um Toast no tempo "long"
                if(l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE: " + lat +"\n LONGITUDE: " + lon, Toast.LENGTH_LONG).show();
                }
            }
        });
        //Checa se foi dado a permissão da camera.
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 0);
        }
        //Atribui a imagem criada no activity_main.xml ao imageViewFoto
        imageViewFoto = (ImageView) findViewById(R.id.image_foto);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {

            //Chama o metodo onClick e automaticamente chama o metodo "pessoal" tirarFoto.
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
        });
    }

    //Cria metodo tirar foto, que ten como intenção fazer a ação de capturar a imagem.
    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    //Chama metodo da super classe e 3 parametros
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //Se condições do if foram verdadeiras ele salva a foto tirada.
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}