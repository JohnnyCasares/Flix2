package com.example.flix.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flix.DetailActivity;
import com.example.flix.R;
import com.example.flix.models.Movie;

import org.parceler.Parcels;

import java.security.acl.LastOwnerException;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
        //Adapter<MovieAdapter.ViewHolder>

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating a layout from xml and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent,false);
        return new ViewHolder(movieView);



    }


    //Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //GEt movie at the passed position
        Movie movie = movies.get(position);
        //Bind the movie data into the ViewHolder()VH
        holder.bind(movie);

    }


    //RETURN THE TOTAl count of items
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{


        ConstraintLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        TextView realeaseDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
            realeaseDate = itemView.findViewById(R.id.releaseDate);


        }

        public void bind(Movie movie) {
            String yyyy = movie.getReleaseDate().substring(0,4);
            String mm = movie.getReleaseDate().substring(5,7);
            String dd = movie.getReleaseDate().substring(8,10);
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverView());
            String imageURL;
            realeaseDate.setText("Release Date: \n" + mm + "/" + dd + "/" + yyyy);


            //if phone is in landscape
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                //then imageurl = backdrop image
                imageURL = movie.getBackDropPath();
            }else
                imageURL = movie.getPosterPath();

            //else image =poster image

            Glide.with(context).load(imageURL).into(ivPoster);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });

        }
    }
}
