plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "br.com.fiap.journey"
    compileSdk = 36

    defaultConfig {
        applicationId = "br.com.fiap.journey"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.crashlytics.buildtools)
    implementation(libs.androidx.ui.text)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.compose.material:material-icons-extended")

    implementation(libs.androidx.room.runtime) // O runtime é de fato a biblioteca do Room. Possui todas as classes necessárias para fazer a persistência de dados
    annotationProcessor(libs.androidx.room.compiler) // Vai fazer a compilação de anotações das classes
    ksp(libs.androidx.room.compiler)// Permite que o kotlin trabalhe com recursos de anotações.

 

    // Retrofit (Conexão com API)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Conversor para JSON (para enviar o objeto {"msg": ...})
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // Conversor para Texto (porque sua API retorna texto puro e não JSON na resposta)
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    // Corrotinas (para não travar o app enquanto espera a resposta)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")


}