package com.example.apppietra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListarProdutosActivity extends AppCompatActivity {
    private ListView listViewProdutos;
    private ProdutoDataAccessObject dao;
    private List<Produto> produtos;
    private List<Produto> produtosFiltrados = new ArrayList<Produto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);

        listViewProdutos = findViewById(R.id.listViewProdutos);
        dao = new ProdutoDataAccessObject(this);
        produtos = dao.obterProdutos();
        produtosFiltrados.addAll(produtos);
        ArrayAdapter<Produto> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, produtos);
        listViewProdutos.setAdapter(adaptador);
    }
}