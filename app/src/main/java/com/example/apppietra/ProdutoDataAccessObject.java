package com.example.apppietra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataAccessObject {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public ProdutoDataAccessObject(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Produto produto) {
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("quantidade", produto.getQuantidade());
        return banco.insert("produto", null, values);
    }

    public List<Produto> obterProdutos() {
        List<Produto> produtos = new ArrayList<Produto>();
        Cursor cursor = banco.query("produto", new String[]{"nome", "quantidade"},
                null, null, null, null, null);

        while(cursor.moveToNext()) {
            Produto produto = new Produto(cursor.getString(0), cursor.getInt(1));
            produtos.add(produto);
        }
        return produtos;
    }
}
