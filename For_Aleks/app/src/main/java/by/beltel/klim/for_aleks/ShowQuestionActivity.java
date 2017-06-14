package by.beltel.klim.for_aleks;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowQuestionActivity extends AppCompatActivity {

    private static final String Key_Index_Number_Question = "numberQuestion";
    private static final String Key_Index_Array_Answer = "arrayAnswer";
    private TextView fieldNumberQuestionShow;
    private TextView fieldQuestionShow;
    private EditText fieldAnswer_;
    private Button buttonNext_;
    private Button button_show_res;
    private  boolean b = false;
    private int numberQuestion;
    private static ArrayList<String> arrayAnswer;
    TextView field1;
    TextView field2;

    public static ArrayList<String> getArrayAnswer(){
        return arrayAnswer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_question);
        if (savedInstanceState != null){
            numberQuestion = savedInstanceState.getInt(Key_Index_Number_Question);
            arrayAnswer = savedInstanceState.getStringArrayList(Key_Index_Array_Answer);
            if (numberQuestion > 0 && numberQuestion <= 15){
                startShowQuestionActivityAfterSave(numberQuestion);
            }
        } else {
            numberQuestion = 1;
            startShowQuestionActivity();
        }

        codeForButtonShowRes();
        codeForButtonNext();
    }


    public void startShowQuestionActivity(){
        arrayAnswer = new ArrayList<>();
        fieldNumberQuestionShow = (TextView) findViewById(R.id.fieldNumberQuestion);
        fieldNumberQuestionShow.setText("" + numberQuestion);
        fieldQuestionShow = (TextView) findViewById(R.id.fieldQuestion);
        fieldQuestionShow.setText(MainActivity.getList_question().get(0));
        field1 = (TextView) findViewById(R.id.textview1);
        field2 = (TextView) findViewById(R.id.textview2);
        field1.setText(MainActivity.getListVerbForQuestion().get(0).toString());
        field2.setText(MainActivity.getArrayForCompare()[0].toString());
        fieldAnswer_ = (EditText) findViewById(R.id.fieldAnswer);
    }

    public void startShowQuestionActivityAfterSave(int numberQuestion){
        fieldNumberQuestionShow = (TextView) findViewById(R.id.fieldNumberQuestion);
        fieldNumberQuestionShow.setText("" + numberQuestion);
        fieldQuestionShow = (TextView) findViewById(R.id.fieldQuestion);
        fieldQuestionShow.setText(MainActivity.getList_question().get(numberQuestion -1));
        field1 = (TextView) findViewById(R.id.textview1);
        field2 = (TextView) findViewById(R.id.textview2);
        field1.setText(MainActivity.getListVerbForQuestion().get(numberQuestion - 1).toString());
        field2.setText(MainActivity.getArrayForCompare()[numberQuestion - 1].toString());
        fieldAnswer_ = (EditText) findViewById(R.id.fieldAnswer);
    }

    public void codeForButtonShowRes(){
        button_show_res = (Button) findViewById(R.id.button_show_results);
        button_show_res.setEnabled(false);
        button_show_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b) {
                    Intent ii = new Intent(ShowQuestionActivity.this, ShowResultActivity.class);
                    startActivity(ii);
                }
            }
        });
    }

    public void codeForButtonNext(){

        buttonNext_ = (Button)findViewById(R.id.buttonNext);
        buttonNext_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fieldAnswer_.getText().toString().length() > 0) {
                    if (numberQuestion <= 14) {
                        fieldAnswer_.setHint("Enter your answer and push button NEXT");
                        arrayAnswer.add(fieldAnswer_.getText().toString());
                        numberQuestion = numberQuestion + 1;
                        fieldNumberQuestionShow.setText(new String("" + numberQuestion));
                        fieldQuestionShow.setText(MainActivity.getList_question().get(numberQuestion - 1));
                        field1.setText(MainActivity.getListVerbForQuestion().get(numberQuestion - 1).toString());
                        field2.setText(MainActivity.getArrayForCompare()[numberQuestion - 1].toString());
                        fieldAnswer_.setText("");
                    } else if (numberQuestion == 15) {
                        fieldAnswer_.setHint("Enter your answer and push button NEXT");
                        arrayAnswer.add(fieldAnswer_.getText().toString());
                        fieldAnswer_.setText("");
                        fieldQuestionShow.setText("");
                        fieldNumberQuestionShow.setText("");
                        b = true;
                        button_show_res.setEnabled(true);
                        button_show_res.setBackgroundColor(Color.parseColor("#009900"));
                        buttonNext_.setEnabled(false);
                        buttonNext_.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        fieldAnswer_.setEnabled(false);
                    }
                } else {
                    fieldAnswer_.setHint("Repeat input answer and push button NEXT");
                }

            }
        });
    }



    @Override
    public void onSaveInstanceState(Bundle saved){
        super.onSaveInstanceState(saved);
        saved.putInt(Key_Index_Number_Question, numberQuestion);
        saved.putStringArrayList(Key_Index_Array_Answer, arrayAnswer);
    }

}
