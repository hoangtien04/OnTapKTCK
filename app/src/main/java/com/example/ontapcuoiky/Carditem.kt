@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.ontapcuoiky

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Preview
@Composable
fun ThucDonCard(thucDon: ThucDon, onClick:() -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp,6.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = thucDon.picture,
                contentDescription = "",
                modifier = Modifier.size(60.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp,8.dp)
            ) {
                Text(text = thucDon.name.uppercase(), fontSize = 17.sp)
                Text(text = thucDon.price.toString(), color = Color.LightGray, fontSize = 14.sp)
            }
        }

    }
}