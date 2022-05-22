package com.dengemo.TekWulf.CANBusGuardian;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
        setFullScreen();
        findViewById(R.id.relativeLayout).setPadding(0, getStatusBarHeight(), 0, 0);

        mRepository = new UserRepository(getApplication());

        TextView textUsername = (TextView) findViewById(R.id.editTextUsername);
        TextView textPassword = (TextView) findViewById(R.id.editTextPassword);

        TextView textForgetPassword = (TextView) findViewById(R.id.textViewForgetPassword);

        MaterialButton loginBtn = (MaterialButton) findViewById(R.id.buttonLogin);
        MaterialButton newUserBtn = (MaterialButton) findViewById(R.id.buttonSignUp);

        //默认测试账号
        User user = new User();
        user.username = "Admin";
        user.password = "Admin123456";
        mRepository.insert(user);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mRepository.queryUser(textUsername.getText().toString(), textPassword.getText().toString()) != null) {
                    //Login successfully
                    Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainPageActivity.class);
                    startActivity(intent);
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

        textForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你再仔细想想！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取状态栏高度
    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    //调整页面为全屏
    private void setFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );
    }
}