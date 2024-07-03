package com.example.auto_data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.auto_data.R

data class NewsItem(val title: String, val description: String, val imageRes: Int)

class NewsScreenViewModel : ViewModel() {
    val newsItems = listOf(
        NewsItem("Tesla releases new model", "The new Tesla Model Z is faster, more efficient, and packed with cutting-edge technology.", R.drawable.tesla_news),
        NewsItem("Ford announces electric Mustang", "Ford's new Mustang EV promises high performance and zero emissions.", R.drawable.mustang_news),
        NewsItem("BMW introduces self-driving feature", "BMW's latest software update includes a fully autonomous driving mode.", R.drawable.bmw_news)
    )
}
