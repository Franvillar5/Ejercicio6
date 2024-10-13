package com.example.coroutinesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    var resultState by mutableStateOf("")
        private set

    var countTime by mutableStateOf(0)
        private set

    var countTime2 by mutableStateOf(0)
        private set

    private var contadoresCancelados = false

    private val job : Job? = null
    private val job2 : Job? = null

    private var oneCount by mutableStateOf(false)

    fun fetchData(){
       if(contadoresCancelados) return
      val job = viewModelScope.launch {
            for (i in 1..5){
                delay(1000)
                if(contadoresCancelados) break
                countTime = i
            }
          oneCount = true
          iniciarSegundoContador()
        }



        if( oneCount){
            job.cancel()
        }

    }
    private fun iniciarSegundoContador(){
        val job2 = viewModelScope.launch {
            countTime2 = 0
            for (i in 1..5){
                delay(1000)
                if(contadoresCancelados) break
                countTime2++
            }
            viewModelScope.launch {
                delay(1000)
                resultState = "Respuesta desde el servidor Web"
            }
        }
    }

    fun cancelarContadores(){
        contadoresCancelados = true
        job?.cancel()
        job2?.cancel()
        countTime = 0 // Resetea el primer contador
        countTime2 = 0 // Resetea el segundo contador
        resultState = "Contadores cancelados"

    }






    /*
      Thread trabaja en el mismo contexto de la IU

    fun bloqueoApp(){
        Thread.sleep(5000)
        resultState = "Respuesta del Servidor Web"

    }
  */

    /*
    fun fetchData(){
        viewModelScope.launch {
            delay(5000)
            resultState = "Respuesta desde el Servidor Web"
        }
    }*/








}