package com.pllug.course.behen;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pllug.course.behen.fragments.SignUpFragment;
import com.pllug.course.behen.repositorys.albums_repository.AlbumsFragment;

import java.io.File;

public class UserActivity extends AppCompatActivity  implements View.OnClickListener{

    private static final int REQUEST_CODE_IMAGE_CAMERA = 1;
    private static final int REQUEST_CODE_IMAGE_GALLERY = 2;
    private static final String TAG = UserActivity.class.getSimpleName();
    private ImageView avatarIv, callIv, chatIv, shareIv;

    private Button toApiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }


    private void initView() {
        avatarIv = findViewById(R.id.user_profile_iv);
        callIv = findViewById(R.id.action_call_iv);
        chatIv = findViewById(R.id.action_chat_iv);
        shareIv = findViewById(R.id.action_share_iv);

        toApiBtn = findViewById(R.id.toApi);

    }

    private void initListener() {
        avatarIv.setOnClickListener(this);
        callIv.setOnClickListener(this);
        chatIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_profile_iv:
                getNewAvatar();
                break;
            case R.id.action_call_iv:
                makeCall();
                break;
            case R.id.action_chat_iv:
                startChat();
                break;
            case R.id.action_share_iv:
                shareContact();
                break;

            case R.id.toApi:
Toast.makeText(this, "User Activity", Toast.LENGTH_LONG).show();
                break;

        }
    }




    private void getNewAvatar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Change avatar")
                .setMessage("Please select option to change avatar.")
                .setIcon(R.mipmap.ic_launcher_round)
                .setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getNewAvatarFromCamera();
                    }
                })
                .setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getNewAvatarFromGallery();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void getNewAvatarFromCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = Uri.parse("file:///sdcard/photo.jpg");
        takePicture.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(takePicture, REQUEST_CODE_IMAGE_CAMERA);
    }

    private void getNewAvatarFromGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, REQUEST_CODE_IMAGE_GALLERY);//one can be replaced with any action code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.d(TAG, "RESULT_OK");
            Uri selectedImage;
            switch (requestCode) {
                case REQUEST_CODE_IMAGE_CAMERA:
                    Log.d(TAG, "REQUEST_CODE_IMAGE_CAMERA");
                    File file = new File(Environment.getExternalStorageDirectory().getPath(), "photo.jpg");
                    Uri uri = Uri.fromFile(file);
//                    selectedImage = data.getData();
                    avatarIv.setImageURI(uri);
                    break;
                case REQUEST_CODE_IMAGE_GALLERY:
                    Log.d(TAG, "REQUEST_CODE_IMAGE_GALLERY");
                    selectedImage = data.getData();
                    avatarIv.setImageURI(selectedImage);
                    break;
            }
        } else {
            Log.d(TAG, "RESULT_NE OK");
        }
    }

    private void makeCall() {
        String phone = "+380671234567";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }

    private void startChat() {
        try {
            String phone = "+380671234567";
            String message = "Hello";

            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.putExtra("address", phone);
            sendIntent.putExtra("sms_body", message);
            sendIntent.setType("vnd.android-dir/mms-sms");
            startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(UserActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void shareContact() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
