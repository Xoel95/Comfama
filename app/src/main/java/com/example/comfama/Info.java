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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class Info extends AppCompatActivity {

    private LinearLayout layout_principal_options, layout_secundario_options;
    private TextView tv_titulo, tv_version, tv_autor, tv_ayuda, tv_correo, tv_telefono, tv_copyright;
    private Button btn_copyright, btn_info;
    private Menu options_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.sin_text) + "\">" + getString(R.string.app_name) + "</font>"));

        declararVariables();
        cargarPreferencias();

        final ZoomLinearLayout zoomLinearLayout = findViewById(R.id.zoom_linear_layout);
        zoomLinearLayout.setOnTouchListener((v, event) -> {
            zoomLinearLayout.init(Info.this);
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

        Intent intent;
        if (item.getItemId() == R.id.initial_page) {
            intent = new Intent(this, Inicio.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_settings) {
            intent = new Intent(this, Options.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.info_app) {
            finish();
            startActivity(getIntent());
            return true;
        } else if (item.getItemId() == R.id.exit_app) {
            finishAffinity();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void onClickBtnCopyright(View view) {
        tv_titulo.setVisibility(View.GONE);
        tv_version.setVisibility(View.GONE);
        tv_autor.setVisibility(View.GONE);
        tv_ayuda.setVisibility(View.GONE);
        tv_correo.setVisibility(View.GONE);
        tv_telefono.setVisibility(View.GONE);
        btn_copyright.setVisibility(View.GONE);
        tv_copyright.setVisibility(View.VISIBLE);
        btn_info.setVisibility(View.VISIBLE);
    }

    public void onClickBtnInfo(View view) {
        tv_titulo.setVisibility(View.VISIBLE);
        tv_version.setVisibility(View.VISIBLE);
        tv_autor.setVisibility(View.VISIBLE);
        tv_ayuda.setVisibility(View.VISIBLE);
        tv_correo.setVisibility(View.VISIBLE);
        tv_telefono.setVisibility(View.VISIBLE);
        btn_copyright.setVisibility(View.VISIBLE);
        tv_copyright.setVisibility(View.GONE);
        btn_info.setVisibility(View.GONE);
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
                tv_titulo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_version.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_autor.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_ayuda.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_correo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_telefono.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                btn_copyright.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_copyright.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                btn_info.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                btn_copyright.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sin_bg, null));
                btn_info.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.sin_bg, null));
                break;

            case "rojo":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.rojo_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.rojo_medio, null)));
                tv_titulo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_version.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_autor.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_ayuda.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_correo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_telefono.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                btn_copyright.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_copyright.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                btn_info.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                btn_copyright.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_bg, null));
                btn_info.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_bg, null));
                break;

            case "verde":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.verde_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.verde_medio, null)));
                tv_titulo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_version.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_autor.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_ayuda.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_correo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_telefono.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                btn_copyright.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_copyright.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                btn_info.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                btn_copyright.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_bg, null));
                btn_info.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_bg, null));
                break;

            case "azul":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.azul_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.azul_medio, null)));
                tv_titulo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_version.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_autor.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_ayuda.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_correo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_telefono.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                btn_copyright.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_copyright.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                btn_info.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                btn_copyright.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_bg, null));
                btn_info.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_bg, null));
                break;

            case "byn":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.byn_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.byn_medio, null)));
                tv_titulo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_version.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_autor.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_ayuda.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_correo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_telefono.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                btn_copyright.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_copyright.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                btn_info.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                btn_copyright.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_bg, null));
                btn_info.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_bg, null));
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
        tv_version = findViewById(R.id.tv_version);
        tv_autor = findViewById(R.id.tv_autor);
        tv_ayuda = findViewById(R.id.tv_ayuda);
        tv_correo = findViewById(R.id.tv_correo);
        tv_telefono = findViewById(R.id.tv_telefono);
        tv_copyright = findViewById(R.id.tv_copyright);
        btn_copyright = findViewById(R.id.btn_copyright);
        btn_info = findViewById(R.id.btn_info);

    }
}