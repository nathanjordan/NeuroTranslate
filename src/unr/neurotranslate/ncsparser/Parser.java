//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "parse.yaccj"

package unr.neurotranslate.ncsparser;

import java.io.*;
import java.util.ArrayList;
import unr.neurotranslate.ncs.*;
import java.util.Stack;

//#line 26 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=dup_yyval(val);
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short REAL=257;
public final static short INTEGER=258;
public final static short LOGICAL=259;
public final static short NAME=260;
public final static short TK_ABSOLUTE_USE=261;
public final static short TK_AMP_END=262;
public final static short TK_AMP_START=263;
public final static short TK_ASCII=264;
public final static short TK_BRAIN=265;
public final static short TK_CA_EXP=266;
public final static short TK_CA_EXTERNAL=267;
public final static short TK_CA_HALF_MIN=268;
public final static short TK_CA_INTERNAL=269;
public final static short TK_CA_SCALE=270;
public final static short TK_CA_SPIKE_INC=271;
public final static short TK_CA_TAU=272;
public final static short TK_CA_TAU_SCALE=273;
public final static short TK_CELL=274;
public final static short TK_CELLS=275;
public final static short TK_CELLS_PER_FREQ=276;
public final static short TK_CELL_TYPE=277;
public final static short TK_CHANNEL=278;
public final static short TK_COLUMN=279;
public final static short TK_CSHELL=280;
public final static short TK_COLUMN_TYPE=281;
public final static short TK_CMP=282;
public final static short TK_G=283;
public final static short TK_CONNECT=284;
public final static short TK_DATA_LABEL=285;
public final static short TK_DELAY=286;
public final static short TK_DEPR_TAU=287;
public final static short TK_DURATION=288;
public final static short TK_DYN_RANGE=289;
public final static short TK_END_BRAIN=290;
public final static short TK_END_COLUMN=291;
public final static short TK_END_CSHELL=292;
public final static short TK_END_CMP=293;
public final static short TK_END_CELL=294;
public final static short TK_END_CHANNEL=295;
public final static short TK_END_LAYER=296;
public final static short TK_END_LSHELL=297;
public final static short TK_END_REPORT=298;
public final static short TK_END_SPIKE=299;
public final static short TK_END_STIMULUS=300;
public final static short TK_END_ST_INJECT=301;
public final static short TK_END_SYNAPSE=302;
public final static short TK_END_SYN_DATA=303;
public final static short TK_END_SYN_FD=304;
public final static short TK_END_SYN_PSG=305;
public final static short TK_END_SYN_LEARN=306;
public final static short TK_E_HALF_MIN_H=307;
public final static short TK_E_HALF_MIN_M=308;
public final static short TK_FACIL_TAU=309;
public final static short TK_FILENAME=310;
public final static short TK_FREQUENCY=311;
public final static short TK_FREQ_ROWS=312;
public final static short TK_FREQ_START=313;
public final static short TK_FSV=314;
public final static short TK_HEIGHT=315;
public final static short TK_H_INITIAL=316;
public final static short TK_H_POWER=317;
public final static short TK_IGNORE_EMPTY=318;
public final static short TK_INJECT=319;
public final static short TK_INTERACTIVE=320;
public final static short TK_LAYER=321;
public final static short TK_LSHELL=322;
public final static short TK_LAYER_TYPE=323;
public final static short TK_LEAK_G=324;
public final static short TK_LEAK_REVERSAL=325;
public final static short TK_LEARN=326;
public final static short TK_LEARN_LABEL=327;
public final static short TK_LOCATION=328;
public final static short TK_LOWER=329;
public final static short TK_MAX_G=330;
public final static short TK_MODE=331;
public final static short TK_M_INITIAL=332;
public final static short TK_M_POWER=333;
public final static short TK_NEG_HEB_WINDOW=334;
public final static short TK_PATTERN=335;
public final static short TK_POS_HEB_WINDOW=336;
public final static short TK_PROB=337;
public final static short TK_PSG_FILE=338;
public final static short TK_RELOAD_SYN=339;
public final static short TK_REPORT=340;
public final static short TK_REPORT_ON=341;
public final static short TK_REVERSAL=342;
public final static short TK_RSE=343;
public final static short TK_RSE_LABEL=344;
public final static short TK_R_MEMBRANE=345;
public final static short TK_SAMESEED=346;
public final static short TK_SAVE_SYN=347;
public final static short TK_SEED=348;
public final static short TK_SFD=349;
public final static short TK_SFD_LABEL=350;
public final static short TK_SLOPE_H=351;
public final static short TK_SLOPE_M=352;
public final static short TK_SPIKE=353;
public final static short TK_STIMULUS=354;
public final static short TK_SPIKE_HW=355;
public final static short TK_ST_INJECT=356;
public final static short TK_STIM_TYPE=357;
public final static short TK_SYNAPSE=358;
public final static short TK_STRENGTH=359;
public final static short TK_SYN_DATA=360;
public final static short TK_SYN_FD=361;
public final static short TK_SYN_LEARN=362;
public final static short TK_SYN_PSG=363;
public final static short TK_SYN_REVERSAL=364;
public final static short TK_TAU_MEMBRANE=365;
public final static short TK_TAU_SCALE_M=366;
public final static short TK_TAU_SCALE_H=367;
public final static short TK_THRESHOLD=368;
public final static short TK_TIME_END=369;
public final static short TK_TIME_START=370;
public final static short TK_TIME_FREQ_INC=371;
public final static short TK_TIMING=372;
public final static short TK_TYPE=373;
public final static short TK_UPPER=374;
public final static short TK_UNITARY_G=375;
public final static short TK_VMREST=376;
public final static short TK_VOLTAGES=377;
public final static short TK_VTAU_VAL_M=378;
public final static short TK_VTAU_VAL_H=379;
public final static short TK_PORT=380;
public final static short TK_WIDTH=381;
public final static short TK_JOB=382;
public final static short TK_DISTRIBUTE=383;
public final static short TK_VAL_M_STDEV=384;
public final static short TK_VOLT_M_STDEV=385;
public final static short TK_SLOPE_M_STDEV=386;
public final static short TK_VAL_H_STDEV=387;
public final static short TK_VOLT_H_STDEV=388;
public final static short TK_SLOPE_H_STDEV=389;
public final static short TK_NEG_HEB_PEAK_DELTA_USE=390;
public final static short TK_NEG_HEB_PEAK_TIME=391;
public final static short TK_VTAU_VOLT_M=392;
public final static short TK_POS_HEB_PEAK_DELTA_USE=393;
public final static short TK_POS_HEB_PEAK_TIME=394;
public final static short TK_VTAU_VOLT_H=395;
public final static short TK_INCLUDE=396;
public final static short TK_RSE_INIT=397;
public final static short TK_VERT_TRANS=398;
public final static short TK_PREV_SPIKE_RANGE=399;
public final static short TK_CONNECT_RPT=400;
public final static short TK_SPIKE_RPT=401;
public final static short TK_SERVER=402;
public final static short TK_SINGLE=403;
public final static short TK_CA_EXP_RANGE=404;
public final static short TK_PHASE_SHIFT=405;
public final static short TK_STRENGTH_RANGE=406;
public final static short TK_SYNAPSE_RSE=407;
public final static short TK_ALPHA_SCALE_H=408;
public final static short TK_ALPHA_SCALE_M=409;
public final static short TK_BETA_SCALE_H=410;
public final static short TK_BETA_SCALE_M=411;
public final static short TK_SAVE=412;
public final static short TK_LOAD=413;
public final static short TK_DISTANCE=414;
public final static short TK_OUTPUT_CONNECT_MAP=415;
public final static short TK_OUTPUT_CELLS=416;
public final static short TK_AUTO=417;
public final static short TK_SERVER_PORT=418;
public final static short TK_VERSION=419;
public final static short TK_SYNAPSE_UF=420;
public final static short TK_RECURRENT_CONNECT=421;
public final static short TK_ALPHA=422;
public final static short TK_SYN_AUGMENTATION=423;
public final static short TK_END_SYN_AUGMENTATION=424;
public final static short TK_MAX_AUGMENTATION=425;
public final static short TK_AUGMENTATION_INIT=426;
public final static short TK_AUGMENTATION_TAU=427;
public final static short TK_SYN_CALCIUM=428;
public final static short TK_CA_TAU_DECAY=429;
public final static short TK_EXP=430;
public final static short TK_SELECT_FRONT=431;
public final static short TK_OPTION=432;
public final static short TK_AVERAGE_SYN=433;
public final static short TK_AUGMENTATION_DELAY=434;
public final static short TK_WARNINGS_OFF=435;
public final static short TK_HIDE_TIMESTEP=436;
public final static short TK_HEBB_START=437;
public final static short TK_HEBB_END=438;
public final static short TK_EVENT=439;
public final static short TK_OVERRIDE=440;
public final static short TK_LEARN_SHAPE=441;
public final static short TK_RATE=442;
public final static short TK_TAU_NOISE=443;
public final static short TK_CORREL=444;
public final static short TK_END_EVENT=445;
public final static short TK_CELL_SEQUENCE=446;
public final static short TK_SFD_START=447;
public final static short TK_SFD_END=448;
public final static short TK_Km=449;
public final static short TK_Kahp=450;
public final static short TK_Ka=451;
public final static short TK_Na=452;
public final static short TK_Knicd=453;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    0,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,   22,    2,   21,   21,   23,   23,   23,
   23,   23,   23,   23,   23,   23,   23,   23,   23,   23,
   23,   23,   23,   23,   23,   23,   23,   23,   23,   23,
   23,   23,   26,    4,   25,   25,   27,   27,   27,   27,
   29,    3,   28,   28,   30,   30,   30,   30,   30,   32,
    6,   31,   31,   33,   33,   33,   35,    5,   34,   34,
   36,   36,   36,   36,   36,   38,    7,   37,   37,   39,
   39,   39,   39,   41,    8,   40,   40,   42,   42,   42,
   42,   44,   42,   45,   42,   46,   42,   47,   42,   48,
   42,   49,   42,   50,   42,   51,   42,   52,   42,   53,
   42,   55,    9,   57,    9,   59,    9,   61,    9,   63,
    9,   54,   54,   54,   56,   56,   56,   58,   58,   58,
   60,   60,   60,   62,   62,   64,   69,   64,   70,   64,
   71,   64,   72,   64,   73,   64,   74,   64,   64,   75,
   65,   76,   65,   65,   65,   77,   66,   78,   66,   79,
   66,   80,   66,   81,   66,   82,   67,   83,   67,   84,
   67,   85,   67,   67,   67,   67,   67,   67,   67,   67,
   67,   87,   67,   88,   67,   89,   67,   90,   67,   91,
   68,   92,   68,   93,   68,   94,   68,   95,   68,   96,
   68,   68,   68,   68,   68,   97,   68,   98,   68,   99,
   68,  100,   68,  102,   10,  101,  101,  103,  103,  103,
  103,  103,  103,  103,  104,  103,  105,  103,  106,  103,
  103,  107,  103,  108,  103,  103,  103,  103,  103,  110,
   11,  109,  109,  111,  111,  113,   12,  112,  112,  114,
  114,  114,  115,  114,  116,  114,  118,   13,  117,  117,
  119,  119,  119,  119,  120,  119,  121,  119,  122,  119,
  123,  119,  124,  119,  125,  119,  127,   14,  126,  126,
  128,  128,  128,  129,  128,  130,  128,  132,   15,  131,
  131,  133,  134,  133,  135,  133,  136,  133,  137,  133,
  138,  133,  139,  133,  140,  133,  141,  133,  142,  133,
  144,   16,  143,  143,  145,  146,  145,  148,   17,  147,
  147,  149,  149,  149,  149,  149,  149,  149,  149,  149,
  149,  149,  149,  149,  149,  149,  149,  149,  150,  149,
  149,  149,  149,  149,  151,  149,  152,  149,  154,   18,
  153,  153,  155,  155,  155,  157,   19,  156,  156,  158,
  158,  158,  158,  158,  158,  158,  158,  158,  158,  158,
  158,  159,  158,  160,  158,  158,  158,  158,  158,  158,
  158,  158,  158,  158,  163,   20,  162,  162,  164,  164,
  164,  164,  161,  161,  165,  165,   86,   86,   43,   43,
   24,   24,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    2,    0,    4,    1,    2,    2,    2,    2,
    1,    1,    2,    2,    2,    2,    2,    2,   12,   12,
    3,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    0,    4,    1,    2,    2,    2,    2,    3,
    0,    4,    1,    2,    2,    2,    2,   10,   10,    0,
    4,    1,    2,    2,    2,    2,    0,    4,    1,    2,
    2,    2,    3,    8,    8,    0,    4,    1,    2,    2,
    5,    6,    5,    0,    4,    1,    2,    2,    2,    2,
    2,    0,    3,    0,    3,    0,    3,    0,    3,    0,
    3,    0,    3,    0,    3,    0,    3,    0,    3,    0,
    3,    0,    5,    0,    5,    0,    5,    0,    5,    0,
    5,    1,    2,    2,    1,    2,    2,    1,    2,    2,
    1,    2,    2,    1,    2,    2,    0,    3,    0,    3,
    0,    3,    0,    3,    0,    3,    0,    3,    2,    0,
    3,    0,    3,    4,    2,    0,    3,    0,    3,    0,
    3,    0,    3,    0,    3,    0,    3,    0,    3,    0,
    3,    0,    3,    2,    2,    2,    2,    2,    2,    2,
    2,    0,    3,    0,    3,    0,    3,    0,    3,    0,
    3,    0,    3,    0,    3,    0,    3,    0,    3,    0,
    3,    3,    3,    2,    2,    0,    3,    0,    3,    0,
    3,    0,    3,    0,    4,    1,    2,    2,    2,    2,
    2,    2,    2,    2,    0,    3,    0,    3,    0,    3,
    2,    0,    3,    0,    3,    2,    2,    2,    2,    0,
    4,    1,    2,    2,    2,    0,    4,    1,    2,    2,
    2,    2,    0,    3,    0,    3,    0,    4,    1,    2,
    2,    2,    2,    2,    0,    3,    0,    3,    0,    3,
    0,    3,    0,    3,    0,    3,    0,    4,    1,    2,
    2,    2,    2,    0,    3,    0,    3,    0,    4,    1,
    2,    2,    0,    3,    0,    3,    0,    3,    0,    3,
    0,    3,    0,    3,    0,    3,    0,    3,    0,    3,
    0,    4,    1,    2,    2,    0,    3,    0,    4,    1,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    0,    3,
    2,    2,    2,    2,    0,    3,    0,    3,    0,    4,
    1,    2,    2,    2,    6,    0,    4,    1,    2,    2,
    5,    2,    2,    2,    1,    2,    2,    2,    2,    2,
    2,    0,    3,    0,    3,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    0,    4,    1,    2,    2,    2,
    5,    3,    1,    2,    1,    1,    1,    2,    2,    1,
    1,    1,
};
final static short yydefred[] = {                         0,
   24,   86,    0,   61,   53,   94,   77,   70,  366,  321,
  328,  359,  224,  287,  256,  267,  250,    0,  298,  395,
    0,    1,    4,    5,    6,    7,    8,    9,   10,   11,
   12,   13,   14,   15,   16,   17,   18,   19,   20,   21,
   22,    0,    0,  122,  124,  126,  128,  130,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   23,    0,    0,    3,    2,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   31,   32,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   26,    0,    0,    0,    0,   88,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   63,    0,
    0,    0,    0,    0,   55,  116,  114,  120,  118,    0,
  110,  108,  104,    0,    0,  102,  106,    0,  112,    0,
   96,    0,    0,    0,    0,    0,    0,   79,    0,    0,
    0,    0,   72,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  384,  382,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  368,    0,  326,    0,  323,    0,
    0,    0,  349,    0,    0,    0,    0,    0,    0,    0,
  357,  355,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  330,    0,    0,    0,    0,  361,  235,
    0,  237,    0,    0,    0,    0,    0,  239,    0,  242,
  244,    0,    0,    0,    0,    0,    0,  226,  294,    0,
    0,  296,    0,    0,  289,  265,  263,    0,    0,    0,
    0,  258,    0,  275,  277,    0,    0,  279,  283,  281,
  285,    0,    0,  269,    0,    0,    0,  252,  303,  309,
  307,    0,  313,  311,  315,  317,  305,  319,    0,  300,
    0,    0,    0,    0,    0,  397,   36,    0,  412,  411,
   34,   33,   38,   35,   37,   28,   43,   45,   44,   29,
   30,   46,    0,   42,   48,   50,   49,   47,    0,   51,
   52,   25,   27,    0,    0,   90,   87,   89,  155,  147,
  157,    0,  151,    0,  149,  153,    0,  132,    0,  135,
    0,  138,    0,  141,    0,  144,   66,    0,   67,   65,
    0,   62,   64,   59,    0,   57,   58,   54,   56,    0,
    0,    0,    0,  101,    0,    0,    0,   99,  100,    0,
    0,   98,    0,   95,   97,    0,    0,   82,   81,    0,
   78,   80,   75,   74,   76,   71,   73,  376,    0,  377,
  372,  381,  380,  378,  391,  392,  386,    0,    0,  370,
  373,  374,  387,  393,  388,  389,  390,  405,  406,    0,
  403,  379,  367,  369,  325,    0,  322,  324,  352,  351,
  347,    0,  341,  346,  354,  333,  334,  344,  345,    0,
    0,  348,  340,  332,  342,  343,  353,  335,  336,  337,
  338,  339,  329,  331,    0,  364,  363,  360,  362,    0,
  232,    0,  231,  241,  229,  230,  234,    0,  228,    0,
    0,  233,  246,  247,  248,  249,  225,  227,    0,  293,
  292,    0,  291,  288,  290,    0,    0,  261,  262,  260,
  257,  259,  273,    0,    0,  272,  271,    0,    0,    0,
    0,  274,  268,  270,  255,  254,  251,  253,    0,    0,
    0,  302,    0,    0,    0,    0,    0,    0,  299,  301,
    0,  400,  399,    0,  396,  398,    0,   41,    0,    0,
    0,    0,    0,    0,  159,    0,  146,    0,    0,  123,
  160,    0,  162,    0,  133,  134,  168,  172,  166,  174,
  125,  170,  136,  137,  127,  182,  180,  176,  178,    0,
    0,  192,  196,    0,    0,    0,    0,    0,    0,  194,
  198,  139,  140,  129,  202,  200,  204,  206,    0,    0,
  208,  210,    0,    0,  220,  216,  222,  218,  142,  143,
  131,  145,    0,    0,   60,    0,  117,  115,  121,  119,
  111,  109,  105,  103,  107,  113,   83,    0,    0,    0,
  407,    0,    0,  404,    0,  350,    0,    0,    0,  236,
  238,  240,  243,  245,  295,  297,  266,  264,  276,  278,
  280,  284,  282,  286,  304,  310,  308,  314,  312,  316,
  318,  306,  320,    0,  402,    0,    0,    0,    0,  156,
  148,  158,  152,  150,  154,    0,    0,    0,  165,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  186,  184,
    0,    0,  188,  189,  185,  190,  191,  187,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  214,  215,
    0,    0,    0,    0,    0,    0,  409,    0,    0,    0,
  408,    0,    0,    0,    0,    0,   93,  161,    0,  163,
  169,  173,  167,  175,  171,  183,  181,  177,  179,    0,
    0,    0,    0,  203,  201,  205,  207,  213,  212,  209,
  211,  221,  217,  223,  219,    0,    0,    0,    0,  371,
    0,  401,    0,    0,   92,  164,    0,    0,    0,    0,
  365,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   84,   85,    0,    0,    0,    0,    0,    0,   68,
   69,    0,    0,   39,   40,
};
final static short yydgoto[] = {                         21,
   22,   23,   24,   25,   26,   27,   28,   29,   30,   31,
   32,   33,   34,   35,   36,   37,   38,   39,   40,   41,
   91,   42,   92,  556,  114,   50,  115,  108,   49,  109,
  142,   53,  143,  137,   52,  138,   96,   43,   97,  130,
   51,  131,  557,  340,  337,  341,  336,  335,  343,  331,
  330,  333,  332,  307,   98,  309,   99,  311,  100,  313,
  101,  315,  102,  308,  506,  514,  533,  550,  493,  498,
  496,  499,  492,  494,  616,  618,  622,  620,  624,  621,
  623,  627,  628,  626,  625,  572,  631,  639,  632,  640,
  642,  641,  643,  644,  647,  648,  652,  654,  651,  653,
  217,   58,  218,  420,  422,  428,  430,  431,  247,   62,
  248,  231,   60,  232,  447,  446,  243,   61,  244,  454,
  455,  458,  460,  459,  461,  224,   59,  225,  439,  442,
  259,   64,  260,  469,  477,  471,  470,  474,  473,  475,
  476,  478,  168,   55,  169,  386,  193,   56,  194,  392,
  401,  400,  198,   57,  199,  164,   54,  165,  369,  368,
  380,  265,   65,  266,  381,
};
final static short yysindex[] = {                      1065,
    0,    0, -293,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -222,    0,    0,
   26,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, 1149, -247,    0,    0,    0,    0,    0,  -40, -176,
 1615, -175, -315,  992, -360,  150, -312, -113,  -19,   69,
  647, -326,    0, -173, -248,    0,    0, -216, -214, -226,
 -226, -212, -206, -207, -197, -250, -196, -195,    0,    0,
 -193, -192, -190, -205, -187, -186, -183, -184, -425, -177,
 1094,    0, -167, -152, -149, -278,    0,  209,  209,  209,
  209,  209, -144, -137, -136, -130, -124, -142,    0, -226,
 -226, -109, -226, -241,    0,    0,    0,    0,    0, -106,
    0,    0,    0, -181,  -94,    0,    0,  -82,    0, 1588,
    0,  -78,  -77,  -76,  -75,  -74, -266,    0, -226,  -67,
 -226, -269,    0, -240,  -66,  -61,  -54, -226, -226,  -45,
 -255,  -39,    0,    0,  -21, -249,   -3,  -69,   -2,    2,
    3, -394,    8,  714,    0,    9,    0, -244,    0, -226,
 -226,  -29,    0,   11,   16, -226,   15,   20,   22,   27,
    0,    0, -226,   29,   30, -243, -226, -226, -226, -226,
 -226, -226,  542,    0,   32,   33,   34,  -31,    0,    0,
   35,    0,   36, -226,   39,   41,   47,    0,   49,    0,
    0,   52, -226, -226, -226, -226, -236,    0,    0, -226,
   55,    0,   54,  -27,    0,    0,    0,   65,   68,   72,
  -71,    0,   76,    0,    0,   80,   79,    0,    0,    0,
    0,   81,   99,    0,   83,   84, -276,    0,    0,    0,
    0,   89,    0,    0,    0,    0,    0,    0, -107,    0,
   90,   91,   92,   93, -270,    0,    0,   95,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -226,    0,    0,    0,    0,    0,   97,    0,
    0,    0,    0,   98,  100,    0,    0,    0,    0,    0,
    0,   82,    0,  101,    0,    0, 1531,    0,  231,    0,
 1291,    0, 1252,    0,  185,    0,    0,  102,    0,    0,
  103,    0,    0,    0, -226,    0,    0,    0,    0, -226,
 -226, -226, -226,    0, -226, -226, -226,    0,    0, -226,
 -226,    0, -226,    0,    0,  106,  108,    0,    0,  123,
    0,    0,    0,    0,    0,    0,    0,    0,  125,    0,
    0,    0,    0,    0,    0,    0,    0, -226, -226,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -394,
    0,    0,    0,    0,    0, -226,    0,    0,    0,    0,
    0, -226,    0,    0,    0,    0,    0,    0,    0, -226,
 -226,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  134,    0,    0,    0,    0, -226,
    0, -226,    0,    0,    0,    0,    0, -226,    0, -226,
 -226,    0,    0,    0,    0,    0,    0,    0, -226,    0,
    0, -226,    0,    0,    0, -226, -226,    0,    0,    0,
    0,    0,    0, -226, -226,    0,    0, -226, -226, -226,
 -226,    0,    0,    0,    0,    0,    0,    0, -226, -226,
 -226,    0, -226, -226, -226, -226, -226, -226,    0,    0,
  135,    0,    0, -226,    0,    0,  138,    0,  139, -226,
 -226, -226, -226, -226,    0, -226,    0, -226, -226,    0,
    0, -226,    0, -226,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -226,
 -226,    0,    0, -226, -226, -226, -226, -226, -226,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -226, -226,
    0,    0, -226, -226,    0,    0,    0,    0,    0,    0,
    0,    0,  144,  146,    0, -226,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  147,  148,  149,
    0, -226, -226,    0, -226,    0, -226, -226,  151,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  155,    0,  156,  159, -226, -226,    0,
    0,    0,    0,    0,    0, -226, -226, -226,    0, -226,
 -226, -226, -226, -226, -226, -226, -226, -226,    0,    0,
 -226, -226,    0,    0,    0,    0,    0,    0, -226, -226,
 -226, -226, -226, -226, -226, -226, -226, -226,    0,    0,
 -226, -226, -226, -226,  163,  164,    0,  167,  168,  169,
    0,  170,  171,  176,  177, -226,    0,    0, -226,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -226,
 -226, -226, -226,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  178,  180,  181,  183,    0,
 -226,    0,  184,  186,    0,    0,  188,  192, -226, -226,
    0,  193,  201,  204,  206, -226, -226,  207,  208, -226,
 -226,    0,    0,  211,  215, -226, -226, -226, -226,    0,
    0, -226, -226,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  781,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  825,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  403,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  866,  951,    0, -242,    0,  572,  662,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -258,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, 1339,
 1387, 1435, 1483,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  266,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  268,  -70,    0,    0,  296,    0,    0,  337,
    0,    0,  334,    0,    0,  340,    0,    0,  382,    0,
    0,  349, -251,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -79,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -367,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  265,    0,    0,    0,    0,    0,    0,    0,
  236,    0,    0,  253,    0,    0,    0,    0,  244,    0,
    0,    0,    0,    0,    0,    0,    0,  264,    0,    0,
    0,    0,  232,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  326,    0,    0,    0,  302,    0,
    0,    0,    0,    0,  308,    0,    0,  336,    0,    0,
    0,    0,    0,  245,  129,
};
final static int YYTABLESIZE=1991;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                        271,
  272,  573,  365,   93,  261,   94,  195,  277,  371,  290,
  132,  245,  166,  139,  405,  297,  167,  133,  575,  310,
  312,  314,  316,   91,  200,   91,  261,  356,  467,  351,
  269,  270,  577,  578,   93,   91,   94,   63,  378,  324,
  325,  379,  327,  267,  196,  268,  246,  273,  201,  202,
  328,  274,  275,  285,  387,  134,  327,  140,  141,  139,
  197,  245,  276,  280,  281,  437,  282,  283,  353,  284,
  355,  286,  287,  110,  288,  289,  338,  362,  363,  558,
  559,  560,  291,  561,  562,  563,  111,  262,  564,  565,
  203,  566,  294,  204,   95,  249,  246,  250,  251,  389,
  390,  132,  263,  140,  141,  395,  135,  295,  133,  262,
  296,  205,  402,  206,   91,  317,  407,  408,  409,  410,
  411,  412,  318,  319,  263,   95,  207,  208,  166,  320,
  327,  112,  167,  424,  327,  321,  209,  103,  110,  113,
  576,  104,  433,  434,  435,  436,  134,  200,  322,  440,
  326,  111,  278,  334,  136,   44,   45,   46,   47,   48,
  210,  249,  211,  250,  251,  339,  279,  372,  580,  264,
  581,  201,  202,  406,  485,  366,  582,  342,  583,  584,
  105,  346,  347,  348,  349,  350,  212,  585,  374,  358,
  586,  264,  354,  359,  587,  588,  112,  135,  360,  252,
  213,  214,  589,  590,  113,  361,  591,  592,  593,  594,
  215,  216,  488,  203,  364,  226,  204,  595,  596,  597,
  367,  598,  599,  600,  601,  602,  603,  505,  391,  513,
  106,  532,  451,  549,  205,  552,  206,  227,  370,  103,
  610,  611,  612,  104,  613,  136,  614,  615,  253,  207,
  208,  254,  255,  256,  555,  257,  373,  375,  219,  209,
  258,  376,  377,  680,  681,  252,  219,  382,  385,  418,
  393,  682,  683,  394,  396,  444,  228,  229,  107,  397,
  398,   66,  105,  210,  399,  211,   67,  195,  403,  404,
    1,  415,  416,  417,  421,  423,  425,  571,  571,    2,
  426,  230,  220,    3,    4,    5,  427,    6,  429,  212,
  220,  432,  441,  443,  253,  571,  479,  254,  255,  256,
  221,  257,  448,  213,  214,  196,  258,  449,  221,  571,
  571,  450,  106,  215,  216,  453,  222,  456,  457,  495,
  462,  197,  465,  466,  222,  223,    7,    8,  472,  481,
  482,  483,  484,  223,  487,  226,  489,  490,  293,  491,
  497,  553,  554,  567,  668,    9,  670,  568,  671,  672,
  673,  674,  675,  676,  677,  678,  679,  227,   10,   11,
  107,   12,  569,   13,  570,   14,   15,   16,   17,  684,
  685,  686,  687,  579,  604,  690,  691,  606,  607,  692,
  693,  694,  695,  655,  463,  656,  658,  659,  660,  329,
  662,  170,  171,  605,  663,  664,  228,  229,  665,  608,
  609,   18,  696,  697,  233,  172,  698,  699,  700,  701,
  702,  617,  234,  619,  235,  703,  704,  707,  173,  708,
  709,  230,  710,  712,  323,  713,  236,  714,   19,  629,
  630,  715,  718,  633,  634,  635,  636,  637,  638,  174,
  719,  175,  176,  720,   20,  721,  724,  725,  645,  646,
  728,  237,  649,  650,  729,  357,  352,  298,  345,  551,
  177,  438,  468,  452,  178,  657,  464,  445,  238,  239,
  480,  240,  241,  388,  414,  179,  507,  180,  508,  384,
  509,  661,  661,  510,  661,  419,  661,  661,  574,  486,
    0,    0,    0,    0,    0,    0,  299,  300,  181,  182,
  183,  184,  185,    0,    0,  511,  301,    0,    0,  186,
  187,    0,  302,    0,    0,    0,    0,  666,  667,  242,
  299,  300,    0,  303,    0,    0,  669,  188,    0,    0,
  301,    0,    0,    0,  189,    0,  302,  304,    0,  305,
  571,  571,  299,  300,    0,    0,    0,  303,  571,  571,
    0,    0,  301,    0,  688,  689,    0,    0,  302,    0,
    0,  304,    0,  305,    0,    0,    0,    0,    0,  303,
  306,  190,  191,  192,    0,  705,    0,    0,  706,    0,
    0,    0,    0,  304,    0,  305,    0,    0,    0,  661,
  661,  661,  661,    0,  306,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  711,    0,    0,    0,  512,    0,  306,    0,  716,  717,
    0,    0,    0,    0,    0,  722,  723,    0,    0,  726,
  727,    0,    0,    0,    0,  730,  731,  732,  733,    0,
    0,  734,  735,  410,  410,  410,    0,    0,  410,  410,
  410,  410,  410,  410,  410,  410,    0,    0,  410,    0,
  410,    0,    0,    0,    0,    0,    0,  410,  410,  410,
    0,  410,    0,    0,    0,  410,    0,  410,    0,    0,
    0,    0,  410,    0,  410,  410,  410,    0,  410,  410,
  410,  410,  410,    0,  410,  410,    0,    0,  410,  410,
    0,    0,    0,    0,    0,    0,  410,  410,  410,  410,
    0,    0,  410,  410,  410,  410,  410,  410,  410,    0,
    0,    0,    0,    0,  410,    0,    0,  410,  410,    0,
  410,  410,  410,  410,  410,  410,    0,    0,    0,    0,
    0,  410,    0,    0,    0,  410,  410,  410,  410,  410,
  410,  410,  410,  410,  410,  410,    0,  410,  410,    0,
  410,  410,  410,  410,    0,    0,  410,  410,  410,  410,
  410,  410,  410,  410,  410,  410,  410,  410,    0,  410,
  410,  410,    0,  170,  171,    0,  410,  410,  410,    0,
  410,  410,  410,  410,    0,    0,    0,  172,    0,    0,
    0,    0,    0,    0,  410,  410,  410,  410,  410,  410,
  173,  410,    0,  358,  358,    0,  410,    0,    0,  410,
  410,  413,    0,  410,  410,  410,  410,  358,    0,  410,
  410,  174,    0,  175,  176,    0,    0,    0,    0,    0,
  358,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  358,  177,    0,    0,    0,  178,    0,    0,    0,
    0,  358,    0,  358,  358,    0,    0,  179,    0,  180,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  358,    0,    0,    0,  358,    0,    0,    0,
  181,  182,  183,  184,  185,    0,    0,  358,    0,  358,
    0,  186,  187,  356,  356,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  356,    0,  188,
  358,  358,  358,  358,  358,    0,  189,    0,    0,    0,
  356,  358,  358,    0,    0,    0,    0,    0,    0,    0,
    0,  356,    0,    0,    0,    0,    0,    0,    0,  358,
    0,  356,  233,  356,  356,    0,  358,  144,    0,    0,
  234,    0,  235,  190,  191,  192,    0,    0,  145,    0,
    0,  146,  356,    0,  236,    0,  356,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  356,    0,  356,
    0,  383,    0,  358,  358,  358,    0,    0,    0,  237,
    0,    0,    0,  147,  148,    0,    0,    0,    0,    0,
  356,  356,  356,  356,  356,    0,  238,  239,    0,  240,
  241,  356,  356,    0,  375,    0,    0,    0,    0,    0,
  149,    0,    0,    0,  150,  375,    0,    0,  375,  356,
    0,  151,    0,    0,    0,    0,  356,    0,    0,    0,
    0,  152,    0,    0,    0,    0,    0,    0,  375,    0,
    0,    0,  153,  154,    0,    0,  155,  242,  394,    0,
  375,  375,    0,  156,    0,    0,    0,    0,    0,  394,
    0,    0,  394,  356,  356,  356,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  375,    0,    0,
  157,  375,  394,    0,    0,    0,    0,    0,  375,  385,
    0,    0,  158,  159,  394,  394,  160,    0,  375,    0,
  385,  161,    0,  385,    0,  162,    0,    0,    0,  375,
  375,    0,    0,  375,    0,    0,    0,    0,    0,  163,
  375,  394,    0,  385,    0,  394,    0,    0,    0,    0,
    0,    0,  394,    0,    0,  385,  385,    0,    0,    0,
    0,    0,  394,    0,    0,    0,    0,  375,    0,    0,
    0,    0,    0,  394,  394,    0,    0,  394,    0,  375,
  375,    0,  385,  375,  394,    0,  385,    0,  375,    0,
    0,    0,  375,  385,  383,    0,    0,    0,    0,    0,
    0,    0,    0,  385,    0,  383,  375,    0,  383,    0,
    0,  394,    0,    0,  385,  385,    0,    0,  385,    0,
    0,    0,    0,  394,  394,  385,    0,  394,  383,    0,
    0,    0,  394,    0,    0,  144,  394,    0,    0,    0,
  383,  383,    0,    0,    0,    0,  145,    0,    0,  146,
  394,    0,  385,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  385,  385,    0,  383,  385,    0,
    0,  383,    0,  385,    0,    0,    0,  385,  383,    0,
    0,  147,  148,    0,    0,    0,    0,    0,  383,    0,
    0,  385,    0,    0,    0,    0,    0,    0,    0,  383,
  383,    0,    0,  383,    0,    0,    0,    0,  149,    1,
  383,    0,  150,    0,    0,    0,    0,    0,    2,  151,
    0,    0,    3,    4,    5,    0,    6,    0,    0,  152,
    0,    0,    0,    0,    0,    0,    0,  383,    0,    0,
  153,  154,    0,    0,  155,    0,    0,    0,    0,  383,
  383,  156,    0,  383,   68,    0,    0,   69,  383,    0,
    0,   70,  383,  292,    0,    7,    8,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  383,    0,  157,    0,
    0,    0,    0,    0,    9,    0,    0,   71,    0,    0,
  158,  159,    0,    0,  160,    0,    0,   10,   11,  161,
   12,    0,   13,  162,   14,   15,   16,   17,    0,   68,
    0,    0,   69,   72,    0,    0,   70,  163,    0,    0,
    0,   73,    0,    0,    0,    0,    0,    0,    0,   74,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   18,    0,   71,    0,    0,    0,   75,    0,    0,    0,
    0,    0,    0,   76,    0,   77,   78,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   19,   72,    0,
    0,    0,    0,   79,   80,   81,   73,    0,    0,    0,
    0,    0,    0,   20,   74,   82,   83,   84,   85,   86,
    0,   87,    0,    0,   88,    0,    0,    0,    0,    0,
    0,   75,    0,    0,    0,   89,    0,    0,   76,    0,
   77,   78,   90,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  534,    0,   79,   80,
   81,    0,    0,    0,    0,    0,    0,    0,  535,  536,
   82,   83,   84,   85,   86,    0,   87,  537,  538,   88,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   89,    0,    0,  299,  300,  515,    0,   90,    0,    0,
    0,    0,    0,  301,    0,    0,    0,  516,  517,  302,
    0,    0,  539,  540,    0,    0,  518,  519,    0,    0,
  303,    0,    0,    0,    0,    0,    0,  541,  542,    0,
    0,    0,  299,  300,  304,    0,  305,    0,    0,    0,
    0,    0,  301,  193,    0,    0,    0,  543,  302,    0,
  544,  520,  521,    0,    0,  193,  193,    0,    0,  303,
    0,    0,    0,    0,  193,  193,    0,  306,    0,  545,
  546,  547,  548,  304,    0,  305,    0,    0,  522,  523,
  193,  193,    0,    0,  524,  525,  526,  527,  528,  529,
  193,  197,  530,    0,    0,  531,  193,    0,    0,  193,
  193,    0,    0,  197,  197,    0,  306,  193,    0,    0,
    0,    0,  197,  197,    0,    0,    0,    0,    0,    0,
    0,  193,    0,  193,    0,    0,  193,  193,  197,  197,
    0,    0,  193,  193,  193,  193,  193,  193,  197,  195,
  193,    0,    0,  193,  197,    0,    0,  197,  197,    0,
    0,  195,  195,    0,  193,  197,    0,    0,    0,    0,
  195,  195,    0,    0,    0,    0,    0,    0,    0,  197,
    0,  197,    0,    0,  197,  197,  195,  195,    0,    0,
  197,  197,  197,  197,  197,  197,  195,  199,  197,    0,
    0,  197,  195,    0,    0,  195,  195,    0,    0,  199,
  199,    0,  197,  195,    0,    0,    0,    0,  199,  199,
    0,    0,    0,    0,    0,    0,    0,  195,    0,  195,
    0,    0,  195,  195,  199,  199,    0,    0,  195,  195,
  195,  195,  195,  195,  199,  500,  195,    0,    0,  195,
  199,    0,    0,  199,  199,    0,    0,    0,  501,    0,
  195,  199,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  116,  199,  117,  199,  118,  119,
  199,  199,  299,  300,    0,  120,  199,  199,  199,  199,
  199,  199,  301,    0,  199,    0,    0,  199,  302,    0,
  344,  116,  502,  117,    0,  118,  119,    0,  199,  303,
    0,    0,  120,    0,    0,    0,  503,    0,    0,    0,
    0,    0,    0,  304,    0,  305,    0,    0,    0,    0,
    0,  121,  122,    0,    0,    0,  504,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  123,    0,    0,  124,  306,    0,  121,  122,
  125,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  126,    0,    0,  127,    0,    0,    0,  123,
  128,    0,  124,  129,    0,    0,    0,  125,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  126,
    0,    0,  127,    0,    0,    0,    0,  128,    0,    0,
  129,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         70,
   71,  369,  258,  282,  275,  284,  319,  258,  258,  435,
  277,  338,  373,  329,  258,  294,  377,  284,  386,   99,
  100,  101,  102,  282,  261,  284,  275,  297,  305,  296,
  257,  258,  400,  401,  282,  294,  284,  260,  433,  110,
  111,  436,  113,  260,  357,  260,  373,  260,  285,  286,
  292,  258,  260,  259,  299,  322,  299,  373,  374,  329,
  373,  338,  260,  260,  260,  302,  260,  260,  139,  260,
  141,  259,  259,  315,  258,  260,  258,  148,  149,  331,
  332,  333,  260,  335,  336,  337,  328,  358,  340,  341,
  327,  343,  260,  330,  373,  269,  373,  271,  272,  170,
  171,  277,  373,  373,  374,  176,  373,  260,  284,  358,
  260,  348,  183,  350,  373,  260,  187,  188,  189,  190,
  191,  192,  260,  260,  373,  373,  363,  364,  373,  260,
  373,  373,  377,  204,  377,  260,  373,  280,  315,  381,
  392,  284,  213,  214,  215,  216,  322,  261,  291,  220,
  260,  328,  403,  260,  421,  449,  450,  451,  452,  453,
  397,  269,  399,  271,  272,  260,  417,  417,  420,  440,
  422,  285,  286,  417,  445,  431,  428,  260,  430,  431,
  323,  260,  260,  260,  260,  260,  423,  439,  258,  430,
  442,  440,  260,  260,  446,  447,  373,  373,  260,  373,
  437,  438,  454,  455,  381,  260,  458,  459,  460,  461,
  447,  448,  283,  327,  260,  287,  330,  469,  470,  471,
  260,  473,  474,  475,  476,  477,  478,  307,  258,  309,
  373,  311,  304,  313,  348,  315,  350,  309,  260,  280,
  492,  493,  494,  284,  496,  421,  498,  499,  422,  363,
  364,  425,  426,  427,  325,  429,  260,  260,  286,  373,
  434,  260,  260,  631,  632,  373,  286,  260,  260,  301,
  260,  639,  640,  258,  260,  303,  348,  349,  421,  260,
  259,  256,  323,  397,  258,  399,   21,  319,  260,  260,
  265,  260,  260,  260,  260,  260,  258,  368,  369,  274,
  260,  373,  330,  278,  279,  280,  260,  282,  260,  423,
  330,  260,  258,  260,  422,  386,  424,  425,  426,  427,
  348,  429,  258,  437,  438,  357,  434,  260,  348,  400,
  401,  260,  373,  447,  448,  260,  364,  258,  260,  258,
  260,  373,  260,  260,  364,  373,  321,  322,  260,  260,
  260,  260,  260,  373,  260,  287,  260,  260,   91,  260,
  260,  260,  260,  258,  616,  340,  618,  260,  620,  621,
  622,  623,  624,  625,  626,  627,  628,  309,  353,  354,
  421,  356,  260,  358,  260,  360,  361,  362,  363,  641,
  642,  643,  644,  260,  260,  647,  648,  260,  260,  651,
  652,  653,  654,  260,  306,  260,  260,  260,  260,  114,
  260,  262,  263,  484,  260,  260,  348,  349,  260,  490,
  491,  396,  260,  260,  326,  276,  260,  260,  260,  260,
  260,  502,  334,  504,  336,  260,  260,  260,  289,  260,
  260,  373,  260,  260,  108,  260,  348,  260,  423,  520,
  521,  260,  260,  524,  525,  526,  527,  528,  529,  310,
  260,  312,  313,  260,  439,  260,  260,  260,  539,  540,
  260,  373,  543,  544,  260,  142,  137,   96,  130,  295,
  331,  217,  247,  231,  335,  556,  243,  224,  390,  391,
  259,  393,  394,  168,  193,  346,  266,  348,  268,  164,
  270,  572,  573,  273,  575,  198,  577,  578,  380,  265,
   -1,   -1,   -1,   -1,   -1,   -1,  332,  333,  369,  370,
  371,  372,  373,   -1,   -1,  295,  342,   -1,   -1,  380,
  381,   -1,  348,   -1,   -1,   -1,   -1,  608,  609,  441,
  332,  333,   -1,  359,   -1,   -1,  617,  398,   -1,   -1,
  342,   -1,   -1,   -1,  405,   -1,  348,  373,   -1,  375,
  631,  632,  332,  333,   -1,   -1,   -1,  359,  639,  640,
   -1,   -1,  342,   -1,  645,  646,   -1,   -1,  348,   -1,
   -1,  373,   -1,  375,   -1,   -1,   -1,   -1,   -1,  359,
  406,  442,  443,  444,   -1,  666,   -1,   -1,  669,   -1,
   -1,   -1,   -1,  373,   -1,  375,   -1,   -1,   -1,  680,
  681,  682,  683,   -1,  406,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  701,   -1,   -1,   -1,  404,   -1,  406,   -1,  709,  710,
   -1,   -1,   -1,   -1,   -1,  716,  717,   -1,   -1,  720,
  721,   -1,   -1,   -1,   -1,  726,  727,  728,  729,   -1,
   -1,  732,  733,  261,  262,  263,   -1,   -1,  266,  267,
  268,  269,  270,  271,  272,  273,   -1,   -1,  276,   -1,
  278,   -1,   -1,   -1,   -1,   -1,   -1,  285,  286,  287,
   -1,  289,   -1,   -1,   -1,  293,   -1,  295,   -1,   -1,
   -1,   -1,  300,   -1,  302,  303,  304,   -1,  306,  307,
  308,  309,  310,   -1,  312,  313,   -1,   -1,  316,  317,
   -1,   -1,   -1,   -1,   -1,   -1,  324,  325,  326,  327,
   -1,   -1,  330,  331,  332,  333,  334,  335,  336,   -1,
   -1,   -1,   -1,   -1,  342,   -1,   -1,  345,  346,   -1,
  348,  349,  350,  351,  352,  353,   -1,   -1,   -1,   -1,
   -1,  359,   -1,   -1,   -1,  363,  364,  365,  366,  367,
  368,  369,  370,  371,  372,  373,   -1,  375,  376,   -1,
  378,  379,  380,  381,   -1,   -1,  384,  385,  386,  387,
  388,  389,  390,  391,  392,  393,  394,  395,   -1,  397,
  398,  399,   -1,  262,  263,   -1,  404,  405,  406,   -1,
  408,  409,  410,  411,   -1,   -1,   -1,  276,   -1,   -1,
   -1,   -1,   -1,   -1,  422,  423,  424,  425,  426,  427,
  289,  429,   -1,  262,  263,   -1,  434,   -1,   -1,  437,
  438,  300,   -1,  441,  442,  443,  444,  276,   -1,  447,
  448,  310,   -1,  312,  313,   -1,   -1,   -1,   -1,   -1,
  289,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  300,  331,   -1,   -1,   -1,  335,   -1,   -1,   -1,
   -1,  310,   -1,  312,  313,   -1,   -1,  346,   -1,  348,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  331,   -1,   -1,   -1,  335,   -1,   -1,   -1,
  369,  370,  371,  372,  373,   -1,   -1,  346,   -1,  348,
   -1,  380,  381,  262,  263,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,   -1,  398,
  369,  370,  371,  372,  373,   -1,  405,   -1,   -1,   -1,
  289,  380,  381,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  300,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  398,
   -1,  310,  326,  312,  313,   -1,  405,  264,   -1,   -1,
  334,   -1,  336,  442,  443,  444,   -1,   -1,  275,   -1,
   -1,  278,  331,   -1,  348,   -1,  335,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  346,   -1,  348,
   -1,  298,   -1,  442,  443,  444,   -1,   -1,   -1,  373,
   -1,   -1,   -1,  310,  311,   -1,   -1,   -1,   -1,   -1,
  369,  370,  371,  372,  373,   -1,  390,  391,   -1,  393,
  394,  380,  381,   -1,  264,   -1,   -1,   -1,   -1,   -1,
  337,   -1,   -1,   -1,  341,  275,   -1,   -1,  278,  398,
   -1,  348,   -1,   -1,   -1,   -1,  405,   -1,   -1,   -1,
   -1,  358,   -1,   -1,   -1,   -1,   -1,   -1,  298,   -1,
   -1,   -1,  369,  370,   -1,   -1,  373,  441,  264,   -1,
  310,  311,   -1,  380,   -1,   -1,   -1,   -1,   -1,  275,
   -1,   -1,  278,  442,  443,  444,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  337,   -1,   -1,
  407,  341,  298,   -1,   -1,   -1,   -1,   -1,  348,  264,
   -1,   -1,  419,  420,  310,  311,  423,   -1,  358,   -1,
  275,  428,   -1,  278,   -1,  432,   -1,   -1,   -1,  369,
  370,   -1,   -1,  373,   -1,   -1,   -1,   -1,   -1,  446,
  380,  337,   -1,  298,   -1,  341,   -1,   -1,   -1,   -1,
   -1,   -1,  348,   -1,   -1,  310,  311,   -1,   -1,   -1,
   -1,   -1,  358,   -1,   -1,   -1,   -1,  407,   -1,   -1,
   -1,   -1,   -1,  369,  370,   -1,   -1,  373,   -1,  419,
  420,   -1,  337,  423,  380,   -1,  341,   -1,  428,   -1,
   -1,   -1,  432,  348,  264,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  358,   -1,  275,  446,   -1,  278,   -1,
   -1,  407,   -1,   -1,  369,  370,   -1,   -1,  373,   -1,
   -1,   -1,   -1,  419,  420,  380,   -1,  423,  298,   -1,
   -1,   -1,  428,   -1,   -1,  264,  432,   -1,   -1,   -1,
  310,  311,   -1,   -1,   -1,   -1,  275,   -1,   -1,  278,
  446,   -1,  407,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  419,  420,   -1,  337,  423,   -1,
   -1,  341,   -1,  428,   -1,   -1,   -1,  432,  348,   -1,
   -1,  310,  311,   -1,   -1,   -1,   -1,   -1,  358,   -1,
   -1,  446,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  369,
  370,   -1,   -1,  373,   -1,   -1,   -1,   -1,  337,  265,
  380,   -1,  341,   -1,   -1,   -1,   -1,   -1,  274,  348,
   -1,   -1,  278,  279,  280,   -1,  282,   -1,   -1,  358,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  407,   -1,   -1,
  369,  370,   -1,   -1,  373,   -1,   -1,   -1,   -1,  419,
  420,  380,   -1,  423,  281,   -1,   -1,  284,  428,   -1,
   -1,  288,  432,  290,   -1,  321,  322,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  446,   -1,  407,   -1,
   -1,   -1,   -1,   -1,  340,   -1,   -1,  314,   -1,   -1,
  419,  420,   -1,   -1,  423,   -1,   -1,  353,  354,  428,
  356,   -1,  358,  432,  360,  361,  362,  363,   -1,  281,
   -1,   -1,  284,  340,   -1,   -1,  288,  446,   -1,   -1,
   -1,  348,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  356,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  396,   -1,  314,   -1,   -1,   -1,  373,   -1,   -1,   -1,
   -1,   -1,   -1,  380,   -1,  382,  383,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  423,  340,   -1,
   -1,   -1,   -1,  400,  401,  402,  348,   -1,   -1,   -1,
   -1,   -1,   -1,  439,  356,  412,  413,  414,  415,  416,
   -1,  418,   -1,   -1,  421,   -1,   -1,   -1,   -1,   -1,
   -1,  373,   -1,   -1,   -1,  432,   -1,   -1,  380,   -1,
  382,  383,  439,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  295,   -1,  400,  401,
  402,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  307,  308,
  412,  413,  414,  415,  416,   -1,  418,  316,  317,  421,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  432,   -1,   -1,  332,  333,  295,   -1,  439,   -1,   -1,
   -1,   -1,   -1,  342,   -1,   -1,   -1,  307,  308,  348,
   -1,   -1,  351,  352,   -1,   -1,  316,  317,   -1,   -1,
  359,   -1,   -1,   -1,   -1,   -1,   -1,  366,  367,   -1,
   -1,   -1,  332,  333,  373,   -1,  375,   -1,   -1,   -1,
   -1,   -1,  342,  295,   -1,   -1,   -1,  386,  348,   -1,
  389,  351,  352,   -1,   -1,  307,  308,   -1,   -1,  359,
   -1,   -1,   -1,   -1,  316,  317,   -1,  406,   -1,  408,
  409,  410,  411,  373,   -1,  375,   -1,   -1,  378,  379,
  332,  333,   -1,   -1,  384,  385,  386,  387,  388,  389,
  342,  295,  392,   -1,   -1,  395,  348,   -1,   -1,  351,
  352,   -1,   -1,  307,  308,   -1,  406,  359,   -1,   -1,
   -1,   -1,  316,  317,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  373,   -1,  375,   -1,   -1,  378,  379,  332,  333,
   -1,   -1,  384,  385,  386,  387,  388,  389,  342,  295,
  392,   -1,   -1,  395,  348,   -1,   -1,  351,  352,   -1,
   -1,  307,  308,   -1,  406,  359,   -1,   -1,   -1,   -1,
  316,  317,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  373,
   -1,  375,   -1,   -1,  378,  379,  332,  333,   -1,   -1,
  384,  385,  386,  387,  388,  389,  342,  295,  392,   -1,
   -1,  395,  348,   -1,   -1,  351,  352,   -1,   -1,  307,
  308,   -1,  406,  359,   -1,   -1,   -1,   -1,  316,  317,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  373,   -1,  375,
   -1,   -1,  378,  379,  332,  333,   -1,   -1,  384,  385,
  386,  387,  388,  389,  342,  295,  392,   -1,   -1,  395,
  348,   -1,   -1,  351,  352,   -1,   -1,   -1,  308,   -1,
  406,  359,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  267,  373,  269,  375,  271,  272,
  378,  379,  332,  333,   -1,  278,  384,  385,  386,  387,
  388,  389,  342,   -1,  392,   -1,   -1,  395,  348,   -1,
  293,  267,  352,  269,   -1,  271,  272,   -1,  406,  359,
   -1,   -1,  278,   -1,   -1,   -1,  366,   -1,   -1,   -1,
   -1,   -1,   -1,  373,   -1,  375,   -1,   -1,   -1,   -1,
   -1,  324,  325,   -1,   -1,   -1,  386,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  345,   -1,   -1,  348,  406,   -1,  324,  325,
  353,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  365,   -1,   -1,  368,   -1,   -1,   -1,  345,
  373,   -1,  348,  376,   -1,   -1,   -1,  353,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  365,
   -1,   -1,  368,   -1,   -1,   -1,   -1,  373,   -1,   -1,
  376,
};
}
final static short YYFINAL=21;
final static short YYMAXTOKEN=453;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"REAL","INTEGER","LOGICAL","NAME","TK_ABSOLUTE_USE","TK_AMP_END",
"TK_AMP_START","TK_ASCII","TK_BRAIN","TK_CA_EXP","TK_CA_EXTERNAL",
"TK_CA_HALF_MIN","TK_CA_INTERNAL","TK_CA_SCALE","TK_CA_SPIKE_INC","TK_CA_TAU",
"TK_CA_TAU_SCALE","TK_CELL","TK_CELLS","TK_CELLS_PER_FREQ","TK_CELL_TYPE",
"TK_CHANNEL","TK_COLUMN","TK_CSHELL","TK_COLUMN_TYPE","TK_CMP","TK_G",
"TK_CONNECT","TK_DATA_LABEL","TK_DELAY","TK_DEPR_TAU","TK_DURATION",
"TK_DYN_RANGE","TK_END_BRAIN","TK_END_COLUMN","TK_END_CSHELL","TK_END_CMP",
"TK_END_CELL","TK_END_CHANNEL","TK_END_LAYER","TK_END_LSHELL","TK_END_REPORT",
"TK_END_SPIKE","TK_END_STIMULUS","TK_END_ST_INJECT","TK_END_SYNAPSE",
"TK_END_SYN_DATA","TK_END_SYN_FD","TK_END_SYN_PSG","TK_END_SYN_LEARN",
"TK_E_HALF_MIN_H","TK_E_HALF_MIN_M","TK_FACIL_TAU","TK_FILENAME","TK_FREQUENCY",
"TK_FREQ_ROWS","TK_FREQ_START","TK_FSV","TK_HEIGHT","TK_H_INITIAL","TK_H_POWER",
"TK_IGNORE_EMPTY","TK_INJECT","TK_INTERACTIVE","TK_LAYER","TK_LSHELL",
"TK_LAYER_TYPE","TK_LEAK_G","TK_LEAK_REVERSAL","TK_LEARN","TK_LEARN_LABEL",
"TK_LOCATION","TK_LOWER","TK_MAX_G","TK_MODE","TK_M_INITIAL","TK_M_POWER",
"TK_NEG_HEB_WINDOW","TK_PATTERN","TK_POS_HEB_WINDOW","TK_PROB","TK_PSG_FILE",
"TK_RELOAD_SYN","TK_REPORT","TK_REPORT_ON","TK_REVERSAL","TK_RSE",
"TK_RSE_LABEL","TK_R_MEMBRANE","TK_SAMESEED","TK_SAVE_SYN","TK_SEED","TK_SFD",
"TK_SFD_LABEL","TK_SLOPE_H","TK_SLOPE_M","TK_SPIKE","TK_STIMULUS","TK_SPIKE_HW",
"TK_ST_INJECT","TK_STIM_TYPE","TK_SYNAPSE","TK_STRENGTH","TK_SYN_DATA",
"TK_SYN_FD","TK_SYN_LEARN","TK_SYN_PSG","TK_SYN_REVERSAL","TK_TAU_MEMBRANE",
"TK_TAU_SCALE_M","TK_TAU_SCALE_H","TK_THRESHOLD","TK_TIME_END","TK_TIME_START",
"TK_TIME_FREQ_INC","TK_TIMING","TK_TYPE","TK_UPPER","TK_UNITARY_G","TK_VMREST",
"TK_VOLTAGES","TK_VTAU_VAL_M","TK_VTAU_VAL_H","TK_PORT","TK_WIDTH","TK_JOB",
"TK_DISTRIBUTE","TK_VAL_M_STDEV","TK_VOLT_M_STDEV","TK_SLOPE_M_STDEV",
"TK_VAL_H_STDEV","TK_VOLT_H_STDEV","TK_SLOPE_H_STDEV",
"TK_NEG_HEB_PEAK_DELTA_USE","TK_NEG_HEB_PEAK_TIME","TK_VTAU_VOLT_M",
"TK_POS_HEB_PEAK_DELTA_USE","TK_POS_HEB_PEAK_TIME","TK_VTAU_VOLT_H",
"TK_INCLUDE","TK_RSE_INIT","TK_VERT_TRANS","TK_PREV_SPIKE_RANGE",
"TK_CONNECT_RPT","TK_SPIKE_RPT","TK_SERVER","TK_SINGLE","TK_CA_EXP_RANGE",
"TK_PHASE_SHIFT","TK_STRENGTH_RANGE","TK_SYNAPSE_RSE","TK_ALPHA_SCALE_H",
"TK_ALPHA_SCALE_M","TK_BETA_SCALE_H","TK_BETA_SCALE_M","TK_SAVE","TK_LOAD",
"TK_DISTANCE","TK_OUTPUT_CONNECT_MAP","TK_OUTPUT_CELLS","TK_AUTO",
"TK_SERVER_PORT","TK_VERSION","TK_SYNAPSE_UF","TK_RECURRENT_CONNECT","TK_ALPHA",
"TK_SYN_AUGMENTATION","TK_END_SYN_AUGMENTATION","TK_MAX_AUGMENTATION",
"TK_AUGMENTATION_INIT","TK_AUGMENTATION_TAU","TK_SYN_CALCIUM","TK_CA_TAU_DECAY",
"TK_EXP","TK_SELECT_FRONT","TK_OPTION","TK_AVERAGE_SYN","TK_AUGMENTATION_DELAY",
"TK_WARNINGS_OFF","TK_HIDE_TIMESTEP","TK_HEBB_START","TK_HEBB_END","TK_EVENT",
"TK_OVERRIDE","TK_LEARN_SHAPE","TK_RATE","TK_TAU_NOISE","TK_CORREL",
"TK_END_EVENT","TK_CELL_SEQUENCE","TK_SFD_START","TK_SFD_END","TK_Km","TK_Kahp",
"TK_Ka","TK_Na","TK_Knicd",
};
final static String yyrule[] = {
"$accept : input",
"input : element",
"input : input element",
"input : input error",
"element : brain",
"element : column",
"element : colshell",
"element : layer",
"element : lshell",
"element : cell",
"element : compart",
"element : channel",
"element : synapse",
"element : syn_psg",
"element : syn_fd",
"element : syn_learn",
"element : syn_data",
"element : syn_augmentation",
"element : spikeshape",
"element : stimulus",
"element : stinject",
"element : report",
"element : event",
"element : TK_INCLUDE NAME",
"$$1 :",
"brain : TK_BRAIN $$1 brainvars TK_END_BRAIN",
"brainvars : brainvar",
"brainvars : brainvars brainvar",
"brainvar : TK_TYPE NAME",
"brainvar : TK_JOB NAME",
"brainvar : TK_DISTRIBUTE NAME",
"brainvar : TK_CONNECT_RPT",
"brainvar : TK_SPIKE_RPT",
"brainvar : TK_FSV value",
"brainvar : TK_DURATION value",
"brainvar : TK_SEED INTEGER",
"brainvar : TK_COLUMN_TYPE NAME",
"brainvar : TK_ST_INJECT NAME",
"brainvar : TK_REPORT NAME",
"brainvar : TK_CONNECT NAME NAME NAME NAME NAME NAME NAME NAME NAME value value",
"brainvar : TK_RECURRENT_CONNECT NAME NAME NAME NAME NAME NAME NAME NAME NAME value value",
"brainvar : TK_SAVE NAME value",
"brainvar : TK_LOAD NAME",
"brainvar : TK_PORT INTEGER",
"brainvar : TK_PORT TK_AUTO",
"brainvar : TK_PORT TK_SINGLE",
"brainvar : TK_SERVER NAME",
"brainvar : TK_SERVER_PORT INTEGER",
"brainvar : TK_DISTANCE LOGICAL",
"brainvar : TK_OUTPUT_CELLS LOGICAL",
"brainvar : TK_OUTPUT_CONNECT_MAP LOGICAL",
"brainvar : TK_OPTION TK_WARNINGS_OFF",
"brainvar : TK_EVENT NAME",
"$$2 :",
"colshell : TK_CSHELL $$2 cshvars TK_END_CSHELL",
"cshvars : cshvar",
"cshvars : cshvars cshvar",
"cshvar : TK_TYPE NAME",
"cshvar : TK_WIDTH value",
"cshvar : TK_HEIGHT value",
"cshvar : TK_LOCATION value value",
"$$3 :",
"column : TK_COLUMN $$3 colvars TK_END_COLUMN",
"colvars : colvar",
"colvars : colvars colvar",
"colvar : TK_TYPE NAME",
"colvar : TK_CSHELL NAME",
"colvar : TK_LAYER_TYPE NAME",
"colvar : TK_CONNECT NAME NAME NAME NAME NAME NAME NAME value value",
"colvar : TK_RECURRENT_CONNECT NAME NAME NAME NAME NAME NAME NAME value value",
"$$4 :",
"lshell : TK_LSHELL $$4 lsvars TK_END_LSHELL",
"lsvars : lsvar",
"lsvars : lsvars lsvar",
"lsvar : TK_TYPE NAME",
"lsvar : TK_LOWER value",
"lsvar : TK_UPPER value",
"$$5 :",
"layer : TK_LAYER $$5 lvars TK_END_LAYER",
"lvars : lvar",
"lvars : lvars lvar",
"lvar : TK_TYPE NAME",
"lvar : TK_LSHELL NAME",
"lvar : TK_CELL_TYPE NAME INTEGER",
"lvar : TK_CONNECT NAME NAME NAME NAME NAME value value",
"lvar : TK_RECURRENT_CONNECT NAME NAME NAME NAME NAME value value",
"$$6 :",
"cell : TK_CELL $$6 cellvars TK_END_CELL",
"cellvars : cellvar",
"cellvars : cellvars cellvar",
"cellvar : TK_TYPE NAME",
"cellvar : TK_CMP NAME NAME value value",
"cellvar : TK_CMP NAME NAME value value value",
"cellvar : TK_CONNECT NAME NAME value value",
"$$7 :",
"compart : TK_CMP $$7 cmpvars TK_END_CMP",
"cmpvars : cmpvar",
"cmpvars : cmpvars cmpvar",
"cmpvar : TK_TYPE NAME",
"cmpvar : TK_SEED INTEGER",
"cmpvar : TK_SPIKE NAME",
"cmpvar : TK_CHANNEL NAME",
"$$8 :",
"cmpvar : TK_TAU_MEMBRANE $$8 twovalue",
"$$9 :",
"cmpvar : TK_R_MEMBRANE $$9 twovalue",
"$$10 :",
"cmpvar : TK_THRESHOLD $$10 twovalue",
"$$11 :",
"cmpvar : TK_LEAK_REVERSAL $$11 twovalue",
"$$12 :",
"cmpvar : TK_LEAK_G $$12 twovalue",
"$$13 :",
"cmpvar : TK_VMREST $$13 twovalue",
"$$14 :",
"cmpvar : TK_CA_INTERNAL $$14 twovalue",
"$$15 :",
"cmpvar : TK_CA_EXTERNAL $$15 twovalue",
"$$16 :",
"cmpvar : TK_CA_TAU $$16 twovalue",
"$$17 :",
"cmpvar : TK_CA_SPIKE_INC $$17 twovalue",
"$$18 :",
"channel : TK_CHANNEL TK_Km $$18 Kmvars TK_END_CHANNEL",
"$$19 :",
"channel : TK_CHANNEL TK_Kahp $$19 Kahpvars TK_END_CHANNEL",
"$$20 :",
"channel : TK_CHANNEL TK_Ka $$20 Kavars TK_END_CHANNEL",
"$$21 :",
"channel : TK_CHANNEL TK_Na $$21 Navars TK_END_CHANNEL",
"$$22 :",
"channel : TK_CHANNEL TK_Knicd $$22 Knicdvars TK_END_CHANNEL",
"Kmvars : chvar",
"Kmvars : Kmvars chvar",
"Kmvars : Kmvars Kmvar",
"Kahpvars : chvar",
"Kahpvars : Kahpvars chvar",
"Kahpvars : Kahpvars Kahpvar",
"Kavars : chvar",
"Kavars : Kavars chvar",
"Kavars : Kavars Kavar",
"Navars : chvar",
"Navars : Navars chvar",
"Navars : Navars Navar",
"Knicdvars : chvar",
"Knicdvars : Knicdvars chvar",
"chvar : TK_TYPE NAME",
"$$23 :",
"chvar : TK_M_POWER $$23 twovalue",
"$$24 :",
"chvar : TK_UNITARY_G $$24 twovalue",
"$$25 :",
"chvar : TK_STRENGTH $$25 twovalue",
"$$26 :",
"chvar : TK_STRENGTH_RANGE $$26 twovalue",
"$$27 :",
"chvar : TK_M_INITIAL $$27 twovalue",
"$$28 :",
"chvar : TK_REVERSAL $$28 twovalue",
"chvar : TK_SEED INTEGER",
"$$29 :",
"Kmvar : TK_E_HALF_MIN_M $$29 twovalue",
"$$30 :",
"Kmvar : TK_TAU_SCALE_M $$30 twovalue",
"Kmvar : TK_SLOPE_M value value value",
"Kmvar : TK_SLOPE_M_STDEV value",
"$$31 :",
"Kahpvar : TK_CA_SCALE $$31 twovalue",
"$$32 :",
"Kahpvar : TK_CA_EXP $$32 twovalue",
"$$33 :",
"Kahpvar : TK_CA_EXP_RANGE $$33 twovalue",
"$$34 :",
"Kahpvar : TK_CA_HALF_MIN $$34 twovalue",
"$$35 :",
"Kahpvar : TK_CA_TAU_SCALE $$35 twovalue",
"$$36 :",
"Kavar : TK_H_INITIAL $$36 twovalue",
"$$37 :",
"Kavar : TK_H_POWER $$37 twovalue",
"$$38 :",
"Kavar : TK_E_HALF_MIN_M $$38 twovalue",
"$$39 :",
"Kavar : TK_E_HALF_MIN_H $$39 twovalue",
"Kavar : TK_SLOPE_M value",
"Kavar : TK_SLOPE_M_STDEV value",
"Kavar : TK_SLOPE_H value",
"Kavar : TK_SLOPE_H_STDEV value",
"Kavar : TK_VAL_M_STDEV value",
"Kavar : TK_VOLT_M_STDEV value",
"Kavar : TK_VAL_H_STDEV value",
"Kavar : TK_VOLT_H_STDEV value",
"$$40 :",
"Kavar : TK_VTAU_VAL_M $$40 values",
"$$41 :",
"Kavar : TK_VTAU_VOLT_M $$41 values",
"$$42 :",
"Kavar : TK_VTAU_VAL_H $$42 values",
"$$43 :",
"Kavar : TK_VTAU_VOLT_H $$43 values",
"$$44 :",
"Navar : TK_E_HALF_MIN_M $$44 twovalue",
"$$45 :",
"Navar : TK_E_HALF_MIN_H $$45 twovalue",
"$$46 :",
"Navar : TK_H_INITIAL $$46 twovalue",
"$$47 :",
"Navar : TK_H_POWER $$47 twovalue",
"$$48 :",
"Navar : TK_TAU_SCALE_M $$48 twovalue",
"$$49 :",
"Navar : TK_TAU_SCALE_H $$49 twovalue",
"Navar : TK_SLOPE_M value value",
"Navar : TK_SLOPE_H value value",
"Navar : TK_SLOPE_M_STDEV value",
"Navar : TK_SLOPE_H_STDEV value",
"$$50 :",
"Navar : TK_ALPHA_SCALE_M $$50 twovalue",
"$$51 :",
"Navar : TK_BETA_SCALE_M $$51 twovalue",
"$$52 :",
"Navar : TK_ALPHA_SCALE_H $$52 twovalue",
"$$53 :",
"Navar : TK_BETA_SCALE_H $$53 twovalue",
"$$54 :",
"synapse : TK_SYNAPSE $$54 synvars TK_END_SYNAPSE",
"synvars : synvar",
"synvars : synvars synvar",
"synvar : TK_TYPE NAME",
"synvar : TK_SEED INTEGER",
"synvar : TK_SFD_LABEL NAME",
"synvar : TK_LEARN_LABEL NAME",
"synvar : TK_DATA_LABEL NAME",
"synvar : TK_SYN_AUGMENTATION NAME",
"synvar : TK_SYN_PSG NAME",
"$$55 :",
"synvar : TK_ABSOLUTE_USE $$55 twovalue",
"$$56 :",
"synvar : TK_DELAY $$56 twovalue",
"$$57 :",
"synvar : TK_SYN_REVERSAL $$57 twovalue",
"synvar : TK_MAX_G value",
"$$58 :",
"synvar : TK_RSE_INIT $$58 twovalue",
"$$59 :",
"synvar : TK_PREV_SPIKE_RANGE $$59 twovalue",
"synvar : TK_HEBB_START value",
"synvar : TK_HEBB_END value",
"synvar : TK_SFD_START value",
"synvar : TK_SFD_END value",
"$$60 :",
"syn_psg : TK_SYN_PSG $$60 spvars TK_END_SYN_PSG",
"spvars : spvar",
"spvars : spvars spvar",
"spvar : TK_TYPE NAME",
"spvar : TK_PSG_FILE NAME",
"$$61 :",
"syn_fd : TK_SYN_FD $$61 sfvars TK_END_SYN_FD",
"sfvars : sfvar",
"sfvars : sfvars sfvar",
"sfvar : TK_TYPE NAME",
"sfvar : TK_SEED INTEGER",
"sfvar : TK_SFD NAME",
"$$62 :",
"sfvar : TK_FACIL_TAU $$62 twovalue",
"$$63 :",
"sfvar : TK_DEPR_TAU $$63 twovalue",
"$$64 :",
"syn_learn : TK_SYN_LEARN $$64 slvars TK_END_SYN_LEARN",
"slvars : slvar",
"slvars : slvars slvar",
"slvar : TK_TYPE NAME",
"slvar : TK_SEED INTEGER",
"slvar : TK_LEARN NAME",
"slvar : TK_LEARN_SHAPE NAME",
"$$65 :",
"slvar : TK_NEG_HEB_WINDOW $$65 twovalue",
"$$66 :",
"slvar : TK_POS_HEB_WINDOW $$66 twovalue",
"$$67 :",
"slvar : TK_NEG_HEB_PEAK_DELTA_USE $$67 twovalue",
"$$68 :",
"slvar : TK_POS_HEB_PEAK_DELTA_USE $$68 twovalue",
"$$69 :",
"slvar : TK_NEG_HEB_PEAK_TIME $$69 twovalue",
"$$70 :",
"slvar : TK_POS_HEB_PEAK_TIME $$70 twovalue",
"$$71 :",
"syn_data : TK_SYN_DATA $$71 sdvars TK_END_SYN_DATA",
"sdvars : sdvar",
"sdvars : sdvars sdvar",
"sdvar : TK_TYPE NAME",
"sdvar : TK_SEED INTEGER",
"sdvar : TK_MAX_G value",
"$$72 :",
"sdvar : TK_DELAY $$72 twovalue",
"$$73 :",
"sdvar : TK_SYN_REVERSAL $$73 twovalue",
"$$74 :",
"syn_augmentation : TK_SYN_AUGMENTATION $$74 savars TK_END_SYN_AUGMENTATION",
"savars : savar",
"savars : savars savar",
"savar : TK_TYPE NAME",
"$$75 :",
"savar : TK_CA_INTERNAL $$75 twovalue",
"$$76 :",
"savar : TK_CA_TAU_DECAY $$76 twovalue",
"$$77 :",
"savar : TK_CA_TAU $$77 twovalue",
"$$78 :",
"savar : TK_CA_SPIKE_INC $$78 twovalue",
"$$79 :",
"savar : TK_MAX_AUGMENTATION $$79 twovalue",
"$$80 :",
"savar : TK_ALPHA $$80 twovalue",
"$$81 :",
"savar : TK_AUGMENTATION_INIT $$81 twovalue",
"$$82 :",
"savar : TK_AUGMENTATION_TAU $$82 twovalue",
"$$83 :",
"savar : TK_AUGMENTATION_DELAY $$83 twovalue",
"$$84 :",
"spikeshape : TK_SPIKE $$84 spikevars TK_END_SPIKE",
"spikevars : spikevar",
"spikevars : spikevars spikevar",
"spikevar : TK_TYPE NAME",
"$$85 :",
"spikevar : TK_VOLTAGES $$85 values",
"$$86 :",
"stimulus : TK_STIMULUS $$86 stvars TK_END_STIMULUS",
"stvars : stvar",
"stvars : stvars stvar",
"stvar : TK_TYPE NAME",
"stvar : TK_MODE NAME",
"stvar : TK_PATTERN NAME",
"stvar : TK_VERT_TRANS value",
"stvar : TK_PHASE_SHIFT value",
"stvar : TK_RATE value",
"stvar : TK_TAU_NOISE value",
"stvar : TK_CORREL value",
"stvar : TK_TIMING NAME",
"stvar : TK_FILENAME NAME",
"stvar : TK_PORT INTEGER",
"stvar : TK_PORT TK_AUTO",
"stvar : TK_SAMESEED LOGICAL",
"stvar : TK_SEED INTEGER",
"stvar : TK_FREQ_ROWS INTEGER",
"stvar : TK_CELLS_PER_FREQ INTEGER",
"stvar : TK_TIME_FREQ_INC value",
"$$87 :",
"stvar : TK_DYN_RANGE $$87 twovalue",
"stvar : TK_AMP_START value",
"stvar : TK_AMP_END value",
"stvar : TK_WIDTH value",
"stvar : TK_FREQ_START value",
"$$88 :",
"stvar : TK_TIME_START $$88 values",
"$$89 :",
"stvar : TK_TIME_END $$89 values",
"$$90 :",
"stinject : TK_ST_INJECT $$90 stivars TK_END_ST_INJECT",
"stivars : stivar",
"stivars : stivars stivar",
"stivar : TK_TYPE NAME",
"stivar : TK_STIM_TYPE NAME",
"stivar : TK_INJECT NAME NAME NAME NAME value",
"$$91 :",
"report : TK_REPORT $$91 reportvars TK_END_REPORT",
"reportvars : reportvar",
"reportvars : reportvars reportvar",
"reportvar : TK_TYPE NAME",
"reportvar : TK_CELLS NAME NAME NAME NAME",
"reportvar : TK_FILENAME NAME",
"reportvar : TK_PORT INTEGER",
"reportvar : TK_PORT TK_AUTO",
"reportvar : TK_ASCII",
"reportvar : TK_ASCII TK_EXP",
"reportvar : TK_CHANNEL NAME",
"reportvar : TK_REPORT_ON NAME",
"reportvar : TK_CELL_SEQUENCE NAME",
"reportvar : TK_PROB value",
"reportvar : TK_FREQUENCY value",
"$$92 :",
"reportvar : TK_TIME_START $$92 values",
"$$93 :",
"reportvar : TK_TIME_END $$93 values",
"reportvar : TK_SYNAPSE NAME",
"reportvar : TK_SYNAPSE_RSE NAME",
"reportvar : TK_SYNAPSE_UF NAME",
"reportvar : TK_SYN_AUGMENTATION NAME",
"reportvar : TK_SYN_CALCIUM NAME",
"reportvar : TK_SEED INTEGER",
"reportvar : TK_SEED TK_SELECT_FRONT",
"reportvar : TK_VERSION INTEGER",
"reportvar : TK_OPTION reportoptions",
"$$94 :",
"event : TK_EVENT $$94 eventvars TK_END_EVENT",
"eventvars : eventvar",
"eventvars : eventvars eventvar",
"eventvar : TK_TYPE NAME",
"eventvar : TK_SYNAPSE NAME",
"eventvar : TK_CELLS NAME NAME NAME NAME",
"eventvar : TK_OVERRIDE NAME value",
"reportoptions : singleoption",
"reportoptions : reportoptions singleoption",
"singleoption : TK_AVERAGE_SYN",
"singleoption : TK_HIDE_TIMESTEP",
"values : value",
"values : values value",
"twovalue : value value",
"twovalue : value",
"value : INTEGER",
"value : REAL",
};

//#line 679 "parse.yaccj"

//Brain Stuff
public Brain brain;

public ArrayList<ColumnShell> columnShellList = new ArrayList<ColumnShell>();
public ArrayList<Column> columnList = new ArrayList<Column>();

public ArrayList<LayerShell> layerShellList = new ArrayList<LayerShell>();
public ArrayList<Layer> layerList = new ArrayList<Layer>();

public ArrayList<Cell> cellList = new ArrayList<Cell>();
public ArrayList<Compartment> compartmentList = new ArrayList<Compartment>();

public ArrayList<Channel> channelList = new ArrayList<Channel>();

public ArrayList<Synapse> synapseList = new ArrayList<Synapse>();
public ArrayList<SynFacilDepress> synFacilDepressList = new ArrayList<SynFacilDepress>();
public ArrayList<SynLearning> synLearningList = new ArrayList<SynLearning>();
public ArrayList<SynData> synDataList = new ArrayList<SynData>();
public ArrayList<SynAugmentation> synAugList = new ArrayList<SynAugmentation>();

public ArrayList<SpikeShape> spikeshapeList = new ArrayList<SpikeShape>();

public ArrayList<Stimulus> stimulusList = new ArrayList<Stimulus>();
public ArrayList<StimulusInject> stimulusInjectList = new ArrayList<StimulusInject>();

public ArrayList<Report> reportList = new ArrayList<Report>();

public ArrayList<Event> eventList = new ArrayList<Event>();

public ArrayList<Connect> connectList = new ArrayList<Connect>();
public ArrayList<RecurrentConnect> recurrentConnectList = new ArrayList<RecurrentConnect>();
public ArrayList<CompartmentConnect> compConnectList = new ArrayList<CompartmentConnect>();
public ArrayList<SynPSG> synpsgList = new ArrayList<SynPSG>();

//For all those pesky mean/stdev values
protected TwoValue twoPtr;

@SuppressWarnings("rawtypes")
protected ArrayList listPtr;

protected Stack<String> filestack = new Stack<String>();

@SuppressWarnings("unused")
private int yywrap() {

	return 0;
	
	}

private void yyerror(String string) {
	
	}
		
private int yylex() {
	
	try {
		
		return scan.yylex();
		
		}
	
	catch (IOException e) {
		
		e.printStackTrace();
		
		}
	
	return 0;
	
	}

public Parser( String filename ) {
	
	addFile(filename);
	
	}

public void addFile( String filename ) {
	
	filestack.push( filename );
	
	}

public void loadFile( String filename ) {
	
	String fileContents = new String();
	
	try {
		
		fileContents = readFileAsString( filename );
		
		}
	
	catch (IOException e){
		
		e.printStackTrace();
		
		}
	
	if(scan == null)
		
		scan = new Scanner(new StringReader( fileContents ), this );
	
	else
		
		scan.yyreset( new StringReader( fileContents ) );
	
	}

private Scanner scan;

public void run() {
	
	while( !filestack.empty() ) {
		
		loadFile( filestack.pop() );
		
		yyparse();
		
		}
	
	}
	
private static String readFileAsString(String filePath) throws java.io.IOException{
    byte[] buffer = new byte[(int) new File(filePath).length()];
    BufferedInputStream f = null;
    try {
        f = new BufferedInputStream(new FileInputStream(filePath));
        f.read(buffer);
    } finally {
        if (f != null) try { f.close(); } catch (IOException ignored) { }
    }
    return new String(buffer);
}

//#line 1674 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 23:
//#line 99 "parse.yaccj"
{ addFile( val_peek(0).sval ); }
break;
case 24:
//#line 104 "parse.yaccj"
{ brain = new Brain(); }
break;
case 28:
//#line 111 "parse.yaccj"
{ brain.type = val_peek(0).sval; }
break;
case 29:
//#line 112 "parse.yaccj"
{ brain.job = val_peek(0).sval; }
break;
case 30:
//#line 113 "parse.yaccj"
{  }
break;
case 31:
//#line 114 "parse.yaccj"
{  }
break;
case 32:
//#line 115 "parse.yaccj"
{  }
break;
case 33:
//#line 116 "parse.yaccj"
{ brain.FSV         = val_peek(0).dval; }
break;
case 34:
//#line 117 "parse.yaccj"
{ brain.duration    = val_peek(0).dval; }
break;
case 35:
//#line 118 "parse.yaccj"
{ brain.seed        = val_peek(0).ival; }
break;
case 36:
//#line 119 "parse.yaccj"
{ brain.columnTypeNames.add( val_peek(0).sval ); }
break;
case 37:
//#line 120 "parse.yaccj"
{ brain.stimulusInjectNames.add( val_peek(0).sval ); }
break;
case 38:
//#line 121 "parse.yaccj"
{ brain.reportNames.add( val_peek(0).sval); }
break;
case 39:
//#line 123 "parse.yaccj"
{
		   
		   Connect i = new Connect( val_peek(10).sval, val_peek(9).sval, val_peek(8).sval, val_peek(7).sval,  val_peek(6).sval, val_peek(5).sval, val_peek(4).sval, val_peek(3).sval, val_peek(2).sval, val_peek(1).dval, val_peek(0).dval );
		   
		   brain.connect.add( i );
		                                   
		   connectList.add( i );
		     
		   }
break;
case 40:
//#line 133 "parse.yaccj"
{
			
			RecurrentConnect r = new RecurrentConnect( null, null, val_peek(10).sval, val_peek(9).sval, null, null, val_peek(8).sval, val_peek(7).sval,  val_peek(6).sval, val_peek(5).dval, val_peek(4).dval );
			   
			layerList.get( layerList.size() - 1 ).recurrentConnects.add( r );
			   
			recurrentConnectList.add( r );
			
			}
break;
case 41:
//#line 143 "parse.yaccj"
{ brain.savefile = val_peek(1).sval; brain.saveTime = val_peek(0).dval; }
break;
case 42:
//#line 144 "parse.yaccj"
{ brain.load = val_peek(0).sval; }
break;
case 43:
//#line 145 "parse.yaccj"
{ brain.port = val_peek(0).ival; }
break;
case 44:
//#line 146 "parse.yaccj"
{ brain.port = -1; }
break;
case 45:
//#line 147 "parse.yaccj"
{ brain.port = -2; }
break;
case 46:
//#line 148 "parse.yaccj"
{ brain.server = val_peek(0).sval; }
break;
case 47:
//#line 149 "parse.yaccj"
{ brain.serverPort = val_peek(0).ival; }
break;
case 48:
//#line 150 "parse.yaccj"
{ brain.distance =  (val_peek(0).ival != 0); }
break;
case 49:
//#line 151 "parse.yaccj"
{ brain.outputCells = ( val_peek(0).ival != 0 );  }
break;
case 50:
//#line 152 "parse.yaccj"
{ brain.outputConnectMap = ( val_peek(0).ival != 0 );  }
break;
case 51:
//#line 153 "parse.yaccj"
{ brain.warningsOn = true;  }
break;
case 52:
//#line 154 "parse.yaccj"
{ brain.eventNames.add( val_peek(0).sval ); }
break;
case 53:
//#line 159 "parse.yaccj"
{

		columnShellList.add( columnShellList.size() , new ColumnShell() ); 
		
		}
break;
case 57:
//#line 170 "parse.yaccj"
{ columnShellList.get( columnShellList.size() - 1).type = val_peek(0).sval; }
break;
case 58:
//#line 171 "parse.yaccj"
{ columnShellList.get( columnShellList.size() - 1).width  = val_peek(0).dval; }
break;
case 59:
//#line 172 "parse.yaccj"
{ columnShellList.get( columnShellList.size() - 1).height = val_peek(0).dval; }
break;
case 60:
//#line 173 "parse.yaccj"
{ columnShellList.get( columnShellList.size() - 1).x = val_peek(1).dval; columnShellList.get(columnShellList.size() - 1).y = val_peek(0).dval; }
break;
case 61:
//#line 178 "parse.yaccj"
{ 
	
	columnList.add( new Column() );
	
	}
break;
case 65:
//#line 189 "parse.yaccj"
{ columnList.get( columnList.size() - 1).type = val_peek(0).sval; }
break;
case 66:
//#line 190 "parse.yaccj"
{ columnList.get( columnList.size() - 1).columnShellName  = val_peek(0).sval; }
break;
case 67:
//#line 191 "parse.yaccj"
{ columnList.get( columnList.size() - 1).layerNames.add( val_peek(0).sval ); }
break;
case 68:
//#line 192 "parse.yaccj"
{ 
               
               Connect i = new Connect( val_peek(8).sval, val_peek(7).sval, val_peek(6).sval, val_peek(5).sval,  val_peek(4).sval, val_peek(3).sval, val_peek(2).sval, val_peek(1).dval, val_peek(0).dval );
               
               columnList.get( columnList.size() - 1).connects.add( i );
               
               connectList.add( i );
             
               }
break;
case 69:
//#line 201 "parse.yaccj"
{
               
               RecurrentConnect r = new RecurrentConnect( null, null, val_peek(8).sval, val_peek(7).sval, null, null, val_peek(6).sval, val_peek(5).sval,  val_peek(4).sval, val_peek(3).dval, val_peek(2).dval );
               
               layerList.get( layerList.size() - 1 ).recurrentConnects.add( r );
               
               recurrentConnectList.add( r );
               
               }
break;
case 70:
//#line 214 "parse.yaccj"
{ layerShellList.add( layerShellList.size() , new LayerShell() ); }
break;
case 74:
//#line 221 "parse.yaccj"
{ layerShellList.get( layerShellList.size() - 1 ).type = val_peek(0).sval; }
break;
case 75:
//#line 222 "parse.yaccj"
{ layerShellList.get( layerShellList.size() - 1 ).lower  = val_peek(0).dval; }
break;
case 76:
//#line 223 "parse.yaccj"
{ layerShellList.get( layerShellList.size() - 1 ).upper  = val_peek(0).dval; }
break;
case 77:
//#line 228 "parse.yaccj"
{ layerList.add( new Layer() ); }
break;
case 81:
//#line 235 "parse.yaccj"
{ layerList.get( layerList.size() - 1 ).type = val_peek(0).sval; }
break;
case 82:
//#line 236 "parse.yaccj"
{ layerList.get( layerList.size() - 1 ).layerShellName = val_peek(0).sval; }
break;
case 83:
//#line 237 "parse.yaccj"
{ layerList.get( layerList.size() - 1 ).cellTypeNames.add( val_peek(1).sval );  layerList.get( layerList.size() - 1 ).cellTypeQuantities.add( val_peek(0).ival ); }
break;
case 84:
//#line 239 "parse.yaccj"
{ 
               Connect i = new Connect( val_peek(6).sval, val_peek(5).sval, val_peek(4).sval, val_peek(3).sval, val_peek(2).sval, val_peek(1).dval, val_peek(0).dval );
               
               layerList.get( layerList.size() - 1 ).connects.add( i );
               	
               connectList.add( i );
               }
break;
case 85:
//#line 248 "parse.yaccj"
{
               
               RecurrentConnect r = new RecurrentConnect( null, null, val_peek(6).sval, val_peek(5).sval, null, null, val_peek(4).sval, val_peek(3).sval,  val_peek(2).sval, val_peek(1).dval, val_peek(0).dval );
               
               layerList.get( layerList.size() - 1 ).recurrentConnects.add( r );
               
               recurrentConnectList.add( r );
               
               }
break;
case 86:
//#line 261 "parse.yaccj"
{ cellList.add( new Cell() ); }
break;
case 90:
//#line 268 "parse.yaccj"
{ cellList.get( cellList.size() - 1 ).type = val_peek(0).sval; }
break;
case 91:
//#line 269 "parse.yaccj"
{ 
       						 cellList.get( cellList.size() - 1 ).compartmentNames.add( val_peek(3).sval );
                             cellList.get( cellList.size() - 1 ).compartmentLabels.add( val_peek(2).sval );
                             cellList.get( cellList.size() - 1 ).xList.add( val_peek(1).dval );
                             cellList.get( cellList.size() - 1 ).yList.add( val_peek(0).dval );
                          }
break;
case 92:
//#line 275 "parse.yaccj"
{
       						 cellList.get( cellList.size() - 1 ).compartmentNames.add( val_peek(4).sval );
                             cellList.get( cellList.size() - 1 ).compartmentLabels.add( val_peek(3).sval );
                             cellList.get( cellList.size() - 1 ).xList.add( new Double(val_peek(2).dval) );
                             cellList.get( cellList.size() - 1 ).yList.add( new Double(val_peek(1).dval) );
       						}
break;
case 93:
//#line 281 "parse.yaccj"
{
			
			CompartmentConnect i = new CompartmentConnect( val_peek(3).sval, val_peek(2).sval, val_peek(1).ival, val_peek(0).dval );
			       
			cellList.get( cellList.size() - 1).connects.add( i );
			   
			compConnectList.add( i );
				
			}
break;
case 94:
//#line 294 "parse.yaccj"
{ compartmentList.add( new Compartment() ); }
break;
case 98:
//#line 301 "parse.yaccj"
{ compartmentList.get( compartmentList.size() - 1).type = val_peek(0).sval; }
break;
case 99:
//#line 302 "parse.yaccj"
{ compartmentList.get( compartmentList.size() - 1).seed = val_peek(0).ival; }
break;
case 100:
//#line 303 "parse.yaccj"
{ compartmentList.get( compartmentList.size() - 1).spikeshapeName = val_peek(0).sval; }
break;
case 101:
//#line 304 "parse.yaccj"
{ compartmentList.get( compartmentList.size() - 1).channelNames.add( val_peek(0).sval ); }
break;
case 102:
//#line 305 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).tauMembrane; }
break;
case 104:
//#line 306 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).rMembrane; }
break;
case 106:
//#line 307 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).threshold; }
break;
case 108:
//#line 308 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).leakReversal; }
break;
case 110:
//#line 309 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).leakConductance; }
break;
case 112:
//#line 310 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).vmRest; }
break;
case 114:
//#line 311 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).caInternal; }
break;
case 116:
//#line 312 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).caExternal; }
break;
case 118:
//#line 313 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).caTau; }
break;
case 120:
//#line 314 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).caSpikeIncrement; }
break;
case 122:
//#line 322 "parse.yaccj"
{ channelList.add( new Channel( val_peek(0).sval )); }
break;
case 124:
//#line 323 "parse.yaccj"
{ channelList.add( new Channel( val_peek(0).sval )); }
break;
case 126:
//#line 324 "parse.yaccj"
{ channelList.add( new Channel( val_peek(0).sval )); }
break;
case 128:
//#line 325 "parse.yaccj"
{ channelList.add( new Channel( val_peek(0).sval )); }
break;
case 130:
//#line 326 "parse.yaccj"
{ channelList.add( new Channel( val_peek(0).sval )); }
break;
case 146:
//#line 355 "parse.yaccj"
{ channelList.get( channelList.size() - 1 ).type = val_peek(0).sval; }
break;
case 147:
//#line 356 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).mPower; }
break;
case 149:
//#line 357 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).unitaryG; }
break;
case 151:
//#line 358 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).strength ; }
break;
case 153:
//#line 359 "parse.yaccj"
{ }
break;
case 155:
//#line 360 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).mInitial; }
break;
case 157:
//#line 361 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).reversalPotential; }
break;
case 159:
//#line 362 "parse.yaccj"
{ channelList.get( channelList.size() - 1 ).seed = val_peek(0).ival; }
break;
case 160:
//#line 365 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).eHalfMinM; }
break;
case 162:
//#line 366 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).tauScaleFactorM; }
break;
case 164:
//#line 367 "parse.yaccj"
{  }
break;
case 165:
//#line 368 "parse.yaccj"
{  }
break;
case 166:
//#line 371 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).caScaleFactor; }
break;
case 168:
//#line 372 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).caExpFactor; }
break;
case 170:
//#line 373 "parse.yaccj"
{  }
break;
case 172:
//#line 374 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).caHalfMin; }
break;
case 174:
//#line 375 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).caTauScaleFactor; }
break;
case 176:
//#line 378 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).hInitial; }
break;
case 178:
//#line 379 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).hPower;    }
break;
case 180:
//#line 380 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).eHalfMinMKa;  }
break;
case 182:
//#line 381 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).eHalfMinH;  }
break;
case 184:
//#line 382 "parse.yaccj"
{  }
break;
case 185:
//#line 383 "parse.yaccj"
{  }
break;
case 186:
//#line 384 "parse.yaccj"
{  }
break;
case 187:
//#line 385 "parse.yaccj"
{  }
break;
case 188:
//#line 387 "parse.yaccj"
{  }
break;
case 189:
//#line 388 "parse.yaccj"
{  }
break;
case 190:
//#line 389 "parse.yaccj"
{  }
break;
case 191:
//#line 390 "parse.yaccj"
{  }
break;
case 192:
//#line 392 "parse.yaccj"
{  }
break;
case 193:
//#line 392 "parse.yaccj"
{  }
break;
case 194:
//#line 393 "parse.yaccj"
{  }
break;
case 195:
//#line 393 "parse.yaccj"
{  }
break;
case 196:
//#line 394 "parse.yaccj"
{  }
break;
case 197:
//#line 394 "parse.yaccj"
{  }
break;
case 198:
//#line 395 "parse.yaccj"
{  }
break;
case 199:
//#line 395 "parse.yaccj"
{  }
break;
case 200:
//#line 398 "parse.yaccj"
{  }
break;
case 202:
//#line 399 "parse.yaccj"
{  }
break;
case 204:
//#line 400 "parse.yaccj"
{  }
break;
case 206:
//#line 401 "parse.yaccj"
{  }
break;
case 208:
//#line 402 "parse.yaccj"
{  }
break;
case 210:
//#line 403 "parse.yaccj"
{  }
break;
case 212:
//#line 404 "parse.yaccj"
{  }
break;
case 213:
//#line 405 "parse.yaccj"
{  }
break;
case 214:
//#line 406 "parse.yaccj"
{  }
break;
case 215:
//#line 407 "parse.yaccj"
{  }
break;
case 216:
//#line 408 "parse.yaccj"
{  }
break;
case 218:
//#line 409 "parse.yaccj"
{  }
break;
case 220:
//#line 410 "parse.yaccj"
{  }
break;
case 222:
//#line 411 "parse.yaccj"
{  }
break;
case 224:
//#line 417 "parse.yaccj"
{ synapseList.add( new Synapse() ); }
break;
case 228:
//#line 424 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).type = val_peek(0).sval; }
break;
case 229:
//#line 425 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).seed = val_peek(0).ival; }
break;
case 230:
//#line 426 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).sfdLabel = val_peek(0).sval; }
break;
case 231:
//#line 427 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).learnLabel = val_peek(0).sval; }
break;
case 232:
//#line 428 "parse.yaccj"
{  }
break;
case 233:
//#line 429 "parse.yaccj"
{  }
break;
case 234:
//#line 430 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).synPSGName  = val_peek(0).sval; }
break;
case 235:
//#line 431 "parse.yaccj"
{ twoPtr = synapseList.get( synapseList.size() - 1).absoluteUse; }
break;
case 237:
//#line 432 "parse.yaccj"
{ twoPtr = synapseList.get( synapseList.size() - 1).delay; }
break;
case 239:
//#line 433 "parse.yaccj"
{ twoPtr = synapseList.get( synapseList.size() - 1).synReversal; }
break;
case 241:
//#line 434 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).maxConduct = val_peek(0).dval; }
break;
case 242:
//#line 435 "parse.yaccj"
{ }
break;
case 244:
//#line 436 "parse.yaccj"
{  }
break;
case 246:
//#line 437 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).hebbStart = val_peek(0).dval; }
break;
case 247:
//#line 438 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).hebbEnd = val_peek(0).dval; }
break;
case 248:
//#line 441 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).sfdStart = val_peek(0).dval; }
break;
case 249:
//#line 442 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).sfdEnd = val_peek(0).dval; }
break;
case 250:
//#line 447 "parse.yaccj"
{ synpsgList.add( new SynPSG() ); }
break;
case 254:
//#line 454 "parse.yaccj"
{ synpsgList.get( synpsgList.size() - 1).type = val_peek(0).sval; }
break;
case 255:
//#line 455 "parse.yaccj"
{ synpsgList.get( synpsgList.size() - 1).filename = val_peek(0).sval; }
break;
case 256:
//#line 460 "parse.yaccj"
{ synFacilDepressList.add( new SynFacilDepress() ); }
break;
case 260:
//#line 467 "parse.yaccj"
{ synFacilDepressList.get( synFacilDepressList.size() - 1 ).type = val_peek(0).sval; }
break;
case 261:
//#line 468 "parse.yaccj"
{ synFacilDepressList.get( synFacilDepressList.size() - 1 ).seed = val_peek(0).ival; }
break;
case 262:
//#line 469 "parse.yaccj"
{ synFacilDepressList.get( synFacilDepressList.size() - 1 ).SFD = val_peek(0).sval; }
break;
case 263:
//#line 470 "parse.yaccj"
{ twoPtr = synFacilDepressList.get( synFacilDepressList.size() - 1 ).facilTau; }
break;
case 265:
//#line 471 "parse.yaccj"
{ twoPtr = synFacilDepressList.get( synFacilDepressList.size() - 1 ).deprTau; }
break;
case 267:
//#line 476 "parse.yaccj"
{ synLearningList.add( new SynLearning() ); }
break;
case 271:
//#line 483 "parse.yaccj"
{ synLearningList.get( synLearningList.size() - 1 ).type = val_peek(0).sval; }
break;
case 272:
//#line 484 "parse.yaccj"
{ synLearningList.get( synLearningList.size() - 1 ).seed = val_peek(0).ival; }
break;
case 273:
//#line 485 "parse.yaccj"
{ synLearningList.get( synLearningList.size() - 1 ).learning = val_peek(0).sval; }
break;
case 274:
//#line 486 "parse.yaccj"
{ synLearningList.get( synLearningList.size() - 1 ).learningShape = val_peek(0).sval; }
break;
case 275:
//#line 487 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).negHebWindow; }
break;
case 277:
//#line 488 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).posHebWindow; }
break;
case 279:
//#line 489 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).negHebPeakDeltaUse; }
break;
case 281:
//#line 490 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).posHebPeakDeltaUse; }
break;
case 283:
//#line 491 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).posHebPeakTime; }
break;
case 285:
//#line 492 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).negHebPeakTime; }
break;
case 287:
//#line 497 "parse.yaccj"
{ synDataList.add( new SynData() ); }
break;
case 291:
//#line 504 "parse.yaccj"
{ synDataList.get( synDataList.size() - 1 ).type = val_peek(0).sval; }
break;
case 292:
//#line 505 "parse.yaccj"
{ synDataList.get( synDataList.size() - 1 ).seed = val_peek(0).ival; }
break;
case 293:
//#line 506 "parse.yaccj"
{ synDataList.get( synDataList.size() - 1 ).maxConduct = val_peek(0).dval; }
break;
case 294:
//#line 507 "parse.yaccj"
{ twoPtr = synDataList.get( synDataList.size() - 1 ).delay; }
break;
case 296:
//#line 508 "parse.yaccj"
{ twoPtr = synDataList.get( synDataList.size() - 1 ).synReversal; }
break;
case 298:
//#line 513 "parse.yaccj"
{ synAugList.add( new SynAugmentation() ); }
break;
case 302:
//#line 519 "parse.yaccj"
{ synAugList.get( synAugList.size() - 1 ).type = val_peek(0).sval; }
break;
case 303:
//#line 520 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).caInternal; }
break;
case 305:
//#line 521 "parse.yaccj"
{  }
break;
case 307:
//#line 522 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).caTau; }
break;
case 309:
//#line 523 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).caSpikeIncrement; }
break;
case 311:
//#line 524 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).maxAugmentation; }
break;
case 313:
//#line 525 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).alpha; }
break;
case 315:
//#line 526 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).augmentationInit; }
break;
case 317:
//#line 527 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).augmentationTau; }
break;
case 319:
//#line 528 "parse.yaccj"
{  }
break;
case 321:
//#line 533 "parse.yaccj"
{ spikeshapeList.add( new SpikeShape() ); }
break;
case 325:
//#line 540 "parse.yaccj"
{ spikeshapeList.get( spikeshapeList.size() - 1 ).type = val_peek(0).sval; }
break;
case 326:
//#line 541 "parse.yaccj"
{ listPtr = spikeshapeList.get( spikeshapeList.size() - 1 ).voltages; }
break;
case 328:
//#line 546 "parse.yaccj"
{ stimulusList.add( new Stimulus() ); }
break;
case 332:
//#line 553 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).type = val_peek(0).sval; }
break;
case 333:
//#line 554 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).mode = val_peek(0).sval; }
break;
case 334:
//#line 555 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).pattern = val_peek(0).sval; }
break;
case 335:
//#line 556 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).vertTrans = val_peek(0).dval; }
break;
case 336:
//#line 557 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).phase = val_peek(0).dval; }
break;
case 337:
//#line 558 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).rate = val_peek(0).dval;}
break;
case 338:
//#line 559 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).tauNoise = val_peek(0).dval; }
break;
case 339:
//#line 560 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).correl = val_peek(0).dval;}
break;
case 340:
//#line 561 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).timing = val_peek(0).sval; }
break;
case 341:
//#line 562 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).filename = val_peek(0).sval; }
break;
case 342:
//#line 563 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).port = val_peek(0).ival; }
break;
case 343:
//#line 564 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).port = -1; }
break;
case 344:
//#line 565 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).sameSeed = (val_peek(0).ival != 0); }
break;
case 345:
//#line 566 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).seed = val_peek(0).ival;}
break;
case 346:
//#line 567 "parse.yaccj"
{  }
break;
case 347:
//#line 568 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).cellsPerFreq = val_peek(0).ival; }
break;
case 348:
//#line 569 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).timeIncrement = val_peek(0).dval; }
break;
case 349:
//#line 570 "parse.yaccj"
{ twoPtr = stimulusList.get( stimulusList.size() - 1 ).dynRange; }
break;
case 351:
//#line 571 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).ampStart = val_peek(0).dval; }
break;
case 352:
//#line 572 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).ampEnd = val_peek(0).dval; }
break;
case 353:
//#line 573 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).width = val_peek(0).dval; }
break;
case 354:
//#line 574 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).freqStart = val_peek(0).dval; }
break;
case 355:
//#line 575 "parse.yaccj"
{ listPtr = stimulusList.get( stimulusList.size() - 1 ).timeStart; }
break;
case 357:
//#line 576 "parse.yaccj"
{ listPtr = stimulusList.get( stimulusList.size() - 1 ).timeEnd; }
break;
case 359:
//#line 581 "parse.yaccj"
{ stimulusInjectList.add( new StimulusInject() ); }
break;
case 363:
//#line 588 "parse.yaccj"
{ stimulusInjectList.get( stimulusInjectList.size() - 1 ).type = val_peek(0).sval; }
break;
case 364:
//#line 589 "parse.yaccj"
{ stimulusInjectList.get( stimulusInjectList.size() - 1 ).stimulusName = val_peek(0).sval; }
break;
case 365:
//#line 590 "parse.yaccj"
{ 
      		stimulusInjectList.get( stimulusInjectList.size() - 1 ).columnName = val_peek(4).sval;
            stimulusInjectList.get( stimulusInjectList.size() - 1 ).layerName = val_peek(3).sval;
            stimulusInjectList.get( stimulusInjectList.size() - 1 ).cellName = val_peek(2).sval;
            stimulusInjectList.get( stimulusInjectList.size() - 1 ).compartmentName = val_peek(1).sval;
            stimulusInjectList.get( stimulusInjectList.size() - 1 ).probability = val_peek(4).dval;
            }
break;
case 366:
//#line 601 "parse.yaccj"
{ reportList.add( new Report() ); }
break;
case 370:
//#line 608 "parse.yaccj"
{ reportList.get( reportList.size() - 1 ).type = val_peek(0).sval; }
break;
case 371:
//#line 610 "parse.yaccj"
{  }
break;
case 372:
//#line 611 "parse.yaccj"
{ reportList.get( reportList.size() - 1 ).filename = val_peek(0).sval; }
break;
case 373:
//#line 612 "parse.yaccj"
{  }
break;
case 374:
//#line 613 "parse.yaccj"
{  }
break;
case 375:
//#line 614 "parse.yaccj"
{  }
break;
case 376:
//#line 615 "parse.yaccj"
{  }
break;
case 377:
//#line 616 "parse.yaccj"
{  }
break;
case 378:
//#line 617 "parse.yaccj"
{  }
break;
case 379:
//#line 618 "parse.yaccj"
{  }
break;
case 380:
//#line 619 "parse.yaccj"
{  }
break;
case 381:
//#line 620 "parse.yaccj"
{  }
break;
case 382:
//#line 621 "parse.yaccj"
{  }
break;
case 383:
//#line 621 "parse.yaccj"
{  }
break;
case 384:
//#line 622 "parse.yaccj"
{  }
break;
case 385:
//#line 622 "parse.yaccj"
{  }
break;
case 386:
//#line 623 "parse.yaccj"
{  }
break;
case 387:
//#line 624 "parse.yaccj"
{  }
break;
case 388:
//#line 625 "parse.yaccj"
{  }
break;
case 389:
//#line 626 "parse.yaccj"
{  }
break;
case 390:
//#line 627 "parse.yaccj"
{  }
break;
case 391:
//#line 628 "parse.yaccj"
{  }
break;
case 392:
//#line 629 "parse.yaccj"
{  }
break;
case 393:
//#line 630 "parse.yaccj"
{  }
break;
case 394:
//#line 631 "parse.yaccj"
{ }
break;
case 395:
//#line 634 "parse.yaccj"
{ eventList.add( new Event() ); }
break;
case 399:
//#line 641 "parse.yaccj"
{ eventList.get( eventList.size() - 1 ).type = val_peek(0).sval; }
break;
case 400:
//#line 642 "parse.yaccj"
{ eventList.get( eventList.size() - 1 ).synapseName = val_peek(0).sval; }
break;
case 401:
//#line 643 "parse.yaccj"
{
            eventList.get( eventList.size() - 1 ).columnName = val_peek(3).sval;
            eventList.get( eventList.size() - 1 ).layerName = val_peek(2).sval;
            eventList.get( eventList.size() - 1 ).cellName = val_peek(1).sval;
            eventList.get( eventList.size() - 1 ).compartmentName = val_peek(0).sval;
        }
break;
case 402:
//#line 649 "parse.yaccj"
{ 
    	eventList.get( eventList.size() - 1 ).filename = val_peek(1).sval;
    	eventList.get( eventList.size() - 1 ).value = val_peek(0).dval; 
    	}
break;
case 405:
//#line 660 "parse.yaccj"
{  }
break;
case 406:
//#line 661 "parse.yaccj"
{  }
break;
case 407:
//#line 666 "parse.yaccj"
{ listPtr.add( val_peek(0).dval ); }
break;
case 408:
//#line 667 "parse.yaccj"
{ listPtr.add( val_peek(1).dval ); }
break;
case 409:
//#line 670 "parse.yaccj"
{ twoPtr.set( val_peek(1).dval , val_peek(0).dval ); twoPtr = null; }
break;
case 410:
//#line 671 "parse.yaccj"
{ twoPtr.set( val_peek(0).dval , 0.0 ); twoPtr = null; }
break;
case 411:
//#line 674 "parse.yaccj"
{ yyval.dval = (double) val_peek(0).ival; }
break;
case 412:
//#line 675 "parse.yaccj"
{ yyval.dval = val_peek(0).dval; }
break;
//#line 2909 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
//## The -Jnoconstruct option was used ##
//###############################################################



}
//################### END OF CLASS ##############################
