package easy.com.moviedbminor.Model.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import easy.com.moviedbminor.Model.Movie.Genres;

/**
 * Created by Hp on 9/17/2017.
 */

public class GenresList {
    @SerializedName("genres")
    ArrayList<Genres> genresArrayList;
    public ArrayList<Genres> getGenresArrayList() {
        return genresArrayList;
    }

    public void setGenresArrayList(ArrayList<Genres> genresArrayList) {
        this.genresArrayList = genresArrayList;
    }


}
