package com.welder.appfilmes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import com.welder.appfilmes.Adapter.AdapterFilme;
import com.welder.appfilmes.Model.Filme;
import com.welder.appfilmes.Model.FilmeApi;
import com.welder.appfilmes.OnClick.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycle_filmes;
    private AdapterFilme adapterFilme;
    private List<Filme> filmeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        IniciarComponentes();

        filmeList = new ArrayList<>();

        // Evento de Click na recyclerView
        recycle_filmes.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recycle_filmes,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(),Detalhes_filme.class);
                        intent.putExtra("capa", filmeList.get(position).getCapa());
                        intent.putExtra("titulo", filmeList.get(position).getTitulo());
                        intent.putExtra("descricao",filmeList.get(position).getDescricao());
                        intent.putExtra("elenco", filmeList.get(position).getElenco());
                        intent.putExtra("video", filmeList.get(position).getVideo());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ));



        //configurar retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://firebasestorage.googleapis.com/v0/b/app-delivery-c204b.appspot.com/o/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        //Iniciar Retrofit

        FilmeApi filmeApi =retrofit.create(FilmeApi.class);
        Call<List<Filme>> call = filmeApi.getFilmes();
        call.enqueue(new Callback<List<Filme>>() {
            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                if (response.code() != 200){
                    return;
                }
                List<Filme> filmes = response.body();

                for (Filme filme : filmes){
                    filmeList.add(filme);
                }

                adapterFilme = new AdapterFilme(getApplicationContext(),filmeList);
                recycle_filmes.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                recycle_filmes.setHasFixedSize(true);
                recycle_filmes.setAdapter(adapterFilme);

            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {

            }
        });

    }

    public void IniciarComponentes(){

        recycle_filmes = findViewById(R.id.recyclerfilmes);

    }
}