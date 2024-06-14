package com.adilegngr.mobile_health_app.uiactivity

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.adilegngr.mobile_health_app.BottomBar
import com.adilegngr.mobile_health_app.TopApplicationBar
import com.adilegngr.mobile_health_app.ui.theme.fillmaxwid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthArticlePage(navController: NavController) {
    Scaffold(
        topBar = { TopApplicationBar("SAĞLIK HABERLERİ", navController) },
        content = { pad -> HealthNews(pad) },
        bottomBar = { BottomBar(navController) }
    )
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HealthNews(h: PaddingValues) {
    Column(
        modifier = fillmaxwid.fillMaxSize().background(color = Color.White),
    ) {
        val url = "https://www.ntv.com.tr/saglik" //

        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        }, update = {
            it.loadUrl(url)
        })
    }
}

