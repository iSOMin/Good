package com.example.sam.good;

import android.view.View;
import android.widget.Button;

public class Stamp {

    private Button button;
    private View buttonimage;
    private int flag;

    public Stamp(View btnId,View btnImageId) {
        button = (Button)btnId;
        buttonimage = btnImageId;
        flag = 0;
    }
    private void changeFlag() {
        if(flag == 0) {
            flag = 1;
        }else {
            flag = 0;
        }
    }
    public int getFlag() {
        return flag;
    }
    public Button getButton() {
        return button;
    }
    public void putStamp(int finalI,Stamp stamp[]) {
        buttonimage.setVisibility(View.VISIBLE);
        changeFlag();
        if(finalI == 0) {
            stamp[finalI + 1].btnVisible();
        }else if(finalI > 0 && finalI < stamp.length - 1) {
            stamp[finalI - 1].btnInvisible();;
            stamp[finalI + 1].btnVisible();
        }else if(finalI == stamp.length) {
            stamp[finalI - 1].btnInvisible();
        }
    }
    public void removeStamp(int finalI,Stamp stamp[]) {
        buttonimage.setVisibility(View.INVISIBLE);
        changeFlag();
        if(finalI == 0) {
            stamp[finalI + 1].btnInvisible();
        }else if(finalI > 0 && finalI < stamp.length - 1) {
            stamp[finalI - 1].btnVisible();
            stamp[finalI + 1].btnInvisible();
        }else if(finalI == stamp.length) {
            stamp[finalI - 1].btnVisible();
        }
    }
    public void btnVisible() {
        button.setVisibility(View.VISIBLE);
    }
    public void btnInvisible() {
        button.setVisibility(View.INVISIBLE);
    }
}

