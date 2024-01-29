plugins {
    id("com.android.application")
}


android {
    namespace = "com.ubaidxdev.videowallpaper"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ubaidxdev.videowallpaper"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    ndkVersion = "26.1.10909125"
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.media3:media3-ui:1.2.1")
    implementation("androidx.media3:media3-exoplayer:1.2.1")
    implementation("androidx.preference:preference:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.multidex:multidex:2.0.1")
//    implementation ("com.google.android.exoplayer:exoplayer:r2.19.1")
//    implementation(" com.google.android.exoplayer:exoplayer-core:r2.19.1")
//    implementation ("com.google.android.exoplayer:exoplayer-dash:r2.19.1")
//    implementation ("com.google.android.exoplayer:exoplayer-hls:r2.19.1")
//    implementation ("com.google.android.exoplayer:exoplayer-smoothstreaming:r2.19.1")
//    implementation ("com.google.android.exoplayer:exoplayer-ui:r2.19.1")

}