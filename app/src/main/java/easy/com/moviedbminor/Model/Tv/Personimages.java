package easy.com.moviedbminor.Model.Tv;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hp on 9/24/2017.
 */

public class Personimages {
    private  int id;
    @SerializedName("profiles")
    private ArrayList<Posters> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Posters> getImages() {
        return images;
    }

    public void setImages(ArrayList<Posters> images) {
        this.images = images;
    }
}
