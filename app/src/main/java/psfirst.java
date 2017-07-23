/**
 * Created by user on 23-Jul-17.
 */
package com.example.user.hithithit;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class psfirst extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.psfirst);
    }
    public void first(View view){
        Intent i=new Intent(psfirst.this,MainActivity.class);
        startActivity(i);
    }
}
