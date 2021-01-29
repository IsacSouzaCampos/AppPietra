package com.example.apppietra;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProdutoAdapter extends BaseAdapter {
    private Activity activity;
    private List<Produto> produtos;

    public ProdutoAdapter(Activity activity, List<Produto> produtos) {
        this.activity = activity;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item, parent, false);

        TextView nome = view.findViewById(R.id.textViewNome2);
        TextView quantidade = view.findViewById(R.id.textViewQuantidadeResult);

        Produto produto = produtos.get(position);

        nome.setText(produto.getNome());
        quantidade.setText(new Integer(produto.getQuantidade()).toString());

        return view;
    }
}
