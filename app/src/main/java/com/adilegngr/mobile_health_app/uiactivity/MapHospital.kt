package com.adilegngr.mobile_health_app.uiactivity
import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.adilegngr.mobile_health_app.BottomBar
import com.adilegngr.mobile_health_app.TopApplicationBar
import com.adilegngr.mobile_health_app.ui.theme.boxes
import com.adilegngr.mobile_health_app.ui.theme.fillmaxwid
import com.adilegngr.mobile_health_app.ui.theme.purewhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapPage(navController: NavController)
{
    Scaffold(
        topBar = { TopApplicationBar("HARİTA ARAMA ",navController) },
        content = {pad -> GmapPage(pad) },
        bottomBar = { BottomBar(navController) }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GmapPage(h: PaddingValues) {
    Column(modifier = fillmaxwid.fillMaxSize())
    {
        val location = remember { mutableStateOf(TextFieldValue("")) }
        val boxcolor = TextFieldDefaults.textFieldColors(containerColor = purewhite)
        TextField(
            value = location.value,
            onValueChange = {
                location.value = it
            },
            colors = boxcolor,
            label = { Text("Konum Ara") },
            modifier = boxes,
            leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) }
        )
        val url =
            "https://www.google.com/maps/search/hospital+most+near+elazığ" + location.value.text + "/data=!3m1!4b1!4m3!2m2!5m1!10e2?authuser=0&entry=ttu"
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


