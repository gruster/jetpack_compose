package android.example.testovoe_jetpack.blocks

import android.example.testovoe_jetpack.ui.theme.Green100
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreenButton(isFocusTextField: MutableState<Boolean>) {

    val textButton = remember { mutableStateOf("Сохранить") }
    val localFocusManager = LocalFocusManager.current
    if (isFocusTextField.value) {
        textButton.value = "Готово"
    }
    Button(
        onClick = {
            localFocusManager.clearFocus()
            isFocusTextField.value = false
            if(!isFocusTextField.value){
                textButton.value = "Сохранить"
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Green100,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
        )
    ) {
        Text(
            text = textButton.value,
            style = MaterialTheme.typography.h2,
            letterSpacing = (-0.408).sp,
        )
    }
}