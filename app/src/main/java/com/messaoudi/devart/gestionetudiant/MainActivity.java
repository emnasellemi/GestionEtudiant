package com.messaoudi.devart.gestionetudiant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Gestion des Etudiants");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_appel:
                startActivity(new Intent(this,AppelActivity.class));
                return true;
            case R.id.menu_note:
                startActivity(new Intent(this,NoteActivity.class));
                return true;
            case R.id.menu_sanction:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
