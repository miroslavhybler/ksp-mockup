package mir.oslav.mockup.example

import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.IntDef
import androidx.annotation.IntRange
import com.mockup.annotations.IgnoreOnMockup
import com.mockup.annotations.Mockup
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter


/**
 * @param rating Example usage of FloatRange annotation
 * @param readers Example usage of IntRange annotation, number of people who read the article
 * @author Miroslav Hýbler <br>
 * created on 15.09.2023
 */
@Mockup
data class Article constructor(
    val id: Int,
    val title: String,
    val content: String,
    val author: Publisher,
    val tags: Array<String>,
    val categories: List<Category>,
    val isSpecialEdition: Boolean,
    val imageUrl: String,
    val gallery: List<GalleryPhoto>,
    val createdAt: String,
    @ArticleType
    val type: Int,
    @FloatRange(from = 0.0, to = 5.0)
    val rating: Float,
    @IntRange(from = 0, to = 10000)
    val readers: Int,
    val minutesReading: Short,
) {

    @IgnoreOnMockup
    val topReader: Reader? = null

    companion object {
        //You can set custom JodaTime format for the date generation in your app's build.gradle.kts
        //ksp {
        //  arg(k = "mockup-date-format", v = "yyyy-MM-dd")
        //}
        //https://www.joda.org/joda-time/key_format.html
        private val dateTimeFormat: DateTimeFormatter = DateTimeFormat.forPattern(
            "yyyy-MM-dd"
        )
    }

    val createdAtFormatted: String
        get() = dateTimeFormat.parseDateTime(createdAt).toString("MM. dd. yyyy")


    @IntDef(
        ArticleType.regular,
        ArticleType.silver,
        ArticleType.gold,
    )
    @Retention(AnnotationRetention.SOURCE)
    @Target(AnnotationTarget.PROPERTY)
    annotation class ArticleType {
        companion object {
            const val regular: Int = 0
            const val silver: Int = 1
            const val gold: Int = 2
        }
    }

    @Mockup
    data class GalleryPhoto constructor(
        val imageUrl: String
    ) {

    }
}

@Mockup
data class Category constructor(
    val id: Int,
    val name: String,
    @ColorInt
    val color: Int
) {

    val formattedName: String
        get() {
            return if (name.length > 11) name.substring(
                startIndex = 0,
                endIndex = 11
            ) else name
        }
}




@Mockup(count = 3)
class Publisher constructor() {
    var id: Int = 0
    var firstName: String = "John"
    var lastName: String = "Doe"
    var dateOfBirth: String = "01-01-1970"

    var description: String = ""
    var themeImageUrl: String? = null
    var avatarUrl: String? = null

    //TODO add rank

    val fullName: String get() = "$firstName $lastName"
}


@Mockup(count = 1)
class Reader constructor() {
    data class UserName constructor(
        val surname: String,
        val birthname: String
    ) {

    }
}