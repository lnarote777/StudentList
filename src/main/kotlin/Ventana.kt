import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.TooltipArea
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Window
import kotlinx.coroutines.delay
import java.io.File


@Composable
fun Ventana(onClose: () -> Unit){
    val path = "src/main/kotlin/estudiantes.txt"
    val archivo = File(path)
   // var eliminar by remember { mutableStateOf(false) }

    Window(
        onCloseRequest = onClose,
        title = "My Student"
    ){
        Content(archivo)
    }
}

@Composable
@Preview
fun Content(archivo: File) {
    var name by remember { mutableStateOf("") }
    val students = remember { mutableStateListOf<String>() }
    var eliminar by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }


    archivo.forEachLine { students.add(it) } //Añade los nombres del archivo a la lista para empezar visualizandolos

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

                AgregarEstudiante(name, { name = it }, students, { students.add(name); name = ""  })

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Students: ${students.size}",
                        fontSize = 15.sp
                    )

                    Box(
                        modifier = Modifier
                            .border(2.dp, color = Color.Black)
                            .size(150.dp, 250.dp)
                    ) {

                        LazyColumn{

                            items(students) { student ->
                                Text(
                                    text = student,
                                    modifier = Modifier.padding(start = 5.dp)
                                )

                                IconToggleButton(
                                    checked = eliminar,
                                    onCheckedChange = { eliminar = it; students.remove(student) }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Eliminar"
                                    )
                                }
                            }

                        }
                    }

                }
            }

            GuardarCambios { archivo.writeText(students.joinToString ("\n" )) }
        }
    }
}


@Composable
fun AgregarEstudiante(name: String, onValueChange: (String)->Unit, students: MutableList<String>, onClick: () -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = onValueChange,
            label = { Text("Nuevo estudiante") }
        )
        Button(
            onClick = onClick
        ){
            Text("Añadir estudiante")
        }
    }
}

@Composable
fun GuardarCambios(click: () -> Unit){
    Button(
        onClick = click
    ) {
        Text("Guardar cambios")
    }
}

@Composable
fun ColumnaEstudiantes(delete: Boolean, onDelete: () -> Unit){

}

@Composable
fun Toast(message: String, onDismiss: () -> Unit) {
    Dialog(
        icon = painterResource("info_icon.png"),
        title = "Atención",
        resizable = false,
        onCloseRequest = onDismiss
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Text(message)
        }
    }
    // Cierra el Toast después de 2 segundos
    LaunchedEffect(Unit) {
        delay(2000)
        onDismiss()
    }
}