package com.example.chatgram;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;



import com.cometchat.chat.core.CometChat;
import com.cometchat.chat.exceptions.CometChatException;
import com.cometchat.chat.models.User;

public class MainActivity extends AppCompatActivity {

    private EditText uidEditText;
    private Button chatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        uidEditText = findViewById(R.id.uidEditText);
        Button loginBtn = findViewById(R.id.loginBtn);
        chatBtn = findViewById(R.id.chatBtn);

        CometChat.init(this, "2746564017229948", "IN", new CometChat.CallbackListener<String>() {
            @Override
            public void onSuccess(String successMessage) {
                Log.d(TAG, "Initialization completed successfully");
            }

            @Override
            public void onError(CometChatException e) {
                Log.d(TAG, "Initialization failed with exception: " + e.getMessage());
            }
        });



        loginBtn.setOnClickListener(view -> {
            String UID = uidEditText.getText().toString().trim();
            if (!UID.isEmpty()) {
                String authKey = "56389b25884a751706c8e08766e304bc628653ba"; // From CometChat Dashboard
                loginUser(UID, authKey);
            } else {
                Toast.makeText(MainActivity.this, "Enter UID", Toast.LENGTH_SHORT).show();
            }
        });


        chatBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MyCustomChatActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser(String uid, String authKey) {
        CometChat.login(uid, authKey, new CometChat.CallbackListener<User>() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                chatBtn.setVisibility(View.VISIBLE); // Show chat button after successful login
            }

            @Override
            public void onError(CometChatException e) {
                Toast.makeText(MainActivity.this, "Login Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
