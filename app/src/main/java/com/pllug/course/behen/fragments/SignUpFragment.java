package com.pllug.course.behen.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pllug.course.behen.MainActivity;
import com.pllug.course.behen.ProfileFragmentActivity;
import com.pllug.course.behen.R;



public class SignUpFragment extends Fragment {

    private View root;
    private Button signUpBtn;
    private TextView signInTv;
    private EditText loginEt, emailEt, passwordEt, confirmPasswordEt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_signup, container, false);

        initView();
        initListeners();

        return root;
    }

    private void initView() {
        signUpBtn = (Button) root.findViewById(R.id.signup_btn);
        signInTv = (TextView) root.findViewById(R.id.signin_txt);
        loginEt = (EditText) root.findViewById(R.id.login_et);
        emailEt = (EditText) root.findViewById(R.id.email_et);
        passwordEt = (EditText) root.findViewById(R.id.password_et);
        confirmPasswordEt = (EditText) root.findViewById(R.id.confirm_password_et);
    }

    private void initListeners() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInput();
            }
        });

        signInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ProfileFragmentActivity) getActivity()).showSignIn();
            }
        });
    }

    private void validateInput() {
        loginEt.setError(null);
        emailEt.setError(null);
        passwordEt.setError(null);
        confirmPasswordEt.setError(null);

        String login = loginEt.getText().toString();
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        String confirmPassword = confirmPasswordEt.getText().toString();

        if (TextUtils.isEmpty(login)) {
            Toast.makeText(getActivity(), "Please enter login!", Toast.LENGTH_SHORT).show();
            loginEt.setError("Please enter login!");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Please enter email!", Toast.LENGTH_SHORT).show();
            emailEt.setError("Please enter email!");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Please enter password!", Toast.LENGTH_SHORT).show();
            passwordEt.setError("Please enter password!");
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(getActivity(), "Please enter confirm password!", Toast.LENGTH_SHORT).show();
            confirmPasswordEt.setError( "Please enter confirm password!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getActivity(), "Passwords aren't equal!", Toast.LENGTH_SHORT).show();
            return;
        }

//        boolean hasBigCharacter = false;
//        for (int i = 0; i < password.length(); i++) {
//            char c = password.charAt(i);
//            if ('A' <= c && c <= 'Z') {
//                hasBigCharacter = true;
//            }
//        }

        ((ProfileFragmentActivity)getActivity()).signUp(login, email, password);
    }
}
