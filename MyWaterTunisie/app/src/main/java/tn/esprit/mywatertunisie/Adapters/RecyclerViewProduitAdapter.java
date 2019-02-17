package tn.esprit.mywatertunisie.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tn.esprit.mywatertunisie.Activities.DetailsProdActivity;
import tn.esprit.mywatertunisie.Entities.Produit;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.URLs;


public class RecyclerViewProduitAdapter extends RecyclerView.Adapter<RecyclerViewProduitAdapter.MyViewHolder> {
    Context ctx;
    List<Produit> mData;

    public RecyclerViewProduitAdapter(Context ctx, List<Produit> mData) {
        this.ctx = ctx;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//       View view;
//       view= LayoutInflater.from(ctx).inflate(R.layout.item_eaux, viewGroup, false);
        CardView view = (CardView) LayoutInflater.from(ctx)
                .inflate(R.layout.item_eaux, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        myViewHolder.tv_name.setText(mData.get(position).getNom());
        myViewHolder.tv_price.setText(mData.get(position).getPrix()+" DT");

        String url_img_prod = URLs.URL_IMG+mData.get(position).getImg();

        Picasso.get().load(url_img_prod).resize(200,200).into(myViewHolder.img_prod);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CLICKING on Produit","item Clicked before");

                Intent intent = new Intent(ctx.getApplicationContext(), DetailsProdActivity.class);
                intent.putExtra("idProd",mData.get(position).getIdProd());
                ctx.startActivity(intent);
                Log.e("CLICKING on Produit","item Clicked after");

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
      private TextView tv_name;
      private TextView tv_price;
      private ImageView img_prod;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.prod_name);
            tv_price = itemView.findViewById(R.id.prod_price);
            img_prod = itemView.findViewById(R.id.image_prod_eau);
        }
    }
}
