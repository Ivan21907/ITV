package cat.dam.ivan.itv.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import cat.dam.ivan.itv.Cotxe;
import cat.dam.ivan.itv.R;
import cat.dam.ivan.itv.database.DataBaseHelper;

public class CustomRecyclerView extends RecyclerView.Adapter<ViewHolder> implements Filterable{
    private Context context;
    private ArrayList<Cotxe> dataSet;
    private ArrayList<Cotxe> itemList;
    private DataBaseHelper databaseHelper;

    //Constructor, aquí passem els ítems que mostrarem, és a dir, el model de dades
    public CustomRecyclerView(Context context, ArrayList<Cotxe> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
        this.itemList = dataSet;
        databaseHelper = new DataBaseHelper(context);
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    dataSet = itemList;
                } else {
                    ArrayList<Cotxe> filteredList = new ArrayList<>();
                    for (Cotxe row : itemList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMatricula().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    dataSet = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataSet;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataSet = (ArrayList<Cotxe>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cotxe, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Donem valor als views de cada card mitjançant el ViewHolder
        final Cotxe cotxes = dataSet.get(position);
        holder.getMatricula().setText(cotxes.getMatricula());
        holder.getEditImg().setOnClickListener(view -> showEditionDialog(cotxes));
        holder.getDeleteImg().setOnClickListener(view -> {
            //elimina fila de la base de dades
            databaseHelper.deleteCotxe(cotxes.getId());
            //refresca la pàgina de l'activity per veure canvis
            ((Activity)context).finish();
            context.startActivity(((Activity) context).getIntent());
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    //Mostra el diàleg d'edició que permet editar un contacte
    private void showEditionDialog(final Cotxe item){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.dialog_update_cotxe, null);
        final EditText et_matricula = subView.findViewById(R.id.et_matricula);
        final EditText et_model = subView.findViewById(R.id.et_model);
        final EditText et_color = subView.findViewById(R.id.et_color);
        final EditText et_itv = subView.findViewById(R.id.et_itv);
        if(item != null)
        {
            et_matricula.setText(item.getMatricula());
            et_model.setText(item.getModel());
            et_color.setText(item.getColor());
            et_itv.setText(item.getAnyItv());
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.cotxe_edition);
        alertDialog.setView(subView);
        alertDialog.create();
        alertDialog.setPositiveButton(R.string.edit_car, (dialog, which) -> {
            final String matricula = et_matricula.getText().toString();
            final String model = et_model.getText().toString();
            final String color = et_color.getText().toString();
            final String itv = et_itv.getText().toString();

            if(TextUtils.isEmpty(matricula)){
                Toast.makeText(context, R.string.noadd_error, Toast.LENGTH_LONG).show();
            }
            else{
                if (item != null) {
                    databaseHelper.updateCotxe(new Cotxe(item.getId(), matricula, model, color, itv));
                    //refresh the activity
                    ((Activity) context).finish();
                    context.startActivity(((Activity) context).getIntent());
                }
                else {
                    Toast.makeText(context, R.string.contact_null, Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, (dialog, which) -> Toast.makeText(context, R.string.cancelled_task, Toast.LENGTH_LONG).show());
        alertDialog.show();
    }
}