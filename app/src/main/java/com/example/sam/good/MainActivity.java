package com.example.sam.good;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

    //배열 크기 선언
    final int arraySize = 15;
    //기본 스탭프 정의
    final Stamp stamp[] = new Stamp[arraySize];

    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //저장된 것 꺼내오기
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

        //레이아웃 겹치기
        Window win = getWindow();
        win.setContentView(R.layout.activity_main);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout relative = (RelativeLayout)inflater.inflate(R.layout.stamp_activity, null);
        RelativeLayout.LayoutParams paramlinear = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        win.addContentView(relative, paramlinear);

        //스탬프 배열 원소 초기화
        stamp[0] = new Stamp(findViewById(R.id.button01),findViewById(R.id.buttonimage01));
        stamp[1] = new Stamp(findViewById(R.id.button02),findViewById(R.id.buttonimage02));
        stamp[2] = new Stamp(findViewById(R.id.button03),findViewById(R.id.buttonimage03));
        stamp[3] = new Stamp(findViewById(R.id.button04),findViewById(R.id.buttonimage04));
        stamp[4] = new Stamp(findViewById(R.id.button05),findViewById(R.id.buttonimage05));
        stamp[5] = new Stamp(findViewById(R.id.button06),findViewById(R.id.buttonimage06));
        stamp[6] = new Stamp(findViewById(R.id.button07),findViewById(R.id.buttonimage07));
        stamp[7] = new Stamp(findViewById(R.id.button08),findViewById(R.id.buttonimage08));
        stamp[8] = new Stamp(findViewById(R.id.button09),findViewById(R.id.buttonimage09));
        stamp[9] = new Stamp(findViewById(R.id.button10),findViewById(R.id.buttonimage10));
        stamp[10] = new Stamp(findViewById(R.id.button11),findViewById(R.id.buttonimage11));
        stamp[11] = new Stamp(findViewById(R.id.button12),findViewById(R.id.buttonimage12));
        stamp[12] = new Stamp(findViewById(R.id.button13),findViewById(R.id.buttonimage13));
        stamp[13] = new Stamp(findViewById(R.id.button14),findViewById(R.id.buttonimage14));
        stamp[14] = new Stamp(findViewById(R.id.button15),findViewById(R.id.buttonimage15));

        // 저장된 값들을 불러옵니다.
        int newFlag = pref.getInt("newFlag",0);

        if(newFlag != 0) {
            for(int i = 0; i < newFlag; i++) {
                stamp[i].putStamp(i, stamp);
            }
            stamp[newFlag].btnVisible();
            stamp[newFlag - 1].btnVisible();
        } else {
            stamp[0].btnVisible();
        }

        //클릭 리스너 설정
        for(int i = 0; i < arraySize; i++) {
            final int finalI = i;

            stamp[finalI].getButton().setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(40);

                    if (stamp[finalI].getFlag() == 0) {
                        stamp[finalI].putStamp(finalI, stamp);
                    }else {
                        stamp[finalI].removeStamp(finalI, stamp);
                    }
                }
            });
        }
    }


    public void onStop(){
        // 어플리케이션이 화면에서 사라질때
        super.onStop();
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        for(int j = 0; j < arraySize; j++) {
            if(stamp[j].getFlag() == 0) {
                editor.putInt("newFlag", j);
                break;
            }
        }
        editor.commit();
    }



}