package io.amosbake.learnrecyclerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.amosbake.learnrecyclerview.fragment.GuideFragment;
import io.amosbake.learnrecyclerview.fragment.ListviewFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSupportFragmentManager().beginTransaction().replace(R.id.frgContainer,new GuideFragment()).commit();
    }

    public void changeFrg(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frgContainer,fragment).addToBackStack(fragment.getClass().getSimpleName()).commit();
    }
}
