package com.example.chatgram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.cometchat.pro.uikit.ui_components.cometchat_conversations_with_messages.CometChatConversationsWithMessagesActivity;

import com.cometchat.pro.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText uidEditText;
    private Button loginBtn, chatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        uidEditText = findViewById(R.id.uidEditText);
        loginBtn = findViewById(R.id.loginBtn);
        chatBtn = findViewById(R.id.chatBtn);

        // Initialize CometChat SDK
        CometChat.init(this, "2746564017229948", "in", new CometChat.CallbackListener<Void>() {
            @Override
            public void onSuccess(Void success) {
                Toast.makeText(MainActivity.this, "CometChat Initialized", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(CometChatException error) {
                Toast.makeText(MainActivity.this, "Initialization Failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        // Login button click listener
        loginBtn.setOnClickListener(view -> {
            String UID = uidEditText.getText().toString().trim();
            if (!UID.isEmpty()) {
                String authKey = "56389b25884a751706c8e08766e304bc628653ba"; // From CometChat Dashboard
                loginUser(UID, authKey);
            } else {
                Toast.makeText(MainActivity.this, "Enter UID", Toast.LENGTH_SHORT).show();
            }
        });

        // Chat button click listener (shown after login)
        chatBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CometChatConversationsWithMessagesActivity.class);
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
