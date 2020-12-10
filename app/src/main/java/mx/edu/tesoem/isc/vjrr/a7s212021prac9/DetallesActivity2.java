package mx.edu.tesoem.isc.vjrr.a7s212021prac9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetallesActivity2 extends AppCompatActivity {

    TextView lblnombre, lbledad, lblsexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles2);

        lblnombre = findViewById(R.id.elblNombre);
        lbledad = findViewById(R.id.dlblEdad);
        lblsexo = findViewById(R.id.dlblSexo);

        String nombre = getIntent().getExtras().get("Nombre").toString();
        DatosParcelable dato = getIntent().getParcelableExtra("DatosParcerable");

        lblnombre.setText(dato.getNombre());
        lbledad.setText(dato.getEdad());
        lblsexo.setText(dato.getSexo());

        getSupportActionBar().setTitle(nombre);
    }
}