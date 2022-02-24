package android.example.testovoe_jetpack.screen.profile

import android.example.testovoe_jetpack.R
import android.example.testovoe_jetpack.blocks.ToolBarWithBack
import android.example.testovoe_jetpack.ui.theme.Grey200
import android.example.testovoe_jetpack.ui.theme.SFProDisplay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutAppScreen(){
    val version = remember { mutableStateOf("v.1.0.0") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ToolBarWithBack(title = stringResource(id = R.string.about_app))
        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(R.string.logo)
            )
            Spacer(modifier = Modifier.height(56.dp))
            Text(
                text = stringResource(R.string.name_of_app),
                fontFamily = SFProDisplay,
                fontWeight = FontWeight.W700,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.35.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.for_client),
                style = MaterialTheme.typography.h4,
                color = Grey200
            )
            Spacer(modifier = Modifier.height(38.dp))
            Text(
                text = version.value,
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.W400,
                color = Grey200
            )
        }
    }
}