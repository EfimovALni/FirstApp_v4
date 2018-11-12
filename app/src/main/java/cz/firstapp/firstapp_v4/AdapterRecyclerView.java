package cz.firstapp.firstapp_v4;


import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
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
    public String apiPressedButton;
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

//    Логика наполнения view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Initial_screen example = mData.get(position);
        holder.tvNameIco.setText(example.getText()); // ез костылей должно быть так!!!!!

        /** Convert Based64 to image*/
        String s = example.getIcon();
        String str_icon = s.replace("data:image/png;base64,", "");
        byte[] decodedString = Base64.decode(str_icon, Base64.DEFAULT);
        holder.ivIco.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));

        //  Set click listener
//        holder.cardView.setOnClickListener(new );


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                //  Passing data to the Second Activity
                Intent intent = new Intent(mContext, SecondActivity.class);
                intent.putExtra("NameIco", mData.get(position).getText());
                intent.putExtra("Ico", mData.get(position).getIcon()); //TODO: ---------------!
                intent.putExtra("api", mData.get(position).getApi());

//                // Вычисление API нажатой кнопки (при нажатии)
                mContext.startActivity(intent);
            }
        });
    }



    /**
     * Method for getting 'API of pressed button'
     */
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

//    Парсим Layout
    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvNameIco;
        protected ImageView ivIco;
        protected CardView cardView;    // Buttons on the main screen

//        собственно ViewHolder – привычная заглушка
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameIco = (TextView) itemView.findViewById(R.id.tv_name_ico);
            ivIco = (ImageView) itemView.findViewById(R.id.iv_ico);
            cardView = (CardView) itemView.findViewById(R.id.cv_buttons_on_main_screen);
        }
    }

}
