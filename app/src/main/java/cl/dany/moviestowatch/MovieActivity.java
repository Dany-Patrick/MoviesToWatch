package cl.dany.moviestowatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import cl.dany.moviestowatch.models.Movie;

public class MovieActivity extends AppCompatActivity {
    private Movie movie;
    private CheckBox viewCb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
         viewCb = findViewById(R.id.viewCb);
        long lastId = getIntent().getLongExtra(MainActivity.MOVIE_ID, 0);
        movie= Movie.findById(Movie.class, lastId);
        getSupportActionBar().setTitle(movie.getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        movie.setWatched(viewCb.isChecked());
        movie.save();
    }
}
