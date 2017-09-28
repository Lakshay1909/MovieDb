package easy.com.moviedbminor.Activities;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import easy.com.moviedbminor.Adapters.Searchadapter;
import easy.com.moviedbminor.Model.Search.SearchResponse;
import easy.com.moviedbminor.Model.Search.Searchresult;
import easy.com.moviedbminor.Network.Moviedbinterface;
import easy.com.moviedbminor.Network.Retrofitinstance;
import easy.com.moviedbminor.R;
import easy.com.moviedbminor.Utils.IntentConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    String query;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Searchresult> searchresults;
    Searchadapter searchadapter;
    Moviedbinterface moviedbinterface;
    private int previoustotal=0,visiblethreshold=5;
    private int firstvisibleitem,visibleitemcount,totalitemcount;
    private boolean Loading=true;
    ProgressBar progressbar;
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent i=getIntent();
        query=  i.getStringExtra("Query");
        toolbar=(Toolbar)findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        setTitle(query);
        searchresults=new ArrayList<>();
        progressbar=(ProgressBar)findViewById(R.id.progressbarsearch);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view_search);
        searchadapter=new Searchadapter(searchresults,SearchActivity.this);
        recyclerView.setAdapter(searchadapter);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SearchActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleitemcount=linearLayoutManager.getChildCount();
                totalitemcount=linearLayoutManager.getItemCount();
                firstvisibleitem=linearLayoutManager.findFirstVisibleItemPosition();
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
                    Log.i("TagSearch","End reached");
                    LoadResults();
                    Loading=true;
                }

            }
        });
        LoadResults();


    }

    private void LoadResults() {
        moviedbinterface= Retrofitinstance.getRetrofit();
        Call<SearchResponse> searchcall=moviedbinterface.getsearchitems(IntentConstants.Api_Key,query,page++);
        searchcall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse search=response.body();
                ArrayList<Searchresult> results=search.getResults();
                oncomplete(results);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });

    }

    private void oncomplete(ArrayList<Searchresult> results) {
        searchresults.clear();
        searchresults.addAll(results);
        searchadapter.notifyDataSetChanged();
    }
}
