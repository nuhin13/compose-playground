package com.nuhin13.composeplayground.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.nuhin13.composeplayground.ui.theme.Typography
import com.nuhin13.composeplayground.viewmodels.ScreensViewModel

@Composable
fun ScreenOne(
    viewModel: ScreensViewModel = ScreensViewModel()
) {

    var name by remember { mutableStateOf("Initial Value") }

    var name2 by rememberSaveable {
        (mutableStateOf("Initial Value 1"))
    }

    val name3: String by
    viewModel.name.observeAsState("Initial")

    Column {

        BoxComposable()
        CustomSurface(weight = 1f, color = Color.Red)
        CustomSurface(weight = 1f, color = Color.Black)

        TextInputField(name = name3, onValueChanged = {
            viewModel.setName(it)
        })

        ScreenItem1(itemString = name3)
        ScreenItem1(itemString = "Bugi 2")
        ScreenItem1(itemString = "Bugi 3")
        StudentList(students = students)
    }
}

@Composable
fun BoxComposable() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .padding(10.dp)
                .fillMaxWidth()
        ) {

        }
        Text(text = "Hello World", style = Typography.bodyLarge)
    }
}

@Composable
fun ColumnScope.CustomSurface(weight: Float, color: Color) {
    Surface(
        modifier = Modifier.weight(weight), color = color
    ) {
        Text(text = "Hello World", style = Typography.bodyLarge)
    }
}

@Composable
fun StudentList(students: List<Student>) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        students.forEach { student ->
            StudentRow(student)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputField(name: String, onValueChanged: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = onValueChanged,
        modifier = Modifier.padding(10.dp)
    )
}


@Composable
fun StudentRow(student: Student) {
    Card(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(
                student.name,
                fontSize = 25.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(10.dp)
            )
            Text(text = student.credits.toString(), color = Color.Gray, modifier = Modifier.padding(10.dp))
        }
    }
}

@Composable
fun ScreenItem1(itemString: String) {
    Column {
        Text(
            text = itemString,
            style = Typography.headlineLarge,
            modifier = Modifier.padding(2.dp)
        )
        Button(onClick = {
            //students[0] = Student("Bugi 4", 100)

        }) {
            Text(text = "Update Students Data")
        }
    }

}


@Composable
@Preview
fun ScreenOnePreview() {
    ScreenOne()
}

data class Student(val name: String, val credits: Int)

val students = listOf(
    Student("Student 1", 125),
    Student("Student 2", 75),
    Student("Student 3", 15),
    Student("Student 4", 87),
    Student("Student 5", 22),
    Student("Student 6", 96),
)
