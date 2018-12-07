package cz.firstapp.firstapp_v4;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.firstapp.firstapp_v4.modelApiSecurity.RootSecurity;
import cz.firstapp.firstapp_v4.internet.Api;
import cz.firstapp.firstapp_v4.internet.ApiClient;
import cz.firstapp.firstapp_v4.modelSecondScreen.RootSecondScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    MainActivity mainActivity;
    private static final String TAG = "Second screen ";
    private TextView tvNameIco, tvInfo_1, tvEmail, tvPhone, tvPurpose, tvImprovements;
    private ImageView ivSecondViewIcon;
    private Spinner sMenu;
    private Button bPreferred, bAttachment, bTicket, bCancel;
    private CheckBox cbUnder, cbMoreThan, cbPurchase;
    private EditText etEmail, etPhone, etPurpose, etImprovements;
    private ScrollView svSecondScreen;
    public String apiPressedButton;
    String action = "action";   //For asking server
    String apiSecurity = "api";   //For asking server
    String valueSecurity = "Security";   //For asking server


    String valueDownloadSecondScreen = "downloadSecondScreen";   //For asking server
    String valuedownloadApiScreen = "downloadApiScreen";   //For asking server

    Api api;

    String spinnerPrompt = null;
    ArrayAdapter<String> itemMenuSpinner = null;
    List<String> listSpinner = new ArrayList<String>();
    List<String> listButtons = new ArrayList<String>();
    List<String> listTexts = new ArrayList<String>();
    List<String> listCheckboxes = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second);

//        svSecondScreen = (ScrollView) findViewById(R.id.sv_second_screen);
            tvNameIco = (TextView) findViewById(R.id.tv_second_view_name_ico);
            ivSecondViewIcon = (ImageView) findViewById(R.id.iv_second_view_ico);
            sMenu = (Spinner) findViewById(R.id.s_menu);
            tvInfo_1 = (TextView) findViewById(R.id.tv_info_1);
            bPreferred = (Button) findViewById(R.id.b_preferred);
            cbUnder = (CheckBox) findViewById(R.id.cb_under);
            cbMoreThan = (CheckBox) findViewById(R.id.cb_more_than);
            cbPurchase = (CheckBox) findViewById(R.id.cb_purchase);

            tvEmail = (TextView) findViewById(R.id.tv_email);
            tvPhone = (TextView) findViewById(R.id.tv_phone);
            tvPurpose = (TextView) findViewById(R.id.tv_purpose);
            tvImprovements = (TextView) findViewById(R.id.tv_improvements);

            etEmail = (EditText) findViewById(R.id.et_email);
            etPhone = (EditText) findViewById(R.id.et_phone);
            etPurpose = (EditText) findViewById(R.id.et_purpose);
            etImprovements = (EditText) findViewById(R.id.et_improvements);

            bAttachment = (Button) findViewById(R.id.b_attachment);
            bTicket = (Button) findViewById(R.id.b_ticket);
            bCancel = (Button) findViewById(R.id.b_cancel);


            //  Receive data
            Intent intent = getIntent();
            String nameIco = intent.getExtras().getString("NameIco");    // From big letter, because it 'Key'
            String ivSecondViewIc = intent.getExtras().getString("Ico");    // From big letter, because it 'Key'
            apiPressedButton = intent.getExtras().getString("api");


            /** Convert Based64 to image*/
            String str_icon = ivSecondViewIc.replace("data:image/png;base64,", "");
            byte[] decodedString = Base64.decode(str_icon, Base64.DEFAULT);

            //  Setting values
            tvNameIco.setText(nameIco);
            ivSecondViewIcon.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
            itemMenuSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpinner);    //        Шаблоны для выпадающего списка

            drawingSecondScreen();
        } catch (Exception e) {
            Log.e("ERROR", "From onCreate: " + e.toString());
            e.printStackTrace();
        }
    }

    /**
     * Get JSON data from LOCAL Server + transmit data to adapter in RecyclerView ......... Start
     */
    // TODO ------------------------------------------Раскоментировать по мере подключения к серверу
    private void drawingSecondScreen() { // TODO: Как заработает - 'getSecondScreen' - ИЗМЕНИТЬ на 'NEW EQUIPMENT'
        api = ApiClient.getClient().create(Api.class);

        switch (apiPressedButton) {
            case "new":
                getDataForBtnNew();
                break;

            case "security":
                getDataForBtnSecurity();
                break;

            case "videoconference":
                Intent intent = new Intent(SecondActivity.this, Videoconference.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
    /**Method for button 'Security'*/
    private void getDataForBtnSecurity() {
        ArrayAdapter<String> finalItemMenuSpinner = itemMenuSpinner;
        api.getSecurity(requestDataSecurity()).enqueue(new Callback<RootSecurity>() {
            @Override
            public void onResponse(Call<RootSecurity> call, Response<RootSecurity> response) {
                /** 1. Drawing all screen .....Start */
                final RootSecurity rootSecurity = response.body();

                /** Fill List<> of items for component 'Spinner' */
                for (int i = 0; i < rootSecurity.getApiSecurity().getSpinnerMy().size(); i++) {
                    listSpinner.add(rootSecurity.getApiSecurity().getSpinnerMy().get(i).getItemMy());
                }

                /** Fill List<> of texts for component 'Text' */
                for (int i = 0; i < rootSecurity.getApiSecurity().getTextsMy().size(); i++) {
                    listTexts.add(response.body().getApiSecurity().getTextsMy().get(i).getText());
                }

                /** Fill List<> of items for  component 'Checkbox' */
                for (int i = 0; i < rootSecurity.getApiSecurity().getCheckboxMy().size(); i++) {
                    listCheckboxes.add(rootSecurity.getApiSecurity().getCheckboxMy().get(i).getText());
                }

                /** Fill List<> of items for component 'Button' */
                for (int i = 0; i < rootSecurity.getApiSecurity().getButtonsMy().size(); i++) {
                    listButtons.add(rootSecurity.getApiSecurity().getButtonsMy().get(i).getText());
                }

//                Com. 2. Start. Filling the Spinner
                // Шаблоны для выпадающего списка
                finalItemMenuSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //  Установка адаптера
                sMenu.setAdapter(finalItemMenuSpinner);

                //  Задаем заголовок списка Spinner
                sMenu.setPrompt(spinnerPrompt);
//                sMenu.setSelection(0);    // Устанка какого то элемента из списка, по умолчинию в первой строке.

                // Устанвка события при выборе элемента из списка
                sMenu.setOnItemSelectedListener(onItemSelectedListener());
//                Com. 2. End. Filling the Spinner


//                Com. 3. Start. Filling the TextView 'tv_info_1'.
                tvInfo_1.setText(rootSecurity.getApiSecurity().getTextsMy().get(0).getText());

//                Com. 4. Start. Filling the Button 'b_preferred'
                bPreferred.setText(rootSecurity.getApiSecurity().getButtonsMy().get(0).getText());

//                Com. 5. Start. Filling the Checkbox 'cb_under'
                cbUnder.setText(rootSecurity.getApiSecurity().getCheckboxMy().get(0).getText());
//                Com. 6. Start. Filling the Checkbox 'cb_more_than'
                cbMoreThan.setText(rootSecurity.getApiSecurity().getCheckboxMy().get(1).getText());
//                Com. 7. Start. Filling the Checkbox 'cb_purchase'
                cbPurchase.setText(rootSecurity.getApiSecurity().getCheckboxMy().get(2).getText());

//                Com. 8. Start. Filling the TextView 'tv_email'
                tvEmail.setText(rootSecurity.getApiSecurity().getTextsMy().get(2).getText());
//                Com. 9. Start. Filling the TextView 'tv_phone'
                tvPhone.setText(rootSecurity.getApiSecurity().getTextsMy().get(3).getText());
//                Com. 10. Start. Filling the TextView 'tv_purpose'
                tvPurpose.setText(rootSecurity.getApiSecurity().getTextsMy().get(4).getText());
//                Com. 11. Start. Filling the TextView 'tv_improvements'
                tvImprovements.setText(rootSecurity.getApiSecurity().getTextsMy().get(5).getText());

//                Com. 12. Start. Filling the Button 'b_preferred'
                bAttachment.setText(rootSecurity.getApiSecurity().getButtonsMy().get(1).getText());
//                Com. 12. Start. Filling the Button 'b_preferred'
                bTicket.setText(rootSecurity.getApiSecurity().getButtonsMy().get(2).getText());
//                Com. 12. Start. Filling the Button 'b_preferred'
                bCancel.setText(rootSecurity.getApiSecurity().getButtonsMy().get(3).getText());

                // Если нажата кнопка "Preferred steps to try" ПОКА - просто выдать сообщение!
                bPreferred.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(),
                                "Preferred steps to try order equipment:\n" +
                                        "1. Thinking\n" +
                                        "2. Discussing\n" +
                                        "3. Do you need this equipment? \n" +
                                        "4. Doubting?\n" +
                                        "4. Go to step 1",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                // Если нажата кнопка "Send ticket" ПОКА - просто выдать сообщение!
                bTicket.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), response.body().getApiSecurity().getTextsMy().get(6).getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                // Если нажата кнопка "Add attachment" ПОКА - просто выдать сообщение!
                bAttachment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sTemp = response.body().getApiSecurity().getButtonsMy().get(1).getText();
                        Toast.makeText(getBaseContext(), "Was pressed button: \n" + sTemp, Toast.LENGTH_SHORT).show();
                    }
                });
                // Если нажата кнопка "Cancel form" ПОКА - просто выдать сообщение!
                bCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sTemp = response.body().getApiSecurity().getButtonsMy().get(3).getText();
                        Toast.makeText(getBaseContext(), "Was pressed button: \n" + sTemp, Toast.LENGTH_SHORT).show();
                    }
                });
            }


            @Override
            public void onFailure(Call<RootSecurity> call, Throwable t) {
                Log.e("ERROR ", "From button 'Security. '" + t);
            }
        });
    }

    /**Method for button 'Security'*/
    private void getDataForBtnNew() {
        //            Шаблоны для выпадающего списка
//        itemMenuSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpinner);
        ArrayAdapter<String> finalItemMenuSpinner = itemMenuSpinner;
        api.getSecondScreen(requestDataSecondScreen()).enqueue(new Callback<RootSecondScreen>() {
            @Override
            public void onResponse(Call<RootSecondScreen> call, Response<RootSecondScreen> response) {
                /** 1. Drawing all screen .....Start */
                final RootSecondScreen rootSecondScreen = response.body();

                /** Fill List<> of items for component 'Spinner' */
                for (int i = 0; i < rootSecondScreen.getSecondScreen().getSpinnerMy().size(); i++) {
                    listSpinner.add(rootSecondScreen.getSecondScreen().getSpinnerMy().get(i).getItemMy());
                }

                /** Fill List<> of texts for component 'Text' */
                for (int i = 0; i < rootSecondScreen.getSecondScreen().getTextsMy().size(); i++) {
                    listTexts.add(rootSecondScreen.getSecondScreen().getTextsMy().get(i).getText());
                }

                /** Fill List<> of items for  component 'Checkbox' */
                for (int i = 0; i < rootSecondScreen.getSecondScreen().getCheckboxMy().size(); i++) {
                    listCheckboxes.add(rootSecondScreen.getSecondScreen().getCheckboxMy().get(i).getText());
                }

                /** Fill List<> of items for component 'Button' */
                for (int i = 0; i < rootSecondScreen.getSecondScreen().getButtonsMy().size(); i++) {
                    listButtons.add(rootSecondScreen.getSecondScreen().getButtonsMy().get(i).getText());
                }

//                Com. 2. Start. Filling the Spinner
                // Шаблоны для выпадающего списка
                finalItemMenuSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //  Установка адаптера
                sMenu.setAdapter(finalItemMenuSpinner);

                //  Задаем заголовок списка Spinner
                sMenu.setPrompt(spinnerPrompt);
//                sMenu.setSelection(0);    // Устанка какого то элемента из списка, по умолчинию в первой строке.

                // Устанвка события при выборе элемента из списка
                sMenu.setOnItemSelectedListener(onItemSelectedListener());
//                Com. 2. End. Filling the Spinner

//                Com. 3. Start. Filling the TextView 'tv_info_1'.
                tvInfo_1.setText(rootSecondScreen.getSecondScreen().getTextsMy().get(0).getText());

//                Com. 4. Start. Filling the Button 'b_preferred'
                bPreferred.setText(rootSecondScreen.getSecondScreen().getButtonsMy().get(0).getText());

//                Com. 5. Start. Filling the Checkbox 'cb_under'
                cbUnder.setText(rootSecondScreen.getSecondScreen().getCheckboxMy().get(0).getText());
//                Com. 6. Start. Filling the Checkbox 'cb_more_than'
                cbMoreThan.setText(rootSecondScreen.getSecondScreen().getCheckboxMy().get(1).getText());
//                Com. 7. Start. Filling the Checkbox 'cb_purchase'
                cbPurchase.setText(rootSecondScreen.getSecondScreen().getCheckboxMy().get(2).getText());

//                Com. 8. Start. Filling the TextView 'tv_email'
                tvEmail.setText(rootSecondScreen.getSecondScreen().getTextsMy().get(2).getText());
//                Com. 9. Start. Filling the TextView 'tv_phone'
                tvPhone.setText(rootSecondScreen.getSecondScreen().getTextsMy().get(3).getText());
//                Com. 10. Start. Filling the TextView 'tv_purpose'
                tvPurpose.setText(rootSecondScreen.getSecondScreen().getTextsMy().get(4).getText());
//                Com. 11. Start. Filling the TextView 'tv_improvements'
                tvImprovements.setText(rootSecondScreen.getSecondScreen().getTextsMy().get(5).getText());

//                Com. 12. Start. Filling the Button 'b_preferred'
                bAttachment.setText(rootSecondScreen.getSecondScreen().getButtonsMy().get(1).getText());
//                Com. 12. Start. Filling the Button 'b_preferred'
                bTicket.setText(rootSecondScreen.getSecondScreen().getButtonsMy().get(2).getText());
//                Com. 12. Start. Filling the Button 'b_preferred'
                bCancel.setText(rootSecondScreen.getSecondScreen().getButtonsMy().get(3).getText());

                // Если нажата кнопка "Preferred steps to try" ПОКА - просто выдать сообщение!
                bPreferred.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(),
                                "Preferred steps to try order equipment:\n" +
                                        "1. Thinking\n" +
                                        "2. Discussing\n" +
                                        "3. Do you need this equipment? \n" +
                                        "4. Doubting?\n" +
                                        "4. Go to step 1",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                // Если нажата кнопка "Send ticket" ПОКА - просто выдать сообщение!
                bTicket.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), rootSecondScreen.getSecondScreen().getTextsMy().get(6).getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                // Если нажата кнопка "Add attachment" ПОКА - просто выдать сообщение!
                bAttachment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sTemp = rootSecondScreen.getSecondScreen().getButtonsMy().get(1).getText();
                        Toast.makeText(getBaseContext(), "Was pressed button: \n" + sTemp, Toast.LENGTH_SHORT).show();
                    }
                });
                // Если нажата кнопка "Cancel form" ПОКА - просто выдать сообщение!
                bCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sTemp = rootSecondScreen.getSecondScreen().getButtonsMy().get(3).getText();
                        Toast.makeText(getBaseContext(), "Was pressed button: \n" + sTemp, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<RootSecondScreen> call, Throwable t) {
                Log.e("ERROR ", "From button 'New. '" + t);
            }
        });
    }


    /* Method for filling requests according the API of website,  POST ....................... Start */
    public Map<String, String> requestDataSecondScreen() {
        final Map<String, String> parametersForServer = new HashMap<>();
        parametersForServer.put(action, valueDownloadSecondScreen);
        return parametersForServer;
    }
    /*Method for filling requests according the API of website,  POST ....................... End */

    /*  ....................... Start */
    public Map<String, String> requestDataSecurity() {
        final Map<String, String> parametersForServer = new HashMap<>();
        parametersForServer.put(action, valuedownloadApiScreen);
        parametersForServer.put(apiSecurity, valueSecurity);
        return parametersForServer;
    }
    /* ....................... End */


    // Метод устанавлювающий событие при выборе элемента из списка
    AdapterView.OnItemSelectedListener onItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /** Change color first row on Spinner*/
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) parent.getChildAt(0)).setTextSize(18);

                if (parent.getItemAtPosition(position).equals("--- Please select related action ---")) {
//                    do nothing
                } else {
                    Toast.makeText(getBaseContext(), parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
}
