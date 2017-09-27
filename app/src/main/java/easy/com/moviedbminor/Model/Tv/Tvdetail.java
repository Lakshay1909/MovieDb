package easy.com.moviedbminor.Model.Tv;

/**
 * Created by Hp on 9/16/2017.
 */

public class Tvdetail {
    private int vote_count;
    private int id;
    private double vote_average;
    private String name;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    public Tvdetail(int vote_count, int id, double vote_average, String name, String poster_path, String backdrop_path, String overview) {
        this.vote_count = vote_count;
        this.id = id;
        this.vote_average = vote_average;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
