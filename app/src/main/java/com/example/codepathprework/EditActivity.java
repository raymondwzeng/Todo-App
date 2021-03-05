package com.example.codepathprework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    EditText textBox;
    Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        textBox = findViewById(R.id.textBox);
        saveButton = findViewById(R.id.saveButton);

        getSupportActionBar().setTitle("Edit Task");

        textBox.setText(getIntent().getStringExtra(MainActivity.contentKey));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent(); //We don't need to specify the destination since we're going back?
                returnIntent.putExtra(MainActivity.contentKey, textBox.getText().toString());
                returnIntent.putExtra(MainActivity.positionKey, getIntent().getExtras().getInt(MainActivity.positionKey)); //Add extras back
                setResult(RESULT_OK, returnIntent); //Returns
                finish();
            }
        });
    }
}
