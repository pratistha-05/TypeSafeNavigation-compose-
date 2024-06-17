package com.example.typesafenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.typesafenavigation.ui.theme.TypeSafeNavigationTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TypeSafeNavigationTheme {
        val navController = rememberNavController()
        NavHost(
          navController = navController,
          startDestination = People
        ) {
          composable<People> {
            Column(
              modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Button(onClick = {
                navController.navigate(
                  Profile(
                  name = "Jacob",
                  age = 25)
                )
              }) {
                Text(text = "Navigate to screen B")
              }
            }
          }
          composable<Profile> {
            val args = it.toRoute<Profile>()
            Column(
              modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(text = "${args.name}, ${args.age} years old")
            }
          }
        }
      }
    }
  }
}

@Serializable
object People

@Serializable
data class Profile(
  val name: String?,
  val age: Int
)