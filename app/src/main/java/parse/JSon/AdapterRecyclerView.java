package parse.JSon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;


public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.myViewHolder> {

    private Context context;
    private ArrayList<ModelItem> itemArrayList;

    public AdapterRecyclerView(Context context, ArrayList<ModelItem> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_recycler_view, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ModelItem currentItem = itemArrayList.get(position);

        String imageUrl = currentItem.getImgUrl();
        String author = currentItem.getAuthor();
        int likes = currentItem.getLikes();

        holder.tvAuthor.setText(author);
        holder.tvLikes.setText("❤ : " + likes);


        // Gestion des erreurs d'affichage ou de chargement de l'image
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.error)
                .placeholder(R.drawable.error);

        //Glide.with(holder.ivImage);
        Glide.with(holder.ivImage.getContext())
                .load(imageUrl)
                .apply(options)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImage);

    }


    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private TextView tvAuthor, tvLikes;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvLikes = itemView.findViewById(R.id.tvLikes);

        }
    }
}
