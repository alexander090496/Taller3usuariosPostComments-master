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

import com.example.aula7.taller3usuariospostcomments.Adapter.AdapterUser;
import com.example.aula7.taller3usuariospostcomments.Conecction.HttpManager;
import com.example.aula7.taller3usuariospostcomments.Model.Usermodel;
import com.example.aula7.taller3usuariospostcomments.Parser.Jsonuser;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progressBarusers;
    RecyclerView recyclerViewusers;
    List<Usermodel> adapterUserList;
    AdapterUser adapterUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.id_tb_toolbar);
        showtoolbar(getResources().getString(R.string.str_tb_p1));
        progressBarusers = (ProgressBar) findViewById(R.id.id_pb_item_users);
        recyclerViewusers = (RecyclerView) findViewById(R.id.id_rv_item1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewusers.setLayoutManager(linearLayoutManager);

        loadData();
    }

    public void showtoolbar(String title){

        //muestra el tooblar y configura pantalla
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_p1,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        loadData();
        return super.onOptionsItemSelected(item);


    }
    public void showPantalla2() {
        Intent Pantalla2 = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(Pantalla2);
    }
    //##################################

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

    public void loadData(){
        if (isOnLine()){
            TaskUser taskUser = new TaskUser();
            taskUser.execute("https://jsonplaceholder.typicode.com/users");
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        adapterUser = new AdapterUser(adapterUserList, getApplicationContext());
        recyclerViewusers.setAdapter(adapterUser);
    }

    public class TaskUser extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarusers.setVisibility(View.VISIBLE);
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
                adapterUserList = Jsonuser.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarusers.setVisibility(View.GONE);
        }
    }




}
