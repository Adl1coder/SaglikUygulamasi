package com.adilegngr.mobile_health_app.uiactivity

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bedtime
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adilegngr.mobile_health_app.BottomBar
import com.adilegngr.mobile_health_app.TopApplicationBar
import com.adilegngr.mobile_health_app.ui.theme.Activityscreen
import com.adilegngr.mobile_health_app.ui.theme.Black
import com.adilegngr.mobile_health_app.ui.theme.fillmaxwid
import com.adilegngr.mobile_health_app.ui.theme.fnt21
import com.adilegngr.mobile_health_app.ui.theme.horzcenter
import com.adilegngr.mobile_health_app.ui.theme.green1
import com.adilegngr.mobile_health_app.ui.theme.rcshape
import com.adilegngr.mobile_health_app.ui.theme.reglogbut
import com.adilegngr.mobile_health_app.ui.theme.reglogbuttxtcol
import com.adilegngr.mobile_health_app.ui.theme.subtxtcol
import com.adilegngr.mobile_health_app.ui.theme.subtxtsize
import com.adilegngr.mobile_health_app.ui.theme.txtbold
import com.adilegngr.mobile_health_app.ui.theme.txtcenter
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SleepTrackerPage(navController: NavController)
{
    Scaffold(
        topBar = { TopApplicationBar("UYKU İZLEME",navController) },
        content = {pad -> SleepTracker(pad) },
        bottomBar = { BottomBar(navController) }
    )
}
@Composable
fun SleepTracker(h:PaddingValues) {
    var start by remember { mutableStateOf(0L) }
    var stop by remember { mutableStateOf(0L) }
    var startstr by remember { mutableStateOf("") }
    var stopstr by remember { mutableStateOf("") }
    var isRunning by remember { mutableStateOf(false) }
    var starten by remember { mutableStateOf(false) }
    var stopen by remember { mutableStateOf(false) }
    var tracken by remember { mutableStateOf(false) }
    val butcolor = ButtonDefaults.buttonColors(green1)
    Column(
        modifier = Activityscreen,
        horizontalAlignment = horzcenter,
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        Text(
            text = "Uykunuzu Takip Edin",
            fontSize = fnt21,
            fontWeight = txtbold,
            color = Black,
            modifier = fillmaxwid.padding(top = 25.dp),
            textAlign = txtcenter
        )
        Icon(
            imageVector = Icons.Outlined.Bedtime,
            contentDescription = "Sleep",
            tint = green1,
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = "Uygulamayı Arka Planda Açık Tutun",
            fontSize = subtxtsize,
            fontWeight = txtbold,
            color = subtxtcol,
            modifier = fillmaxwid,
            textAlign = txtcenter
        )
        Text(
            text = "'Sağlam kafa , sağlam vücutta bulunur.  Mustafa Kemal  Atatürk'",
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = txtbold,
            modifier = fillmaxwid,
            textAlign = txtcenter
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = fillmaxwid
        )
        {
            Button(
                onClick = {
                    start = System.currentTimeMillis()
                    isRunning = true
                    starten = true
                },
                modifier = Modifier.width(120.dp),
                colors = butcolor,
                shape = rcshape
            ) {
                Text(
                    text = "BAŞLA", color = reglogbuttxtcol, fontSize = reglogbut,
                    textAlign = txtcenter
                )
            }
            Button(
                onClick = {
                    stop = System.currentTimeMillis()
                    isRunning = false
                    stopen = true
                },
                modifier = Modifier.width(120.dp),
                colors = butcolor,
                shape = rcshape
            )
            {
                Text(
                    text = "DUR", color = reglogbuttxtcol, fontSize = reglogbut,
                    textAlign = txtcenter
                )
            }
        }
        startstr = formatDateTime(start)
        stopstr = formatDateTime(stop)
        Button(
            onClick = {
                isRunning = false
                tracken = true
            },
            modifier = fillmaxwid,
            colors = butcolor,
            shape = rcshape
        )
        {
            Text(
                text = "UYKUYU TAKİP ET", color = reglogbuttxtcol, fontSize = reglogbut,
                textAlign = txtcenter
            )
        }
        if (starten) {
            Row(horizontalArrangement = Arrangement.Center)
            {
                Text(
                    text = "Başlangıç : " + startstr,
                    fontSize = 18.sp,
                    fontWeight = txtbold,
                    color = Black
                )
            }
        }
        if (stopen) {
            Row(horizontalArrangement = Arrangement.Center)
            {
                Text(
                    text = "Bitiş  : " + stopstr,
                    fontSize = 18.sp,
                    fontWeight = txtbold,
                    color = Black
                )
            }
        }
        if (tracken) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 35.dp)
            )
            {
                Text(
                    text = "Uyku Süresi : " + formatTime(stop - start),
                    fontSize = fnt21,
                    fontWeight = txtbold,
                    color = Color.Blue
                )
            }
        }
    }
}
fun formatDateTime(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss", Locale.getDefault())
    return dateFormat.format(Date(timestamp))
}
fun formatTime(timeInMillis: Long): String {
    val hours = (timeInMillis / (1000 * 60 * 60)) % 24
    val minutes = (timeInMillis / (1000 * 60)) % 60
    val seconds = (timeInMillis / 1000) % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}