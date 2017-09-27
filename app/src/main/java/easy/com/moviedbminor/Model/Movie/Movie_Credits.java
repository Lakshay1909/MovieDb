package easy.com.moviedbminor.Model.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hp on 9/17/2017.
 */

public class Movie_Credits {
    private int id;
    @SerializedName("cast")
    ArrayList<MovieCast> movieCasts;
    @SerializedName("crew")
    ArrayList<MovieCrew> movieCrews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<MovieCast> getMovieCasts() {
        return movieCasts;
    }

    public void setMovieCasts(ArrayList<MovieCast> movieCasts) {
        this.movieCasts = movieCasts;
    }

    public ArrayList<MovieCrew> getMovieCrews() {
        return movieCrews;
    }

    public void setMovieCrews(ArrayList<MovieCrew> movieCrews) {
        this.movieCrews = movieCrews;
    }
}
