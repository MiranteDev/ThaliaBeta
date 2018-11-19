package atec.thalia.thaliabeta;

import android.app.Application;
import android.content.SharedPreferences;

import atec.thalia.thaliabeta.Model.Post;
import atec.thalia.thaliabeta.Model.User;

/**
 * Created by Joao Mirante on 18/11/2018.
 */

public class AppThalia extends Application {

    public static final String KEY="hash";

    SharedPreferences sharedPreferences;
    String hash;
    User user;
    Post post;


    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences(AppThalia.class.getName(),MODE_PRIVATE);
        user = new User();
        post = null;
        if(sharedPreferences.contains(KEY)){
           hash = sharedPreferences.getString(KEY,"");
        }else{
            hash=null;
        }


    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHash() {
        if(sharedPreferences.contains(KEY)){
            return sharedPreferences.getString(KEY,"");
        }

        return null;
    }

    public void setHash(String hash) {
        this.hash = hash;
        sharedPreferences.edit().putString(KEY,hash).commit();

    }
}
