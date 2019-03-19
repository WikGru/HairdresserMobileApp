package com.example.grudzina_bakowski.Fryzjer.OpcjeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.grudzina_bakowski.Fryzjer.Database.ConnectionName;
import com.example.grudzina_bakowski.Fryzjer.MainActivity;
import com.example.grudzina_bakowski.Fryzjer.R;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;


public class LogOutAdmin extends Fragment {

    private View myFragView;
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
        myFragView = inflater.inflate(R.layout.fragment_loginasadmin, container, false);

        sqLiteCRUD = new SQLiteCRUD(myFragView.getContext());


        connectionName = new ConnectionName();
        callbackManager = CallbackManager.Factory.create();

        loginButton = myFragView.findViewById(R.id.login_button);

        username = myFragView.findViewById(R.id.username);
        pin = myFragView.findViewById(R.id.pin);

        textView = myFragView.findViewById(R.id.test_json);

                try {
                    sqLiteCRUD.SetUser("user");
                } catch (Exception e) {
                    e.printStackTrace();
                }

        return myFragView;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent(myFragView.getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        myFragView.getContext().startActivity(intent); // głowna klasa
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}

