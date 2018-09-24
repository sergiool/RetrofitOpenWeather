package site.ufsj.retrofitopenweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import site.ufsj.retrofitopenweather.models.Day;

public class DayAdapter extends BaseAdapter {
    Context context;
    List<Day> days;

    public DayAdapter(Context context, List<Day> days){
        this.context =  context;
        this.days = days;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int i) {
        return days.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View layout;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.day_layout, null);
        }
        else{
            layout = convertView;
        }

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));
        TextView data = layout.findViewById(R.id.textViewData);
        Calendar dia = Calendar.getInstance();
        dia.add(Calendar.DATE, i);
        data.setText("Previsão para o dia: " + sdf.format(dia.getTime()));

        TextView temp = (TextView) layout.findViewById(R.id.textViewT);
        temp.setText("Temperatura: " + days.get(i).getTemp().getMin() +" a " + days.get(i).getTemp().getMax() + " oC");

        TextView clima = (TextView) layout.findViewById(R.id.textViewD);
        clima.setText(days.get(i).getWeather().get(0).getDescription());

        return layout;
    }
}
