package com.example.dell.big_wanandroid.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.uname)
    EditText uname;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.register)
    Button register;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.login, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.login:
                String names = uname.getText().toString();
                String pwds = pwd.getText().toString();
                Intent intent = new Intent();
                SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor edit = login.edit();
                edit.putString("uname",names);
                edit.commit();
                intent.putExtra("uname",names);
                intent.putExtra("pwds",pwds);

                setResult(200,intent);
                finish();
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void login(HashMap<String,String> map) {
        if (map!=null) {
            String name = map.get("name");
            String pwds = map.get("pwd");
            uname.setText(name);
            pwd.setText(pwds);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
