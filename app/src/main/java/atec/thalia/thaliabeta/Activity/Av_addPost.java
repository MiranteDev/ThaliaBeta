package atec.thalia.thaliabeta.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import atec.thalia.thaliabeta.AppThalia;
import atec.thalia.thaliabeta.Model.Media;
import atec.thalia.thaliabeta.Model.Post;
import atec.thalia.thaliabeta.Model.UploadFileResponse;
import atec.thalia.thaliabeta.R;
import atec.thalia.thaliabeta.Remote.SendFile;
import atec.thalia.thaliabeta.Util.WebServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Av_addPost extends AppCompatActivity implements RestObserver {

    Button bt_picker;
    TextView path;
    ImageView imageView;
    AppThalia app;
    String pathfile;
    Skyrunner mSky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av_add_post);

        mSky = new Skyrunner(20);
        mSky.addObserver(this);


        bt_picker = findViewById(R.id.bt_select);
        path = findViewById(R.id.filepath);
        imageView = findViewById(R.id.img);


        app = ((AppThalia)getApplication());


        bt_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.create(Av_addPost.this).start();
            }
        });

        //enviarpost.setEnabled(false);


    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);

            path.setText(image.getPath());

            File file = new File(image.getPath());


          final Call<UploadFileResponse> cal = new SendFile("http://api.mirante.site:8081/mposts/","upload",app.getHash(),"file",file).getCall();

          cal.enqueue(new Callback<UploadFileResponse>() {
                @Override
                public void onResponse(Call<UploadFileResponse> call, Response<UploadFileResponse> response) {
                    pathfile = response.body().getFileDownloadUri();
                    Toast.makeText(getApplicationContext(),"feito",Toast.LENGTH_LONG).show();

                    app.setPost(mockupdata(pathfile));
                    finish();

                }

                @Override
                public void onFailure(Call<UploadFileResponse> call, Throwable t) {

                }
            });



            Picasso.with(getApplicationContext()).load(new File(image.getPath())).into(imageView);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    public Post mockupdata(String pathfile){

        Media m = new Media(Media.IMAGE,pathfile);
        Post post = new Post("123","19-11-2018","Como eu gosto de programar!",1,1);
        post.setCreator(app.getUser());
        post.setMedia(m);

        return post;
    }


    @Override
    public void receivedResponse(ResponseR2D2 response) {
        switch (response.getId()){
            case Skyrunner.RequestTag.KPOSONE:

                if(response.getMessage().toString().compareTo("adicionado")==0){
                    Toast.makeText(getApplicationContext(),"Post adicionado com sucesso!!",Toast.LENGTH_LONG).show();
                }else if(response.getMessage().toString().compareTo("nadicionado")==0){
                    Toast.makeText(getApplicationContext(),"Falha ao adicionar post!! Por favor renicie aplicação",Toast.LENGTH_LONG).show();
                }


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
