package com.pllug.course.behen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.pllug.course.behen.R;



public class ForgotPasswordFragment extends Fragment {

    private View root;
    private Button sendRecoverCodeBtn;
    private EditText emailEt;
    private TextView backToSignInTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        initView();
        initListeners();

        return root;
    }

    private void initView() {
        sendRecoverCodeBtn = (Button) root.findViewById(R.id.send_recover_code_btn);
        emailEt = (EditText) root.findViewById(R.id.email_et);
        backToSignInTv = (TextView) root.findViewById(R.id.back_signin_txt);
    }

    private void initListeners() {
        sendRecoverCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });

        backToSignInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).showSignIn();
            }
        });
    }

    private void validateData() {
        emailEt.setError(null);
        String email = emailEt.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Please enter valid email!", Toast.LENGTH_SHORT).show();
            emailEt.setError("Please enter valid email!");
            return;
        }

        ((MainActivity) getActivity()).sendRecoverCode(email);
    }
}
