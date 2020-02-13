package com.poc.telstraproject.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.poc.telstraproject.R;
import com.poc.telstraproject.model.RowData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter <ListAdapter.RowHolder> {

    private List<RowData> rows ;

    public ListAdapter(List<RowData> rows) {
        this.rows = rows;
    }

    public void setData(List<RowData> rows)
    {
        this.rows = rows;
    }


    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_row,parent,false);
        return new RowHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        RowData row = rows.get(position);
        holder.title.setText(row.getTitle());
        holder.description.setText(row.getDescription());
        Picasso.get().load(row.getImageHref()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(rows != null && rows.size() > 0)
        {
            return rows.size();
        }
        return 0;
    }

    class RowHolder extends  RecyclerView.ViewHolder{
        private TextView title;
        private TextView description;
        private ImageView imageView;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            description = itemView.findViewById(R.id.text_view_desc);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
