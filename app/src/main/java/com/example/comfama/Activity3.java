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
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class Activity3 extends AppCompatActivity {

    private Intent intent;
    private WebView webView;
    private TextView tv_lidl, tv_amazon, tv_ikea, tv_drfarma, tv_leroymerlin;
    private LinearLayout layout_principal_options, layout_secundario_options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"" + getResources().getColor(R.color.sin_text) + "\">" + getString(R.string.app_name) + "</font>"));

        declararVariables();
        cargarPreferencias();

        intent = getIntent();
        String datoRecibido = intent.getStringExtra("url");
        visibilidadWebView(datoRecibido);
        webView.loadUrl(datoRecibido);
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