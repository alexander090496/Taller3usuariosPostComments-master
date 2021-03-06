package com.example.aula7.taller3usuariospostcomments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.aula7.taller3usuariospostcomments.Adapter.Adapterpost;
import com.example.aula7.taller3usuariospostcomments.Conecction.HttpManager;
import com.example.aula7.taller3usuariospostcomments.Model.Postmodel;
import com.example.aula7.taller3usuariospostcomments.Parser.Jsonpost;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progressBarpost;
    RecyclerView recyclerViewpost;
    List<Postmodel> postmodelList;
    Adapterpost adapterpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        toolbar = (Toolbar)findViewById(R.id.id_tb_toolbar);
        showtoolbar(getResources().getString(R.string.str_tb_p2),true);
        progressBarpost = (ProgressBar) findViewById(R.id.id_pb_item_post);
        recyclerViewpost = (RecyclerView) findViewById(R.id.id_rv_item_post);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewpost.setLayoutManager(linearLayoutManager);

        loadData(Integer.toString(getIntent().getExtras().getInt("idUser")));
    }
    public void showtoolbar(String title,boolean upButton){
        //muestra el tooblar y configura pantalla
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_p2,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        loadData(Integer.toString(getIntent().getExtras().getInt("idUser")));
        return super.onOptionsItemSelected(item);
    }
    public void showPantalla1() {
        Intent Pantalla1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(Pantalla1);
    }
//############################################
    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }

    public void loadData(String idUser){
        if (isOnLine()){
            TaskPost taskPost = new TaskPost();
            taskPost.execute("https://jsonplaceholder.typicode.com/posts?userId="+idUser);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        adapterpost = new Adapterpost(postmodelList, getApplicationContext());
        recyclerViewpost.setAdapter(adapterpost);
    }

    public class TaskPost extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarpost.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                postmodelList = Jsonpost.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarpost.setVisibility(View.GONE);
        }
    }
}
