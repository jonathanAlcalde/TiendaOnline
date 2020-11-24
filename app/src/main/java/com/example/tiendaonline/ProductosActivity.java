package com.example.tiendaonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.HttpCookie;
import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity {
    TextView tvNombre;
    Button btnCarrito;
    ListView lvProductos;
    Cliente micliente;

    FirebaseDatabase mibase;
    DatabaseReference miReferencia;

    ArrayList<Producto> listaProductos;
    ArrayAdapter<Producto> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        String id = getIntent().getExtras().getString("cliente");
        mibase = FirebaseDatabase.getInstance();
        miReferencia = mibase.getReference();

        tvNombre = findViewById(R.id.verNombre);
        btnCarrito = findViewById(R.id.btnCarrito);
        lvProductos = findViewById(R.id.verProductos);

        listaProductos = new ArrayList<>();

        adaptador = new ArrayAdapter<Producto>(ProductosActivity.this, android.R.layout.simple_list_item_1,listaProductos);

        lvProductos.setAdapter(adaptador);
        miReferencia.child("productos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    listaProductos.clear();
                    for (DataSnapshot dato: dataSnapshot.getChildren()){
                        Producto unProducto = dato.getValue(Producto.class);
                        listaProductos.add(unProducto);
                        adaptador.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        miReferencia.child("clientes").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                micliente = dataSnapshot.getValue(Cliente.class);
                tvNombre.setText(micliente.nombre);

                if(micliente.carrito!=null){
                    btnCarrito.setText(getString(R.string.ver_carrito) + " ("+micliente.carrito.size()+")");
                }
                else{
                    btnCarrito.setText(getString(R.string.ver_carrito));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}