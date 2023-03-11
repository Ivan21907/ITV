package cat.dam.ivan.itv.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cat.dam.ivan.itv.R;

public class ViewHolderItv extends RecyclerView.ViewHolder {
    //Aquí obtenim la referència als nostres elements visuals
    private final TextView matricula;
    private final ImageView img_edit;

    //Constructor
    public ViewHolderItv(View itemView) {
        super(itemView);
        matricula = itemView.findViewById(R.id.tv_matricula);
        img_edit = itemView.findViewById(R.id.img_edit);
    }
    //Getters
    public TextView getMatricula() {
        return matricula;
    }
    public ImageView getEditImg() {
        return img_edit;
    }

}
