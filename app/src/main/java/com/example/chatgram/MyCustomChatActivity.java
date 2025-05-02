package com.example.chatgram;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cometchat.chat.constants.CometChatConstants;
import com.cometchat.chat.core.CometChat;
import com.cometchat.chat.exceptions.CometChatException;
import com.cometchat.chat.models.TextMessage;

public class MyCustomChatActivity extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageEditText = findViewById(R.id.messageEditText);
        sendBtn = findViewById(R.id.sendBtn);

        sendBtn.setOnClickListener(view -> {
            String messageText = messageEditText.getText().toString().trim();
            if (!messageText.isEmpty()) {
                sendMessage(messageText);
            }
        });
    }

    private void sendMessage(String messageText) {
        String recipientUid = "receiverUID";
        TextMessage textMessage = new TextMessage(recipientUid, messageText, CometChatConstants.RECEIVER_TYPE_USER);
        CometChat.sendMessage(textMessage, new CometChat.CallbackListener<TextMessage>() {
            @Override
            public void onSuccess(TextMessage message) {
                Toast.makeText(MyCustomChatActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(CometChatException e) {
                Toast.makeText(MyCustomChatActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
