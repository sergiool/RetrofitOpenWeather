package site.ufsj.retrofitopenweather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import site.ufsj.retrofitopenweather.models.WeatherAPIResult;
import site.ufsj.retrofitopenweather.rest.WeatherApiInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView textView = (TextView) findViewById(R.id.textview);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending request...", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                //Send get weather request
                WeatherApiInterface weatherApiInterface = WeatherApiInterface.retrofit.create(WeatherApiInterface.class);
                //We need to pass our city ID and our openweathermap APPID
                Call<WeatherAPIResult> call = weatherApiInterface.getWeather(3465644, "476f28ed531b9477e89ddb6ab463dbd5");
                call.enqueue(new Callback<WeatherAPIResult>() {
                    @Override
                    public void onResponse(Call<WeatherAPIResult> call, Response<WeatherAPIResult> response) {
                        if(response.isSuccessful()) {
                            //Handle the received weather data here
                            WeatherAPIResult result = response.body();
                            textView.setText(result.getCity().getName() + " :"
                            + result.getList().get(0).getWeather().get(0).getDescription());
                        } else {
                            Log.e("MainActivity", "Response received but request not successful. Response: " + response.raw());
                            textView.setText("Response received but request not successful. Response: " + response.raw());
                        }

                    }

                    @Override
                    public void onFailure(Call<WeatherAPIResult> call, Throwable t) {
                        Log.e("MainActivity", "Request error!");

                    }


                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
