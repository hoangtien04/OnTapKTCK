package com.example.ontapcuoiky

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader

class ThucDonViewModel(private val context: Context) : ViewModel(){

    private var thucDonList :List<ThucDon> = emptyList()
    var thucdon:ThucDon by mutableStateOf(ThucDon(0,"",0,"",""))
    fun getThucDon():List<ThucDon>{
        if(thucDonList.isEmpty()){
            val json = readJsonFromFile(context, "ontap.json")
            val type = object:TypeToken<List<ThucDon>>(){}.type
            thucDonList = Gson().fromJson(json,type)
        }
        return thucDonList
    }

    fun getThucDon(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            thucdon = getThucDon().find { it.id == id.toInt() }!!
        }
    }

    private fun readJsonFromFile(context:Context,filename:String): String {
        val inputStream = context.assets.open(filename)
        val bufferedReader = BufferedReader(inputStream.reader())
        return bufferedReader.use { it.readText() }
    }
}