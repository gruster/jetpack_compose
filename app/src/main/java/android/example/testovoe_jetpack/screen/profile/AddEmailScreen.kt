package android.example.testovoe_jetpack.screen.profile

import android.example.testovoe_jetpack.R
import android.example.testovoe_jetpack.blocks.GreenButton
import android.example.testovoe_jetpack.blocks.ProfileTextField
import android.example.testovoe_jetpack.blocks.ToolBarWithBack
import android.example.testovoe_jetpack.screen.ProfileViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AddEmailScreen(profileViewModel: ProfileViewModel = viewModel()){
    val userInfo by profileViewModel.userInfo.observeAsState()
    val email = remember{ mutableStateOf(userInfo?.email) }
    val isFocusTextField = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ToolBarWithBack(title = stringResource(id = R.string.my_email_title))
        Spacer(modifier = Modifier.height(27.dp))
        ProfileTextField(
            email,
            stringResource(R.string.name),
            stringResource(R.string.hint_no_spam),
            isFocusTextField,
        )
        Column(
            modifier = Modifier.weight(1f).padding(16.dp),
            Arrangement.Bottom
        ) {
            GreenButton(isFocusTextField = isFocusTextField)
        }
    }
}