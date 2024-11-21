#include <jni.h>
#include <string>
#include <jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_bsuir_1schedule_1app_activities_MainActivity_stringFromJNI(JNIEnv* env, jobject /* this */) {
    std::string message = "Успешный вход в систему!";
    return env->NewStringUTF(message.c_str());
}