package com.adilegngr.mobile_health_app.uiactivity

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adilegngr.mobile_health_app.R
import com.adilegngr.mobile_health_app.TopBar
import com.adilegngr.mobile_health_app.model.UserDatabaseHelper
import com.adilegngr.mobile_health_app.ui.theme.Activityscreen
import com.adilegngr.mobile_health_app.ui.theme.boxes
import com.adilegngr.mobile_health_app.ui.theme.fillmaxwid
import com.adilegngr.mobile_health_app.ui.theme.horzcenter
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(context: Context, navController: NavController,databaseHelper: UserDatabaseHelper)
{
    Scaffold(
        topBar = { TopBar("GİRİŞ") },
        content = {pad -> LoginFill(pad,context,navController,databaseHelper) },
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginFill(h: PaddingValues,context: Context, navController: NavController,databaseHelper: UserDatabaseHelper) {
    Column(
        modifier = Activityscreen,
        horizontalAlignment = horzcenter,
        verticalArrangement = vertspace
    )
    {
        val mail = remember { mutableStateOf(TextFieldValue("")) }
        val pw = remember { mutableStateOf(TextFieldValue("")) }
        val pwvisib = remember { mutableStateOf(false) }
        val boxcolor = TextFieldDefaults.textFieldColors(containerColor = purewhite)
        val butcolor = ButtonDefaults.buttonColors(green1)
        Image(
            painter = painterResource(id = R.drawable.health_app),
            contentDescription = "Logo",
            modifier = Modifier.size(160.dp).padding(top=25.dp)
        )
        TextField(
            value = mail.value,
            onValueChange = {
                mail.value = it
            },
            colors = boxcolor,
            label = { Text("E-posta Adresini Girin") },
            modifier = boxes,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) }
        )
        TextField(
            value = pw.value,
            onValueChange = {
                pw.value = it
            },
            colors = boxcolor,
            label = { Text("Şifreyi Girin") },
            modifier = boxes,
            visualTransformation = if (pwvisib.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (pwvisib.value) {
                    IconButton(onClick = { pwvisib.value = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "show_pw"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { pwvisib.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_pw"
                        )
                    }
                }
            }
        )
        Button(
            onClick = {
                if (mail.value.text.isNotEmpty() && pw.value.text.isNotEmpty()) {
                    val user = databaseHelper.getUserByUseremail(mail.value.text)
                    if (user != null && user.password == pw.value.text) {
                        navController.navigate("dashboard/${mail.value.text}");
                        Toast.makeText(
                            context, "Giriş Başarılı ",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context, "Geçersiz Kimlik Bilgileri ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context, "Lütfen Tüm Bilgileri Doldurun",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = fillmaxwid,
            colors = butcolor,
            shape = rcshape
        )
        {
            Text(
                text = "GİRİŞ YAP / KAYDOL", color = reglogbuttxtcol, fontSize = reglogbut
            )
        }
        Text(
            text = "Şifrenizi mi Unuttunuz ?",
            color = subtxtcol,
            fontSize = subtxtsize,
            fontWeight = txtbold
        )
        Button(
            onClick = {
                navController.navigate("forgotpw")
            },
            modifier = fillmaxwid,
            colors = butcolor,
            shape = rcshape
        )
        {
            Text(
                text = "ŞİFREYİ SIFIRLA", color = reglogbuttxtcol, fontSize = reglogbut,
                textAlign = txtcenter
            )
        }
        Text(
            text = "Yeni Kullanıcı? Kaydolun",
            color = subtxtcol,
            fontSize = subtxtsize,
            fontWeight = txtbold
        )
        Button(
            onClick = {
                navController.navigate("reg")
            },
            modifier = fillmaxwid,
            colors = butcolor,
            shape = rcshape
        )
        {
            Text(
                text = "Üye Ol / Kaydol ", color = reglogbuttxtcol, fontSize = reglogbut,
                textAlign = txtcenter
            )
        }
    }
}