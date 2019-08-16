package com.teamcs.mm.militarydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class MainActivity extends Activity {

    TextToSpeech ttobj;
    WebAppInterface webAppInterface;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                //Toast.makeText(getApplicationContext(),"onAdLoaded",Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AdRequest adRequest = new AdRequest.Builder().build();
                        mAdView.loadAd(adRequest);
                    }
                }, 30000);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                //Toast.makeText(getApplicationContext(),"onAdFailedToLoad",Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AdRequest adRequest = new AdRequest.Builder().build();
                        mAdView.loadAd(adRequest);
                    }
                }, 30000);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
               /// Toast.makeText(getApplicationContext(),"onAdOpened",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                //Toast.makeText(getApplicationContext(),"onAdClicked",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                //Toast.makeText(getApplicationContext(),"onAdLeftApplication",Toast.LENGTH_SHORT).show();

                //PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
                //mPublisherAdView.loadAd(adRequest);
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                //Toast.makeText(getApplicationContext(),"onAdClosed",Toast.LENGTH_SHORT).show();

                //PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
                //mPublisherAdView.loadAd(adRequest);
            }
        });


        final WebView WV = (WebView) findViewById(R.id.webView1);

        // Decaring string ie,URL , Asset Url
        final String URL = "file:///android_asset/index.html";



        WebSettings settings = WV.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        String databasePath = this.getApplicationContext().getDir("databases", Context.MODE_PRIVATE).getPath();
        settings.setDatabasePath(databasePath);
        webAppInterface=new WebAppInterface(this,WV);
        WV.addJavascriptInterface(webAppInterface, "Android");
        WV.loadUrl(URL);

        ttobj=new  TextToSpeech(getApplicationContext(),
                new  TextToSpeech.OnInitListener()  {
                    @Override
                    public  void  onInit(int  status)  {
                        if(status  !=  TextToSpeech.ERROR){ttobj.setLanguage(Locale.UK);}
                        else{
                            Toast.makeText(getApplicationContext(),  "Can't initialize Text to Speech feature",   Toast.LENGTH_SHORT).show();
                        }
                    }
                    // onInit end
                }); // new tts listner end


    }

    private boolean _doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        webAppInterface.back_to_main();
        if (_doubleBackToExitPressedOnce)
        {
            super.onBackPressed();
            return;
        }
        this._doubleBackToExitPressedOnce = true;
        //Toast.makeText(this, getResources().getString(R.string.exit_greeting), Toast.LENGTH_SHORT).show();
        //Snackbar.make(getWindow().getDecorView(), getResources().getString(R.string.exit_greeting), Snackbar.LENGTH_LONG).show();

        //WV.loadUrl("javascript:android_hello()");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                _doubleBackToExitPressedOnce = false;
            }
        }, 2000);
        /*
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }
}
