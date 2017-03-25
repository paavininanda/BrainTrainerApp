package com.paavini.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button,playAgain,button1,button2,button3,button4;
    TextView timer,score,ques,result;
    GridLayout gridLayout;
    CountDownTimer countDownTimer;
    int a,b,location,totalques=0,correctques=0;

    public void clicked(View view){
        totalques++;
        result.setVisibility(View.VISIBLE);
        if(view.getTag().toString().equals(Integer.toString(location))){
            result.setText("Correct!");
            correctques++;
        }
        else{
            result.setText("Incorrect!");
        }
        score.setText( Integer.toString(correctques)+"/"+Integer.toString(totalques));
        makeAQues();
    }

    public void makeAQues(){
        int[] ansButton={0,0,0,0,0};
        Random random=new Random();
        a = random.nextInt(21);
        b=random.nextInt(21);
        ques.setText(Integer.toString(a)+"+" + Integer.toString(b));
        location = random.nextInt(4)+1;
        for(int i=1;i<=4;i++){
            if(i!=location){
                int incorrect=random.nextInt(41);
                while(incorrect==(a+b)){
                    incorrect=random.nextInt(41);
                }
                ansButton[i]=incorrect;
            }
            else{
                ansButton[i]=a+b;
            }
        }
        button1.setText(Integer.toString(ansButton[1]));
        button2.setText(Integer.toString(ansButton[2]));
        button3.setText(Integer.toString(ansButton[3]));
        button4.setText(Integer.toString(ansButton[4]));


    }

    public void start(View view){
        button=(Button)findViewById(R.id.start);
        button.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        ques.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);

        totalques=0;
        correctques=0;
        score.setText("0/0");
        timer.setText("30s");
        countDownTimer=new CountDownTimer(30000+100,1000) {
            @Override
            public void onTick(long l) {
                timer.setText(Integer.toString((int)l/1000)+"s");
            }

            @Override
            public void onFinish() {
                //gridLayout.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                timer.setText("0s");
                result.setText("Final Score: " + Integer.toString(correctques));
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start() ;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        timer= (TextView) findViewById(R.id.timer);
        score = (TextView) findViewById(R.id.score);
        ques=(TextView) findViewById(R.id.sum);
        result=(TextView) findViewById(R.id.result);
        playAgain=(Button) findViewById(R.id.playAgain);
        button1=(Button) findViewById(R.id.button1) ;
        button2=(Button) findViewById(R.id.button2) ;
        button3=(Button) findViewById(R.id.button3) ;
        button4=(Button) findViewById(R.id.button4) ;


        gridLayout.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        ques.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);

        makeAQues();

    }
}
