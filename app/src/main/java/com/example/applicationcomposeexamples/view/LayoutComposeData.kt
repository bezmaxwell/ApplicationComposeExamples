package com.example.applicationcomposeexamples.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class LayoutComposeData {
}

@Composable
fun DataDisplay(data: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = data, style = MaterialTheme.typography.bodyLarge, color = Color.White)
            Text(text = value, style = MaterialTheme.typography.bodyLarge, color = Color.White)
        }
    }
}

@Composable
fun DataList() {
    val dataList = listOf(
        Pair("Data 1:", "18/08/2023"),
        Pair("Data 2:", "25/08/2023"),
        Pair("Data 3:", "02/09/2023"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        dataList.forEach { (data, value) ->
            DataDisplay(data = data, value = value)
        }
    }
}

@Preview
@Composable
fun PreviewDataList() {
    DataList()
}