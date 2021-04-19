package com.sdainfo.apiretrofit.act;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sdainfo.apiretrofit.R;
import com.sdainfo.apiretrofit.api.DataService;
import com.sdainfo.apiretrofit.model.Cep;
import com.sdainfo.apiretrofit.model.Photo;
import com.sdainfo.apiretrofit.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText textView1;
    TextView textView2;
    Button button;
    Retrofit retrofit;
    String urlCep = "https://viacep.com.br/ws/";
    String urlLista = "https://jsonplaceholder.typicode.com";
    List<Photo> photoList = new ArrayList<>();
    List<Post> postsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.tv_1);
        textView2 = findViewById(R.id.tv_2);
        button = findViewById(R.id.button);

        //configuracao de cep
        retrofit = new Retrofit.Builder().baseUrl(urlCep).addConverterFactory(GsonConverterFactory.create()).build();

        //cofig lista
        retrofit = new Retrofit.Builder().baseUrl(urlLista).addConverterFactory(GsonConverterFactory.create()).build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // recuperarCep();

                // recuperarListaPhotos();

                recuperarListaPosts();

            }
        });
    }

    private void recuperarCep() {

        String cep = textView1.getText().toString();

        DataService dataService = retrofit.create(DataService.class);
        Call<Cep> cepCall = dataService.recupararCep(cep);

        cepCall.enqueue(new Callback<Cep>() {
            @Override
            public void onResponse(Call<Cep> call, Response<Cep> response) {
                if (response.isSuccessful()) {
                    String mensagem = response.message();
                    Cep cepCorpoObjeto = response.body();

                    String endereco = "logradouro : " + cepCorpoObjeto.getLogradouro() +
                            "complemento : " + cepCorpoObjeto.getComplemento() +
                            "localidade : " + cepCorpoObjeto.getLocalidade() +
                            "bairro" + cepCorpoObjeto.getBairro() +
                            "UF : " + cepCorpoObjeto.getUf() +
                            "cep : " + cepCorpoObjeto.getCep();

                    textView2.setText(endereco);

                } else {
                    Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cep> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void recuperarListaPhotos() {
        DataService dataService = retrofit.create(DataService.class);

        Call<List<Photo>> callFoto = dataService.recuperarListaPhotos();
        callFoto.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    photoList = response.body();
                    for (int i = 0; i < photoList.size(); i++) {
                        Photo foto = photoList.get(i);
                        Log.d("Retorno ", "resultado id : " + foto.getId() + " title : " + foto.getTitle());
                        textView2.setText(foto.getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });


    }

    private void recuperarListaPosts() {
        DataService dataService = retrofit.create(DataService.class);

        Call<List<Post>> callPost = dataService.recuperarListaPosts();
        callPost.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    postsList = response.body();
                    for (int i = 0; i < postsList.size(); i++) {
                        Post post = postsList.get(i);
                        Log.d("Retorno ", "resultado id : " + post.getId() + " title : " + post.getTitle());
                        textView2.setText(post.getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

        Toast.makeText(this, "foi", Toast.LENGTH_SHORT).show();
    }


}