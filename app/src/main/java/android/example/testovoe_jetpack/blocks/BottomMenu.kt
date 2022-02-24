package android.example.testovoe_jetpack.blocks

import android.example.testovoe_jetpack.pojo.BottomMenuContent
import android.example.testovoe_jetpack.ui.theme.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighLightColor: Color = Green100,
    activeTextColor: Color = Green100,
    inactiveTextColor: Color = Grey300,
    initialSelectedItemIndex: Int = 3,
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp)
            .background(Color.White)
    ) {
        items.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                BottomMenuItem(
                    item = item,
                    isSelected = index == selectedItemIndex,
                    activeHighLightColor = activeHighLightColor,
                    activeTextColor = activeTextColor,
                    inactiveTextColor = inactiveTextColor
                ) {
                    selectedItemIndex = index
                }
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighLightColor: Color = Green100,
    counterBackground: Color = Red100,
    activeTextColor: Color = Green100,
    inactiveHighLightColor: Color = Grey400,
    inactiveTextColor: Color = Grey300,
    onItemClick: () -> Unit
) {

    val counter by remember { mutableStateOf(item.counter) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onItemClick()
            }
            .padding(top = 2.dp)

    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeHighLightColor else inactiveHighLightColor,
                modifier = Modifier.size(32.dp)
            )
            if (counter > 0) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .offset(x = 7.dp, y = (-1).dp)
                        .clip(shape = CircleShape)
                        .background(Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(18.dp)
                            .clip(shape = CircleShape)
                            .background(counterBackground),
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(y = (-1).dp),
                            text = counter.toString(),
                            fontFamily = SFProText,
                            fontWeight = FontWeight.W500,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor,
            fontFamily = SFProText,
            fontWeight = if(isSelected) FontWeight.W600 else FontWeight.W500,
            fontSize = 10.sp,
            lineHeight = 12.sp,
            letterSpacing = (-0.24).sp,
        )
    }
}