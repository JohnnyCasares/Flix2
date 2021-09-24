package com.example.flix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flix.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class slideYouTube extends YouTubeBaseActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyAXP0mCvCcGPtZNcBjkLvMeH3oP1hYHDDY";
    public static final String VIDEOS_URL ="https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";


    YouTubePlayerView youTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_youtube);

        youTubePlayer = findViewById(R.id.ytPlayer);
        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEOS_URL, movie.getMovieId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {

                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if(results.length()==0){
                        return;
                    }
                    String youtubeKey = results.getJSONObject(0).getString("key");
                    Log.d("DetailActivity",youtubeKey);
                    initializeYoutube(youtubeKey);

                } catch (JSONException e) {
                    Log.e("DetailActivity", "Failed to parse Json");
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });


    }
    private void initializeYoutube(final String youtubeKey) {
        youTubePlayer.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailActivity","OnInitializationSuccess");
                youTubePlayer.cueVideo(youtubeKey);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity","OnInitializationFailure");

            }
        });
    }

}