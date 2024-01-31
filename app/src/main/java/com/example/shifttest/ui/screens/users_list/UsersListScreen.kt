package com.example.shifttest.ui.screens.users_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shifttest.data.user.User
import com.example.shifttest.ui.view_model.UsersViewModel

@Composable
fun UsersListScreen(usersViewModel: UsersViewModel, onUserClick: (String) -> Unit) {
    val usersList by usersViewModel.usersList.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { usersViewModel.refresh() }) {
                Icon(Icons.Outlined.Refresh, contentDescription = "Refresh")
            }
        }) {
        val paddingValues = it
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(usersList) { user ->
                ItemUser(
                    user,
                    Modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .padding(16.dp)
                ) {
                    onUserClick(user.name.toString())
                }
            }
        }
    }

}

@Composable
private fun ItemUser(user: User, modifier: Modifier = Modifier, onUserClick: (String) -> Unit) {
    Row(
        modifier = modifier
            .clickable {
                onUserClick(user.name.toString())
            }
    ) {
        AsyncImage(
            user.picture.large,
            contentDescription = "User image",
            modifier = Modifier
                .size(105.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = user.name.toString(),
                color = Color(0xFF000000),
                fontSize = 20.sp
            )
            Text(
                text = user.cell,
                color = Color(0xFFA4A4A4),
                fontSize = 16.sp
            )
            Text(
                text = user.location.toString(),
                color = Color(0xFFA4A4A4),
                fontSize = 16.sp
            )
        }
    }
}
