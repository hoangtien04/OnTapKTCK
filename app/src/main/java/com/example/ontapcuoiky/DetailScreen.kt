package com.example.ontapcuoiky

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalSubclassOptIn::class, ExperimentalMaterial3Api::class)
fun DetailScreen(navController: NavController,id:String,viewModel: ThucDonViewModel){
    var thucDon:ThucDon by remember {mutableStateOf(ThucDon(0,"",0,"",""))}
    viewModel.getThucDon(id)
    thucDon = viewModel.thucdon
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Đặt Món")},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                )
            )
        }
    ){
        Box (
            modifier = Modifier.fillMaxSize().padding(it),
        ){

            Box(
                modifier = Modifier.background(Color.White).height(280.dp).fillMaxWidth()
            ){
                AsyncImage(
                model = thucDon.picture,
                contentDescription = "",
                modifier = Modifier.size(250.dp).align(alignment = Alignment.Center)
           )
            }
            Box(
                modifier = Modifier.padding(top = 280.dp).fillMaxSize().clip(
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
                ).background(Color.Blue),
            ) {
                Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(50.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()){
                        Text(
                            text = thucDon.name.uppercase(),
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = thucDon.price.toString(),
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth().align(alignment = Alignment.CenterVertically),
                            textAlign = TextAlign.Right,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(text = thucDon.description,color = Color.White)
                }

            }
        }
    }
}