package com.example.isalmicomposeui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.isalmicomposeui.R

@Composable
fun SplashScreen(modifier: Modifier, onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000) // 2 seconds
        onTimeout()
    }

    SplashContent()
}

@Composable
fun SplashContent(modifier: Modifier = Modifier) {

    Box ( modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = "App Background",
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "App Icon",
            Modifier.fillMaxSize(0.7f)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun SplashContentPreview() {
    SplashContent()
}