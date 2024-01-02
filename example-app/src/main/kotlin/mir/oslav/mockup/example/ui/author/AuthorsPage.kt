package mir.oslav.mockup.example.ui.author

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import mir.oslav.mockup.Mockup
import mir.oslav.mockup.example.Publisher
import mir.oslav.mockup.example.ui.Photo


/**
 * @author Miroslav Hýbler <br>
 * created on 18.09.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Mockup data can be used like that too, but it's not purpose
fun AuthorsScreen(
    navHostController: NavHostController,
    publishers: List<Publisher> = Mockup.publisher.list,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Authors") },
            )
        },
        content = { paddingValues ->
            LazyColumn(
                content = {
                    itemsIndexed(items = publishers) { index, user ->
                        AuthorItem(
                            publisher = user,
                            navHostController = navHostController
                        )
                    }
                },
                contentPadding = paddingValues,
                modifier = Modifier.fillMaxSize()
            )
        }
    )

}


@Composable
private fun AuthorItem(publisher: Publisher, navHostController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                navHostController.navigate(route = "author/${publisher.id}")
            })
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Photo(
            imageUrl = publisher.themeImageUrl,
            modifier = Modifier
                .size(size = 56.dp)
                .clip(shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(width = 12.dp))

        Column(modifier = Modifier.weight(weight = 1f)) {
            Text(
                text = publisher.fullName,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(height = 4.dp))
            Text(
                text = publisher.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
@Preview
private fun AuthorsScreenPreview() {
    //Preview for screen using Mockup instead of @PreviewParameterProvider
    AuthorsScreen(
        navHostController = rememberNavController(),
        publishers = Mockup.publisher.list,
    )
}