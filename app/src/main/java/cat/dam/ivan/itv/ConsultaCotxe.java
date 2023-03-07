package cat.dam.ivan.itv;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cat.dam.ivan.itv.adapters.CustomRecyclerView;
import cat.dam.ivan.itv.database.DataBaseHelper;

public class ConsultaCotxe extends AppCompatActivity
{

    private Button btnBack;
    private CustomRecyclerView mAdapter;
    private RecyclerView rc_nameList;
    private DataBaseHelper dataBaseHelper;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_cotxe);
        initViews();
        initListeners();
        initRecyclerView();
        initDataBaseHelper();

    }

    private void initViews() {
        rc_nameList = findViewById(R.id.rc_nameList);
        btnBack = findViewById(R.id.btn_menuPrincipal);
    }

    private void initListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultaCotxe.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rc_nameList.setLayoutManager(linearLayoutManager);
        rc_nameList.setHasFixedSize(true);

    }

    private void initDataBaseHelper() {
        dataBaseHelper = new DataBaseHelper(this);
        ArrayList<Cotxe> cotxes = dataBaseHelper.getCars();
        if(cotxes.size() > 0){
            rc_nameList.setVisibility(View.VISIBLE);
            mAdapter = new CustomRecyclerView(this, cotxes);
            rc_nameList.setAdapter(mAdapter);
        }else{
            rc_nameList.setVisibility(View.GONE);
            Toast.makeText(this, R.string.no_cotxes, Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView =  (SearchView) search.getActionView();
        search(searchView);
        return true;
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String nouText) {
                if (mAdapter!=null)
                    mAdapter.getFilter().filter(nouText);
                return true;
            }
        });
    }




}
