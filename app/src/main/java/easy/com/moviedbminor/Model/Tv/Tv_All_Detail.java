package easy.com.moviedbminor.Model.Tv;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import easy.com.moviedbminor.Model.Movie.Genres;

/**
 * Created by Hp on 9/23/2017.
 */

public class Tv_All_Detail {
    private String backdrop_path;
    private ArrayList<Integer> episode_run_time;
    private String first_air_date;
    @SerializedName("genres")
    private ArrayList<Genres> genreslist;
    private int id;
    private ArrayList<String> languages;
    private String name;
    private int number_of_episodes;
    private int number_of_seasons;
    private ArrayList<String> origin_country;
    private String original_language;
    private String original_name;
    private String overview;
    private String poster_path;
    @SerializedName("seasons")
    private ArrayList<Season> season;
    @SerializedName("networks")
    private ArrayList<Network_response> networks;
    private double vote_average;
    private int vote_count;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public ArrayList<Integer> getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(ArrayList<Integer> episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public ArrayList<Genres> getGenreslist() {
        return genreslist;
    }

    public void setGenreslist(ArrayList<Genres> genreslist) {
        this.genreslist = genreslist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public ArrayList<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(ArrayList<String> origin_country) {
        this.origin_country = origin_country;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public ArrayList<Season> getSeason() {
        return season;
    }

    public void setSeason(ArrayList<Season> season) {
        this.season = season;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public ArrayList<Network_response> getNetworks() {
        return networks;
    }

    public void setNetworks(ArrayList<Network_response> networks) {
        this.networks = networks;
    }
}
