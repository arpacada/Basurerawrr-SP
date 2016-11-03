package com.example.alpha.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Alpha on 10/16/2016.
 */


public class ShakespeareAdapter extends ArrayAdapter<Shakespeare> {

    ShakespeareDBHelper db;
    Intent intent;
    Context c;
    //private static final String LOG_TAG = ShakespeareAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the List is the data we want
     * to populate into the lists
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param SP A List of Shakespeare objects to display in a list
     */
    public ShakespeareAdapter(Activity context, List<Shakespeare> SP) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, SP);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        // Gets the Shakespeare object from the ArrayAdapter at the appropriate position
        final Shakespeare Shakespeare = getItem(position);
        ImageButton editBook;
        ImageButton deleteBook;
        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_shakespeare, parent, false);
        }

        db = new ShakespeareDBHelper(getContext());

         editBook = (ImageButton) convertView.findViewById(R.id.edit_button);
         deleteBook = (ImageButton) convertView.findViewById(R.id.delete_button);
        editBook.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in = new Intent(parent.getContext(), editSPBook.class);
                String msg = "editBook";
                in.putExtra("key", msg);
                in.putExtra("title", Shakespeare.getTitle());
                in.putExtra("dialogue", Shakespeare.getDialogue());
                in.putExtra("image", Shakespeare.getImage());
                in.putExtra("id", Shakespeare.getID());
                parent.getContext().startActivity(in);
            }
        });

        deleteBook.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent in = new Intent(parent.getContext(), FragmentLayout.class);
                Shakespeare bookSample = new Shakespeare(Shakespeare.getID(), Shakespeare.getTitle(),Shakespeare.getDialogue(), Shakespeare.getImage());
                db.deleteBook(bookSample);
                View c = LayoutInflater.from(getContext()).inflate(R.layout.list_item_shakespeare, parent, false);
                parent.getContext().startActivity(in);
            }
        });
        ImageView iconView = (ImageView) convertView.findViewById(R.id.list_item_icon);
        //iconView.setImageResource(Shakespeare.image);
        iconView.setImageResource(R.drawable.cupcake);


        TextView TitleView = (TextView) convertView.findViewById(R.id.list_item_title);

        //Please replace with
        TitleView.setText(Shakespeare.getTitle());

        TextView DialogueView = (TextView) convertView.findViewById(R.id.list_item_dialogue);

        //Please replace with
        DialogueView.setText(Shakespeare.getDialogue());

        return convertView;
    }
}

