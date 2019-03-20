package com.nitesh.dubey.fitnessbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edttxt1,edttxt2;
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edttxt1 = (EditText) findViewById(R.id.edttxt1);
        edttxt2 = (EditText) findViewById(R.id.edttxt2);
        txt1 = (TextView) findViewById(R.id.txt1);


    }

    public void goToMainScreen (View view) {
        String username = edttxt1.getText().toString();
        String password = edttxt2.getText().toString();

        if((username.matches("fitnessbook")) && (password.matches("12345"))){
            Intent mainActivity = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(mainActivity);
        }
        else {
            Toast.makeText(LoginActivity.this, "Please Enter Valid Information",Toast.LENGTH_LONG).show();
            return;
        }
    }

}
