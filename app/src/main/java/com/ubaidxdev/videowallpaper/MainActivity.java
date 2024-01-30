package com.ubaidxdev.videowallpaper;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ubaidxdev.videowallpaper.Adapter.GifWallpaperAdapter;
import com.ubaidxdev.videowallpaper.InterfaceClasses.GifInterface;
import com.ubaidxdev.videowallpaper.Utils.Util;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> arrayList;
    GifWallpaperAdapter gifWallpaperAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
* */
        arrayList = new ArrayList<>();
//        Button setWallpaperButton = findViewById(R.id.setWallpaperButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

         gifWallpaperAdapter = new GifWallpaperAdapter(this, arrayList, new GifInterface() {
            @Override
            public void getGif(int position) {

//                Util.gif = arrayList.get(position);
//                Util.gif =R.raw.car4;
                try {
                    WallpaperManager.getInstance(MainActivity.this).clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int wallpaperFlags = WallpaperManager.FLAG_SYSTEM;  // Add your desired flags
                final Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                        new ComponentName(MainActivity.this, GIFLiveWallpaper.class.getName()));


                startActivity(intent);
            }
        });

        recyclerView.setAdapter(gifWallpaperAdapter);

//        setWallpaperButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        loadData();

    }

    void loadData(){
        arrayList.add(R.raw.car);
        arrayList.add(R.raw.car2);
        arrayList.add(R.raw.car3);
        arrayList.add(R.raw.car4);
        arrayList.add(R.raw.car5);
        gifWallpaperAdapter.notifyDataSetChanged();

    }
}
