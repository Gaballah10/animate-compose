package com.example.animatedbottomsheet.ui.login.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.animatedbottomsheet.R
import com.example.animatedbottomsheet.ui.widget.AnimatedIconBox
import com.example.animatedbottomsheet.ui.widget.AnimatedIconBoxInfinity
import com.example.animatedbottomsheet.ui.widget.IconWithOrbitingSun
import com.example.animatedbottomsheet.ui.widget.IconWithSunAndMoon
import com.example.animatedbottomsheet.ui.widget.LoginShadowButton
import com.example.animatedbottomsheet.utils.SusaFontFamily
import com.example.animatedbottomsheet.utils.VayuFontFamily


@Composable
fun LoginPage() {

    LoginScreen()
}

@Composable
fun LoginScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (bgColor, icLogin, blueDotTxt, calmTxt, appleBtn, googleBtn) = createRefs()


        //---- Background color
        Box(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(bgColor) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }) {
            Image(
                painter = painterResource(id = R.drawable.bg_login_screen),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        IconWithSunAndMoon(Modifier
            .constrainAs(icLogin) {
                top.linkTo(parent.top, margin = 110.dp)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            } , R.drawable.ic_travel_login_mod)


        Text(
            modifier = Modifier.constrainAs(blueDotTxt) {
                top.linkTo(icLogin.bottom, margin = (40).dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "Blue Dot.",
            fontSize = 48.sp,
            fontFamily = SusaFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color(color = 0xFF222222)
        )

        Text(
            modifier = Modifier.constrainAs(calmTxt) {
                top.linkTo(blueDotTxt.bottom, margin = 18.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "The Calm Way to Travel",
            fontFamily = VayuFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color.Black
        )


        LoginShadowButton(modifier = Modifier.constrainAs(appleBtn) {
            top.linkTo(calmTxt.bottom, margin = 40.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, text = "Continue with Apple", svgResId = R.drawable.ic_apple_logo, onClick = {
            Log.d("button Clicked ", "Go with apple")
        })

        LoginShadowButton(modifier = Modifier.constrainAs(googleBtn) {
            top.linkTo(appleBtn.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, text = "Continue with Google", svgResId = R.drawable.ic_google_logo, onClick = {
            Log.d("button Clicked ", "Go with google")
        })


    }

}