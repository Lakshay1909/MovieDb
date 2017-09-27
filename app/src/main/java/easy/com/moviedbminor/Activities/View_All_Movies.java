package easy.com.moviedbminor.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import easy.com.moviedbminor.Adapters.Popularmovie;
import easy.com.moviedbminor.Model.Movie.MovieDetail;
import easy.com.moviedbminor.Model.Movie.PopularMovie;
import easy.com.moviedbminor.Network.Moviedbinterface;
import easy.com.moviedbminor.Network.Retrofitinstance;
import easy.com.moviedbminor.R;
import easy.com.moviedbminor.Utils.IntentConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class View_All_Movies extends AppCompatActivity {
    RecyclerView recyclerView;
    private int movietype;
    ProgressBar progressBar;
    ArrayList<MovieDetail> Allmovies;
    Popularmovie Allmovie_adapter;
    private int previoustotal=0,visiblethreshold=5;
    private int firstvisibleitem,visibleitemcount,totalitemcount;
    private boolean Loading=true;
    int PageNumber=1;
    Moviedbinterface moviedbinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__all__movies);
        Intent i=getIntent();
       movietype= i.getIntExtra(IntentConstants.View_All_Movies,-1);
        Allmovies=new ArrayList<>();
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        recyclerView=(RecyclerView)findViewById(R.id.View_All_Movies);
        if(movietype==-1)
            finish();
        switch (movietype) {
            case IntentConstants.Now_showing_view:
                setTitle("Now_Playing_Movies");
                break;
            case IntentConstants.Popular_View:
                setTitle("Popular_Movies");
                break;
            case IntentConstants.Top_Rated_View:
                setTitle("Top_Rated_Movies");
                break;
            case IntentConstants.Upcoming_View:
                setTitle("Upcoming_Movies");
                break;
        }
        moviedbinterface= Retrofitinstance.getRetrofit();

        Allmovie_adapter=new Popularmovie(this, Allmovies, new Popularmovie.Movieclicklistener() {
          @Override
          public void itemclick(View v, int position) {
//              Toast.makeText(View_All_Movies.this,"Hey",Toast.LENGTH_SHORT).show();
              Intent i=new Intent(View_All_Movies.this,Movie_Detail_Activity.class);
              i.putExtra(IntentConstants.Movie_Id,Allmovies.get(position).getId());
              startActivity(i);
          }
      });
        recyclerView.setAdapter(Allmovie_adapter);
        final GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                visibleitemcount=gridLayoutManager.getChildCount();
                totalitemcount=gridLayoutManager.getItemCount();
                firstvisibleitem=gridLayoutManager.findFirstVisibleItemPosition();
                if(Loading)
                {
                    if(totalitemcount>previoustotal)
                    {
                        Loading=false;
                        previoustotal=totalitemcount;
                    }
                }
                if(!Loading && (totalitemcount-visibleitemcount)<=(firstvisibleitem+visiblethreshold))
                {
                    Log.i("Tag","End reached");
                    LoadInfinitemovies(movietype);
                    Loading=true;
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        LoadInfinitemovies(movietype);
    }

    private void LoadInfinitemovies(int movietype) {
        switch(movietype)
        {
            case IntentConstants.Popular_View:
                Call<PopularMovie> popularMovieCall=moviedbinterface.getpopularmovies("29213ce3aa2d1e8b18fb60d618ccec21",PageNumber++);
                    popularMovieCall.enqueue(new Callback<PopularMovie>() {
                        @Override
                        public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                            PopularMovie popularMovie=response.body();
                            ArrayList<MovieDetail> details=popularMovie.getDetail();
                            onComplete(details);
                            progressBar.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<PopularMovie> call, Throwable t) {

                        }
                    });
                break;
            case IntentConstants.Now_showing_view:
                Call<PopularMovie> nowplayingCall=moviedbinterface.getnowplayingmovies("29213ce3aa2d1e8b18fb60d618ccec21",PageNumber++);
                nowplayingCall.enqueue(new Callback<PopularMovie>() {
                    @Override
                    public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                        PopularMovie popularMovie=response.body();
                        ArrayList<MovieDetail> details=popularMovie.getDetail();
                        onComplete(details);
                        progressBar.setVisibility(View.GONE);



                    }

                    @Override
                    public void onFailure(Call<PopularMovie> call, Throwable t) {

                    }
                });
                break;
            case IntentConstants.Top_Rated_View:
                Call<PopularMovie> TopratedCall=moviedbinterface.gettopratedmovies("29213ce3aa2d1e8b18fb60d618ccec21",PageNumber++);
                TopratedCall.enqueue(new Callback<PopularMovie>() {
                    @Override
                    public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                        PopularMovie popularMovie=response.body();
                        ArrayList<MovieDetail> details=popularMovie.getDetail();
                        onComplete(details);
                        progressBar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<PopularMovie> call, Throwable t) {

                    }
                });
                break;
            case IntentConstants.Upcoming_View:
                Call<PopularMovie> UpcomingCall=moviedbinterface.getupcomingmovies("29213ce3aa2d1e8b18fb60d618ccec21",PageNumber++);
                UpcomingCall.enqueue(new Callback<PopularMovie>() {
                    @Override
                    public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                        PopularMovie popularMovie=response.body();
                        ArrayList<MovieDetail> details=popularMovie.getDetail();
                        onComplete(details);
                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<PopularMovie> call, Throwable t) {

                    }
                });
                break;

        }
    }
    private void onComplete(ArrayList<MovieDetail> moviedetail)
    {
        Allmovies.addAll(moviedetail);
        Allmovie_adapter.notifyDataSetChanged();
    }
}
