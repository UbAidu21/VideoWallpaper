package com.ubaidxdev.videowallpaper.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ubaidxdev.videowallpaper.InterfaceClasses.GifInterface;
import com.ubaidxdev.videowallpaper.R;
import java.util.ArrayList;

public class GifWallpaperAdapter extends RecyclerView.Adapter<GifWallpaperAdapter.ViewHolder> {
    Context context;
    ArrayList<Integer> arrayList;
    GifInterface gifInterface;

    public GifWallpaperAdapter(Context context, ArrayList<Integer> arrayList, GifInterface gifInterface) {
        this.context = context;
        this.arrayList = arrayList;
        this.gifInterface = gifInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(arrayList.get(position)).into(holder.imageView);

        holder.bindData(gifInterface);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        void bindData(GifInterface gifInterface){

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gifInterface.getGif(getAdapterPosition());
                }
            });

        }
    }
}
