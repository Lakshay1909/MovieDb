package easy.com.moviedbminor.Model.Movie;

/**
 * Created by Hp on 9/21/2017.
 */

public class Tvcast_Response {
    private String credit_id;
    private String original_name;
    private int id;
    private String character;
    private String name;
    private String poster_path;
    private String backdrop_path;

    public Tvcast_Response(String credit_id, String original_name, int id, String character, String name, String poster_path, String backdrop_path) {
        this.credit_id = credit_id;
        this.original_name = original_name;
        this.id = id;
        this.character = character;
        this.name = name;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
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
}
