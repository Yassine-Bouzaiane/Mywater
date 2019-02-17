package tn.esprit.mywatertunisie.Adapters;

import android.content.Context;
import android.content.Intent;
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

import tn.esprit.mywatertunisie.Activities.ChantierActivity;
import tn.esprit.mywatertunisie.Entities.Electricite;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.URLs;


public class RecyclerViewElectAdapter extends RecyclerView.Adapter<RecyclerViewElectAdapter.MyViewHolder> {


    Context ctx;
        List<Electricite> mData;

        public RecyclerViewElectAdapter(Context ctx, List<Electricite> mData) {
            this.ctx = ctx;
            this.mData = mData;
        }

        @NonNull
        @Override
        public RecyclerViewElectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            View view;
//            view= LayoutInflater.from(ctx).inflate(R.layout.item_elect, viewGroup, false);
            CardView view = (CardView) LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_elect, viewGroup, false);

            RecyclerViewElectAdapter.MyViewHolder viewHolder = new RecyclerViewElectAdapter.MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewElectAdapter.MyViewHolder myViewHolder, int position) {
            myViewHolder.tv_titre.setText(mData.get(position).getTitre());
            myViewHolder.tv_decription.setText(mData.get(position).getDecription());
            String url_img_prod = URLs.URL_IMG+mData.get(position).getImageElect();
            Picasso.get().load(url_img_prod).resize(200,200).into(myViewHolder.img_elect);

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("CLICKING on Elect","item Clicked before");

                    Intent intent = new Intent(ctx.getApplicationContext(), ChantierActivity.class);
                    ctx.startActivity(intent);
                    Log.e("CLICKING on Elect","item Clicked after");

                }
            });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder{
            private TextView tv_titre;
            private TextView tv_decription;
            private ImageView img_elect;
            Context ctx;
            public MyViewHolder(View itemView) {
                super(itemView);

                tv_titre = itemView.findViewById(R.id.titre_elect);
                tv_decription = itemView.findViewById(R.id.decription_elect);
                img_elect = itemView.findViewById(R.id.image_elect);

            }

        }
    }
