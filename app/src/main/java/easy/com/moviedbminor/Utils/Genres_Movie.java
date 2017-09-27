package easy.com.moviedbminor.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import easy.com.moviedbminor.Model.Movie.Genres;

/**
 * Created by Hp on 9/18/2017.
 */

public class Genres_Movie {
    public static HashMap<Integer,String> GenresMap;
    public static boolean isGenresloaded()
    {
        return (GenresMap!=null);
    }
    public static void LoadGenres(ArrayList<Genres> genres)
    {
        if(genres==null)
            return;
        GenresMap=new HashMap<>();
        for(Genres g:genres)
        {
            GenresMap.put(g.getId(),g.getName());
        }
    }
    public static String getGenrename(Integer genreid)
    {
        if(genreid==null)
            return null;
        return GenresMap.get(genreid);
    }
}
