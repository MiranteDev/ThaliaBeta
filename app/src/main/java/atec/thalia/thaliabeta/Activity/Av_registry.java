package atec.thalia.thaliabeta.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONException;
import org.json.JSONObject;

import atec.thalia.thaliabeta.Model.User;
import atec.thalia.thaliabeta.R;
import atec.thalia.thaliabeta.Util.WebServices;

public class Av_registry extends AppCompatActivity implements RestObserver {

    Button bt_registry;
    Skyrunner mSky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av_registry);

        bt_registry = findViewById(R.id.avR_btregistry);

        mSky = new Skyrunner(20);
        mSky.addObserver(this);
        bt_registry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                User u= mockupdata();


                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(new Gson().toJson(u));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestR2D2 req = new RequestR2D2(WebServices.SERVIDOR+WebServices.REGISTRY,jsonObject,RequestR2D2.GET);
                req.addParValue("password","123");
                mSky.sendRequest(req,Skyrunner.RequestTag.KPOSONE);

            }
        });

    }



    @Override
    public void receivedResponse(ResponseR2D2 response) {

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


    public User mockupdata(){
        User u = new User();
        u.setFirstname("Joao");
        u.setLastname("Mirante");
        u.setEmail("joaomirante16@gmail.com");
        u.setGender(User.MALE);
        u.setBirthdate("18-10-1997");
        u.setCity("Setubal");
        u.setCountry("Portugal");



        return u;
    }
}
