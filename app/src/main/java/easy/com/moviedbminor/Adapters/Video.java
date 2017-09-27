package easy.com.moviedbminor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import easy.com.moviedbminor.Model.Movie.Video_Details;
import easy.com.moviedbminor.R;
import easy.com.moviedbminor.Utils.IntentConstants;

/**
 * Created by Hp on 9/18/2017.
 */

public class Video extends RecyclerView.Adapter<Video.VideoView>{
  private   ArrayList<Video_Details> video_details;
    private Context mcontext;

    public Video(ArrayList<Video_Details> video_details, Context mcontext) {
        this.video_details = video_details;
        this.mcontext = mcontext;
    }

    @Override
    public VideoView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoView(LayoutInflater.from(mcontext).inflate(R.layout.video_item,parent,false));
    }

    @Override
    public void onBindViewHolder(VideoView holder, int position) {
        Picasso.with(mcontext).load(IntentConstants.YouTube_Thumbnail+video_details.get(position).getKey()+IntentConstants.YouTube_Thumbnail_quality).into(holder.imageView);
            holder.textView.setText(video_details.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return video_details.size();
    }

    public class VideoView extends RecyclerView.ViewHolder
    {
        public CardView cardView;
        public ImageView imageView;
        public TextView textView;
        public VideoView(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.view_video);
            imageView=itemView.findViewById(R.id.image_view_video);
            textView=itemView.findViewById(R.id.video_name);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent youtubeintent=new Intent(Intent.ACTION_VIEW, Uri.parse(IntentConstants.YouTube_Base_Url + video_details.get(getAdapterPosition()).getKey()));
                    mcontext.startActivity(youtubeintent);
                }

            });
        }

    }
}
