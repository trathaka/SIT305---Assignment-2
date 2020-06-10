package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment2.JapaneseNavigation.JapaneseMainFragment;
import com.example.assignment2.KoreanNavigation.KoreanMainFragment;

public class LanguageSelection extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] name = {"Korean", "Japanese", "Chinese", "French" ,"Spanish" ,"Arabic" ,"Hindi"};
    int[] images = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,};
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
        lv = findViewById(R.id.idListView);
        MyAdapter adapter = new MyAdapter(getBaseContext(),name,images);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        if (position == 0){
            Intent intent = new Intent (this, KoreanMainFragment.class);
            startActivity(intent);
        }

        if (position == 1){
            Intent intent = new Intent (this, JapaneseMainFragment.class);
            startActivity(intent);

        }

        if (position == 2){
            Toast.makeText(LanguageSelection.this, "Coming soon!", Toast.LENGTH_SHORT).show();

        }

        if (position == 3){
            Toast.makeText(LanguageSelection.this, "Coming soon!", Toast.LENGTH_SHORT).show();
        }

        if (position == 4){
            Toast.makeText(LanguageSelection.this, "Coming soon!", Toast.LENGTH_SHORT).show();
        }

        if (position == 5){
            Toast.makeText(LanguageSelection.this, "Coming soon!", Toast.LENGTH_SHORT).show();
        }

        if (position == 6){
            Toast.makeText(LanguageSelection.this, "Coming soon!", Toast.LENGTH_SHORT).show();
        }

        if (position == 7){
            Toast.makeText(LanguageSelection.this, "Coming soon!", Toast.LENGTH_SHORT).show();
        }

        if (position == 8){
            Toast.makeText(LanguageSelection.this, "Coming soon!", Toast.LENGTH_SHORT).show();
        }

    }
}

class MyAdapter extends ArrayAdapter {
    int[] imageArray;
    String[] nameArray;

    public MyAdapter(@NonNull Context context, String[] name1, int[] img1) {
        super(context, R.layout.language_selection_layout,R.id.idName,name1);
        this.imageArray = img1;
        this.nameArray = name1;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.language_selection_layout,parent, false);

        ImageView myImage = row.findViewById(R.id.idPic);
        TextView myName = row.findViewById(R.id.idName);

        myImage.setImageResource(imageArray[position]);
        myName.setText(nameArray[position]);

        return row;
    }
}