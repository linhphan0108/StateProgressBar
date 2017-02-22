package com.example.linh.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, TextWatcher {

    StateProgressbar mStateProgressbar;
    SeekBar mSeekBar;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStateProgressbar = (StateProgressbar) findViewById(R.id.stateProgressbar);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mEditText = (EditText) findViewById(R.id.edt);
        mEditText.addTextChangedListener(this);

        mStateProgressbar.addLevel(300);
        mStateProgressbar.addLevel(1000);
        mStateProgressbar.addLevel(600);
        mStateProgressbar.invalidate();

        mSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        try {
            mStateProgressbar.setProgress(progress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            mStateProgressbar.setProgress(Integer.valueOf(String.valueOf(s)));
        }catch (NumberFormatException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
