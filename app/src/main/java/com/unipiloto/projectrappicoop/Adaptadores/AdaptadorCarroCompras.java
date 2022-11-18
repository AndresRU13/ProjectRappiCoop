package com.unipiloto.projectrappicoop.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unipiloto.projectrappicoop.Objects.Products;
import com.unipiloto.projectrappicoop.R;

import java.util.List;

public class AdaptadorCarroCompras extends RecyclerView.Adapter<AdaptadorCarroCompras.ProductosViewHolder> {

    Context context;
    List<Products> carroCompra;
    TextView tvTotal;
    EditText viewValue, viewProducts, viewProvider;
    String productos = "";
    double total = 0;

    public AdaptadorCarroCompras(Context context, List<Products> carroCompra, TextView tvTotal) {
        this.context = context;
        this.carroCompra = carroCompra;
        this.tvTotal = tvTotal;

        for(int i = 0; i < carroCompra.size(); i++) {
            total = total + Double.parseDouble(""+carroCompra.get(i).getValor());
        }
        tvTotal.setText(""+total);
    }

    public AdaptadorCarroCompras(Context context, List<Products> carroCompra, EditText viewValue, EditText viewProducts, EditText viewProvider) {
        this.context = context;
        this.carroCompra = carroCompra;
        this.viewValue = viewValue;
        this.viewProducts = viewProducts;
        this.viewProvider = viewProvider;

        String proveedor = "";
        for(int i = 0; i < carroCompra.size(); i++) {
            total = total + Double.parseDouble(""+carroCompra.get(i).getValor());
            productos = productos + " - " + carroCompra.get(i).getNombre();
            proveedor = carroCompra.get(i).getExpedicion();
        }
        viewProvider.setText("" + proveedor);
        viewValue.setText("$ " + total);
        viewProducts.setText(productos + " -");
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_carro_compras, null, false);
        return new AdaptadorCarroCompras.ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder productosViewHolder, final int i) {
        productosViewHolder.tvNomProducto.setText(carroCompra.get(i).getNombre());
        productosViewHolder.tvDescripcion.setText(carroCompra.get(i).getDescripcion());
        productosViewHolder.tvPrecio.setText(""+carroCompra.get(i).getValor());

    }

    @Override
    public int getItemCount() {
        return carroCompra.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomProducto, tvDescripcion, tvPrecio;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNomProducto = itemView.findViewById(R.id.tvNomProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}
