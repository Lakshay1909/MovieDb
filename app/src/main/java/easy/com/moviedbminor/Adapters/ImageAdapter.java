package easy.com.moviedbminor.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import easy.com.moviedbminor.Model.Tv.Posters;
import easy.com.moviedbminor.R;
import easy.com.moviedbminor.Utils.IntentConstants;

/**
 * Created by Hp on 9/25/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.Personimagesview> {
    private Context context;
    ArrayList<Posters> images;

    public ImageAdapter(Context context, ArrayList<Posters> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public Personimagesview onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Personimagesview(LayoutInflater.from(context).inflate(R.layout.image_list,parent,false));
    }

    @Override
    public void onBindViewHolder(Personimagesview holder, int position) {
        Picasso.with(context).load(IntentConstants.Images_Base_Url+images.get(position).getFile_path()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public  class Personimagesview extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView imageView;

        public Personimagesview(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageview_person_image);
            cardView=(CardView)itemView.findViewById(R.id.card_view_images);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }

    }
}
