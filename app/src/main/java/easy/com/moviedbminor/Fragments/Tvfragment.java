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

import easy.com.moviedbminor.Activities.Tv_Detail_Activity;
import easy.com.moviedbminor.Activities.View_All_Tv;
import easy.com.moviedbminor.Adapters.Tvshows;
import easy.com.moviedbminor.Model.Tv.Tv;
import easy.com.moviedbminor.Model.Tv.Tvdetail;
import easy.com.moviedbminor.Network.Moviedbinterface;
import easy.com.moviedbminor.Network.Retrofitinstance;
import easy.com.moviedbminor.R;
import easy.com.moviedbminor.Utils.IntentConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hp on 9/16/2017.
 */

public class Tvfragment extends Fragment {
    ArrayList<Tvdetail> tvDetails;
    ArrayList<Tvdetail> upcomingtvdetails,nowplayingtvdetails,topratedtvdetails;
    RecyclerView recyclerView,recyclerView1,recyclerView2,recyclerView3;
    Tvshows popularmovieadapter,topratedmovieadapter, upcomingadapter,nowplayingmovieadapter;
    TextView topratedtexttv,upcomingtexttv,nowshowingtexttv,populartexttv;
    ProgressBar progressBar;
    boolean topratedtvloaded=false,nowshowingtvloaded=false,upcomingtvloaded=false,populartvloaded=false;
    RelativeLayout r1,r2,r3,r4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tv_fragment,container,false);
        tvDetails=new ArrayList<>();
        upcomingtvdetails=new ArrayList<>();
        nowplayingtvdetails=new ArrayList<>();
        topratedtvdetails=new ArrayList<>();

        recyclerView=v.findViewById(R.id.airingtoday_recycler);
        recyclerView1=v.findViewById(R.id.popular_recycler);
        recyclerView2=v.findViewById(R.id.topratedtv_recycler);
        recyclerView3=v.findViewById(R.id.nowplayingtv_recycler);

        r1=v.findViewById(R.id.frameLayout6);

        r2=v.findViewById(R.id.frameLayout7);

        r3=v.findViewById(R.id.frameLayout8);

        r4=v.findViewById(R.id.frameLayout9);


        topratedtexttv=v.findViewById(R.id.topratedtv_view);
        populartexttv=v.findViewById(R.id.populartv_view);
        upcomingtexttv=v.findViewById(R.id.airingtoday_view);
        nowshowingtexttv=v.findViewById(R.id.nowplayingtv_view);

        topratedtexttv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), View_All_Tv.class);
                i.putExtra(IntentConstants.View_All_Tv,IntentConstants.Top_Rated_TvView);
                startActivity(i);
            }
        });
        populartexttv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), View_All_Tv.class);
                i.putExtra(IntentConstants.View_All_Tv,IntentConstants.Popular_TvView);
                startActivity(i);
            }
        });
        upcomingtexttv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), View_All_Tv.class);
                i.putExtra(IntentConstants.View_All_Tv,IntentConstants.Upcoming_TvView);
                startActivity(i);
            }
        });
        nowshowingtexttv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), View_All_Tv.class);
                i.putExtra(IntentConstants.View_All_Tv,IntentConstants.Now_showing_Tvview);
                startActivity(i);
            }
        });



        progressBar=(ProgressBar)v.findViewById(R.id.progressbartv);
        progressBar.setVisibility(View.VISIBLE);
        popularmovieadapter=new Tvshows(getActivity(), tvDetails, new Tvshows.Tvclicklistener() {
            @Override
            public void itemclick(View v, int position) {
                Intent i=new Intent(getActivity(), Tv_Detail_Activity.class);
                i.putExtra(IntentConstants.Tv_id,tvDetails.get(position).getId());
                startActivity(i);
            }
        });
        upcomingadapter=new Tvshows(getActivity(), upcomingtvdetails, new Tvshows.Tvclicklistener() {
            @Override
            public void itemclick(View v, int position) {
                Intent i=new Intent(getActivity(), Tv_Detail_Activity.class);
                i.putExtra(IntentConstants.Tv_id,upcomingtvdetails.get(position).getId());
                startActivity(i);
            }
        });
        topratedmovieadapter=new Tvshows(getActivity(), topratedtvdetails, new Tvshows.Tvclicklistener() {
            @Override
            public void itemclick(View v, int position) {
                Intent i=new Intent(getActivity(), Tv_Detail_Activity.class);
                i.putExtra(IntentConstants.Tv_id,topratedtvdetails.get(position).getId());
                startActivity(i);
            }
        });
        nowplayingmovieadapter=new Tvshows(getActivity(), nowplayingtvdetails, new Tvshows.Tvclicklistener() {
            @Override
            public void itemclick(View v, int position) {
                Intent i=new Intent(getActivity(), Tv_Detail_Activity.class);
                i.putExtra(IntentConstants.Tv_id,nowplayingtvdetails.get(position).getId());
                startActivity(i);
            }
        });


        Moviedbinterface moviedbinterface= Retrofitinstance.getRetrofit();
        Call<Tv> populartvCall=moviedbinterface.getpopular(IntentConstants.Api_Key,1);
        Call<Tv> upcomingtvCall=moviedbinterface.getonair(IntentConstants.Api_Key,1);
        Call<Tv> nowplayingtvmovieCall=moviedbinterface.getairingtoday(IntentConstants.Api_Key,1);
        Call<Tv> topratedmovietvcall=moviedbinterface.gettoprated(IntentConstants.Api_Key,1);

        nowplayingtvmovieCall.enqueue(new Callback<Tv>() {
            @Override
            public void onResponse(Call<Tv> call, Response<Tv> response) {
                Log.i("Tag","insidesuccesstv"+response.code());
                Tv tv=response.body();
                ArrayList<Tvdetail> details=tv.getTvdetails();
                onNowplayingComplete(details);
                topratedtvloaded=true;
                checkViewsLoaded();
            }

            @Override
            public void onFailure(Call<Tv> call, Throwable t) {

            }
        });
        populartvCall.enqueue(new Callback<Tv>() {
            @Override
            public void onResponse(Call<Tv> call, Response<Tv> response) {
                Log.i("Tag","insidesuccesstv"+response.code());
                Tv tv=response.body();
                ArrayList<Tvdetail> details=tv.getTvdetails();
                onComplete(details);
                nowshowingtvloaded=true;
                checkViewsLoaded();
            }

            @Override
            public void onFailure(Call<Tv> call, Throwable t) {

            }
        });
        topratedmovietvcall.enqueue(new Callback<Tv>() {
            @Override
            public void onResponse(Call<Tv> call, Response<Tv> response) {
                Log.i("Tag","insidesuccesstv"+response.code());
                Tv tv=response.body();
                ArrayList<Tvdetail> details=tv.getTvdetails();
                onTopratedComplete(details);
                upcomingtvloaded=true;
                checkViewsLoaded();
            }

            @Override
            public void onFailure(Call<Tv> call, Throwable t) {

            }
        });
        upcomingtvCall.enqueue(new Callback<Tv>() {
            @Override
            public void onResponse(Call<Tv> call, Response<Tv> response) {
                Log.i("Tag","insidesuccesstv"+response.code());
                Tv tv=response.body();
                ArrayList<Tvdetail> details=tv.getTvdetails();
                onUpcomingComplete(details);
                populartvloaded=true;
                checkViewsLoaded();
            }

            @Override
            public void onFailure(Call<Tv> call, Throwable t) {

            }
        });
        recyclerView.setAdapter(nowplayingmovieadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        recyclerView1.setAdapter(popularmovieadapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());


        recyclerView2.setAdapter(topratedmovieadapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setItemAnimator(new DefaultItemAnimator());


        recyclerView3.setAdapter(upcomingadapter);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView3.setItemAnimator(new DefaultItemAnimator());

        return v;
    }

    private void onComplete(ArrayList<Tvdetail> detail) {
        tvDetails.clear();
        tvDetails.addAll(detail);
        popularmovieadapter.notifyDataSetChanged();
    }

    private void onUpcomingComplete(ArrayList<Tvdetail> detail) {
        upcomingtvdetails.clear();
        upcomingtvdetails.addAll(detail);
        upcomingadapter.notifyDataSetChanged();
    }
    private void onTopratedComplete(ArrayList<Tvdetail> detail) {
        topratedtvdetails.clear();
        topratedtvdetails.addAll(detail);
        topratedmovieadapter.notifyDataSetChanged();
    }
    private void onNowplayingComplete(ArrayList<Tvdetail> detail) {
        nowplayingtvdetails.clear();
        nowplayingtvdetails.addAll(detail);
        nowplayingmovieadapter.notifyDataSetChanged();
    }
    private void checkViewsLoaded()
    {
        if(topratedtvloaded && nowshowingtvloaded && upcomingtvloaded && populartvloaded)
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
