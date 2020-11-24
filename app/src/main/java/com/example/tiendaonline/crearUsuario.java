package com.example.tiendaonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.makeText;

public class crearUsuario extends AppCompatActivity {
    FirebaseDatabase mibase;
    DatabaseReference miReferencia;

    EditText edtnom_Cliente;
    EditText edidCliente;
    EditText edcodigoCliente;
    Button btnRegistrarse;
    CheckBox chTerminos;

    Cliente nuevoCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        btnRegistrarse.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                    String nombre = edtnom_Cliente.getText().toString();
                    String id = edidCliente.getText().toString();
                    String codigo = edcodigoCliente.getText().toString();
                    boolean terminos =  chTerminos.isChecked();
                    if(nombre == null){
                       Toast.makeText(crearUsuario.this,"Escribe el nombre del cliente",Toast.LENGTH_LONG).show();
                    }
                    else if(id == null){
                        Toast.makeText(crearUsuario.this,"Escribe el id del cliente",Toast.LENGTH_LONG).show();
                    }
                    else if(codigo == null){
                        Toast.makeText(crearUsuario.this,"Escribe el codigo del cliente",Toast.LENGTH_LONG).show();
                    }
                    else if(!terminos){
                        Toast.makeText(crearUsuario.this,"Acepta los terminos y condiciones",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        nuevoCliente = new Cliente(id,codigo,nombre);
                        miReferencia.child("clientes").child(nuevoCliente.id).setValue(nuevoCliente);
                        Intent intcrearUsuario = new Intent(crearUsuario.this,MainActivity.class);
                        startActivity(intcrearUsuario);
                        Toast.makeText(crearUsuario.this, "Correcto: Bienvenido " +nuevoCliente.nombre , Toast.LENGTH_LONG).show();
                    }


            }
        });

    }



    //Cliente miguel =  new Cliente("2345poiuy","C002","Miguel");

    //miReferencia.child("clientes").child(miguel.id).setValue(miguel);
}