package android.example.testovoe_jetpack.screen.profile

import android.example.testovoe_jetpack.R
import android.example.testovoe_jetpack.blocks.GreenButton
import android.example.testovoe_jetpack.blocks.ProfileTextField
import android.example.testovoe_jetpack.blocks.ToolBarWithBack
import android.example.testovoe_jetpack.pojo.UserInfo
import android.example.testovoe_jetpack.screen.ProfileViewModel
import android.example.testovoe_jetpack.ui.theme.Grey200
import android.example.testovoe_jetpack.ui.theme.Grey500
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun AddInfoScreen(profileViewModel: ProfileViewModel = viewModel()) {
    val userInfo by profileViewModel.userInfo.observeAsState()
    val name = remember { mutableStateOf(userInfo?.name) }
    val surname = remember { mutableStateOf(userInfo?.surname) }


    val isFocusTextField = remember { mutableStateOf(false) }
    var imageUrl by remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUrl = uri
    }

    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var scrollToPosition by remember { mutableStateOf(0F) }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier
            .onGloballyPositioned {
                if (isFocusTextField.value) {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(scrollToPosition.roundToInt())
                    }
                }
            }
        ) {
            ToolBarWithBack(stringResource(R.string.private_info))
            Spacer(modifier = Modifier.height(35.dp))
            ChooseAvatar(
                imageUrl,
                bitmap,
                launcher,
                userInfo
            )
            Spacer(modifier = Modifier.height(24.dp))

            ProfileTextField(
                name,
                stringResource(R.string.name),
                "",
                isFocusTextField,
            )

            Spacer(modifier = Modifier.height(24.dp))

            ProfileTextField(
                surname,
                stringResource(R.string.surname),
                stringResource(R.string.hint_surname),
                isFocusTextField
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(37.dp))

        Column(modifier = Modifier.onGloballyPositioned { coordinates ->
                scrollToPosition = coordinates.positionInParent().y
            }
        ) {
            Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 29.dp)) {
                GreenButton(isFocusTextField)
            }
            if (!isFocusTextField.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.delete_profile),
                        style = MaterialTheme.typography.h2,
                        letterSpacing = (-0.408).sp,
                        color = Grey200,
                        modifier = Modifier.clickable { /* TODO */ }
                    )
                }
            }
        }

    }
}

@Composable
fun ChooseAvatar(
    imageUrl: Uri?,
    bitmap: MutableState<Bitmap?>,
    launcher: ManagedActivityResultLauncher<String, Uri?>,
    userInfo: UserInfo?
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(shape = CircleShape)
                .background(color = Grey500)
                .clickable {
                    launcher.launch("image/*")
                },
            Alignment.Center,
        ) {
            if (imageUrl != null) {
                imageUrl.let {
                    if (Build.VERSION.SDK_INT < 28) {
                        bitmap.value =
                            MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        bitmap.value = ImageDecoder.decodeBitmap(source)
                    }

                    bitmap.value?.let { bitmap ->
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = stringResource(R.string.download_photo),
                            modifier = Modifier.size(400.dp)
                        )
                    }
                }
            } else {
                if (userInfo?.avatar ?: 0 == 0) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = stringResource(R.string.download_photo),
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    Image(
                        painter = painterResource(id = userInfo?.avatar ?: 0),
                        contentDescription = stringResource(R.string.avatar),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(400.dp)
                            .clip(shape = CircleShape)
                    )
                }
            }
        }
    }
}