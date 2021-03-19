package com.example.socialmediasparks;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user = auth.getCurrentUser();
    private TextView name, email;
//    private ImageView profpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
//        profpic = (ImageView) findViewById(R.id.profpic);

        if(user != null){
//            String photoUrl = "https://graph.facebook.com/" + facebookUserId + "/picture?type=medium"
//            Picasso.with(this).load(photoUrl).into(userImage);
            name.setText(user.getDisplayName());
            email.setText(user.getEmail());
        }

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                LoginManager.getInstance().logOut();
                openLogin();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(user == null){
            openLogin();
        }
    }

    private void openLogin() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}