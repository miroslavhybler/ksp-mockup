package mir.oslav.mockup.processor.data


/**
 * Holds temporary data for property which will be included in generated Mockup.kt object.
 * @param propertyName Name of the property.
 * @param providerClassName Class name (type) of mockup data provider providing data for [propertyName].
 * @param providerClassPackage Package name of generated provider.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 18.09.2023
 */
data class MockupObjectMember constructor(
    val propertyName: String,
    val providerClassName: String,
    val providerClassPackage: String
) {



}