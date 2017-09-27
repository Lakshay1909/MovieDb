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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import easy.com.moviedbminor.Adapters.ImageAdapter;
import easy.com.moviedbminor.Adapters.MovieCastPerson;
import easy.com.moviedbminor.Adapters.TvcastPerson;
import easy.com.moviedbminor.Model.Movie.MovieCastofPerson;
import easy.com.moviedbminor.Model.Movie.Moviecast_Response;
import easy.com.moviedbminor.Model.Movie.Person;
import easy.com.moviedbminor.Model.Movie.Tvcast_Response;
import easy.com.moviedbminor.Model.Movie.TvcastofPerson;
import easy.com.moviedbminor.Model.Tv.Personimages;
import easy.com.moviedbminor.Model.Tv.Posters;
import easy.com.moviedbminor.Network.Moviedbinterface;
import easy.com.moviedbminor.Network.Retrofitinstance;
import easy.com.moviedbminor.R;
import easy.com.moviedbminor.Utils.IntentConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_detail extends AppCompatActivity {
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView imageView;
    Toolbar toolbar;
    TextView name,age,birthplace,biography;
    RecyclerView moviecast,tvcast,images;
    private Call<Person> personCall;
    private Call<MovieCastofPerson> moviecastofperson;
    private Call<TvcastofPerson> tvcastofperson;
    private Call<Personimages> personimagesCall;
    MovieCastPerson movieCastPerson;
    TvcastPerson tvcastperson;
    ImageAdapter imageAdapter;
    Moviedbinterface moviedbinterface;
    ArrayList<Moviecast_Response> moviecastresponse;
    ArrayList<Tvcast_Response> tvcastresponse;
    ArrayList<Posters> posters;
    int cast_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        Intent i=getIntent();
       cast_id= i.getIntExtra(IntentConstants.Cast_Id,-1);
        if(cast_id==-1)
            finish();
        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar_cast);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout_cast);
        imageView=(ImageView)findViewById(R.id.image_view_cast);
        name=(TextView)findViewById(R.id.name_cast_detail);
        age=(TextView)findViewById(R.id.age_cast_detail);
        birthplace=(TextView)findViewById(R.id.text_view_birthplace_cast_detail);
        biography=(TextView)findViewById(R.id.biography_person_detail);
        toolbar=(Toolbar)findViewById(R.id.toolbar_cast);
        setSupportActionBar(toolbar);
        setTitle(" ");

        moviecastresponse=new ArrayList<>();
        tvcastresponse=new ArrayList<>();
        posters=new ArrayList<>();

        moviecast=(RecyclerView)findViewById(R.id.recycler_view_movie_cast_person);
        tvcast=(RecyclerView)findViewById(R.id.recycler_view_tv_cast_person);
        images=(RecyclerView)findViewById(R.id.recycler_view_cast_images);

        movieCastPerson=new MovieCastPerson(moviecastresponse, Profile_detail.this, new MovieCastPerson.MovieCastPersonclicklistener() {
            @Override
            public void itemclick(View v, int position) {
                Intent i=new Intent(Profile_detail.this,Movie_Detail_Activity.class);
                i.putExtra(IntentConstants.Movie_Id,moviecastresponse.get(position).getId());
                startActivity(i);

            }
        });
        tvcastperson=new TvcastPerson(tvcastresponse, Profile_detail.this, new TvcastPerson.TvCastPersonclicklistener() {
            @Override
            public void itemclick(View v, int position) {
                Intent i=new Intent(Profile_detail.this,Tv_Detail_Activity.class);
                i.putExtra(IntentConstants.Tv_id,tvcastresponse.get(position).getId());
                startActivity(i);
            }
        });
        imageAdapter=new ImageAdapter(Profile_detail.this,posters);

        moviecast.setAdapter(movieCastPerson);
        moviecast.setLayoutManager(new LinearLayoutManager(Profile_detail.this,LinearLayoutManager.HORIZONTAL,false));
        moviecast.setItemAnimator(new DefaultItemAnimator());

        tvcast.setAdapter(tvcastperson);
        tvcast.setLayoutManager(new LinearLayoutManager(Profile_detail.this,LinearLayoutManager.HORIZONTAL,false));
        tvcast.setItemAnimator(new DefaultItemAnimator());

        images.setAdapter(imageAdapter);
        images.setLayoutManager(new LinearLayoutManager(Profile_detail.this,LinearLayoutManager.HORIZONTAL,false));
        images.setItemAnimator(new DefaultItemAnimator());

        moviedbinterface=Retrofitinstance.getRetrofit();
        personCall=moviedbinterface.getpersondetails(cast_id,IntentConstants.Api_Key);
        personCall.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                final Person person=response.body();
                name.setText(person.getName());
                Picasso.with(Profile_detail.this).load(IntentConstants.Images_Base_Url+person.getProfile_path()).into(imageView);
                setAge(person.getBirthday());
                biography.setText(person.getBiography());
                birthplace.setText(person.getPlace_of_birth());
                setMoviecast(person.getId());
                setTvcast(person.getId());
                setImages(person.getId());
                appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if(appBarLayout.getTotalScrollRange()+verticalOffset==0)
                            collapsingToolbarLayout.setTitle(person.getName());
                        else
                            collapsingToolbarLayout.setTitle("");
                    }
                });
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

            }
        });

    }

    private void setImages(int id) {
        personimagesCall=moviedbinterface.getpersonimages(cast_id,IntentConstants.Api_Key);
        personimagesCall.enqueue(new Callback<Personimages>() {
            @Override
            public void onResponse(Call<Personimages> call, Response<Personimages> response) {
                Personimages personimages=response.body();
                ArrayList<Posters> image=personimages.getImages();
                for(Posters imageposter:image)
                {
                    if(imageposter.getFile_path()!=null)
                    {
                        posters.add(imageposter);
                    }
                }
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Personimages> call, Throwable t) {

            }
        });
    }

    private void setTvcast(int id) {
        tvcastofperson=moviedbinterface.gettvcast(id,IntentConstants.Api_Key);
        tvcastofperson.enqueue(new Callback<TvcastofPerson>() {
            @Override
            public void onResponse(Call<TvcastofPerson> call, Response<TvcastofPerson> response) {
                TvcastofPerson tv=response.body();
               ArrayList<Tvcast_Response> tvcast= tv.getGettvresponse();
                oncomplete(tvcast);
            }

            @Override
            public void onFailure(Call<TvcastofPerson> call, Throwable t) {

            }
        });
    }

    private void oncomplete(ArrayList<Tvcast_Response> tvcast) {
        tvcastresponse.clear();
        tvcastresponse.addAll(tvcast);
        tvcastperson.notifyDataSetChanged();
    }

    private void setMoviecast(int id) {
        moviecastofperson=moviedbinterface.getmoviecast(id,IntentConstants.Api_Key);
        moviecastofperson.enqueue(new Callback<MovieCastofPerson>() {
            @Override
            public void onResponse(Call<MovieCastofPerson> call, Response<MovieCastofPerson> response) {
                MovieCastofPerson movie=response.body();
               ArrayList<Moviecast_Response> moviecast= movie.getGetmoviecast();
                onMoviecomplete(moviecast);
            }

            @Override
            public void onFailure(Call<MovieCastofPerson> call, Throwable t) {

            }
        });
    }

    private void onMoviecomplete(ArrayList<Moviecast_Response> moviecast) {
        moviecastresponse.clear();
        moviecastresponse.addAll(moviecast);
        movieCastPerson.notifyDataSetChanged();
    }

    private void setAge(String birthday) {
        if(birthday!=null && !birthday.trim().isEmpty())
        {
            SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2=new SimpleDateFormat("yyyy");
            try {
               Date agecast = sdf1.parse(birthday);
                age.setText((Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(sdf2.format(agecast)))+"");

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
