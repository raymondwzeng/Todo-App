package com.example.codepathprework;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

    public static final String positionKey = "POSITION";
    public static final String contentKey = "CONTENT";
    public static final int requestCode = 200;

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

        ItemsAdapter.OnClickListener onClickListener = new ItemsAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                //Create a new intent
                Intent editIntent = new Intent(MainActivity.this, EditActivity.class);
                Log.d("MainActivity", "Type: " + editIntent.getType());
                editIntent.putExtra(positionKey, position);
                editIntent.putExtra(contentKey, items.get(position));
                startActivityForResult(editIntent, requestCode);
            }
        };

        //Create the adapter
        itemsAdapter = new ItemsAdapter(items, onLongClickListener, onClickListener);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MainActivity.requestCode && resultCode == RESULT_OK)
        {
            //Prepare data for changes
            int position = data.getExtras().getInt(positionKey);
            String newString = data.getStringExtra(contentKey);
            items.set(position, newString);
            Log.d("MainActivity", "New string: " + items.get(position));
            itemsAdapter.notifyItemChanged(position);
            WriteData();
            Toast.makeText(getApplicationContext(), "Edited task is now: "+newString, Toast.LENGTH_SHORT).show();
        } else {
            Log.d("MainActivity", "A different request code was sent, or edit was canceled.");
        }
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