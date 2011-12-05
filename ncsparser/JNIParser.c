#include "JNIParser.h"
#include "arrays.h"

JNIEXPORT jobject JNICALL Java_unr_neurotranslate_util_NCSParser_ParseInput(JNIEnv * env, jobject o , jint node, jcharArray filename, jint output ) {
	
	ARRAYS* arrays = ParseInput ( 1 , (char*) filename , 0 );

	}
