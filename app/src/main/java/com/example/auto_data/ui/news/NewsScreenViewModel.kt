package com.example.auto_data.ui.news

import androidx.lifecycle.ViewModel
import com.example.auto_data.R

data class NewsItem(
    val title: String,
    val description: String,
    val imageRes: Int,
    val link: String = ""
)

class NewsScreenViewModel : ViewModel() {
    val newsItems = listOf(
        NewsItem(
            "Tesla releases new model",
            "The new Tesla Model is faster, more efficient, and packed with cutting-edge technology.",
            R.drawable.tesla_news,
            "https://www.tesla.com/blog/introducing-new-model-3-performance"
        ),
        NewsItem(
            "Ford announces electric Mustang",
            "Ford's new Mustang EV promises high performance and zero emissions.",
            R.drawable.mustang_news,
            "https://www.drive.com.au/news/2024-ford-mustang-mach-e-confirmed-australia/"
        ),
        NewsItem(
            "BMW introduces self-driving feature",
            "BMW's latest software update includes a fully autonomous driving mode.",
            R.drawable.bmw_news,
            "https://www.press.bmwgroup.com/global/article/detail/T0438214EN/level-3-highly-automated-driving-available-in-the-new-bmw-7-series-from-next-spring?language=en"
        )
    )
}
