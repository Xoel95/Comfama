package com.example.comfama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class Activity2 extends AppCompatActivity {

    private Intent intent;
    private TextView grabar;
    private Button btn_buscar;
    private EditText et_escribir;
    private ImageButton ibtn_hablar;
    private LinearLayout layout_principal_options, layout_secundario_options;
//    private ImageView action_settings;
//    private AnimatedVectorDrawable options_animation;
//    private AnimatedVectorDrawableCompat options_animation_compat;

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.sin_text) + "\">" + getString(R.string.app_name) + "</font>"));

        declararVariables();
        cargarPreferencias();

//        action_settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.initial_page) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_settings) {
            intent = new Intent(this, Options.class);
//            options_animation_compat = AnimatedVectorDrawableCompat.create(this,R.drawable.settings_animated);
//            assert options_animation_compat != null;
//            options_animation_compat.start();
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.exit_app) {
            intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECOGNIZE_SPEECH_ACTIVITY) {
            if (resultCode == RESULT_OK && null != data) {
                ArrayList<String> speech = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String strSpeech2Text = speech.get(0);
                grabar.setText(strSpeech2Text);
                intent = new Intent(this, Activity3.class);
                if (strSpeech2Text.equalsIgnoreCase(getResources().getString(R.string.productos_basicos))) {
                    String alcampo = getResources().getString(R.string.lidl);
                    intent.putExtra("url", alcampo);
                    startActivity(intent);
                } else if (strSpeech2Text.equalsIgnoreCase(getResources().getString(R.string.cosas_varias))) {
                    String amazon = getResources().getString(R.string.amazon);
                    intent.putExtra("url", amazon);
                    startActivity(intent);
                } else if (strSpeech2Text.equalsIgnoreCase(getResources().getString(R.string.muebles))) {
                    String ikea = getResources().getString(R.string.ikea);
                    intent.putExtra("url", ikea);
                    startActivity(intent);
                } else if (strSpeech2Text.equalsIgnoreCase(getResources().getString(R.string.parafarmacia))) {
                    String drfarma = getResources().getString(R.string.drfarma);
                    intent.putExtra("url", drfarma);
                    startActivity(intent);
                } else if (strSpeech2Text.equalsIgnoreCase(getResources().getString(R.string.herramientas))) {
                    String leroyMerlin = getResources().getString(R.string.leroy_merlin);
                    intent.putExtra("url", leroyMerlin);
                    startActivity(intent);
                }
            }
        }
    }
    public void onClickImgBtnHablar(View v) {
        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        // Configura el Lenguaje dependiendo del idioma del sistema
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, getResources().getString(R.string.idioma));
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.no_recon_excep),
                    Toast.LENGTH_SHORT).show();
        }
    }

//    public void onClickImgBtnOptions(View view) {
//        intent = new Intent(this, Options.class);
//        startActivity(intent);
//    }

    public void onClickBtnPrueba(View view) {
        String prueba = et_escribir.getText().toString();
        intent = new Intent(this, Activity3.class);
        if (prueba.equalsIgnoreCase(getResources().getString(R.string.productos_basicos))) {
            String alcampo = getResources().getString(R.string.lidl);
            intent.putExtra("url", alcampo);
            startActivity(intent);
        } else if (prueba.equalsIgnoreCase(getResources().getString(R.string.cosas_varias))) {
            String amazon = getResources().getString(R.string.amazon);
            intent.putExtra("url", amazon);
            startActivity(intent);
        } else if (prueba.equalsIgnoreCase(getResources().getString(R.string.muebles))) {
            String ikea = getResources().getString(R.string.ikea);
            intent.putExtra("url", ikea);
            startActivity(intent);
        } else if (prueba.equalsIgnoreCase(getResources().getString(R.string.parafarmacia))) {
            String drfarma = getResources().getString(R.string.drfarma);
            intent.putExtra("url", drfarma);
            startActivity(intent);
        } else if (prueba.equalsIgnoreCase(getResources().getString(R.string.herramientas))) {
            String leroyMerlin = getResources().getString(R.string.leroy_merlin);
            intent.putExtra("url", leroyMerlin);
            startActivity(intent);
        }

    }

    private void cargarPreferencias() {
        SharedPreferences preferenciasEscribir = getSharedPreferences("escribir", Context.MODE_PRIVATE);

        boolean escribir = preferenciasEscribir.getBoolean("escribir", false);

        if (escribir) {
            et_escribir.setVisibility(View.VISIBLE);
            btn_buscar.setVisibility(View.VISIBLE);
            ibtn_hablar.setVisibility(View.GONE);
        } else {
            et_escribir.setVisibility(View.GONE);
            btn_buscar.setVisibility(View.GONE);
            ibtn_hablar.setVisibility(View.VISIBLE);
        }

        SharedPreferences preferenciasDaltonismo = getSharedPreferences("daltonismo", Context.MODE_PRIVATE);

        String daltonismo = preferenciasDaltonismo.getString("daltonismo", "sin");

        if (daltonismo.equals("sin")) {
            layout_principal_options.setBackgroundColor(getResources().getColor(R.color.sin_bg));
            layout_secundario_options.setBackgroundColor(getResources().getColor(R.color.sin_medio));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.sin_medio)));
        } else if (daltonismo.equals("rojo")) {
            layout_principal_options.setBackgroundColor(getResources().getColor(R.color.rojo_bg));
            layout_secundario_options.setBackgroundColor(getResources().getColor(R.color.rojo_medio));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.rojo_medio)));
        } else if (daltonismo.equals("verde")) {
            layout_principal_options.setBackgroundColor(getResources().getColor(R.color.verde_bg));
            layout_secundario_options.setBackgroundColor(getResources().getColor(R.color.verde_medio));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.verde_medio)));
        } else if (daltonismo.equals("azul")) {
            layout_principal_options.setBackgroundColor(getResources().getColor(R.color.azul_bg));
            layout_secundario_options.setBackgroundColor(getResources().getColor(R.color.azul_medio));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.azul_medio)));
        } else if (daltonismo.equals("byn")) {
            layout_principal_options.setBackgroundColor(getResources().getColor(R.color.byn_bg));
            layout_secundario_options.setBackgroundColor(getResources().getColor(R.color.byn_medio));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.byn_medio)));
        }
    }

    public void declararVariables() {
        grabar = findViewById(R.id.txtGrabarVoz);
        btn_buscar = findViewById(R.id.btn_buscar);
        et_escribir = findViewById(R.id.et_escribir);
        ibtn_hablar = findViewById(R.id.ibtn_hablar);
        layout_principal_options = findViewById(R.id.layout_principal_options);
        layout_secundario_options = findViewById(R.id.layout_secundario_options);
//        options_animation = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.settings_animated);
    }
}