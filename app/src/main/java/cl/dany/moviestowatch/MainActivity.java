package cl.dany.moviestowatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cl.dany.moviestowatch.models.Movie;

public class MainActivity extends AppCompatActivity {
    private  List<Movie> movies ;
    public static final String MOVIE_ID = "cl.dany.moviestowatch.KEY.MOVIE_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText txtName = findViewById(R.id.txtName);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnLastMovie = findViewById(R.id.btnLastMovie);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userText = txtName.getText().toString();
                if(userText.trim().length() > 0)
                {
                    Movie newMovie = new Movie(userText,false);
                    newMovie.save();
                    getMovies();
                    txtName.setText("");
                    Toast.makeText(MainActivity.this, "Guardado!", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this, "Ingrese un nombre porfavor.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLastMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( movies.size() > 0) {
                    int listSize = movies.size();
                    Movie lastMovie = movies.get(listSize - 1);
                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    intent.putExtra(MOVIE_ID, lastMovie.getId());
                    startActivity(intent);
            }else
                {
                    Toast.makeText(MainActivity.this, "La lista está vacia!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private List<Movie> getMovies()
    {
         movies = Movie.find(Movie.class, "watched = 0");
        return movies;
    }

    @Override
    protected void onResume() {
        super.onResume();
        movies = getMovies();
    }
}
