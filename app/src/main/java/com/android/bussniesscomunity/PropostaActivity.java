package com.android.bussniesscomunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.bussniesscomunity.Adapter.PropostaAdapter;
import com.android.bussniesscomunity.Modelo.Propostas;

import org.w3c.dom.Text;

import java.util.List;

public class PropostaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView titulo, telefone, descricao;
    public List<Propostas> listaProposta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposta);
        titulo = findViewById(R.id.tituloProposta);
        descricao = findViewById(R.id.descricaoProposta);
        telefone = findViewById(R.id.telefoneProposta);

        Intent i = getIntent();

        titulo.setText(i.getStringExtra("titulo"));
        descricao.setText(i.getStringExtra("descricao"));
        telefone.setText(i.getStringExtra("telefone"));

    }
}
