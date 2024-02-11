
import Versions.coil
import Versions.coil_accomp
import Versions.hilt_nav
import Versions.lifecycle_version

object Versions{
        const val minSdk = 24
         const val compileSdk = 34
        const val  corektx = "1.8.0"
        const val  appcomat = "1.6.1"
        const val  android_lifecyle = "2.3.1"
        const val  activity_compose = "1.5.1"
        const val  kotlin_compiler_ext = "1.3.2"
        const val  junit = "4.13.2"
        const val  junit_test = "1.1.5"
        const val  espresso_core = "3.5.1"
        const val  retrofit = "2.9.0"
        const val  okhttp = "5.0.0-alpha.11"
        const val  gson = "2.10.1"
        const val  dagger_hilt = "2.42"
        const val  compose_nav = "2.4.0-alpha10"
    const val lifecycle_version = "2.7.0"
    const val arch_version = "2.1.0"
    const val coroutine = "1.6.4"
    const val coroutine_core= "1.6.1"
    const val glide= "4.15.1"
    const val coil= "2.5.0"
    const val coil_accomp= "0.19.0"
    const val hilt_nav= "1.1.0"
    const val material= "1.11.0"

}
//Android dependencies
object Deps{
    const val  core = "androidx.core:core-ktx:${Versions.corektx}"
   const val  appcompat = "androidx.appcompat:appcompat:${Versions.appcomat}"
    const val  kotlin_bom = "org.jetbrains.kotlin:kotlin-bom:${Versions.corektx}"
    const val  android_lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.android_lifecyle}"
    const val  junit = "junit:junit:${Versions.junit}"
    const val  junit_test = "androidx.test.ext:junit:${Versions.junit_test}"
    const val  espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    const val  material = "com.google.android.material:material:${Versions.material}"
}
//Compose dependencies
object Compose{
    const val  activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose}"
    const val  bom = "androidx.compose:compose-bom:2022.10.00"
    const val  ui = "androidx.compose.ui:ui"
    const val  ui_test = "androidx.compose.ui:ui-test-junit4"
    const val  ui_graphics = "androidx.compose.ui:ui-graphics"
    const val  tooling_preview = "androidx.compose.ui:ui-tooling-preview"
    const val  material3 = "androidx.compose.material3:material3"
    const val  tooling = "androidx.compose.ui:ui-tooling"

    const val  test_manifest = "androidx.compose.ui:ui-test-manifest"
    const val  navGraph = "androidx.navigation:navigation-compose:${Versions.compose_nav}"
}

object Dagger{
    const val  dagger = "com.google.dagger:hilt-android:${Versions.dagger_hilt}"
    const val  dagger_compiler= "com.google.dagger:hilt-compiler:${Versions.dagger_hilt}"
    const val  hilt_navigation= "androidx.hilt:hilt-navigation-compose:$hilt_nav"
}
object Retrofit{
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    }
object Glide{
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}
object ColiImage{
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    const val coil_accomp = "com.google.accompanist:accompanist-coil:${Versions.coil_accomp}"

}
object  Coroutines{
    const val coroutine = ("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}")
    const val  coroutine_core = ("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine_core}")
}
object Viewmodel{
    // ViewModel
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle_version}"
    // ViewModel utilities for Compose
    const val viewmodel_compose = "androidx.lifecycle:lifecycle-viewmodel-compose:${lifecycle_version}"
    // LiveData
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    // Lifecycles only (without ViewModel or LiveData)
    const val lifecycle_runtime = ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    // Saved state module for ViewModel
    const val savedstate = ("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

    // Annotation processor
    const val compiler = ("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
    // alternately - if using Java8, use the following instead of lifecycle-compiler
    const val common = ("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")

    // optional - helpers for implementing LifecycleOwner in a Service
    const val service = ("androidx.lifecycle:lifecycle-service:$lifecycle_version")

    // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
    const val process = ("androidx.lifecycle:lifecycle-process:$lifecycle_version")

    // optional - ReactiveStreams support for LiveData
    const val reactivestreams = ("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version")

    // optional - Test helpers for LiveData
    const val testing = ("androidx.arch.core:core-testing:2.2.0")

    // optional - Test helpers for Lifecycle runtime
    const val runtime_testing = ("androidx.lifecycle:lifecycle-runtime-testing:$lifecycle_version")


}