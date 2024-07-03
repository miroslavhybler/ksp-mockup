# ksp-mockup
Ksp mockup is simple ksp ([Kotlin Symbol Processing](https://kotlinlang.org/docs/ksp-overview.html#supported-libraries)) 
library for generating fake mockup data. These are handy  and convenient to be used in @Preview 
functions in Jetpack Compose applications. Instead of creating @PreviewParameterProvider just get 
mockup data with Mockup object.

## Add Library
Add maven repository and library dependency to your app's gradle files. Make sure to add ksp plugin too.

**Project's build.gradle.ksp**
```kotlin
// Project's build.gradle.kts make sure to keep compatible ksp version with your kotlin version 
plugins {
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
}
```

**Project's settings.gradle.kts**
```kotlin
// Adds maven 
dependencyResolutionManagement {
    repositories {
        maven(url="https://jitpack.io")
    }
}
```

**Application's module build.gradle.kts**
```kotlin
// Apply ksp plugin
plugins {
    id("com.google.devtools.ksp")
}


dependencies {
    //Always use the same version for annotations and processor
    val mockupVersion= "1.1.6"
    implementation("com.github.miroslavhybler:ksp-mockup-annotations:$mockupVersion")
    //use kspDebug since mockup is meant to be only for compose preview in debug mode
    kspDebug("com.github.miroslavhybler:kps-mockup-processor:$mockupVersion")
}
```

### Usage
Annotate desired classes with @Mockup and that's it

```kotlin
@Mockup
data class User constructor(
    val id: Int,
    val firstName: String,
    val lastName: String,
)
```

Build your project and access generated data through generated Mockup object.

```kotlin
//accessing same single user instance
val user = Mockup.user.single

//Getting list of items
val usersList = Mockup.user.list

//Getting random user instance from the list
val userRandom = Mockup.user.random
```


### Supported types
- Simple kotlin types like Int, Long, ...
- String which will contain "Lorem ipsum..." text
- List (kotlin.List), but only when list contains simple type or @Mockup annotated classes, other types won't work
- Arrays with known type (like IntArray, FloatArray, ...) are generated empty
- Classes annotated with @Mockup annotation


### Limitations 
- Not applicable to to java classes and java types
- Any other types that are not specified in [Supported types](#Supported-types) are **not** supported and code generation will fail.
- Generated data are **not** aggregated between each other! Don't try to access data related on id's or something.

### Used resources
🏞 Images for previews taken from [Pixabay](https://www.pixabay.com/).