package com.example.artests.l21handbookcats;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class DetailActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        setWebView();
    }
    private void setWebView(){
        webView=(WebView)findViewById(R.id.webView);
        Intent intent=getIntent();
        String resName="n"+intent.getIntExtra("head",0);
        Log.i("name", resName);
        String text=readRawTextFile(getBaseContext(),getResources().getIdentifier(resName,"raw","com.example.artests.l21handbookcats"));
        webView.loadData(text,"text/html","ru_RU");
        webView.getSettings().setTextZoom(130);
        webView.setOnTouchListener(new WebView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

    }
    private String readRawTextFile(Context baseContext, int resId) {
        InputStream inputStream=baseContext.getResources().openRawResource(resId);
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String line;
        StringBuilder stringBuilder=new StringBuilder();
        try{
            while((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);
                stringBuilder.append("<br/>");
            }
        } catch (IOException e) {
            return null;
        }
        return stringBuilder.toString();
    }
}
