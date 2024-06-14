package com.adilegngr.mobile_health_app.uiactivity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Egg
import androidx.compose.material.icons.outlined.LocalPizza
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.NordicWalking
import androidx.compose.material.icons.outlined.Person4
import androidx.compose.material.icons.outlined.Power
import androidx.compose.material.icons.outlined.RiceBowl
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material.icons.outlined.SetMeal
import androidx.compose.material.icons.outlined.SportsGymnastics
import androidx.compose.material3.Button

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adilegngr.mobile_health_app.BottomBar
import com.adilegngr.mobile_health_app.TopApplicationBar
import com.adilegngr.mobile_health_app.ui.theme.fillmaxwid
import com.adilegngr.mobile_health_app.ui.theme.fnt18
import com.adilegngr.mobile_health_app.ui.theme.fnt19
import com.adilegngr.mobile_health_app.ui.theme.fnt20
import com.adilegngr.mobile_health_app.ui.theme.icon
import com.adilegngr.mobile_health_app.ui.theme.mobkeypad
import com.adilegngr.mobile_health_app.ui.theme.purewhite
import com.adilegngr.mobile_health_app.ui.theme.green1
import com.adilegngr.mobile_health_app.ui.theme.rcshape
import com.adilegngr.mobile_health_app.ui.theme.reglogbut
import com.adilegngr.mobile_health_app.ui.theme.reglogbuttxtcol
import com.adilegngr.mobile_health_app.ui.theme.subtxtcol
import com.adilegngr.mobile_health_app.ui.theme.subtxtsize
import com.adilegngr.mobile_health_app.ui.theme.txtbold
import com.adilegngr.mobile_health_app.ui.theme.txtcenter
import com.adilegngr.mobile_health_app.ui.theme.vertspace
import com.adilegngr.mobile_health_app.ui.theme.wid220

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaloriePage(navController: NavController)
{
    Scaffold(
        topBar = { TopApplicationBar("KALORİ YÖNETİMİ",navController) },
        content = {pad -> Calorie(pad) },
        bottomBar = { BottomBar(navController) }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calorie(h: PaddingValues) {
    val sp5 = Modifier.padding(5.dp)
    val ht = remember { mutableStateOf(TextFieldValue("")) }
    val wt = remember { mutableStateOf(TextFieldValue("")) }
    val age = remember { mutableStateOf(TextFieldValue("")) }
    val gender = listOf("Kız", "Erkek")
    val genindex = remember { mutableStateOf(0) }
    val activities = listOf(
        "Egzersiz Yok (Sedanter)",
        "Hafif Aktif (Haftada 1-3 gün)",
        "Orta Derecede Aktif (Haftada 6-7 gün)",
        "Çok Aktif (Günde iki kez)",
        "Ekstra Aktif (Zor egzersiz / Antrenman)"
    )
    val actindex = remember { mutableStateOf(0) }
    val diettype = listOf(
        "Yüksek Karbonhidrat (60:25:15)", "Orta Karbonhidrat (50:30:20)", "Zone Karbonhidrat (40:30:30)",
        "Düşük Karbonhidrat (25:35:40)", "Keto Diyet (05:35:60)"
    )
    val dietindex = remember { mutableStateOf(0) }
    val extra = remember { mutableStateOf(0) }
    val tdeeval = listOf(1.2, 1.375, 1.55, 1.725, 1.9)
    val carblist = listOf(0.6, 0.5, 0.4, 0.25, 0.05)
    val protlist = listOf(0.25, 0.3, 0.3, 0.35, 0.35)
    val fatlist = listOf(0.15, 0.2, 0.3, 0.4, 0.6)
    val txtcol = TextFieldDefaults.textFieldColors(containerColor = Color.White)
    val butcolor = ButtonDefaults.buttonColors(green1)
    val enable = remember { mutableStateOf(false) }
    Column(
        modifier = fillmaxwid.fillMaxSize().background(purewhite).padding(25.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly,
    )
    {
        if (!enable.value) {
            Text(
                text = "Kalori Alımınızı Yönetin",
                fontSize = fnt20,
                fontWeight = txtbold,
                modifier = fillmaxwid.padding(top = 25.dp),
                textAlign = txtcenter,
                color = Color.Black
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = fillmaxwid
            )
            {
                TextField(
                    value = ht.value,
                    onValueChange = {
                        ht.value = it
                    },
                    colors = txtcol,
                    label = { Text("Boy") },
                    placeholder = { Text("cm") },
                    modifier = Modifier
                        .width(86.dp)
                        .border(BorderStroke(2.dp, green1)),
                    keyboardOptions = mobkeypad,
                )
                TextField(
                    value = wt.value,
                    onValueChange = {
                        wt.value = it
                    },
                    colors = txtcol,
                    label = { Text("Kilo") },
                    placeholder = { Text("kg") },
                    modifier = Modifier
                        .width(86.dp)
                        .border(BorderStroke(2.dp, green1)),
                    keyboardOptions = mobkeypad,
                )
                TextField(
                    value = age.value,
                    onValueChange = {
                        if (it.text.length <= 3)
                            age.value = it
                    },
                    colors = txtcol,
                    label = { Text("Yaş") },
                    modifier = Modifier
                        .width(86.dp)
                        .border(BorderStroke(2.dp, green1)),
                    keyboardOptions = mobkeypad,
                )
            }
            genindex.value =
                dropselector(Icons.Outlined.Person4, gender, "Cinsiyet", fillmaxwid)
            if (genindex.value == 0) {
                extra.value = 5
            } else {
                extra.value = -161
            }
            actindex.value = dropselector(
                Icons.Outlined.SportsGymnastics,
                activities,
                "Aktivite Türünü Seçin",
                fillmaxwid
            )
            dietindex.value =
                dropselector(Icons.Outlined.SetMeal, diettype, "Diyet Türünü Seçin", fillmaxwid)
            Button(
                onClick = { enable.value = true },
                modifier = fillmaxwid,
                colors = butcolor,
                shape = rcshape
            )
            {
                Text(
                    text = "KALORİ VERİLERİNİ HESAPLA",
                    color = reglogbuttxtcol,
                    fontSize = reglogbut,
                    textAlign = txtcenter
                )
            }
        }
        if (enable.value && ht.value.text.isNotEmpty() && wt.value.text.isNotEmpty() &&
            age.value.text.isNotEmpty() && actindex.value >= 0 && dietindex.value >= 0
        ) {
            Row(
                horizontalArrangement = vertspace,
                modifier = fillmaxwid.padding(top = 35.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Scale,
                    contentDescription = "Bmi",
                    tint = green1,
                    modifier = icon,
                )
                Text(
                    text = "Vücut Kitle İndeksi (BMI)".uppercase(),
                    fontSize = fnt18,
                    fontWeight = txtbold,
                    textAlign = txtcenter,
                    modifier = wid220,
                            color = Color.Black
                )
                Spacer(modifier = sp5)
                Text(
                    text = (wt.value.text.toFloat() * 10000 / (ht.value.text.toFloat()
                            * ht.value.text.toFloat())).toInt()
                        .toString(), fontSize = fnt19, color = green1,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = vertspace,
                modifier = fillmaxwid,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Power,
                    contentDescription = "tdee",
                    tint = green1,
                    modifier = icon,

                )
                Text(
                    text = "Günlük Enerji Harcaması".uppercase(),
                    fontSize = fnt18,
                    fontWeight = txtbold,
                    textAlign = txtcenter,
                    modifier = wid220,
                    color = Color.Black
                )
                Text(
                    text = (tdeeval.get(actindex.value) * (10 * wt.value.text.toInt()
                            + (6.25 * ht.value.text.toInt()) - (5 * age.value.text.toInt())
                            + extra.value)).toInt().toString(), fontSize = fnt19,
                    color = green1, fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = vertspace,
                modifier = fillmaxwid,
            ) {
                Icon(
                    imageVector = Icons.Outlined.MonitorWeight,
                    contentDescription = "Kilo Alma",
                    tint = green1,
                    modifier = icon,

                )
                Text(
                    text = "Kalori (Kilo Alma)".uppercase(),
                    fontSize = fnt18,
                    fontWeight = txtbold,
                    textAlign = txtcenter,
                    modifier = wid220,
                    color = Color.Black
                )
                Text(
                    text = ((tdeeval.get(actindex.value) * (10 * wt.value.text.toInt()
                            + (6.25 * ht.value.text.toInt()) - (5 * age.value.text.toInt())
                            + extra.value)) * 1.20).toInt().toString(), fontSize = fnt19,
                    color = green1, fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = vertspace,
                modifier = fillmaxwid,
            ) {
                Icon(
                    imageVector = Icons.Outlined.NordicWalking,
                    contentDescription = "Kilo Verme",
                    tint = green1,
                    modifier = icon
                )
                Text(
                    text = "Kalori (Kilo Verme)".uppercase(),
                    fontSize = fnt18,
                    fontWeight = txtbold,
                    textAlign = txtcenter,
                    modifier = wid220,
                    color = Color.Black
                )
                Text(
                    text = ((tdeeval.get(actindex.value) * (10 * wt.value.text.toInt()
                            + (6.25 * ht.value.text.toInt()) - (5 * age.value.text.toInt())
                            + extra.value)) * 0.80).toInt().toString(), fontSize = fnt19,
                    color = green1, fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = vertspace,
                modifier = fillmaxwid,
            ) {
                Icon(
                    imageVector = Icons.Outlined.RiceBowl,
                    contentDescription = "Karbo Kcal",
                    tint = green1,
                    modifier = icon,

                )
                Text(
                    text = "Karbonhidrat Kcal / Günlük İhtiyaç".uppercase(),
                    fontSize = fnt18,
                    fontWeight = txtbold,
                    textAlign = txtcenter,
                    modifier = wid220,
                    color = Color(0xFF0D7A70)
                )
                Text(
                    text = ((carblist.get(dietindex.value)) * (tdeeval.get(actindex.value)
                            * (10 * wt.value.text.toInt()) + (6.25 * ht.value.text.toInt())
                            - (5 * age.value.text.toInt()) + extra.value)).toInt()
                        .toString(), fontSize = fnt19, color = green1,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = vertspace,
                modifier = fillmaxwid,
            ) {
                Icon(                    imageVector = Icons.Outlined.RiceBowl,
                    contentDescription = "Karbo Gram",
                    tint = green1,
                    modifier = icon,

                )
                Text(
                    text = "Karbonhidrat Gram / Günlük İhtiyaç".uppercase(),
                    fontSize = fnt18,
                    fontWeight = txtbold,
                    textAlign = txtcenter,
                    modifier = wid220,
                    color = Color(0xFF0D7A70)
                )
                Text(
                    text = (((carblist.get(dietindex.value)) * (tdeeval.get(actindex.value)
                            * (10 * wt.value.text.toInt() + (6.25 * ht.value.text.toInt())
                            - (5 * age.value.text.toInt()) + extra.value))) / 4).toInt()
                        .toString(), fontSize = fnt19, color = green1,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = vertspace,
                modifier = fillmaxwid,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Egg,
                    contentDescription = "Protein Kcal",
                    tint = green1,
                    modifier = icon
                )
                Text(
                    text = "Protein Kcal / Günlük İhtiyaç".uppercase(),
                    fontSize = fnt18,
                    fontWeight = txtbold,
                    textAlign = txtcenter,
                    modifier = wid220,
                    color = Color.Blue
                )
                Text(
                    text = ((protlist.get(dietindex.value)) * (tdeeval.get(actindex.value)
                            * (10 * wt.value.text.toInt()) + (6.25 * ht.value.text.toInt())
                            - (5 * age.value.text.toInt()) + extra.value)).toInt()
                        .toString(), fontSize = fnt19, color = green1,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = vertspace,
                modifier = fillmaxwid,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Egg,
                    contentDescription = "Protein Gram",
                    tint = green1,
                    modifier = icon
                )
                Text(
                    text = "Protein Gram / Günlük İhtiyaç".uppercase(),
                    fontSize = fnt18,
                    fontWeight = txtbold,
                    textAlign = txtcenter,
                    modifier = wid220,
                    color = Color.Blue
                )
                Text(
                    text = ((protlist.get(dietindex.value)) * (tdeeval.get(actindex.value)
                            * (10 * wt.value.text.toInt() + (6.25 * ht.value.text.toInt())
                            - (5 * age.value.text.toInt()) + extra.value)) / 4).toInt()
                        .toString(), fontSize = fnt19, color = green1,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = vertspace,
                modifier = fillmaxwid,
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalPizza,
                    contentDescription = "Yağ Kcal",
                    tint = green1,
                    modifier = icon
                )
                Text(
                    text = "Yağ Kcal / Günlük İhtiyaç".uppercase(),
                    fontSize = fnt18,
                    fontWeight = txtbold,
                    textAlign = txtcenter,
                    modifier = wid220,
                    color = Color.Red
                )
                Text(
                    text = ((fatlist.get(dietindex.value)) * (tdeeval.get(actindex.value)
                            * (10 * wt.value.text.toInt()) + (6.25 * ht.value.text.toInt())
                            - (5 * age.value.text.toInt()) + extra.value)).toInt()
                        .toString(), fontSize = fnt19, color = green1,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = vertspace,
                modifier = fillmaxwid.padding(bottom = 35.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalPizza,
                    contentDescription = "Yağ Gram",
                    tint = green1,
                    modifier = icon
                )
                Text(
                    text = "Yağ Gram / Günlük İhtiyaç".uppercase(),
                    fontSize = fnt18,
                    fontWeight = txtbold,
                    textAlign = txtcenter,
                    modifier = wid220,
                    color = Color.Red
                )
                Text(
                    text = ((fatlist.get(dietindex.value)) * (tdeeval.get(actindex.value)
                            * (10 * wt.value.text.toInt()) + (6.25 * ht.value.text.toInt())
                            - (5 * age.value.text.toInt()) + extra.value) / 9).toInt()
                        .toString(), fontSize = fnt19, color = green1,
                    fontWeight = txtbold
                )
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = fillmaxwid,
            )
            {
                Text(
                    text = "Lütfen Tüm Detayları Doldurun", fontSize = subtxtsize,
                    fontWeight = txtbold, color = subtxtcol, textAlign = txtcenter
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dropselector(icon: ImageVector, options:List<String>, plchold: String, wid: Modifier):Int {
    var isexpand by remember { mutableStateOf(false) }
    var answer by remember { mutableStateOf("") }
    ExposedDropdownMenuBox(expanded = isexpand,
        onExpandedChange = { isexpand = it })
    {
        TextField(
            value = answer,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isexpand) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(containerColor = purewhite),
            leadingIcon = { Icon(icon, contentDescription = null) },
            modifier = wid.menuAnchor()
                .border(BorderStroke(2.dp, green1)),
            placeholder = { Text(text = plchold) },
        )
        ExposedDropdownMenu(
            expanded = isexpand,
            onDismissRequest = { isexpand = false },
            modifier = Modifier.background(color = purewhite)
        )
        {
            options.forEach { iterator ->
                DropdownMenuItem(
                    text = { Text(text = iterator) },
                    onClick = {
                        answer = iterator
                        isexpand = false
                    })
            }
        }
    }
    return options.indexOf(answer)
}