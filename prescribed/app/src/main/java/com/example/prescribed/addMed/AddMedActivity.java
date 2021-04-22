package com.example.prescribed.addMed;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prescribed.R;

import java.util.ArrayList;
import java.util.List;

public class AddMedActivity extends AppCompatActivity {

    private EditText editText;
    private EditText editText2;
    private List<String> answers;
    private int questionCount = 0;
    private Button nextButton;
    private Spinner freq_spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_med_name);

        editText = (EditText) findViewById(R.id.addMedText_name);
        nextButton = (Button) findViewById(R.id.addMed_next_name);

        answers = new ArrayList<>(4);


        nextButton.setEnabled(true);
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
}
