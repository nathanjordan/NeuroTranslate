/*
 * JNIFunctions.c
 *
 *  Created on: Dec 8, 2011
 *      Author: njordan
 */
#include "JNIFunctions.h"

#define NCS_PACKAGE "Lunr/neurotranslate/ncsclasses/\0"

void setIntField( JNIEnv * env, jobject o , char* fieldName , int value ) {

	jclass c = (*env)->GetObjectClass( env, o );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , "I" );

	(*env)->SetIntField( env , o , f, value );

	}

void setLongField( JNIEnv * env, jobject o , char* fieldName , long value ) {

	jclass c = (*env)->GetObjectClass( env, o );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , "J" );

	(*env)->SetLongField( env , o , f, value );

	}

void setFloatField( JNIEnv * env, jobject o , char* fieldName , float value ) {

	jclass c = (*env)->GetObjectClass( env, o );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , "F" );

	(*env)->SetFloatField( env , o , f, value );

	}

void setDoubleField( JNIEnv * env, jobject o , char* fieldName , double value ) {

	jclass c = (*env)->GetObjectClass( env, o );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , "D" );

	(*env)->SetDoubleField( env , o , f, value );

	}

void setCharField( JNIEnv * env, jobject o , char* fieldName , char value ) {

	jclass c = (*env)->GetObjectClass( env, o );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , "C" );

	(*env)->SetCharField( env , o , f, value );

	}

void setObjField( JNIEnv * env, jobject o , char* fieldName , jobject value , char* class ) {

	jclass c = (*env)->GetObjectClass( env, o );

	char qualifiedClass[80];
	qualifiedClass[0] = '\0';

	strcat( qualifiedClass , NCS_PACKAGE );
	strcat( qualifiedClass , class );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , qualifiedClass );

	(*env)->SetObjectField( env , o , f, value );

	}

void setIntArrayField( JNIEnv * env, jobject o , char* fieldName , int size , int* values) {

	jclass c = (*env)->GetObjectClass( env, o );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , "[I" );

	jintArray a = (*env)->NewIntArray( env , size );

	(*env)->ReleaseIntArrayElements( env , a , values , 0 );

	(*env)->SetObjectField( env , o , f , a );

	}

void setLongArrayField( JNIEnv * env, jobject o , char* fieldName , int size , long* values) {

	jclass c = (*env)->GetObjectClass( env, o );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , "[J" );

	jintArray a = (*env)->NewLongArray( env , size );

	(*env)->ReleaseLongArrayElements( env , a , values , 0 );

	(*env)->SetObjectField( env , o , f , a );

	}

void setFloatArrayField( JNIEnv * env, jobject o , char* fieldName , int size , float* values) {

	jclass c = (*env)->GetObjectClass( env, o );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , "[F" );

	jintArray a = (*env)->NewFloatArray( env , size );

	(*env)->ReleaseFloatArrayElements( env , a , values , 0 );

	(*env)->SetObjectField( env , o , f , a );

	}

void setDoubleArrayField( JNIEnv * env, jobject o , char* fieldName , int size , double* values) {

	jclass c = (*env)->GetObjectClass( env, o );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , "[D" );

	jintArray a = (*env)->NewDoubleArray( env , size );

	(*env)->ReleaseDoubleArrayElements( env , a , values , 0 );

	(*env)->SetObjectField( env , o , f , a );

	}

void setCharArrayField( JNIEnv * env, jobject o , char* fieldName , int size , char* values ) {

	jclass c = (*env)->GetObjectClass( env, o );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , "[C" );

	jintArray a = (*env)->NewCharArray( env , size );

	//TODO: Might need to convert these to jchars first
	(*env)->ReleaseCharArrayElements( env , a , values , 0 );

	(*env)->SetObjectField( env , o , f , a );

	}

void setObjArrayField( JNIEnv * env, jobject o , char* fieldName , int size , jobject* values , char* class ) {

	jclass c = (*env)->GetObjectClass( env, o );

	char qualifiedClass[80];
	qualifiedClass[0] = '\0';

	strcat( qualifiedClass , "[\0" );
	strcat( qualifiedClass , NCS_PACKAGE );
	strcat( qualifiedClass , class );

	jclass e = (*env)->FindClass( env , qualifiedClass );

	jfieldID f = (*env)->GetFieldID( env , c , fieldName , qualifiedClass );

	jobjectArray a = (*env)->NewObjectArray( env , size , e , values[0] );

	(*env)->SetObjectField( env , o , f , a );

	}
