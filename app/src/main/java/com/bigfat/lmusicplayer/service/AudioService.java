package com.bigfat.lmusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

/**
 * Created by yueban on 15/4/8.
 */
public class AudioService extends Service {

    private AudioBinder binder = new AudioBinder();
    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mp.getDuration() - mp.getCurrentPosition() < 1000) {
                    mediaPlayer.stop();
                }
            }
        });
    }

    public class AudioBinder extends Binder {

        public void playAudio(String url) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
