package com.example.coroutinesapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coroutinesapp.R
import com.example.coroutinesapp.viewmodel.MainViewModel

@Composable
fun CoroutinesApp(viewModel: MainViewModel  ,modifier: Modifier = Modifier){
    var changeColor by remember {
        mutableStateOf(false)
    }



    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
                changeColor = !changeColor

        },
            colors = ButtonDefaults.buttonColors(
                if( changeColor ) Color.Green else Color.Blue
            )

            ) {
            Text(text = stringResource(R.string.cambio_de_color))
        }
        Spacer(modifier = modifier.height(30.dp)  )
        Text(text = "${viewModel.countTime} [s]")  // Mostrar el primer contador
        Text(text = "${viewModel.countTime2} [s]")  // Mostrar el segundo contador
        Text(text = viewModel.resultState  )
        Spacer(modifier = modifier.height(30.dp))
        Button(onClick = {
            viewModel.fetchData()
        }) {
            Text(text =
            stringResource(id = R.string.realizar_consulta) )
        }
        Button(onClick = {
            viewModel.cancelarContadores()  // Cancela los contadores
        }) {
            Text(text = "Cancelar contadores")
        }
    }


}