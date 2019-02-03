package com.pllug.course.behen.fragments.signin;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pllug.course.behen.R;

public class SignInView implements SignInContract.ISignInView{
    private Button signInBtn;
    private TextView signUpTv, forgotPasswordTv, aboutTv;
    private EditText loginEt, passwordEt;

    private SignInContract.ISignInPresenter presenter;

    public SignInView() {

    }

    public void setPresenter(SignInContract.ISignInPresenter presenter) {
        this.presenter = presenter;
    }

    public void init(View root) {
        signInBtn = (Button) root.findViewById(R.id.signin_btn);
        signUpTv = (TextView) root.findViewById(R.id.signup_txt);
        forgotPasswordTv = (TextView) root.findViewById(R.id.forgot_password_txt);
        loginEt = (EditText) root.findViewById(R.id.login_et);
        passwordEt = (EditText) root.findViewById(R.id.password_et);
        initListeners();
    }

    private void initListeners() {
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signInClick();
            }
        });

        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showSignUp();
            }
        });

        forgotPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showForgotPassword();
            }
        });

    }

    @Override
    public void hideLoginError() {
        loginEt.setError(null);
    }

    @Override
    public void hidePasswordError() {
        passwordEt.setError(null);
    }

    @Override
    public void showLoginError() {
        loginEt.setError("Please enter login!");
    }

    @Override
    public void showPasswordError() {
        passwordEt.setError("Please enter password!");
    }

    @Override
    public String getLoginText() {
        return loginEt.getText().toString();
    }

    @Override
    public String getPasswordText() {
        return passwordEt.getText().toString();
    }
}