package com.example.apppietra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AtualizarActivity extends AppCompatActivity {
    private TextView textView3;
    private TextView textView5;
    private Button button;
    private Button button2;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        getSupportActionBar().hide();

        textView3 = findViewById(R.id.textView3);
        textView5 = findViewById(R.id.textView5);

        Intent it = getIntent();
        System.out.println("entrou no onCreate do Atualizar Activity");
        if(it.hasExtra("produto")) {
            System.out.println("entrou no if");
            produto = (Produto) it.getSerializableExtra("produto");
            System.out.println("passou o getSerializableExtra");
            textView3.setText(produto.getNome());
            System.out.println("textView3.setText");
            textView5.setText(new Integer(produto.getQuantidade()).toString());
            System.out.println("textView5.setText");
        }
    }
}