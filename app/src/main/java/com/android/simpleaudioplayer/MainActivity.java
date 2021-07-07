package com.android.simpleaudioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer song;
    private Button playPause;
    private AudioManager audio;
    SeekBar volume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audio = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        song = MediaPlayer.create(this,R.raw.tareefan);
        playPause = findViewById(R.id.playPause);
        volume = findViewById(R.id.volume);
        volume.setMax(maxVolume);
        volume.setProgress(audio.getStreamVolume(AudioManager.STREAM_MUSIC));
        playPause.setBackgroundColor(getColor(R.color.colorPrimaryDark));
        playPause.setTextColor(getColor(R.color.white));
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audio.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                playPause.setBackgroundColor(getColor(R.color.colorAccent));
                playPause.setTextColor(getColor(R.color.black));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                playPause.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                playPause.setTextColor(getColor(R.color.white));
            }
        });
        
        playPause.setText(R.string.play_text);
    }
    public void setPlayPause(View v){
        if(song.isPlaying()){
            song.pause();
            playPause.setText(R.string.play_text);
        }
        else if(!song.isPlaying()){
            song.start();
            playPause.setText(R.string.pause_text);
        }
    }
}