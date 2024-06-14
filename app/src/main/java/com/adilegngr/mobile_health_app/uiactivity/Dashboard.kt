package com.adilegngr.mobile_health_app.uiactivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bloodtype
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Person4
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adilegngr.mobile_health_app.BottomBar
import com.adilegngr.mobile_health_app.R
import com.adilegngr.mobile_health_app.TopApplicationBar
import com.adilegngr.mobile_health_app.model.MedicalDatabaseHelper
import com.adilegngr.mobile_health_app.model.NoteRepository
import com.adilegngr.mobile_health_app.model.UserDatabaseHelper
import com.adilegngr.mobile_health_app.ui.theme.Activityscreen
import com.adilegngr.mobile_health_app.ui.theme.fillmaxwid
import com.adilegngr.mobile_health_app.ui.theme.fnt20
import com.adilegngr.mobile_health_app.ui.theme.fnt21
import com.adilegngr.mobile_health_app.ui.theme.fnt23
import com.adilegngr.mobile_health_app.ui.theme.horzcenter
import com.adilegngr.mobile_health_app.ui.theme.horzstart
import com.adilegngr.mobile_health_app.ui.theme.iconsize
import com.adilegngr.mobile_health_app.ui.theme.green1
import com.adilegngr.mobile_health_app.ui.theme.subtxtcol
import com.adilegngr.mobile_health_app.ui.theme.txtbold
import com.adilegngr.mobile_health_app.ui.theme.vertspace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardPage(navController: NavController, email: String, databaseHelper1: UserDatabaseHelper, databaseHelper2: MedicalDatabaseHelper
)
{
    Scaffold(
        topBar = { TopApplicationBar("GÖSTERGE PANELİ",navController) },
        content = {pad -> DashboardContent(pad, databaseHelper1, databaseHelper2, email) },
        bottomBar = { BottomBar(navController) }
    )
}
@Composable
fun DashboardContent(
    h: PaddingValues, databaseHelper1: UserDatabaseHelper, databaseHelper2: MedicalDatabaseHelper, email: String
) {
    val sp5 = Modifier.padding(5.dp)
    val userreg = databaseHelper1.getUserByUseremail(email)
    val usermed = databaseHelper2.medgetUserByUseremail(email)
    Column(
        modifier = Activityscreen,
        horizontalAlignment = horzcenter,
        verticalArrangement = vertspace
    )
    {
        if (userreg != null && usermed != null) {
            val bmi = usermed.bmi
            Image(
                painter = painterResource(id = R.drawable.health_app),
                contentDescription = "Logo",
                modifier = Modifier.size(135.dp).padding(top = 15.dp)
            )
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Kullanıcı",
                    tint = green1,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = userreg.name.toString().uppercase(),
                    fontSize = fnt23,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = "Telefon",
                    tint = green1,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = userreg.mobile.toString().uppercase(),
                    fontSize = fnt23,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid
            )
            {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Konum",
                    tint = green1,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = usermed.location.toString().uppercase(),
                    fontSize = fnt23,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid,
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Person4,
                    contentDescription = "Cinsiyet",
                    tint = green1,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                if (usermed.gender == "Erkek") {
                    Image(
                        painter = painterResource(id = R.drawable.men),
                        contentDescription = "Erkek",
                        modifier = iconsize
                    )
                } else if (usermed.gender == "Kız") {
                    Image(
                        painter = painterResource(id = R.drawable.women),
                        contentDescription = "Kadın",
                        modifier = iconsize
                    )
                } else {

                }
                Spacer(modifier = sp5)
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "Yaş",
                    tint = green1,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = usermed.age.toString(), fontSize = fnt23,
                    fontWeight = txtbold
                )
                Spacer(modifier = sp5)
                Icon(
                    imageVector = Icons.Outlined.Bloodtype,
                    contentDescription = "Kan Grubu",
                    tint = green1,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = usermed.bloodgrp.toString(),
                    fontSize = fnt23,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid,
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Scale,
                    contentDescription = "Vücut Kitle İndeksi",
                    tint = green1,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = bmi.toString(), fontSize = fnt23,
                    fontWeight = txtbold
                )
                Spacer(modifier = sp5)
                if (bmi != null) {
                    if (bmi.toFloat() < 18.5) {
                        Text(
                            text = "(ZAYIF)",
                            color = Color(0xFF3F51B5),
                            fontSize = fnt21,
                            fontWeight = txtbold
                        )
                    } else if (bmi.toFloat() in 18.5..24.9) {
                        Text(
                            text = "(NORMAL)",
                            color = Color(0xFF028B7F),
                            fontSize = fnt21,
                            fontWeight = txtbold
                        )
                    } else if (bmi.toFloat() in 25.0..29.9) {
                        Text(
                            text = "(KİLOLU)",
                            color = Color(0xFFB8A81E),
                            fontSize = fnt21,
                            fontWeight = txtbold
                        )
                    } else if (bmi.toFloat() in 30.0..34.9) {
                        Text(
                            text = "(OBEz)",
                            color = Color(0xFFFF9800),
                            fontSize = fnt21,
                            fontWeight = txtbold
                        )
                    } else {
                        Text(
                            text = "(AŞIRI OBEZ)",
                            color = Color.Red,
                            fontSize = fnt21,
                            fontWeight = txtbold
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid
            )
            {
                Icon(
                    imageVector = Icons.Outlined.MonitorHeart,
                    contentDescription = "Kan Basıncı",
                    tint = green1,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = usermed.bloodpres.toString(), fontSize = fnt23,
                    fontWeight = txtbold
                )
            }
            Row(
                horizontalArrangement = horzstart,
                modifier = fillmaxwid.padding(bottom = 35.dp)
            )
            {
                Icon(
                    imageVector = Icons.Outlined.MedicalServices,
                    contentDescription = "Kategori",
                    tint = green1,
                    modifier = iconsize
                )
                Spacer(modifier = sp5)
                Text(
                    text = usermed.category.toString().uppercase(), fontSize = fnt23,
                    fontWeight = txtbold
                )
            }
        } else {
            Text(
                text = "Kalorilerinizi yönetiniz. ", fontSize = fnt20, color = subtxtcol,
                fontWeight = txtbold
            )
        }
    }
}