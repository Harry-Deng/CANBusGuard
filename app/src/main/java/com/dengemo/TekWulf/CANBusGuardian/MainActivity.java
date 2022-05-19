package com.dengemo.TekWulf.CANBusGuardian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dengemo.TekWulf.CANBusGuardian.room.UserRepository;
import com.dengemo.TekWulf.CANBusGuardian.room.entity.User;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    UserRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRepository = new UserRepository(getApplication());

        TextView username = (TextView) findViewById(R.id.editTextUsername);
        TextView password = (TextView) findViewById(R.id.editTextPassword);

        MaterialButton loginBtn = (MaterialButton) findViewById(R.id.buttonLogin);
        MaterialButton newUserBtn = (MaterialButton) findViewById(R.id.buttonSignUp);

        User user = new User();
        user.username = "hello";
        user.password = "wor";
        mRepository.insert(user);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mRepository.queryUser(username.getText().toString(), password.getText().toString()) != null) {
                    //Login successfully
                    Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                } else {
                    //Login failed
                    Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                }

            }

        });

        newUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}