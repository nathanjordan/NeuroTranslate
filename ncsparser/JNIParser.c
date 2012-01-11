#include "JNIParser.h"
#include "arrays.h"
#include "JNIFunctions.h"
#include <string.h>

/***************************************************************************************
 *
 * Function Prototypes
 *
 **************************************************************************************/

/**
 * Main NCS parser function that
 */
extern ARRAYS *ParseInput (int node, char *filename, int output);

/*
 * Parser functions, they convert C code to java
 */
jobject parseArrays( JNIEnv * env , ARRAYS* arr );
jobject parseBrain( JNIEnv * env , T_BRAIN* );
jobject parseLocator( JNIEnv * env , LOCATOR* loc );
jobject parseList( JNIEnv * env , LIST* l );
jobject parseConnect( JNIEnv * env , T_CONNECT* con );
jobject parseRecurrent( JNIEnv * env , T_RECURRENT* r );
jobject parseCompartmentConnect( JNIEnv * env , T_CMPCONNECT* con );
jobject parseAnything( JNIEnv * env , T_ANYTHING* a );

/***************************************************************************************
 *
 * Native Method
 *
 **************************************************************************************/

JNIEXPORT jobject JNICALL Java_unr_neurotranslate_util_NCSParser_ParseInput(JNIEnv * env, jobject o , jint node, jstring filename, jint output ) {
	
	jboolean copy = 0;

	char * filename8 = (*env)->GetStringUTFChars( env, filename , &copy );
	
	ARRAYS* arrays = ParseInput( node , filename8 , output );

	return parseArrays( env , arrays );

	}

int main() {

	return 0;

	}

/***************************************************************************************
 *
 * Parser Functions
 *
 **************************************************************************************/

jobject parseArrays( JNIEnv * env , ARRAYS* arr ) {

	//create an array object
	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/Arrays" );

	jobject o = (*env)->AllocObject( env, c );
	//parse the brain
	jfieldID f = (*env)->GetFieldID( env , c , "brain" , "Lunr/neurotranslate/ncsclasses/Brain;" );

	(*env)->SetObjectField( env , o , f , parseBrain( env , arr->Brain ) );

	return o;

	}

jobject parseBrain( JNIEnv * env , T_BRAIN* b ) {

	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/Brain" );

	jobject brain = (*env)->AllocObject( env, c );

	////  Brain Fields  ////

	setObjField( env , brain , "l" , parseLocator( env , &b->L) , "Locator;\0" );

	if(b->job)
	setCharArrayField( env , brain , "job" , strlen(b->job) , b->job );

	if(b->distribute)
	setCharArrayField( env , brain , "distribute" , strlen(b->distribute) , b->distribute );

	setIntField( env , brain , "connectRpt" , b->ConnectRpt );

	setIntField( env , brain , "spikeRpt" , b->SpikeRpt );

	setDoubleField( env , brain , "Duration" , b->Duration );

	setIntField( env , brain , "FSV" , b->FSV );

	setLongField( env , brain , "Seed" , b->Seed );

	setIntField( env , brain , "nColumns" , b->nColumns );

	setObjField( env , brain , "columnNames" , parseList( env , b->ColumnNames) , "List;\0" );

	setIntArrayField( env , brain , "columns" , b->nColumns , b->Columns );

	setIntField( env , brain , "nStInject" , b->nStInject );

	//stinjnames

	setIntArrayField( env , brain , "stInject" , b->nStInject , b->StInject );

	setIntField( env , brain , "nReports" , b->nReports );

	//reportNames

	setIntArrayField( env , brain , "reports" , b->nReports , b->Reports );

	setIntField( env , brain , "nConnect" , b->nConnect );

	//cnList

	//connects
	if(b->savefile)
	setCharArrayField( env , brain , "savefile" , strlen( b->savefile ) , b->savefile );

	setDoubleField( env , brain , "savetime" , b->savetime );

	if(b->loadfile)
	setCharArrayField( env , brain , "loadfile" , strlen( b->loadfile ) , b->loadfile );

	setIntField( env , brain , "hostport" , b->HostPort );

	setIntField( env , brain , "port" , b->Port );

	if(b->HostName)
	setCharArrayField( env , brain , "hostname" , strlen( b->HostName ) , b->HostName );

	setIntField( env , brain , "flag" , b->flag );

	setIntField( env , brain , "nRecurrent" , b->nRecurrent );

	//recurrentList

	setIntField( env , brain , "nEvents" , b->nEvents );

	//event names

	setIntArrayField( env , brain , "events" , b->nEvents , b->Events );

	return brain;

	}

jobject parseLocator( JNIEnv * env , LOCATOR* loc ) {

	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/Locator" );

	jobject l = (*env)->AllocObject( env, c );

	setIntField( env , l , "kind" , loc->kind );

	if(loc->name)
	setCharArrayField( env , l , "name" , strlen( loc->name ) , loc->name );

	setIntField( env , l , "index" , loc->idx );

	if(loc->file)
	setCharArrayField( env , l , "file" , strlen( loc->file ) , loc->file );

	setIntField( env , l , "line" , loc->line );

	return l;

	}

jobject parseList( JNIEnv * env , LIST* l ) {

	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/List" );

	jobject li = (*env)->AllocObject( env, c );

	setObjField( env , li , "l" , parseLocator( env , &l->L) , "Locator;\0" );

	setIntField( env , li , "qty" , l->Qty );

	if(l->label)
	setCharArrayField( env , li , "label" , strlen( l->label ) , l->label );

	setDoubleField( env , li , "x" , l->x );

	setDoubleField( env , li , "y" , l->x );

	setDoubleField( env , li , "z" , l->x );

	return li;

	}

jobject parseRecurrent( JNIEnv * env , T_RECURRENT* r ) {

	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/Recurrent" );

	jobject li = (*env)->AllocObject( env, c );

	setObjField( env , li , "l" , parseLocator( env , &r->L) , "Locator;\0" );

	if(r->colNameA)
	setCharArrayField( env , li , "colNameA" , strlen( r->colNameA ) , r->colNameA );

	if(r->layNameA)
	setCharArrayField( env , li , "layNameA" , strlen( r->layNameA ) , r->layNameA );

	if(r->cellNameA)
	setCharArrayField( env , li , "cellNameA" , strlen( r->cellNameA ) , r->cellNameA );

	if(r->cmpNameA)
	setCharArrayField( env , li , "cmpNameA" , strlen( r->cmpNameA ) , r->cmpNameA );

	if(r->colNameB)
	setCharArrayField( env , li , "colNameB" , strlen( r->colNameB ) , r->colNameB );

	if(r->layNameB)
	setCharArrayField( env , li , "layNameB" , strlen( r->layNameB ) , r->layNameB );

	if(r->cellNameB)
	setCharArrayField( env , li , "cellNameB" , strlen( r->cellNameB ) , r->cellNameB );

	if(r->cmpNameB)
	setCharArrayField( env , li , "cmpNameB" , strlen( r->cmpNameB ) , r->cmpNameB );

	if(r->synName)
	setCharArrayField( env , li , "synName" , strlen( r->synName ) , r->synName );

	setDoubleField( env , li , "PrecurrenceAtoB" , r->PrecurrenceAtoB);

	setDoubleField( env , li , "PrecurrenceBtoA" , r->PrecurrenceBtoA);

	return li;

	}

jobject parseConnect( JNIEnv * env , T_CONNECT* con ) {

	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/Connect" );

	jobject o = (*env)->AllocObject( env, c );

	setObjField( env , o , "l" , parseLocator( env , &con->L) , "Locator;\0" );

	if(con->fromColName)
	setCharArrayField( env , o , "fromColName" , strlen( con->fromColName ) , con->fromColName );

	if(con->fromLayName)
	setCharArrayField( env , o , "fromLayName" , strlen( con->fromLayName ) , con->fromLayName );

	if(con->fromCmpName)
	setCharArrayField( env , o , "fromCmpName" , strlen( con->fromCmpName ) , con->fromCmpName );

	if(con->toColName)
	setCharArrayField( env , o , "toColName" , strlen( con->toColName ) , con->toColName );

	if(con->toLayName)
	setCharArrayField( env , o , "toLayName" , strlen( con->toLayName ) , con->toLayName );

	if(con->toCellName)
	setCharArrayField( env , o , "toCellName" , strlen( con->toCellName ) , con->toCellName );

	if(con->toCmpName)
	setCharArrayField( env , o , "toCmpName" , strlen( con->toCmpName ) , con->toCmpName );

	if(con->SynName)
	setCharArrayField( env , o , "synName" , strlen( con->SynName ) , con->SynName );

	setIntField( env , o , "fromCol" , con->FromCol );

	setIntField( env , o , "fromLay" , con->FromLay );

	setIntField( env , o , "fromCell" , con->FromCell );

	setIntField( env , o , "fromCmp" , con->FromCmp );

	setIntField( env , o , "toCol" , con->ToCol );

	setIntField( env , o , "toLay" , con->ToLay );

	setIntField( env , o , "toCell" , con->ToCell );

	setIntField( env , o , "toCmp" , con->ToCmp );

	setIntField( env , o , "synType" , con->SynType );

	setDoubleField( env , o , "speed" , con->speed );

	setDoubleField( env , o , "prob" , con->Prob );

	setDoubleField( env , o , "step" , con->step );

	setDoubleArrayField( env , o , "delay", 2 , con->delay );

	setDoubleArrayField( env , o , "recurrentProbability", 2 , con->recurrentProbability );

	if(con->recurrentConnection)
	setObjField( env , o , "l" , parseConnect( env , con->recurrentConnection) , "Connect;\0" );

	setIntField( env , o , "disabled" , con->disabled );

	}

jobject parseCompartmentConnect( JNIEnv * env , T_CMPCONNECT* con ) {

	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/Connect" );

	jobject o = (*env)->AllocObject( env, c );

	setObjField( env , o , "l" , parseLocator( env , &con->L) , "Locator;\0" );

	if(con->fromCmpName)
	setCharArrayField( env , o , "fromCmpName" , strlen( con->fromCmpName ) , con->fromCmpName );

	if(con->toCmpName)
	setCharArrayField( env , o , "toCmpName" , strlen( con->toCmpName ) , con->toCmpName );

	setIntField( env , o , "fromCmp" , con->FromCmp );

	setIntField( env , o , "toCmp" , con->ToCmp );

	setDoubleField( env , o , "Speed" , con->Speed );

	setDoubleField( env , o , "G" , con->G );

	setDoubleField( env , o , "retroG" , con->retroG );

	setDoubleField( env , o , "delay" , con->delay );

	return o;
	}

jobject parseAnything( JNIEnv * env , T_ANYTHING* a ) {

	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/Anything" );

	jobject o = (*env)->AllocObject( env, c );

	setObjField( env , o , "l" , parseLocator( env , &a->L) , "Locator;\0" );

	return o;

	}

jobject parseCShell( JNIEnv * env , T_CSHELL* cs ) {

	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/CShell" );

	jobject o = (*env)->AllocObject( env, c );

	setObjField( env , o , "l" , parseLocator( env , &cs->L) , "Locator;\0" );

	setDoubleField( env , o , "width" , cs->width );

	setDoubleField( env , o , "height" , cs->height );

	setDoubleField( env , o , "x" , cs->x );

	setDoubleField( env , o , "y" , cs->y );

	return o;

	}

jobject parseColumn( JNIEnv * env , T_COLUMN* col ) {

	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/Column" );

	jobject o = (*env)->AllocObject( env, c );

	setObjField( env , o , "l" , parseLocator( env , &col->L) , "Locator;\0" );

	if(col->shellName)
	setCharArrayField( env , o , "shellName" , strlen( col->shellName ) , col->shellName );

	setIntField( env , o , "CShell" , col->CShell );

	setIntField( env , o , "nLayers" , col->nLayers );

	if(col->LayerNames)
	setObjField( env , o , "layerNames" , parseList( env , col->LayerNames) , "List;\0" );

	if(col->Layers)
	setIntArrayField( env , o , "layers" , col->nLayers , col->Layers );

	setIntField( env , o , "nConnect" , col->nConnect );

	jobject* cnList = calloc( sizeof(jobject) , col->nConnect );

	for( int i = 0 ; i < col->nConnect ; i++ ) {

		cnList[i] = parseConnect( env , &col->CnList[i] );

		}

	setObjArrayField( env , o , "cnList" , col->nConnect , cnList , "Connect;\0" );

	//////////////////
	//
	//  connect (WTF???)
	//
	//////////////////

	setIntField( env , o , "nRecurrent" , col->nRecurrent );

	jobject* recurrentList = calloc( sizeof(jobject) , col->nRecurrent );

	for( int i = 0 ; i < col->nRecurrent ; i++ ) {

		recurrentList[i] = parseRecurrent( env , &col->recurrentList[i] );

		}

	setObjArrayField( env , o , "recurrentList" , col->nRecurrent , recurrentList , "Recurrent;\0" );

	return o;

	}

jobject parseLShell( JNIEnv * env , T_LSHELL* shell ) {

	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/LShell" );

	jobject o = (*env)->AllocObject( env, c );

	setObjField( env , o , "l" , parseLocator( env , &shell->L) , "Locator;\0" );

	setDoubleField( env , o , "lower" , shell->Lower );

	setDoubleField( env , o , "upper" , shell->Upper );

	return o;

	}

/*jobject parseLayer( JNIEnv * env , T_LAYER* lay ) {

	jclass c = (*env)->FindClass( env, "unr/neurotranslate/ncsclasses/Layer" );

	jobject o = (*env)->AllocObject( env, c );

	setObjField( env , o , "l" , parseLocator( env , &lay->L) , "Locator;\0" );

	if(lay->shellName)
	setCharArrayField( env , o , "shellName" , strlen( lay->shellName ) , lay->shellName );

	setIntField( env , o , "LShell" , lay->Lower );

	}
*/
