package cat.dam.ivan.itv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cat.dam.ivan.itv.adapters.CustomRecyclerViewItv;
import cat.dam.ivan.itv.database.DataBaseHelper;

public class ConsultaCotxeItvCaducada extends AppCompatActivity
{
    private Button btnShow;
    private CustomRecyclerViewItv mAdapter;
    private RecyclerView rc_nameList;
    private DataBaseHelper dataBaseHelper;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cotxe_itv_caducada);
        initViews();
        initRecyclerView();
        initDataBaseHelper();

    }

    private void initViews() {
        rc_nameList = findViewById(R.id.rc_nameList);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rc_nameList.setLayoutManager(linearLayoutManager);
        rc_nameList.setHasFixedSize(true);

    }

    private void initDataBaseHelper() {
        dataBaseHelper = new DataBaseHelper(this);
        ArrayList<Cotxe> cotxesItv = dataBaseHelper.getCarsItv();
        if(cotxesItv.size() > 0){
            rc_nameList.setVisibility(View.VISIBLE);
            mAdapter = new CustomRecyclerViewItv(this, cotxesItv);
            rc_nameList.setAdapter(mAdapter);
        }else{
            rc_nameList.setVisibility(View.GONE);
            Toast.makeText(this, "No hi ha cotxes amb matricula caducada", Toast.LENGTH_SHORT).show();

        }


    }



}
