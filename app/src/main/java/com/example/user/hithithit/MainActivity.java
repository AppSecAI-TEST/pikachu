package com.example.user.hithithit;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    ImageView p1,p2,p3;
    TextView tv_left,tv_score;
    Button button;
    Random r;
    int score= 0,fps =1000,left= 5,tempifleft= 0;

    int which=0;
    int whichsave=0;

    AnimationDrawable an;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r= new Random();
        button=(Button) findViewById(R.id.button);
        tv_left=(TextView) findViewById(R.id.tv_left);
        tv_score=(TextView) findViewById(R.id.tv_score);

        p1=(ImageView) findViewById(R.id.p1);
        p2=(ImageView) findViewById(R.id.p2);
        p3=(ImageView) findViewById(R.id.p3);

        p1.setVisibility(View.INVISIBLE);
        p2.setVisibility(View.INVISIBLE);
        p3.setVisibility(View.INVISIBLE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               left= 5;
                tv_left.setText("LEFT: "+ left);
                score= 0;
                tv_score.setText("SCORE: "+ score);
                android.os.Handler handler= new android.os.Handler();
                handler.postDelayed(new Runnable(){
                   @Override
                    public void run(){
                       theGameActions();
                   }
                },1000);
                button.setEnabled(false);

            }
        });

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempifleft=1;
                p1.setImageResource(R.drawable.pikachu);
                score=score+1;
                tv_score.setText("SCORE: "+ score);
                p1.setEnabled(false);
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempifleft=1;
                p2.setImageResource(R.drawable.pikachu);
                score=score+1;
                tv_score.setText("SCORE: "+ score);
                p2.setEnabled(false);

            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempifleft=1;
                p3.setImageResource(R.drawable.pikachu);
                score=score+1;
                tv_score.setText("SCORE: "+ score);
                p3.setEnabled(false);

            }
        });

    }
    private void theGameActions(){
         if(score<10) {
             fps = 1000;
         }else if(score>=10 && score <15){
                 fps=950;
         }else if(score>=15 && score <20){
             fps=900;
         }else if(score>=20 && score <25){
             fps=850;
         }else if(score>=25 && score <30){
             fps=800;
         }else if(score>=30 && score <35){
             fps=750;
         }else if(score>=35 && score <40){
             fps=700;
         }else if(score>=40 && score <45){
             fps=650;
         }else if(score>=45 && score <50){
             fps=600;
         }else if(score>=50 && score <55){
             fps=550;
         }else if(score>=55 && score <60){
             fps=500;
         }else if(score>=60 && score <65){
             fps=450;
         }else if(score>=65 && score <70){
             fps=400;
         }else if(score>=70){
             fps=350;
         }
         an=(AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.anim);
        do{
            which=r.nextInt(3)+1;
        }while(whichsave==which);
        whichsave=which;

        if(which == 1){
            p1.setImageDrawable(an);
            p1.setVisibility(View.VISIBLE);
            p1.setEnabled(true);
        }else if(which == 2){
            p2.setImageDrawable(an);
            p2.setVisibility(View.VISIBLE);
            p2.setEnabled(true);
        }else if(which == 3){
            p3.setImageDrawable(an);
            p3.setVisibility(View.VISIBLE);
            p3.setEnabled(true);
        }
        an.start();
        android.os.Handler handler=new android.os.Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                p1.setVisibility(View.INVISIBLE);
                p2.setVisibility(View.INVISIBLE);
                p3.setVisibility(View.INVISIBLE);

                p1.setEnabled(false);
                p2.setEnabled(false);
                p3.setEnabled(false);

                if (tempifleft == 0) {
                    left = left - 1;
                    tv_left.setText("LEFT: " + left);
                } else if (tempifleft == 1) {
                    tempifleft = 0;
                }
                if (left == 0) {
                    Toast.makeText(MainActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                    button.setEnabled(true);
                    Intent intent =new Intent(getApplicationContext(),result.class);
                    intent.putExtra("SCORE",score);
                    Intent intent1 =new Intent(MainActivity.this,result.class);
                    startActivity(intent);
                } else if (left > 0) {
                    theGameActions();
                }
            }
        },fps);
    }
    public void onBackPressed(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to exit?");
        builder.setCancelable(true);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
}
