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

//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    testInstrumentationRunner = "br.com.devmeist3r.HiltTestRunner"

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
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
  implementation("androidx.activity:activity-compose:1.8.2")
  implementation(platform("androidx.compose:compose-bom:2024.03.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")
  implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0")

  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2024.03.00"))
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
  implementation("androidx.compose.material:material:1.6.4")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
  implementation("androidx.compose.material:material-icons-extended:1.6.4")
  implementation("com.google.accompanist:accompanist-flowlayout:0.17.0")
  implementation("androidx.navigation:navigation-compose:2.7.7")

  // Paging3
  implementation("androidx.paging:paging-runtime-ktx:3.2.1")
  implementation("androidx.paging:paging-compose:3.3.0-alpha05")

  // Coroutines
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

  // Coroutine Lifecycle Scopes
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

  // Retrofit
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
  implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

  //DI - Hilt
  implementation("com.google.dagger:hilt-android:2.49")
  implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
  kapt("com.google.dagger:hilt-compiler:2.48.1")
  kapt("androidx.hilt:hilt-compiler:1.2.0")

  //Room
  implementation("androidx.room:room-ktx:2.6.1")
  implementation("androidx.room:room-runtime:2.6.1")
  kapt("androidx.room:room-compiler:2.6.1")

  testImplementation("junit:junit:4.13.2")

  //truth
  implementation("com.google.truth:truth:1.4.0")

  //Javax Inject
  implementation("javax.inject:javax.inject:1")

  //Hilt
  kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.48.1")

  androidTestImplementation("com.google.dagger:hilt-android-testing:2.48")

  // Unit tests
  testImplementation("junit:junit:4.13.2")
  testImplementation("androidx.arch.core:core-testing:2.2.0")
  /**/testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
  /**/testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
  testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
  testImplementation("org.mockito:mockito-inline:5.2.0")
  testImplementation("androidx.room:room-testing:2.6.1")
  testImplementation("androidx.paging:paging-common:3.2.1")

  // Instrumentation tests
  androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
  androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
  androidTestImplementation("com.google.truth:truth:1.4.0")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

  androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.4")
  debugImplementation("androidx.compose.ui:ui-tooling:1.6.4")
  debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.4")
}
