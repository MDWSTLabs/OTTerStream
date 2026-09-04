# ProGuard rules for OTTerStream

# Keep all classes in our app package
-keep class com.mdwst.otterstream.** { *; }

# Kotlin metadata
-keepclassmembers class ** {
    *** Companion;
}
-keep class kotlin.Metadata { *; }

# Retrofit
-keepattributes Signature
-keepattributes *Annotation*
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# OkHttp
-keep class okhttp3.** { *; }
-keep class okhttp3.internal.** { *; }
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn okhttp3.**

# Kotlinx Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class ** {
    *** Companion;
}
-keepclasseswithmembers class * {
    @kotlinx.serialization.Serializable <methods>;
}

# Room
-keep class androidx.room.** { *; }
-keepclassmembers class * extends androidx.room.RoomDatabase { *; }

# Hilt / Dagger
-keep class dagger.** { *; }
-keep class com.google.dagger.** { *; }
-keep class * extends com.google.dagger.internal.Factory
-keep class * extends com.google.dagger.internal.Binding
-keepclasseswithmembers class * {
    @dagger.* <methods>;
}
-keepclasseswithmembers class * {
    @javax.inject.* <methods>;
}

# Jetpack Compose
-keep class androidx.compose.** { *; }

# Media3
-keep class androidx.media3.** { *; }

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Remove logging in release
-assumenosideeffects class timber.log.Timber {
    public static *** d(...);
    public static *** i(...);
    public static *** v(...);
}

# Keep model classes
-keepclassmembers class com.mdwst.otterstream.data.models.** {
    <init>();
    <fields>;
}

# Preserve line numbers for debugging
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
