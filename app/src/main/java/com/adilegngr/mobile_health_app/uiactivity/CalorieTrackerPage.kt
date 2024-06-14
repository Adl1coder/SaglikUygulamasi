package com.adilegngr.mobile_health_app.uiactivity

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.LocalDrink
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adilegngr.mobile_health_app.TopBar
import com.adilegngr.mobile_health_app.model.MedicalDatabaseHelper
import com.adilegngr.mobile_health_app.model.MedicalDb
import com.adilegngr.mobile_health_app.ui.theme.Activityscreen
import com.adilegngr.mobile_health_app.ui.theme.fillmaxwid
import com.adilegngr.mobile_health_app.ui.theme.fnt18
import com.adilegngr.mobile_health_app.ui.theme.horzcenter
import com.adilegngr.mobile_health_app.ui.theme.green1
import com.adilegngr.mobile_health_app.ui.theme.rcshape
import com.adilegngr.mobile_health_app.ui.theme.reglogbut
import com.adilegngr.mobile_health_app.ui.theme.reglogbuttxtcol
import com.adilegngr.mobile_health_app.ui.theme.subtxtcol
import com.adilegngr.mobile_health_app.ui.theme.txtbold
import com.adilegngr.mobile_health_app.ui.theme.txtcenter
import com.adilegngr.mobile_health_app.ui.theme.vertspace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalorieTrackerPage(context: Context, navController: NavController,databaseHelper:MedicalDatabaseHelper)
{
    Scaffold(
        topBar = { TopBar("GÜNLÜK KALORİ TAKİBİ") },
        content = {pad -> CalorieTrackerFill(pad,context,navController,databaseHelper) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalorieTrackerFill(h: PaddingValues, context: Context, navController: NavController, databaseHelper: MedicalDatabaseHelper) {
    Column(
        modifier = Activityscreen,
        horizontalAlignment = horzcenter,
        verticalArrangement = vertspace
    )
    {
        val foodItem = remember { mutableStateOf(TextFieldValue("")) }
        val foodCalories = remember { mutableStateOf(TextFieldValue("")) }
        val drinkItem = remember { mutableStateOf(TextFieldValue("")) }
        val drinkCalories = remember { mutableStateOf(TextFieldValue("")) }
        val totalCalories = remember { mutableStateOf(0) }
        val txtfieldcol = TextFieldDefaults.textFieldColors(containerColor = Color.White)

        Text(
            text = "Günlük Kalori Takibinizi Yapın", fontSize = fnt18, color = subtxtcol,
            fontWeight = txtbold, modifier = Modifier.padding(top=30.dp)
        )

        TextField(
            value = foodItem.value,
            onValueChange = {
                foodItem.value = it
            },
            singleLine = true,
            colors = txtfieldcol,
            label = { Text("Yiyecek Girin") },
            modifier = fillmaxwid.border(BorderStroke(2.dp, green1)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            leadingIcon = { Icon(Icons.Outlined.Fastfood, contentDescription = null) }
        )

        TextField(
            value = foodCalories.value,
            onValueChange = {
                foodCalories.value = it
            },
            singleLine = true,
            colors = txtfieldcol,
            label = { Text("Kalori Girin") },
            modifier = fillmaxwid.border(BorderStroke(2.dp, green1)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        TextField(
            value = drinkItem.value,
            onValueChange = {
                drinkItem.value = it
            },
            singleLine = true,
            colors = txtfieldcol,
            label = { Text("İçecek Girin") },
            modifier = fillmaxwid.border(BorderStroke(2.dp, green1)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            leadingIcon = { Icon(Icons.Outlined.LocalDrink, contentDescription = null) }
        )

        TextField(
            value = drinkCalories.value,
            onValueChange = {
                drinkCalories.value = it
            },
            singleLine = true,
            colors = txtfieldcol,
            label = { Text("Kalori Girin") },
            modifier = fillmaxwid.border(BorderStroke(2.dp, green1)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        Button(
            onClick = {
                val total = (foodCalories.value.text.toIntOrNull() ?: 0) + (drinkCalories.value.text.toIntOrNull() ?: 0)
                totalCalories.value = total
                if (foodItem.value.text.isNotEmpty() && foodCalories.value.text.isNotEmpty() &&
                    drinkItem.value.text.isNotEmpty() && drinkCalories.value.text.isNotEmpty()
                ) {
                    val userCalorieData = MedicalDb(
                        id = null,
                        email = "",  // Kullanıcı email bilgisini buraya ekleyin
                        location = "",  // Konum bilgisi ekleyin
                        bmi = 0,  // Vücut kitle indeksi ekleyin
                        age = "",  // Yaş bilgisi ekleyin
                        gender = "",  // Cinsiyet bilgisi ekleyin
                        bloodgrp = "",  // Kan grubu bilgisi ekleyin
                        bloodpres = "",  // Tansiyon bilgisi ekleyin
                        category = ""  // Kategori bilgisi ekleyin
                    )
                    databaseHelper.insertnewUser(userCalorieData)
                    navController.navigate("dashboard/${totalCalories.value}")
                } else {
                    Toast.makeText(
                        context, "Lütfen Tüm Bilgileri Doldurun",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = fillmaxwid,
            colors = ButtonDefaults.buttonColors(green1),
            shape = rcshape
        ) {
            Text(
                text = "KALORİLERİ GÖNDER",
                color = reglogbuttxtcol,
                fontSize = reglogbut,
                textAlign = txtcenter
            )
        }

        Text(
            text = "Günlük Toplam Kalori: ${totalCalories.value} kcal",
            fontSize = fnt18,
            color = subtxtcol,
            fontWeight = txtbold,
            modifier = Modifier.padding(top=20.dp)
        )
    }
}
