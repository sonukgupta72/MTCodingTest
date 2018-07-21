package com.sonukgupta.mtcodingtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sonukgupta.mtcodingtest.model.PageQSRModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by sonu on 21/7/18.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<PageQSRModel> mModelList;
    private Context mContext;
    SearchResultItemClickCallBack mSearchResultItemClickCallBack;

    public SearchResultAdapter(final List<PageQSRModel> modelList, SearchResultItemClickCallBack addressItemCallback) {
        mModelList = modelList;
        mSearchResultItemClickCallBack = addressItemCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        int adapterPosition = holder.getAdapterPosition();

        PageQSRModel pageQSRModel = mModelList.get(adapterPosition);

        if (pageQSRModel.getThumbnail() != null
                && pageQSRModel.getThumbnail().getSource() != null){
            new DownloadImageTask(holder.ivProfilePic)
                    .execute(pageQSRModel.getThumbnail().getSource());

            //holder.ivProfilePic.setImageBitmap(getBitmapFromURL(pageQSRModel.getThumbnail().getSource()));
        }

        holder.tvFullName.setText(pageQSRModel.getTitle());
        if (pageQSRModel.getTerms() != null
                && pageQSRModel.getTerms().getDescription() != null
                && pageQSRModel.getTerms().getDescription().size() > 0) {

            String prof = "";
            for (int i=0; i < pageQSRModel.getTerms().getDescription().size() ; i++) {
                prof += pageQSRModel.getTerms().getDescription().get(i) + ((i+1)<pageQSRModel.getTerms().getDescription().size() ? ", " : "");
            }

            holder.tvProfession.setText(prof);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchResultItemClickCallBack.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProfilePic;
        private TextView tvFullName, tvProfession;

        public ViewHolder(final View itemView) {
            super(itemView);
            ivProfilePic = itemView.findViewById(R.id.ivProfilePic);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvProfession = itemView.findViewById(R.id.tvProfession);
        }

    }

//    public static Bitmap getBitmapFromURL(String src) {
//        try {
//            URL url = new URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            return myBitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.e("Exception",e.getMessage());
//            return null;
//        }
//    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
