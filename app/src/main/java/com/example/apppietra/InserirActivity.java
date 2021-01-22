package com.example.apppietra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InserirActivity extends AppCompatActivity {
    private EditText nome;
    private EditText quantidade;
    private ProdutoDataAccessObject dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        getSupportActionBar().hide();

        nome = findViewById(R.id.editTextNome);
        quantidade = findViewById(R.id.editTextQuantidade);
        dao = new ProdutoDataAccessObject(this);
    }

    public void salvar(View view) {
        String nomeProduto = nome.getText().toString();
        Produto produto = new Produto();
        produto.setNome(nomeProduto);
        produto.setQuantidade(Integer.parseInt(quantidade.getText().toString()));
        dao.inserir(produto);
        Toast.makeText(this, "Produto inserido: " + nomeProduto,
                Toast.LENGTH_SHORT).show();
        finish();
    }
}