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
        //Toast.makeText(LanguageSelection.this, "You clicked at " + position, Toast.LENGTH_SHORT).show();

        if (position == 0){
            Intent intent = new Intent (this, MainActivity.class);
            startActivity(intent);
        }
    }
}

class MyAdapter extends ArrayAdapter {
    int[] imageArray;
    String[] nameArray;

    public MyAdapter(@NonNull Context context, String[] name1, int[] img1) {
        super(context, R.layout.language_listview_layout,R.id.idName,name1);
        this.imageArray = img1;
        this.nameArray = name1;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.language_listview_layout,parent, false);

        ImageView myImage = row.findViewById(R.id.idPic);
        TextView myName = row.findViewById(R.id.idName);

        myImage.setImageResource(imageArray[position]);
        myName.setText(nameArray[position]);

        return row;
    }
}