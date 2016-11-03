package com.example.alpha.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mariz on 10/28/2016.
 */
public class addSPBook extends Activity {
    EditText editTextAddTitle;
    EditText editTextAddDialogue;

    Button addBookBTN;
    Button cancelBTN;

    ShakespeareDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);

        //get references to UI widgets-- EditText, ListView
        editTextAddTitle = (EditText) findViewById(R.id.add_book_title);
        editTextAddDialogue = (EditText) findViewById(R.id.add_dialogue);

        addBookBTN = (Button) findViewById(R.id.addBook_Button);
        cancelBTN = (Button) findViewById(R.id.cancel_add);

        dbHelper = new ShakespeareDBHelper(this);

        //generateUIandData();
        addBookBTN.setOnClickListener(addBookBTNListener);
        cancelBTN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                Intent in = new Intent(getApplicationContext(),FragmentLayout.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            }


        });

    }

    private View.OnClickListener addBookBTNListener = new View.OnClickListener() {
        public void onClick(View v) {

            dbHelper.addBook(editTextAddTitle.getText().toString(), editTextAddDialogue.getText().toString(), 4);

            //dbHelper.addBook(titleText,dialogueText,2);


            Intent in = new Intent(getApplicationContext(),FragmentLayout.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(in);

        }
    };





}
