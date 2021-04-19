package com.sdainfo.apiretrofit.api;

import com.sdainfo.apiretrofit.model.Cep;
import com.sdainfo.apiretrofit.model.Photo;
import com.sdainfo.apiretrofit.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataService {

    @GET("01001000/json/") //estatico
    Call <Cep> recupararCepEstatico();

    @GET("{cep}/json/") //com parametro
    Call <Cep> recupararCep(@Path("cep") String cep);

    @GET("/photos")
    Call<List<Photo>> recuperarListaPhotos();

    @GET("/posts")
    Call<List<Post>> recuperarListaPosts();

}








