package pi2.econommarket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class ListarAdapter extends RecyclerView.Adapter<ContactViewHolder>{

    private Context context;
    private List<Contact> contactBook;

    public ListarAdapter(Context context, List<Contact> contactBook){
        this.context = context;
        this.contactBook = contactBook;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.listar, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position){
        Contact contact = contactBook.get(position);

        holder.listarName.setText(contact.getName());
        holder.listaEmail.setText(contact.getEmail());
    }

}
