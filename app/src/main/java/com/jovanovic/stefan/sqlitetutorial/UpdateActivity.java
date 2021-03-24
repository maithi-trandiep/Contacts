package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nom_input, prenom_input, telephone_input;
    Button update_button, delete_button;

    String id, nom, prenom, telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nom_input = findViewById(R.id.nom_input2);
        prenom_input = findViewById(R.id.prenom_input2);
        telephone_input = findViewById(R.id.telephone_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nom);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                nom = nom_input.getText().toString().trim();
                prenom = prenom_input.getText().toString().trim();
                telephone = telephone_input.getText().toString().trim();
                myDB.updateData(id, nom, prenom, telephone);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nom") &&
                getIntent().hasExtra("prenom") && getIntent().hasExtra("telephone")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            nom = getIntent().getStringExtra("nom");
            prenom = getIntent().getStringExtra("prenom");
            telephone = getIntent().getStringExtra("telephone");

            //Setting Intent Data
            nom_input.setText(nom);
            prenom_input.setText(prenom);
            telephone_input.setText(telephone);
            Log.d("stev", nom+" "+prenom+" "+telephone);
        }else{
            Toast.makeText(this, "Pas de contacts.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer " + nom + " ?");
        builder.setMessage("Etes-vous sûr de vouloir supprimer " + nom + " ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
