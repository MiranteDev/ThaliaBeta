package atec.thalia.thaliabeta.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.squareup.picasso.Picasso;

import java.io.File;

import atec.thalia.thaliabeta.R;
import atec.thalia.thaliabeta.Remote.SendFile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Av_addPost extends AppCompatActivity {

    Button bt_picker;
    TextView path;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av_add_post);


        bt_picker = findViewById(R.id.bt_select);
        path = findViewById(R.id.filepath);
        imageView = findViewById(R.id.img);

       // fileService = RetroFitClient.getClient("http://192.168.0.171:8081/upload/").create(FileService.class);





        bt_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.create(Av_addPost.this).start();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);

            path.setText(image.getPath());

            File file = new File(image.getPath());


            new SendFile("http://192.168.0.171:8081/upload/","uploadFile","file",file).getCall().enqueue(new Callback<SendFile.FileInfo>() {
                @Override
                public void onResponse(Call<SendFile.FileInfo> call, Response<SendFile.FileInfo> response) {
                    Toast.makeText(getApplicationContext(),"Upload com sucesso!",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<SendFile.FileInfo> call, Throwable t) {

                }
            });





            Picasso.with(getApplicationContext()).load(new File(image.getPath())).into(imageView);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}
