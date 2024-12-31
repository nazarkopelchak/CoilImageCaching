package com.nazarkopelchak.coilimagecaching

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.imageLoader
import coil.memory.MemoryCache
import com.nazarkopelchak.coilimagecaching.ui.theme.CoilImageCachingTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val imageUrl = "https://giffiles.alphacoders.com/157/15771.gif"
        val imageUr2 = "https://images8.alphacoders.com/111/1117718.jpg"

        setContent {
            CoilImageCachingTheme {
                Column(
                    modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(498f/498f)
                    )
                    Spacer(Modifier.height(16.dp))
                    SubcomposeAsyncImage(
                        model = imageUr2,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2/1f),
                        loading = {
                            CircularProgressIndicator()
                        }
                    )
                    Button(
                        onClick = {
                            //imageLoader.diskCache?.clear()
                            //imageLoader.memoryCache?.clear()
                            imageLoader.diskCache?.remove(imageUr2) // removes a single image from the disk cache
                            imageLoader.memoryCache?.remove(MemoryCache.Key(imageUr2)) // removes a single image from the memory cache
                        }
                    ) {
                        Text(text = "Clear Coil Memory")
                    }
                }
            }
        }
    }
}