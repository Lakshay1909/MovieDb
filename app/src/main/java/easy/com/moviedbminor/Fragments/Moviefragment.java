package easy.com.moviedbminor.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import easy.com.moviedbminor.Activities.Movie_Detail_Activity;
import easy.com.moviedbminor.Adapters.Popularmovie;
import easy.com.moviedbminor.Adapters.Upcoming;
import easy.com.moviedbminor.Model.Movie.MovieDetail;
import easy.com.moviedbminor.Model.Movie.PopularMovie;
import easy.com.moviedbminor.Network.Moviedbinterface;
import easy.com.moviedbminor.Network.Retrofitinstance;
import easy.com.moviedbminor.R;
import easy.com.moviedbminor.Utils.IntentConstants;
import easy.com.moviedbminor.Activities.View_All_Movies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hp on 8/22/2017.
 */

public class Moviefragment extends Fragment{
    ArrayList<MovieDetail> movieDetails;
    ArrayList<MovieDetail> upcomingmoviedetails,nowplayingmoviedetails,topratedmoviedetails;
    RecyclerView recyclerView,recyclerView1,recyclerView2,recyclerView3;
    Popularmovie popularmovieadapter,topratedmovieadapter;
    Upcoming upcoming,nowplayingmovieadapter;
    ProgressBar progressBar;
    boolean topratedloaded=false,nowshowingloaded=false,upcomingloaded=false,popularloaded=false;
    RelativeLayout r1,r2,r3,r4;
    TextView topratedtext,upcomingtext,nowshowingtext,populartext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.movie_fragment,container,false);
        movieDetails=new ArrayList<>();
        upcomingmoviedetails=new ArrayList<>();
        nowplayingmoviedetails=new ArrayList<>();
        topratedmoviedetails=new ArrayList<>();
//Recycler Views
        recyclerView=v.findViewById(R.id.popularmovies);
        recyclerView1=v.findViewById(R.id.upcomingmovies);
        recyclerView2=v.findViewById(R.id.topratedmovies);
        recyclerView3=v.findViewById(R.id.nowplayingmovies);
//Relative Layout
        r1=v.findViewById(R.id.frameLayout2);

        r2=v.findViewById(R.id.frameLayout3);

        r3=v.findViewById(R.id.frameLayout4);

        r4=v.findViewById(R.id.frameLayout5);

        topratedtext=v.findViewById(R.id.toprated_view);
        populartext=v.findViewById(R.id.popular_view);
        upcomingtext=v.findViewById(R.id.upcoming_view);
        nowshowingtext=v.findViewById(R.id.nowplaying_view);


        topratedtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), View_All_Movies.class);
                i.putExtra(IntentConstants.View_All_Movies,IntentConstants.Top_Rated_View);
                startActivity(i);
            }
        });
        populartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), View_All_Movies.class);
                i.putExtra(IntentConstants.View_All_Movies,IntentConstants.Popular_View);
                startActivity(i);
            }
        });
        upcomingtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), View_All_Movies.class);
                i.putExtra(IntentConstants.View_All_Movies,IntentConstants.Upcoming_View);
                startActivity(i);
            }
        });
        nowshowingtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), View_All_Movies.class);
                i.putExtra(IntentConstants.View_All_Movies,IntentConstants.Now_showing_view);
                startActivity(i);
            }
        });

        progressBar=(ProgressBar)v.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        popularmovieadapter=new Popularmovie(getActivity(), movieDetails, new Popularmovie.Movieclicklistener() {
            @Override
            public void itemclick(View v, int position) {
//                Toast.makeText(getActivity(),movieDetails.get(position).getId()+" Clicked",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity(), Movie_Detail_Activity.class);
                i.putExtra(IntentConstants.Movie_Id,movieDetails.get(position).getId());
                startActivity(i);
            }
        });
        upcoming=new Upcoming(getActivity(), upcomingmoviedetails, new Upcoming.Movieclicklistener() {
            @Override
            public void itemclick(View v, int position) {
//                Toast.makeText(getActivity(),position+" Clicked Upcoming",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity(), Movie_Detail_Activity.class);
                i.putExtra(IntentConstants.Movie_Id,upcomingmoviedetails.get(position).getId());
                startActivity(i);
            }
        });
        topratedmovieadapter=new Popularmovie(getActivity(), topratedmoviedetails, new Popularmovie.Movieclicklistener() {
            @Override
            public void itemclick(View v, int position) {
//                Toast.makeText(getActivity(),position+" Clicked Toprated",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity(), Movie_Detail_Activity.class);
                i.putExtra(IntentConstants.Movie_Id,topratedmoviedetails.get(position).getId());
                startActivity(i);
            }
        });
        nowplayingmovieadapter=new Upcoming(getActivity(), nowplayingmoviedetails, new Upcoming.Movieclicklistener() {
            @Override
            public void itemclick(View v, int position) {
//                Toast.makeText(getActivity(),position+" Clicked Nowplaying",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity(), Movie_Detail_Activity.class);
                i.putExtra(IntentConstants.Movie_Id,nowplayingmoviedetails.get(position).getId());
                startActivity(i);
            }
        });
        Moviedbinterface moviedbinterface= Retrofitinstance.getRetrofit();
        Call<PopularMovie> popularMovieCall=moviedbinterface.getpopularmovies("29213ce3aa2d1e8b18fb60d618ccec21",1);
        Call<PopularMovie> upcomingCall=moviedbinterface.getupcomingmovies("29213ce3aa2d1e8b18fb60d618ccec21",1);
        Call<PopularMovie> nowplayingmovieCall=moviedbinterface.getnowplayingmovies("29213ce3aa2d1e8b18fb60d618ccec21",1);
        Call<PopularMovie> topratedmoviecall=moviedbinterface.gettopratedmovies("29213ce3aa2d1e8b18fb60d618ccec21",1);

        popularMovieCall.enqueue(new Callback<PopularMovie>() {
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                Log.i("Tag","insidesuccess"+response.code());
                PopularMovie popularMovie=response.body();
                ArrayList<MovieDetail> details=popularMovie.getDetail();
                onComplete(details);
                popularloaded=true;
                checkViewsLoaded();
            }

            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {

            }
        });
        upcomingCall.enqueue(new Callback<PopularMovie>() {
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                Log.i("Tag","insidesuccess"+response.code());
                PopularMovie popularMovie=response.body();
                ArrayList<MovieDetail> details=popularMovie.getDetail();
                onUpcomingComplete(details);
                upcomingloaded=true;
                checkViewsLoaded();
            }

            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {

            }
        });
        nowplayingmovieCall.enqueue(new Callback<PopularMovie>() {
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                Log.i("Tag","insidesuccess"+response.code());
                PopularMovie popularMovie=response.body();
                ArrayList<MovieDetail> details=popularMovie.getDetail();
                onNowplayingComplete(details);
                nowshowingloaded=true;
                checkViewsLoaded();
            }

            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {

            }
        });
        topratedmoviecall.enqueue(new Callback<PopularMovie>() {
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                Log.i("Tag","insidesuccess"+response.code());
                PopularMovie popularMovie=response.body();
                ArrayList<MovieDetail> details=popularMovie.getDetail();
                onTopratedComplete(details);
                topratedloaded=true;
                checkViewsLoaded();
            }

            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {

            }
        });
        recyclerView.setAdapter(popularmovieadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        recyclerView1.setAdapter(upcoming);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());


        recyclerView2.setAdapter(topratedmovieadapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setItemAnimator(new DefaultItemAnimator());


        recyclerView3.setAdapter(nowplayingmovieadapter);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView3.setItemAnimator(new DefaultItemAnimator());

        return v;
    }

    private void onComplete(ArrayList<MovieDetail> detail) {
        movieDetails.clear();
        movieDetails.addAll(detail);
        popularmovieadapter.notifyDataSetChanged();
    }

    private void onUpcomingComplete(ArrayList<MovieDetail> detail) {
        upcomingmoviedetails.clear();
        upcomingmoviedetails.addAll(detail);
        upcoming.notifyDataSetChanged();
    }
    private void onTopratedComplete(ArrayList<MovieDetail> detail) {
        topratedmoviedetails.clear();
        topratedmoviedetails.addAll(detail);
        topratedmovieadapter.notifyDataSetChanged();
    }
    private void onNowplayingComplete(ArrayList<MovieDetail> detail) {
        nowplayingmoviedetails.clear();
        nowplayingmoviedetails.addAll(detail);
        nowplayingmovieadapter.notifyDataSetChanged();
    }
    private void checkViewsLoaded()
    {
        if(topratedloaded && nowshowingloaded && upcomingloaded && popularloaded)
        {
            progressBar.setVisibility(View.GONE);
            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.VISIBLE);
            r3.setVisibility(View.VISIBLE);
            r4.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView1.setVisibility(View.VISIBLE);
            recyclerView2.setVisibility(View.VISIBLE);
            recyclerView3.setVisibility(View.VISIBLE);
        }
    }
}
