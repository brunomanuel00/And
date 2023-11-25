package com.mr_bruno0012.optativaandroid

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr_bruno0012.optativaandroid.data.remote.dto.PostResponse
import com.mr_bruno0012.optativaandroid.data.remote.dto.PostsService
import com.mr_bruno0012.optativaandroid.ui.theme.OptativaAndroidTheme
import androidx.compose.runtime.produceState as produceState


class MainActivity : ComponentActivity() {

    private val service = PostsService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val posts  = produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getPosts()

                }
            )
            OptativaAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    LazyColumn(){
                        items(posts.value){
                            Column (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ){
                                Text(text = it.title, fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.body, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    OptativaAndroidTheme {
//        Greeting("Android")
//    }
//}