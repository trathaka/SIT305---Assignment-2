package com.example.assignment2.KoreanNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment2.R;
import com.example.assignment2.TimePicker;

public class KoreanSettingFragment extends Fragment {
    String string;
    String string2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        Button buttonG = (Button) view.findViewById(R.id.button_google);
        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonGClick(v);
            }
        });

        Button buttonT = (Button) view.findViewById(R.id.button_twitter);
        buttonT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonTClick(v);

            }
        });

        Button buttonF = (Button) view.findViewById(R.id.button_facebook);
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFClick(v);
            }
        });

        Button button = (Button) view.findViewById(R.id.button_set_alarm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TimePicker.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void buttonFClick(View v) {
        try{
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        string = "http://www.deakin.edu.au";
        sharingIntent.putExtra(Intent.EXTRA_TEXT, string);
        sharingIntent.setPackage("com.facebook.katana");
        startActivity(sharingIntent);
        }catch(Exception e){
            Toast.makeText(getActivity(),"Please Install the Facebook App,", Toast.LENGTH_SHORT).show();
        }
    }

    public void buttonTClick(View v) {
        try{
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        string2 = "http://www.deakin.edu.au";
        sharingIntent.putExtra(Intent.EXTRA_TEXT, string);
        sharingIntent.setPackage("advanced.twitter.android");
        startActivity(sharingIntent);
        }catch(Exception e){
            Toast.makeText(getActivity(),"Please Install the Twitter App,", Toast.LENGTH_SHORT).show();
        }
    }

    public void buttonGClick(View v) {
        try {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Monkify: Learn languages!" + "http://www.deakin.edu.au");
            sharingIntent.setPackage("com.google.android.apps.plus");
            startActivity(sharingIntent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please Install the GooglePlus App,", Toast.LENGTH_SHORT).show();
        }
    }
}
