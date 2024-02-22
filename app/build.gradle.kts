import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

val apiKeyPropertiesFile = rootProject.file("apiKey.properties")
val apiKeyProperties = Properties()
apiKeyProperties.load(FileInputStream(apiKeyPropertiesFile))

android {
    namespace = "br.com.devmeist3r"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.devmeist3r"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", apiKeyProperties["API_KEY"] as String)
        buildConfigField("String", "BASE_URL", apiKeyProperties["BASE_URL"] as String)
        buildConfigField("String", "BASE_URL_IMAGE", apiKeyProperties["BASE_URL_IMAGE"] as String)

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    //Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Splashscreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Others - Compose dependencies
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    implementation("com.google.accompanist:accompanist-flowlayout:0.17.0")
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // Paging3
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    //DI - Hilt
    implementation ("com.google.dagger:hilt-android:2.48.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")
    kapt ("com.google.dagger:hilt-compiler:2.48.1")
    kapt ("androidx.hilt:hilt-compiler:1.1.0")

    //Room
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("androidx.room:room-runtime:2.6.0")
    kapt ("androidx.room:room-compiler:2.6.0")

    testImplementation("junit:junit:4.13.2")

  //truth

  implementation("com.google.truth:truth:1.1.3")
  androidTestImplementation("com.google.dagger:hilt-android-testing:2.45")
  kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.45")
  androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
  androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
  androidTestImplementation("com.google.truth:truth:1.1.3")

  // Dependência principal do Mockito
  testImplementation ("org.mockito:mockito-core:2.28.2")
  // Dependência do Mockito para testes no Android
  androidTestImplementation("org.mockito:mockito-android:2.28.2")

  // Dependência do Mockito para ser possível mockar classes e métodos constantes
  testImplementation("org.mockito:mockito-inline:2.28.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.6.2")
  debugImplementation("androidx.compose.ui:ui-tooling:1.6.2")
  debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.2")
}
