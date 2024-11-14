package com.example.listofproductes.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.listofproductes.ui.theme.ListofproductesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListofproductesTheme {

                val navHostController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavGraph(navHostController = navHostController, startDestination =  NavRote.UserListScreen.path)

                }
            }
        }
    }
}


