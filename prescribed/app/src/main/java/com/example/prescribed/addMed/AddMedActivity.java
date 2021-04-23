package com.example.prescribed.addMed;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.prescribed.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddMedActivity extends AppCompatActivity {

    private EditText editText;
    private EditText editText2;
    private List<String> answers;
    private int questionCount = 0;
    private Button nextButton;
    private ImageView talkButton;
    private Spinner freq_spinner;

    private SpeechRecognizer speechRecognizer;
    public static final Integer RecordAudioRequestCode = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_med_name);

        editText = (EditText) findViewById(R.id.addMedText_name);
        nextButton = (Button) findViewById(R.id.addMed_next_name);
        talkButton = (ImageView) findViewById(R.id.talkButton_name);

        answers = new ArrayList<>(4);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {}

            @Override
            public void onBeginningOfSpeech() {
                editText.setText("");
                editText.setHint("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {}

            @Override
            public void onBufferReceived(byte[] bytes) {}

            @Override
            public void onEndOfSpeech() {}

            @Override
            public void onError(int i) {}

            @Override
            public void onResults(Bundle bundle) {
                talkButton.setImageResource(R.drawable.green_mic_better);
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                editText.setText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {}

            @Override
            public void onEvent(int i, Bundle bundle) {}
        });


        talkButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    speechRecognizer.stopListening();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    talkButton.setImageResource(R.drawable.green_mic);
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }

    public void nextQuestion(View view){

        switch(questionCount)
        {
            case 0:
                if(answers.size() > questionCount){
                    answers.set(questionCount, editText.getText().toString());
                }
                else{
                    answers.add(questionCount, editText.getText().toString());
                }

                this.setContentView(R.layout.enter_med_freq);

                freq_spinner = (Spinner) findViewById(R.id.freq_spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.freq_spinner_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                freq_spinner.setAdapter(adapter);


                editText = (EditText) findViewById(R.id.addMedText_freq);

                questionCount++;

                if(answers.size() > questionCount && answers.get(questionCount) != null){
                    String[] temp = answers.get(questionCount).split("~");

                    editText.setText(temp[0]);

                    //Sets spinner to the previous selection
                    String[] s = getResources().getStringArray(R.array.freq_spinner_array);
                    for(int i=0; i<s.length ;i++){
                        if(temp[1].equals(s[i])){
                            freq_spinner.setSelection(i);
                            break;
                        }
                    }
                }
                break;
            case 1:
                if(answers.size() > questionCount){
                    answers.set(questionCount, editText.getText().toString() + "~" + freq_spinner.getSelectedItem().toString());
                }
                else{
                    answers.add(questionCount, editText.getText().toString() + "~" + freq_spinner.getSelectedItem().toString());
                }

                this.setContentView(R.layout.enter_med_start);

                editText = (EditText) findViewById(R.id.editTextTime);
                editText2 = (EditText) findViewById(R.id.editTextDate);

                questionCount++;

                if(answers.size() > questionCount && answers.get(questionCount) != null){
                    String[] temp = answers.get(questionCount).split("~");

                    editText.setText(temp[0]);
                    editText2.setText(temp[1]);
                }

                break;
            case 2:

                if(answers.size() > questionCount){
                    answers.set(questionCount, editText.getText().toString() + "~" + editText2.getText().toString());
                }
                else{
                    answers.add(questionCount, editText.getText().toString() + "~" + editText2.getText().toString());
                }

                this.setContentView(R.layout.enter_med_notes);

                editText = (EditText) findViewById(R.id.editText_notes);

                questionCount++;
                break;
        }

    }

    public void previousQuestion(View view){
        switch(questionCount)
        {
            case 1:
                questionCount--;
                setContentView(R.layout.enter_med_name);

                editText = (EditText) findViewById(R.id.addMedText_name);
                editText.setText(answers.get(questionCount));
                break;
            case 2:
                questionCount--;
                setContentView(R.layout.enter_med_freq);
                String[] temp = answers.get(questionCount).split("~");

                editText = (EditText) findViewById(R.id.addMedText_freq);
                editText.setText(temp[0]);

                freq_spinner = (Spinner) findViewById(R.id.freq_spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.freq_spinner_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                freq_spinner.setAdapter(adapter);

                //Sets spinner to the previous selection
                String[] s = getResources().getStringArray(R.array.freq_spinner_array);
                for(int i=0; i<s.length ;i++){
                    if(temp[1].equals(s[i])){
                        freq_spinner.setSelection(i);
                        break;
                    }
                }
                break;
            case 3:
                questionCount--;
                setContentView(R.layout.enter_med_start);

                editText = (EditText) findViewById(R.id.editTextTime);
                editText2 = (EditText) findViewById(R.id.editTextDate);

                String[] temp2 = answers.get(questionCount).split("~");

                editText.setText(temp2[0]);
                editText2.setText(temp2[1]);

                break;
        }
    }

    public void doneClicked(View view){

    }

    public void voiceInputClicked(View view){

    }
}
