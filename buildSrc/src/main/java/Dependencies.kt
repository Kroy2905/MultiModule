object Versions{
        const val minSdk = "34"
         const val compileSdk = "34"
        const val  corektx = "1.8.0"
        const val  appcomat = "1.6.1"
        const val  android_lifecyle = "2.3.1"
        const val  activity_compose = "1.5.1"
        const val  junit = "4.13.2"
        const val  junit_test = "1.1.5"
        const val  espresso_core = "3.5.1"
        const val  retrofit = "2.9.0"
        const val  okhttp = "5.0.0-alpha.11"
        const val  gson = "2.10.1"
        const val  dagger_hilt = "2.44"
        const val  compose_nav = "2.4.0-alpha10"

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
}
object Retrofit{
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    }