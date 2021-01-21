package com.example.apppietra;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
}
