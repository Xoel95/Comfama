package com.example.comfama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class Inicio extends AppCompatActivity {

    private Intent intent;
    private LinearLayout layout_principal_options, layout_secundario_options;
    private TextView tv_titulo, tv_intro;
    private ImageView iv_logo;
    private Menu options_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.sin_text) + "\">" + getString(R.string.app_name) + "</font>"));

        declararVariables();
        cargarPreferencias();

        final ZoomLinearLayout zoomLinearLayout = findViewById(R.id.zoom_linear_layout);
        zoomLinearLayout.setOnTouchListener((v, event) -> {
            zoomLinearLayout.init(Inicio.this);
            return false;
        });
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
            finish();
            startActivity(getIntent());
            return true;
        } else if (item.getItemId() == R.id.action_settings) {
            intent = new Intent(this, Options.class);
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

    public void onClickImgView(View view) {
        intent = new Intent(this, ReconVoz.class);
        startActivity(intent);
    }

    private void cargarPreferencias() {

        SharedPreferences preferenciasNoche = getSharedPreferences("noche", Context.MODE_PRIVATE);

        boolean noche = preferenciasNoche.getBoolean("noche", false);

        if (noche) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        SharedPreferences preferencias = getSharedPreferences("daltonismo", Context.MODE_PRIVATE);
        String daltonismo = preferencias.getString("daltonismo", "sin");

        switch (daltonismo) {

            case "sin":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sin_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sin_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.sin_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.sin_medio, null)));
                iv_logo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.logo_app_sin, null));
                tv_titulo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_intro.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                break;

            case "rojo":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.rojo_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.rojo_medio, null)));
                iv_logo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.logo_app_rojo, null));
                tv_titulo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_intro.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                break;

            case "verde":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.verde_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.verde_medio, null)));
                iv_logo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.logo_app_verde, null));
                tv_titulo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_intro.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                break;

            case "azul":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.azul_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.azul_medio, null)));
                iv_logo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.logo_app_azul, null));
                tv_titulo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_intro.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                break;

            case "byn":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.byn_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.byn_medio, null)));
                iv_logo.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.logo_app_byn, null));
                tv_titulo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_intro.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
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
        layout_principal_options = findViewById(R.id.layout_principal_options);
        layout_secundario_options = findViewById(R.id.layout_secundario_options);
        tv_titulo = findViewById(R.id.tv_titulo);
        tv_intro = findViewById(R.id.tv_intro);
        iv_logo = findViewById(R.id.iv_logo);
    }
}