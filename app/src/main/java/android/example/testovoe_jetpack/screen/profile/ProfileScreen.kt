package android.example.testovoe_jetpack.screen.profile

import android.example.testovoe_jetpack.R
import android.example.testovoe_jetpack.blocks.BottomMenu
import android.example.testovoe_jetpack.pojo.BottomMenuContent
import android.example.testovoe_jetpack.navigation.Screen
import android.example.testovoe_jetpack.screen.ProfileViewModel
import android.example.testovoe_jetpack.ui.theme.*
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController) {
    val profileViewModel = ProfileViewModel()
    val userInfo by profileViewModel.userInfo.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundApp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(29.dp))
            ToolBar(navController = navController)
            Spacer(modifier = Modifier.height(23.dp))
            UserInfoCard(
                name = userInfo?.name,
                surname = userInfo?.surname,
                rates = userInfo?.rates,
                registrationDate = userInfo?.registrationDate,
                avatar = userInfo?.avatar
            )
            Spacer(modifier = Modifier.height(21.dp))
            ContactInfo(
                phone = userInfo?.phone,
                email = userInfo?.email,
                navController = navController
            )
            Spacer(modifier = Modifier.height(24.dp))
            ProfileOptions(navController = navController)
            Spacer(modifier = Modifier.height(24.dp))
            userInfo?.let {
                if (it.isShowBanner) {
                    Banner()
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            ButtonExit()
            Spacer(modifier = Modifier.height(71.dp))
        }
        BottomMenu(
            modifier = Modifier.align(Alignment.BottomCenter),
            items = listOf(
                BottomMenuContent(
                    stringResource(R.string.bottom_menu_services),
                    0,
                    R.drawable.ic_services
                ),
                BottomMenuContent(
                    stringResource(R.string.bottom_menu_orders),
                    userInfo?.orders ?: 0,
                    R.drawable.ic_orders
                ),
                BottomMenuContent(
                    stringResource(R.string.bottom_menu_knowledge),
                    0,
                    R.drawable.ic_knowledge
                ),
                BottomMenuContent(
                    stringResource(R.string.bottom_menu_profile),
                    0,
                    R.drawable.ic_profile
                ),
            )
        )
    }

}

@Composable
private fun ToolBar(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.my_profile),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = R.drawable.ic_pencil),
            contentDescription = stringResource(R.string.edit_profile),
            modifier = Modifier
                .clickable { navController.navigate(route = Screen.AddInfoScreen.route) },
        )
    }
}

@Composable
private fun UserInfoCard(
    name: String?,
    surname: String?,
    rates: Double?,
    registrationDate: Int?,
    avatar: Int?
) {
    val isShowHint = !(avatar != null && avatar > 0)
    val fullName = if (!surname.isNullOrEmpty()) {
        "$name ${surname[0].uppercase()}."
    } else {
        name
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 0.6.dp,
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Box {
                    val avatarImg = if (!isShowHint) {
                        painterResource(id = avatar ?: 0)
                    } else {
                        painterResource(id = R.drawable.ic_default_avatar)
                    }
                    Image(
                        painter = avatarImg,
                        contentDescription = stringResource(R.string.avatar),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(shape = CircleShape)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = fullName ?: "",
                        style = MaterialTheme.typography.h1
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = stringResource(R.string.rates),
                            Modifier.padding(end = 5.dp)
                        )
                        Text(
                            text = rates.toString(),
                            style = MaterialTheme.typography.h4,
                        )
                    }
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.padding(end = 4.dp),
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = stringResource(R.string.registration_date)
                        )
                        Text(
                            style = MaterialTheme.typography.h4,
                            color = Grey100,
                            text = "На сервисе с ${registrationDate.toString()}"
                        )
                    }
                }
            }
            Row {
                if (isShowHint) {
                    Row(
                        modifier = Modifier
                            .width(80.dp)
                            .padding(top = 5.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_up_left_arrow_red),
                            contentDescription = stringResource(R.string.arrow_add_avatar)
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(top = 11.dp, start = 12.dp)
                            .width(217.dp),
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = SFProText,
                            lineHeight = 16.sp
                        ),
                        text = stringResource(R.string.hint_add_avatar)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactInfo(phone: String?, email: String?, navController: NavHostController) {
    Card(
        Modifier.fillMaxWidth(),
        elevation = 0.6.dp,
        shape = MaterialTheme.shapes.small
    ) {
        Column {
            ProfileOptionsItem(
                iconId = R.drawable.ic_phone,
                iconDescription = stringResource(R.string.my_phone_field_icon),
                label = stringResource(R.string.my_phone_title),
                contact = phone ?: "",
                navController = navController,
                route = "" // TODO нет роута
            )
            Border()
            ProfileOptionsItem(
                iconId = R.drawable.ic_email,
                iconDescription = stringResource(R.string.my_email_field_icon),
                label = stringResource(R.string.my_email_title),
                contact = email ?: "",
                navController = navController,
                route = Screen.AddEmailScreen.route
            )
        }
    }
}

@Composable
fun ProfileOptions(navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 0.6.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Column {
            ProfileOptionsItem(
                iconId = R.drawable.ic_star_rate_app,
                iconDescription = stringResource(R.string.rate_app),
                label = stringResource(R.string.rate_app),
                contact = "options",
                navController = navController,
                route = "" // TODO нет роута
            )
            Border()
            ProfileOptionsItem(
                iconId = R.drawable.ic_support,
                iconDescription = stringResource(R.string.write_to_support),
                label = stringResource(R.string.write_to_support),
                contact = "options",
                navController = navController,
                route = "" // TODO нет роута
            )
            Border()
            ProfileOptionsItem(
                iconId = R.drawable.ic_about,
                iconDescription = stringResource(R.string.about_app),
                label = stringResource(R.string.about_app),
                contact = "options",
                navController = navController,
                route = Screen.AboutAppScreen.route
            )
        }
    }
}

@Composable
fun ProfileOptionsItem(
    iconId: Int,
    iconDescription: String,
    label: String,
    contact: String,
    navController: NavHostController,
    route: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                if (route.isNotEmpty()) navController.navigate(route) //TODO как будудут все роуты, убрать if
            }
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp)
    ) {
        Image(
            painter = painterResource(iconId),
            contentDescription = iconDescription,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp)
        ) {
            Text(
                style = MaterialTheme.typography.h3,
                text = label
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(fraction = 1f)
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (contact != "options") {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = contact.ifEmpty { stringResource(R.string.empty_field) },
                        style = MaterialTheme.typography.h3,
                        color = Grey200
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = stringResource(R.string.add_email)
                )
            }
        }
    }
}

@Composable
fun Border() {
    Divider(
        modifier = Modifier
            .padding(start = 60.dp)
            .fillMaxWidth()
            .height(0.5.dp)
            .clip(shape = RoundedCornerShape(2.dp)),
        color = GreyBorder
    )
}

@Composable
fun Banner() {
    val context = LocalContext.current
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .height(80.dp)
            .border(
                0.5.dp,
                Color.Black.copy(alpha = 0.05f),
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    Brush.linearGradient(
                        listOf(
                            BannerGradientStart,
                            BannerGradientEnd
                        )
                    )
                )
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(R.string.work_for_yourself),
                    style = MaterialTheme.typography.h2,
                    color = Color.White
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = stringResource(R.string.become_performer),
                    style = MaterialTheme.typography.h5,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
            Box(contentAlignment = Alignment.TopEnd) {
                Box {
                    Image(
                        modifier = Modifier
                            .offset(y = 3.dp)
                            .height(118.dp)
                            .width(118.dp)
                            .padding(end = 6.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.banner_be_performer),
                        contentDescription = stringResource(R.string.banner_be_performer)
                    )
                }
                Box(modifier = Modifier
                    .offset(x = (-8).dp, y = 9.dp)
                    .clickable { /*TODO*/
                        Toast
                            .makeText(context, "закрываем баннер", Toast.LENGTH_SHORT)
                            .show()
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = stringResource(R.string.ic_close_banner)
                    )
                }
            }
        }
    }
}

@Composable
fun ButtonExit() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Red100
        ),
        onClick = { /*TODO*/ },
        shape = MaterialTheme.shapes.small,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.4.dp
        )
    ) {
        Text(
            text = stringResource(R.string.exit_app),
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight.W400,
            letterSpacing = (-0.408).sp,
        )
    }
}
