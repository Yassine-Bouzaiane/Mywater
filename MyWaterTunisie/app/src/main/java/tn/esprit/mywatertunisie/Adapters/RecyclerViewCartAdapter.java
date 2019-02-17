package tn.esprit.mywatertunisie.Adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import com.squareup.picasso.Picasso;


import tn.esprit.mywatertunisie.Entities.Cart;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.URLs;

public class RecyclerViewCartAdapter extends BaseAdapter {


    private List<Cart> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public RecyclerViewCartAdapter(Context aContext, List<Cart> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.cart_list_item_layout, parent, false);


            holder = new ViewHolder();
            holder.img_prod =  convertView.findViewById(R.id.img_prod2);
            holder.tv_name =  convertView.findViewById(R.id.tv_name2);
            holder.tv_price =  convertView.findViewById(R.id.tv_description2);


            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Cart cart = listData.get(position);
        holder.tv_name.setText(cart.getNom());
        holder.tv_price.setText(cart.getPrix() + "DT");


        String url_img_prod = URLs.URL_IMG + listData.get(position).getImg();

        Picasso.get().load(url_img_prod).resize(200, 200).into(holder.img_prod);


     //   int imageId = this.getMipmapResIdByName(cart.getImg());
       // holder.img_prod.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).

//    public int getMipmapResIdByName(String resName) {
//        String pkgName = context.getPackageName();
//        // Return 0 if not found.
//        int resID = context.getResources().getIdentifier(resName, "mipmap", pkgName);
//        Log.i("CustomListView", "Res Name: " + resName + "==> Res ID = " + resID);
//        return resID;
//    }

    static class ViewHolder {
        ImageView img_prod;
        TextView tv_name;
        TextView tv_price;
    }


}







        //extends RecyclerView.Adapter<RecyclerViewCartAdapter.MyViewHolder> {


/*    Context ctx;
    List<Cart> mData;

    public RecyclerViewCartAdapter(Context ctx, List<Cart> mData) {
        Log.e("adapt ","adapt1");
        Log.e("adapt item",""+mData.size());
        this.ctx = ctx;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.e("adapt ","adapt2");

        View view;
        view = LayoutInflater.from(ctx).inflate(R.layout.item_eaux, viewGroup, false);
        RecyclerViewCartAdapter.MyViewHolder viewHolder = new RecyclerViewCartAdapter.MyViewHolder(view);
        return viewHolder;    }

    *//*@NonNull
    @Override
    public  onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.e("adapt ","adapt2");

        View view;
        view = LayoutInflater.from(ctx).inflate(R.layout.item_eaux, viewGroup, false);
        RecyclerViewCartAdapter.MyViewHolder viewHolder = new RecyclerViewCartAdapter.MyViewHolder(view);
        return viewHolder;
    }*//*

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCartAdapter.MyViewHolder myViewHolder, final int position) {
        Log.e("adapt ","adapt3");

        myViewHolder.tv_name.setText(mData.get(position).getNom());
        myViewHolder.tv_price.setText(mData.get(position).getPrix()+" DT");

        String url_img_prod = URLs.URL_IMG + mData.get(position).getImg();

        Picasso.get().load(url_img_prod).resize(200, 200).into(myViewHolder.img_prod);

   *//*     myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CLICkING","item Clicked before");

                Intent intent = new Intent(ctx.getApplicationContext(), DetailsProdActivity.class);
                intent.putExtra("idProd",mData.get(position).getIdProd());
                ctx.startActivity(intent);
                Log.e("CLICkING","item Clicked after");

            }
        });
   *//*

    }

    @Override
    public int getItemCount() {
        Log.e("adapt ","adapt3");

        if (mData==(null) )
            return 0;
        else
        { Log.e("size item",""+mData.size());
        return mData.size();}
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_price;
        private ImageView img_prod;

        public MyViewHolder(View itemView) {

            super(itemView);
            Log.e("adapt ","adapt4");

            tv_name = itemView.findViewById(R.id.prod_name);
            tv_price = itemView.findViewById(R.id.prod_price);
            img_prod = itemView.findViewById(R.id.image_prod_eau);
        }
    }*/
