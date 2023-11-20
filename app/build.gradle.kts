plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.onelifefoundation_admin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.onelifefoundation_admin"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17)) // or any other version you want
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.9.1")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    val dagger = "2.47"
    val hiltComposeNavigation = "1.0.0"
    var navigation = "2.6.0"
    var material3_version = "1.0.0-alpha01"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //dagger
    implementation("com.google.dagger:hilt-android:$dagger")
    ksp("com.google.dagger:hilt-compiler:$dagger")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltComposeNavigation")


    //material 3
    implementation("androidx.compose.material3:material3:$material3_version")
    //compose navigation
    implementation("androidx.navigation:navigation-compose:$navigation")
    //icons
    implementation("androidx.compose.material:material-icons-extended:1.4.0")
    //coil image
    implementation("io.coil-kt:coil-compose:2.4.0")

    //accompainist and lotties file
    val accompanist_version="0.24.2-alpha"
    implementation ("com.google.accompanist:accompanist-pager:$accompanist_version")
    implementation ("com.google.accompanist:accompanist-pager-indicators:$accompanist_version")

    implementation ("com.airbnb.android:lottie-compose:5.2.0")
    //splash
    implementation ("androidx.core:core-splashscreen:1.0.0-beta01")


}