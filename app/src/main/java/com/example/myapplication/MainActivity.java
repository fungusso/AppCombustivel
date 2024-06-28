package com.example.myapplication;


import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.ekn.gruzer.gaugelibrary.Range;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContracts;


public class MainActivity extends AppCompatActivity {
    private Button btCalcular;
    private Button btlimpar;

    private Button btTiproad;
    private EditText etPeso;
    private EditText etAltura;

    private EditText etTippiso;

    private EditText etMedveic;

    private HalfGauge halfGauge;
    private TextView tvRsultadoEscrito;

    private TextView RResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btCalcular = findViewById(R.id.btCalcular);
        btlimpar = findViewById(R.id.btLimpar);
        etAltura = findViewById(R.id.etAltura);
        etPeso = findViewById(R.id.etPeso);
        btTiproad = findViewById(R.id.btTiproad);
        etTippiso =  findViewById(R.id.etTippiso);
        etMedveic =  findViewById(R.id.etMedveic);
        halfGauge = findViewById(R.id.halfGauge);
        tvRsultadoEscrito = findViewById(R.id.tvResultadoEscrito);
        RResultado = findViewById(R.id.RResultado);
// range de cores para o halfgauge

        Range range1 = new Range();
        Range range2 = new Range();
        Range range3 = new Range();
        Range range4 = new Range();
        Range range5 = new Range();
        Range range6 = new Range();
        Range range7 = new Range();
        Range range8 = new Range();
        Range range9 = new Range();
        Range range10 = new Range();
// escala de percentual para a paleta de cores

        range1.setColor(Color.parseColor( "#006400"));
        range1.setFrom(1.0);
        range1.setTo(10.0);

        range2.setColor(Color.parseColor( "#2E8B57"));
        range2.setFrom(10);
        range2.setTo(20);

        range3.setColor(Color.parseColor( "#008000"));
        range3.setFrom(20);
        range3.setTo(30);

        range4.setColor(Color.parseColor( "#32CD32"));
        range4.setFrom(30);
        range4.setTo(40);

        range5.setColor(Color.parseColor( "#3CB371"));
        range5.setFrom(40);
        range5.setTo(50);

        range6.setColor(Color.parseColor( "#90EE90"));
        range6.setFrom(50);
        range6.setTo(60);

        range7.setColor(Color.parseColor( "#98FB98"));
        range7.setFrom(60);
        range7.setTo(69);

        range8.setColor(Color.parseColor( "#FFA07A"));
        range8.setFrom(70);
        range8.setTo(80);

        range9.setColor(Color.parseColor( "#FF6347"));
        range9.setFrom(80);
        range9.setTo(90);

        range10.setColor(Color.parseColor( "#FF0000"));
        range10.setFrom(90);
        range10.setTo(100);
// atribuir escala

        halfGauge.addRange(range1);
        halfGauge.addRange(range2);
        halfGauge.addRange(range3);
        halfGauge.addRange(range4);
        halfGauge.addRange(range5);
        halfGauge.addRange(range6);
        halfGauge.addRange(range7);
        halfGauge.addRange(range8);
        halfGauge.addRange(range9);
        halfGauge.addRange(range10);
// parametros do half
        halfGauge.setValueColor(Color.BLACK);
        halfGauge.setMaxValueTextColor(Color.RED);
        halfGauge.setMinValueTextColor(Color.BLUE);
        halfGauge.setMaxValue(100);
        halfGauge.setMinValue(1);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btCalcularOnClick();
                }
            });
        btlimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                halfGauge.setValue(0.00);
                 etAltura.setText("");
                 etPeso.setText("");
                 RResultado.setText("");
                 tvRsultadoEscrito.setText("");
                 etTippiso.setText("");
                 etPeso.requestFocus();
                 etMedveic.setText("");
            }
        });
        btTiproad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSomeActivityForResult();
            }
        });
    }
    public void openSomeActivityForResult() {
        Intent intent = new Intent(MainActivity.this, ListarActivity.class);
        someActivityResultLauncher.launch(intent);
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                    public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent extras = getIntent();
                        String codigo = extras.getDataString();
                        if (codigo != null) {
                            etTippiso.setText(codigo);

                        }else {
                              etTippiso.setText("5");
                          }
     //
                    }
                }
            });

    @SuppressLint("DefaultLocale")
    // consiste campos de entrada
    private void btCalcularOnClick() {
        if (etPeso.getText().toString().isEmpty()){
            etPeso.setError("Peso deve ser preenchido");
            etPeso.requestFocus();
            return;
        }
        if (etAltura.getText().toString().isEmpty()){
            etAltura.setError("Altura deve ser preenchido");
            etAltura.requestFocus();
            return;
        }
        if (etTippiso.getText().toString().isEmpty()){
            etTippiso.setError("Tipo de Piso deve ser preenchido");
            etTippiso.requestFocus();
            return;
        }
        if (etMedveic.getText().toString().isEmpty()){
            etMedveic.setError("Média em Km do Veículo deve ser preenchido");
            etMedveic.requestFocus();
            return;
        }
// formatação de casas decimais
        NumberFormat  f = NumberFormat.getCurrencyInstance();
        DecimalFormat ff = new DecimalFormat("0.00");
        DecimalFormat fofa = new DecimalFormat( "0,00");

        System.out.println("peso " + etPeso);
        System.out.println("altura " + etAltura);
// atribuição de campos da tela
        double peso = Double.parseDouble(etPeso.getText().toString());
        double altura = Double.parseDouble(etAltura.getText().toString());
        double media  = Double.parseDouble(etMedveic.getText().toString());
        double piso = Double.parseDouble(etTippiso.getText().toString());
// calculo para ver relação do combustível menor pelo maior
        double resultado = (peso / altura) * 100;
// calculo que desconta a média de rendimento do carro menos percentual conforme o piso
        if (piso == 1){
            media = media - (media * 0.1);
        }
        else if (piso == 3) {
            media = media - (media * 0.2);
        }
        else if (piso == 4) {
            media = media - (media * 0.25);
        }else if (piso == 5) {
            media = media - (media * 0.3);
        }else if (piso == 6) {
            media = media - (media * 0.15);
        }
        System.out.println("resul media ======> " + media);
        System.out.println("resultado ======> " + resultado);
       //  System.out.println(str);
          //halfGauge.setValue(resultado);
          int inteiro = (int) Math.round(resultado);
          halfGauge.setValue(inteiro);

// percentual abaixo de 70% viabiliza o álcool senão gasolina
        if (resultado < 70){
            double mediareal = peso / media;
            // double saida = (Double.parseDouble(ff.format(mediareal)));
            RResultado.setText(String.format("%.2f",mediareal));

        }else {
            double mediareal = altura / media;
            //double saida = (Double.parseDouble(ff.format(mediareal)));
            RResultado.setText(String.format("%.2f",mediareal));
        }

        EditText etTippiso1 = etTippiso;
// cor da indicação do combustível acompanha a paletta de cores
        if (resultado < 10){
            tvRsultadoEscrito.setTextColor(Color.parseColor( "#006400"));
            tvRsultadoEscrito.setText("Álcool");

        } else if (resultado >= 10 && resultado < 20) {
            tvRsultadoEscrito.setTextColor(Color.parseColor( "#2E8B57"));
            tvRsultadoEscrito.setText("Álcool");

        } else if (resultado >= 20 && resultado < 30 ) {
            tvRsultadoEscrito.setTextColor(Color.parseColor( "#008000"));
            tvRsultadoEscrito.setText("Álcool");

        } else if (resultado >= 30 && resultado < 40) {
            tvRsultadoEscrito.setTextColor(Color.parseColor( "#32CD32"));
            tvRsultadoEscrito.setText("Álcool");

        } else if (resultado >= 40 && resultado < 50) {
            tvRsultadoEscrito.setTextColor(Color.parseColor( "#90EE90"));
            tvRsultadoEscrito.setText("Álcoll");

        } else if (resultado >= 50 && resultado < 60) {
            tvRsultadoEscrito.setTextColor(Color.parseColor( "#FA8072"));
            tvRsultadoEscrito.setText("Álcool");

        } else if (resultado >= 60 && resultado < 70) {
            tvRsultadoEscrito.setTextColor(Color.parseColor( "#E9967A"));
            tvRsultadoEscrito.setText("Álcool");
        }else if (resultado >= 70) {
            tvRsultadoEscrito.setTextColor(Color.parseColor( "#FF0000"));
            tvRsultadoEscrito.setText("Gasolina");
        }else if (resultado >= 80) {
            tvRsultadoEscrito.setTextColor(Color.parseColor( "#FF6347"));
            tvRsultadoEscrito.setText("Gasolina");
        }else if (resultado >= 90) {
            tvRsultadoEscrito.setTextColor(Color.parseColor( "#FF0000"));
            tvRsultadoEscrito.setText("Gasolina");
        }
    }

}


