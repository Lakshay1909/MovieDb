package easy.com.moviedbminor.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hp on 8/22/2017.
 */

public class Retrofitinstance {
private   static Retrofit retrofit;
    private static Moviedbinterface moviedbinterface;
    public static Moviedbinterface getRetrofit()
    {
        if(retrofit==null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            moviedbinterface=retrofit.create(Moviedbinterface.class);
            return moviedbinterface;
        }
        return moviedbinterface;
    }

}
