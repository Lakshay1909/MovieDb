package easy.com.moviedbminor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import easy.com.moviedbminor.Activities.Movie_Detail_Activity;
import easy.com.moviedbminor.Activities.Profile_detail;
import easy.com.moviedbminor.Activities.Tv_Detail_Activity;
import easy.com.moviedbminor.Model.Search.Searchresult;
import easy.com.moviedbminor.R;
import easy.com.moviedbminor.Utils.IntentConstants;

/**
 * Created by Hp on 9/26/2017.
 */

public class Searchadapter extends RecyclerView.Adapter<Searchadapter.Resultview> {

    private ArrayList<Searchresult> result;
    private Context context;

    public Searchadapter(ArrayList<Searchresult> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @Override
    public Resultview onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Resultview(LayoutInflater.from(context).inflate(R.layout.item_search,parent,false));
    }

    @Override
    public void onBindViewHolder(Resultview holder, int position) {
        Picasso.with(context).load(IntentConstants.Images_Base_Url_342+result.get(position).getPoster_path()).into(holder.imageView);
        if(result.get(position).getName()!=null && !result.get(position).getName().trim().isEmpty())
        {
            holder.name.setText(result.get(position).getName());
        }
        else
            holder.name.setText("");
        if(result.get(position).getMedia_type()!=null && result.get(position).getMedia_type().equals("movie"))
        {
         holder.media_type.setText("Movie");
        }
        else if(result.get(position).getMedia_type()!=null && result.get(position).getMedia_type().equals("tv"))
        {
            holder.media_type.setText("Tv Shows");
        }
        else if(result.get(position).getMedia_type()!=null && result.get(position).getMedia_type().equals("person"))
        {
            holder.media_type.setText("Person");
        }
        else
            holder.media_type.setText("");
        if(result.get(position).getOverview()!=null && !result.get(position).getOverview().trim().isEmpty())
        {
            holder.overview.setText(result.get(position).getOverview());
        }
        else
            holder.overview.setText("");
        if(result.get(position).getRelease_date()!=null && !result.get(position).getRelease_date().trim().isEmpty()) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
            try {
                Date releaseDate = sdf1.parse(result.get(position).getRelease_date());
                holder.year.setText(sdf2.format(releaseDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else
            holder.year.setText("");

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class Resultview extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;
        TextView name,overview,year,media_type;
        public Resultview(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.poster_search);
            cardView=(CardView)itemView.findViewById(R.id.card_view_search);
            name=(TextView)itemView.findViewById(R.id.name_search);
            overview=(TextView)itemView.findViewById(R.id.overview_search);
            year=(TextView)itemView.findViewById(R.id.year_search);
            media_type=(TextView)itemView.findViewById(R.id.media_type_search);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(result.get(getAdapterPosition()).getMedia_type().equals("movie"))
                    {
                        Intent i=new Intent(context, Movie_Detail_Activity.class);
                        i.putExtra(IntentConstants.Movie_Id,result.get(getAdapterPosition()).getId());
                        context.startActivity(i);
                    }
                    else if(result.get(getAdapterPosition()).getMedia_type().equals("tv"))
                    {
                        Intent i=new Intent(context, Tv_Detail_Activity.class);
                        i.putExtra(IntentConstants.Tv_id,result.get(getAdapterPosition()).getId());
                        context.startActivity(i);
                    }
                    else if(result.get(getAdapterPosition()).getMedia_type().equals("person"))
                    {
                        Intent i=new Intent(context, Profile_detail.class);
                        i.putExtra(IntentConstants.Cast_Id,result.get(getAdapterPosition()).getId());
                        context.startActivity(i);
                    }
                }
            });
        }
    }
}
