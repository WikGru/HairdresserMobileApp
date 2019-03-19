package com.example.grudzina_bakowski.Fryzjer.OpcjeFragments;

import android.content.Intent;

import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionName;
import com.example.grudzina_bakowski.Fryzjer.HandlerHTTP.GetData;
import com.example.grudzina_bakowski.Fryzjer.MainActivityAdmin;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;
import com.facebook.CallbackManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.grudzina_bakowski.Fryzjer.R;
import com.facebook.login.widget.LoginButton;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class LogInAsAdmin extends Fragment {

    private CallbackManager callbackManager;
    private TextView textView;
    private LoginButton fbLoginButton;
    private Button loginButton;
    private EditText username;
    private EditText pin;
    private static final String EMAIL = "email";
    private Fragment active;
    private boolean isLoggedIn = false;
    private ConnectionName connectionName;
    private String user;
    private String password;

    private SQLiteCRUD sqLiteCRUD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        active = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View myFragView = inflater.inflate(R.layout.fragment_loginasadmin, container, false);

        sqLiteCRUD = new SQLiteCRUD(myFragView.getContext());


        connectionName = new ConnectionName();
        callbackManager = CallbackManager.Factory.create();

        loginButton = myFragView.findViewById(R.id.login_button);

        username = myFragView.findViewById(R.id.username);
        pin = myFragView.findViewById(R.id.pin);

        textView = myFragView.findViewById(R.id.test_json);

        //LOG IN BUTTON LISTENER
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CHECK IF USER INPUT IS CORRECT WITH ADMIN PASS
                try {
                    JSONObject jsonObject=get_from_database_admin_and_password();
                    user =jsonObject.getString("user");
                    password=jsonObject.getString("password");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if(username.getText().toString().equals(user) && pin.getText().toString().equals(password)){
                    sqLiteCRUD.SetUser("admin");
                    myFragView.getContext().startActivity(new Intent(myFragView.getContext(), MainActivityAdmin.class)); // głowna klasa dla admina
                }else {
                }

            }
        });


        return myFragView;

    }

    public JSONObject get_from_database_admin_and_password(){
        JSONArray jsonArr = null;
        JSONObject jsonObject=null;
        try {
            jsonArr = new GetData(connectionName.getCOLLECTION_NAME_ADMIN()).execute().get();
            jsonObject=jsonArr.getJSONObject(0);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}

