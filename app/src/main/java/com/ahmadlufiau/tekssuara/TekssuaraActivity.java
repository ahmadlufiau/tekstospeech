package com.ahmadlufiau.tekssuara;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

public class TekssuaraActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    StreamPlayer streamPlayer;
    String text_to_speech;

    private TextToSpeech initTextToSpeechService(){
        TextToSpeech service = new TextToSpeech();
        String username = "bc5044c4-f927-4cf2-a0f4-2f6588e64c05";
        String password = "SVogAT2DnStX";
        service.setUsernameAndPassword(username, password);
        return service;
    }

    private class WatsonTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute(){
            text_to_speech = editText.getText().toString();
        }

        @Override
        protected String doInBackground(String... textToSpeak) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText("running the Watson thread");
                }
            });

            TextToSpeech textToSpeech = initTextToSpeechService();
            streamPlayer = new StreamPlayer();
            streamPlayer.playStream(textToSpeech.synthesize(text_to_speech, Voice.EN_ALLISON).execute());

            return "text to speech done";
        }

        @Override
        protected void onPostExecute(String result) {
            textView.setText("TTS status: " + result);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tekssuara);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("the text to speech: " + editText.getText());
                textView.setText("TTS: " + editText.getText());

                WatsonTask task = new WatsonTask();
                task.execute(new String[]{});


            }
        });
    }
}
