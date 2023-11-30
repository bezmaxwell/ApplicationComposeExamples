package com.example.applicationcomposeexamples.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.applicationcomposeexamples.R
import com.example.applicationcomposeexamples.ui.theme.ApplicationComposeExamplesTheme
import com.example.applicationcomposeexamples.viewModel.RentalViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentalScreen(viewModel: RentalViewModel = RentalViewModel()) {
    val rentalInfo by remember { viewModel.rentalInfo }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = rentalInfo?.imageResId ?: R.drawable.ic_launcher_foreground
            ),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )

        Text(text = rentalInfo?.name ?: "", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Data do Aluguel: ${rentalInfo?.rentalDate ?: ""}")
        Text(text = "Data de Devolução: ${rentalInfo?.returnDate ?: ""}")
        Text(text = "Preço do Aluguel: ${rentalInfo?.rentalPrice ?: 0.0}")

        Spacer(modifier = Modifier.height(16.dp))

        val rentalDaysState = remember { mutableStateOf("") }
        OutlinedTextField(
            value = rentalDaysState.value,
            onValueChange = { rentalDaysState.value = it },
            label = { Text("Quantidade de Dias") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val rentalDays = rentalDaysState.value.toIntOrNull() ?: 0
            if (rentalDays > 0) {
                viewModel.calculateReturnDate(rentalDays)
            }
        }) {
            Text(text = "Calcular Data de Devolução")
        }
    }
}

@Composable
fun RentalApp() {
    ApplicationComposeExamplesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RentalScreen()
        }
    }
}

@Composable
@Preview
fun PreviewCompose() {
    ApplicationComposeExamplesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RentalScreen()
        }
    }
}
