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
        setContent {
            Shop(this,money,clickval)
        }
    }
}

@Composable
fun Shop(activity : Activity, money : MutableState<Int>, clickval : MutableState<Int>)
{
    var context = LocalContext.current
    var clickCost by remember { mutableStateOf(100)}

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
            Text(text = stringResource(id = R.string.money)+"：${MainActivity.money.value}")
            Text(text = stringResource(id = R.string.current_per_click)+"\n${MainActivity.clickval.value}",
                fontSize = 40.sp,
                textAlign = TextAlign.Center)


            Row()
            {
                Button(
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Cyan),
                    onClick = {Buy(context,2,clickCost,money)},
                    modifier = Modifier.size(width = 400.dp, height = 300.dp)
                )
                {
                    Text(text = stringResource(id = R.string.add_per_click) + "${clickCost}")
                }
            }

            Button(
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Gray),
                onClick = {Back(activity,money,clickval)},
                modifier = Modifier.size(width = 400.dp, height = 180.dp)
            )
            {
                Text(text = stringResource(id = R.string.button))
            }
        }
    }
}

fun Buy(context : Context, type : Int, clickCost : Int,money : MutableState<Int>)
{
    if(type == 2 && MainActivity.money.value > clickCost) {
        MainActivity.money.value -= clickCost
        MainActivity.clickval.value += 1;
    }
}

fun Back(activity : Activity, money : MutableState<Int>, clickval : MutableState<Int>)
{
    activity.finish()
}