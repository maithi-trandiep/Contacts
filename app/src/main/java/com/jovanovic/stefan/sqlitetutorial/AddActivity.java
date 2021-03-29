package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText nom_inpu, prenom_input, telephone_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nom_input = findViewById(R.id.nom_input);
        prenom_input = findViewById(R.id.prenom_input);
        telephone_input = findViewById(R.id.telephone_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addContact(nom_input.getText().toString().trim(),
                        prenom_input.getText().toString().trim(),
                        telephone_input.getText().toString().trim());
            }
        });
    }
}
