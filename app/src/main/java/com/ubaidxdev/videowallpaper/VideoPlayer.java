//import android.content.Context;
//import android.net.Uri;
//import android.util.Log;
//import android.view.Surface;
//
//import androidx.annotation.OptIn;
//import androidx.media3.common.util.UnstableApi;
//import androidx.media3.common.util.Util;
//import androidx.media3.datasource.DataSource;
//import androidx.media3.datasource.DefaultDataSourceFactory;
//import androidx.media3.exoplayer.DefaultLoadControl;
//import androidx.media3.exoplayer.DefaultRenderersFactory;
//import androidx.media3.exoplayer.ExoPlayer;
//import androidx.media3.exoplayer.source.MediaSource;
//import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
//import androidx.media3.ui.PlayerView;
//
//public class VideoPlayer {
//
//    private static final String TAG = VideoPlayer.class.getSimpleName();
//    private static ExoPlayer player;
//
//    @UnstableApi public static void initialize(Context context, String videoFileName, Surface surface) {
//        if (player == null) {
//            player = ExoPlayerFactory.newSimpleInstance(
//                    new DefaultRenderersFactory(context),
//                    new DefaultTrackSelector(),
//                    new DefaultLoadControl()
//            );
//
//            PlayerView playerView = new PlayerView(context);
//            playerView.setPlayer(player);
//            player.setVideoSurface(surface);
//            player.setPlayWhenReady(true);
//
//            Uri videoUri = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + videoFileName);
//            MediaSource mediaSource = buildMediaSource(videoUri, context);
//            player.prepare(mediaSource, true, false);
//        } else {
//            Log.e(TAG, "Player is already initialized.");
//        }
//    }
//
//    @OptIn(markerClass = UnstableApi.class) private static MediaSource buildMediaSource(Uri uri, Context context) {
//        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
//                Util.getUserAgent(context, context.getApplicationInfo().packageName));
//        return new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
//    }
//
//    public static void release() {
//        if (player != null) {
//            player.release();
//            player = null;
//        }
//    }
//
//    public static void play() {
//        if (player != null) {
//            player.setPlayWhenReady(true);
//        }
//    }
//
//    public static void pause() {
//        if (player != null) {
//            player.setPlayWhenReady(false);
//        }
//    }
//}
