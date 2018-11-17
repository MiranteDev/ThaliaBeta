package atec.thalia.thaliabeta;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import atec.thalia.thaliabeta.Activity.Av_Pesquisa;
import atec.thalia.thaliabeta.Fragments.Frag_home;

public class MainActivity extends AppCompatActivity {


    LinearLayout pesq;
    BottomNavigationView bottomNavigationView;
    Frag_home frag_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Inicializar os fragmentos
        frag_home = new Frag_home();
        bottomNavigationView = findViewById(R.id.MA_nav_bar);


        pesq = findViewById(R.id.avM_pesq);

        pesq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Av_Pesquisa.class);
                overridePendingTransition(R.animator.fade_in,R.animator.fade_out);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){

                    case R.id.nav_home:
                        setFragment(frag_home);
                        return true;
                }

                return true;
            }
        });


        setFragment(frag_home);

    }

    private void setFragment(Fragment fragment){

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();

    }
}
