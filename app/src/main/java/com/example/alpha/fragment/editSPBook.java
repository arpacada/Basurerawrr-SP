package com.example.alpha.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Alpha on 10/28/2016.
 */

public class editSPBook extends Activity {
    EditText editTextAddTitle;
    EditText editTextAddDialogue;

    EditText editTextYear;
    EditText editNumOfCopies;

    Button editBookBTN;
    Button cancelBTN;

    ShakespeareDBHelper dbHelper;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_book);

        //get references to UI widgets-- EditText, ListView
        /*editTextAddTitle = (EditText) findViewById(R.id.add_book_title);
        editTextAddDialogue = (EditText) findViewById(R.id.add_dialogue);*/

        editTextAddTitle = (EditText)findViewById(R.id.editbook);
        editTextAddDialogue = (EditText)findViewById(R.id.editdialogue);
        dbHelper = new ShakespeareDBHelper(this, ShakespeareContract.ShakespeareEntry.DBNAME, 1);

        editTextYear = (EditText) findViewById(R.id.editText);
        editNumOfCopies = (EditText) findViewById(R.id.editText2);

        editBookBTN = (Button) findViewById(R.id.edit_title);
        cancelBTN = (Button) findViewById(R.id.cancel_button);

        intent = getIntent();

        dbHelper = new ShakespeareDBHelper(this, ShakespeareContract.ShakespeareEntry.DBNAME, 1);

        //generateUIandData();
        editBookBTN.setOnClickListener(addBookBTNListener);
        cancelBTN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                Intent intt = new Intent(getApplicationContext(),FragmentLayout.class);
                intt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intt);
            }


        });

    }

    private View.OnClickListener addBookBTNListener = new View.OnClickListener() {
        public void onClick(View v) {

            //dbHelper.addBook(editTextAddTitle.getText().toString(), editTextAddDialogue.getText().toString(), 4);

            //dbHelper.addBook(titleText,dialogueText,2);
            dbHelper.updateBook(new Shakespeare(intent.getIntExtra("id", 0), editTextAddTitle.getText().toString(), editTextAddDialogue.getText().toString(),intent.getIntExtra("image",0) ));
            //TODO UPDATE BOOK WITH NEW AREAS
            Intent in = new Intent(getApplicationContext(),FragmentLayout.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(in);

        }
    };





}


