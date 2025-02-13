package com.guru.composecookbook.ui.templates.logins

import android.animation.ValueAnimator
import android.content.Context
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.LottieAnimationView
import com.guru.composecookbook.ui.components.HorizontalDottedProgressBar


@Preview
@Composable
fun LoginScreen1() {
    Scaffold {
        ScrollableColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(20.dp))
            LottieLoadingView(context = AmbientContext.current)
            Text(
                text = "Welcome Back",
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "We have missed you, Let's start by Sign In!",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            //TextField
            var email by remember { mutableStateOf(TextFieldValue("")) }
            var password by remember { mutableStateOf(TextFieldValue("")) }
            var hasError by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = email,
                leadingIcon = { Icon(imageVector = Icons.Default.Email) },
                maxLines = 1,
                isErrorValue = hasError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = "Email address") },
                placeholder = { Text(text = "abc@gmail.com") },
                onValueChange = {
                    email = it
                },
            )
            OutlinedTextField(
                value = password,
                leadingIcon = { Icon(imageVector = Icons.Default.VpnKey) },
                maxLines = 1,
                isErrorValue = hasError,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Password") },
                placeholder = { Text(text = "12334444") },
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {
                    password = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            var loading by remember { mutableStateOf(false) }
            Button(
                onClick = {
                    if (validateSignIn(email.text, password.text)) {
                        hasError = true
                        loading = false
                    } else {
                        loading = true
                        hasError = false
                     }
                  },
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp).height(50.dp)
                    .clip(CircleShape)
            ) {
                if (loading) {
                    HorizontalDottedProgressBar()
                } else {
                    Text(text = "Log In")
                }
            }
            Box(modifier = Modifier.padding(vertical = 16.dp)) {
                Spacer(
                    modifier = Modifier.align(Alignment.Center)
                        .height(1.dp).fillMaxWidth().background(Color.LightGray)
                )
                Text(
                    text = "Or use",
                    color = Color.LightGray,
                    modifier = Modifier.align(Alignment.Center)
                        .background(MaterialTheme.colors.background).padding(horizontal = 16.dp)
                )
            }

            OutlinedButton(onClick = { }, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Icon(imageVector = Icons.Default.Facebook)
                Text(
                    text = "Sign in with Facebook",
                    style = MaterialTheme.typography.h6.copy(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(onClick = { }, modifier = Modifier.fillMaxWidth().height(50.dp)) {
                Icon(imageVector = Icons.Default.Email)
                Text(
                    text = "Sign in with Gmail",
                    style = MaterialTheme.typography.h6.copy(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            val primaryColor = MaterialTheme.colors.primary
            val annotatedString = remember {
                AnnotatedString.Builder("Don't have an account? Register")
                    .apply {
                        addStyle(style = SpanStyle(color = primaryColor), 23, 31)
                    }
            }
            Text(
                text = annotatedString.toAnnotatedString(),
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                    .clickable(onClick = {}),
                textAlign = TextAlign.Center
            )
        }
    }
}

fun validateSignIn(email: String, password: String) = email.isNullOrBlank() || password.isNullOrBlank()


@Composable
fun LottieLoadingView(context: Context) {
    val lottieView = remember {
        LottieAnimationView(context).apply {
            setAnimation("working.json")
            repeatCount = ValueAnimator.INFINITE
        }
    }
    AndroidView({ lottieView }, modifier = Modifier.fillMaxWidth().height(250.dp)) {
        it.playAnimation()
    }

    // TODO Lottie compose not able to pick file somehow so using androidview

//    val lottieSpec = remember { LottieAnimationSpec.RawRes(R.raw.cryptoload)}
//    val lottieAnimationState =
//        rememberLottieAnimationState(autoPlay = true, repeatCount = Integer.MAX_VALUE)
//
//    LottieAnimation(
//        spec = lottieSpec,
//        modifier = Modifier.preferredSize(100.dp)
//    )
}