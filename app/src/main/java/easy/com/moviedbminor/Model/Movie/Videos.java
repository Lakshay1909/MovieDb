package easy.com.moviedbminor.Model.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hp on 9/17/2017.
 */

public class Videos {
    private int id;
    @SerializedName("results")
    private ArrayList<Video_Details> videoDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Video_Details> getVideoDetails() {
        return videoDetails;
    }

    public void setVideoDetails(ArrayList<Video_Details> videoDetails) {
        this.videoDetails = videoDetails;
    }
}
