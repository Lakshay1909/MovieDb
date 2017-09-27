package easy.com.moviedbminor.Model.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hp on 9/21/2017.
 */

public class MovieCastofPerson {
    private int id;
    @SerializedName("cast")
    ArrayList<Moviecast_Response> getmoviecast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Moviecast_Response> getGetmoviecast() {
        return getmoviecast;
    }

    public void setGetmoviecast(ArrayList<Moviecast_Response> getmoviecast) {
        this.getmoviecast = getmoviecast;
    }
}
