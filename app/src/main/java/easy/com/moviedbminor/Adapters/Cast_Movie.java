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
import easy.com.moviedbminor.R;

/**
 * Created by Hp on 9/18/2017.
 */

public class Cast_Movie extends RecyclerView.Adapter<Cast_Movie.CastView>{

private ArrayList<MovieCast> movieCast;
  private Context mContext;
    private Castclicklistener castclicklistener;
    public String url="http://image.tmdb.org/t/p/w342/";

    public Cast_Movie( Context mContext,ArrayList<MovieCast> movieCast, Castclicklistener castclicklistener) {
        this.movieCast = movieCast;
        this.mContext = mContext;
        this.castclicklistener = castclicklistener;
    }

    @Override
    public CastView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.cast_layout,parent,false);
        return new CastView(v,castclicklistener);
    }

    @Override
    public void onBindViewHolder(CastView holder, int position) {
        MovieCast movie=movieCast.get(position);
        Picasso.with(mContext).load(url+movie.getProfile_path()).into(holder.imageView);
        holder.textView.setText(movie.getName());
        holder.textView1.setText(movie.getCharacter());
    }

    @Override
    public int getItemCount() {
        return movieCast.size();
    }

    public interface Castclicklistener
    {
         void Click(View v,int position);
    }
    public static class CastView extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imageView;
        TextView textView,textView1;
        Castclicklistener mcastclicklistener;

        public CastView(View itemView, Castclicklistener castclicklistener) {
            super(itemView);
            mcastclicklistener = castclicklistener;
            itemView.setOnClickListener(this);
            imageView=itemView.findViewById(R.id.cast_image);
            textView=itemView.findViewById(R.id.cast_name);
            textView1=itemView.findViewById(R.id.cast_as);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            if(position!=RecyclerView.NO_POSITION)
                mcastclicklistener.Click(view,position);
        }
    }
}
