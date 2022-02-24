package android.example.testovoe_jetpack.blocks

import android.example.testovoe_jetpack.ui.theme.Green100
import android.example.testovoe_jetpack.ui.theme.Grey200
import android.example.testovoe_jetpack.ui.theme.Grey500
import android.example.testovoe_jetpack.ui.theme.SFProText
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileTextField(
    content: MutableState<String?>,
    label: String,
    prompt: String,
    isFocusTextField: MutableState<Boolean>
) {
    val focusBackground = remember { mutableStateOf(Grey500) }
    val focusBorder = remember { mutableStateOf(Color.White) }
    val focusLabelColor = remember { mutableStateOf(Grey200) }
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Green100,
        backgroundColor = Green100.copy(alpha = 0.4f)
    )

    val focusLabelTextStyle = TextStyle(
        fontSize = 11.sp,
        fontFamily = SFProText,
        lineHeight = 13.sp
    )
    val emptyLabelTextStyle = MaterialTheme.typography.h2
    val checkLabelTextStyle = remember { mutableStateOf(focusLabelTextStyle) }
    Column(modifier = Modifier.padding(start = 20.dp, end = 15.dp)) {
        Box(
            modifier = Modifier
                .border(width = 1.dp, focusBorder.value, shape = MaterialTheme.shapes.small)
        ) {
            CompositionLocalProvider(
                LocalTextSelectionColors provides customTextSelectionColors
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .onFocusChanged {
                            if (it.isFocused) {
                                focusBackground.value = Color.Transparent
                                focusBorder.value = Green100
                                focusLabelColor.value = Green100
                                checkLabelTextStyle.value = focusLabelTextStyle
                                isFocusTextField.value = true
                            } else {
                                focusBackground.value = Grey500
                                focusBorder.value = Color.White
                                focusLabelColor.value = Grey200
                                isFocusTextField.value = false
                                if (content.value?.isNotEmpty() == true) {
                                    checkLabelTextStyle.value = focusLabelTextStyle
                                } else {
                                    checkLabelTextStyle.value = emptyLabelTextStyle
                                }
                            }
                        },
                    value = content.value ?: "",
                    onValueChange = { content.value = it },
                    shape = MaterialTheme.shapes.small,
                    label = {
                        Text(
                            text = label,
                            style = checkLabelTextStyle.value,
                            fontWeight = FontWeight.Normal,
                        )
                    },
                    textStyle = TextStyle(
                        fontFamily = SFProText,
                        fontWeight = FontWeight.W500,
                        fontSize = 17.sp,
                        lineHeight = 22.sp,
                        letterSpacing = (-0.408).sp
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = focusBackground.value,
                        focusedLabelColor = Green100,
                        unfocusedLabelColor = Grey200,
                        cursorColor = Green100,
                    ),
                )
            }
        }
        if (prompt.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = prompt,
                fontSize = 11.sp,
                fontFamily = SFProText,
                lineHeight = 16.sp,
                color = Grey200
            )
        }
    }
}