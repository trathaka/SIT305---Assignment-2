package com.example.assignment2.JapaneseListView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.JapaneseLesson.Japanese1Activity;
import com.example.assignment2.JapaneseLesson.Japanese2Activity;
import com.example.assignment2.JapaneseLesson.Japanese3Activity;
import com.example.assignment2.R;

public class JapaneseListAdapter extends RecyclerView.Adapter {

    // Create new views
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_selection_listview, parent, false);
        return new ListViewHolder(view);
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return JapaneseData.title.length;
    }

    // Provide a reference to the views for each data item
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextView;
        private ImageView mImageView;

        public ListViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.KtextView);
            mImageView = (ImageView) itemView.findViewById(R.id.KimageView);
            itemView.setOnClickListener(this);
        }
        // Bind views with the specified elements in each position
        public void bindView(int position) {
            mTextView.setText(JapaneseData.title[position]);
            mImageView.setImageResource(JapaneseData.image[position]);
        }
        // Handle onClick activities for each item in the views based on the position
        public void onClick(View view) {
            if (getLayoutPosition() == 0) {
                Context context = view.getContext();
                Intent intent = new Intent(context, Japanese1Activity.class);
                context.startActivity(intent);
            }
            if (getLayoutPosition() == 1) {
                Context context = view.getContext();
                Intent intent = new Intent(context, Japanese2Activity.class);
                context.startActivity(intent);
            }
            if (getLayoutPosition() == 2) {
                Context context = view.getContext();
                Intent intent = new Intent(context, Japanese3Activity.class);
                context.startActivity(intent);
            }
            if (getLayoutPosition() == 3) {
                Toast.makeText(view.getContext(), "This lesson is coming soon!", Toast.LENGTH_SHORT).show();
            }
            if (getLayoutPosition() == 4) {
                Toast.makeText(view.getContext(), "This lesson is coming soon!", Toast.LENGTH_SHORT).show();
            }
            if (getLayoutPosition() == 5) {
                Toast.makeText(view.getContext(), "This lesson is coming soon!", Toast.LENGTH_SHORT).show();
            }
            if (getLayoutPosition() == 6) {
                Toast.makeText(view.getContext(), "This lesson is coming soon!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
