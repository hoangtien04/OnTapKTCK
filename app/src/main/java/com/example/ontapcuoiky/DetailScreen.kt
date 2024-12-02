package com.example.ontapcuoiky

import android.annotation.SuppressLint
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showBackground = true)
@OptIn(ExperimentalSubclassOptIn::class, ExperimentalMaterial3Api::class)
fun DetailScreen(navController: NavController,id:String,viewModel: ThucDonViewModel){
    var thucDon:ThucDon by remember {mutableStateOf(ThucDon(0,"",0,"",""))}
    viewModel.getThucDon(id)
    thucDon = viewModel.thucdon
    var soLuong by remember{mutableStateOf(TextFieldValue("1"))}
    var soLuongTangGiam by remember{ mutableStateOf(1) }
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
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(35.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()){
                        Text(
                            text = thucDon.name.uppercase(),
                            fontSize = 24.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(8.dp)
                        )
                        Text(
                            text = thucDon.price.toString(),
                            fontSize = 18.sp,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(text = thucDon.description,color = Color.White,textAlign = TextAlign.Justify)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ){
                        Button(onClick = {
                            if(soLuongTangGiam > 1){
                                soLuongTangGiam --
                                soLuong = TextFieldValue(soLuongTangGiam.toString())
                            }
                        },
                            modifier = Modifier.width(70.dp).height(52.dp),
                            shape = RoundedCornerShape(topStartPercent = 12, bottomStartPercent = 12, topEndPercent = 0, bottomEndPercent = 0) ,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        )
                        {
                            Text(text="-",color = Color.Black, fontSize = 20.sp)
                        }
                        TextField(
                            value = soLuong,
                            onValueChange = {
                                val input = it.text.toIntOrNull()
                                if (input!=null && input in 1..99) {
                                    soLuongTangGiam  = input
                                    soLuong = it
                                } else if(it.text.isEmpty()) {
                                    soLuong = it
                                }
                            }
                            ,
                            shape = RoundedCornerShape(0),
                            modifier = Modifier.height(52.dp).width(65.dp).padding( end = 0.dp).fillMaxSize(),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                            ),
                            textStyle = TextStyle(fontSize = 20.sp,textAlign = TextAlign.Center),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Button(onClick = {
                            if(soLuongTangGiam <100){
                                soLuongTangGiam ++
                                soLuong = TextFieldValue(soLuongTangGiam.toString())
                            }
                        },
                            shape = RoundedCornerShape(topStartPercent = 0, bottomStartPercent = 0, topEndPercent = 12, bottomEndPercent = 12),
                            modifier = Modifier.height(52.dp).width(70.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            )
                        ) {
                            Text(text="+",color = Color.Black, fontSize = 20.sp)
                        }
                    }
                    Column(
                        modifier=Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().height(70.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "TỔNG TIỀN", color = Color.LightGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Text(text = "${thucDon.price * soLuongTangGiam}" + " VND", color = Color.Red, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            }
                            Button(
                                onClick = {},
                                modifier = Modifier.fillMaxSize().align(alignment = Alignment.CenterVertically).weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(0)
                            ) {
                                Text(text = "ĐẶT HÀNG", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold,)
                            }

                        }

                    }
                }

            }
        }
    }
}