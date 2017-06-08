package com.doricovix.utif.phpdroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by utif on 6/8/2017.
 */

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    //1
    private Context context;
    private List<MyData> my_data;

    public CustomAdapter(Context context, List<MyData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    //4
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        return new ViewHolder(itemView);
    }

    //5
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.description.setText(my_data.get(position).getDescription());
        Glide.with(context).load(my_data.get(position).getImage()).into(holder.imageView);
    }

    //3
    @Override
    public int getItemCount() {
        return my_data.size();
    }

    //2
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView description;
        public ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);

            description = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
