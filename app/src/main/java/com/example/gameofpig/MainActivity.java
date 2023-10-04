package com.example.gameofpig;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final int puntuacionGana = 100;
    final int numeroDadoFallo = 1;
    final int numeroDadoInicial = 1;
    final int puntuacionInicial = 0;
    final int turnoJugadorInicial = 1;

    int puntuacionJugador1, puntuacionJugador2, umbralJugador1, umbralJugador2, numeroDado, turnoJugador, baseScrollX;

    Button buttonJugar, buttonPasar, buttonReinicia;

    TextView TextViewPuntuacionJugador1, TextViewPuntuacionJugador2, TextViewUmbral1, TextViewUmbral2, TextViewJugador1, TextViewJugador2, TextViewJugadorGanador;
    ConstraintLayout ConstraintLayoutJugador1, ConstraintLayoutJugador2, ConstraintLayoutGanador;
    ImageView ImageViewDado;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        puntuacionJugador1 = puntuacionInicial;
        umbralJugador1 = puntuacionInicial;
        puntuacionJugador2 = puntuacionInicial;
        umbralJugador2 = puntuacionInicial;

        numeroDado = numeroDadoInicial;
        turnoJugador = turnoJugadorInicial;

        buttonJugar = (Button)findViewById(R.id.buttonJugar);
        buttonPasar = (Button)findViewById(R.id.buttonPasar);
        buttonReinicia = (Button)findViewById(R.id.buttonReinicia);

        ConstraintLayoutJugador1 = findViewById(R.id.ConstraintLayoutJugador1);
        ConstraintLayoutJugador2 = findViewById(R.id.ConstraintLayoutJugador2);
        ConstraintLayoutGanador = findViewById(R.id.ConstraintLayoutGanador);

        TextViewJugador1 = findViewById(R.id.textViewJugador1);
        TextViewJugador2 = findViewById(R.id.textViewJugador2);
        TextViewPuntuacionJugador1 = findViewById(R.id.textViewJugador1Puntos);
        TextViewPuntuacionJugador2 = findViewById(R.id.textViewJugador2Puntos);
        TextViewUmbral1 = findViewById(R.id.textViewJugador1Umbral);
        TextViewUmbral2 = findViewById(R.id.textViewJugador2Umbral);
        TextViewJugadorGanador = findViewById(R.id.textViewJugadorGanador);

        TextViewPuntuacionJugador1.setText(Integer.toString(puntuacionJugador1));
        TextViewPuntuacionJugador2.setText(Integer.toString(puntuacionJugador2));

        ImageViewDado = findViewById(R.id.imageViewDado);
        baseScrollX = ImageViewDado.getScrollX();

        random = new Random();

        buttonJugar.setOnClickListener(jugarListener);
        buttonPasar.setOnClickListener(pasarListener);
        buttonReinicia.setOnClickListener(reiniciarListener);

    }

    private View.OnClickListener jugarListener = new View.OnClickListener() {

        public void onClick(View view) {
            if (turnoJugador == 1) {
                numeroDado = tiraDado();
                if(numeroDado == numeroDadoFallo) {
                    umbralJugador1 = 0;
                    TextViewUmbral1.setText(Integer.toString(umbralJugador1));
                } else {
                    umbralJugador1 = numeroDado + Integer.parseInt(TextViewUmbral1.getText().toString());
                    TextViewUmbral1.setText(Integer.toString(umbralJugador1));
                }
            }
            if (turnoJugador == 2) {
                numeroDado = tiraDado();
                if(numeroDado == numeroDadoFallo) {
                    umbralJugador2 = 0;
                    TextViewUmbral2.setText(Integer.toString(umbralJugador2));
                } else {
                    umbralJugador2 = numeroDado + Integer.parseInt(TextViewUmbral2.getText().toString());
                    TextViewUmbral2.setText(Integer.toString(umbralJugador2));
                }
            }
            if (numeroDado == numeroDadoFallo){
                cambiaJugador();
            }
        }
    };

    private View.OnClickListener pasarListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (turnoJugador == 1){
                puntuacionJugador1 += Integer.parseInt(TextViewUmbral1.getText().toString());
                umbralJugador1 = 0;
                if (puntuacionJugador1 >= puntuacionGana){
                    reiniciaPuntuaciones();
                    TextViewJugadorGanador.setText("JUGADOR 1");
                    ConstraintLayoutGanador.setVisibility(View.VISIBLE);
                    buttonJugar.setClickable(false);
                    buttonPasar.setClickable(false);

                } else {
                    TextViewUmbral1.setText(Integer.toString(umbralJugador1));
                    TextViewPuntuacionJugador1.setText(Integer.toString(puntuacionJugador1));
                }
            } else {
                puntuacionJugador2 += Integer.parseInt(TextViewUmbral2.getText().toString());
                umbralJugador2 = 0;
                if (puntuacionJugador2 >= puntuacionGana){
                    reiniciaPuntuaciones();
                    TextViewJugadorGanador.setText("JUGADOR 2");
                    ConstraintLayoutGanador.setVisibility(View.VISIBLE);
                    buttonJugar.setClickable(false);
                    buttonPasar.setClickable(false);
                } else {
                    TextViewUmbral2.setText(Integer.toString(umbralJugador2));
                    TextViewPuntuacionJugador2.setText(Integer.toString(puntuacionJugador2));
                }
            }
            cambiaJugador();
        }
    };

    private View.OnClickListener reiniciarListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reiniciaPuntuaciones();
        }
    };

    private void cambiaJugador(){
        if (turnoJugador == 1) {
            ConstraintLayoutJugador2.setBackgroundColor(Color.parseColor("#9575CD"));
            ConstraintLayoutJugador1.setBackgroundColor(Color.parseColor("#D0C3E8"));

            TextViewJugador1.setTextColor(Color.parseColor("#673AB7"));
            TextViewPuntuacionJugador1.setTextColor(Color.parseColor("#673AB7"));
            TextViewUmbral1.setTextColor(Color.parseColor("#673AB7"));

            TextViewJugador2.setTextColor(Color.WHITE);
            TextViewPuntuacionJugador2.setTextColor(Color.WHITE);
            TextViewUmbral2.setTextColor(Color.WHITE);

            turnoJugador = 2;
        } else {
            ConstraintLayoutJugador1.setBackgroundColor(Color.parseColor("#9575CD"));
            ConstraintLayoutJugador2.setBackgroundColor(Color.parseColor("#D0C3E8"));

            TextViewJugador2.setTextColor(Color.parseColor("#673AB7"));
            TextViewPuntuacionJugador2.setTextColor(Color.parseColor("#673AB7"));
            TextViewUmbral2.setTextColor(Color.parseColor("#673AB7"));

            TextViewJugador1.setTextColor(Color.WHITE);
            TextViewPuntuacionJugador1.setTextColor(Color.WHITE);
            TextViewUmbral1.setTextColor(Color.WHITE);

            turnoJugador = 1;
        }
    }

    private int tiraDado(){
        int num = random.nextInt(6)+1;
        ImageViewDado.setScrollX(baseScrollX + ImageViewDado.getLayoutParams().height * num);
        return num;
    }

    private void reiniciaPuntuaciones() {
        puntuacionJugador1 = 0;
        umbralJugador1 = 0;
        puntuacionJugador2 = 0;
        umbralJugador2 = 0;
        TextViewPuntuacionJugador1.setText(Integer.toString(puntuacionJugador1));
        TextViewUmbral1.setText(Integer.toString(umbralJugador1));
        TextViewPuntuacionJugador2.setText(Integer.toString(puntuacionJugador2));
        TextViewUmbral2.setText(Integer.toString(umbralJugador2));
        ConstraintLayoutGanador.setVisibility(View.GONE);
        buttonJugar.setClickable(true);
        buttonPasar.setClickable(true);
    }

}