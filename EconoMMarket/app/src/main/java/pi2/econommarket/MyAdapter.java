package pi2.econommarket;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Produtos> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtHeader;
        private ImageView icone;
        private ImageView mercado;
        private TextView txtFooter;
        public View layout;

        private ViewHolder(View v) {
            super(v);
            layout = v;
            icone = (ImageView) v.findViewById(R.id.imgProduto);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            mercado = (ImageView) v.findViewById(R.id.imgMercado);
        }
    }


    MyAdapter(List<Produtos> myDataset) {
        values = myDataset;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Produtos produto = values.get(position);
        holder.txtHeader.setText(produto.getTitulo());
        if(produto.getMercado().equals("extra"))
            holder.mercado.setImageResource(R.drawable.extra_logo);
        else{
            holder.mercado.setImageResource(R.drawable.pao_logo);
        }

        Picasso.get()
                .load(produto.getImagem())
                .into(holder.icone);
        holder.txtFooter.setText("R$: " + produto.getPreco() + "\nCategoria: " + produto.getCategoria());

    }


    @Override
    public int getItemCount() {
        return values.size();
    }
}
