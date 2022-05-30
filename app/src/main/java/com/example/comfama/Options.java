package com.example.comfama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Options extends AppCompatActivity {

    private SwitchCompat btn_switch_noche, btn_switch_escribir;
    private SharedPreferences preferencias;
    private SharedPreferences.Editor editor;
    private RadioGroup rg_dal;
    private LinearLayout layout_principal_options, layout_secundario_options;
    private TextView tv_noche, tv_mudos, tv_daltonismo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.sin_text) + "\">" + getString(R.string.app_name) + "</font>"));

        declararVariables();
        cargarPreferencias();

        btn_switch_noche.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked){
                // Modo noche
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                Toast.makeText(Options.this, "Seleccionado Modo Noche", Toast.LENGTH_SHORT).show();
            } else {
                // Modo día
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        if (item.getItemId() == R.id.initial_page) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_settings) {
            finish();
            startActivity(getIntent());
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

    private void cargarPreferencias() {

        SharedPreferences preferencias = getSharedPreferences("daltonismo", Context.MODE_PRIVATE);

        String daltonismo = preferencias.getString("daltonismo", "sin");

        if (daltonismo.equals("sin")) {
            layout_principal_options.setBackgroundColor(getResources().getColor(R.color.sin_bg));
            layout_secundario_options.setBackgroundColor(getResources().getColor(R.color.sin_medio));
            Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.sin_text) + "\">" + getString(R.string.app_name) + "</font>"));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.sin_medio)));
            tv_noche.setTextColor(getResources().getColor(R.color.sin_text));
            tv_mudos.setTextColor(getResources().getColor(R.color.sin_text));
            tv_daltonismo.setTextColor(getResources().getColor(R.color.sin_text));
        } else if (daltonismo.equals("rojo")) {
            layout_principal_options.setBackgroundColor(getResources().getColor(R.color.rojo_bg));
            layout_secundario_options.setBackgroundColor(getResources().getColor(R.color.rojo_medio));
            Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.rojo_text) + "\">" + getString(R.string.app_name) + "</font>"));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.rojo_medio)));
            tv_noche.setTextColor(getResources().getColor(R.color.rojo_text));
            tv_mudos.setTextColor(getResources().getColor(R.color.rojo_text));
            tv_daltonismo.setTextColor(getResources().getColor(R.color.rojo_text));
        } else if (daltonismo.equals("verde")) {
            layout_principal_options.setBackgroundColor(getResources().getColor(R.color.verde_bg));
            layout_secundario_options.setBackgroundColor(getResources().getColor(R.color.verde_medio));
            Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.verde_text) + "\">" + getString(R.string.app_name) + "</font>"));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.verde_medio)));
            tv_noche.setTextColor(getResources().getColor(R.color.verde_text));
            tv_mudos.setTextColor(getResources().getColor(R.color.verde_text));
            tv_daltonismo.setTextColor(getResources().getColor(R.color.verde_text));
        } else if (daltonismo.equals("azul")) {
            layout_principal_options.setBackgroundColor(getResources().getColor(R.color.azul_bg));
            layout_secundario_options.setBackgroundColor(getResources().getColor(R.color.azul_medio));
            Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.azul_text) + "\">" + getString(R.string.app_name) + "</font>"));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.azul_medio)));
            tv_noche.setTextColor(getResources().getColor(R.color.azul_text));
            tv_mudos.setTextColor(getResources().getColor(R.color.azul_text));
            tv_daltonismo.setTextColor(getResources().getColor(R.color.azul_text));
        } else if (daltonismo.equals("byn")) {
            layout_principal_options.setBackgroundColor(getResources().getColor(R.color.byn_bg));
            layout_secundario_options.setBackgroundColor(getResources().getColor(R.color.byn_medio));
            Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.byn_text) + "\">" + getString(R.string.app_name) + "</font>"));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.byn_medio)));
            tv_noche.setTextColor(getResources().getColor(R.color.byn_text));
            tv_mudos.setTextColor(getResources().getColor(R.color.byn_text));
            tv_daltonismo.setTextColor(getResources().getColor(R.color.byn_text));
        }
    }

    public void declararVariables() {
        btn_switch_noche = findViewById(R.id.btn_switch_noche);
        btn_switch_escribir = findViewById(R.id.btn_switch_escribir);
        rg_dal = findViewById(R.id.rg_dal);
        layout_principal_options = findViewById(R.id.layout_principal_options);
        layout_secundario_options = findViewById(R.id.layout_secundario_options);
        tv_noche = findViewById(R.id.tv_noche);
        tv_mudos = findViewById(R.id.tv_mudos);
        tv_daltonismo = findViewById(R.id.tv_daltonismo);
    }
}