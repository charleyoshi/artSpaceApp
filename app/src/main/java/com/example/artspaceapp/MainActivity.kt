package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpacePage()
                }
            }
        }
    }
}

@Composable
fun ArtSpacePage(modifier: Modifier = Modifier) {
    val imageOne = R.drawable.image_one
    val imageTwo = R.drawable.image_two
    val imageThree = R.drawable.image_three

    var title by remember { mutableStateOf(R.string.image_one_title) }

    var author by remember { mutableStateOf(R.string.image_one_author) }

    var year by remember { mutableStateOf(R.string.image_two_year) }

    var currentImage by remember {
        mutableStateOf(imageOne)
    }

    var imageResource by remember {
        mutableStateOf(currentImage)
    }
    Column(
        modifier = Modifier.width(900.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkDisplay(currentImage = currentImage)
        Spacer(modifier = modifier.size(26.dp))
        ArtworkTitle(title = title, year = year, author = author)
        Spacer(modifier = modifier.size(55.dp))
        Row(
            modifier = modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(48.dp, Alignment.CenterHorizontally)
        ) {
            // Previous Button
            Button(
                onClick = {
                    when (currentImage) {
                        imageOne -> {
                            currentImage = imageThree
                            title = R.string.image_three_title
                            author = R.string.image_three_author
                            year = R.string.image_three_year
                        }
                        imageTwo -> {
                            currentImage = imageOne
                            title = R.string.image_one_title
                            author = R.string.image_one_author
                            year = R.string.image_one_year
                        }
                        else -> {
                            currentImage = imageTwo
                            title = R.string.image_two_title
                            author = R.string.image_two_author
                            year = R.string.image_two_year
                        }
                    }
                }
            ) {
                Text(
                    text = "Previous",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }

            // Next Button
            Button(
                onClick = {
                    when (currentImage) {
                        imageOne -> {
                            currentImage = imageTwo
                            title = R.string.image_two_title
                            author = R.string.image_two_author
                            year = R.string.image_two_year
                        }
                        imageTwo -> {
                            currentImage = imageThree
                            title = R.string.image_three_title
                            author = R.string.image_three_author
                            year = R.string.image_three_year
                        }
                        else -> {
                            currentImage = imageOne
                            title = R.string.image_one_title
                            author = R.string.image_one_author
                            year = R.string.image_one_year
                        }
                    }
                }
            ) {
                Text(
                    text = "Next",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }

}


@Composable
fun ArtworkDisplay(
    modifier: Modifier = Modifier,
    @DrawableRes currentImage: Int
) {
    Image(
        painter = painterResource(currentImage),
        contentDescription = stringResource(id = R.string.image_one_title),
        modifier = modifier.border(18.dp, Color.White).shadow(12.dp)
    )
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes year: Int,
    @StringRes author: Int,

) {
    Column (
        modifier = Modifier
            .background(Color.LightGray)
            .width(270.dp)
            .padding(24.dp, 12.dp),
    ) {
        // Artwork title
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Light,
            color = colorResource(id = R.color.black),
            fontSize = 30.sp
        )
        Row {
            Text(
                text = stringResource(id = author),
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = R.color.black)
            )
            Text(
                text = " (${stringResource(id = year)})",
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = colorResource(id = R.color.black)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtSpacePage()
    }
}