package cat.dam.ivan.itv.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.dam.ivan.itv.Cotxe;
import cat.dam.ivan.itv.R;
import cat.dam.ivan.itv.database.DataBaseHelper;

public class CustomRecyclerViewItv extends RecyclerView.Adapter<ViewHolderItv> implements Filterable{
    private Context context;
    private ArrayList<Cotxe> dataSet;
    private ArrayList<Cotxe> itemList;
    private DataBaseHelper databaseHelper;

    //Constructor, aquí passem els ítems que mostrarem, és a dir, el model de dades
    public CustomRecyclerViewItv(Context context, ArrayList<Cotxe> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
        this.itemList = dataSet;
        databaseHelper = new DataBaseHelper(context);
    }


    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public ViewHolderItv onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cotxeitv, parent, false);
        return new ViewHolderItv(view);
    }

    public void onBindViewHolder(@NonNull ViewHolderItv holder, int position) {

        //Donem valor als views de cada card mitjançant el ViewHolder
        final Cotxe cotxes = dataSet.get(position);
        holder.getMatricula().setText(cotxes.getMatricula());
        //viewHolder.getEditImg().setOnClickListener(view -> showEditionDialog(item));

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}