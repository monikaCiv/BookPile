package com.example.monique.hrpaknjiga.models;

import android.graphics.Color;

import java.util.Random;

public class ColorWheel {

    private String[] mColors = {
            "#c51162",//pinks
            "#f06292",
            "#ec407a",
            "#d81b60",
            "#c2185b",
            "#ad1457",
            "#880e4f",

            "#8b4173", //purples 1
            "#733162",
            "#944a7b",
            "#52204a",
            "#200820",
            "#412039",
            "#94527b",

            "#230c22",  //purples 2
            "#441940",
            "#632754",
            "#833B6C",
            "#5F1A37",
            "#CB807D",

            "#546E7A" //gray

    };

    public int getColor() {
        String color;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);
        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);
        return colorAsInt;
    }

}
