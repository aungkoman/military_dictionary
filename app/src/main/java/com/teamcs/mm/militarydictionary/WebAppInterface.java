package com.teamcs.mm.militarydictionary;

import android.content.*;
import android.webkit.*;
import android.widget.*;



public class WebAppInterface {
    Context mContext;
    tts Tts;
    WebView webView;




    /** Instantiate the interface and set the context */
    WebAppInterface(Context c,WebView wv) {
        mContext = c;
        Tts =new tts(c);
        webView=wv;
    }


    public void hello(String msg) {

        final String webUrl = "javascript:hello('"+msg+"')";
        webView.loadUrl(webUrl);
    }


    public void back_to_main() {

        final String webUrl = "javascript:back_to_main()";
        webView.loadUrl(webUrl);
    }




    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    /** Show a toast long*/
    @JavascriptInterface
    public void showToastlong(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
    }

    /** Say a Speak*/
    @JavascriptInterface
    public void speak(String speak) {
        Tts.speakText(speak);
    }

    @JavascriptInterface
    public void share(String shareSubject,String shareBody)
    {
        //String shareBody = "Here is the share content body";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSubject);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

        mContext.startActivity(Intent.createChooser(sharingIntent, "Share with.."));
    }






}

