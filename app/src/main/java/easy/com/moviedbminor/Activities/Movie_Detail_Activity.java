package easy.com.moviedbminor.Activities;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import easy.com.moviedbminor.Adapters.Cast_Movie;
import easy.com.moviedbminor.Adapters.Crew_Movie;
import easy.com.moviedbminor.Adapters.Popularmovie;
import easy.com.moviedbminor.Adapters.Video;
import easy.com.moviedbminor.Model.Movie.Genres;
import easy.com.moviedbminor.Model.Movie.GenresList;
import easy.com.moviedbminor.Model.Movie.MovieCast;
import easy.com.moviedbminor.Model.Movie.MovieCrew;
import easy.com.moviedbminor.Model.Movie.MovieDetail;
import easy.com.moviedbminor.Model.Movie.Movie_All_Detail;
import easy.com.moviedbminor.Model.Movie.Movie_Credits;
import easy.com.moviedbminor.Model.Movie.PopularMovie;
import easy.com.moviedbminor.Model.Movie.Similar_Movies;
import easy.com.moviedbminor.Model.Movie.Video_Details;
import easy.com.moviedbminor.Model.Movie.Videos;
import easy.com.moviedbminor.Network.Moviedbinterface;
import easy.com.moviedbminor.Network.Retrofitinstance;
import easy.com.moviedbminor.R;
import easy.com.moviedbminor.Utils.Genres_Movie;
import easy.com.moviedbminor.Utils.IntentConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie_Detail_Activity extends AppCompatActivity {
    ArrayList<MovieCrew> movieCrews;
    ArrayList<MovieCast> movieCasts;
    ArrayList<MovieDetail> movieDetails;
    ArrayList<Video_Details> video_details;
    private Call<Movie_All_Detail> movie_all_detailCall;
    private Call<Videos> videosCall;
    private Call<Similar_Movies> popularMovieCall;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView imageView,imageView1;
    private Call<Movie_Credits> movie_credits_call;
    AppBarLayout appBarLayout;
    TextView rating,year,genredetail,title,overview,release_textview,runtime_textview;
    Toolbar toolbar;
    Popularmovie popularmovieadapter;
    Video videoadapter;
    Cast_Movie cast_movie_adapter;
    Crew_Movie crew_movie_adapter;
    Moviedbinterface moviedbinterface;
    ImageButton imageButton;
    int movie_id;
    RecyclerView recyclerView,recyclerView1,recyclerView2,recyclerView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__detail_);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        Intent i=getIntent();
        movie_id= i.getIntExtra(IntentConstants.Movie_Id,-1);
        Log.i("Id",movie_id+"");
        if(movie_id==-1)
            finish();
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar);
        rating=(TextView)findViewById(R.id.text_view_rating);
        imageView=(ImageView)findViewById(R.id.image_view_backdrop);
        imageView1=(ImageView)findViewById(R.id.image_view_poster);
        year=(TextView)findViewById(R.id.text_view_year_movie_detail);
        genredetail=(TextView)findViewById(R.id.text_view_genre_movie_detail);
        title=(TextView)findViewById(R.id.text_view_title_movie_detail);
        overview=(TextView)findViewById(R.id.overview_movie_detail);
        release_textview=(TextView)findViewById(R.id.text_view_details_movie_detail_release);
        runtime_textview=(TextView)findViewById(R.id.text_view_details_movie_detail_runtime);
        imageButton=(ImageButton)findViewById(R.id.image_button_share_movie_detail);
        //Recycler Views
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_trailers_movie_detail);
        recyclerView1=(RecyclerView)findViewById(R.id.recycler_view_cast_movie_detail);
        recyclerView2=(RecyclerView)findViewById(R.id.recycler_view_crew_movie_detail);
        recyclerView3=(RecyclerView)findViewById(R.id.recycler_view_similar_movie_detail);
        //Array List
        movieCrews=new ArrayList<>();
        movieCasts=new ArrayList<>();
        movieDetails=new ArrayList<>();
        video_details=new ArrayList<>();


        //Adapters
        popularmovieadapter=new Popularmovie(Movie_Detail_Activity.this, movieDetails, new Popularmovie.Movieclicklistener() {
            @Override
            public void itemclick(View v, int position) {
                Intent i=new Intent(Movie_Detail_Activity.this,Movie_Detail_Activity.class);
                i.putExtra(IntentConstants.Movie_Id,movieDetails.get(position).getId());
                startActivity(i);
            }
        });
        videoadapter=new Video(video_details,Movie_Detail_Activity.this);
        cast_movie_adapter=new Cast_Movie(Movie_Detail_Activity.this, movieCasts, new Cast_Movie.Castclicklistener() {
            @Override
            public void Click(View v, int position) {
                Intent i=new Intent(Movie_Detail_Activity.this,Profile_detail.class);
                i.putExtra(IntentConstants.Cast_Id,movieCasts.get(position).getId());
                startActivity(i);
            }
        });
        crew_movie_adapter=new Crew_Movie(Movie_Detail_Activity.this, movieCrews, new Crew_Movie.Crewclicklistener() {
            @Override
            public void Click(View v, int position) {
                Intent i=new Intent(Movie_Detail_Activity.this,Profile_detail.class);
                i.putExtra(IntentConstants.Cast_Id,movieCrews.get(position).getId());
                startActivity(i);
            }
        });
        recyclerView.setAdapter(videoadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Movie_Detail_Activity.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView1.setAdapter(cast_movie_adapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(Movie_Detail_Activity.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        recyclerView2.setAdapter(crew_movie_adapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(Movie_Detail_Activity.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        recyclerView3.setAdapter(popularmovieadapter);
        recyclerView3.setLayoutManager(new LinearLayoutManager(Movie_Detail_Activity.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        moviedbinterface= Retrofitinstance.getRetrofit();
        movie_all_detailCall=moviedbinterface.getmoviedetail(movie_id,IntentConstants.Api_Key);
        movie_all_detailCall.enqueue(new Callback<Movie_All_Detail>() {
            @Override
            public void onResponse(Call<Movie_All_Detail> call, Response<Movie_All_Detail> response) {
                Log.i("All_moviedetail",response.code()+"");
                final Movie_All_Detail moviedetails=response.body();
                Picasso.with(Movie_Detail_Activity.this).load(IntentConstants.Images_Base_Url+moviedetails.getBackdrop_path()).into(imageView);
                Picasso.with(Movie_Detail_Activity.this).load(IntentConstants.Images_Base_Url_185+moviedetails.getPoster_path()).into(imageView1);
                setGenres(moviedetails.getGenres());
                setYear(moviedetails.getRelease_date());
                title.setText(moviedetails.getTitle());
                overview.setText(moviedetails.getOverview());
                rating.setText(moviedetails.getVote_average()+"");
                setRuntime(moviedetails.getRelease_date(),moviedetails.getRuntime());
                setTrailers();
                setCastsAndCrew();
                setSimilar();
                appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if(appBarLayout.getTotalScrollRange()+verticalOffset==0)
                          collapsingToolbarLayout.setTitle(moviedetails.getTitle());
                        else
                            collapsingToolbarLayout.setTitle("");
                    }
                });
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent movieshare=new Intent(Intent.ACTION_SEND);
                        movieshare.setType("text/plain");
                        String extra="";
                        if(moviedetails.getTitle()!=null) extra+=moviedetails.getTitle()+" \n";
                        if(moviedetails.getTagline()!=null) extra+=moviedetails.getTagline()+" \n";
                        if(moviedetails.getRelease_date()!=null) extra+=moviedetails.getRelease_date()+ "\n";
                        if(moviedetails.getImdb_id()!=null) extra+=IntentConstants.Imdb_Base_url+moviedetails.getImdb_id();
                        movieshare.putExtra(Intent.EXTRA_TEXT,extra);
                        startActivity(movieshare);
                    }
                });
            }

            @Override
            public void onFailure(Call<Movie_All_Detail> call, Throwable t) {
                Log.i("All_moviedetail",t.getMessage());
            }
        });



    }

    private void setSimilar() {
        popularMovieCall=moviedbinterface.getSimilarmovies(movie_id,IntentConstants.Api_Key);
        popularMovieCall.enqueue(new Callback<Similar_Movies>() {
            @Override
            public void onResponse(Call<Similar_Movies> call, Response<Similar_Movies> response) {
                Log.i("TagDetail",response.code()+"");
                Similar_Movies similar_movie=response.body();
                ArrayList<MovieDetail> movie=similar_movie.getSimilarmovies();
                onMoviecomplete(movie);
            }

            @Override
            public void onFailure(Call<Similar_Movies> call, Throwable t) {

            }
        });
    }

    private void onMoviecomplete(ArrayList<MovieDetail> movie) {
        movieDetails.clear();
        movieDetails.addAll(movie);
        popularmovieadapter.notifyDataSetChanged();
    }

    private void setCastsAndCrew()
    {
        movie_credits_call=moviedbinterface.getcredits(movie_id,IntentConstants.Api_Key);
        movie_credits_call.enqueue(new Callback<Movie_Credits>() {
            @Override
            public void onResponse(Call<Movie_Credits> call, Response<Movie_Credits> response) {
                Log.i("TagDetail",response.code()+"");
                Movie_Credits movie_credit=response.body();
                ArrayList<MovieCast> movie_cast=movie_credit.getMovieCasts();
                ArrayList<MovieCrew> movie_crew=movie_credit.getMovieCrews();
                onCrewcomplete(movie_crew);
                onCastcomplete(movie_cast);
            }

            @Override
            public void onFailure(Call<Movie_Credits> call, Throwable t) {

            }
        });
    }

    private void onCastcomplete(ArrayList<MovieCast> movie_cast) {
        movieCasts.clear();
        movieCasts.addAll(movie_cast);
        cast_movie_adapter.notifyDataSetChanged();
    }
    private void onCrewcomplete(ArrayList<MovieCrew> movie_crew) {
        movieCrews.clear();
        movieCrews.addAll(movie_crew);
        crew_movie_adapter.notifyDataSetChanged();
    }
    private void setTrailers() {
        videosCall=moviedbinterface.getvideos(movie_id,IntentConstants.Api_Key);
        videosCall.enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                Log.i("TagDetail",response.code()+"");
                Videos video=response.body();
                ArrayList<Video_Details> video_detail=video.getVideoDetails();
                onVideoComplete(video_detail);
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {

            }
        });

    }

    private void onVideoComplete(ArrayList<Video_Details> video_detail) {
        video_details.clear();
        video_details.addAll(video_detail);
        videoadapter.notifyDataSetChanged();
    }

    private void setRuntime(String release_date, int runtime) {
        String release="";
        String run_time_movie="";
        if(release_date!=null && !release_date.trim().isEmpty())
        {
            SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2=new SimpleDateFormat("MMM d,yyyy");
            try {
                Date date=sdf1.parse(release_date);
                release+=sdf2.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(runtime!=0)
        {
            if(runtime<60)
            {
                run_time_movie+=runtime+" min(s)";
            }
            else
            {
                run_time_movie+=runtime/60 +" hr " +runtime%60 +" mins";
            }
        }
        release_textview.setText(release);
        runtime_textview.setText(run_time_movie);
    }

    private void setYear(String release_date) {
        if (release_date != null && !release_date.trim().isEmpty()) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
            try {
                Date releaseDate = sdf1.parse(release_date);
                year.setText(sdf2.format(releaseDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            year.setText("");
        }
    }

    private void setGenres(ArrayList<Genres> genres) {
        String genre="";
        if(genres!=null)
        {
            for(int i=0;i<genres.size();i++)
            {
                if(genres.get(i)==null) continue;
                if(i==genres.size()-1)
                {
                    genre=genre.concat(genres.get(i).getName());
                }
                else
                {
                    genre=genre.concat(genres.get(i).getName()+", ");
                }
            }
        }
        genredetail.setText(genre);
    }
    
}
