package com.example.chatgram;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.AppSettings;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        String appID = "2746564017229948"; // Replace with your actual App ID
        String region = "IN"; // CometChat expects region to be in lowercase like "us", "eu", "in"
        String authKey = "56389b25884a751706c8e08766e304bc628653ba"; // Optional if you plan to login here

        AppSettings appSettings = new AppSettings.AppSettingsBuilder()
                .subscribePresenceForAllUsers()
                .setRegion(region)
                .autoEstablishSocketConnection(true)
                .build();

        CometChat.init(this, appID, appSettings, new CometChat.CallbackListener<String>() {
            @Override
            public void onSuccess(String successMessage) {
                Log.d(TAG, "Initialization completed successfully");
            }

            @Override
            public void onError(CometChatException e) {
                Log.d(TAG, "Initialization failed with exception: " + e.getMessage());
            }
        });

    }
}
