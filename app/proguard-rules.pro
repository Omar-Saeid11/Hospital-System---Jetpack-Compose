# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Keep classes for Hilt (Dependency Injection)
-keep class dagger.hilt.** { *; }
-dontwarn dagger.hilt.**
-keep class javax.annotation.** { *; }

# Keep classes used by the Android system
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Retrofit (keep API interfaces and models)
-keep class retrofit2.** { *; }
-keep interface retrofit2.http.** { *; }

# Gson (keep model classes)
-keep class com.example.hospitalsystem.model.** { *; }

# Keep Room entities and DAOs
-keep class androidx.room.RoomDatabase { *; }
-keep class androidx.room.RoomOpenHelper { *; }
-keep class * implements androidx.room.RoomDatabase$Callback { *; }
-keep @androidx.room.Dao class * { *; }
-keep @androidx.room.Entity class * { *; }

# Keep data classes for Parcelable (if using Parcelable)
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep Lottie animations (if using Lottie)
-keep class com.airbnb.lottie.** { *; }

# Remove debugging information to further obfuscate the app
-assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** d(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}

# Strip out any non-essential debugging info (e.g., verbose logs)
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
}

# Optimization passes to further shrink code
-optimizationpasses 5

# Keep all classes and methods related to Compose
-keep class androidx.compose.** { *; }
