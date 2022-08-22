package com.example.myprofilejetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun ProfilePageConstraintLayout() {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, start = 16.dp, bottom = 100.dp, end = 16.dp)
    ) {
        ConstraintLayout {
            val (image, name, place, rowStats, btnFollow, btnMessage) = createRefs()
            val guideline = createGuidelineFromTop(0.2f)

            Image(
                painter = painterResource(id = R.drawable.ic_profile_pic),
                contentDescription = "profilePic",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.Green, shape = CircleShape)
                    .constrainAs(image) {
                        top.linkTo(guideline)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop,

                )

            Text(
                text = "MyProfile",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(0.dp, 16.dp, 0.dp, 0.dp)
                    .constrainAs(name) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(text = "India",
                modifier = Modifier.constrainAs(place) {
                    top.linkTo(name.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .constrainAs(rowStats) {
                        top.linkTo(place.bottom, margin = 16.dp)
                    }) {
                ProfilePageStats("150", "Followers")
                ProfilePageStats("100", "Followings")
                ProfilePageStats("10", "Posts")
            }

            Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(btnFollow) {
                top.linkTo(rowStats.bottom)
                start.linkTo(parent.start)
                end.linkTo(btnMessage.start)
            }) {
                Text(text = "Follow")
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(btnMessage) {
                top.linkTo(rowStats.bottom)
                start.linkTo(btnFollow.end)
                end.linkTo(parent.end)
            }) {
                Text(text = "Direct Message")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePageConstraintLayoutPreview() {
    ProfilePageConstraintLayout()
}