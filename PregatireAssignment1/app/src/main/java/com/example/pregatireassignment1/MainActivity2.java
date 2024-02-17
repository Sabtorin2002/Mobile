package com.example.pregatireassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        int sizePrimit = intent.getIntExtra("lungimeaListei",0);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("sizePrimit", sizePrimit);
        editor.apply();
        Toast.makeText(getApplicationContext(),"Lungimea listei este:"+sizePrimit,Toast.LENGTH_LONG).show();

//        studentList.addAll(this.studentListTxt);
//        studentList.stream().forEach(str ->{tvStudenti.append(str.toString());
//            tvStudenti.append("\n");});
    }
}