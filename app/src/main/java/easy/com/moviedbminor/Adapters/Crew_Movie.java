package easy.com.moviedbminor.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import easy.com.moviedbminor.Model.Movie.MovieCast;
import easy.com.moviedbminor.Model.Movie.MovieCrew;
import easy.com.moviedbminor.R;

/**
 * Created by Hp on 9/18/2017.
 */

public class Crew_Movie extends RecyclerView.Adapter<Crew_Movie.CrewView>{

        private ArrayList<MovieCrew> movieCrew;
        private Context mContext;
        private Crewclicklistener crewclicklistener;
        public String url="http://image.tmdb.org/t/p/w342/";

        public Crew_Movie( Context mContext,ArrayList<MovieCrew> movieCrew,Crewclicklistener crewclicklistener) {
            this.movieCrew = movieCrew;
            this.mContext = mContext;
            this.crewclicklistener = crewclicklistener;
        }

        @Override
        public CrewView onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(mContext).inflate(R.layout.crew_layout,parent,false);
            return new CrewView(v,crewclicklistener);
        }

        @Override
        public void onBindViewHolder(CrewView holder, int position) {
            MovieCrew movie=movieCrew.get(position);
            Picasso.with(mContext).load(url+movie.getProfile_path()).into(holder.imageView);
            holder.textView.setText(movie.getName());
            holder.textView1.setText(movie.getJob());
            holder.textView2.setText(movie.getDepartment());
        }

        @Override
        public int getItemCount() {
            return movieCrew.size();
        }

        public interface Crewclicklistener
        {
            void Click(View v,int position);
        }
        public static class CrewView extends RecyclerView.ViewHolder implements View.OnClickListener
        {
            ImageView imageView;
            TextView textView,textView1,textView2;
            Crewclicklistener mcrewclicklistener;

            public CrewView(View itemView, Crewclicklistener crewclicklistener) {
                super(itemView);
                mcrewclicklistener = crewclicklistener;
                itemView.setOnClickListener(this);
                imageView=itemView.findViewById(R.id.crew_image);
                textView=itemView.findViewById(R.id.crew_name);
                textView1=itemView.findViewById(R.id.crew_job);
                textView2=itemView.findViewById(R.id.crew_department);
            }

            @Override
            public void onClick(View view) {
                int position=getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION)
                    mcrewclicklistener.Click(view,position);
            }
        }
    }


