plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.suyo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.suyo"
        minSdk = 34
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.android.support:appcompat-v7")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:20.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.midtrans:uikit:2.0.0-SANDBOX")
//    implementation("com.midtrans:corekit:1.22.0")
//    implementation("com.midtrans:uikit:1.22.0")
//    implementation("com.midtrans:corekit:1.22.0")
//    implementation("com.midtrans:uikit:1.22.0")
//    implementation("com.midtrans:uikit:1.27.0-SANDBOX")

//    implementation("com.midtrans:uikit:1.27.0-SANDBOX")
//    implementation("com.midtrans:corekit:1.27.0")


}