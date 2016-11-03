package com.example.alpha.fragment;



/**

 * Created by Alpha on 10/16/2016.

 */

public class Shakespeare {



    //    public static String[] TITLES = {"TITLE 1", "TITLE2"};

   /* public static String[] DIALOGUE = {"To be, or not to be: that is the question", "Romeo, Romeo! wherefore art thou Romeo?",

            "Now is the winter of our discontent.", "Is this a dagger which I see before me, the handle toward my hand?",

            "A man can die but once. ", "How sharper than a serpent’s tooth it is to have a thankless child!",

            "I am one who loved not wisely but too well.", "We are such stuff as dreams are made on, and our little life is rounded with a sleep. ",

            "Beware the Ides of March. ", "If music be the food of love play on.",};*/

    /*String Title;

    String Dialogue;

    int image; // drawable reference id*/



     /*public Shakespeare(String vTitle,  String vDialogue, int image )

    {

        this.Title = vTitle;

        this.Dialogue = vDialogue;

        this.image = image;

    }*/



    private int id;

    private String title;

    private String dialogue;

    private int image;



    public Shakespeare() {



    }



    public Shakespeare(int id, String title, String dialogue, int image){

        this.id=id;

        this.title=title;

        this.dialogue=dialogue;

        this.image=image;

    }



    public void setID(int id) {

        this.id = id;

    }



    public void setTitle(String title) {

        this.title = title;

    }



    public void setDialogue(String dialogue) {

        this.dialogue = dialogue;

    }



    public void setImage(int image) {

        this.image = image;

    }



    public int getID() {

        return id;

    }



    public String getTitle() {

        return title;

    }



    public String getDialogue() {

        return dialogue;

    }



    public int getImage() {

        return image;

    }



}