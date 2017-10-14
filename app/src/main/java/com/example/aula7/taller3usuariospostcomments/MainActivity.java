package com.example.aula7.taller3usuariospostcomments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.id_tb_toolbar);
        showtoolbar(getResources().getString(R.string.str_tb_p1));
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
        showPantalla2();
        return super.onOptionsItemSelected(item);


    }
    public void showPantalla2() {
        //Intent Pantalla2 = new Intent(getApplicationContext(), Pantalla2.class);
        //startActivity(Pantalla2);
    }
}
