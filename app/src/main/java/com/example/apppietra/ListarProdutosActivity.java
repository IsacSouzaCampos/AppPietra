package com.example.apppietra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListarProdutosActivity extends AppCompatActivity {
    private ListView listViewProdutos;
    private ProdutoDataAccessObject dao;
    private List<Produto> produtos;
    private List<Produto> produtosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);

        listViewProdutos = findViewById(R.id.listViewProdutos);
        dao = new ProdutoDataAccessObject(this);
        produtos = dao.obterProdutos();
        produtosFiltrados.addAll(produtos);
        ArrayAdapter<Produto> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, produtosFiltrados);
        listViewProdutos.setAdapter(adaptador);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public void cadastrar(MenuItem item) {
        Intent it = new Intent(this, InserirActivity.class);
        startActivity(it);
    }

    @Override
    public void onResume() {
        super.onResume();
        produtos = dao.obterProdutos();
        produtosFiltrados.clear();
        produtosFiltrados.addAll(produtos);
        listViewProdutos.invalidateViews();
    }
}