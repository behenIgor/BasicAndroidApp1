package com.pllug.course.behen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.pllug.course.behen.fragments.ForgotPasswordFragment;
import com.pllug.course.behen.fragments.signin.SignInFragment;
import com.pllug.course.behen.fragments.SignUpFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectToAuthServer();
        showSignIn();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }



    public void showSignUp() {
        replaceFragment(new SignUpFragment());
    }

    public void showSignIn() {
        replaceFragment(new SignInFragment());
    }

    public void showForgotPassword() {
        replaceFragment(new ForgotPasswordFragment());
    }


    public void connectToAuthServer() {
        Toast.makeText(this, "Connect to Auth server!", Toast.LENGTH_SHORT).show();
    }


    public void signIn(String login, String password) {
        Toast.makeText(this, "You are logged in!", Toast.LENGTH_SHORT).show();
        goToProfileScreen();
    }

    public void signUp(String login, String email, String password) {
        Toast.makeText(this, "You are Signed up!", Toast.LENGTH_SHORT).show();
        goToProfileScreen();
    }


    public void sendRecoverCode(String email) {
        Toast.makeText(this, "You will receive recover code!", Toast.LENGTH_SHORT).show();
    }


    private void goToProfileScreen() {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        startActivity(intent);
    }




    public void replaceFragment(Fragment fragment, int containerId) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }



}

