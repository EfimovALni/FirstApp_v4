package cz.firstapp.firstapp_v4;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cz.firstapp.firstapp_v4.model.Initial_screen;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {
    private Context mContext;
    private List<Initial_screen> mData;
    private MainActivity mainActivity;

    private static final String TAG = "Res ";

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setmData(List<Initial_screen> mData) {
        this.mData = mData;
    }

    public AdapterRecyclerView(Context mContext, List<Initial_screen> mData) {
        this.mContext = mContext;
        this.mData = mData;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Initial_screen example = mainActivity.mDataFromServer.get(position);
        Initial_screen example = mData.get(position);

        holder.tvNameIco.setText(example.getText());
        holder.tvNameColor.setText(example.getColor());
        holder.tvIcoBase.setText(example.getIcon());

        //  TODO: Работает с захардкореными даннами, а с инете не хочет ;(
        String s = example.getIcon();
        String str_icon = s.replace("data:image/png;base64,", "");
        byte[] decodedString = Base64.decode(str_icon, Base64.DEFAULT);
        holder.ivIco.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
//        //  TODO: Работает с захардкореными даннами!

//        String s = mData.get(position).getIcon();
//        String str_icon = s.replace("data:image/png;base64,", "");
//        byte[] decodedString = Base64.decode(str_icon, Base64.DEFAULT);
//        holder.ivIco.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));

//        holder.ivIco.setImageResource(R.drawable.application);    //TODO: *Work*


//        holder.ivIco.setImageBitmap(mainActivity.decodeImg(mData.get(position).getIcon()));    //TODO: *Doesn't work* ?
//        Log.d(TAG, "onBindViewHolder: " + mData.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
//        return mainActivity.mDataFromServer == null ? 0 : mainActivity.mDataFromServer.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvNameIco;
        protected TextView tvNameColor;
        protected ImageView ivIco;
        protected TextView tvIcoBase;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameIco = (TextView) itemView.findViewById(R.id.tv_name_ico);
            tvNameColor = (TextView) itemView.findViewById(R.id.tv_name_color);
            ivIco = (ImageView) itemView.findViewById(R.id.iv_ico);
            tvIcoBase = (TextView) itemView.findViewById(R.id.tv_ico_base);
        }
    }


//    /**
//     * Method for DeSerialize picture ........................................................ Start
//     */
//    public Bitmap decodeImgNew(String str_icon_raw) {
//
//
//        String s = mData.get;
//        String str_icon = s.replace("data:image/png;base64,", "");
//        byte[] decodedString = Base64.decode(str_icon, Base64.DEFAULT);
//
//
//
//        /** Preparing Base64 for Deserialize ico */
//        String str_icon = str_icon_raw.replace(
//                "data:image/png;base64,", "");
//        byte[] decodedString = Base64.decode(str_icon, Base64.DEFAULT);
//
//        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//    }
//    /* Method for DeSerialize picture ........................................................ End */
}
