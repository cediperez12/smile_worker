package com.smile.worker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smile.worker.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewsListAdapter extends RecyclerView.Adapter<ReviewsListAdapter.ViewHolder>{

    String chat_name[], chat_display[];
    int images[];
    Context context;

    public ReviewsListAdapter (Context ct, String s1[], String s2[], int img[]){
        context = ct;
        chat_name = s1;
        chat_display = s2;
        images = img;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_reviews_list,parent,false);

        return new ReviewsListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_Reviews_Name.setText(chat_name[position]);
        holder.reviews_comment.setText(chat_display[position]);
        holder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return chat_name.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_fragmentReviews_Name_actWorker)
        TextView tv_Reviews_Name;
        @BindView(R.id.tv_fragmentReviews_Comment_actWorker)
        TextView reviews_comment;
        @BindView(R.id.imV_fragmentReviews_actWorker)
        CircleImageView imageView;
        @BindView(R.id.rb_ratings_reviews)
        RatingBar rb_ratings_reviews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }


}
