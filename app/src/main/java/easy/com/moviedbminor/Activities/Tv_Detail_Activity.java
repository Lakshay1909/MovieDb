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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import easy.com.moviedbminor.Adapters.Cast_Movie;
import easy.com.moviedbminor.Adapters.Crew_Movie;
import easy.com.moviedbminor.Adapters.Tvshows;
import easy.com.moviedbminor.Adapters.Video;
import easy.com.moviedbminor.Model.Movie.Genres;
import easy.com.moviedbminor.Model.Movie.MovieCast;
import easy.com.moviedbminor.Model.Movie.MovieCrew;
import easy.com.moviedbminor.Model.Movie.MovieDetail;
import easy.com.moviedbminor.Model.Movie.Movie_Credits;
import easy.com.moviedbminor.Model.Movie.Similar_Movies;
import easy.com.moviedbminor.Model.Movie.Video_Details;
import easy.com.moviedbminor.Model.Movie.Videos;
import easy.com.moviedbminor.Model.Tv.Network_response;
import easy.com.moviedbminor.Model.Tv.SimilarTv;
import easy.com.moviedbminor.Model.Tv.Tv_All_Detail;
import easy.com.moviedbminor.Model.Tv.Tvdetail;
import easy.com.moviedbminor.Network.Moviedbinterface;
import easy.com.moviedbminor.Network.Retrofitinstance;
import easy.com.moviedbminor.R;
import easy.com.moviedbminor.Utils.IntentConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tv_Detail_Activity extends AppCompatActivity {
    ArrayList<MovieCrew> tvCrews;
    ArrayList<MovieCast> tvCasts;
    ArrayList<Tvdetail> tvDetails;
    ArrayList<Video_Details> video_details;
    private Call<Tv_All_Detail> tv_all_detailCall;
    private Call<Videos> videosCall;
    private Call<SimilarTv> tvCall;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView imageView,imageView1;
    private Call<Movie_Credits> tv_credits_call;
    AppBarLayout appBarLayout;
    TextView rating,year,genredetail,title,overview,origin_country_text,runtime_textview,first_air_date,episodes,seasons,network;
    Toolbar toolbar;
    Tvshows tvadapter;
    Video videoadapter;
    Cast_Movie cast_tv_adapter;
    Crew_Movie crew_tv_adapter;
    Moviedbinterface moviedbinterface;
    int tv_id;
    RecyclerView recyclerView,recyclerView1,recyclerView2,recyclerView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv__detail_);
        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar_tv_detail);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout_tv_detail);
        toolbar=(Toolbar)findViewById(R.id.toolbar_tv_detail);
        setSupportActionBar(toolbar);
        setTitle("");
        imageView=(ImageView)findViewById(R.id.image_view_backdrop_tv_detail);
        imageView1=(ImageView)findViewById(R.id.image_view_poster_tv_detail);
        title=(TextView)findViewById(R.id.text_view_title_tv_detail);
        year=(TextView)findViewById(R.id.text_view_year_tv_detail);
        genredetail=(TextView)findViewById(R.id.text_view_genre_tv_detail);
        rating=(TextView)findViewById(R.id.text_view_rating_tv_detail);
        overview=(TextView)findViewById(R.id.overview_tv_detail);
        first_air_date=(TextView)findViewById(R.id.text_view_details_tv_detail_release);
        runtime_textview=(TextView)findViewById(R.id.text_view_details_tv_detail_runtime);
        origin_country_text=(TextView)findViewById(R.id.text_view_details_tv_detail_origin_country);
        episodes=(TextView)findViewById(R.id.text_view_details_tv_detail_number_episodes);
        seasons=(TextView)findViewById(R.id.text_view_details_tv_detail_number_seasons);
        network=(TextView)findViewById(R.id.text_view_details_tv_detail_network_info);

        //Recycler Views
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_trailers_tv_detail);
        recyclerView1=(RecyclerView)findViewById(R.id.recycler_view_cast_tv_detail);
        recyclerView2=(RecyclerView)findViewById(R.id.recycler_view_crew_tv_detail);
        recyclerView3=(RecyclerView)findViewById(R.id.recycler_view_similar_tv_detail);
        Intent i=getIntent();
        tv_id= i.getIntExtra(IntentConstants.Tv_id,-1);
        if(tv_id==-1)
            finish();
        //Arraylist
        tvCasts=new ArrayList<>();
        tvCrews=new ArrayList<>();
        tvDetails=new ArrayList<>();
        video_details=new ArrayList<>();

       tvadapter=new Tvshows(Tv_Detail_Activity.this, tvDetails, new Tvshows.Tvclicklistener() {
           @Override
           public void itemclick(View v, int position) {
               Intent i=new Intent(Tv_Detail_Activity.this,Tv_Detail_Activity.class);
               i.putExtra(IntentConstants.Tv_id,tvDetails.get(position).getId());
               startActivity(i);
           }
       });
        videoadapter=new Video(video_details,Tv_Detail_Activity.this);
        cast_tv_adapter=new Cast_Movie(Tv_Detail_Activity.this, tvCasts, new Cast_Movie.Castclicklistener() {
            @Override
            public void Click(View v, int position) {
                Intent i=new Intent(Tv_Detail_Activity.this,Profile_detail.class);
                i.putExtra(IntentConstants.Cast_Id,tvCasts.get(position).getId());
                startActivity(i);
            }
        });
        crew_tv_adapter=new Crew_Movie(Tv_Detail_Activity.this, tvCrews, new Crew_Movie.Crewclicklistener() {
            @Override
            public void Click(View v, int position) {
                Intent i=new Intent(Tv_Detail_Activity.this,Profile_detail.class);
                i.putExtra(IntentConstants.Cast_Id,tvCrews.get(position).getId());
                startActivity(i);
            }
        });
        recyclerView.setAdapter(videoadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Tv_Detail_Activity.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView1.setAdapter(cast_tv_adapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(Tv_Detail_Activity.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        recyclerView2.setAdapter(crew_tv_adapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(Tv_Detail_Activity.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        recyclerView3.setAdapter(tvadapter);
        recyclerView3.setLayoutManager(new LinearLayoutManager(Tv_Detail_Activity.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        moviedbinterface= Retrofitinstance.getRetrofit();
       tv_all_detailCall= moviedbinterface.gettvdetails(tv_id,IntentConstants.Api_Key);
        tv_all_detailCall.enqueue(new Callback<Tv_All_Detail>() {
            @Override
            public void onResponse(Call<Tv_All_Detail> call, Response<Tv_All_Detail> response) {
                Log.i("Tvtag",response.code()+"");
                final Tv_All_Detail tvdetail=response.body();
                Picasso.with(Tv_Detail_Activity.this).load(IntentConstants.Images_Base_Url+tvdetail.getBackdrop_path()).into(imageView);
                Picasso.with(Tv_Detail_Activity.this).load(IntentConstants.Images_Base_Url_185+tvdetail.getPoster_path()).into(imageView1);
                setGenres(tvdetail.getGenreslist());
                setYear(tvdetail.getFirst_air_date());
                setRuntime(tvdetail.getEpisode_run_time());
                setDetails(tvdetail.getNetworks(),tvdetail.getOrigin_country());
                setDate(tvdetail.getFirst_air_date());
                title.setText(tvdetail.getName());
                rating.setText(tvdetail.getVote_average()+"");
                overview.setText(tvdetail.getOverview());
                episodes.setText(tvdetail.getNumber_of_episodes()+"");
                seasons.setText(tvdetail.getNumber_of_seasons()+"");
                setTrailers();
                setCastsAndCrew();
                setSimilar();
                appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if(appBarLayout.getTotalScrollRange()+verticalOffset==0)
                            collapsingToolbarLayout.setTitle(tvdetail.getName());
                        else
                            collapsingToolbarLayout.setTitle("");
                    }
                });



            }

            @Override
            public void onFailure(Call<Tv_All_Detail> call, Throwable t) {
                Log.i("Tvtag",t.getMessage());
            }
        });



        }
    private void setSimilar() {
        tvCall=moviedbinterface.getsimilartvshows(tv_id,IntentConstants.Api_Key);
      tvCall.enqueue(new Callback<SimilarTv>() {
          @Override
          public void onResponse(Call<SimilarTv> call, Response<SimilarTv> response) {
              SimilarTv similar=response.body();
              ArrayList<Tvdetail> detail=similar.getSimilartv();
              onTvcomplete(detail);
          }

          @Override
          public void onFailure(Call<SimilarTv> call, Throwable t) {

          }
      });
    }

    private void onTvcomplete(ArrayList<Tvdetail> movie) {
        tvDetails.clear();
        tvDetails.addAll(movie);
        tvadapter.notifyDataSetChanged();
    }
    private void setCastsAndCrew()
    {
        tv_credits_call=moviedbinterface.gettvcredits(tv_id,IntentConstants.Api_Key);
        tv_credits_call.enqueue(new Callback<Movie_Credits>() {
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
        tvCasts.clear();
        tvCasts.addAll(movie_cast);
        cast_tv_adapter.notifyDataSetChanged();
    }
    private void onCrewcomplete(ArrayList<MovieCrew> movie_crew) {
        tvCrews.clear();
        tvCrews.addAll(movie_crew);
        crew_tv_adapter.notifyDataSetChanged();
    }
    private void setTrailers() {
        videosCall=moviedbinterface.gettvvideos(tv_id,IntentConstants.Api_Key);
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
    private void setDate(String release_date) {
        String release="";
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
        first_air_date.setText(release);
    }

    private void setDetails(ArrayList<Network_response> networks, ArrayList<String> origin_country) {
        String network_information="";
        String country="";
        if(origin_country!=null && !origin_country.isEmpty()) {
            for (String s : origin_country) {
                if (s == null || s.trim().isEmpty()) continue;
                country += s + " ";

            }
            
        }
        origin_country_text.setText(country);
        if(networks!=null && !networks.isEmpty())
        {
            for(Network_response n:networks)
            {
                if(n==null || n.getName()==null || n.getName().trim().isEmpty()) continue;
                network_information+=n.getName()+" ";
            }

        }
        network.setText(network_information);
    }

    private void setRuntime(ArrayList<Integer> episode_run_time) {
       String runtime="";
        if(episode_run_time!=null && !episode_run_time.isEmpty() && episode_run_time.get(0)!=null)
        {
            if(episode_run_time.get(0)<60)
                runtime+=episode_run_time.get(0) + " min(s)";
            else
                runtime+=episode_run_time.get(0)/60 + " hr " + episode_run_time.get(0)%60 +" min(s)";
        }
        runtime_textview.setText(runtime);
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

