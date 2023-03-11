package cat.dam.ivan.itv;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cat.dam.ivan.itv.database.DataBaseHelper;

public class MantenimentActivity extends AppCompatActivity
{

    //Atributs
    private EditText etMatricula, etModel, etColor, etAnyItv;
    private Button btnAfegir, btnTornarMenu;
    private DataBaseHelper dataBaseHelper;

    //metode onCreate que s'executa quan s'obre l'activitat
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manteniment);
        initViews();
        initDataBaseHelper();
        initListeners();


    }

    //metode que inicialitza els elements de la vista
    private void initViews() {
        etMatricula = findViewById(R.id.et_matricula);
        etModel = findViewById(R.id.et_model);
        etColor = findViewById(R.id.et_color);
        etAnyItv = findViewById(R.id.et_any_itv);
        btnAfegir = findViewById(R.id.btn_afegir);
        btnTornarMenu = findViewById(R.id.btn_menuPrincipal);
    }

    //metode que inicialitza els listeners dels elements de la vista
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
                Toast.makeText(MantenimentActivity.this, R.string.new_car, Toast.LENGTH_SHORT).show();
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

    //metode que inicialitza el DataBaseHelper
    private void initDataBaseHelper() {
        dataBaseHelper = new DataBaseHelper(this);
    }



}
