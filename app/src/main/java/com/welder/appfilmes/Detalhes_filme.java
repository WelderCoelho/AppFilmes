package com.welder.appfilmes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detalhes_filme extends AppCompatActivity {

    private ImageView capaFilme, play;
    private TextView texto_detalhe, elencoFilme, nomeFilme;
    private Toolbar toolbar_detalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);
        getSupportActionBar().hide();
        IniciarComponentes();

        String capa = getIntent().getExtras().getString("capa");
        String titulo = getIntent().getExtras().getString("titulo");
        String descricao = getIntent().getExtras().getString("descricao");
        String elenco = getIntent().getExtras().getString("elenco");
        String video = getIntent().getExtras().getString("video");

        String stVideo = video;

        Glide.with(getApplicationContext()).load(capa).into(capaFilme);
        nomeFilme.setText(titulo);
        elencoFilme.setText(elenco);
        texto_detalhe.setText(descricao);

        //Toolbar

        toolbar_detalhe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detalhes_filme.this,Video.class);
                intent.putExtra("video", stVideo);
                startActivity(intent);
            }
        });


    }

    public void IniciarComponentes(){

        capaFilme = findViewById(R.id.img_detalhe);
        play = findViewById(R.id.play);
        nomeFilme = findViewById(R.id.nomeFilmeDetalhe);
        elencoFilme = findViewById(R.id.ElencoFilmeDetalhe);
        texto_detalhe = findViewById(R.id.textoFilmeDetalhe);
        toolbar_detalhe = findViewById(R.id.toolbar_detalhe);

    }
}