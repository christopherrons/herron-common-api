dependencies {
    // External Libs
    implementation(libs.spring.boot.starter.web)
    implementation(libs.javax.json.api)
    implementation(libs.javax.json)
    implementation(libs.commons.math)

    // External Test Libs
    testImplementation(testlibs.junit.jupiter.api)
    testImplementation(testlibs.junit.jupiter.engine)
    testImplementation(testlibs.spring.boot.starter.test)
    testImplementation(testlibs.podam.random)
}

tasks.test {
    useJUnitPlatform()
}