package com.example.dotacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dotacompose.ui.theme.DotaComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewMessageCard()
        }
    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        DotaComposeTheme {
            val list = mutableListOf<Obj>()
            for (i in 0..10) {
                list.add(Obj("Макс", "Сукаловский"))
            }
            Conversation(list)
        }
    }

    @Composable
    fun Conversation(messages: List<Obj>) {
        LazyColumn {
            items(messages) {
                MessageCard(message = it)
            }
        }
    }

    data class Obj(val name: String, val secondName: String)

    @Composable
    fun MessageCard(message: Obj) {
        var isExpanded by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 5.dp)
                .clickable { isExpanded = !isExpanded }
                .border(if (!isExpanded) 2.dp else 0.dp, MaterialTheme.colors.error),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .padding(horizontal = 5.dp, vertical = 5.dp)
                    .border(if (isExpanded) 1.5.dp else 0.dp, MaterialTheme.colors.secondary, CircleShape),
                painter = painterResource(id = R.drawable.img),
                contentDescription = "11111",
            )
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
            ) {
                Text(text = message.name, color = MaterialTheme.colors.secondaryVariant)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = message.secondName)
            }
        }
    }

}