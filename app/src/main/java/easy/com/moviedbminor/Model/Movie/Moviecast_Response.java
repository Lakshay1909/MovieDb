package easy.com.moviedbminor.Model.Movie;

/**
 * Created by Hp on 9/21/2017.
 */

public class Moviecast_Response {
    private String character;
    private String credit_id;
    private String release_date;
    private String backdrop_path;
    private String poster_path;
    private String title;
    private int id;

    public Moviecast_Response(String character, String credit_id, String release_date, String backdrop_path, String poster_path, String title) {
        this.character = character;
        this.credit_id = credit_id;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.title = title;
    }

    public String getCharacter() {
        return character;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
