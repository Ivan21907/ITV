package cat.dam.ivan.itv;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import cat.dam.ivan.itv.database.DataBaseHelper;

public class MantenimentActivity extends AppCompatActivity
{

    private EditText etMatricula, etModel, etColor, etAnyItv;
    private Button btnAfegir, btnTornarMenu;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manteniment);
        initViews();
        initDataBaseHelper();
        initListeners();


    }

    private void initViews() {
        etMatricula = findViewById(R.id.et_matricula);
        etModel = findViewById(R.id.et_model);
        etColor = findViewById(R.id.et_color);
        etAnyItv = findViewById(R.id.et_any_itv);
        btnAfegir = findViewById(R.id.btn_afegir);
        btnTornarMenu = findViewById(R.id.btn_menuPrincipal);
    }

    private void initListeners() {
        btnAfegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matricula = etMatricula.getText().toString();
                String model = etModel.getText().toString();
                String color = etColor.getText().toString();
                String anyItv = etAnyItv.getText().toString();
                Cotxe cotxe = new Cotxe(matricula, model, color, anyItv);
                dataBaseHelper.addCar(cotxe);
            }
        });

        btnTornarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MantenimentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDataBaseHelper() {
        dataBaseHelper = new DataBaseHelper(this);
    }



}
