package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=";
    private final String API_KEY = "0e09b6080c69447d580d9e8ecab704b4";
    public static final String TAG = "MainActivity";
    List<Movie> movies;


    MySQLConnectionClass connectionClass;
    Connection con;
    ResultSet rs;
    String name, str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();

        // Create the adapter
        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        // Set the adapter on a recycler view
        rvMovies.setAdapter(movieAdapter);
        // Set a layout Manager
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        // Create the AsynchClient
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL+ API_KEY, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray result = jsonObject.getJSONArray("results");
                    Log.i(TAG, "Results: " + result.toString());
                    movies.addAll(Movie.fromJsonArray(result));
                    movieAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Movies: " + movies.size());
                } catch (JSONException e) {
                    Log.e(TAG, "Hit json exception", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });



        connectionClass = new MySQLConnectionClass();
        connect();

    }

    public void connect() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                con = connectionClass.conn();
                if (con == null){
                    str = "Error in connection with MySql server";
                } else{
                    str = "Connected with MYSQL server";
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            runOnUiThread(()-> {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            });
        });
    }

    public void loginClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void btnClick(View view) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()-> {
            try{
                con = connectionClass.conn();
                String QUERY = "show tables from flixster_schema;";
                PreparedStatement stmt = con.prepareStatement(QUERY);
                ResultSet rs = stmt.executeQuery();
                StringBuilder bstr = new StringBuilder("Tables\n");
                while (rs.next()){
                    bstr.append(rs.getString("Tables_in_flixster_schema")).append("\n");
                }
                name = bstr.toString();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            runOnUiThread(()-> {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("MainActivity", name.toString());
            });
        });

    }
}