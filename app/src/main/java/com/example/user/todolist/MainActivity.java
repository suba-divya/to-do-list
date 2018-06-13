package com.example.user.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.todolist.R;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    public static final String debugtag = "JWP";
    public static final String textfile="todolist.txt";
    public static final String filesaved = "FileSaved";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addSaveButtonlistner();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        boolean FileSaved = prefs.getBoolean(filesaved,false);
        if(FileSaved){

            loadsavedfile();}


    }

    private void loadsavedfile(){
        try {
            FileInputStream fis =  openFileInput(textfile);
            BufferedReader Reader =  new BufferedReader(new InputStreamReader(new DataInputStream(fis)));
            EditText editText =(EditText)findViewById(R.id.text);
            String line;
            while((line= Reader.readLine())!= null){
                editText.append(line);
                editText.append("\n");

            }
            fis.close();
        } catch (Exception e) {
            Log.d(debugtag,"unable to read file" );

        }

    }
    private void addSaveButtonlistner(){
        Button savebtn= (Button)findViewById(R.id.save);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText =(EditText)findViewById(R.id.text);
                String text = editText.getText().toString();
                try {

                    FileOutputStream fos =  openFileOutput(textfile , Context.MODE_PRIVATE);
                    fos.write(text.getBytes());
                    fos.close();
                    SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean(filesaved,true);
                    editor.commit();





                } catch (Exception e) {
                    Log.d(debugtag,"unable to save file" );

                }




            }
        });
    }


}
