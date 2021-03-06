package com.pllug.course.behen.fragments.signin;

import android.text.TextUtils;

import com.pllug.course.behen.MainActivity;
import com.pllug.course.behen.ProfileFragmentActivity;

public class SignInPresenter implements SignInContract.ISignInPresenter {

    private SignInContract.ISignInView view;
    private SignInFragment fragment;

    public SignInPresenter(SignInContract.ISignInView view, SignInFragment fragment) {
        this.view = view;
        this.fragment = fragment;
    }

    @Override
    public void signInClick() {
        validateInput();
    }

    private void validateInput() {
        view.hideLoginError();
        view.hidePasswordError();

        String login = view.getLoginText();
        String password = view.getPasswordText();

        if (TextUtils.isEmpty(login)) {
            view.showLoginError();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            view.showPasswordError();
            return;
        }

        ((ProfileFragmentActivity) fragment.getActivity()).signIn(login, password);
    }

    @Override
    public void showSignUp() {
        ((ProfileFragmentActivity) fragment.getActivity()).showSignUp();
    }

    @Override
    public void showForgotPassword() {
        ((ProfileFragmentActivity) fragment.getActivity()).showForgotPassword();
    }
}