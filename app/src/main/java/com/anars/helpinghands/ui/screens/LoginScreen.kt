package com.anars.helpinghands.ui.screens

import androidx.annotation.ColorRes
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anars.helpinghands.R
import com.anars.helpinghands.data.Country
import com.anars.helpinghands.ui.theme.HelpingHandsTheme
import com.anars.helpinghands.ui.theme.LatoFontFamily
import com.anars.helpinghands.utils.CountryData.countries

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var mobileNumber by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf("+91") }
    var showDialog by remember { mutableStateOf(false) }

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
                text = "Log In",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = LatoFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.color_accent)
                )
            )

            // Illustration Image

            Spacer(modifier = Modifier.height(75.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_login),
                contentDescription = "Login Illustration",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Mobile Number Field
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = R.string.mobile_number),
                    style = TextStyle(fontWeight = FontWeight.Light, fontSize = 14.sp),
                    color = Color.Black,
                    fontFamily = LatoFontFamily,
                    fontWeight = FontWeight.Normal,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = colorResource(R.color.grey),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(start = 10.dp, end = 1.dp)
                ) {
                    // Dropdown for Country Code
                    Box {
                        Text(
                            text = selectedCountry,
                            modifier = Modifier
                                .clickable { showDialog = true }
                                .padding(8.dp),
                            fontFamily = LatoFontFamily,
                            fontWeight = FontWeight.Normal,
                        )

                    }



                    Spacer(modifier = Modifier.width(8.dp))

                    Divider(
                        color = colorResource(R.color.grey),
                        modifier = Modifier
                            .height(40.dp) // Set desired height
                            .width(1.dp)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    // Phone Number TextField
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = {
                            if (it.length <= 10) {
                                phoneNumber = it
                            }
                        },
                        placeholder = {
                            Text(
                                "Enter Your Mobile Number",
                                fontSize = 14.sp,
                                fontFamily = LatoFontFamily,
                                fontWeight = FontWeight.Normal,
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            if (showDialog) {
                CountrySelectionDialog(
                    countries = countries,
                    onDismiss = { showDialog = false },
                    onCountrySelected = { selectedCountry = "${it.code}" }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Login Button
            Button(
                onClick = { navController.navigate("otp_screen") },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.color_accent)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.login_with_otp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = LatoFontFamily,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            // Terms and Conditions
            TextButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterHorizontally),

                ) {
                Text(
                    text = "Terms & Conditions",
                    fontWeight = FontWeight.Normal,
                    fontFamily = LatoFontFamily,
                    color = colorResource(R.color.primary),
                    fontSize = 14.sp
                )
            }
        }
    }
}