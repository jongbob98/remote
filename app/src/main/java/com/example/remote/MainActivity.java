package com.example.remote;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private Socket socket; // Socket object to handle TCP connection
    private PrintWriter output; // PrintWriter object to send data to Raspberry Pi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the button from the layout and set a click listener
        Button buttonSend1 = findViewById(R.id.button5);
        Button buttonSend2 = findViewById(R.id.button2);
        Button buttonSend3 = findViewById(R.id.button4);
        Button buttonSend4 = findViewById(R.id.button3);
        Button buttonSend5 = findViewById(R.id.button8);
        Button buttonSend6 = findViewById(R.id.button6);
        Button buttonSend7 = findViewById(R.id.bttonu7);

        buttonSend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSerialValue("Value1");
            }
        });

        buttonSend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSerialValue("Value2");
            }
        });

        buttonSend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSerialValue("Value3");
            }
        });

        buttonSend4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSerialValue("Value4");
            }
        });

        buttonSend5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSerialValue("Value4");
            }
        });

        buttonSend6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSerialValue("Value4");
            }
        });

        buttonSend7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSerialValue("Value4");
            }
        });

        // Start the TCP connection to the Raspberry Pi with the given IP and port
        new ConnectTask().execute("192.168.0.100", "12345");
    }

    // AsyncTask to handle the TCP connection in the background
    private class ConnectTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                // Connect to the Raspberry Pi using the IP address and port
                socket = new Socket(params[0], Integer.parseInt(params[1]));

                // Initialize the output stream to send data to Raspberry Pi
                OutputStream outputStream = socket.getOutputStream();
                output = new PrintWriter(outputStream, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    // Method to send serial value to Raspberry Pi
    private void sendSerialValue(String value) {
        if (output != null) {
            output.println(value);
        }
    }
}