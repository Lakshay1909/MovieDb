package easy.com.moviedbminor.Network;

import easy.com.moviedbminor.Model.Movie.GenresList;
import easy.com.moviedbminor.Model.Movie.MovieCastofPerson;
import easy.com.moviedbminor.Model.Movie.Movie_All_Detail;
import easy.com.moviedbminor.Model.Movie.Movie_Credits;
import easy.com.moviedbminor.Model.Movie.Person;
import easy.com.moviedbminor.Model.Movie.PopularMovie;
import easy.com.moviedbminor.Model.Movie.Similar_Movies;
import easy.com.moviedbminor.Model.Movie.TvcastofPerson;
import easy.com.moviedbminor.Model.Movie.Videos;
import easy.com.moviedbminor.Model.Search.SearchResponse;
import easy.com.moviedbminor.Model.Tv.Images;
import easy.com.moviedbminor.Model.Tv.Personimages;
import easy.com.moviedbminor.Model.Tv.Seasondetails;
import easy.com.moviedbminor.Model.Tv.SimilarTv;
import easy.com.moviedbminor.Model.Tv.Tv;
import easy.com.moviedbminor.Model.Tv.Tv_All_Detail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hp on 8/22/2017.
 */

public interface Moviedbinterface {
    @GET("movie/popular")
   Call<PopularMovie> getpopularmovies(@Query("api_key") String apikey,@Query("page") int page);
    @GET("movie/upcoming")
    Call<PopularMovie> getupcomingmovies(@Query("api_key") String apikey,@Query("page") int page);
    @GET("movie/now_playing")
    Call<PopularMovie> getnowplayingmovies(@Query("api_key") String apikey,@Query("page") int page);
    @GET("movie/top_rated")
    Call<PopularMovie> gettopratedmovies(@Query("api_key") String apikey,@Query("page") int page);
   @GET("genre/movie/list")
   Call<GenresList> getgenreslist(@Query("api_key") String apikey);
    @GET("movie/{id}/similar")
    Call<Similar_Movies> getSimilarmovies(@Path("id") int movieId,@Query("api_key") String apikey);
    @GET("movie/{id}")
    Call<Movie_All_Detail> getmoviedetail(@Path("id") int movieId,@Query("api_key") String apikey);
    @GET("movie/{movie_id}/credits")
    Call<Movie_Credits> getcredits(@Path("movie_id") int movieId,@Query("api_key") String apikey);
    @GET("movie/{movie_id}/videos")
    Call<Videos> getvideos(@Path("movie_id") int movieId,@Query("api_key") String apikey);
    @GET("person/{person_id}")
    Call<Person> getpersondetails(@Path("person_id") int id,@Query("api_key") String apikey);
    @GET("person/{person_id}/movie_credits")
    Call<MovieCastofPerson> getmoviecast(@Path("person_id") int id,@Query("api_key") String apikey);
    @GET("person/{person_id}/tv_credits")
    Call<TvcastofPerson> gettvcast(@Path("person_id") int id,@Query("api_key") String apikey);
    @GET("movie/{movie_id}/images")
    Call<Images> getmovieimages(@Path("movie_id") int id,@Query("api_key") String apikey);
    @GET("person/{person_id}/images")
    Call<Personimages> getpersonimages(@Path("person_id") int id, @Query("api_key") String apikey);
     @GET("search/multi")
     Call<SearchResponse> getsearchitems(@Query("api_key") String apikey, @Query("query") String query, @Query("page") int page);
    //TV
    @GET("tv/airing_today")
    Call<Tv> getairingtoday(@Query("api_key") String apikey, @Query("page") int page);
    @GET("tv/on_the_air")
    Call<Tv> getonair(@Query("api_key") String apikey, @Query("page") int page);
    @GET("tv/top_rated")
    Call<Tv> gettoprated(@Query("api_key") String apikey, @Query("page") int page);
    @GET("tv/popular")
    Call<Tv> getpopular(@Query("api_key") String apikey, @Query("page") int page);
    @GET("tv/{tv_id}")
    Call<Tv_All_Detail> gettvdetails(@Path("tv_id") int id,@Query("api_key") String apikey);
    @GET("tv/{tv_id}/videos")
    Call<Videos> gettvvideos(@Path("tv_id") int id,@Query("api_key") String apikey);
    @GET("tv/{tv_id}/similar")
    Call<SimilarTv> getsimilartvshows(@Path("tv_id") int id, @Query("api_key") String apikey);
    @GET("tv/{tv_id}/images")
    Call<Images> getimages(@Path("tv_id") int id, @Query("api_key") String apikey);
    @GET("tv/{tv_id}/season/{season_number}")
    Call<Seasondetails> getseasons(@Path("tv_id") int id,@Query("api_key") String apikey);
 @GET("tv/{tv_id}/season/{season_number}/images")
  Call<Images> getseasonimages(@Path("tv_id") int id,@Path("season_number") int season,@Query("api_key") String apikey);
    @GET("tv/{tv_id}/credits")
    Call<Movie_Credits> gettvcredits(@Path("tv_id") int id,@Query("api_key") String apikey);
}
