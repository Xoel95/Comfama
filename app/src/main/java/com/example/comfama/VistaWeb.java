package com.example.comfama;

import androidx.appcompat.app.AppCompatActivity;
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
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class VistaWeb extends AppCompatActivity {

    private Intent intent;
    private WebView webView;
    private TextView tv_lidl, tv_amazon, tv_ikea, tv_drfarma, tv_leroymerlin;
    private LinearLayout layout_principal_options, layout_secundario_options;
    private Menu options_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_web);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.sin_text) + "\">" + getString(R.string.app_name) + "</font>"));

        declararVariables();
        cargarPreferencias();

        final ZoomLinearLayout zoomLinearLayout = findViewById(R.id.zoom_linear_layout);
        zoomLinearLayout.setOnTouchListener((v, event) -> {
            zoomLinearLayout.init(VistaWeb.this);
            return false;
        });

        intent = getIntent();
        String datoRecibido = intent.getStringExtra("url");
        visibilidadWebView(datoRecibido);
        webView.loadUrl(datoRecibido);
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

    public void visibilidadWebView(String datoRecibido) {
        if (datoRecibido.equalsIgnoreCase(getResources().getString(R.string.lidl))) {
            tv_lidl.setVisibility(View.VISIBLE);
            tv_amazon.setVisibility(View.GONE);
            tv_ikea.setVisibility(View.GONE);
            tv_drfarma.setVisibility(View.GONE);
            tv_leroymerlin.setVisibility(View.GONE);
        } else if (datoRecibido.equalsIgnoreCase(getResources().getString(R.string.amazon))) {
            tv_lidl.setVisibility(View.GONE);
            tv_amazon.setVisibility(View.VISIBLE);
            tv_ikea.setVisibility(View.GONE);
            tv_drfarma.setVisibility(View.GONE);
            tv_leroymerlin.setVisibility(View.GONE);
        } else if (datoRecibido.equalsIgnoreCase(getResources().getString(R.string.ikea))) {
            tv_lidl.setVisibility(View.GONE);
            tv_amazon.setVisibility(View.GONE);
            tv_ikea.setVisibility(View.VISIBLE);
            tv_drfarma.setVisibility(View.GONE);
            tv_leroymerlin.setVisibility(View.GONE);
        } else if (datoRecibido.equalsIgnoreCase(getResources().getString(R.string.drfarma))) {
            tv_lidl.setVisibility(View.GONE);
            tv_amazon.setVisibility(View.GONE);
            tv_ikea.setVisibility(View.GONE);
            tv_drfarma.setVisibility(View.VISIBLE);
            tv_leroymerlin.setVisibility(View.GONE);
        } else {
            tv_lidl.setVisibility(View.GONE);
            tv_amazon.setVisibility(View.GONE);
            tv_ikea.setVisibility(View.GONE);
            tv_drfarma.setVisibility(View.GONE);
            tv_leroymerlin.setVisibility(View.VISIBLE);
        }
    }

    private void cargarPreferencias() {

        SharedPreferences preferencias = getSharedPreferences("daltonismo", Context.MODE_PRIVATE);

        String daltonismo = preferencias.getString("daltonismo", "sin");

        switch (daltonismo) {

            case "sin":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sin_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sin_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.sin_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.sin_medio, null)));
                tv_lidl.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_amazon.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_ikea.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_drfarma.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_leroymerlin.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                break;

            case "rojo":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.rojo_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.rojo_medio, null)));
                tv_lidl.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_amazon.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_ikea.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_drfarma.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_leroymerlin.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                break;

            case "verde":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.verde_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.verde_medio, null)));
                tv_lidl.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_amazon.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_ikea.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_drfarma.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_leroymerlin.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                break;

            case "azul":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.azul_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.azul_medio, null)));
                tv_lidl.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_amazon.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_ikea.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_drfarma.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_leroymerlin.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                break;

            case "byn":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.byn_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.byn_medio, null)));
                tv_lidl.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_amazon.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_ikea.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_drfarma.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_leroymerlin.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
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
        webView = findViewById(R.id.webview);
        tv_lidl = findViewById(R.id.tv_lidl);
        tv_amazon = findViewById(R.id.tv_amazon);
        tv_ikea = findViewById(R.id.tv_ikea);
        tv_drfarma = findViewById(R.id.tv_drfarma);
        tv_leroymerlin = findViewById(R.id.tv_leroymerlin);
        layout_principal_options = findViewById(R.id.layout_principal_options);
        layout_secundario_options = findViewById(R.id.layout_secundario_options);
    }
}