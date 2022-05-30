package com.example.comfama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private LinearLayout layout_principal_options, layout_secundario_options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.sin_text) + "\">" + getString(R.string.app_name) + "</font>"));

        declararVariables();
        cargarPreferencias();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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

    public void onClickImgView(View view) {
        intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    private void cargarPreferencias() {

        SharedPreferences preferencias = getSharedPreferences("daltonismo", Context.MODE_PRIVATE);

        String daltonismo = preferencias.getString("daltonismo", "sin");

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
        layout_principal_options = findViewById(R.id.layout_principal_options);
        layout_secundario_options = findViewById(R.id.layout_secundario_options);
    }
}