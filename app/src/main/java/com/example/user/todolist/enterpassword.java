package com.example.user.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.id.button2;

public class enterpassword extends AppCompatActivity {
    EditText editText;
    Button button,button2;
    String password;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterpassword);

        SharedPreferences settings = getSharedPreferences(
                "PREFS", 0
        );
        password = settings.getString("password", "");
        editText= (EditText) findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                if(text.equals(password)){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(enterpassword.this,"Wrong password entered!",Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), createpassword.class);
                startActivity(intent);
                finish();
            }
        }
        );


    }

}
