package by.beltel.klim.for_aleks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class ShowResultActivity extends AppCompatActivity {
    private static final String Key_ListRigthAnswer = "listRigthAnswer";
    private static final String Key_ListRWrongAnswer = "listWrongAnswer";
    private static final String Key_TextForNumberResult = "textForNumberResult";
    private static final String Key_ReviewForwardRigth = "reviewForwardRigth";
    private static final String Key_ReviewForwardWrong = "reviewForwardWrong";
    private static final String Key_xxRigth = "xxRigth";
    private static final String Key_xxWrong = "xxWrong";
    private TextView number_result;
    private TextView rigth_result;
    private TextView wrong_result;
    private Button reviewRigth;
    private Button reviewWrong;
    private String textForNumberResult;
    private ArrayList<String> listRigthAnswer;
    private ArrayList<String> listWrongAnswer;
    private byte reviewForwardRigth = 0;
    private byte reviewForwardWrong = 0;
    private int xxRigth = 0;
    private int xxWrong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        if (savedInstanceState != null){
            listRigthAnswer = savedInstanceState.getStringArrayList(Key_ListRigthAnswer);
            listWrongAnswer = savedInstanceState.getStringArrayList(Key_ListRWrongAnswer);
            textForNumberResult = savedInstanceState.getString(Key_TextForNumberResult);
            reviewForwardRigth = savedInstanceState.getByte(Key_ReviewForwardRigth);
            reviewForwardWrong = savedInstanceState.getByte(Key_ReviewForwardWrong);
            xxRigth = savedInstanceState.getInt(Key_xxRigth);
            xxWrong = savedInstanceState.getInt(Key_xxWrong);
            startShowResultActivityAfterSaved();
        } else {
            startShowResultActivity();
        }
        reviewRigth = (Button)findViewById(R.id.button_review);
        reviewWrong = (Button)findViewById(R.id.button_review1);
        inspectList();
        MainActivity.destroyFields();
    }


    public void startShowResultActivity(){
        number_result = (TextView)findViewById(R.id.numberResult);
        textForNumberResult = giveNumberResult(ShowQuestionActivity.getArrayAnswer(), MainActivity.getArrayForCompare());
        number_result.setText(textForNumberResult);
        rigth_result = (TextView)findViewById(R.id.rigthAnswer);
        wrong_result = (TextView)findViewById(R.id.wrongAnswer);
        listRigthAnswer = giveRigthQuestion(ShowQuestionActivity.getArrayAnswer(), MainActivity.getArrayForCompare());
        listWrongAnswer = giveWronghQuestion(ShowQuestionActivity.getArrayAnswer(), MainActivity.getArrayForCompare());


    }

    public void startShowResultActivityAfterSaved(){
        number_result = (TextView)findViewById(R.id.numberResult);
        number_result.setText(textForNumberResult);
        rigth_result = (TextView)findViewById(R.id.rigthAnswer);
        wrong_result = (TextView)findViewById(R.id.wrongAnswer);

    }


    public void inspectList(){
        if (listRigthAnswer.size() == 0){
            rigth_result.setText("");
            reviewRigth.setEnabled(false);
            wrong_result.setText(listWrongAnswer.get(0));
            setButtonReviewWrong(reviewWrong);
        } else if (listWrongAnswer.size() == 0){
            wrong_result.setText("");
            reviewWrong.setEnabled(false);
            rigth_result.setText(listRigthAnswer.get(0));
            setButtonReviewRigth(reviewRigth);
        } else if(listRigthAnswer.size() == 1){
            rigth_result.setText(listRigthAnswer.get(0));
            reviewRigth.setEnabled(false);
            wrong_result.setText(listWrongAnswer.get(0));
            setButtonReviewWrong(reviewWrong);
        } else if(listWrongAnswer.size() == 1){
            rigth_result.setText(listRigthAnswer.get(0));
            reviewWrong.setEnabled(false);
            wrong_result.setText(listWrongAnswer.get(0));
            setButtonReviewRigth(reviewRigth);
        } else if(listRigthAnswer.size() > 1 || listWrongAnswer.size() > 1){
            rigth_result.setText(listRigthAnswer.get(0));
            wrong_result.setText(listWrongAnswer.get(0));
            setButtonReviewRigth(reviewRigth);
            setButtonReviewWrong(reviewWrong);
        }

    }


    public void setButtonReviewRigth(Button reviewRigth){
        reviewRigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listRigthAnswer.size() >= 3) {


                    if (reviewForwardRigth == 0) {
                        xxRigth = xxRigth + 1;
                        rigth_result.setText(listRigthAnswer.get(xxRigth));
                        if (xxRigth == listRigthAnswer.size() - 1) {
                            xxRigth = xxRigth - 1;
                            reviewForwardRigth = 1;
                        }
                    } else {
                        rigth_result.setText(listRigthAnswer.get(xxRigth));
                        xxRigth = xxRigth - 1;
                        if (xxRigth == 0) {
                            xxRigth = -1;
                            reviewForwardRigth = 0;
                        }
                    }

                } else {
                    if (reviewForwardRigth == 0) {
                        xxRigth = xxRigth + 1;
                        rigth_result.setText(listRigthAnswer.get(xxRigth));
                        if (xxRigth == listRigthAnswer.size() - 1) {
                            xxRigth = xxRigth - 1;
                            reviewForwardRigth = 1;
                        }
                    } else {
                        rigth_result.setText(listRigthAnswer.get(xxRigth));
                        reviewForwardRigth = 0;
                    }

                }

            }
        });
    }




    public void setButtonReviewWrong(Button reviewWrong){
        reviewWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listWrongAnswer.size() >= 3) {
                    if (reviewForwardWrong == 0) {
                        xxWrong = xxWrong + 1;
                        wrong_result.setText(listWrongAnswer.get(xxWrong));
                        if (xxWrong == listWrongAnswer.size() - 1) {
                            xxWrong = xxWrong - 1;
                            reviewForwardWrong = 1;
                        }
                    } else {
                        wrong_result.setText(listWrongAnswer.get(xxWrong));
                        xxWrong = xxWrong - 1;
                        if (xxWrong == 0) {
                            xxWrong = -1;
                            reviewForwardWrong = 0;
                        }
                    }
                }else {
                    if (reviewForwardWrong == 0) {
                        xxWrong = xxWrong + 1;
                        wrong_result.setText(listWrongAnswer.get(xxWrong));
                        if (xxWrong == listWrongAnswer.size() - 1) {
                            xxWrong = xxWrong - 1;
                            reviewForwardWrong = 1;
                        }
                    } else {
                        wrong_result.setText(listWrongAnswer.get(xxWrong));
                        reviewForwardWrong = 0;
                    }
                }

            }
        });
    }




    public String giveNumberResult(ArrayList<String> arrayAnswer, String[] arrayForCompare){
        int x = 0;
        for (int i = 0; i < arrayAnswer.size(); i++){
            if (arrayAnswer.get(i).equalsIgnoreCase(arrayForCompare[i])){
                x = x + 1;
            }
        }
        String s = "You have " + x + " rigth answer!"
                + "\n" + "You have " + (arrayAnswer.size() - x) + " wrong answer!" ;
        return s;
    }


    public ArrayList<String>  giveRigthQuestion(ArrayList<String> arrayAnswer, String[] arrayForCompare){
        ArrayList<String> ss = new ArrayList<>();
        for (int i = 0; i < arrayAnswer.size(); i++){
            if (arrayAnswer.get(i).equalsIgnoreCase(arrayForCompare[i])){
                ss.add((i + 1) + "\n" + new String(MainActivity.getList_question().get(i))
                        + "\n"
                        +" rigth answer " + arrayForCompare[i] + "\n"
                        + " your answer " + arrayAnswer.get(i));
            }
        }
        return ss;
    }

    public ArrayList<String> giveWronghQuestion(ArrayList<String> arrayAnswer, String[] arrayForCompare){
        ArrayList<String> ss = new ArrayList<>();
        for (int i = 0; i < arrayAnswer.size(); i++){
            if (!arrayAnswer.get(i).equalsIgnoreCase(arrayForCompare[i])){
                ss.add((i + 1) + "\n" + new String(MainActivity.getList_question().get(i))
                        + "\n"
                        +" rigth answer " + arrayForCompare[i] + "\n"
                        + " your answer " + arrayAnswer.get(i));
            }
        }
        return ss;
    }

    @Override
    public void onSaveInstanceState(Bundle saved){
        super.onSaveInstanceState(saved);
        saved.putStringArrayList(Key_ListRigthAnswer, listRigthAnswer);
        saved.putStringArrayList(Key_ListRWrongAnswer, listWrongAnswer);
        saved.putString(Key_TextForNumberResult, textForNumberResult);
        saved.putByte(Key_ReviewForwardRigth, reviewForwardRigth);
        saved.putByte(Key_ReviewForwardWrong, reviewForwardWrong);
        saved.putInt(Key_xxRigth, xxRigth);
        saved.putInt(Key_xxWrong, xxWrong);
    }
}
