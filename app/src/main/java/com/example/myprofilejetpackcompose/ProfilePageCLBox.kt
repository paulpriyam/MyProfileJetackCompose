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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@Composable
fun ProfilePageConstraintLayoutWithBox() {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 16.dp, bottom = 50.dp, end = 16.dp)
    ) {
        BoxWithConstraints {
            val constraints = if (minWidth < 600.dp) {
                portraitConstraint(16.dp)
            } else {
                landScapeConstraint(16.dp)
            }
            ConstraintLayout(constraints) {

                Image(
                    painter = painterResource(id = R.drawable.ic_profile_pic),
                    contentDescription = "profilePic",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(width = 2.dp, color = Color.Green, shape = CircleShape)
                        .layoutId("image"),
                    contentScale = ContentScale.Crop,

                    )

                Text(
                    text = "MyProfile",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(0.dp, 16.dp, 0.dp, 0.dp)
                        .layoutId("name")
                )
                Text(
                    text = "India",
                    modifier = Modifier.layoutId("place")
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("rowStats")
                ) {
                    ProfilePageStats("150", "Followers")
                    ProfilePageStats("100", "Followings")
                    ProfilePageStats("10", "Posts")
                }

                Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("btnFollow")) {
                    Text(text = "Follow")
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("btnMessage")) {
                    Text(text = "Direct Message")
                }
            }

        }
    }
}

private fun portraitConstraint(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val name = createRefFor("name")
        val place = createRefFor("place")
        val rowStat = createRefFor("rowStats")
        val btnFollow = createRefFor("btnFollow")
        val btnMessage = createRefFor("btnMessage")
        val guideline = createGuidelineFromTop(0.3f)
        constrain(image) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(name) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(place) {
            top.linkTo(name.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowStat) {
            top.linkTo(place.bottom, margin = margin)
        }
        constrain(btnFollow) {
            top.linkTo(rowStat.bottom)
            start.linkTo(parent.start)
            end.linkTo(btnMessage.start)
        }
        constrain(btnMessage) {
            top.linkTo(rowStat.bottom)
            start.linkTo(btnFollow.end)
            end.linkTo(parent.end)
        }
    }
}

private fun landScapeConstraint(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val name = createRefFor("name")
        val place = createRefFor("place")
        val rowStat = createRefFor("rowStats")
        val btnFollow = createRefFor("btnFollow")
        val btnMessage = createRefFor("btnMessage")
        val guideline = createRefFor("guideline")
        constrain(image) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(name) {
            top.linkTo(parent.top)
            start.linkTo(image.end)
            end.linkTo(parent.end)
        }
        constrain(place) {
            top.linkTo(name.bottom)
            start.linkTo(image.end)
            end.linkTo(parent.end)
        }
        constrain(rowStat) {
            top.linkTo(place.bottom)
            start.linkTo(image.end)
            end.linkTo(parent.end)
        }
        constrain(btnFollow) {
            top.linkTo(rowStat.bottom)
            start.linkTo(image.end)
            end.linkTo(btnMessage.start)
        }
        constrain(btnMessage) {
            top.linkTo(rowStat.bottom)
            start.linkTo(btnFollow.end)
            end.linkTo(parent.end)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePageConstraintLayoutWithBoxPreview() {
    ProfilePageConstraintLayoutWithBox()
}