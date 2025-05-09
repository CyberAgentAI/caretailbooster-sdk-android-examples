package com.github.cyberagentai.caretailbooster_sdk_android_examples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.github.cyberagentai.caretailbooster_sdk_android_examples.theme.AndroidSDKTheme
import com.retaiboo.caretailboostersdk.useCaRetailBooster
import com.retaiboo.caretailboostersdk.CaRetailBoosterCallback
import com.retaiboo.caretailboostersdk.CaRetailBoosterEnvMode
import com.retaiboo.caretailboostersdk.CaRetailBoosterOptions
import com.retaiboo.caretailboostersdk.CaRetailBoosterRewardAdOptions

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = object : CaRetailBoosterCallback {
            override fun onRewardModalClose() {
                Log.d("callback", "onRewardModalClose: ")
            }

            override fun onMarkSucceeded() {
                Log.d("callback", "onMarkSucceeded: ")
            }
        }
        val rewardAdOptions = object : CaRetailBoosterRewardAdOptions {
            override val width = 173
            override val height = 210
        }
        val options = object : CaRetailBoosterOptions {
            override val rewardAd = rewardAdOptions
        }

        enableEdgeToEdge()
        setContent {
            val banner1 = useCaRetailBooster(
                context = this@MainActivity,
                mediaId = "media_id",
                userId = "user_id",
                crypto = "crypto",
                tagGroupId = "banner1",
                mode = CaRetailBoosterEnvMode.MOCK
            )
            val reward1 = useCaRetailBooster(
                context = this@MainActivity,
                mediaId = "media_id",
                userId = "user_id",
                crypto = "crypto",
                tagGroupId = "reward1",
                mode = CaRetailBoosterEnvMode.MOCK,
                callback = callback,
                options = options
            )
            AndroidSDKTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Top bar") }
                        )
                    },
                ) { contentPadding ->
                    Column(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(
                            modifier = Modifier
                                .padding(contentPadding)
                                .fillMaxSize()
                        ) {
                            item {
                                Text(
                                    text = "Banner Sample"
                                )
                            }
                            item {
                                LazyRow {
                                    banner1.ads.forEach { ad ->
                                        item {
                                            ad()
                                        }
                                    }
                                }
                            }
                            item {
                                Text(
                                    text = "Reward Sample"
                                )
                            }
                            item {
                                LazyRow {
                                    reward1.ads.forEach { ad ->
                                        item {
                                            ad()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
