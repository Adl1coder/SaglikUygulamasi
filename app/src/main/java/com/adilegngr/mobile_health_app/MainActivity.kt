package com.adilegngr.mobile_health_app

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.RiceBowl
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.adilegngr.mobile_health_app.model.MedicalDatabaseHelper
import com.adilegngr.mobile_health_app.model.UserDatabaseHelper
import com.adilegngr.mobile_health_app.ui.theme.mobile_health_appTheme
import com.adilegngr.mobile_health_app.ui.theme.fnt18
import com.adilegngr.mobile_health_app.ui.theme.fnt24
import com.adilegngr.mobile_health_app.ui.theme.horzspacear
import com.adilegngr.mobile_health_app.ui.theme.icon
import com.adilegngr.mobile_health_app.ui.theme.purewhite
import com.adilegngr.mobile_health_app.ui.theme.green1
import com.adilegngr.mobile_health_app.ui.theme.mobile_health_appTheme
import com.adilegngr.mobile_health_app.ui.theme.rcshape
import com.adilegngr.mobile_health_app.ui.theme.txtbold
import com.adilegngr.mobile_health_app.uiactivity.BloodDonationPage
import com.adilegngr.mobile_health_app.uiactivity.CaloriePage
import com.adilegngr.mobile_health_app.uiactivity.DashboardPage

import com.adilegngr.mobile_health_app.uiactivity.ForgotPasswordPage
import com.adilegngr.mobile_health_app.uiactivity.HealthArticlePage
import com.adilegngr.mobile_health_app.uiactivity.LoginPage
import com.adilegngr.mobile_health_app.uiactivity.MapPage
import com.adilegngr.mobile_health_app.uiactivity.CalorieTrackerPage
import com.adilegngr.mobile_health_app.uiactivity.RegistrationPage
import com.adilegngr.mobile_health_app.uiactivity.SleepTrackerPage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {
    private lateinit var databaseHelper1: UserDatabaseHelper
    private lateinit var databaseHelper2: MedicalDatabaseHelper

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper1 = UserDatabaseHelper(this)
        databaseHelper2 = MedicalDatabaseHelper(this)
        setContent {
            mobile_health_appTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val firebasedb = FirebaseDatabase.getInstance();
                    val reference = firebasedb.getReference("User");
                    val get_permission = rememberLauncherForActivityResult(
                        ActivityResultContracts.RequestPermission()
                    ) { isGranted ->
                        if (isGranted) {
                            // Permission accepted Do Something
                        } else {
                            // Permission not accepted show message
                        }
                    }
                    SideEffect {
                        get_permission.launch(POST_NOTIFICATIONS)
                    }
                    App(applicationContext,reference,databaseHelper1,databaseHelper2)
                }
            }
        }
    }
}
@Composable
fun App(context:Context,databaseReference: DatabaseReference,databaseHelper1: UserDatabaseHelper,databaseHelper2: MedicalDatabaseHelper) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splashscreen"
    ) {
        composable("splashscreen") {
            SplashScreen(navController)
        }
        composable("reg") {
            RegistrationPage(context, navController, databaseReference, databaseHelper1)
        }
        composable("medusreg") {
            CalorieTrackerPage(context, navController, databaseHelper2)
        }
        composable("login") {
            LoginPage(context, navController, databaseHelper1)
        }
        composable("forgotpw") {
            ForgotPasswordPage(context, navController, databaseReference, databaseHelper1)
        }
        composable("dashboard/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")
            DashboardPage(navController, email.toString(), databaseHelper1, databaseHelper2)
        }
        composable("caloriemgt") {
            CaloriePage(navController)
        }
        composable("article") {
            HealthArticlePage(navController)
        }
        composable("maps"){
            MapPage(navController)
        }
        composable("sleep"){
            SleepTrackerPage(navController)
        }
        composable("emergency"){
            BloodDonationPage(navController)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(abc:String) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = green1),
        title = {
            Text(
                abc, color = purewhite,
                fontSize = fnt24, fontWeight = txtbold
            )
        })
}
@Composable
fun TopApplicationBar(abc:String,navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(green1)
    ) {
        val openDialog = remember { mutableStateOf(false) }
        val context = LocalContext.current
        Text(
            abc, color = purewhite,
            fontSize = fnt24, fontWeight = txtbold,
            modifier = Modifier.padding(start = 15.dp)
        )
        IconButton(
            onClick = { openDialog.value = true; }) {
            Icon(
                imageVector = Icons.Outlined.Logout,
                contentDescription = "Logout",
                tint = purewhite,
                modifier = icon,
            )
        }
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                title = { Text(text = "Logout") },
                text = { Text("Are you sure you want to Logout ?") },
                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false;
                            Toast.makeText(
                                context, "Logout Successful ",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate("login")
                        }, colors = ButtonDefaults.buttonColors(green1),
                        shape = rcshape
                    )
                    {
                        Text(
                            "Yes", color = purewhite,
                            fontSize = fnt18, fontWeight = txtbold,
                        )
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        }, colors = ButtonDefaults.buttonColors(green1),
                        shape = rcshape
                    )
                    {
                        Text(
                            "No", color = purewhite,
                            fontSize = fnt18, fontWeight = txtbold,
                        )
                    }
                },
            )
        }
    }
}
@Composable
fun BottomBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(green1),
        horizontalArrangement = horzspacear
    )
    {
        val butcolor = ButtonDefaults.buttonColors(green1)
        val size24 = Modifier.size(24.dp)
        Button(
            onClick = { navController.navigate("caloriemgt") },
            colors = butcolor,
        ) {
            Icon(
                imageVector = Icons.Outlined.RiceBowl,
                contentDescription = "Calorie",
                modifier = size24
            )
        }
        Button(
            onClick = { navController.navigate("article") },
            colors = butcolor,
        ) {
            Icon(
                imageVector = Icons.Filled.Article,
                contentDescription = "Article",
                modifier = size24
            )
        }
        Button(
            onClick = { navController.navigate("sleep") },
            colors = butcolor,
        ) {
            Icon(
                imageVector = Icons.Filled.Bedtime,
                contentDescription = "Doctor",
                modifier = size24
            )
        }
        Button(
            onClick = { navController.navigate("maps") },
            colors = butcolor,
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "Map",
                modifier = size24
            )
        }
        Button(
            onClick = { navController.navigate("emergency") },
            colors = butcolor,
        ) {
            Icon(
                imageVector = Icons.Filled.Contacts,
                contentDescription = "Emergency",
                modifier = size24
            )
        }
    }
}