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

import com.example.aula7.taller3usuariospostcomments.Adapter.AdapterComenst;
import com.example.aula7.taller3usuariospostcomments.Conecction.HttpManager;
import com.example.aula7.taller3usuariospostcomments.Model.Commentsmodel;
import com.example.aula7.taller3usuariospostcomments.Parser.JsonComents;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progressBarcoments;
    RecyclerView recyclerViewcoments;
    List<Commentsmodel> adapterCommentsmodelList;
    AdapterComenst adapterComenst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        toolbar = (Toolbar)findViewById(R.id.id_tb_toolbar);
        showtoolbar(getResources().getString(R.string.str_tb_p3),true);



        progressBarcoments = (ProgressBar) findViewById(R.id.id_pb_item_comments);
        recyclerViewcoments = (RecyclerView) findViewById(R.id.id_rv_item_commenst);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewcoments.setLayoutManager(linearLayoutManager);

        loadData(Integer.toString(getIntent().getExtras().getInt("idPost")));
    }
    public void showtoolbar(String title, boolean upButton){

        //muestra el tooblar y configura pantalla
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_p3,menu);
        return super.onCreateOptionsMenu(menu);

    }
    public void showPantalla2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        loadData(Integer.toString(getIntent().getExtras().getInt("idPost")));
        return super.onOptionsItemSelected(item);
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

    public void loadData(String idPost){
        if (isOnLine()){
            Taskcoments taskcoments = new Taskcoments();
            taskcoments.execute("https://jsonplaceholder.typicode.com/comments?postId="+idPost);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        adapterComenst = new AdapterComenst(adapterCommentsmodelList, getApplicationContext());
        recyclerViewcoments.setAdapter(adapterComenst);
    }

    public class Taskcoments extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarcoments.setVisibility(View.VISIBLE);
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
                adapterCommentsmodelList = JsonComents.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();

            progressBarcoments.setVisibility(View.GONE);
        }
    }
}


