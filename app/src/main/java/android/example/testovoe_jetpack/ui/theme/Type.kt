package android.example.testovoe_jetpack.ui.theme

import android.example.testovoe_jetpack.R
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)
val SFProDisplay = FontFamily(
        Font(R.font.sfprodisplay_semibold, FontWeight.W600),
        Font(R.font.sfprodisplay_bold, FontWeight.W700),
)

val SFProText = FontFamily(
    Font(R.font.sfprotext_regular, FontWeight.W400),
    Font(R.font.sfprotext_semibold, FontWeight.W600),
)

val SFProTypography = Typography(
    h1 = TextStyle(
        fontFamily = SFProDisplay,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    h2 = TextStyle(
        fontFamily = SFProText,
        fontWeight = FontWeight.W600,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.408).sp
    ),
    h3 = TextStyle(
        fontFamily = SFProText,
        fontWeight = FontWeight.W400,
        fontSize = 17.sp,
        lineHeight = 22.sp
    ),
    h4 = TextStyle(
        fontFamily = SFProText,
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.24).sp
    ),
    h5 = TextStyle(
        fontFamily = SFProText,
        fontWeight = FontWeight.W400,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.078).sp
    ),
)
