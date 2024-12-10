package org.iesharia.proyectroommap.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import kotlinx.coroutines.flow.first
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.GeoPoint
import org.osmdroid.util.MapTileIndex



// Google Satellite Tile Source
val GoogleSat: OnlineTileSourceBase = object : XYTileSource(
    "Google-Sat",
    0, 19, 256, ".png", arrayOf(
        "https://mt0.google.com",
        "https://mt1.google.com",
        "https://mt2.google.com",
        "https://mt3.google.com"
    )
) {
    override fun getTileURLString(aTile: Long): String {
        return baseUrl + "/vt/lyrs=s&x=" + MapTileIndex.getX(aTile) + "&y=" + MapTileIndex.getY(
            aTile
        ) + "&z=" + MapTileIndex.getZoom(aTile)
    }
}

@Composable
fun MyMapView(modifier: Modifier = Modifier, viewModel: MarcadoresViewModel) {
    // Cargar los marcadores desde el ViewModel
    val marcadoresWithTipo by viewModel.marcadoresLiveData.observeAsState(emptyList())

    // Configuración de la cámara inicial
    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(28.957375205489004, -13.554245657440829) // Coordenadas iniciales
        zoom = 17.0 // Nivel de zoom inicial
    }

    // Propiedades del mapa con valores predeterminados
    var mapProperties by remember {
        mutableStateOf(DefaultMapProperties)
    }

    // Configurar propiedades del mapa en un SideEffect
    SideEffect {
        mapProperties = mapProperties
            .copy(tileSources = GoogleSat)
            .copy(isEnableRotationGesture = true)
            .copy(zoomButtonVisibility = ZoomButtonVisibility.NEVER)
    }

    // Crear el mapa con las propiedades configuradas
    OpenStreetMap(
        modifier = modifier.fillMaxSize(),
        cameraState = cameraState,
        properties = mapProperties
    ) {
        // Dibujar los marcadores en el mapa
        marcadoresWithTipo.forEach { marcadorWithTipo ->
            val marcador = marcadorWithTipo.marker
            val tipo = marcadorWithTipo.markerType?.name ?: "Sin tipo"  // Asegúrate de acceder correctamente al nombre del tipo

            val markerState = rememberMarkerState(
                geoPoint = GeoPoint(marcador.latitude, marcador.longitude)
            )

            // Crear el marcador
            Marker(
                state = markerState,
                title = marcador.title,
                snippet = tipo
            ) {
                // Crear la ventana de información del marcador
                Column(
                    modifier = Modifier
                        .size(150.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(7.dp))
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = it.title, fontWeight = FontWeight.Bold) // Título del marcador
                    Text(text = it.snippet, fontSize = 12.sp) // Tipo del marcador
                }
            }
        }
    }
}
