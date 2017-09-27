package easy.com.moviedbminor.Model.Tv;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hp on 9/23/2017.
 */

public class SimilarTv {
    private int page;
    @SerializedName("results")
    private ArrayList<Tvdetail> similartv;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Tvdetail> getSimilartv() {
        return similartv;
    }

    public void setSimilartv(ArrayList<Tvdetail> similartv) {
        this.similartv = similartv;
    }
}
