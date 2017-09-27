package easy.com.moviedbminor.Model.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import easy.com.moviedbminor.Model.Movie.MovieDetail;

/**
 * Created by Hp on 8/22/2017.
 */

public class PopularMovie {
   private int page;
   private int total_results;
   private int total_pages;
    @SerializedName("results")
   private ArrayList<MovieDetail> detail;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<MovieDetail> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<MovieDetail> detail) {
        this.detail = detail;
    }
}
