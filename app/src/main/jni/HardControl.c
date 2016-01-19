#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <jni.h>  /* /usr/lib/jvm/java-1.7.0-openjdk-amd64/include/ */

#include <android/log.h> /*liblog*/
 
//__android_log_print(ANDROID_LOG_DEBUG,"","");

#if 0
typedef struct {
    char *name;          /* Java����õĺ����� */
    char *signature;    /* JNI�ֶ�������, ������ʾJava����õĺ���Ĳ���ͷ���ֵ���� */
    void *fnPtr;          /* C����ʵ�ֵı��غ��� */
} JNINativeMethod;
#endif

jint fd = -1;

jint led_open(JNIEnv *env, jobject cls)
{
	fd = open("/dev/firefly-led",O_RDWR);
	if (fd < 0)
	{
		__android_log_print(ANDROID_LOG_DEBUG,"LED","native add led_open fail,fd = %d",fd);
		return -1;
	} else
		__android_log_print(ANDROID_LOG_DEBUG,"LED","native add led_open,fd = %d",fd);

	return 0;
}

void led_close(JNIEnv *env, jobject cls)
{
	close(fd);
	__android_log_print(ANDROID_LOG_DEBUG,"LED","native add led_close");
	
}


jint led_ctrl(JNIEnv *env, jobject cls,jint which,jint status)
{
	jint ret = -1;
	ret = ioctl(fd,status,which);

	__android_log_print(ANDROID_LOG_DEBUG,"LED","native add led_ctrl: which = %d,status = %d,ret = %d",which,status,ret);
	return ret;
}


static const JNINativeMethod methods[] = {
	{"ledOpen", "()I", (void *)led_open},
	{"ledClose", "()V", (void *)led_close},
	{"ledCtrl", "(II)I", (void *)led_ctrl},
};



/* System.loadLibrary */
JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM *jvm, void *reserved)
{
	JNIEnv *env;
	jclass cls;

	if ((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_4)) {
		return JNI_ERR; /* JNI version not supported */
	}
	cls = (*env)->FindClass(env,"ieee/hardlibrary/HardControl");
	if (cls == NULL) {
		return JNI_ERR;
	}

	/* 2. map java hello <-->c c_hello */
	if ((*env)->RegisterNatives(env, cls, methods, sizeof(methods)/sizeof(methods[0])) < 0)
		return JNI_ERR;

	return JNI_VERSION_1_4;
}

