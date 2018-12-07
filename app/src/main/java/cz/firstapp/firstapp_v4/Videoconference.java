package cz.firstapp.firstapp_v4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Videoconference extends AppCompatActivity {

    private Spinner sTime;
    private Spinner sTimeZone;


    String[] times = {"0:00", "0:30", "1:00", "1:30", "2:00", "2:30", "3:00", "3:30", "4:00", "4:30	", "5:00", 	"5:30",	"6:00",	"6:30",	"7:00",	"7:30",	"8:00",	"8:30",	"9:00",	"9:30",	"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"};
    String[] timeZone =  {"EU", "AS", "LA", "US", "AR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoconference);

        sTime = (Spinner) findViewById(R.id.s_time);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapterTimes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);
        // Определяем разметку для использования при выборе элемента
        adapterTimes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        sTime.setAdapter(adapterTimes);

        sTimeZone = (Spinner) findViewById(R.id.s_time_zone);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapterTimeZone = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timeZone);
        // Определяем разметку для использования при выборе элемента
        adapterTimeZone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        sTimeZone.setAdapter(adapterTimeZone);



    }
}
