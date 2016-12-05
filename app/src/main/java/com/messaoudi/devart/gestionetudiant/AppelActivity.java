package com.messaoudi.devart.gestionetudiant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AppelActivity extends AppCompatActivity {

    LinearLayout layoutEtud;
    EditText txtnom, txtprenom, txtclasse;
    Button btnAjouter, btnValider;
    ListView listEtudiant;
    List<String> donneees = new ArrayList<>();
    List<String> absent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appel);
        getSupportActionBar().setTitle("Appel");
        initFind();
        Listener_click();
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
                startActivity(new Intent(this, AppelActivity.class));
                return true;
            case R.id.menu_note:
                startActivity(new Intent(this, NoteActivity.class));
                return true;
            case R.id.menu_sanction:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initFind() {
        layoutEtud = (LinearLayout) findViewById(R.id.layoutEtudAppel);
        txtnom = (EditText) findViewById(R.id.txtNomAppel);
        txtprenom = (EditText) findViewById(R.id.txtPrenomAppel);
        txtclasse = (EditText) findViewById(R.id.txtClassAppel);
        btnAjouter = (Button) findViewById(R.id.btnAjouterAppel);
        btnValider = (Button) findViewById(R.id.btnValiderAppel);
        listEtudiant = (ListView) findViewById(R.id.listEtudAppel);
    }

    public void Listener_click() {
        btnAjouter.setOnClickListener(ajoutListener);
        btnValider.setOnClickListener(validerListener);
    }


    View.OnClickListener ajoutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nom, prenom, classe, sommeTxt;
            nom = txtnom.getText().toString();
            prenom = txtprenom.getText().toString();
            classe = txtclasse.getText().toString();
            sommeTxt = nom + " " + prenom + " : " + classe;
            donneees.add(sommeTxt);
            ArrayAdapter arrayAdapter = new ArrayAdapter(AppelActivity.this, android.R.layout.simple_list_item_multiple_choice, donneees);
            listEtudiant.setAdapter(arrayAdapter);
            listEtudiant.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            layoutEtud.setVisibility(View.VISIBLE);
            btnValider.setVisibility(View.VISIBLE);
        }
    };

    View.OnClickListener validerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SparseBooleanArray sparseBooleanArray = listEtudiant.getCheckedItemPositions();
            absent.clear();
            for (int i = 0; i < donneees.size(); i++){
                if(!sparseBooleanArray.get(i)){
                    absent.add(listEtudiant.getItemAtPosition(i).toString());
                }
            }
            FragmentManager fm = AppelActivity.this.getSupportFragmentManager();
            AbscenceDialog dialog = AbscenceDialog.newInstance();
            dialog.show(fm,null);
        }
    };

    public List<String> getAbsent() {
        return absent;
    }
}
