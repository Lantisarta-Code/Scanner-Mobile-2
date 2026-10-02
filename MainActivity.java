package com.easycentral.bot;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * The whole screen of the app is the page in assets/index.html (it also does the encrypted talking to the
 * desktop program). This class only shows that page and gives it a place to keep its settings.
 */
public class MainActivity extends Activity {

    private WebView web;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        web = new WebView(this);
        setContentView(web);

        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowUniversalAccessFromFileURLs(true);      // the page talks to the computer in the home network
        s.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        s.setTextZoom(100);

        web.setWebViewClient(new WebViewClient());
        web.addJavascriptInterface(new Bridge(), "Android");
        web.loadUrl("file:///android_asset/index.html");
    }

    @Override
    protected void onResume() {
        super.onResume();
        web.onResume();
    }

    @Override
    protected void onPause() {
        web.onPause();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        // the page closes its own dialog / goes back to the first tab; otherwise the app goes to the background
        web.evaluateJavascript("(window.onBack && window.onBack()) ? 1 : 0", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                if (!"1".equals(value)) {
                    moveTaskToBack(true);
                }
            }
        });
    }

    private SharedPreferences prefs() {
        return getSharedPreferences("easycentral", Context.MODE_PRIVATE);
    }

    /** What the page can ask the phone for. */
    private class Bridge {

        @JavascriptInterface
        public String getPref(String key) {
            return prefs().getString(key, "");
        }

        @JavascriptInterface
        public void setPref(String key, String value) {
            prefs().edit().putString(key, value).apply();
        }

        @JavascriptInterface
        public void copy(final String text) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    if (cm != null) {
                        cm.setPrimaryClip(ClipData.newPlainText("links", text));
                    }
                }
            });
        }
    }
}
