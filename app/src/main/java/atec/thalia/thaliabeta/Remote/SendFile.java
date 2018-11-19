package atec.thalia.thaliabeta.Remote;

import com.google.gson.Gson;

import java.io.File;

import atec.thalia.thaliabeta.Model.Post;
import atec.thalia.thaliabeta.Model.UploadFileResponse;
import atec.thalia.thaliabeta.Model.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Joao Mirante on 18/11/2018.
 */

public class SendFile {


    FileService fileService;
    Call<UploadFileResponse> call;



    public SendFile(String url,String map,String hash,String parameter,File file) {

        fileService = RetroFitClient.getClient(url).create(FileService.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        RequestBody hashbody = RequestBody.create(MediaType.parse("application/json"),hash);

        MultipartBody.Part body = MultipartBody.Part.createFormData(parameter,file.getName(),requestBody);

        call = fileService.upload(body,hashbody,map);


    }

    public Call<UploadFileResponse> getCall() {
        return call;
    }

    public static class RetroFitClient {

        public static Retrofit retrofit = null;

        public static Retrofit getClient(String url){
            if(retrofit == null){
                retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
            }


            return retrofit;
        }

    }

    public class FileInfo {
        String filename;
        long size;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }
    }


    public interface FileService{
        @Multipart
        @POST("{map}")
        Call<UploadFileResponse> upload(@Part MultipartBody.Part file, @Part("hash") RequestBody hash, @Path("map") String map);
    }

}
