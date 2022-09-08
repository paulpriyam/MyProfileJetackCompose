package com.example.myprofilejetpackcompose

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Expand
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.example.myprofilejetpackcompose.ui.theme.MyProfileJetpackComposeTheme

@Composable
fun Demo1() {
    MyProfileJetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 4.dp)
            ) {
//        var shouldShowOnBoarding by remember { mutableStateOf(true) }
                /**
                 * This will survive configuration changes.
                 */
                var shouldShowOnBoarding by rememberSaveable { mutableStateOf(false) }
                if (shouldShowOnBoarding)
                    onBoardingScreen(onContinueClick = { shouldShowOnBoarding = false })
                else
                    MyList()
            }
        }
    }


}

@Composable
fun MyList(nameList: List<String> = List(1000) { "$it" }) {
//    Column(modifier = Modifier.padding(vertical = 4.dp)) {
//        for (name in nameList)
//            eachItem(name = name)
//    }
    /**
     * This is similar to recycler view
     */
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = nameList) { name ->
            eachItem(name = name)
        }
    }
}

@Composable
fun eachItem(name: String) {
    Surface(
        modifier = Modifier.padding(4.dp),
        color = MaterialTheme.colors.primary
    ) {
        var expanded by remember { mutableStateOf(false) }
//        val extraPadding by animateDpAsState(
//            if (expanded) 48.dp else 0.dp,
//            animationSpec = spring(
//                dampingRatio = Spring.DampingRatioMediumBouncy,
//                stiffness = Spring.StiffnessLow
//            )
//        )
        Row(modifier = Modifier.padding(20.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
            ) {
                Text(text = "Hello")
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                if (expanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4)
                    )
                }
            }
            IconButton(onClick = {
                expanded = !expanded
            }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) stringResource(id = R.string.show_less) else stringResource(
                        id = R.string.show_more
                    )
                )
            }
        }
    }
}

@Composable
fun onBoardingScreen(onContinueClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome to OnBoarding Screen")
        Button(onClick = onContinueClick, modifier = Modifier.padding(top = 20.dp)) {
            Text(text = "Continue")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun onBoardingScreenPreview() {
    onBoardingScreen(onContinueClick = {})
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DEMO_PREVIEW_DARK"
)
@Preview(showBackground = true)
@Composable
fun DemoPreview() {
    Demo1()
}