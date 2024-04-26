import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import java.io.File
import javax.swing.text.AbstractDocument.Content

@Composable
fun Ventana(onClose: () -> Unit){
    Window(
        onCloseRequest = onClose,
        title = "My Student"
    ){
        Content()
    }
}

@Composable
@Preview
fun Content(){
    var name by remember { mutableStateOf("") }
    val students = remember { mutableStateListOf<String>() }
    val path = "src/main/kotlin/estudiantes.txt"
    val archivo = File(path)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterVertically)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nuevo estudiante") }
                    )
                    Button(
                        onClick = { students.add(name) }
                    ){
                        Text("Añadir estudiante")
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Students: ${students.size}")
                    Box{
                        LazyColumn(
                            modifier = Modifier
                                .border(2.dp, color = Color.Black)
                                .size(150.dp, 250.dp)
                        ) {

                        }
                    }


                }

            }
            Button(
                onClick = { TODO() }
            ){
                Text("Guardar cambios")
            }
        }
    }
}

