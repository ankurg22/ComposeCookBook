package com.guru.composecookbook.ui.templates

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.unit.dp

@Composable
fun TemplateScreen() {
    val context = AmbientContext.current
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(templates) { template ->
            Button(
                onClick = {
                    context.startActivity(TemplatesActivity.newIntent(context, template, false))
                },
                modifier = Modifier.fillMaxWidth().padding(12.dp)
            ) {
                Text(text = template, modifier = Modifier.padding(8.dp))
            }
        }
    }

}


val templates = listOf(
    "Login",
    "Profiles",
    "Settings",
    "On-boarding",
    "Empty Screens",
    "Loaders",
    "Canvas Drawing",
    "Animations",
)