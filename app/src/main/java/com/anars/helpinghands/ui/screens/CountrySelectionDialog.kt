package com.anars.helpinghands.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.anars.helpinghands.R
import com.anars.helpinghands.data.Country
import com.anars.helpinghands.ui.theme.LatoFontFamily

@Composable
fun CountrySelectionDialog(
    countries: List<Country>,
    onDismiss: () -> Unit,
    onCountrySelected: (Country) -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .clickable(onClick = onDismiss)
        ) {
            Card(
                shape = RoundedCornerShape(4.dp),
                elevation = 8.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .wrapContentHeight()
            ) {
                Column {
                    Text(
                        text = "Select Country",
                        fontSize = 18.sp,
                        fontFamily = LatoFontFamily,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    LazyColumn {
                        itemsIndexed(countries) { index, country ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onCountrySelected(country)
                                        onDismiss()
                                    }
                                    .padding(vertical = 8.dp, horizontal = 16.dp)
                            ) {
                                Text(
                                    text = country.name,
                                    modifier = Modifier.weight(1f), // Pushes the country code to the end
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = colorResource(id = R.color.black)
                                    ),
                                    fontFamily = LatoFontFamily,
                                    fontWeight = FontWeight.Normal,

                                )
                                Text(
                                    text = country.code,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Light,
                                        color = colorResource(id = R.color.black)
                                    ),
                                    fontFamily = LatoFontFamily,
                                    fontWeight = FontWeight.Normal,

                                )
                            }




                            if (index < countries.lastIndex) {
                                Divider(
                                    color = colorResource(id = R.color.grey),
                                    thickness = 1.dp,
                                    modifier = Modifier.padding(horizontal = 10.dp)
                                )
                            }
                        }
                    }


                }
            }
        }
    }
}
