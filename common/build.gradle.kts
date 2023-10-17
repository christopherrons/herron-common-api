dependencies {
    // External Libs
    implementation(libs.spring.boot.starter.web)
    implementation(libs.commons.math)
    implementation(libs.spring.kafka)
    implementation(libs.immutables.value)
    annotationProcessor(libs.immutables.value)
//    implementation(libs.jackson.datatype)

    // External Test Libs
    testImplementation(testlibs.junit.jupiter.api)
    testImplementation(testlibs.junit.jupiter.engine)
    testImplementation(testlibs.spring.boot.starter.test)
    testImplementation(testlibs.podam.random)
}

tasks.test {
    useJUnitPlatform()
}