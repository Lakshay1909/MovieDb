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
import easy.com.moviedbminor.R;

/**
 * Created by Hp on 9/9/2017.
 */

public class Upcoming extends RecyclerView.Adapter<Upcoming.Movieview> {
        private ArrayList<MovieDetail> movieDetails;
        private Context mContext;
        private Movieclicklistener mclicklistener;
        public String url="http://image.tmdb.org/t/p/w342/";

        public Upcoming(Context context, ArrayList<MovieDetail> details, Movieclicklistener listener) {
            mContext = context;
            movieDetails = details;
            mclicklistener = listener;
        }

        @Override
        public Movieview onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.smallmovielist, parent, false);
            return new Movieview(v, mclicklistener);
        }

        @Override
        public void onBindViewHolder(Upcoming.Movieview holder, int position) {
            MovieDetail movieDetail=movieDetails.get(position);
            if(movieDetail.getBackdrop_path()!=null)
            Picasso.with(mContext).load(url+movieDetail.getBackdrop_path()).into(holder.imageView);
            else
                Picasso.with(mContext).load(url+movieDetail.getPoster_path()).into(holder.imageView);
            holder.textView.setText(movieDetail.getTitle());
            holder.textView1.setText(movieDetail.getVote_average()+"");
        }

        @Override
        public int getItemCount() {
            return movieDetails.size();
        }

        public interface Movieclicklistener {
            void itemclick(View v, int position);
        }

        public static class Movieview extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView imageView;
            TextView textView;
            TextView textView1;
            Movieclicklistener mclicklistener;

            public Movieview(View itemView, Movieclicklistener movieclicklistener) {
                super(itemView);
                itemView.setOnClickListener(this);
                mclicklistener = movieclicklistener;
                imageView = itemView.findViewById(R.id.imageView2);
                textView = itemView.findViewById(R.id.textView);
                textView1 = itemView.findViewById(R.id.textView2);
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


