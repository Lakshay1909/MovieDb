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
import easy.com.moviedbminor.Adapters.Tvshows;
import easy.com.moviedbminor.Model.Movie.MovieDetail;
import easy.com.moviedbminor.Model.Movie.PopularMovie;
import easy.com.moviedbminor.Model.Tv.Tv;
import easy.com.moviedbminor.Model.Tv.Tvdetail;
import easy.com.moviedbminor.Network.Moviedbinterface;
import easy.com.moviedbminor.Network.Retrofitinstance;
import easy.com.moviedbminor.R;
import easy.com.moviedbminor.Utils.IntentConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class View_All_Tv extends AppCompatActivity {

    RecyclerView recyclerView;
    private int movietype;
    ProgressBar progressBar;
    ArrayList<Tvdetail> Alltv;
    Tvshows Alltv_adapter;
    private int previoustotal=0,visiblethreshold=5;
    private int firstvisibleitem,visibleitemcount,totalitemcount;
    private boolean Loading=true;
    int PageNumber=1;
    Moviedbinterface moviedbinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__all__tv);
        Intent i=getIntent();
        movietype= i.getIntExtra(IntentConstants.View_All_Tv,-1);
        Alltv=new ArrayList<>();
        progressBar=(ProgressBar)findViewById(R.id.progressbartvAll);
        recyclerView=(RecyclerView)findViewById(R.id.View_All_Tv);
        if(movietype==-1)
            finish();
        switch (movietype) {
            case IntentConstants.Now_showing_Tvview:
                setTitle("On The Air");
                break;
            case IntentConstants.Popular_TvView:
                setTitle("Popular");
                break;
            case IntentConstants.Top_Rated_TvView:
                setTitle("Top_Rated");
                break;
            case IntentConstants.Upcoming_TvView:
                setTitle("Airing Today");
                break;
        }
        moviedbinterface= Retrofitinstance.getRetrofit();

      Alltv_adapter=new Tvshows(View_All_Tv.this, Alltv, new Tvshows.Tvclicklistener() {
          @Override
          public void itemclick(View v, int position) {
              Intent i=new Intent(View_All_Tv.this,Tv_Detail_Activity.class);
              i.putExtra(IntentConstants.Tv_id,Alltv.get(position).getId());
              startActivity(i);
          }
      });
        recyclerView.setAdapter(Alltv_adapter);
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
                    LoadInfinitetv(movietype);
                    Loading=true;
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        LoadInfinitetv(movietype);
    }

    private void LoadInfinitetv(int movietype) {
        switch(movietype)
        {
            case IntentConstants.Popular_TvView:
                Call<Tv> popularTvCall=moviedbinterface.getpopular("29213ce3aa2d1e8b18fb60d618ccec21",PageNumber++);
                popularTvCall.enqueue(new Callback<Tv>() {
                    @Override
                    public void onResponse(Call<Tv> call, Response<Tv> response) {
                        Tv populartv=response.body();
                        ArrayList<Tvdetail> details=populartv.getTvdetails();
                        onComplete(details);
                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<Tv> call, Throwable t) {

                    }
                });
                break;
            case IntentConstants.Now_showing_Tvview:
                Call<Tv> onTheairtvcall=moviedbinterface.getonair("29213ce3aa2d1e8b18fb60d618ccec21",PageNumber++);
                onTheairtvcall.enqueue(new Callback<Tv>() {
                    @Override
                    public void onResponse(Call<Tv> call, Response<Tv> response) {
                        Tv populartv=response.body();
                        ArrayList<Tvdetail> details=populartv.getTvdetails();
                        onComplete(details);
                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<Tv> call, Throwable t) {

                    }
                });
                break;
            case IntentConstants.Top_Rated_TvView:
                Call<Tv> topratedTvcall=moviedbinterface.gettoprated("29213ce3aa2d1e8b18fb60d618ccec21",PageNumber++);
                topratedTvcall.enqueue(new Callback<Tv>() {
                    @Override
                    public void onResponse(Call<Tv> call, Response<Tv> response) {
                        Tv populartv=response.body();
                        ArrayList<Tvdetail> details=populartv.getTvdetails();
                        onComplete(details);
                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<Tv> call, Throwable t) {

                    }
                });
                break;
            case IntentConstants.Upcoming_TvView:
                Call<Tv> Airingtodaytvcall=moviedbinterface.getairingtoday("29213ce3aa2d1e8b18fb60d618ccec21",PageNumber++);
                Airingtodaytvcall.enqueue(new Callback<Tv>() {
                    @Override
                    public void onResponse(Call<Tv> call, Response<Tv> response) {
                        Tv populartv=response.body();
                        ArrayList<Tvdetail> details=populartv.getTvdetails();
                        onComplete(details);
                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<Tv> call, Throwable t) {

                    }
                });
                break;

        }
    }
    private void onComplete(ArrayList<Tvdetail> moviedetail)
    {
        Alltv.addAll(moviedetail);
        Alltv_adapter.notifyDataSetChanged();
    }
}


