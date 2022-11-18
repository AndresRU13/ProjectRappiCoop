package com.unipiloto.projectrappicoop.Adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unipiloto.projectrappicoop.FormOrder.FormCreateOrder;
import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.R;

import java.io.Serializable;
import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ProductosViewHolder> {

    Context context;
    Button btnVerCarro;
    List<Products> listaProductos;
    List<Products> carroCompra;

    public AdaptadorProductos(Context context, Button btnVerCarro, List<Products> listaProductos, List<Products> carroCompra) {
        this.context = context;
        this.btnVerCarro = btnVerCarro;
        this.listaProductos = listaProductos;
        this.carroCompra = carroCompra;
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_productos, null, false);
        return new AdaptadorProductos.ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder productosViewHolder, @SuppressLint("RecyclerView") final int i) {
        productosViewHolder.tvNomProducto.setText(listaProductos.get(i).getNombre());
        productosViewHolder.tvDescripcion.setText(listaProductos.get(i).getDescripcion());
        productosViewHolder.tvPrecio.setText(""+listaProductos.get(i).getValor());

        productosViewHolder.cbCarro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(productosViewHolder.cbCarro.isChecked() == true) {
                    carroCompra.add(listaProductos.get(i));
                } else if(productosViewHolder.cbCarro.isChecked() == false) {
                    carroCompra.remove(listaProductos.get(i));
                }
            }
        });

        btnVerCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FormCreateOrder.class);
                intent.putExtra("CarroCompras", (Serializable) carroCompra);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomProducto, tvDescripcion, tvPrecio;
        CheckBox cbCarro;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNomProducto = itemView.findViewById(R.id.tvNomProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            cbCarro = itemView.findViewById(R.id.cbCarro);
        }
    }
}
