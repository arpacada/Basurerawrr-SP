package com.example.alpha.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;


public class FragmentLayout extends AppCompatActivity {

    public static ShakespeareDBHelper dbHelper;

    Shakespeare firstItem = new Shakespeare(1, "Hello", "It's Me", 4);
    static ArrayList<Shakespeare> retrieved;

    Button addBook;
    ImageButton editBook;
    ImageButton deleteBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);

        dbHelper = new ShakespeareDBHelper(this);

        //Shakespeare newItem = new Shakespeare(1, "Hello", "It's Me", 4);
        //dbHelper.addBook( "Romeo", "Wherefore", 4);
        retrieved = dbHelper.getAllBooks();

        addBook = (Button) findViewById(R.id.add_button);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),addSPBook.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            }
        });

        //generateUIandData();



    }
    /*void generateUIandData() {

        }*/


    public static class TitlesFragment extends ListFragment {
        boolean mDualPane;
        int mCurCheckPosition = 0;
        private ShakespeareAdapter SPAdapter;
        /*Shakespeare[] SP = {
                new Shakespeare("Hamlet","1", R.drawable.cupcake),
                new Shakespeare("Romeo and Juliet","2", R.drawable.donut),
                new Shakespeare("Richard III","3", R.drawable.eclair),
                new Shakespeare("Macbeth","4", R.drawable.froyo),
                new Shakespeare("Henry IV, Part 2","5",  R.drawable.gingerbread),
                new Shakespeare("King Lear","6",  R.drawable.honeycomb),
                new Shakespeare("Othello","7", R.drawable.icecream),
                new Shakespeare("The Tempest","8",R.drawable.jellybean),
                new Shakespeare("Julius Caesar","9",  R.drawable.kitkat),
                new Shakespeare("Twelfth Night","10",  R.drawable.lollipop)
//                new Shakespeare("Hamlet",Shakespeare.DIALOGUE[1], R.drawable.cupcake),
//                new Shakespeare("Romeo and Juliet",Shakespeare.DIALOGUE[2], R.drawable.donut),
//                new Shakespeare("Richard III",Shakespeare.DIALOGUE[3], R.drawable.eclair),
//                new Shakespeare("Macbeth",Shakespeare.DIALOGUE[4], R.drawable.froyo),
//                new Shakespeare("Henry IV, Part 2",Shakespeare.DIALOGUE[5],  R.drawable.gingerbread),
//                new Shakespeare("King Lear",Shakespeare.DIALOGUE[6],  R.drawable.honeycomb),
//                new Shakespeare("Othello",Shakespeare.DIALOGUE[7], R.drawable.icecream),
//                new Shakespeare("The Tempest",Shakespeare.DIALOGUE[8],R.drawable.jellybean),
//                new Shakespeare("Julius Caesar",Shakespeare.DIALOGUE[9],  R.drawable.kitkat),
//                new Shakespeare("Twelfth Night",Shakespeare.DIALOGUE[10],  R.drawable.lollipop)
//                new Shakespeare("Hamlet", R.drawable.cupcake),
//                new Shakespeare("Romeo and Juliet",R.drawable.donut),
//                new Shakespeare("Richard III", R.drawable.eclair),
//                new Shakespeare("Macbeth", R.drawable.froyo),
//                new Shakespeare("Henry IV, Part 2", R.drawable.gingerbread),
//                new Shakespeare("King Lear", R.drawable.honeycomb),
//                new Shakespeare("Othello", R.drawable.icecream),
//                new Shakespeare("The Tempest",R.drawable.jellybean),
//                new Shakespeare("Julius Caesar",R.drawable.kitkat),
//                new Shakespeare("Twelfth Night", R.drawable.lollipop)
        };*/
        @Override
        // public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            // Populate list with our static array of titles.

            //SPAdapter = new ShakespeareAdapter(getActivity(), Arrays.asList(SP));
            SPAdapter = new ShakespeareAdapter(getActivity(), retrieved);
            //View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // Get a reference to the ListView, and attach this adapter to it.
//            ListView listView = (ListView) rootView.findViewById(R.id.listview_shakespeare);
//            listView.setAdapter(SPAdapter);
            setListAdapter(SPAdapter);

            try{
                SPAdapter.notifyDataSetChanged();
            }catch(Throwable e){
                //error occured. Probably null
            }

            // Check to see if we have a frame in which to embed the details
            // fragment directly in the containing UI.
            View detailsFrame = getActivity().findViewById(R.id.details);
            mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

            if (savedInstanceState != null) {
                // Restore last state for checked position.
                mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
            }

            if (mDualPane) {
                // In dual-pane mode, the list view highlights the selected item.
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                // Make sure our UI is in the correct state.
                showDetails(mCurCheckPosition);
            }
            // return rootView;
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt("curChoice", mCurCheckPosition);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            showDetails(position);
        }

        /**
         * Helper function to show the details of a selected item, either by
         * displaying a fragment in-place in the current UI, or starting a
         * whole new activity in which it is displayed.
         */
        void showDetails(int index) {
            mCurCheckPosition = index;

            if (mDualPane) {
                // We can display everything in-place with fragments, so update
                // the list to highlight the selected item and show the data.
                getListView().setItemChecked(index, true);

                // Check what fragment is currently shown, replace if needed.
                DetailsFragment details = (DetailsFragment)
                        getFragmentManager().findFragmentById(R.id.details);
                if (details == null || details.getShownIndex() != index) {
                    // Make new fragment to show this selection.
                    details = DetailsFragment.newInstance(index);

                    // Execute a transaction, replacing any existing fragment
                    // with this one inside the frame.
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
//                    if(index == 0) {
                    ft.replace(R.id.details, details);
//                    } else{
//                        ft.replace(R.id.a_item, details);
//                    }
//

                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }

            } else {
                // Otherwise we need to launch a new activity to display
                // the dialog fragment with selected text.
                Intent intent = new Intent();
                intent.setClass(getContext(), DetailsActivity.class);
                intent.putExtra("index", index);
                System.out.println(index);
                startActivity(intent);
            }
        }
    }


    public static class DetailsFragment extends Fragment {
        /**
         * Create a new instance of DetailsFragment, initialized to
         * show the text at 'index'.
         */
        public static DetailsFragment newInstance(int index) {
            DetailsFragment f = new DetailsFragment();

            // Supply index input as an argument.
            Bundle args = new Bundle();
            args.putInt("index", index);
            f.setArguments(args);

            return f;
        }

        public int getShownIndex() {
            return getArguments().getInt("index", 0);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if (container == null) {
                // We have different layouts, and in one of them this
                // fragment's containing frame doesn't exist.  The fragment
                // may still be created from its saved state, but there is
                // no reason to try to create its view hierarchy because it
                // won't be displayed.  Note this is not needed -- we could
                // just run the code below, where we would create and return
                // the view hierarchy; it would just never be used.
                return null;
            }

            ScrollView scroller = new ScrollView(getActivity());
            TextView text = new TextView(getActivity());
            int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    4, getActivity().getResources().getDisplayMetrics());
            text.setPadding(padding, padding, padding, padding);
            scroller.addView(text);
            text.setText(retrieved.get(getShownIndex()).getDialogue());
            return scroller;
        }
    }



    public static class DetailsActivity extends FragmentActivity {
        //        public DetailsActivity(){
//            super();
//        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //Toast.makeText(getApplicationContext(),"Details SavedInstanceState is null",Toast.LENGTH_LONG).show();
            if (getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_LANDSCAPE) {
                // If the screen is now in landscape mode, we can show the
                // dialog in-line with the list so we don't need this activity.
                finish();
                return;
            }
//
            if (savedInstanceState == null) {

                // During initial setup, plug in the details fragment.
                DetailsFragment details = new DetailsFragment();
                details.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
            }
        }
    }
}
