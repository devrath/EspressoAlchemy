plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.istudio.power_mock"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.istudio.power_mock"
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    configurations {
        all {
            exclude(group = "org.objenesis", module = "objenesis")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation("org.mockito:mockito-core:3.6.0")
    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-core:2.15.0")
    testImplementation("io.kotlintest:kotlintest:2.0.7")
    testImplementation("org.powermock:powermock-module-junit4-rule:2.0.0-beta.5")
    testImplementation("org.powermock:powermock-core:2.0.0-beta.5")
    testImplementation("org.powermock:powermock-module-junit4:2.0.0-beta.5")
    testImplementation("org.powermock:powermock-api-mockito2:2.0.0-beta.5")
}