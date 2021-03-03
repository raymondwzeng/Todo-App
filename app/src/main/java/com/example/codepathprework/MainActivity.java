package com.example.codepathprework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.apache.commons.io.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    List<String> items; //Create our global list of items

    Button button;
    EditText editText;
    RecyclerView recyclerView;

    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.buttonAdd);
        editText = findViewById(R.id.taskInput);
        recyclerView = findViewById(R.id.itemView);

        ReadData();

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener()
        {
            @Override
            public void onItemLongClicked(int position) {
                Toast.makeText(getApplicationContext(), "Removed a task: "+items.get(position), Toast.LENGTH_SHORT).show();
                items.remove(position);
                itemsAdapter.notifyItemRemoved(position);
                WriteData();
            }
        };

        //Create the adapter
        itemsAdapter = new ItemsAdapter(items, onLongClickListener);
        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = editText.getText().toString();
                items.add(task);
                itemsAdapter.notifyItemInserted(items.size()-1);
                editText.setText(""); //Clear out the text field
                Toast.makeText(getApplicationContext(), "Created a new task: "+task, Toast.LENGTH_SHORT).show();
                WriteData();
            }
        });
    }

    private File getData() {
        return new File(getFilesDir(), "data.txt");
    }

    private void ReadData()
    {
        try {
            items = new ArrayList<>(FileUtils.readLines(getData(), Charset.defaultCharset()));
        } catch (Exception e) {
            Log.e("MainActivity", "Error while reading data: ", e);
            items = new ArrayList<>();
        }
    }

    private void WriteData()
    {
        try {
            FileUtils.writeLines(getData(), items);
        } catch (Exception e) {
            Log.e("MainActivity", "Error while writing data: ", e);
        }
    }
}