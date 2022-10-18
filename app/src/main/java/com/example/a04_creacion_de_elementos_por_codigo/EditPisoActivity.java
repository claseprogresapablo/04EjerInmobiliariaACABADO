package com.example.a04_creacion_de_elementos_por_codigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a04_creacion_de_elementos_por_codigo.configuraciones.Constantes;
import com.example.a04_creacion_de_elementos_por_codigo.databinding.ActivityAddPisoBinding;
import com.example.a04_creacion_de_elementos_por_codigo.databinding.ActivityEditPisoBinding;
import com.example.a04_creacion_de_elementos_por_codigo.databinding.ActivityMainBinding;
import com.example.a04_creacion_de_elementos_por_codigo.modelos.Piso;

public class EditPisoActivity extends AppCompatActivity {


    private ActivityEditPisoBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //2-Construye el binding
        binding = ActivityEditPisoBinding.inflate(getLayoutInflater());
        //3- Asocia el binding a la activity
        setContentView(binding.getRoot());



        //FASE 1-> CONSEGUIR LA INFORMACION
        //RECUOERAR EL INTENT
        Intent intent = getIntent();
        //RECUPERAR BUNDLE
        Bundle bundle = intent.getExtras();
        //SI NO ES NULL HAY UN STRING
        if (bundle != null){

            Piso p = (Piso) bundle.getSerializable(Constantes.INMUEBLE);
            //FASE 2-> MOSTRAR LOS DATOS EN LA INTERFAZM
            inicializaVariables(p);

        }
        
        binding.btnEliminarEditPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bundle!=null){
                    int num = bundle.getInt(Constantes.NUMERO);

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constantes.ELIMINAR, num);
                    intent.putExtras(bundle);
                    //devolver
                    setResult(RESULT_OK,intent);
                    finish();

                } else {
                    Toast.makeText(EditPisoActivity.this, "no hay", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnEditarEditPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int num = bundle.getInt(Constantes.NUMERO);
                Piso p = createPiso();
                if (p!=null){

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.EDITAR,p);
                    bundle.putInt(Constantes.NUMERO,num);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();

                } else {
                    Toast.makeText(EditPisoActivity.this, "fALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializaVariables(Piso p) {
        binding.txtDireccionEditPiso.setText(p.getDireccion());
        binding.txtNumEditPiso.setText(String.valueOf(p.getNumero()));
        binding.txtCiudadEditPiso.setText(p.getCiudad());
        binding.txtProvinciaEditPiso.setText(p.getProvincia());
        binding.txtCPEditPiso.setText(p.getCp());
        binding.rbValPisoEditPiso.setRating(p.getValoracion());
    }

    private Piso createPiso() {

        if (binding.txtDireccionEditPiso.getText().toString().isEmpty() || binding.txtNumEditPiso.getText().toString().isEmpty() || binding.txtCiudadEditPiso.getText().toString().isEmpty() ||
                binding.txtProvinciaEditPiso.getText().toString().isEmpty() || binding.txtCPEditPiso.getText().toString().isEmpty()){

            return null;

        }


        return new Piso(binding.txtDireccionEditPiso.getText().toString(),
                Integer.parseInt(binding.txtNumEditPiso.getText().toString()),
                binding.txtCiudadEditPiso.getText().toString(),
                binding.txtProvinciaEditPiso.getText().toString(),
                binding.txtCPEditPiso.getText().toString(),
                binding.rbValPisoEditPiso.getRating());




    }
}