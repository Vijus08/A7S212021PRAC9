package mx.edu.tesoem.isc.vjrr.a7s212021prac9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gvDatos;
    EditText txtnombre, txtedad, txtsexo;
    List<Datos> datos = new ArrayList<>();
    AdaptadorBase adaptadorBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvDatos = findViewById(R.id.gvDatos);
        txtnombre = findViewById(R.id.txtNombre);
        txtedad = findViewById(R.id.txtEdad);
        txtsexo = findViewById(R.id.txtSexo);

        Veridica();

        gvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datos dato = (Datos) adaptadorBase.getItem(position);
                DatosParcelable datosParcelable = new DatosParcelable(dato.getNombre(), dato.getEdad(), dato.getSexo());
                Intent intent = new Intent(MainActivity.this, DetallesActivity2.class);
                intent.putExtra("Nombre", dato.getNombre());
                intent.putExtra("DatosParcerable", datosParcelable);
                startActivity(intent);
            }
        });
    }

    private void Veridica(){
        Almacen conexion = new Almacen();

        if (conexion.Existe(this)){
            if (conexion.Leer(this)){
                datos = conexion.getDatos();
                adaptadorBase = new AdaptadorBase(datos, this);
                gvDatos.setAdapter(adaptadorBase);
            }else{
                Toast.makeText(this, "No se pudo leer la información", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "No existe el archivo, favor de gravar información", Toast.LENGTH_SHORT).show();
        }
    }

    public void Graba(View v){
        Datos dato = new Datos();
        Almacen conexion = new Almacen();

        dato.setNombre(txtnombre.getText().toString());
        dato.setEdad(txtedad.getText().toString());
        dato.setSexo(txtsexo.getText().toString());

        datos.add(dato);
        if (conexion.Escribir(this,datos)){
            Toast.makeText(this, "Se escribieron correctamente", Toast.LENGTH_SHORT).show();
            Veridica();
        }else{
            Toast.makeText(this, "Error al escribir...", Toast.LENGTH_SHORT).show();
        }
    }

}