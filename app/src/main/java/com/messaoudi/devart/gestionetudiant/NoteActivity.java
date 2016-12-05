package com.messaoudi.devart.gestionetudiant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;
import android.widget.AdapterView.OnItemSelectedListener;

public class NoteActivity extends AppCompatActivity {
    Spinner spinnertype;
    EditText nom,prenom,classe,coef,mat,note;
    Button btnajouter;
    ListView listnote;
    String type;
    List<String> donneees = new ArrayList<>();
    List<String> TypeList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        getSupportActionBar().setTitle("Notes");
        initView();

        // Initializing a String Array
        String[] types = new String[]{
                "DS",
                "TP",
                "EXAMEN"
        };

       TypeList  = new ArrayList<>(Arrays.asList(types));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,TypeList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnertype.setAdapter(spinnerArrayAdapter);
        initListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
    public  void initView(){
        nom=(EditText)findViewById(R.id.txtNomNotes);
        prenom=(EditText)findViewById(R.id.txtPrenomNotes);
        classe=(EditText)findViewById(R.id.txtClassNote);
        mat=(EditText)findViewById(R.id.txtMatiereNote);
        note=(EditText)findViewById(R.id.txtNoteNotes);
        coef=(EditText)findViewById(R.id.txtCoefNote);
        spinnertype=(Spinner)findViewById(R.id.txtSpinnerType);
        btnajouter=(Button)findViewById(R.id.btnAjouterNote);
        listnote=(ListView)findViewById(R.id.listEtudNote);

    }
    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void initListener() {
        btnajouter.setOnClickListener(ajoutListener);
        spinnertype.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        type= spinnertype.getItemAtPosition(position).toString();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        showToast("Spinner1: unselected");
                    }
                });
    }
    View.OnClickListener ajoutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String txtnom, txtprenom, txtclasse,txtmat,txtnote,txtcoef, sommeTxt;
            txtnom = nom.getText().toString();
            txtnote = note.getText().toString();
            txtprenom = prenom.getText().toString();
            txtclasse = classe.getText().toString();
            txtmat=mat.getText().toString();
            txtcoef=coef.getText().toString();


            sommeTxt = txtnom + " " + txtprenom + " : " + txtclasse+" /matiére: "+txtmat+" /Note :"+txtnote+" /coefficient :"+txtcoef+" /Type :"+type;
            donneees.add(sommeTxt);
            ArrayAdapter arrayAdapter = new ArrayAdapter(NoteActivity.this, android.R.layout.simple_list_item_1, donneees);
            listnote.setAdapter(arrayAdapter);
        }
    };


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
