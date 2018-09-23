package site.ufsj.retrofitopenweather.rest;


import java.io.IOException;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import site.ufsj.retrofitopenweather.models.WeatherAPIResult;


public interface WeatherApiInterface {
    @GET("/data/2.5/forecast/daily")
    Call<WeatherAPIResult> getWeather(@Query("id") int cityID, @Query("APPID") String appID);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
