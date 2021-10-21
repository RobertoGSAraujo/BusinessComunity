package com.android.bussniesscomunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.bussniesscomunity.Modelo.Propostas;
import com.android.bussniesscomunity.Modelo.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CadastroPropostaActivity extends AppCompatActivity {

    EditText tituloProposta;
    EditText descricaoProposta;
    EditText telefone;
    private Button btnCadastroPropostas;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_proposta);

        tituloProposta = findViewById(R.id.tituloProposta);
        descricaoProposta = findViewById(R.id.descricaoProposta);
        btnCadastroPropostas = findViewById(R.id.btnCadastroPropostas);
        telefone = findViewById(R.id.telefone);

        mAuth = FirebaseAuth.getInstance();


        btnCadastroPropostas.setOnClickListener(ClickbtnPropostas);

    }
    View.OnClickListener ClickbtnPropostas =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String txt_tituloproposta = tituloProposta.getText().toString();
            String txt_descricaoproposta = descricaoProposta.getText().toString();
            String txt_telefone = telefone.getText().toString();


            if (txt_tituloproposta.isEmpty() && txt_descricaoproposta.isEmpty()) {

                Toast.makeText(CadastroPropostaActivity.this, "Todos os campos são requeridos", Toast.LENGTH_SHORT).show();
            } else  {

                registrar (txt_tituloproposta, txt_descricaoproposta, txt_telefone);

            }

        }
    };

    private void registrar(final String tituloproposta, final String descricaoproposta, final String telefone ){


        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        assert firebaseUser != null;
        String propId = firebaseUser.getUid();
        Propostas chaveIdentificacao = new Propostas();
        chaveIdentificacao.setID();

        reference = FirebaseDatabase.getInstance().getReference("Propostas").child(chaveIdentificacao.getID());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("idUsuario", propId);
        hashMap.put("idProposta", chaveIdentificacao.getID());
        hashMap.put("titulo", tituloproposta);
        hashMap.put("descricao", descricaoproposta);
        hashMap.put("telefone", telefone);

        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {

                    Intent intent = new Intent(CadastroPropostaActivity.this, ListaPropostasActivity.class);
                    startActivity(intent);
                    finish();
                }

                        }
                        );

    }

}
