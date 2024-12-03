package com.example.ontapcuoiky

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ontapcuoiky.ui.theme.OnTapCuoiKyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ThucDonViewModel(this)
        setContent {
            OnTapCuoiKyTheme {
                var navController = rememberNavController()
                NavGraph(navController = navController,viewModel)

            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun homeScreen(viewModel: ThucDonViewModel,navController: NavController) {
    var thucDonlist by remember {
        mutableStateOf(viewModel.getThucDon())
    }
    val activity = (LocalContext.current as? Activity)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Thực đơn")
                },
                actions = {
                    IconButton(onClick = { activity?.finishAndRemoveTask() }) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        }

    ) {
        LazyColumn(
            modifier = Modifier.padding(it).background(Color.LightGray),
        ) {
            items(thucDonlist) {
                ThucDonCard(thucDon = it, onClick = {
                    navController.navigate(Screen.Detail.route + "?id=${it.id}")
                }
                )
            }
        }
    }
}