package com.example.hospitalsystem.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.core.AuthPref
import com.example.hospitalsystem.core.NetworkMonitor
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.composables.ProfileItem
import com.example.hospitalsystem.presentation.viewmodels.profile.ProfileViewModel
import com.example.hospitalsystem.theme.Primary
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(), employeeId: Int
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val authPref = remember { AuthPref(context) }
    val scope = rememberCoroutineScope()

    DisposableEffect(context) {
        val networkMonitor = NetworkMonitor(context)
        val observer = Observer<Boolean> { isConnected ->
            if (isConnected) {
                viewModel.refreshProfile(employeeId)
            }
        }
        networkMonitor.isConnected.observe(context as LifecycleOwner, observer)
        onDispose {
            networkMonitor.isConnected.removeObserver(observer)
            networkMonitor.unregisterCallback()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        when {
            uiState.isLoading -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Loading...",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }

            uiState.profile != null -> {
                val profile = uiState.profile?.data

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "My Profile",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {

                        Card(
                            shape = RoundedCornerShape(24.dp),
                            elevation = CardDefaults.cardElevation(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 60.dp),
                            colors = CardDefaults.cardColors(Color.White)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 60.dp,
                                        start = 16.dp,
                                        end = 16.dp,
                                        bottom = 16.dp
                                    )
                            ) {
                                profile?.let {
                                    Text(
                                        text = "${it.firstName} ${it.lastName}",
                                        color = Primary,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    ProfileItem(
                                        icon = R.drawable.ic_specialist,
                                        text = it.specialist ?: ""
                                    )
                                    ProfileItem(icon = R.drawable.ic_gender, text = it.gender ?: "")
                                    ProfileItem(
                                        icon = R.drawable.ic_calendar,
                                        text = it.birthday ?: ""
                                    )
                                    ProfileItem(
                                        icon = R.drawable.ic_location,
                                        text = it.address ?: ""
                                    )
                                    ProfileItem(icon = R.drawable.ic_status, text = it.status ?: "")
                                    ProfileItem(icon = R.drawable.ic_email, text = it.email ?: "")
                                    ProfileItem(icon = R.drawable.ic_phone, text = it.mobile ?: "")
                                }
                            }
                        }

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(Color.Transparent)
                                .border(
                                    width = 4.dp,
                                    brush = Brush.radialGradient(
                                        colors = listOf(Color(0xFF04302C), Primary)
                                    ),
                                    shape = CircleShape
                                )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.user),
                                contentDescription = "Profile Image",
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    IconButton(
                        onClick = {
                            scope.launch {
                                authPref.clearUserType()
                            }
                            navController.navigate(Screen.LoginScreen.route) {
                                popUpTo(0) { inclusive = true }
                            }
                        },
                        modifier = Modifier
                            .background(Color.Red, shape = CircleShape)
                            .size(50.dp)
                    ) {
                        Icon(
                            painter = rememberVectorPainter(Icons.AutoMirrored.Outlined.Logout),
                            contentDescription = "Logout",
                            tint = Color.White
                        )
                    }


                }
            }

            uiState.error != null -> {
                Text(
                    text = "Error loading profile: ${uiState.error}",
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
