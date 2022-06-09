package com.example.comfama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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

public class ReconVoz extends AppCompatActivity {

    private Intent intent;
    private TextView tv_explicacion, tv_grabar;
    private Button btn_buscar;
    private EditText et_escribir;
    private ImageButton ibtn_hablar;
    private LinearLayout layout_principal_options, layout_secundario_options;
    private Menu options_menu;
//    private ImageView action_settings;
//    private AnimatedVectorDrawable options_animation;
//    private AnimatedVectorDrawableCompat options_animation_compat;

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recon_voz);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.sin_text) + "\">" + getString(R.string.app_name) + "</font>"));

        declararVariables();
        cargarPreferencias();

        final ZoomLinearLayout zoomLinearLayout = findViewById(R.id.zoom_linear_layout);
        zoomLinearLayout.setOnTouchListener((v, event) -> {
            zoomLinearLayout.init(ReconVoz.this);
            return false;
        });

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
        options_menu = menu;
        cargarMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.initial_page) {
            intent = new Intent(this, Inicio.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_settings) {
            intent = new Intent(this, Options.class);
//            options_animation_compat = AnimatedVectorDrawableCompat.create(this,R.drawable.settings_animated);
//            assert options_animation_compat != null;
//            options_animation_compat.start();
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.info_app) {
            intent = new Intent(this, Info.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.exit_app) {
            finishAffinity();
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
                tv_grabar.setText(strSpeech2Text);
                intent = new Intent(this, VistaWeb.class);
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

    public void onClickBtnEscribir(View view) {
        String prueba = et_escribir.getText().toString();
        intent = new Intent(this, VistaWeb.class);
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

        switch (daltonismo) {

            case "sin":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sin_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sin_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.sin_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.sin_medio, null)));
                ibtn_hablar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sin_medio, null));
                btn_buscar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sin_bg, null));
                tv_explicacion.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_grabar.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                btn_buscar.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                et_escribir.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                ibtn_hablar.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.mic_sin, null));
                break;

            case "rojo":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.rojo_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.rojo_medio, null)));
                ibtn_hablar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_medio, null));
                btn_buscar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_bg, null));
                tv_explicacion.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_grabar.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                btn_buscar.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                et_escribir.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                ibtn_hablar.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.mic_rojo, null));
                break;

            case "verde":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.verde_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.verde_medio, null)));
                ibtn_hablar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_medio, null));
                btn_buscar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_bg, null));
                tv_explicacion.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_grabar.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                btn_buscar.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                et_escribir.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                ibtn_hablar.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.mic_verde, null));
                break;

            case "azul":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.azul_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.azul_medio, null)));
                ibtn_hablar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_medio, null));
                btn_buscar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_bg, null));
                tv_explicacion.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_grabar.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                btn_buscar.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                et_escribir.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                ibtn_hablar.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.mic_azul, null));
                break;

            case "byn":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.byn_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.byn_medio, null)));
                ibtn_hablar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_medio, null));
                btn_buscar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_bg, null));
                tv_explicacion.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_grabar.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                btn_buscar.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                et_escribir.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                ibtn_hablar.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.mic_byn, null));
                break;

        }
    }

    private void cargarMenu() {

        SharedPreferences preferencias = getSharedPreferences("daltonismo", Context.MODE_PRIVATE);
        String daltonismo = preferencias.getString("daltonismo", "sin");

        switch (daltonismo) {

            case "sin":
                options_menu.findItem(R.id.initial_page).setIcon(R.drawable.logo_app_sin);
                options_menu.findItem(R.id.action_settings).setIcon(R.drawable.settings_sin);
                options_menu.findItem(R.id.info_app).setIcon(R.drawable.info_sin);
                options_menu.findItem(R.id.exit_app).setIcon(R.drawable.exit_sin);
                break;

            case "rojo":
                options_menu.findItem(R.id.initial_page).setIcon(R.drawable.logo_app_rojo);
                options_menu.findItem(R.id.action_settings).setIcon(R.drawable.settings_rojo);
                options_menu.findItem(R.id.info_app).setIcon(R.drawable.info_rojo);
                options_menu.findItem(R.id.exit_app).setIcon(R.drawable.exit_rojo);
                break;

            case "verde":
                options_menu.findItem(R.id.initial_page).setIcon(R.drawable.logo_app_verde);
                options_menu.findItem(R.id.action_settings).setIcon(R.drawable.settings_verde);
                options_menu.findItem(R.id.info_app).setIcon(R.drawable.info_verde);
                options_menu.findItem(R.id.exit_app).setIcon(R.drawable.exit_verde);
                break;

            case "azul":
                options_menu.findItem(R.id.initial_page).setIcon(R.drawable.logo_app_azul);
                options_menu.findItem(R.id.action_settings).setIcon(R.drawable.settings_azul);
                options_menu.findItem(R.id.info_app).setIcon(R.drawable.info_azul);
                options_menu.findItem(R.id.exit_app).setIcon(R.drawable.exit_azul);
                break;

            case "byn":
                options_menu.findItem(R.id.initial_page).setIcon(R.drawable.logo_app_byn);
                options_menu.findItem(R.id.action_settings).setIcon(R.drawable.settings_byn);
                options_menu.findItem(R.id.info_app).setIcon(R.drawable.info_byn);
                options_menu.findItem(R.id.exit_app).setIcon(R.drawable.exit_byn);
                break;

        }
    }

    public void declararVariables() {
        tv_explicacion = findViewById(R.id.tv_explicacion);
        tv_grabar = findViewById(R.id.tv_grabar);
        btn_buscar = findViewById(R.id.btn_buscar);
        et_escribir = findViewById(R.id.et_escribir);
        ibtn_hablar = findViewById(R.id.ibtn_hablar);
        layout_principal_options = findViewById(R.id.layout_principal_options);
        layout_secundario_options = findViewById(R.id.layout_secundario_options);
//        options_animation = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.settings_animated);
    }
}