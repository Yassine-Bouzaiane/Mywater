package tn.esprit.mywatertunisie.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tn.esprit.mywatertunisie.Activities.AdminDetailsUser;
import tn.esprit.mywatertunisie.Entities.User;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.URLs;


public class AdminRecyclerViewUsersAdapter extends RecyclerView.Adapter<AdminRecyclerViewUsersAdapter.MyViewHolder> {
    Context ctx;
    List<User> mData;

    public AdminRecyclerViewUsersAdapter(Context ctx, List<User> mData) {
        this.ctx = ctx;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view= LayoutInflater.from(ctx).inflate(R.layout.item_user, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        myViewHolder.tv_noms.setText(mData.get(position).getNom());
        myViewHolder.tv_emails.setText(mData.get(position).getEmail());

        String url_img_users = URLs.URL_IMG+mData.get(position).getImageUser();
        Picasso.get().load(url_img_users).resize(150,150).into(myViewHolder.img_users);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CLICKING on user >>>>","item Clicked before");

                Intent intent = new Intent(ctx.getApplicationContext() , AdminDetailsUser.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("idUser",mData.get(position).getId());
                intent.putExtra("nomUser",mData.get(position).getNom());
                intent.putExtra("imageUser",mData.get(position).getImageUser());
                intent.putExtra("adresseUser",mData.get(position).getAdresse());
                intent.putExtra("emailUser",mData.get(position).getEmail());
                intent.putExtra("typeUser",mData.get(position).getTypeUser());
                ctx.startActivity(intent);
                Log.e("CLICkING","item Clicked after");

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_noms;
        private TextView tv_emails;
        private ImageView img_users;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_noms = itemView.findViewById(R.id.admin_userS_names);
            tv_emails = itemView.findViewById(R.id.admin_userS_emails);
            img_users = itemView.findViewById(R.id.admin_userS_images);
        }
    }
}
