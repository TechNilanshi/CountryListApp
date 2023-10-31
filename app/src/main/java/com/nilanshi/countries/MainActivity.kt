package com.nilanshi.countries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.nilanshi.common.presentation.components.AppToolbarComponent
import com.nilanshi.common.theme.EuropeanCountriesTheme
import com.nilanshi.countries.navigation.EuropeanCountriesNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            EuropeanCountriesTheme {
                val navController = rememberNavController()
                var isShowBackButton by remember { mutableStateOf(false) }
                val defaultTitle = "Country List"
                var actionBarTitle by remember { mutableStateOf(defaultTitle) }

                Scaffold(
                    topBar = {
                        AppToolbarComponent(
                            title = actionBarTitle,
                            onUpClick = {
                                navController.navigateUp()
                            },
                            isShowBackButton = isShowBackButton,
                        )
                    },
                    content = { paddingValues ->
                        EuropeanCountriesNavGraph(
                            navController = navController,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            actionBarTitle = {
                                actionBarTitle = it ?: ""
                            },
                            isShowBackButton = {
                                isShowBackButton = it
                            }
                        )
                    },
                )
            }
        }
    }
}
