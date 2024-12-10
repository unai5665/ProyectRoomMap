package org.iesharia.proyectroommap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.iesharia.proyectroommap.data.AppDatabase
import org.iesharia.proyectroommap.view.MarcadoresViewModel
import org.iesharia.proyectroommap.view.MyMapView


class MainActivity : ComponentActivity() {
    private val viewModel: MarcadoresViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cargar marcadores al iniciar la actividad
        viewModel.cargarMarcadores()

        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Mostrar el mapa y los marcadores
                    MyMapView(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
