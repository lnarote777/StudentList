import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window


@Composable
fun Ventana(onClose: () -> Unit){

   // val path = "src/main/kotlin/estudiantes.txt"
   // val archivo = File(path)
   // var eliminar by remember { mutableStateOf(false) }



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
    var eliminar by remember { mutableStateOf(false) }
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
                        onClick = { students.add(name); name = "" }
                    ){
                        Text("Añadir estudiante")
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Students: ${students.size}")
                    Box(
                        modifier = Modifier
                            .border(2.dp, color = Color.Black)
                            .size(150.dp, 250.dp)
                    ){
                        LazyColumn{
                            items(students){student ->
                                Text(student)
                                IconToggleButton(
                                    checked = eliminar,
                                    onCheckedChange = {eliminar = it}
                                ){
                                    Icon(
                                        imageVector =Icons.Default.Delete ,
                                        contentDescription = "Eliminar"
                                    )
                                }
                            }


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
