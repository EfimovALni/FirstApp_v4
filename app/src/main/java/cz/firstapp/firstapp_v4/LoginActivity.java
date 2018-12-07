package cz.firstapp.firstapp_v4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import cz.firstapp.firstapp_v4.internet.Api;
import cz.firstapp.firstapp_v4.internet.ApiClient;
import cz.firstapp.firstapp_v4.modelLogin.RootLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    /**
     * It answer of server, if we change on a server, we MUST change HERE! READ_CONTACTS
     */
    private static boolean FLAG_LOGIN_INFO_IS_CORRECT;
    private static final String STATUS_OF_REQUEST_LOGIN_RESULT = "OK";

    private static final int MINIMUM_LENGTH_OF_PASSWORD = 4;

    boolean LOGIN_AND_PASSWORD_IS_OK = false;
    Api api;
    ApiClient apiClient;
    MainActivity mainActivity;
//    RootLogin rootLogin;

    AutoCompleteTextView etLogin;
    AutoCompleteTextView etNameOfServer;
    TextView etPassword;
    Button bSingIn, bTest;
    CheckBox chSavingLoginInfo;

    TextView tvTest;
    AutoCompleteTextView tvMessage, tvLoginResult, tvStatus;

    /**
     * For asking server
     */
    String action = "action";
    String valueAction = "testLogin";
    String login = "user";
    String valueLogin = "testuser"; // for testing instead of inputing data in editText
    String password = "passenc";
    String valuePassword = "testpass";  // for testing instead of inputing data in editText

    // Saving pass
    public SharedPreferences mPreferences;
    public SharedPreferences.Editor mEditor;
    private SharedPreferences mPrefs;
    private static final String PREFS_NAME = "PrefsFile";




    //    Saving  userinfo anather way
    public SharedPreferences sPref;
    String SAVED_LOGIN = "saved_login";
    String SAVED_PASSWORD = "saved_password";
    String PREFF_CHECK = "pref_check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        api = ApiClient.getClient().create(Api.class);

        etNameOfServer = (AutoCompleteTextView) findViewById(R.id.tv_name_of_server);
        etLogin = (AutoCompleteTextView) findViewById(R.id.tv_login);
        etPassword = (TextView) findViewById(R.id.et_password);
        bSingIn = (Button) findViewById(R.id.b_sign_in_application);
        bTest = (Button) findViewById(R.id.b_test);
        chSavingLoginInfo = (CheckBox) findViewById(R.id.cb_savin_login_info);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        mEditor = mPreferences.edit();

        sPref = getPreferences(MODE_PRIVATE);

//        checkSharedPreferences();


        /** Принимаю данные из IntentLogOut, а именно значение флаг null. И исходя из этого, очищаем данные о логине и пароле */
        Intent intentLogOut = getIntent();
        Bundle res = intentLogOut.getExtras();


        bSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBooleanCheckFillingFields()) {
                    singIn();
                } else {
                    Toast.makeText(LoginActivity.this, "All fields should be filled.", Toast.LENGTH_SHORT).show();
                }
//                attemptLogin();

////                save the checkBox preferences
//                if (chSavingLoginInfo.isChecked()) {
////                    set a checkBox when the application starts
//                    mEditor.putString(getString(R.string.checkBox), "True");
//                    mEditor.commit();
//
////                    save the login
//                    String login = etLogin.getText().toString().trim();
//                    mEditor.putString(getString(R.string.login), login);
//                    mEditor.commit();
//
////                    save the password
//                    String password = etPassword.getText().toString().trim();
//                    mEditor.putString(getString(R.string.password), password);
//                    mEditor.commit();
//                } else {
////                    set a checkBox when the application starts
//                    mEditor.putString(getString(R.string.checkBox), "False");
//                    mEditor.commit();
//
////                    save the login
//                    mEditor.putString(getString(R.string.login), "");
//                    mEditor.commit();
//
////                    save the password
//                    mEditor.putString(getString(R.string.password), "");
//                    mEditor.commit();
//                }
            }
        });


        /*if (res == null) {
            deleteUserInfo();
            System.out.println(res + "================================================");
        } else {
            loadUserInfo();
            System.out.println(res + "++++++++++++++++++++++++++++++++++++++++++++++");
        }*/
    }

//    @Override
//    public void onBackPressed() {
//        Intent startMain = new Intent(Intent.ACTION_MAIN);
//        startMain.addCategory(Intent.CATEGORY_HOME);
//        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(startMain);
//    }

    private void singIn() {
//        if (chSavingLoginInfo.isChecked()) {
        if (chSavingLoginInfo.isSelected()) {
            Toast.makeText(this, "CheckBox was selected", Toast.LENGTH_SHORT).show();
            singInAccount();

            sPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(SAVED_LOGIN, etLogin.getText().toString().trim());
            ed.putString(SAVED_PASSWORD, etPassword.getText().toString().trim());
            ed.putBoolean(PREFF_CHECK, isBooleanCheckFillingFields());
            ed.apply();

            String savedLogin = sPref.getString(SAVED_LOGIN, "");       /* For testing */
            String savedPassword = sPref.getString(SAVED_PASSWORD, ""); /* For testing */

            Toast.makeText(LoginActivity.this, "Login information was saved." +
                            "\n Login: " + savedLogin + "" +
                            "\n Password: " + savedPassword,
                    Toast.LENGTH_SHORT).show();                                 /* For testing */
        } else {
            singInAccount();
//            sPref.edit().clear().apply();
        }
    }


    public void loadUserInfo() {
        String savedLogin = sPref.getString(SAVED_LOGIN, "");
        String savedPassword = sPref.getString(SAVED_PASSWORD, "");
        etLogin.setText(savedLogin);
        etPassword.setText(savedPassword);
//        Toast.makeText(LoginActivity.this, "Log: " + savedLogin + "\nPass: " + savedPassword, Toast.LENGTH_SHORT).show();
    }


    public void deleteUserInfo() {
        mEditor.clear().apply();
//        mEditor.remove("saved_login").apply();
//        mEditor.remove("saved_password").apply();//


//        SharedPreferences.Editor edit = sPref.edit();
//        edit.clear().apply();
//        Toast.makeText(LoginActivity.this, "deleteUserInfo\n All login data was deleted.", Toast.LENGTH_SHORT).show();
    }


    /**
     * Attempts to sign the account specified by the login form.
     */
//    private void attemptLogin() {
//
//        // Check for a valid 'name of server' & 'login' & 'password', if the user entered one.
//        if (isBooleanCheckFillingFields()) {
//            singInAccount();
//        } else {
//            Toast.makeText(getApplicationContext(), "All fields must be filled.", Toast.LENGTH_SHORT).show();
//        }
//    }

    /**
     * Checking fields filling yes or not ?  TRUE / FALSE
     */
    private boolean isBooleanCheckFillingFields() {
//                (!TextUtils.isEmpty(etNameOfServer.getText())) &
        return (!TextUtils.isEmpty(etLogin.getText())) &
                (!TextUtils.isEmpty(etPassword.getText()));
    }

    /**
     * This method ONLY FOR testing
     */
    private void singInAccount() {
        api.getLoginAccount(requestLoginDataFromUser()).enqueue(new Callback<RootLogin>() {
            @Override
            public void onResponse(Call<RootLogin> call, Response<RootLogin> response) {
                final RootLogin rootLogin = response.body();
//                Сверяем, стутус "ОК" который записан в JSON файле на сервере. Если значение status на сервере = "ОК" то показываем второй экран
                if (rootLogin.getLoginResult().equals(STATUS_OF_REQUEST_LOGIN_RESULT)) {
                    FLAG_LOGIN_INFO_IS_CORRECT = true;
//                    Saving pass and login when app was closed
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect login or password. ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RootLogin> call, Throwable t) {

            }
        });
    }

    private boolean isPasswordValid(String password) {
        return password.length() > MINIMUM_LENGTH_OF_PASSWORD;
    }


    //..Method for filling users information, for POST ........Start.........
    public Map<String, String> requestLoginDataFromUser() {
        final Map<String, String> parametersFromUser = new HashMap<>();

        parametersFromUser.put(action, valueAction);
        parametersFromUser.put(login, etLogin.getText().toString().trim());
        parametersFromUser.put(password, md5());

        return parametersFromUser;
    }
    //..Method for filling users information, for POST ........End .........


    //  START -----   Code for calculating "MD5"
    String md5Back;
    String testMD5;     //  Переменная в которую записыавется данные из текстового поля "Пароль"

    public String md5() {
        MessageDigest md5;
        {
            try {
                md5 = MessageDigest.getInstance("MD5");

                testMD5 = etPassword.getText().toString().trim();

                byte[] bytes = md5.digest(testMD5.getBytes());
                StringBuilder builder = new StringBuilder();

                for (byte b : bytes) {
                    builder.append(String.format("%02X", b));
                }

                md5Back = builder.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return md5Back;
    }
    //  End -----   Code for calculating "MD5"

   /* String SAVED_LOGIN_B = null;
    String SAVED_PASSWORD_B = null;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_LOGIN_B, etLogin.getText().toString().trim());
        outState.putString(SAVED_PASSWORD_B,etPassword.getText().toString().trim());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SAVED_LOGIN = savedInstanceState.getString("SAVED_LOGIN_B");
        SAVED_PASSWORD = savedInstanceState.getString("SAVED_LOGIN_B");
    }*/
}
