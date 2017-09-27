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

import easy.com.moviedbminor.Model.Tv.Tvdetail;
import easy.com.moviedbminor.R;

/**
 * Created by Hp on 9/16/2017.
 */

public class Tvshows  extends RecyclerView.Adapter<Tvshows.Tvview>{


        private ArrayList<Tvdetail> tvDetails;
        private Context mContext;
        private Tvclicklistener mclicklistener;
        public String url="http://image.tmdb.org/t/p/w185/";

        public Tvshows(Context context, ArrayList<Tvdetail> details,Tvclicklistener listener) {
            mContext = context;
            tvDetails = details;
            mclicklistener = listener;
        }

        @Override
        public Tvview onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.tv_list, parent, false);
            return new Tvview(v, mclicklistener);
        }

        @Override
        public void onBindViewHolder(Tvview holder, int position) {
            Tvdetail tvDetail=tvDetails.get(position);
            Picasso.with(mContext).load(url+tvDetail.getPoster_path()).into(holder.imageView);
            holder.textView.setText(tvDetail.getName());
            holder.textView1.setText(tvDetail.getVote_average()+"");
        }

        @Override
        public int getItemCount() {
            return tvDetails.size();
        }

        public interface Tvclicklistener {
            void itemclick(View v, int position);
        }

        public static class Tvview extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView imageView;
            TextView textView;
            TextView textView1;
            Tvclicklistener mclicklistener;

            public Tvview(View itemView, Tvclicklistener tvclicklistener) {
                super(itemView);
                itemView.setOnClickListener(this);
                mclicklistener = tvclicklistener;
                imageView = itemView.findViewById(R.id.imageViewtv);
                textView = itemView.findViewById(R.id.textViewtv);
                textView1 = itemView.findViewById(R.id.textViewtv2);
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


