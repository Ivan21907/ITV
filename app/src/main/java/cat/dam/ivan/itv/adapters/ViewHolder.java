package cat.dam.ivan.itv.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import cat.dam.ivan.itv.R;

public class ViewHolder extends RecyclerView.ViewHolder
{
    //Aquí obtenim la referència als nostres elements visuals
    private final TextView matricula;
    private final ImageView img_delete;
    private final ImageView img_edit;

    //Constructor
    public ViewHolder(View itemView)
    {
        super(itemView);
        matricula = itemView.findViewById(R.id.tv_mat);
        img_delete = itemView.findViewById(R.id.img_delete);
        img_edit = itemView.findViewById(R.id.img_edit);
    }
    //Getters
    public TextView getMatricula()
    {
        return matricula;
    }
    public ImageView getDeleteImg()
    {
        return img_delete;
    }
    public ImageView getEditImg()
    {
        return img_edit;
    }
}
