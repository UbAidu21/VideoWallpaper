package com.ubaidxdev.videowallpaper;

import android.app.WallpaperManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.ubaidxdev.videowallpaper.Utils.Util;

import java.io.IOException;
import java.io.InputStream;

public class GIFLiveWallpaper extends WallpaperService {

    public Engine onCreateEngine() {
        return new GIFWallpaperEngine();
    }

    private class GIFWallpaperEngine extends Engine {

        private SurfaceHolder holder;
        private Movie movie;
        private boolean visible;
        private final Handler handler;
        private float scaleRatio;
        private float y;
        private float x;
        private Paint paint;

        public GIFWallpaperEngine() {
            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            handler = new Handler();

        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            this.holder = surfaceHolder;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            handler.removeCallbacks(drawGIF);
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);

            // Load your GIF here
            try {
                InputStream inputStream = getResources().openRawResource(Util.gif);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                movie = Movie.decodeByteArray(bytes, 0, bytes.length);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);

            int mMovieWidth = movie.width();
            int mMovieHeight = movie.height();
            scaleRatio = Math.max((float) width / mMovieWidth, (float) height / mMovieHeight);

            this.x = (width - (mMovieWidth * scaleRatio)) / 2;
            this.y = (height - (mMovieHeight * scaleRatio)) / 2;
            x = x / scaleRatio;
            y = y / scaleRatio;

            // Set wallpaper offset and zoom out to span both screens
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
        }

        private final Runnable drawGIF = this::draw;
        @Override
        public void onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Handle touch down event (e.g., change color)
                    paint.setColor(Color.GREEN);
                    break;
                case MotionEvent.ACTION_UP:
                    // Handle touch up event (e.g., revert to the original color)
                    paint.setColor(Color.BLUE);
                    break;
                // Handle other touch events if needed
            }
        }

        private void draw() {
            if (visible) {
                Canvas canvas = holder.lockCanvas();
                canvas.save();
                canvas.scale(scaleRatio, scaleRatio);
                movie.draw(canvas, x, y);
                canvas.restore();
                holder.unlockCanvasAndPost(canvas);
                movie.setTime((int) (System.currentTimeMillis() % movie.duration()));
                handler.removeCallbacks(drawGIF);
                int frameDuration = 0;
                handler.postDelayed(drawGIF, frameDuration);
            }
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
                handler.post(drawGIF);
            } else {
                handler.removeCallbacks(drawGIF);
            }
        }
    }
}
