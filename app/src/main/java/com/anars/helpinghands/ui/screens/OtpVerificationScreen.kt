package com.anars.helpinghands.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.lightColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anars.helpinghands.R
import com.anars.helpinghands.ui.theme.LatoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpVerificationScreen(navController: NavController) {
    var otpCode by remember { mutableStateOf("") }
    var timer by remember { mutableStateOf(59) }
    val scope = rememberCoroutineScope()
    val focusRequesters = List(4) { FocusRequester() } // Focus control for auto-moving
    val keyboardController = LocalSoftwareKeyboardController.current

    // Simulate countdown
    LaunchedEffect(timer) {
        if (timer > 0) {
            kotlinx.coroutines.delay(1000)
            timer--
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Title
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = stringResource(id = R.string.otp_verification),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = LatoFontFamily,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.color_accent)
                )
            )

            Spacer(modifier = Modifier.height(80.dp))
            // Illustration Image
            Image(
                painter = painterResource(id = R.drawable.ic_otp_verification),
                contentDescription = "Login Illustration",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // OTP TextField Row
            OtpInputField(otpCode) { newOtp -> otpCode = newOtp }

            Spacer(modifier = Modifier.height(24.dp))

            // Submit Button

            Button(
                onClick = { navController.navigate("home_screen") },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.color_accent)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.submit),
                    fontFamily = LatoFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }



            Spacer(modifier = Modifier.height(16.dp))

            // Timer with Resend OTP
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = String.format("00:%02d", timer),
                    style = TextStyle(
                        fontSize = 18.sp,  fontFamily = LatoFontFamily,
                        fontWeight = FontWeight.Normal, color = colorResource(
                            id = R.color.grey
                        )
                    ),
                    modifier = Modifier.weight(1f), // Centers the timer
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Resend OTP",
                    color = colorResource(id = R.color.primary),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 15.dp)// Pushes "Resend OTP" to the right
                        .clickable {
                            timer = 59 // Reset timer on resend
                        },
                    textAlign = TextAlign.End,
                    fontFamily = LatoFontFamily,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}

@Composable
fun OtpInputField(otpCode: String, onOtpChange: (String) -> Unit) {
    val focusRequesters = List(4) { FocusRequester() } // Focus control for auto-moving
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(4) { index ->
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(60.dp),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = otpCode.getOrNull(index)?.toString() ?: "",
                    onValueChange = { input ->
                        if (input.length == 1) {
                            val newOtp = otpCode.toCharArray().apply {
                                if (index < otpCode.length) this[index] = input[0]
                                else if (otpCode.length < 4) onOtpChange(otpCode + input)
                            }.joinToString("")
                            onOtpChange(newOtp)

                            // Move to next field if not last
                            if (index < 3) focusRequesters[index + 1].requestFocus()
                            else keyboardController?.hide()
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        color = Color.Black,

                        textAlign = TextAlign.Center,
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .width(40.dp)
                        .focusRequester(focusRequesters[index])
                        .onFocusChanged {
                            if (it.isFocused && otpCode.length > index) {
                                onOtpChange(otpCode.substring(0, index))
                            }
                        }
                )

                Divider(
                    color = colorResource(id = R.color.grey),
                    thickness = 1.dp,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}
