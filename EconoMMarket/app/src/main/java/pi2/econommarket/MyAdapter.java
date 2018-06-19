package pi2.econommarket;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Produtos> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtHeader;
        private ImageView icone;
        private TextView txtFooter;
        public View layout;

        private ViewHolder(View v) {
            super(v);
            layout = v;
            icone = (ImageView) v.findViewById(R.id.imgProduto);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
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
        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove(position);
            }
        });

        Picasso.get()
                .load(produto.getImagem())
                .into(holder.icone);
        holder.txtFooter.setText("R$: " + produto.getPreco() + " Categoria: " + produto.getCategoria());
    }


    @Override
    public int getItemCount() {
        return values.size();
    }
}
