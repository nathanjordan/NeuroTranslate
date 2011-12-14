/*
 * JNIFunctions.h
 *
 *  Created on: Dec 8, 2011
 *      Author: njordan
 */

#ifndef JNIFUNCTIONS_H_
#define JNIFUNCTIONS_H_

#include <jni.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

void setIntField( JNIEnv * env, jobject o , char* fieldName , int value );

void setLongField( JNIEnv * env, jobject o , char* fieldName , long value );

void setFloatField( JNIEnv * env, jobject o , char* fieldName , float value );

void setDoubleField( JNIEnv * env, jobject o , char* fieldName , double value );

void setCharField( JNIEnv * env, jobject o , char* fieldName , char value );

void setObjField( JNIEnv * env, jobject o , char* fieldName , jobject value , char* class );

void setIntArrayField( JNIEnv * env, jobject o , char* fieldName , int size , int* values);

void setLongArrayField( JNIEnv * env, jobject o , char* fieldName , int size , long* values);

void setFloatArrayField( JNIEnv * env, jobject o , char* fieldName , int size , float* values);

void setDoubleArrayField( JNIEnv * env, jobject o , char* fieldName , int size , double* values);

void setCharArrayField( JNIEnv * env, jobject o , char* fieldName , int size , char* values);

void setObjArrayField( JNIEnv * env, jobject o , char* fieldName , int size , jobject* values , char* class );

#endif /* JNIFUNCTIONS_H_ */
