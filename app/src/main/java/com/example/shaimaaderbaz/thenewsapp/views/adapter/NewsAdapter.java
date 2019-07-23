package com.example.shaimaaderbaz.thenewsapp.views.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaimaaderbaz.thenewsapp.R;
import com.example.shaimaaderbaz.thenewsapp.data.model.Article;
import com.example.shaimaaderbaz.thenewsapp.views.utils.SimpleGestureFilter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final NewsItemListener listener;

    private List<Article> dataSet;

    public NewsAdapter(List<Article> dataSet, NewsItemListener listener) {
        this.dataSet = dataSet;
        this.listener = listener;
    }


    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new NewsAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final NewsAdapter.ViewHolder holder, int position) {
        if (dataSet.get(position) != null) {
            holder.bind(dataSet.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_item_image)
        ImageView itemImage;

        @BindView(R.id.tv_item_name)
        TextView itemName;

        private SimpleGestureFilter simpleGestureFilter;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            simpleGestureFilter = new SimpleGestureFilter(v.getContext(), new SimpleGestureFilter.SimpleGestureListener() {
                @Override
                public void onSwipe(int direction) {
                    listener.onItemSwiped(dataSet.get(getAdapterPosition()));
                }

                @Override
                public void onDoubleTap() {

                }
            });
        }

        void bind(Article item) {
            itemName.setText(item.getTitle());
            String imageUrl = item.getUrlToImage();
            Picasso.with(itemView.getContext()).load(imageUrl)
                    .resize(2048, 1600)
                    .onlyScaleDown()
                    .into(itemImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onItemClicked(dataSet.get(getAdapterPosition()));
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null)
                        listener.onItemLongClicked(dataSet.get(getAdapterPosition()));
                    return true;
                }
            });
        }
    }

    public interface NewsItemListener {
        void onItemClicked(Article article);
        void onItemSwiped(Article article);
        void onItemLongClicked(Article article);
    }
}
