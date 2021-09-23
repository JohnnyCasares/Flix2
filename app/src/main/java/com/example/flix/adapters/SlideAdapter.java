package com.example.flix.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.flix.R;
import com.example.flix.models.Movie;

import java.util.List;

public class SlideAdapter extends PagerAdapter {

    private Context context;
    private List<Movie> movies;
    String imageURL;

    public SlideAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_item, null);
        imageURL = movies.get(position).getBackDropPath();


        ImageView slideImg = slideLayout.findViewById(R.id.slide_image);
        TextView slideTitle = slideLayout.findViewById(R.id.slide_title);
        Glide.with(context).load(imageURL).into(slideImg);
        slideTitle.setText(movies.get(position).getTitle().toString());

        container.addView(slideLayout);
        return  slideLayout;

    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
