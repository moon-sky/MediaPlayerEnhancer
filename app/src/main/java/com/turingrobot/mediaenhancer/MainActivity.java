package com.turingrobot.mediaenhancer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TuringLocalMusicPlayer.getInstance().setContext(this);
    }
    public void playRaw(View view) throws Exception {
//        TuringLocalMusicPlayer.getInstance().play(R.raw.test+"");
        TuringLocalMusicPlayer.getInstance().play("http://zzya.beva.cn/dq/Fm5OywiZryf7SqujxS898s-_SDEC.mp3");
    }
    public void playGain(View view){
        try {
//            TuringLocalMusicPlayer.getInstance().play(R.raw.test+"",2000);
            TuringLocalMusicPlayer.getInstance().play("http://zzya.beva.cn/dq/Fm5OywiZryf7SqujxS898s-_SDEC.mp3",1500);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
