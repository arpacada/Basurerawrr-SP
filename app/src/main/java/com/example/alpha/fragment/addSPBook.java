package com.example.alpha.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Mariz on 10/28/2016.
 */
public class addSPBook extends Activity {
    EditText editTextAddTitle;
    EditText editTextAddDialogue;

    Button addBookBTN;
    Button cancelBTN;
    Spinner genreSpinner;
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
        genreSpinner = (Spinner) findViewById(R.id.spinner);
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

        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_genre_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        genreSpinner.setAdapter(spinnerAdapter);

        // Set the integer mSelected to the constant values
        genreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.genre_romance))) {
                        genre = PetEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        genre = PetEntry.GENDER_FEMALE;
                    } else if (selection.equals(getString(R.string.)))
                    } else {
                        genre = PetEntry.GENDER_UNKNOWN;
                    }
                }
            }

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
