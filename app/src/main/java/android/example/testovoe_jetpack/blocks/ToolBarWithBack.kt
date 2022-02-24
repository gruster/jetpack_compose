package android.example.testovoe_jetpack.blocks

import android.example.testovoe_jetpack.R
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ToolBarWithBack(title: String) {
    val dispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
    Spacer(modifier = Modifier.height(29.dp))
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = stringResource(R.string.button_back),
            modifier = Modifier
                .padding(start = 14.dp)
                .clickable {
                dispatcher.onBackPressed()
            }
        )
        Text(
            text = title,
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
                .padding(end = 34.dp),
        )

    }
}