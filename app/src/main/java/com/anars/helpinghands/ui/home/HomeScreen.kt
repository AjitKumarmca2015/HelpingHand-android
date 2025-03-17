package com.anars.helpinghands.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anars.helpinghands.R
import com.anars.helpinghands.data.NavigationDataModel
import com.anars.helpinghands.ui.theme.LatoFontFamily
import com.anars.helpinghands.ui.theme.backgroundColor

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavBar() },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = colorResource(id = R.color.grey_light)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            TopBar()
            BannerSection()
            TabSection()
            MainContent()
        }
    }
}


@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Ensures text and icon are separated
    ) {
        Column {
            Text(
                text = "Welcome,",
                fontSize = 12.sp,
                textAlign = TextAlign.Start, // Aligns text to the start
                fontFamily = LatoFontFamily,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.color_accent)
            )
            Text(
                text = "Sam Parker",
                fontSize = 16.sp,
                color = colorResource(id = R.color.color_accent),
                fontFamily = LatoFontFamily,
                fontWeight = FontWeight.ExtraBold,
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_home_search), // Replace with your custom icon
            contentDescription = "Search Icon",
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.CenterVertically)
        )

    }
}


@Composable
fun BannerSection() {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xFF0F3D56), // Dark Blue Background
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = "You are not \nalone in case of\nan emergency",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = LatoFontFamily,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(25.dp))

                // Dots Indicator
                Row {
                    listOf( colorResource(id = R.color.orange), Color.Gray, Color.Gray, Color.Gray).forEach {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .padding(2.dp)
                                .background(it, shape = CircleShape)
                        )
                    }
                }
            }

            // Placeholder for the Hand Image
            Image(
                painter = painterResource(id = R.drawable.ic_intro_sixth), // Replace with actual image resource
                contentDescription = "Helping Hands",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }

}


@Composable
fun TabSection() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Who's coming", "Assist now", "History")

    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        edgePadding = 16.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab])
                    .height(2.dp),
                color = colorResource(id = R.color.orange)
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { selectedTab = index },
                text = {
                    Text(
                        text = title,
                        fontSize = 12.sp,
                        fontFamily = LatoFontFamily,
                        color = if (selectedTab == index) colorResource(id = R.color.black) else colorResource(
                            id = R.color.grey
                        ),
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

@Composable
fun MainContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty_whocomming), // Replace with actual resource
            contentDescription = "Illustration",
            modifier = Modifier.size(180.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Don’t worry, you will get to know\nwho’s coming whenever you’ll trigger\nan emergency notification.",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(66.dp))
    }
}

@Composable
fun BottomNavBar() {
    val navItemsLeft = listOf("Home", "Alerts")
    val navItemsRight = listOf("Profile", "Settings")
    val selectedItem = remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomNavigation(
            backgroundColor = Color.White,
            elevation = 8.dp,
            modifier = Modifier.height(80.dp)
        ) {
            // Left side items
            navItemsLeft.forEachIndexed { index, item ->
                BottomNavigationItem(
                    selected = selectedItem.value == index,
                    onClick = { selectedItem.value = index },
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = getIconForItem(
                                    item,
                                    selectedItem.value == index
                                )
                            ),
                            contentDescription = item,
                            tint = if (selectedItem.value == index) Color.Black else Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item,
                            fontSize = 12.sp,
                            fontFamily = LatoFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = if (selectedItem.value == index) Color.Black else Color.Gray
                        )
                    },
                    selectedContentColor = colorResource(id = R.color.primary),
                    unselectedContentColor = Color.Gray
                )
            }

            // Spacer for floating button
            Spacer(Modifier.weight(1f))

            // Right side items
            navItemsRight.forEachIndexed { index, item ->
                BottomNavigationItem(
                    selected = selectedItem.value == index + navItemsLeft.size,
                    onClick = { selectedItem.value = index + navItemsLeft.size },
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = getIconForItem(
                                    item,
                                    selectedItem.value == index + navItemsLeft.size
                                )
                            ),
                            contentDescription = item,
                            tint = if (selectedItem.value == index + navItemsLeft.size) Color.Black else Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )


                    },
                    label = {
                        Text(
                            text = item,
                            fontSize = 12.sp,
                            fontFamily = LatoFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = if (selectedItem.value == index + navItemsLeft.size) Color.Black else Color.Gray
                        )
                    },
                    selectedContentColor = colorResource(id = R.color.primary),
                    unselectedContentColor = Color.Gray
                )
            }
        }

            // Center Floating Action Button with Glow Effect
        Box(
            modifier = Modifier
                .offset(y = -25.dp) // Adjust floating effect
                .size(70.dp) // Button size
                .background(Color.Transparent, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_nav_plus),
                    contentDescription = "Login Illustration",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(70.dp)
                )


        }
    }
}





@Composable
fun getIconForItem(item: String, isSelected: Boolean): Int {
    return when (item) {
        "Home" -> if (isSelected) R.drawable.ic_nav_home else R.drawable.ic_nav_home
        "Alerts" -> if (isSelected) R.drawable.ic_nav_notification else R.drawable.ic_nav_notification
        "Profile" -> if (isSelected) R.drawable.ic_nav_profile else R.drawable.ic_nav_profile
        "Settings" -> if (isSelected) R.drawable.ic_nav_settings else R.drawable.ic_nav_settings
        else -> R.drawable.ic_nav_home
    }
}




