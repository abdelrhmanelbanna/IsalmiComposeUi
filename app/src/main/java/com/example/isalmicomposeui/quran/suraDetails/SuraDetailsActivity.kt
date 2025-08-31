package com.example.isalmicomposeui.quran.suraDetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.isalmicomposeui.ui.theme.IsalmiComposeUiTheme

class SuraDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       val suraName = intent.getStringExtra("sura_name")

        val suraPos = intent.getIntExtra("sura_position",-1)

        val fileName = "${suraPos + 1}.txt"
       val suraContent = this.assets.open(fileName).bufferedReader().use { it.readText() }

        val suraLines: List<String> = suraContent
            .lines()
            .filter { it.isNotBlank() }

        setContent {
            IsalmiComposeUiTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SuraDetailsScreen(
                        suraName = suraName?:"",
                        suraLines = suraLines,
                        onBack = { finish() },
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

}

