#include "JNIParser.h"
#include "arrays.h"

extern ARRAYS *ParseInput (int node, char *filename, int output);

JNIEXPORT jobject JNICALL Java_unr_neurotranslate_util_NCSParser_ParseInput(JNIEnv * env, jobject o , jint node, jstring filename, jint output ) {
	
	char * filename8 = (*env)->GetStringUTFChars( env, filename , NULL );
	
	ARRAYS* arrays = ParseInput ( 1 , filename , 0 );
	
	return null;
	
	}
