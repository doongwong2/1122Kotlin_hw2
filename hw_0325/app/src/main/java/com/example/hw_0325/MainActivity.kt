package com.example.hw_0325

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hw_0325.MainActivity.Companion.autoval
import com.example.hw_0325.MainActivity.Companion.clickval
import com.example.hw_0325.MainActivity.Companion.money

class MainActivity : ComponentActivity() {

    companion object{
        var clickval = mutableStateOf(1)
        var autoval = mutableStateOf(0)
        var money = mutableStateOf(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                setContent{
                    Activity1()
            }
        }
    }

@Preview
@Composable
fun Activity1()
{
    var context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = "${MainActivity.money.value}",
                fontSize = 100.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())

            Text(text = stringResource(id = R.string.clickval) + ": ${MainActivity.clickval.value}")
            Text(text = stringResource(id = R.string.autoval) + ": ${MainActivity.autoval.value}")
            Button(
                shape = RectangleShape,
                onClick = {BtnFun(context)},
                modifier = Modifier
                    .size(width = 400.dp,height = 500.dp))
            {
                Text(text = stringResource(R.string.click))
            }
            Button(
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Gray),
                onClick = {BtnFunScene(context)},
                modifier = Modifier.size(width = 400.dp, height = 180.dp)
            )
            {
                Text(text = stringResource(id = R.string.button))
            }
        }
    }

}

fun BtnFun(context : Context)
{
    MainActivity.money.value += MainActivity.clickval.value
    Log.d("a","money is ${MainActivity.money}")
}

fun BtnFunScene(context : Context)
{
    val intent = Intent()
    intent.setClassName(context,"com.example.hw_0325.MainActivity2")
    intent.putExtra("money",MainActivity.money.value)
    intent.putExtra("clickval",MainActivity.clickval.value)
    intent.putExtra("autoval",MainActivity.autoval.value)
    context.startActivity(intent)
}