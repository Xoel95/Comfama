package com.example.comfama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Options extends AppCompatActivity {

    private SwitchCompat btn_switch_noche, btn_switch_escribir;
    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor;
    private RadioGroup rg_dal;
    private RadioButton rb_sin, rb_rojo, rb_verde, rb_azul, rb_byn;
    private LinearLayout layout_principal_options, layout_secundario_options;
    private TextView tv_noche, tv_mudos, tv_daltonismo;
    private Menu options_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.sin_text) + "\">" + getString(R.string.app_name) + "</font>"));

        declararVariables();
        cargarPreferencias();

        final ZoomLinearLayout zoomLinearLayout = findViewById(R.id.zoom_linear_layout);
        zoomLinearLayout.setOnTouchListener((v, event) -> {
            zoomLinearLayout.init(Options.this);
            return false;
        });

        btn_switch_noche.setOnCheckedChangeListener((buttonView, isChecked) -> {

            preferencias = getSharedPreferences("noche", Context.MODE_PRIVATE);
            editor = preferencias.edit();

            if (isChecked){
                // Modo noche
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                editor.putBoolean("noche",true);
                editor.apply();

                Toast.makeText(Options.this, "Seleccionado Modo Noche", Toast.LENGTH_SHORT).show();
            } else {
                // Modo día
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                editor.putBoolean("noche",false);
                editor.apply();

                Toast.makeText(Options.this, "Seleccionado Modo Día", Toast.LENGTH_SHORT).show();
            }
        });

        btn_switch_escribir.setOnCheckedChangeListener((buttonView, isChecked) -> {

            preferencias = getSharedPreferences("escribir", Context.MODE_PRIVATE);
            editor = preferencias.edit();

            if (isChecked){
                // Modo escribir
                editor.putBoolean("escribir",true);
                editor.apply();

                Toast.makeText(Options.this, "Seleccionado Modo Escribir", Toast.LENGTH_SHORT).show();
            } else {
                // Modo voz
                editor.putBoolean("escribir",false);
                editor.apply();

                Toast.makeText(Options.this, "Seleccionado Modo Hablar", Toast.LENGTH_SHORT).show();
            }
        });

        rg_dal.setOnCheckedChangeListener((radioGroup, id) -> {

            preferencias = getSharedPreferences("daltonismo", Context.MODE_PRIVATE);
            editor = preferencias.edit();

            if (id == R.id.rb_sin){
                editor.putString("daltonismo", "sin");
                editor.apply();

                Toast.makeText(Options.this, "Seleccionado Modo Sin Daltonismo", Toast.LENGTH_SHORT).show();
            } else if(id == R.id.rb_rojo){
                editor.putString("daltonismo", "rojo");
                editor.apply();

                Toast.makeText(Options.this, "Seleccionado Modo Deuteranopía", Toast.LENGTH_SHORT).show();
            } else if(id == R.id.rb_verde){
                editor.putString("daltonismo", "verde");
                editor.apply();

                Toast.makeText(Options.this, "Seleccionado Modo Protanopía", Toast.LENGTH_SHORT).show();
            } else if(id == R.id.rb_azul){
                editor.putString("daltonismo", "azul");
                editor.apply();

                Toast.makeText(Options.this, "Seleccionado Modo Tritanopía", Toast.LENGTH_SHORT).show();
            } else if(id == R.id.rb_byn){
                editor.putString("daltonismo", "byn");
                editor.apply();

                Toast.makeText(Options.this, "Seleccionado Modo Acromatopsia", Toast.LENGTH_SHORT).show();
            }

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
            finish();
            startActivity(getIntent());
            return true;
        } else if (item.getItemId() == R.id.info_app) {
            intent = new Intent(this, Info.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.exit_app) {
            finishAffinity();
            return true;
        }  else {
            return super.onOptionsItemSelected(item);
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
                tv_noche.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_mudos.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                tv_daltonismo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                rb_sin.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                rb_rojo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                rb_verde.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                rb_azul.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                rb_byn.setTextColor(ResourcesCompat.getColor(getResources(), R.color.sin_text, null));
                break;

            case "rojo":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rojo_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.rojo_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.rojo_medio, null)));
                tv_noche.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_mudos.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                tv_daltonismo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                rb_sin.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                rb_rojo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                rb_verde.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                rb_azul.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                rb_byn.setTextColor(ResourcesCompat.getColor(getResources(), R.color.rojo_text, null));
                break;

            case "verde":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.verde_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.verde_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.verde_medio, null)));
                tv_noche.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_mudos.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                tv_daltonismo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                rb_sin.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                rb_rojo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                rb_verde.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                rb_azul.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                rb_byn.setTextColor(ResourcesCompat.getColor(getResources(), R.color.verde_text, null));
                break;

            case "azul":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.azul_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.azul_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.azul_medio, null)));
                tv_noche.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_mudos.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                tv_daltonismo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                rb_sin.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                rb_rojo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                rb_verde.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                rb_azul.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                rb_byn.setTextColor(ResourcesCompat.getColor(getResources(), R.color.azul_text, null));
                break;

            case "byn":
                layout_principal_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_bg, null));
                layout_secundario_options.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.byn_medio, null));
                Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + ResourcesCompat.getColor(getResources(), R.color.byn_text, null) + "\">" + getString(R.string.app_name) + "</font>"));
                Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.byn_medio, null)));
                tv_noche.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_mudos.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                tv_daltonismo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                rb_sin.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                rb_rojo.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                rb_verde.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                rb_azul.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
                rb_byn.setTextColor(ResourcesCompat.getColor(getResources(), R.color.byn_text, null));
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
        btn_switch_noche = findViewById(R.id.btn_switch_noche);
        btn_switch_escribir = findViewById(R.id.btn_switch_escribir);
        rg_dal = findViewById(R.id.rg_dal);
        rb_sin = findViewById(R.id.rb_sin);
        rb_rojo = findViewById(R.id.rb_rojo);
        rb_verde = findViewById(R.id.rb_verde);
        rb_azul = findViewById(R.id.rb_azul);
        rb_byn = findViewById(R.id.rb_byn);
        layout_principal_options = findViewById(R.id.layout_principal_options);
        layout_secundario_options = findViewById(R.id.layout_secundario_options);
        tv_noche = findViewById(R.id.tv_noche);
        tv_mudos = findViewById(R.id.tv_mudos);
        tv_daltonismo = findViewById(R.id.tv_daltonismo);
    }
}