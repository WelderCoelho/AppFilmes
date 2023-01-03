package com.welder.appfilmes.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmeApi {

            @GET("filmes.json?alt=media&token=7c5668f0-577e-4308-9310-f0cb4c79da1a")
            Call<List<Filme>> getFilmes();
}
