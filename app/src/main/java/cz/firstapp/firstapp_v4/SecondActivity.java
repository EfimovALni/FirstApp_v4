package cz.firstapp.firstapp_v4;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cz.firstapp.firstapp_v4.internet.Api;
import cz.firstapp.firstapp_v4.internet.ApiClient;
import cz.firstapp.firstapp_v4.model.DataResponse;
import cz.firstapp.firstapp_v4.model.Initial_screen;
import cz.firstapp.firstapp_v4.secondScreen.Converter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "Second screen ";
    private TextView tvNameIco;
    private ImageView ivSecondViewIcon;
    private Spinner sMenu;
    MainActivity mainActivity;

    private String[] iconsHardCode = {"Laptop", "Desktop", "Mobile device", "Keyboard", "Mouse", "Monitor", "Others"};
    ArrayList<String> nameIconsForSpinner = new ArrayList<>();

    Api api;
    Converter converter;
    List<Converter> mDataFromServerLocal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvNameIco = (TextView) findViewById(R.id.tv_second_view_name_ico);
        ivSecondViewIcon = (ImageView) findViewById(R.id.iv_second_view_ico);
        sMenu = (Spinner) findViewById(R.id.s_menu);


        //  Receive data
        Intent intent = getIntent();
        String nameIco = intent.getExtras().getString("NameIco");    // From big letter, because it 'Key'
        String ivSecondViewIc = intent.getExtras().getString("Ico");    // From big letter, because it 'Key'

        /** Convert Based64 to image*/
        String str_icon = ivSecondViewIc.replace("data:image/png;base64,", "");
        byte[] decodedString = Base64.decode(str_icon, Base64.DEFAULT);

        //  Setting values
        tvNameIco.setText(nameIco);
        ivSecondViewIcon.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));


//        for (int i = 0; i < mainActivity.nameIcons.size(); i++) {
//            Log.e("onCreate: ", mainActivity.nameIcons.get(i));
//        }

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < iconsHardCode.length; i++) {
            list.add("New equipment - " + iconsHardCode[i]);
//            list.add(mainActivity.nameIcons.get(i));
        }


        // Шаблоны для выпадающего списка
        ArrayAdapter<String> itemMenuSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
//        ArrayAdapter<String> itemMenuSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, iconsHardCode);
        itemMenuSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //  Установка адаптера
        sMenu.setAdapter(itemMenuSpinner);

        sMenu.setPrompt("--- Please select related action ---");


        // Устанвка события при выборе элемента из списка
        sMenu.setOnItemSelectedListener(onItemSelectedListener());


        /** Прикручиваем данные с JSON for SecondScreen */
//        Converter converter = null;


        drawingInterface();
    }

    /**
     * Get JSON data from LOCAL Server + transmit data to adapter in RecyclerView ......... Start
     */
    private void drawingInterface() {
        api = ApiClient.getClientLocal().create(Api.class);
        api.getData().enqueue(new Callback<Converter>() {
            @Override
            public void onResponse(Call<Converter> call, Response<Converter> response) {
                final  Converter converter = response.body();


                Log.e("..................", converter.getName_button());


            }

            @Override
            public void onFailure(Call<Converter> call, Throwable t) {

            }
        });

    }

    // Метод устанавлювающий событие при выборе элемента из списка
    AdapterView.OnItemSelectedListener onItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
}
