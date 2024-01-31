package com.example.shifttest.ui.screens.user_detail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.shifttest.ui.view_model.UsersViewModel

@Composable
fun DetailScreen(viewModel: UsersViewModel, userName: String, onBackstack: () -> Unit) {
    val user = viewModel.findUser(userName)
    val activity = LocalContext.current as Activity

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { onBackstack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
            }
        }) {
        val paddingValues = it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            AsyncImage(
                model = user.picture.large,
                contentDescription = "UserImage",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            Text(
                text = user.name.toString(),
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Gender: ${user.gender}",
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 20.sp
            )
            Text(
                text = "Nationality: ${user.nat}",
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 20.sp
            )
            Text(
                text = buildAnnotatedString {
                    append("Cell phone number: ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append(user.cell)
                    }
                },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        val callIntent = Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:${user.cell}")
                        )
                        startActivity(
                            activity,
                            callIntent,
                            null
                        )
                    },
                fontSize = 20.sp
            )
            Text(
                text = buildAnnotatedString {
                    append("Phone number: ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append(user.phone)
                    }
                },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        val callIntent = Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:${user.phone}")
                        )
                        startActivity(
                            activity,
                            callIntent,
                            null
                        )
                    },
                fontSize = 20.sp
            )
            Text(
                text = buildAnnotatedString {
                    append("Email: ")
                    withStyle(
                        style = SpanStyle(
                            textDecoration = TextDecoration.Underline,
                            color = Color(0xFF0000FF)
                        )
                    ) {
                        append(user.email)
                    }
                },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        val emailIntent =
                            Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${user.email}"))
                        startActivity(
                            activity,
                            emailIntent,
                            null
                        )
                    },
                fontSize = 20.sp
            )
            Text(
                text = buildAnnotatedString {
                    append("Address: ")
                    withStyle(
                        style = SpanStyle(
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(user.location.toString())
                    }
                },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        val latitude = user.location.coordinates.latitude
                        val longitude = user.location.coordinates.longitude

                        val mapIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("geo:$latitude,$longitude?z=14")
                        )
                        startActivity(activity, mapIntent, null)
                    },
                fontSize = 20.sp
            )
        }
    }
}