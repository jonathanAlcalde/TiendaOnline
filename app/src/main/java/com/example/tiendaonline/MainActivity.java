package com.example.tiendaonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase mibase;
    DatabaseReference miReferencia;

    ArrayList <Cliente> listaClientes;

    EditText edtCodigo;
    Button btnIngresar;
    Button btnRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mibase = FirebaseDatabase.getInstance();
        miReferencia = mibase.getReference();

        listaClientes = new ArrayList<>();
        edtCodigo =findViewById(R.id.edtCodigo);
        btnIngresar = findViewById(R.id.btnIngresar);

        miReferencia.child("clientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    listaClientes.clear();
                    for(DataSnapshot dato:dataSnapshot.getChildren()){
                        Cliente unCliente = dato.getValue(Cliente.class);
                        listaClientes.add(unCliente);
                        Toast.makeText(MainActivity.this,"Cliente" + unCliente.nombre,Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String codigo = edtCodigo.getText().toString();
                boolean correcto = false;

                for(Cliente micliente:listaClientes){
                    if(codigo.equalsIgnoreCase(micliente.codigo)) {
                        correcto=true;
                        Intent entrar = new Intent(MainActivity.this,ProductosActivity.class);
                        entrar.putExtra("cliente",micliente.id);
                        startActivity(entrar);
                        Toast.makeText(MainActivity.this, "Correcto: Bienvenido " +micliente.nombre , Toast.LENGTH_LONG).show();

                    }
                }
                if(!correcto){
                    Toast.makeText(MainActivity.this,"Cliente no exite",Toast.LENGTH_LONG).show() ;
                }

            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent registro = new Intent(MainActivity.this,crearUsuario.class);
                startActivity(registro);
                Toast.makeText(MainActivity.this,"Registrar un nuevo cliente",Toast.LENGTH_LONG).show();
            }

        });






    }
}