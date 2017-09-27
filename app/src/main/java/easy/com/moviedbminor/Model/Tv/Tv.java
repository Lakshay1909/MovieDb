package easy.com.moviedbminor.Model.Tv;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hp on 9/16/2017.
 */

public class Tv {
    @SerializedName("page")
    private int pagetv;
    @SerializedName("results")
    ArrayList<Tvdetail> tvdetails;
@SerializedName("total_results")
    private int total_resultstv;
    @SerializedName("total_pages")
    private int total_pagestv;

    public int getPagetv() {
        return pagetv;
    }

    public void setPagetv(int pagetv) {
        this.pagetv = pagetv;
    }

    public ArrayList<Tvdetail> getTvdetails() {
        return tvdetails;
    }

    public void setTvdetails(ArrayList<Tvdetail> tvdetails) {
        this.tvdetails = tvdetails;
    }

    public int getTotal_resultstv() {
        return total_resultstv;
    }

    public void setTotal_resultstv(int total_resultstv) {
        this.total_resultstv = total_resultstv;
    }

    public int getTotal_pagestv() {
        return total_pagestv;
    }

    public void setTotal_pagestv(int total_pagestv) {
        this.total_pagestv = total_pagestv;
    }
}
