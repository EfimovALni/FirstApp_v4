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
import java.util.List;

import cz.firstapp.firstapp_v4.internet.Api;
import cz.firstapp.firstapp_v4.internet.ApiClient;
import cz.firstapp.firstapp_v4.modelSecondScreen.SecondScreenModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "Second screen ";
    private TextView tvNameIco, tvInfo_1, tvEmail, tvPhone, tvPurpose,tvImprovements;
    private ImageView ivSecondViewIcon;
    private Spinner sMenu;
    private Button bPreferred, bAttachment, bTicket, bCancel;
    private CheckBox cbUnder, cbMoreThan, cbPurchase;
    private EditText etEmail, etPhone, etPurpose, etImprovements;
    private ScrollView svSecondScreen;


    String spinnerPrompt;
    ArrayAdapter<String> itemMenuSpinner;
    List<String> listSpinner = new ArrayList<String>();
    List<String> listButtons = new ArrayList<String>();
    List<String> listTexts = new ArrayList<String>();
    List<String> listCheckboxes = new ArrayList<String>();

    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        /** Convert Based64 to image*/
        String str_icon = ivSecondViewIc.replace("data:image/png;base64,", "");
        byte[] decodedString = Base64.decode(str_icon, Base64.DEFAULT);

        //  Setting values
        tvNameIco.setText(nameIco);
        ivSecondViewIcon.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));

//        // Шаблоны для выпадающего списка
        itemMenuSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpinner);
//        itemMenuSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        //  Установка адаптера
//        sMenu.setAdapter(itemMenuSpinner);
//
//        //  Задаем заголовок списка pinner
//        sMenu.setPrompt(spinnerPrompt); // Почему то ничего в СПИНЕНЕРЕ не показыватся ;(
//        sMenu.setSelection(1);
//
//        // Устанвка события при выборе элемента из списка
//        sMenu.setOnItemSelectedListener(onItemSelectedListener());
        drawingInterface();
    }

    /**
     * Get JSON data from LOCAL Server + transmit data to adapter in RecyclerView ......... Start
     */
    private void drawingInterface() {
        api = ApiClient.getClientLocal().create(Api.class);
        api.getData().enqueue(new Callback<SecondScreenModel>() {
            @Override
            public void onResponse(Call<SecondScreenModel> call, Response<SecondScreenModel> response) {
                final SecondScreenModel secondActivity = response.body();




//                /** Fill String for 'prompt' - first item in Spinnr. */
//                spinnerPrompt = secondActivity.getSpinnerMy().getPromptMy();

                /** Fill List<> of items for component 'Spinner' */
                for (int i = 0; i < secondActivity.getSpinnerMy().size(); i++) {
                    listSpinner.add(secondActivity.getSpinnerMy().get(i).getItemMy());
                }

                /** Fill List<> of texts for component 'Text' */
                for (int i = 0; i < secondActivity.getTextsMy().size(); i++) {
                    listTexts.add(secondActivity.getTextsMy().get(i).getText());
                }

                /** Fill List<> of items for  component 'Checkbox' */
                for (int i = 0; i < secondActivity.getCheckboxMy().size(); i++) {
                    listCheckboxes.add(secondActivity.getCheckboxMy().get(i).getText());
                }

                /** Fill List<> of items for component 'Button' */
                for (int i = 0; i < secondActivity.getButtonsMy().size(); i++) {
                    listButtons.add(secondActivity.getButtonsMy().get(i).getText());
                }


//                Com. 2. Start. Filling the Spinner
                // Шаблоны для выпадающего списка
                itemMenuSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //  Установка адаптера
                sMenu.setAdapter(itemMenuSpinner);

                //  Задаем заголовок списка Spinner
                sMenu.setPrompt(spinnerPrompt);
//                sMenu.setSelection(0);    // Устанка какого то элемента из списка, по умолчинию в первой строке.

                // Устанвка события при выборе элемента из списка
                sMenu.setOnItemSelectedListener(onItemSelectedListener());
//                Com. 2. End. Filling the Spinner


//                Com. 3. Start. Filling the TextView 'tv_info_1'.
                tvInfo_1.setText(secondActivity.getTextsMy().get(0).getText());

//                Com. 4. Start. Filling the Button 'b_preferred'
                bPreferred.setText(secondActivity.getButtonsMy().get(0).getText());

//                Com. 5. Start. Filling the Checkbox 'cb_under'
                cbUnder.setText(secondActivity.getCheckboxMy().get(0).getText());
//                Com. 6. Start. Filling the Checkbox 'cb_more_than'
                cbMoreThan.setText(secondActivity.getCheckboxMy().get(1).getText());
//                Com. 7. Start. Filling the Checkbox 'cb_purchase'
                cbPurchase.setText(secondActivity.getCheckboxMy().get(2).getText());

//                Com. 8. Start. Filling the TextView 'tv_email'
                tvEmail.setText(secondActivity.getTextsMy().get(2).getText());
//                Com. 9. Start. Filling the TextView 'tv_phone'
                tvPhone.setText(secondActivity.getTextsMy().get(3).getText());
//                Com. 10. Start. Filling the TextView 'tv_purpose'
                tvPurpose.setText(secondActivity.getTextsMy().get(4).getText());
//                Com. 11. Start. Filling the TextView 'tv_improvements'
                tvImprovements.setText(secondActivity.getTextsMy().get(5).getText());

//                Com. 12. Start. Filling the Button 'b_preferred'
                bAttachment.setText(secondActivity.getButtonsMy().get(1).getText());
//                Com. 12. Start. Filling the Button 'b_preferred'
                bTicket.setText(secondActivity.getButtonsMy().get(2).getText());
//                Com. 12. Start. Filling the Button 'b_preferred'
                bCancel.setText(secondActivity.getButtonsMy().get(3).getText());

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
                        Toast.makeText(getBaseContext(), secondActivity.getTextsMy().get(6).getText(), Toast.LENGTH_SHORT).show();
                    }
                });

//                /** C.1. Start. Only for testing */
//                System.out.println(". . . . . . . . . . . START . . . . . . . . . . . .");
//                System.out.println("\n*** Spinner ***");
//                for (int i = 0; i < secondActivity.getSpinnerMy().size(); i++) {
//                    System.out.println(i + ". " + secondActivity.getSpinnerMy().get(i).getItemMy());
//                }
//
//                System.out.println("\n*** Texts ***");
//                for (int i = 0; i < secondActivity.getTextsMy().size(); i++) {
//                    System.out.println(i + ". " + secondActivity.getTextsMy().get(i).getText());
//                }
//
//                System.out.println("\n*** Checkbox ***");
//                for (int i = 0; i < secondActivity.getCheckboxMy().size(); i++) {
//                    System.out.println(i + ". " + secondActivity.getCheckboxMy().get(i).getText());
//                }
//
//                System.out.println("\n*** Buttons ***");
//                for (int i = 0; i < secondActivity.getButtonsMy().size(); i++) {
//                    System.out.println(i + ". " + secondActivity.getButtonsMy().get(i).getText());
//                }
//                System.out.println(". . . . . . . . . . END . . . . . . . . . . .");
//                /** C.1. End. Only for testing */
            }

            @Override
            public void onFailure(Call<SecondScreenModel> call, Throwable t) {
                Log.e("Err from SecondActivity", "" + t);
            }
        });
    }



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
