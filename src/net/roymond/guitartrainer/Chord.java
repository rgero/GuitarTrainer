package net.roymond.guitartrainer;

import javax.swing.*;

/**
 * Created by gero on 1/9/2017.
 */
public class Chord {

    private String name;
    private ImageIcon image;

    Chord(String name, ImageIcon image){
        this.name = name;
        this.image = image;
    }

    public String getName(){
        return name;
    }

    public ImageIcon getImage(){
        return image;
    }

}
