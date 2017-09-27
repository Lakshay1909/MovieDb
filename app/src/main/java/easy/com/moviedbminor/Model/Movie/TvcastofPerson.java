package easy.com.moviedbminor.Model.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hp on 9/21/2017.
 */

public class TvcastofPerson {
    private int id;
    @SerializedName("cast")
    private ArrayList<Tvcast_Response> gettvresponse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Tvcast_Response> getGettvresponse() {
        return gettvresponse;
    }

    public void setGettvresponse(ArrayList<Tvcast_Response> gettvresponse) {
        this.gettvresponse = gettvresponse;
    }
}
