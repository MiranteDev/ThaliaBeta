package atec.thalia.thaliabeta.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import net.eunainter.r2std2oid.RequestR2D2;
import net.eunainter.r2std2oid.ResponseR2D2;
import net.eunainter.r2std2oid.RestObserver;
import net.eunainter.r2std2oid.Skyrunner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import atec.thalia.thaliabeta.Activity.Av_addPost;
import atec.thalia.thaliabeta.AppThalia;
import atec.thalia.thaliabeta.Model.Media;
import atec.thalia.thaliabeta.Model.Post;
import atec.thalia.thaliabeta.Model.User;
import atec.thalia.thaliabeta.R;
import atec.thalia.thaliabeta.Util.WebServices;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frag_home extends Fragment implements RestObserver{

    RecyclerView recyclerView;
    View v;
    RvAdapterPost adapterPost;
    SwipeRefreshLayout swipeRefreshLayout;
    Skyrunner mSky;
    ArrayList<Post> postArrayList;
    Button open,enviarpost;
    AppThalia app;


    public Frag_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_frag_home, container, false);

        recyclerView = v.findViewById(R.id.fragH_rv);

        swipeRefreshLayout = v.findViewById(R.id.swiperefresh);

        app = ((AppThalia)getActivity().getApplication());
        open = v.findViewById(R.id.open);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Av_addPost.class);
                startActivity(intent);
            }
        });



        mSky = new Skyrunner(20);
        mSky.addObserver(this);

        enviarpost = v.findViewById(R.id.eviarpost);
        enviarpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(app.getPost()!=null) {


                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(new Gson().toJson(app.getPost()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    RequestR2D2 req = new RequestR2D2(WebServices.SERVIDOR + WebServices.ADDPOST, jsonObject, RequestR2D2.POST);


                    mSky.sendRequest(req, Skyrunner.RequestTag.KPOSTHREE);

                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getPosts();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(),"Atualizado com sucesso!!",Toast.LENGTH_SHORT);

                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });


        postArrayList = new ArrayList<>();
        /*
        User u = new User();
        u.setFirstname("João");
        u.setLastname("Vilares");
        u.setPathimage("https://cdn.discordapp.com/attachments/393040860918579210/512807052716933122/transferir.png");
        Media m = new Media(Media.IMAGE,"https://cdn.discordapp.com/attachments/393040860918579210/512807052716933122/transferir.png");

        User u1 = new User();
        u1.setFirstname("Sergio");
        u1.setLastname("Figueiredo");
        u1.setPathimage("https://media.licdn.com/dms/image/C5603AQGmHWvUiLqI-A/profile-displayphoto-shrink_800_800/0?e=1547683200&v=beta&t=v6-mJlyXfZz81dbDBQjA0oOvkyWiuvVXPU26hq_3OXo");
        Media m1 = new Media(Media.IMAGE,"https://improvephotography.com/wp-content/uploads/2018/07/Sunrise-in-Rhodes-by-Rick-McEvoy-Photography-001.jpg");


        User u2 = new User();
        u2.setFirstname("José");
        u2.setLastname("Nascimento");
        u2.setPathimage("https://media.licdn.com/dms/image/C4D03AQGYeUV6j-OWEw/profile-displayphoto-shrink_800_800/0?e=1547683200&v=beta&t=rLzTOp1NNBeQXhpn4RXAob6vfzSW4cnI9OZ31R3aOUs");
        Media m2 = new Media(Media.IMAGE,"https://boygeniusreport.files.wordpress.com/2017/05/water.jpg?quality=98&strip=all&w=782");


        postArrayList.add(new Post("123","16-11-2018","Ola a todos isto é o meu primeiro post!",u,m));
        postArrayList.add(new Post("123","16-11-2018","Ola a todos isto é o meu primeiro post!fsdfsdfsdfsdfsdfsdfs",u1,m1));
        postArrayList.add(new Post("123","16-11-2018","Ola a todos isto é o meu primeiro post!fsdfsdfsdfsdfsdfsdfs",u2,m2));
*/


        RequestR2D2 req = new RequestR2D2(WebServices.SERVIDOR+WebServices.GETPOSTS,null,RequestR2D2.GET);


        mSky.sendRequest(req,Skyrunner.RequestTag.KPOSONE);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));




        return v;
    }

    public void getPosts(){
        RequestR2D2 req = new RequestR2D2(WebServices.SERVIDOR+WebServices.GETPOSTS,null,RequestR2D2.GET);
        mSky.sendRequest(req,Skyrunner.RequestTag.KPOSTWO);

    }

    @Override
    public void receivedResponse(ResponseR2D2 response) {

        switch (response.getId()){
            case Skyrunner.RequestTag.KPOSONE:

                postArrayList = new Gson().fromJson(response.getJSONArray().toString(),new TypeToken<List<Post>>(){}.getType());
                adapterPost = new RvAdapterPost(postArrayList);
                recyclerView.setAdapter(adapterPost);

               for(Post p:postArrayList){
                   Log.i("msg",p.getContent());
               }

                break;

            case Skyrunner.RequestTag.KPOSTWO:

                postArrayList = new Gson().fromJson(response.getJSONArray().toString(),new TypeToken<List<Post>>(){}.getType());
                adapterPost.notifyDataSetChanged();


            break;

            case Skyrunner.RequestTag.KPOSTHREE:

                app.setPost(null);

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



    public class RvAdapterPost extends RecyclerView.Adapter<RvAdapterPost.VHPost>{

        ArrayList<Post> posts;

        public RvAdapterPost(ArrayList<Post> posts) {
            this.posts = posts;
        }

        @NonNull
        @Override
        public VHPost onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.cv_post,parent,false);
            return new VHPost(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VHPost holder, int position) {
            Post post = posts.get(position);

            Picasso.with(getActivity().getApplicationContext()).load(post.getCreator().getPathimage()).into(holder.getIconuser()); //Put image user into post
            Picasso.with(getActivity().getApplicationContext()).load(post.getMedia().getPath_file()).into(holder.getMediacontent());
            holder.getUsername().setText(post.getCreator().getFirstname()+" "+post.getCreator().getLastname());
            //holder.getWatchers().setText(String.valueOf(post.getCreator().getWatched().size()));
            holder.getTextcontent().setText(post.getContent());
            holder.getLikes().setText(String.valueOf(post.getLikes())+" likes");


            //CLICK OF LIKE BUTTON
            holder.getLike().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            holder.getComment().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        public class VHPost extends RecyclerView.ViewHolder{

            ImageView mediacontent;
            TextView username,watchers,timepost,textcontent,likes,like,comment;

            CircleImageView iconuser;


            public VHPost(View itemView) {
                super(itemView);

                iconuser = itemView.findViewById(R.id.cv_post_usericon);
                mediacontent = itemView.findViewById(R.id.cv_post_mediacontent);
                username = itemView.findViewById(R.id.cv_post_username);
                watchers = itemView.findViewById(R.id.cv_post_userwatchers);
                timepost = itemView.findViewById(R.id.cv_post_timecreate);
                textcontent = itemView.findViewById(R.id.cv_post_textcontent);
                likes = itemView.findViewById(R.id.cv_post_likes);
                like = itemView.findViewById(R.id.cv_post_btlike);
                comment = itemView.findViewById(R.id.cv_post_btcomment);





            }


            public CircleImageView getIconuser() {
                return iconuser;
            }

            public ImageView getMediacontent() {
                return mediacontent;
            }

            public TextView getUsername() {
                return username;
            }

            public TextView getWatchers() {
                return watchers;
            }

            public TextView getTimepost() {
                return timepost;
            }

            public TextView getTextcontent() {
                return textcontent;
            }

            public TextView getLikes() {
                return likes;
            }

            public TextView getLike() {
                return like;
            }

            public TextView getComment() {
                return comment;
            }
        }
    }

}
