package com.dash.a1511n.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dash.a1511n.R;
import com.dash.a1511n.model.bean.LoginBean;
import com.dash.a1511n.presenter.LoginPresenter;
import com.dash.a1511n.util.ApiUtil;
import com.dash.a1511n.util.CommonUtils;
import com.dash.a1511n.view.Iview.LoginActivityInter;

public class LoginActivity extends AppCompatActivity implements LoginActivityInter {

    private EditText edit_phone;
    private EditText edit_pwd;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_phone = findViewById(R.id.edit_phone);
        edit_pwd = findViewById(R.id.edit_pwd);

        //登录的presenter
        loginPresenter = new LoginPresenter(this);

    }

    /**
     * 登录的点击事件
     * @param view
     */
    public void login(View view) {
        String phone = edit_phone.getText().toString();
        String pwd = edit_pwd.getText().toString();

        loginPresenter.getLogin(ApiUtil.LOGIN_URL,phone,pwd);
    }

    @Override
    public void getLoginSuccess(LoginBean loginBean) {

        Toast.makeText(this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();

        if ("0".equals(loginBean.getCode())) {//登录成功
            //登录成功之后需要做:.....保存状态true...
            CommonUtils.putBoolean("isLogin",true);
            CommonUtils.saveString("uid", String.valueOf(loginBean.getData().getUid()));
            CommonUtils.saveString("name",loginBean.getData().getUsername());
            CommonUtils.saveString("iconUrl",loginBean.getData().getIcon());

            //结束当前界面
            finish();
        }


    }
}
