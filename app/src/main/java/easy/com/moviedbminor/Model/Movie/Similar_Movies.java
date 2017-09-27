package easy.com.moviedbminor.Model.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hp on 9/17/2017.
 */

public class Similar_Movies {
    private int page;
    @SerializedName("results")
    private ArrayList<MovieDetail> similarmovies;
    private int total_pages;
    private int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<MovieDetail> getSimilarmovies() {
        return similarmovies;
    }

    public void setSimilarmovies(ArrayList<MovieDetail> similarmovies) {
        this.similarmovies = similarmovies;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
