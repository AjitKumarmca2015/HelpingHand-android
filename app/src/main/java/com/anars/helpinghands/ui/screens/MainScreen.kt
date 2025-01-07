package com.anars.helpinghands.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.anars.helpinghands.ui.theme.HelpingHandsTheme

@Composable
fun MainScreen() {

    HelpingHandsTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Welcome to the Main Screen!",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}