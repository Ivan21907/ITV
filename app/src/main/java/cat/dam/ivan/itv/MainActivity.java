package cat.dam.ivan.itv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    private Button btnManteniment, btnConsulta, btnItvCaducada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();

    }

    private void initViews() {
        btnManteniment = findViewById(R.id.btn_manteniment);
        btnConsulta = findViewById(R.id.btn_consulta);
        btnItvCaducada = findViewById(R.id.btn_itv_caducada);
    }

    private void initListeners() {
        btnManteniment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MantenimentActivity.class);
                startActivity(intent);
            }
        });
        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConsultaCotxe.class);
                startActivity(intent);
            }
        });
        btnItvCaducada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConsultaCotxeItvCaducada.class);
                startActivity(intent);
            }
        });
    }



}