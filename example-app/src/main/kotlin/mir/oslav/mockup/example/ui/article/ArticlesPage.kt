@file:OptIn(ExperimentalLayoutApi::class)

package mir.oslav.mockup.example.ui.article

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import mir.oslav.mockup.Mockup
import mir.oslav.mockup.example.Article
import mir.oslav.mockup.example.ui.Photo
import java.util.Locale


/**
 * @author Miroslav Hýbler <br>
 * created on 18.09.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen(
    navHostController: NavHostController,
    articles: List<Article> = Mockup.article.list,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Articles") },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            content = {
                itemsIndexed(items = articles) { index, article ->
                    ArticleItem(
                        article = article,
                        navHostController = navHostController
                    )
                }
            },
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize(),
        )
    }
}


@Composable
private fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    navHostController: NavHostController
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    navHostController.navigate(
                        route = "article/${article.id}"
                    )
                }
            )
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 228.dp)
        ) {
            Photo(
                imageUrl = article.imageUrl,
                modifier = Modifier
                    .matchParentSize()
                    .clip(shape = RoundedCornerShape(size = 16.dp))
            )

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, end = 12.dp)
                    .align(alignment = Alignment.TopEnd),
                horizontalArrangement = Arrangement.End
            ) {
                article.categories.forEach { category ->
                    Text(
                        text = category.formattedName,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .padding(all = 2.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .padding(vertical = 4.dp, horizontal = 4.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }


            Text(
                text = remember(key1 = article) {
                    article.createdAtFormatted
                },
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 4.dp)
                    .align(alignment = Alignment.BottomStart)
                    .background(
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
        }


        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = article.author.fullName,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "rating: ${String.format(Locale.getDefault(), "%.2f", article.rating)}/5",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ArticleScreenPreview() {
    ArticlesScreen(
        navHostController = rememberNavController(),
        articles = Mockup.article.list,
    )
}