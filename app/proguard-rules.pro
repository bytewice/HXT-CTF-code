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

#############################################
#       OFUSCAÇÃO AVANÇADA - R8/PROGUARD   #
#############################################

# Remove logs (console & Android Log)
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}

# Remove Java assertions
-assumenosideeffects class java.lang.Class {
    public boolean desiredAssertionStatus();
}

# Remove anotações desnecessárias
-keepattributes *Annotation*

# Oculta nomes de classes internas e métodos sintetizados
-renamesourcefileattribute X
-keepattributes SourceFile,LineNumberTable

# Remove debug, metadata e info extra
-dontpreverify
-dontnote
-dontwarn

#############################################
#       OFUSCAÇÃO MÁXIMA DE CÓDIGO          #
#############################################

# Renomeia TODAS as classes, métodos e campos
-obfuscationdictionary obf_dict.txt
-classobfuscationdictionary obf_dict.txt
-packageobfuscationdictionary obf_dict.txt

# Mantém apenas o necessário para o Android funcionar
-keep class androidx.** { *; }
-keep class com.google.** { *; }
-keep class android.support.** { *; }
-keep class android.** { *; }

# Mantém Activities (sem isso o app quebra)
-keep class * extends android.app.Activity {
    public *;
}

# Mantém serviços
-keep class * extends android.app.Service {
    public *;
}

# Mantém BroadcastReceivers
-keep class * extends android.content.BroadcastReceiver {
    public *;
}

# Mantém ContentProviders
-keep class * extends android.content.ContentProvider {
    public *;
}

# Mantém classes usadas por XML/Manifest
-keepclassmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#############################################
#       CONTROLE DE FLUXO & OTIMIZAÇÃO      #
#############################################

# Permite ao R8 reescrever blocos internos de código
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
