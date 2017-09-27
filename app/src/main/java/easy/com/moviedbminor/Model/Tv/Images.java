package easy.com.moviedbminor.Model.Tv;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hp on 9/23/2017.
 */

public class Images {
    private int id;
    @SerializedName("posters")
    private ArrayList<Posters> posters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Posters> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<Posters> posters) {
        this.posters = posters;
    }
}
