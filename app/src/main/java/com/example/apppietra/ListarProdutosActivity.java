package com.example.apppietra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

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
        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "Clicou no item: " + produtos.get(position).getNome(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        dao = new ProdutoDataAccessObject(this);
        produtos = dao.obterProdutos();
        produtosFiltrados.addAll(produtos);
//        ArrayAdapter<Produto> adaptador = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, produtosFiltrados);
        ProdutoAdapter adaptador = new ProdutoAdapter(this, produtosFiltrados);
        listViewProdutos.setAdapter(adaptador);

        registerForContextMenu(listViewProdutos);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.appBarSearchConsultar).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procuraProduto(newText);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_contexto, menu);
    }

    public void procuraProduto(String nomeProduto) {
        produtosFiltrados.clear();
        for(Produto p : produtos) {
            if(p.getNome().toLowerCase().contains(nomeProduto.toLowerCase())) {
                produtosFiltrados.add(p);
            }
        }
        listViewProdutos.invalidateViews();
    }

    public void cadastrar(MenuItem item) {
        Intent it = new Intent(this, InserirActivity.class);
        startActivity(it);
    }

    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Produto produtoExcluir = produtosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Tem certeza de que deseja excluir o produto \"" + produtoExcluir.getNome() + "\"?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        produtosFiltrados.remove(produtoExcluir);
                        produtos.remove(produtoExcluir);
                        dao.excluir(produtoExcluir);
                        listViewProdutos.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Produto produtoAtualizar = produtosFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, AtualizarActivity.class);
        it.putExtra("produto", produtoAtualizar);
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