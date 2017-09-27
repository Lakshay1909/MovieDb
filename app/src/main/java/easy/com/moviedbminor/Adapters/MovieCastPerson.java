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

import easy.com.moviedbminor.Model.Movie.MovieDetail;
import easy.com.moviedbminor.Model.Movie.Moviecast_Response;
import easy.com.moviedbminor.R;

/**
 * Created by Hp on 9/22/2017.
 */

public class MovieCastPerson extends RecyclerView.Adapter<MovieCastPerson.CastPersonView> {
        private ArrayList<Moviecast_Response> movieDetails;
        private Context mContext;
        private MovieCastPersonclicklistener mclicklistener;
        public String url="http://image.tmdb.org/t/p/w185/";

    public MovieCastPerson(ArrayList<Moviecast_Response> movieDetails, Context mContext, MovieCastPersonclicklistener mclicklistener) {
        this.movieDetails = movieDetails;
        this.mContext = mContext;
        this.mclicklistener = mclicklistener;
    }

    @Override
        public CastPersonView onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.movie_cast_person, parent, false);
            return new CastPersonView(v, mclicklistener);
        }

        @Override
        public void onBindViewHolder(CastPersonView holder, int position) {
            Moviecast_Response movieDetail=movieDetails.get(position);
            Picasso.with(mContext).load(url+movieDetail.getPoster_path()).into(holder.imageView);
            holder.textView.setText(movieDetail.getTitle());
            holder.textView1.setText(movieDetail.getCharacter()+"");
        }

        @Override
        public int getItemCount() {
            return movieDetails.size();
        }

        public interface MovieCastPersonclicklistener {
            void itemclick(View v, int position);
        }

        public static class CastPersonView extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView imageView;
            TextView textView;
            TextView textView1;
            MovieCastPersonclicklistener mclicklistener;

            public CastPersonView(View itemView, MovieCastPersonclicklistener movieclicklistener) {
                super(itemView);
                itemView.setOnClickListener(this);
                mclicklistener = movieclicklistener;
                imageView = itemView.findViewById(R.id.imageView_moviecast);
                textView = itemView.findViewById(R.id.textView_moviecast);
                textView1 = itemView.findViewById(R.id.textView2_moviecast);
            }

            @Override
            public void onClick(View view) {
                int id = view.getId();
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {

                    mclicklistener.itemclick(view, position);

                }
            }
        }
    }


