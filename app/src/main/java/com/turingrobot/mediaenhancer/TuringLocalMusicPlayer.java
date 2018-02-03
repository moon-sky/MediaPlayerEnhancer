package com.turingrobot.mediaenhancer;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.audiofx.LoudnessEnhancer;
import android.util.Log;

import java.io.IOException;

/**
 * Created by 51101 on 2017/12/14.
 */

public class TuringLocalMusicPlayer implements MediaPlayer.OnPreparedListener{
    private static TuringLocalMusicPlayer turingPlayer;
    private String url;
    private static final String TAG = TuringLocalMusicPlayer.class.getSimpleName();
    private MediaPlayer mediaPlayer;
    private Context mContext;
    private MediaPlayer.OnCompletionListener completionListener;
    private MediaPlayer.OnErrorListener errorListener;
    private MediaPlayer.OnPreparedListener prepareListenr;

    private TuringLocalMusicPlayer() {
    }

    ;

    public static TuringLocalMusicPlayer getInstance() {
        if (turingPlayer == null) {
            turingPlayer = new TuringLocalMusicPlayer();
        }
        return turingPlayer;
    }

    public void setContext(Context ctx) {
        this.mContext = ctx;
    }

    public void setCompleteListener(MediaPlayer.OnCompletionListener listener) {
        this.completionListener = listener;
    }

    public void setErrorListener(MediaPlayer.OnErrorListener listener) {
        this.errorListener = listener;
    }

    public void setPrepareListenr(MediaPlayer.OnPreparedListener listenr) {
        this.prepareListenr = listenr;
    }


    /**
     * 释放资源
     */
    public void releaseMusicResource() {
        if (mediaPlayer != null) {
            try {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.reset();
            } catch (Exception e) {

            }
            mediaPlayer = null;
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void play(String url, int mb) throws IOException {
        Log.d(TAG, "play local music url is:" + url);
        releaseMusicResource();//释放资源

        try {  if (Integer.parseInt(url) > 0) {// 在这里判断是否播放的是资源文件本地资源

                mediaPlayer = MediaPlayer.create(this.mContext, Integer.parseInt(url));
                LoudnessEnhancer enhancer = new LoudnessEnhancer(mediaPlayer.getAudioSessionId());
                enhancer.setEnabled(true);
                float curGain = enhancer.getTargetGain();
                Log.d(TAG, "current gain is：" + curGain);
                enhancer.setTargetGain(mb);
//                mediaPlayer.setVolume(5.0f,5.0f);
                mediaPlayer.setOnCompletionListener(this.completionListener); // 监听是否完成
                mediaPlayer.setOnErrorListener(this.errorListener);//播放出错监听
                mediaPlayer.start();

            return;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(completionListener); // 监听是否完成
        mediaPlayer.setOnErrorListener(errorListener);//播放出错监听
        mediaPlayer.setDataSource(url);
        LoudnessEnhancer enhancer = new LoudnessEnhancer(mediaPlayer.getAudioSessionId());
        enhancer.setTargetGain(1500);
        enhancer.setEnabled(true);
        mediaPlayer.setLooping(false);
        mediaPlayer.prepareAsync();
        mediaPlayer.setVolume(1.0f, 1.0f);
    }

    /**
     * 播放单首
     */
    public void play(final String url) throws Exception {
        Log.d(TAG, "play local music url is:" + url);
        releaseMusicResource();//释放资源

        try {
            if (Integer.parseInt(url) > 0) {// 在这里判断是否播放的是资源文件本地资源

                mediaPlayer = MediaPlayer.create(this.mContext, Integer.parseInt(url));
                mediaPlayer.setOnCompletionListener(this.completionListener); // 监听是否完成
                mediaPlayer.setOnErrorListener(this.errorListener);//播放出错监听
                mediaPlayer.start();

                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(completionListener); // 监听是否完成
        mediaPlayer.setOnErrorListener(errorListener);//播放出错监听
        mediaPlayer.setDataSource(url);
        mediaPlayer.setLooping(false);
        mediaPlayer.prepareAsync();
        mediaPlayer.setVolume(1.0f, 1.0f);

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        if(mediaPlayer!=null){
            mediaPlayer.start();
        }
    }
}
