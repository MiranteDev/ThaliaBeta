package atec.thalia.thaliabeta.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import atec.thalia.thaliabeta.AppThalia;
import atec.thalia.thaliabeta.MainActivity;
import atec.thalia.thaliabeta.Model.User;
import atec.thalia.thaliabeta.R;
import atec.thalia.thaliabeta.Util.WebServices;

public class Av_login extends AppCompatActivity implements RestObserver {

    EditText et_email,et_password;
    Button bt_login,bt_resgitry;
    Skyrunner mSky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av_login);

        et_email = findViewById(R.id.avL_email);

        et_password = findViewById(R.id.avL_password);

        bt_login = findViewById(R.id.avL_btlogin);
        bt_resgitry = findViewById(R.id.avL_btregistro);


        mSky = new Skyrunner(20);
        mSky.addObserver(this);

        bt_resgitry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Av_registry.class);
                startActivity(intent);
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_email.length()>0 && et_password.length()>0){
                    RequestR2D2 req = new RequestR2D2(WebServices.SERVIDOR+WebServices.LOGIN,null,RequestR2D2.GET);
                    req.addParValue("email",et_email.getText().toString());
                    req.addParValue("password",et_password.getText().toString());

                    mSky.sendRequest(req,Skyrunner.RequestTag.KPOSONE);
                }




            }
        });

    }

    @Override
    public void receivedResponse(ResponseR2D2 response) {
        switch (response.getId()){
            case Skyrunner.RequestTag.KPOSONE:

                Log.i("msg:",response.getMessage().toString());
                if(response.getMessage().toString().compareTo("null")!=0){
                    ((AppThalia)getApplication()).setHash(response.getMessage().toString());

                    RequestR2D2 req = new RequestR2D2(WebServices.SERVIDOR+WebServices.GETUSER,null,RequestR2D2.GET);
                    req.addParValue("hash",response.getMessage().toString());

                    mSky.sendRequest(req,Skyrunner.RequestTag.KPOSTWO);


                }
                break;

            case Skyrunner.RequestTag.KPOSTWO:

                ((AppThalia)getApplication()).setUser(new Gson().fromJson(response.getJSONObj().toString(), User.class));
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

                break;
        }
    }

    @Override
    public void startConnecting() {

    }

    @Override
    public void endConnecting() {

    }

    @Override
    public void requestTimeout() {

    }
}
