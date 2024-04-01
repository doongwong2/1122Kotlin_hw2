package com.example.hw_0325

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue


class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var money = mutableStateOf(intent.getIntExtra("money",0))
        var clickval = mutableStateOf(intent.getIntExtra("clickval",1))
        var autoval = mutableStateOf(intent.getIntExtra("autoval",0))
        setContent {
            Shop(this,money,clickval,autoval)
        }
    }
}

@Composable
fun Shop(activity : Activity, money : MutableState<Int>, clickval : MutableState<Int>, autoval : MutableState<Int>)
{
    var context = LocalContext.current
    var autoCost by remember { mutableStateOf(1)}
    var clickCost by remember { mutableStateOf(1)}

    for ( i in 1.. clickval.value)
    {
        autoCost *= 2
    }
    for ( i in 1.. autoval.value)
    {
        clickCost *= 2
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = "money: ${money.value}")
            Text(text = "current auto is:",
                fontSize = 40.sp,
                textAlign = TextAlign.Center)
            Text(text = "current per click is:",
                fontSize = 40.sp,
                textAlign = TextAlign.Center)


            Row()
            {
                Button(
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    onClick = {Buy(context,1,autoCost,clickCost,money)},
                    modifier = Modifier.size(width = 200.dp, height = 300.dp)
                )
                {
                    Text(text = "add auto for ${autoCost}")
                }

                Button(
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Cyan),
                    onClick = {Buy(context,2,autoCost,clickCost,money)},
                    modifier = Modifier.size(width = 200.dp, height = 300.dp)
                )
                {
                    Text(text = "add per click for ${clickCost}")
                }
            }

            Button(
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Gray),
                onClick = {Back(activity,money,clickval,autoval)},
                modifier = Modifier.size(width = 400.dp, height = 180.dp)
            )
            {
                Text(text = stringResource(R.string.button))
            }
        }
    }
}

fun Buy(context : Context, type : Int, autoCost : Int, clickCost : Int,money : MutableState<Int>)
{
    if(type == 1 && money.value > autoCost) money.value  -= autoCost
    if(type == 2 && money.value > clickCost) money.value -= clickCost
    autoCost *= 2
    clickCost *= 2
}

fun Back(activity : Activity, money : MutableState<Int>, clickval : MutableState<Int>, autoval : MutableState<Int>)
{
    MainActivity.money.value = money.value
    MainActivity.autoval.value = autoval.value
    MainActivity.clickval.value = clickval.value

    activity.finish()
}