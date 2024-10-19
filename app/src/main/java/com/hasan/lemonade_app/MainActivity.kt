package com.hasan.lemonade_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hasan.lemonade_app.ui.theme.LemonadeappTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeappTheme {
                LemonApp()
            }
        }
    }
}


//// fungsi halaman pertama dibukak
//@Composable
//fun WelcomeScreen(name: String, onStartClicked: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "Welcome $name!",
//            style = MaterialTheme.typography.headlineMedium
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = onStartClicked) {
//            Text("Start")
//        }
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {
    // state welcome screen
    var showWelcomeScreen by remember { mutableStateOf(true) }
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "LemonApk",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            if (showWelcomeScreen) {
                WelcomeScreen(name = "User") {
                    showWelcomeScreen = false // Mengubah state agar masuk ke aplikasi
                }
            } else {
                when (currentStep) {
                    1 -> {
                        LemonTextAndImage(
                            textLabelResourceId = R.string.lemon_select,
                            drawableResourceId = R.drawable.lemon_tree,
                            contentDescriptionResourceId = R.string.lemon_tree_content_description,
                            onImageClick = {
                                currentStep = 2
                                squeezeCount = (2..4).random()
                            }
                        )
                    }
                    2 -> {
                        LemonTextAndImage(
                            textLabelResourceId = R.string.lemon_squeeze,
                            drawableResourceId = R.drawable.lemon_squeeze,
                            contentDescriptionResourceId = R.string.lemon_content_description,
                            onImageClick = {
                                squeezeCount--
                                if (squeezeCount == 0) {
                                    currentStep = 3
                                }
                            }
                        )
                    }

                    3 -> {
                        LemonTextAndImage(
                            textLabelResourceId = R.string.lemon_drink,
                            drawableResourceId = R.drawable.lemon_drink,
                            contentDescriptionResourceId = R.string.lemonade_content_description,
                            onImageClick = {
                                currentStep = 4
                            }
                        )
                    }
                    4 -> {
                        LemonTextAndImage(
                            textLabelResourceId = R.string.lemon_empty_glass,
                            drawableResourceId = R.drawable.lemon_restart,
                            contentDescriptionResourceId = R.string.empty_glass_content_description,
                            onImageClick = {
                                currentStep = 1
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId),
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_image_width))
                        .height(dimensionResource(R.dimen.button_image_height))
                        .padding(dimensionResource(R.dimen.button_interior_padding))
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            Text(
                text = stringResource(textLabelResourceId),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun LemonPreview() {
    LemonadeappTheme {
        LemonApp()
    }
}



@Composable
fun WelcomeScreen(name: String, onStartClicked: () -> Unit) {
    Column {
        Text(text = "Welcome $name!")
        Button(
            onClick = onStartClicked
        ) {
            Text("Start")
        }
    }
}