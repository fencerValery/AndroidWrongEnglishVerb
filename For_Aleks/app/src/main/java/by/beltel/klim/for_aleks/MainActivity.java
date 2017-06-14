package by.beltel.klim.for_aleks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static String[] arrayFromRes;
    private static ArrayList<VerbForAleks> list_VerbForAleks;
    private static ArrayList<StringBuilder> list_question;
    private static ArrayList<VerbForAleks> listVerbForQuestion;
    private static String[] arrayForCompare;
    private static  Intent i;
    private Button buttonStart;


    public static String[] getArrayFromRes(){return arrayFromRes;}

    public static ArrayList<StringBuilder> getList_question() {
        return list_question;
    }

    public static ArrayList<VerbForAleks> getListVerbForQuestion() {
        return listVerbForQuestion;
    }

    public static String[] getArrayForCompare() {
        return arrayForCompare;
    }

    public static void destroyFields(){
        arrayFromRes = null;
        list_VerbForAleks = null;
        list_question = null;
        listVerbForQuestion = null;
        arrayForCompare = null;
        i = null;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTest();

    }

   public void startTest(){
       buttonStart = (Button)findViewById(R.id.start);
       buttonStart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listVerbForQuestion = new ArrayList<>();
               arrayForCompare = new String[15];
               getArrayListVerb();
           }
       });
   }

    public void getArrayListVerb() {
        arrayFromRes = getResources().getStringArray(R.array.array_verb);
        list_VerbForAleks = new ArrayList<>();
        for (int i = 0; i < arrayFromRes.length; i++) {
            String[] ss = arrayFromRes[i].split(" ");
            VerbForAleks v = new VerbForAleks();
            v.setTranslation(ss[0]);
            v.setPast_practical(ss[1]);
            v.setPast_simple(ss[2]);
            v.setInfinitive_(ss[3]);
            list_VerbForAleks.add(v);
        }

        for (int i = 0; i < list_VerbForAleks.size(); i++){
            for (int y = 0; y < list_VerbForAleks.get(i).getTranslation().length(); y++){
                if (list_VerbForAleks.get(i).getTranslation().charAt(y) == '_'){
                    list_VerbForAleks.get(i).setTranslation(new String(list_VerbForAleks.get(i).getTranslation().replace('_', ' ')));
                }
            }
        }



        RandomNumbers randomNumbers = new RandomNumbers();
        newListRandomNumber(randomNumbers);

    }


    public void newListRandomNumber( RandomNumbers randomNumbers){
        for (int i = 0; i < randomNumbers.getArrayRandomNumbers().length; i++){
            listVerbForQuestion.add(list_VerbForAleks.get(randomNumbers.getArrayRandomNumbers()[i]));
        }

        list_question = new ArrayList<>();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeRussianTranslation(listVerbForQuestion.get(0).getPast_practical())));
        arrayForCompare[0] = listVerbForQuestion.get(0).getTranslation();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeFirstForm(listVerbForQuestion.get(1).getTranslation())));
        arrayForCompare[1] = listVerbForQuestion.get(1).getPast_practical();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeSecondForm(listVerbForQuestion.get(2).getTranslation())));
        arrayForCompare[2] = listVerbForQuestion.get(2).getPast_simple();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeFirdForm(listVerbForQuestion.get(3).getTranslation())));
        arrayForCompare[3] = listVerbForQuestion.get(3).getInfinitive_();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeRussianTranslation(listVerbForQuestion.get(4).getInfinitive_())));
        arrayForCompare[4] = listVerbForQuestion.get(4).getTranslation();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeFirstForm(listVerbForQuestion.get(5).getTranslation())));
        arrayForCompare[5] = listVerbForQuestion.get(5).getPast_practical();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeSecondForm(listVerbForQuestion.get(6).getTranslation())));
        arrayForCompare[6] = listVerbForQuestion.get(6).getPast_simple();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeFirdForm(listVerbForQuestion.get(7).getTranslation())));
        arrayForCompare[7] = listVerbForQuestion.get(7).getInfinitive_();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeRussianTranslation(listVerbForQuestion.get(8).getPast_simple())));
        arrayForCompare[8] = listVerbForQuestion.get(8).getTranslation();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeFirstForm(listVerbForQuestion.get(9).getTranslation())));
        arrayForCompare[9] = listVerbForQuestion.get(9).getPast_practical();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeSecondForm(listVerbForQuestion.get(10).getTranslation())));
        arrayForCompare[10] = listVerbForQuestion.get(10).getPast_simple();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeFirdForm(listVerbForQuestion.get(11).getTranslation())));
        arrayForCompare[11] = listVerbForQuestion.get(11).getInfinitive_();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeRussianTranslation(listVerbForQuestion.get(12).getInfinitive_())));
        arrayForCompare[12] = listVerbForQuestion.get(12).getTranslation();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeFirstForm(listVerbForQuestion.get(13).getTranslation())));
        arrayForCompare[13] = listVerbForQuestion.get(13).getPast_practical();
        list_question.add(new StringBuilder().append(QuestionForAleks.makeFirdForm(listVerbForQuestion.get(14).getTranslation())));
        arrayForCompare[14] = listVerbForQuestion.get(14).getInfinitive_();

        i = new Intent(this, ShowQuestionActivity.class);
        startActivity(i);
    }


}


 class VerbForAleks{
     private String translation;
     private String past_practical;
     private String past_simple;
     private String infinitive_;

     public VerbForAleks(){}

     public String getTranslation(){
         return translation;
     }

     public void setTranslation(String translation){
         this.translation = translation;
     }

     public  String getPast_practical(){
         return past_practical;
     }

     public void setPast_practical(String past_practical){
         this.past_practical = past_practical;
     }

     public  String getPast_simple(){
         return past_simple;
     }

     public void setPast_simple(String past_simple){
         this.past_simple = past_simple;
     }

     public String getInfinitive_(){
         return infinitive_;
     }

     public void setInfinitive_(String infinitive){
         infinitive_ = infinitive;
     }

     public String toString(){
         String s = translation + " " + past_practical + " " + past_simple + " " + infinitive_;
         return s;
     }

}


class QuestionForAleks{
    private final static String russian_translation = "Russian translation verb ";
    private final static String form_first = "First form of ";
    private final static String form_second = "Second form of ";
    private final static String form_fird = "Third form of ";


    public static StringBuilder makeRussianTranslation(String englishVerb){
        StringBuilder s = new StringBuilder();
        s.append(russian_translation).append(englishVerb);
        return s;
    }

    public static StringBuilder makeFirstForm(String russianVerb){
        StringBuilder s = new StringBuilder();
        s.append(form_first).append(russianVerb);
        return s;
    }

    public static StringBuilder makeSecondForm(String russianVerb){
        StringBuilder s = new StringBuilder();
        s.append(form_second).append(russianVerb);
        return s;
    }

    public static StringBuilder makeFirdForm(String russianVerb){
        StringBuilder s = new StringBuilder();
        s.append(form_fird).append(russianVerb);
        return s;
    }
}


class RandomNumbers{
    private final int quantityVerb = 199;
    private int[] arrayRandomNumbers = new int[15];

    public RandomNumbers(){
        int[] arrayAll = new int[quantityVerb];
        for (int i = 0; i < arrayAll.length; i++){
            arrayAll[i] = i;
        }
        int number;
        for (int i = 0; i < arrayRandomNumbers.length; i++){
            number = (int)(Math.random() * quantityVerb);
            while (true){
                if (arrayAll[number] != -1){
                    break;
                } else {
                    number = (int)(Math.random() * quantityVerb);
                }
            }
            arrayAll[number] = -1;
            arrayRandomNumbers[i] = number;
        }
    }

    public int[] getArrayRandomNumbers(){
        return arrayRandomNumbers;
    }
}