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

import java.io.*;
import java.util.ArrayList;
import unr.neurotranslate.ncs.*;

//#line 23 "Parser.java"




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
  valstk[++valptr]=val;
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
   23,   23,   23,   26,    4,   25,   25,   27,   27,   27,
   27,   29,    3,   28,   28,   30,   30,   30,   30,   30,
   30,   32,    6,   31,   31,   33,   33,   33,   35,    5,
   34,   34,   36,   36,   36,   36,   36,   36,   38,    7,
   37,   37,   39,   39,   39,   39,   41,    8,   40,   40,
   42,   42,   42,   42,   44,   42,   45,   42,   46,   42,
   47,   42,   48,   42,   49,   42,   50,   42,   51,   42,
   52,   42,   53,   42,   55,    9,   57,    9,   59,    9,
   61,    9,   63,    9,   54,   54,   54,   56,   56,   56,
   58,   58,   58,   60,   60,   60,   62,   62,   64,   69,
   64,   70,   64,   71,   64,   72,   64,   73,   64,   74,
   64,   64,   75,   65,   76,   65,   65,   65,   77,   66,
   78,   66,   79,   66,   80,   66,   81,   66,   82,   67,
   83,   67,   84,   67,   85,   67,   67,   67,   67,   67,
   67,   67,   67,   67,   87,   67,   88,   67,   89,   67,
   90,   67,   91,   68,   92,   68,   93,   68,   94,   68,
   95,   68,   96,   68,   68,   68,   68,   68,   97,   68,
   98,   68,   99,   68,  100,   68,  102,   10,  101,  101,
  103,  103,  103,  103,  103,  103,  103,  104,  103,  105,
  103,  106,  103,  107,  103,  108,  103,  109,  103,  103,
  103,  103,  103,  111,   11,  110,  110,  112,  112,  114,
   12,  113,  113,  115,  115,  115,  116,  115,  117,  115,
  119,   13,  118,  118,  120,  120,  120,  120,  121,  120,
  122,  120,  123,  120,  124,  120,  125,  120,  126,  120,
  128,   14,  127,  127,  129,  129,  129,  130,  129,  131,
  129,  133,   15,  132,  132,  134,  135,  134,  136,  134,
  137,  134,  138,  134,  139,  134,  140,  134,  141,  134,
  142,  134,  143,  134,  145,   16,  144,  144,  146,  147,
  146,  149,   17,  148,  148,  150,  150,  150,  150,  150,
  150,  150,  150,  150,  150,  150,  150,  150,  150,  150,
  150,  150,  151,  150,  150,  150,  150,  150,  152,  150,
  153,  150,  155,   18,  154,  154,  156,  156,  156,  158,
   19,  157,  157,  159,  159,  159,  159,  159,  159,  159,
  159,  159,  159,  159,  159,  160,  159,  161,  159,  159,
  159,  159,  159,  159,  159,  159,  159,  159,  164,   20,
  163,  163,  165,  165,  165,  165,  162,  162,  166,  166,
   86,   86,   43,   43,   24,   24,
};
final static short yylen[] = {                            2,
    1,    2,    2,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    2,    0,    4,    1,    2,    2,    2,    2,
    1,    1,    2,    2,    2,    2,    2,    2,   12,   13,
   12,    3,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    0,    4,    1,    2,    2,    2,    2,
    3,    0,    4,    1,    2,    2,    2,    2,   10,   11,
   10,    0,    4,    1,    2,    2,    2,    2,    0,    4,
    1,    2,    2,    2,    3,    8,    9,    8,    0,    4,
    1,    2,    2,    5,    6,    6,    0,    4,    1,    2,
    2,    2,    2,    2,    0,    3,    0,    3,    0,    3,
    0,    3,    0,    3,    0,    3,    0,    3,    0,    3,
    0,    3,    0,    3,    0,    5,    0,    5,    0,    5,
    0,    5,    0,    5,    1,    2,    2,    1,    2,    2,
    1,    2,    2,    1,    2,    2,    1,    2,    2,    0,
    3,    0,    3,    0,    3,    0,    3,    0,    3,    0,
    3,    2,    0,    3,    0,    3,    4,    2,    0,    3,
    0,    3,    0,    3,    0,    3,    0,    3,    0,    3,
    0,    3,    0,    3,    0,    3,    2,    2,    2,    2,
    2,    2,    2,    2,    0,    3,    0,    3,    0,    3,
    0,    3,    0,    3,    0,    3,    0,    3,    0,    3,
    0,    3,    0,    3,    3,    3,    2,    2,    0,    3,
    0,    3,    0,    3,    0,    3,    0,    4,    1,    2,
    2,    2,    2,    2,    2,    2,    2,    0,    3,    0,
    3,    0,    3,    0,    3,    0,    3,    0,    3,    2,
    2,    2,    2,    0,    4,    1,    2,    2,    2,    0,
    4,    1,    2,    2,    2,    2,    0,    3,    0,    3,
    0,    4,    1,    2,    2,    2,    2,    2,    0,    3,
    0,    3,    0,    3,    0,    3,    0,    3,    0,    3,
    0,    4,    1,    2,    2,    2,    2,    0,    3,    0,
    3,    0,    4,    1,    2,    2,    0,    3,    0,    3,
    0,    3,    0,    3,    0,    3,    0,    3,    0,    3,
    0,    3,    0,    3,    0,    4,    1,    2,    2,    0,
    3,    0,    4,    1,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    0,    3,    2,    2,    2,    2,    0,    3,
    0,    3,    0,    4,    1,    2,    2,    2,    6,    0,
    4,    1,    2,    2,    5,    2,    2,    2,    1,    2,
    2,    2,    2,    2,    2,    0,    3,    0,    3,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    0,    4,
    1,    2,    2,    2,    5,    3,    1,    2,    1,    1,
    1,    2,    2,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   24,   89,    0,   62,   54,   97,   79,   72,  370,  325,
  332,  363,  227,  291,  260,  271,  254,    0,  302,  399,
    0,    1,    4,    5,    6,    7,    8,    9,   10,   11,
   12,   13,   14,   15,   16,   17,   18,   19,   20,   21,
   22,    0,    0,  125,  127,  129,  131,  133,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   23,    0,    0,    3,    2,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   31,   32,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   26,    0,    0,    0,    0,   91,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   64,    0,
    0,    0,    0,    0,   56,  119,  117,  123,  121,    0,
  113,  111,  107,    0,    0,  105,  109,    0,  115,    0,
   99,    0,    0,    0,    0,    0,    0,   81,    0,    0,
    0,    0,   74,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  388,  386,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  372,    0,  330,    0,  327,    0,
    0,    0,  353,    0,    0,    0,    0,    0,    0,    0,
  361,  359,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  334,    0,    0,    0,    0,  365,  238,
    0,  240,    0,  244,    0,    0,    0,  242,    0,  246,
  248,    0,    0,    0,    0,    0,    0,  229,  298,    0,
    0,  300,    0,    0,  293,  269,  267,    0,    0,    0,
    0,  262,    0,  279,  281,    0,    0,  283,  287,  285,
  289,    0,    0,  273,    0,    0,    0,  256,  307,  313,
  311,    0,  317,  315,  319,  321,  309,  323,    0,  304,
    0,    0,    0,    0,    0,  401,   36,    0,  416,  415,
   34,   33,   38,   35,   37,   28,   44,   46,   45,   29,
   30,   47,    0,   43,   49,   51,   50,   48,    0,   52,
   53,   25,   27,    0,    0,   93,   90,   92,  158,  150,
  160,    0,  154,    0,  152,  156,    0,  135,    0,  138,
    0,  141,    0,  144,    0,  147,   67,    0,   68,   66,
    0,   63,   65,   60,    0,   58,   59,   55,   57,    0,
    0,    0,    0,  104,    0,    0,    0,  102,  103,    0,
    0,  101,    0,   98,  100,    0,    0,   84,   83,    0,
   80,   82,   77,   76,   78,   73,   75,  380,    0,  381,
  376,  385,  384,  382,  395,  396,  390,    0,    0,  374,
  377,  378,  391,  397,  392,  393,  394,  409,  410,    0,
  407,  383,  371,  373,  329,    0,  326,  328,  356,  355,
  351,    0,  345,  350,  358,  337,  338,  348,  349,    0,
    0,  352,  344,  336,  346,  347,  357,  339,  340,  341,
  342,  343,  333,  335,    0,  368,  367,  364,  366,    0,
  235,    0,  234,    0,  232,  233,  237,    0,  231,    0,
    0,  236,  250,  251,  252,  253,  228,  230,    0,  297,
  296,    0,  295,  292,  294,    0,    0,  265,  266,  264,
  261,  263,  277,    0,    0,  276,  275,    0,    0,    0,
    0,  278,  272,  274,  259,  258,  255,  257,    0,    0,
    0,  306,    0,    0,    0,    0,    0,    0,  303,  305,
    0,  404,  403,    0,  400,  402,    0,   42,    0,    0,
    0,    0,    0,    0,  162,    0,  149,    0,    0,  126,
  163,    0,  165,    0,  136,  137,  171,  175,  169,  177,
  128,  173,  139,  140,  130,  185,  183,  179,  181,    0,
    0,  195,  199,    0,    0,    0,    0,    0,    0,  197,
  201,  142,  143,  132,  205,  203,  207,  209,    0,    0,
  211,  213,    0,    0,  223,  219,  225,  221,  145,  146,
  134,  148,    0,    0,   61,    0,  120,  118,  124,  122,
  114,  112,  108,  106,  110,  116,   85,    0,    0,    0,
  411,    0,    0,  408,    0,  354,    0,    0,    0,  239,
  241,  245,  243,  247,  249,  299,  301,  270,  268,  280,
  282,  284,  288,  286,  290,  308,  314,  312,  318,  316,
  320,  322,  310,  324,    0,  406,    0,    0,    0,    0,
  159,  151,  161,  155,  153,  157,    0,    0,    0,  168,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  189,
  187,    0,    0,  191,  192,  188,  193,  194,  190,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  217,
  218,    0,    0,    0,    0,    0,    0,  413,    0,    0,
    0,  412,    0,    0,    0,    0,    0,    0,  164,    0,
  166,  172,  176,  170,  178,  174,  186,  184,  180,  182,
    0,    0,    0,    0,  206,  204,  208,  210,  216,  215,
  212,  214,  224,  220,  226,  222,    0,    0,    0,    0,
  375,    0,  405,    0,    0,   95,   96,  167,    0,    0,
    0,    0,  369,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   88,    0,    0,    0,    0,   87,
    0,    0,    0,   71,    0,    0,   70,    0,   41,   40,
};
final static short yydgoto[] = {                         21,
   22,   23,   24,   25,   26,   27,   28,   29,   30,   31,
   32,   33,   34,   35,   36,   37,   38,   39,   40,   41,
   91,   42,   92,  556,  114,   50,  115,  108,   49,  109,
  142,   53,  143,  137,   52,  138,   96,   43,   97,  130,
   51,  131,  557,  340,  337,  341,  336,  335,  343,  331,
  330,  333,  332,  307,   98,  309,   99,  311,  100,  313,
  101,  315,  102,  308,  506,  514,  533,  550,  493,  498,
  496,  499,  492,  494,  617,  619,  623,  621,  625,  622,
  624,  628,  629,  627,  626,  572,  632,  640,  633,  641,
  643,  642,  644,  645,  648,  649,  653,  655,  652,  654,
  217,   58,  218,  420,  422,  428,  424,  430,  431,  247,
   62,  248,  231,   60,  232,  447,  446,  243,   61,  244,
  454,  455,  458,  460,  459,  461,  224,   59,  225,  439,
  442,  259,   64,  260,  469,  477,  471,  470,  474,  473,
  475,  476,  478,  168,   55,  169,  386,  193,   56,  194,
  392,  401,  400,  198,   57,  199,  164,   54,  165,  369,
  368,  380,  265,   65,  266,  381,
};
final static short yysindex[] = {                      1099,
    0,    0, -293,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -234,    0,    0,
  197,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, 1273, -269,    0,    0,    0,    0,    0, -106,  -47,
 1685, -207, -322, 1026, -361,  697, -309,  -10,  -27,  136,
   74, -334,    0, -173, -248,    0,    0, -228, -224, -229,
 -229, -214, -227, -205, -202, -252, -201, -200,    0,    0,
 -197, -195, -193, -235, -206, -191, -186, -187, -360, -167,
 1128,    0, -165, -163, -158, -247,    0,  158,  158,  158,
  158,  158, -149, -137, -136, -130, -113, -175,    0, -229,
 -229,  -98, -229,  -23,    0,    0,    0,    0,    0,  -96,
    0,    0,    0, -184,  -77,    0,    0,  -76,    0, 1649,
    0,  -75,  -74,  -71,  -67,  -66, -266,    0, -229,  -63,
 -229, -221,    0, -343,  -61,  -54,  -45, -229, -229,  -44,
 -255,  -31,    0,    0,  -25, -250,  -22, -104,  -16,   -3,
   -2, -419,    2,  748,    0,    3,    0, -238,    0, -229,
 -229,  -68,    0,    4,  -21, -229,   10,   12,   -9,   20,
    0,    0, -229,   23,   31, -249, -229, -229, -229, -229,
 -229, -229,  547,    0,   35,   36,   37,  -80,    0,    0,
   40,    0,   41,    0,   46,   47,   50,    0,   51,    0,
    0,   52, -229, -229, -229, -229, -236,    0,    0, -229,
   48,    0,   53,   76,    0,    0,    0,   56,   62,   63,
   32,    0,   65,    0,    0,   70,   69,    0,    0,    0,
    0,   72,  -46,    0,   73,   79, -133,    0,    0,    0,
    0,   82,    0,    0,    0,    0,    0,    0, -140,    0,
   83,   92,   95,   96, -270,    0,    0,   97,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -229,    0,    0,    0,    0,    0,   99,    0,
    0,    0,    0,  100,  101,    0,    0,    0,    0,    0,
    0,  106,    0,  105,    0,    0,  233,    0,  787,    0,
 1400,    0, 1317,    0,  -24,    0,    0,  107,    0,    0,
  109,    0,    0,    0, -229,    0,    0,    0,    0, -229,
 -229, -229, -229,    0, -229, -229, -229,    0,    0, -229,
 -229,    0, -229,    0,    0,  125,  124,    0,    0,  126,
    0,    0,    0,    0,    0,    0,    0,    0,  128,    0,
    0,    0,    0,    0,    0,    0,    0, -229, -229,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -419,
    0,    0,    0,    0,    0, -229,    0,    0,    0,    0,
    0, -229,    0,    0,    0,    0,    0,    0,    0, -229,
 -229,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  130,    0,    0,    0,    0, -229,
    0, -229,    0, -229,    0,    0,    0, -229,    0, -229,
 -229,    0,    0,    0,    0,    0,    0,    0, -229,    0,
    0, -229,    0,    0,    0, -229, -229,    0,    0,    0,
    0,    0,    0, -229, -229,    0,    0, -229, -229, -229,
 -229,    0,    0,    0,    0,    0,    0,    0, -229, -229,
 -229,    0, -229, -229, -229, -229, -229, -229,    0,    0,
  139,    0,    0, -229,    0,    0,  147,    0,  149, -229,
 -229, -229, -229, -229,    0, -229,    0, -229, -229,    0,
    0, -229,    0, -229,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -229,
 -229,    0,    0, -229, -229, -229, -229, -229, -229,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -229, -229,
    0,    0, -229, -229,    0,    0,    0,    0,    0,    0,
    0,    0,  151,  152,    0, -229,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  155,  156,  157,
    0, -229, -229,    0, -229,    0, -229, -229,  159,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  165,    0,  166,  169, -229, -229,
    0,    0,    0,    0,    0,    0, -229, -229, -229,    0,
 -229, -229, -229, -229, -229, -229, -229, -229, -229,    0,
    0, -229, -229,    0,    0,    0,    0,    0,    0, -229,
 -229, -229, -229, -229, -229, -229, -229, -229, -229,    0,
    0, -229, -229, -229, -229,  170,  171,    0,  173,  175,
  176,    0,  179,  181,  182,  183, -229, -229,    0, -229,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -229, -229, -229, -229,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  184,  186,  188,  192,
    0, -229,    0,  200,  201,    0,    0,    0,  203,  206,
 -229, -229,    0,  212,  218,  220,  221, -229, -229,  222,
  223, -229, -229, -229,    0,  227,  228, -229, -229,    0,
 -229, -229, -229,    0, -229, -229,    0, -229,    0,    0,
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
    0,    0,    0,  815,    0,    0,    0,    0,    0,    0,
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
    0,    0,    0,    0,    0,    0,    0,    0,    0,  859,
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
    0,    0,    0,    0,    0,  408,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  900,  985,    0, -237,    0,  577,  667,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -240,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 1448, 1496, 1544, 1592,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -239,    0,    0,    0,    0,    0,    0,
    0,    0, -142,    0,    0,    0,    0, 1183,    0,    0,
};
final static short yygindex[] = {                         0,
  364,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  305,  -70,    0,    0,  304,    0,    0,  381,
    0,    0,  350,    0,    0,  356,    0,    0,  398,    0,
    0,  365, -251,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -79,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -367,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  279,    0,    0,    0,    0,    0,    0,    0,
    0,  250,    0,    0,  267,    0,    0,    0,    0,  256,
    0,    0,    0,    0,    0,    0,    0,    0,  277,    0,
    0,    0,    0,  245,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  342,    0,    0,    0,  318,
    0,    0,    0,    0,    0,  314,    0,    0,  349,    0,
    0,    0,    0,    0,  249,  140,
};
final static int YYTABLESIZE=2061;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                        271,
  272,  573,  365,  245,  261,  277,  139,  371,  405,  195,
  132,  166,   93,  378,   94,  167,  379,  133,  575,  310,
  312,  314,  316,  285,  200,   63,  261,  269,  270,  351,
  274,  267,  577,  578,   93,  268,   94,   86,  246,  324,
  325,   94,  327,   94,   86,  273,  297,  196,  201,  202,
  140,  141,  286,   94,  275,  134,   86,  276,  280,  281,
  387,  331,  282,  197,  283,  437,  284,  287,  353,  132,
  355,  288,  289,  338,  290,  356,  133,  362,  363,  558,
  559,  560,   86,  561,  562,  563,  358,  262,  564,  565,
  203,  566,  291,  204,  294,  249,  295,  250,  251,  389,
  390,  296,  263,   95,  103,  395,  135,  139,  104,  262,
  317,  205,  402,  206,  134,  322,  407,  408,  409,  410,
  411,  412,  318,  319,  263,   95,  207,  208,  249,  320,
  250,  251,   94,   86,  166,  331,  209,   69,  167,  331,
  576,   69,  433,  434,  435,  436,  321,  105,   69,  440,
  278,  140,  141,  374,  136,   44,   45,   46,   47,   48,
  210,  326,  211,  334,  279,  135,  372,  406,  580,  264,
  581,  467,  582,  103,  485,  366,  583,  104,  584,  585,
   69,   86,  339,  342,  346,  347,  212,  586,  348,  391,
  587,  264,  349,  350,  588,  589,  354,  106,  359,  252,
  213,  214,  590,  591,  245,  360,  592,  593,  594,  595,
  215,  216,  488,  136,  361,  364,  105,  596,  597,  598,
  418,  599,  600,  601,  602,  603,  604,  505,  367,  513,
   69,  532,  252,  549,  370,  552,  394,  373,  195,  246,
  611,  612,  613,  375,  614,  107,  615,  616,  253,  398,
  200,  254,  255,  256,  555,  257,  376,  377,  219,  463,
  258,  382,  385,  393,  681,  682,  106,  110,  328,  396,
  551,  397,  683,  684,  201,  202,  196,  399,   69,  233,
  111,  253,  403,  479,  254,  255,  256,  234,  257,  235,
  404,  110,  197,  258,  415,  416,  417,  571,  571,  421,
  423,  236,  220,  425,  111,  441,  426,  299,  300,  427,
  429,  432,  443,  448,  107,  571,  203,  301,  226,  204,
  221,  449,  450,  302,  453,  112,  237,  456,  457,  571,
  571,  462,  465,  113,  303,  451,  222,  205,  466,  206,
  227,  472,  481,  238,  239,  223,  240,  241,  304,  112,
  305,  482,  207,  208,  483,  484,  487,  113,  489,  490,
  491,  219,  209,  495,  497,  669,  553,  671,  554,  672,
  673,  674,  675,  676,  677,  678,  679,  680,  444,  228,
  229,  306,  567,  568,   67,  569,  210,  570,  211,  579,
  685,  686,  687,  688,  242,  293,  691,  692,  605,  233,
  693,  694,  695,  696,  230,  220,  607,  234,  608,  235,
  656,  657,  212,  606,  659,  660,  661,  329,  663,  609,
  610,  236,  226,  221,  664,  665,  213,  214,  666,  697,
  698,  618,  699,  620,  700,  701,  215,  216,  702,  222,
  703,  704,  705,  709,  227,  710,  237,  711,  223,  630,
  631,  712,   66,  634,  635,  636,  637,  638,  639,  714,
  715,    1,  716,  238,  239,  717,  240,  241,  646,  647,
    2,  720,  650,  651,    3,    4,    5,  721,    6,  722,
  723,  726,  727,  228,  229,  658,  731,  732,  323,  299,
  300,  357,  352,  298,  345,  438,  468,  452,  464,  301,
  445,  662,  662,  480,  662,  302,  662,  662,  230,  388,
  414,  419,  384,  486,  242,    0,  303,    7,    8,  574,
    0,    0,    0,    0,    0,    0,    0,  500,    0,    0,
  304,    0,  305,    0,    0,    0,    9,    0,  667,  668,
  501,    0,    0,    0,    0,    0,    0,  670,    0,   10,
   11,    0,   12,    0,   13,    0,   14,   15,   16,   17,
    0,  571,  571,  306,  299,  300,    0,    0,    0,  571,
  571,    0,    0,    0,  301,  689,  690,    0,    0,    0,
  302,    0,    0,    0,  502,    0,    0,    0,    0,    0,
    0,  303,   18,    0,    0,    0,  706,  707,  503,  708,
    0,    0,    0,    0,    0,  304,    0,  305,    0,    0,
  662,  662,  662,  662,    0,    0,    0,    0,  504,   19,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  713,    0,    0,    0,   20,    0,    0,  306,    0,
  718,  719,    0,    0,    0,    0,    0,  724,  725,    0,
    0,  728,  729,  730,    0,    0,    0,  733,  734,    0,
  735,  736,  737,    0,  738,  739,    0,  740,  414,  414,
  414,    0,    0,  414,  414,  414,  414,  414,  414,  414,
  414,    0,    0,  414,    0,  414,    0,    0,    0,    0,
    0,    0,  414,  414,  414,    0,  414,    0,    0,    0,
  414,    0,  414,    0,    0,    0,    0,  414,    0,  414,
  414,  414,    0,  414,  414,  414,  414,  414,    0,  414,
  414,    0,    0,  414,  414,    0,    0,    0,    0,    0,
    0,  414,  414,  414,  414,    0,    0,  414,  414,  414,
  414,  414,  414,  414,    0,    0,    0,    0,    0,  414,
    0,    0,  414,  414,    0,  414,  414,  414,  414,  414,
  414,    0,    0,    0,    0,    0,  414,    0,    0,    0,
  414,  414,  414,  414,  414,  414,  414,  414,  414,  414,
  414,    0,  414,  414,    0,  414,  414,  414,  414,    0,
    0,  414,  414,  414,  414,  414,  414,  414,  414,  414,
  414,  414,  414,    0,  414,  414,  414,    0,  170,  171,
    0,  414,  414,  414,    0,  414,  414,  414,  414,    0,
    0,    0,  172,    0,    0,    0,    0,    0,    0,  414,
  414,  414,  414,  414,  414,  173,  414,    0,  362,  362,
    0,  414,    0,    0,  414,  414,  413,    0,  414,  414,
  414,  414,  362,    0,  414,  414,  174,    0,  175,  176,
    0,    0,    0,    0,    0,  362,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  362,  177,    0,    0,
    0,  178,    0,    0,    0,    0,  362,    0,  362,  362,
    0,    0,  179,    0,  180,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  362,    0,    0,
    0,  362,    0,    0,    0,  181,  182,  183,  184,  185,
    0,    0,  362,    0,  362,    0,  186,  187,  360,  360,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  360,    0,  188,  362,  362,  362,  362,  362,
    0,  189,    0,    0,    0,  360,  362,  362,  170,  171,
    0,    0,    0,    0,    0,    0,  360,    0,    0,    0,
    0,    0,  172,    0,  362,    0,  360,    0,  360,  360,
    0,  362,    0,    0,    0,  173,    0,    0,  190,  191,
  192,    0,    0,    0,    0,    0,    0,  360,    0,    0,
    0,  360,    0,    0,    0,    0,  174,    0,  175,  176,
    0,  144,  360,    0,  360,    0,    0,    0,  362,  362,
  362,    0,  145,    0,    0,  146,    0,  177,    0,    0,
    0,  178,    0,    0,    0,  360,  360,  360,  360,  360,
    0,    0,  179,    0,  180,  383,  360,  360,    0,    0,
    0,    0,  507,    0,  508,    0,  509,  147,  148,  510,
    0,    0,    0,    0,  360,  181,  182,  183,  184,  185,
    0,  360,    0,    0,    0,    0,  186,  187,  379,    0,
    0,  511,    0,    0,  149,    0,    0,    0,  150,  379,
    0,    0,  379,    0,  188,  151,    0,    0,    0,    0,
    0,  189,    0,    0,    0,  152,    0,    0,  360,  360,
  360,    0,  379,    0,    0,    0,  153,  154,  299,  300,
  155,    0,  398,    0,  379,  379,    0,  156,  301,    0,
    0,    0,    0,  398,  302,    0,  398,    0,  190,  191,
  192,    0,    0,    0,    0,  303,    0,    0,    0,    0,
    0,  379,    0,    0,  157,  379,  398,    0,    0,  304,
    0,  305,  379,  389,    0,    0,  158,  159,  398,  398,
  160,    0,  379,    0,  389,  161,    0,  389,    0,  162,
    0,    0,    0,  379,  379,    0,    0,  379,    0,    0,
  512,    0,  306,  163,  379,  398,    0,  389,    0,  398,
    0,    0,    0,    0,    0,    0,  398,    0,    0,  389,
  389,    0,    0,    0,    0,    0,  398,    0,    0,    0,
    0,  379,    0,    0,    0,    0,    0,  398,  398,    0,
    0,  398,    0,  379,  379,    0,  389,  379,  398,    0,
  389,    0,  379,    0,    0,    0,  379,  389,  387,    0,
    0,    0,    0,    0,    0,    0,    0,  389,    0,  387,
  379,    0,  387,    0,    0,  398,    0,    0,  389,  389,
    0,    0,  389,    0,    0,    0,    0,  398,  398,  389,
    0,  398,  387,    0,    0,    0,  398,    0,    0,  144,
  398,    0,    0,    0,  387,  387,    0,    0,    0,    0,
  145,    0,    0,  146,  398,    0,  389,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  389,  389,
    0,  387,  389,    0,    0,  387,    0,  389,    0,    0,
    0,  389,  387,    0,    0,  147,  148,    0,    0,    0,
    0,    0,  387,    0,    0,  389,    0,    0,    0,    0,
    0,    0,    0,  387,  387,    0,    0,  387,    0,    0,
    0,    0,  149,    1,  387,    0,  150,    0,    0,    0,
    0,    0,    2,  151,    0,    0,    3,    4,    5,    0,
    6,    0,    0,  152,    0,    0,    0,    0,    0,    0,
    0,  387,    0,    0,  153,  154,    0,    0,  155,    0,
    0,    0,    0,  387,  387,  156,    0,  387,   68,    0,
    0,   69,  387,    0,    0,   70,  387,  292,    0,    7,
    8,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  387,    0,  157,    0,    0,    0,    0,    0,    9,    0,
    0,   71,    0,    0,  158,  159,    0,    0,  160,    0,
    0,   10,   11,  161,   12,    0,   13,  162,   14,   15,
   16,   17,    0,   39,    0,    0,   39,   72,    0,    0,
   39,  163,   39,    0,    0,   73,    0,    0,    0,    0,
    0,    0,    0,   74,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   18,    0,   39,    0,    0,    0,
   75,    0,    0,    0,    0,    0,    0,   76,    0,   77,
   78,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   19,   39,    0,    0,    0,    0,   79,   80,   81,
   39,    0,    0,    0,    0,    0,    0,   20,   39,   82,
   83,   84,   85,   86,    0,   87,    0,    0,   88,    0,
    0,    0,    0,   68,    0,   39,   69,    0,    0,   89,
   70,    0,   39,    0,   39,   39,   90,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   39,   39,   39,    0,   71,    0,    0,    0,
    0,    0,    0,    0,   39,   39,   39,   39,   39,    0,
   39,    0,    0,   39,    0,    0,    0,    0,    0,    0,
    0,  534,   72,    0,   39,    0,    0,    0,    0,    0,
   73,   39,    0,  535,  536,    0,    0,    0,   74,    0,
    0,    0,  537,  538,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   75,    0,    0,  299,  300,
    0,    0,   76,    0,   77,   78,    0,    0,  301,    0,
    0,    0,    0,    0,  302,    0,    0,  539,  540,    0,
    0,    0,   79,   80,   81,  303,    0,    0,    0,    0,
    0,    0,  541,  542,   82,   83,   84,   85,   86,  304,
   87,  305,    0,   88,  515,    0,    0,    0,    0,    0,
    0,    0,  543,    0,   89,  544,  516,  517,    0,    0,
    0,   90,    0,    0,    0,  518,  519,    0,    0,    0,
    0,    0,  306,    0,  545,  546,  547,  548,    0,    0,
    0,  299,  300,    0,    0,    0,    0,    0,    0,    0,
    0,  301,  196,    0,    0,    0,    0,  302,    0,    0,
  520,  521,    0,    0,  196,  196,    0,    0,  303,    0,
    0,    0,    0,  196,  196,    0,    0,    0,    0,    0,
    0,    0,  304,    0,  305,    0,    0,  522,  523,  196,
  196,    0,    0,  524,  525,  526,  527,  528,  529,  196,
  200,  530,    0,    0,  531,  196,    0,    0,  196,  196,
    0,    0,  200,  200,    0,  306,  196,    0,    0,    0,
    0,  200,  200,    0,    0,    0,    0,    0,    0,    0,
  196,    0,  196,    0,    0,  196,  196,  200,  200,    0,
    0,  196,  196,  196,  196,  196,  196,  200,  198,  196,
    0,    0,  196,  200,    0,    0,  200,  200,    0,    0,
  198,  198,    0,  196,  200,    0,    0,    0,    0,  198,
  198,    0,    0,    0,    0,    0,    0,    0,  200,    0,
  200,    0,    0,  200,  200,  198,  198,    0,    0,  200,
  200,  200,  200,  200,  200,  198,  202,  200,    0,    0,
  200,  198,    0,    0,  198,  198,    0,    0,  202,  202,
    0,  200,  198,    0,    0,    0,    0,  202,  202,    0,
    0,    0,    0,    0,    0,  116,  198,  117,  198,  118,
  119,  198,  198,  202,  202,    0,  120,  198,  198,  198,
  198,  198,  198,  202,    0,  198,    0,    0,  198,  202,
    0,  344,  202,  202,    0,    0,    0,    0,    0,  198,
  202,  116,    0,  117,    0,  118,  119,    0,    0,    0,
    0,    0,  120,    0,  202,    0,  202,    0,    0,  202,
  202,    0,  121,  122,    0,  202,  202,  202,  202,  202,
  202,    0,    0,  202,    0,    0,  202,    0,    0,    0,
    0,    0,    0,  123,    0,    0,  124,  202,    0,    0,
    0,  125,    0,    0,    0,    0,    0,    0,  121,  122,
    0,    0,    0,  126,    0,    0,  127,    0,    0,    0,
    0,  128,    0,    0,  129,    0,    0,    0,    0,  123,
    0,    0,  124,    0,    0,    0,    0,  125,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  126,
    0,    0,  127,    0,    0,    0,    0,  128,    0,    0,
  129,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         70,
   71,  369,  258,  338,  275,  258,  329,  258,  258,  319,
  277,  373,  282,  433,  284,  377,  436,  284,  386,   99,
  100,  101,  102,  259,  261,  260,  275,  257,  258,  296,
  258,  260,  400,  401,  282,  260,  284,  277,  373,  110,
  111,  282,  113,  284,  284,  260,  294,  357,  285,  286,
  373,  374,  259,  294,  260,  322,  296,  260,  260,  260,
  299,  299,  260,  373,  260,  302,  260,  259,  139,  277,
  141,  258,  260,  258,  435,  297,  284,  148,  149,  331,
  332,  333,  322,  335,  336,  337,  430,  358,  340,  341,
  327,  343,  260,  330,  260,  269,  260,  271,  272,  170,
  171,  260,  373,  373,  280,  176,  373,  329,  284,  358,
  260,  348,  183,  350,  322,  291,  187,  188,  189,  190,
  191,  192,  260,  260,  373,  373,  363,  364,  269,  260,
  271,  272,  373,  373,  373,  373,  373,  280,  377,  377,
  392,  284,  213,  214,  215,  216,  260,  323,  291,  220,
  403,  373,  374,  258,  421,  449,  450,  451,  452,  453,
  397,  260,  399,  260,  417,  373,  417,  417,  420,  440,
  422,  305,  424,  280,  445,  431,  428,  284,  430,  431,
  323,  421,  260,  260,  260,  260,  423,  439,  260,  258,
  442,  440,  260,  260,  446,  447,  260,  373,  260,  373,
  437,  438,  454,  455,  338,  260,  458,  459,  460,  461,
  447,  448,  283,  421,  260,  260,  323,  469,  470,  471,
  301,  473,  474,  475,  476,  477,  478,  307,  260,  309,
  373,  311,  373,  313,  260,  315,  258,  260,  319,  373,
  492,  493,  494,  260,  496,  421,  498,  499,  422,  259,
  261,  425,  426,  427,  325,  429,  260,  260,  286,  306,
  434,  260,  260,  260,  632,  633,  373,  315,  292,  260,
  295,  260,  640,  641,  285,  286,  357,  258,  421,  326,
  328,  422,  260,  424,  425,  426,  427,  334,  429,  336,
  260,  315,  373,  434,  260,  260,  260,  368,  369,  260,
  260,  348,  330,  258,  328,  258,  260,  332,  333,  260,
  260,  260,  260,  258,  421,  386,  327,  342,  287,  330,
  348,  260,  260,  348,  260,  373,  373,  258,  260,  400,
  401,  260,  260,  381,  359,  304,  364,  348,  260,  350,
  309,  260,  260,  390,  391,  373,  393,  394,  373,  373,
  375,  260,  363,  364,  260,  260,  260,  381,  260,  260,
  260,  286,  373,  258,  260,  617,  260,  619,  260,  621,
  622,  623,  624,  625,  626,  627,  628,  629,  303,  348,
  349,  406,  258,  260,   21,  260,  397,  260,  399,  260,
  642,  643,  644,  645,  441,   91,  648,  649,  260,  326,
  652,  653,  654,  655,  373,  330,  260,  334,  260,  336,
  260,  260,  423,  484,  260,  260,  260,  114,  260,  490,
  491,  348,  287,  348,  260,  260,  437,  438,  260,  260,
  260,  502,  260,  504,  260,  260,  447,  448,  260,  364,
  260,  260,  260,  260,  309,  260,  373,  260,  373,  520,
  521,  260,  256,  524,  525,  526,  527,  528,  529,  260,
  260,  265,  260,  390,  391,  260,  393,  394,  539,  540,
  274,  260,  543,  544,  278,  279,  280,  260,  282,  260,
  260,  260,  260,  348,  349,  556,  260,  260,  108,  332,
  333,  142,  137,   96,  130,  217,  247,  231,  243,  342,
  224,  572,  573,  259,  575,  348,  577,  578,  373,  168,
  193,  198,  164,  265,  441,   -1,  359,  321,  322,  380,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  295,   -1,   -1,
  373,   -1,  375,   -1,   -1,   -1,  340,   -1,  609,  610,
  308,   -1,   -1,   -1,   -1,   -1,   -1,  618,   -1,  353,
  354,   -1,  356,   -1,  358,   -1,  360,  361,  362,  363,
   -1,  632,  633,  406,  332,  333,   -1,   -1,   -1,  640,
  641,   -1,   -1,   -1,  342,  646,  647,   -1,   -1,   -1,
  348,   -1,   -1,   -1,  352,   -1,   -1,   -1,   -1,   -1,
   -1,  359,  396,   -1,   -1,   -1,  667,  668,  366,  670,
   -1,   -1,   -1,   -1,   -1,  373,   -1,  375,   -1,   -1,
  681,  682,  683,  684,   -1,   -1,   -1,   -1,  386,  423,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  702,   -1,   -1,   -1,  439,   -1,   -1,  406,   -1,
  711,  712,   -1,   -1,   -1,   -1,   -1,  718,  719,   -1,
   -1,  722,  723,  724,   -1,   -1,   -1,  728,  729,   -1,
  731,  732,  733,   -1,  735,  736,   -1,  738,  261,  262,
  263,   -1,   -1,  266,  267,  268,  269,  270,  271,  272,
  273,   -1,   -1,  276,   -1,  278,   -1,   -1,   -1,   -1,
   -1,   -1,  285,  286,  287,   -1,  289,   -1,   -1,   -1,
  293,   -1,  295,   -1,   -1,   -1,   -1,  300,   -1,  302,
  303,  304,   -1,  306,  307,  308,  309,  310,   -1,  312,
  313,   -1,   -1,  316,  317,   -1,   -1,   -1,   -1,   -1,
   -1,  324,  325,  326,  327,   -1,   -1,  330,  331,  332,
  333,  334,  335,  336,   -1,   -1,   -1,   -1,   -1,  342,
   -1,   -1,  345,  346,   -1,  348,  349,  350,  351,  352,
  353,   -1,   -1,   -1,   -1,   -1,  359,   -1,   -1,   -1,
  363,  364,  365,  366,  367,  368,  369,  370,  371,  372,
  373,   -1,  375,  376,   -1,  378,  379,  380,  381,   -1,
   -1,  384,  385,  386,  387,  388,  389,  390,  391,  392,
  393,  394,  395,   -1,  397,  398,  399,   -1,  262,  263,
   -1,  404,  405,  406,   -1,  408,  409,  410,  411,   -1,
   -1,   -1,  276,   -1,   -1,   -1,   -1,   -1,   -1,  422,
  423,  424,  425,  426,  427,  289,  429,   -1,  262,  263,
   -1,  434,   -1,   -1,  437,  438,  300,   -1,  441,  442,
  443,  444,  276,   -1,  447,  448,  310,   -1,  312,  313,
   -1,   -1,   -1,   -1,   -1,  289,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  300,  331,   -1,   -1,
   -1,  335,   -1,   -1,   -1,   -1,  310,   -1,  312,  313,
   -1,   -1,  346,   -1,  348,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  331,   -1,   -1,
   -1,  335,   -1,   -1,   -1,  369,  370,  371,  372,  373,
   -1,   -1,  346,   -1,  348,   -1,  380,  381,  262,  263,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  276,   -1,  398,  369,  370,  371,  372,  373,
   -1,  405,   -1,   -1,   -1,  289,  380,  381,  262,  263,
   -1,   -1,   -1,   -1,   -1,   -1,  300,   -1,   -1,   -1,
   -1,   -1,  276,   -1,  398,   -1,  310,   -1,  312,  313,
   -1,  405,   -1,   -1,   -1,  289,   -1,   -1,  442,  443,
  444,   -1,   -1,   -1,   -1,   -1,   -1,  331,   -1,   -1,
   -1,  335,   -1,   -1,   -1,   -1,  310,   -1,  312,  313,
   -1,  264,  346,   -1,  348,   -1,   -1,   -1,  442,  443,
  444,   -1,  275,   -1,   -1,  278,   -1,  331,   -1,   -1,
   -1,  335,   -1,   -1,   -1,  369,  370,  371,  372,  373,
   -1,   -1,  346,   -1,  348,  298,  380,  381,   -1,   -1,
   -1,   -1,  266,   -1,  268,   -1,  270,  310,  311,  273,
   -1,   -1,   -1,   -1,  398,  369,  370,  371,  372,  373,
   -1,  405,   -1,   -1,   -1,   -1,  380,  381,  264,   -1,
   -1,  295,   -1,   -1,  337,   -1,   -1,   -1,  341,  275,
   -1,   -1,  278,   -1,  398,  348,   -1,   -1,   -1,   -1,
   -1,  405,   -1,   -1,   -1,  358,   -1,   -1,  442,  443,
  444,   -1,  298,   -1,   -1,   -1,  369,  370,  332,  333,
  373,   -1,  264,   -1,  310,  311,   -1,  380,  342,   -1,
   -1,   -1,   -1,  275,  348,   -1,  278,   -1,  442,  443,
  444,   -1,   -1,   -1,   -1,  359,   -1,   -1,   -1,   -1,
   -1,  337,   -1,   -1,  407,  341,  298,   -1,   -1,  373,
   -1,  375,  348,  264,   -1,   -1,  419,  420,  310,  311,
  423,   -1,  358,   -1,  275,  428,   -1,  278,   -1,  432,
   -1,   -1,   -1,  369,  370,   -1,   -1,  373,   -1,   -1,
  404,   -1,  406,  446,  380,  337,   -1,  298,   -1,  341,
   -1,   -1,   -1,   -1,   -1,   -1,  348,   -1,   -1,  310,
  311,   -1,   -1,   -1,   -1,   -1,  358,   -1,   -1,   -1,
   -1,  407,   -1,   -1,   -1,   -1,   -1,  369,  370,   -1,
   -1,  373,   -1,  419,  420,   -1,  337,  423,  380,   -1,
  341,   -1,  428,   -1,   -1,   -1,  432,  348,  264,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  358,   -1,  275,
  446,   -1,  278,   -1,   -1,  407,   -1,   -1,  369,  370,
   -1,   -1,  373,   -1,   -1,   -1,   -1,  419,  420,  380,
   -1,  423,  298,   -1,   -1,   -1,  428,   -1,   -1,  264,
  432,   -1,   -1,   -1,  310,  311,   -1,   -1,   -1,   -1,
  275,   -1,   -1,  278,  446,   -1,  407,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  419,  420,
   -1,  337,  423,   -1,   -1,  341,   -1,  428,   -1,   -1,
   -1,  432,  348,   -1,   -1,  310,  311,   -1,   -1,   -1,
   -1,   -1,  358,   -1,   -1,  446,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  369,  370,   -1,   -1,  373,   -1,   -1,
   -1,   -1,  337,  265,  380,   -1,  341,   -1,   -1,   -1,
   -1,   -1,  274,  348,   -1,   -1,  278,  279,  280,   -1,
  282,   -1,   -1,  358,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  407,   -1,   -1,  369,  370,   -1,   -1,  373,   -1,
   -1,   -1,   -1,  419,  420,  380,   -1,  423,  281,   -1,
   -1,  284,  428,   -1,   -1,  288,  432,  290,   -1,  321,
  322,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  446,   -1,  407,   -1,   -1,   -1,   -1,   -1,  340,   -1,
   -1,  314,   -1,   -1,  419,  420,   -1,   -1,  423,   -1,
   -1,  353,  354,  428,  356,   -1,  358,  432,  360,  361,
  362,  363,   -1,  281,   -1,   -1,  284,  340,   -1,   -1,
  288,  446,  290,   -1,   -1,  348,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  356,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  396,   -1,  314,   -1,   -1,   -1,
  373,   -1,   -1,   -1,   -1,   -1,   -1,  380,   -1,  382,
  383,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  423,  340,   -1,   -1,   -1,   -1,  400,  401,  402,
  348,   -1,   -1,   -1,   -1,   -1,   -1,  439,  356,  412,
  413,  414,  415,  416,   -1,  418,   -1,   -1,  421,   -1,
   -1,   -1,   -1,  281,   -1,  373,  284,   -1,   -1,  432,
  288,   -1,  380,   -1,  382,  383,  439,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  400,  401,  402,   -1,  314,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  412,  413,  414,  415,  416,   -1,
  418,   -1,   -1,  421,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  295,  340,   -1,  432,   -1,   -1,   -1,   -1,   -1,
  348,  439,   -1,  307,  308,   -1,   -1,   -1,  356,   -1,
   -1,   -1,  316,  317,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  373,   -1,   -1,  332,  333,
   -1,   -1,  380,   -1,  382,  383,   -1,   -1,  342,   -1,
   -1,   -1,   -1,   -1,  348,   -1,   -1,  351,  352,   -1,
   -1,   -1,  400,  401,  402,  359,   -1,   -1,   -1,   -1,
   -1,   -1,  366,  367,  412,  413,  414,  415,  416,  373,
  418,  375,   -1,  421,  295,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  386,   -1,  432,  389,  307,  308,   -1,   -1,
   -1,  439,   -1,   -1,   -1,  316,  317,   -1,   -1,   -1,
   -1,   -1,  406,   -1,  408,  409,  410,  411,   -1,   -1,
   -1,  332,  333,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  342,  295,   -1,   -1,   -1,   -1,  348,   -1,   -1,
  351,  352,   -1,   -1,  307,  308,   -1,   -1,  359,   -1,
   -1,   -1,   -1,  316,  317,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  373,   -1,  375,   -1,   -1,  378,  379,  332,
  333,   -1,   -1,  384,  385,  386,  387,  388,  389,  342,
  295,  392,   -1,   -1,  395,  348,   -1,   -1,  351,  352,
   -1,   -1,  307,  308,   -1,  406,  359,   -1,   -1,   -1,
   -1,  316,  317,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  373,   -1,  375,   -1,   -1,  378,  379,  332,  333,   -1,
   -1,  384,  385,  386,  387,  388,  389,  342,  295,  392,
   -1,   -1,  395,  348,   -1,   -1,  351,  352,   -1,   -1,
  307,  308,   -1,  406,  359,   -1,   -1,   -1,   -1,  316,
  317,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  373,   -1,
  375,   -1,   -1,  378,  379,  332,  333,   -1,   -1,  384,
  385,  386,  387,  388,  389,  342,  295,  392,   -1,   -1,
  395,  348,   -1,   -1,  351,  352,   -1,   -1,  307,  308,
   -1,  406,  359,   -1,   -1,   -1,   -1,  316,  317,   -1,
   -1,   -1,   -1,   -1,   -1,  267,  373,  269,  375,  271,
  272,  378,  379,  332,  333,   -1,  278,  384,  385,  386,
  387,  388,  389,  342,   -1,  392,   -1,   -1,  395,  348,
   -1,  293,  351,  352,   -1,   -1,   -1,   -1,   -1,  406,
  359,  267,   -1,  269,   -1,  271,  272,   -1,   -1,   -1,
   -1,   -1,  278,   -1,  373,   -1,  375,   -1,   -1,  378,
  379,   -1,  324,  325,   -1,  384,  385,  386,  387,  388,
  389,   -1,   -1,  392,   -1,   -1,  395,   -1,   -1,   -1,
   -1,   -1,   -1,  345,   -1,   -1,  348,  406,   -1,   -1,
   -1,  353,   -1,   -1,   -1,   -1,   -1,   -1,  324,  325,
   -1,   -1,   -1,  365,   -1,   -1,  368,   -1,   -1,   -1,
   -1,  373,   -1,   -1,  376,   -1,   -1,   -1,   -1,  345,
   -1,   -1,  348,   -1,   -1,   -1,   -1,  353,   -1,   -1,
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
"brainvar : TK_CONNECT NAME NAME NAME NAME NAME NAME NAME NAME NAME value value value",
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
"colvar : TK_CONNECT NAME NAME NAME NAME NAME NAME NAME value value value",
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
"lvar : TK_CONNECT NAME NAME NAME NAME NAME value value value",
"lvar : TK_RECURRENT_CONNECT NAME NAME NAME NAME NAME value value",
"$$6 :",
"cell : TK_CELL $$6 cellvars TK_END_CELL",
"cellvars : cellvar",
"cellvars : cellvars cellvar",
"cellvar : TK_TYPE NAME",
"cellvar : TK_CMP NAME NAME value value",
"cellvar : TK_CMP NAME NAME value value value",
"cellvar : TK_CONNECT NAME NAME value value value",
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
"$$58 :",
"synvar : TK_MAX_G $$58 twovalue",
"$$59 :",
"synvar : TK_RSE_INIT $$59 twovalue",
"$$60 :",
"synvar : TK_PREV_SPIKE_RANGE $$60 twovalue",
"synvar : TK_HEBB_START value",
"synvar : TK_HEBB_END value",
"synvar : TK_SFD_START value",
"synvar : TK_SFD_END value",
"$$61 :",
"syn_psg : TK_SYN_PSG $$61 spvars TK_END_SYN_PSG",
"spvars : spvar",
"spvars : spvars spvar",
"spvar : TK_TYPE NAME",
"spvar : TK_PSG_FILE NAME",
"$$62 :",
"syn_fd : TK_SYN_FD $$62 sfvars TK_END_SYN_FD",
"sfvars : sfvar",
"sfvars : sfvars sfvar",
"sfvar : TK_TYPE NAME",
"sfvar : TK_SEED INTEGER",
"sfvar : TK_SFD NAME",
"$$63 :",
"sfvar : TK_FACIL_TAU $$63 twovalue",
"$$64 :",
"sfvar : TK_DEPR_TAU $$64 twovalue",
"$$65 :",
"syn_learn : TK_SYN_LEARN $$65 slvars TK_END_SYN_LEARN",
"slvars : slvar",
"slvars : slvars slvar",
"slvar : TK_TYPE NAME",
"slvar : TK_SEED INTEGER",
"slvar : TK_LEARN NAME",
"slvar : TK_LEARN_SHAPE NAME",
"$$66 :",
"slvar : TK_NEG_HEB_WINDOW $$66 twovalue",
"$$67 :",
"slvar : TK_POS_HEB_WINDOW $$67 twovalue",
"$$68 :",
"slvar : TK_NEG_HEB_PEAK_DELTA_USE $$68 twovalue",
"$$69 :",
"slvar : TK_POS_HEB_PEAK_DELTA_USE $$69 twovalue",
"$$70 :",
"slvar : TK_NEG_HEB_PEAK_TIME $$70 twovalue",
"$$71 :",
"slvar : TK_POS_HEB_PEAK_TIME $$71 twovalue",
"$$72 :",
"syn_data : TK_SYN_DATA $$72 sdvars TK_END_SYN_DATA",
"sdvars : sdvar",
"sdvars : sdvars sdvar",
"sdvar : TK_TYPE NAME",
"sdvar : TK_SEED INTEGER",
"sdvar : TK_MAX_G value",
"$$73 :",
"sdvar : TK_DELAY $$73 twovalue",
"$$74 :",
"sdvar : TK_SYN_REVERSAL $$74 twovalue",
"$$75 :",
"syn_augmentation : TK_SYN_AUGMENTATION $$75 savars TK_END_SYN_AUGMENTATION",
"savars : savar",
"savars : savars savar",
"savar : TK_TYPE NAME",
"$$76 :",
"savar : TK_CA_INTERNAL $$76 twovalue",
"$$77 :",
"savar : TK_CA_TAU_DECAY $$77 twovalue",
"$$78 :",
"savar : TK_CA_TAU $$78 twovalue",
"$$79 :",
"savar : TK_CA_SPIKE_INC $$79 twovalue",
"$$80 :",
"savar : TK_MAX_AUGMENTATION $$80 twovalue",
"$$81 :",
"savar : TK_ALPHA $$81 twovalue",
"$$82 :",
"savar : TK_AUGMENTATION_INIT $$82 twovalue",
"$$83 :",
"savar : TK_AUGMENTATION_TAU $$83 twovalue",
"$$84 :",
"savar : TK_AUGMENTATION_DELAY $$84 twovalue",
"$$85 :",
"spikeshape : TK_SPIKE $$85 spikevars TK_END_SPIKE",
"spikevars : spikevar",
"spikevars : spikevars spikevar",
"spikevar : TK_TYPE NAME",
"$$86 :",
"spikevar : TK_VOLTAGES $$86 values",
"$$87 :",
"stimulus : TK_STIMULUS $$87 stvars TK_END_STIMULUS",
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
"$$88 :",
"stvar : TK_DYN_RANGE $$88 twovalue",
"stvar : TK_AMP_START value",
"stvar : TK_AMP_END value",
"stvar : TK_WIDTH value",
"stvar : TK_FREQ_START value",
"$$89 :",
"stvar : TK_TIME_START $$89 values",
"$$90 :",
"stvar : TK_TIME_END $$90 values",
"$$91 :",
"stinject : TK_ST_INJECT $$91 stivars TK_END_ST_INJECT",
"stivars : stivar",
"stivars : stivars stivar",
"stivar : TK_TYPE NAME",
"stivar : TK_STIM_TYPE NAME",
"stivar : TK_INJECT NAME NAME NAME NAME value",
"$$92 :",
"report : TK_REPORT $$92 reportvars TK_END_REPORT",
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
"$$93 :",
"reportvar : TK_TIME_START $$93 values",
"$$94 :",
"reportvar : TK_TIME_END $$94 values",
"reportvar : TK_SYNAPSE NAME",
"reportvar : TK_SYNAPSE_RSE NAME",
"reportvar : TK_SYNAPSE_UF NAME",
"reportvar : TK_SYN_AUGMENTATION NAME",
"reportvar : TK_SYN_CALCIUM NAME",
"reportvar : TK_SEED INTEGER",
"reportvar : TK_SEED TK_SELECT_FRONT",
"reportvar : TK_VERSION INTEGER",
"reportvar : TK_OPTION reportoptions",
"$$95 :",
"event : TK_EVENT $$95 eventvars TK_END_EVENT",
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

//#line 640 "parse.yaccj"

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

//For all those pesky mean/stdev values
protected TwoValue twoPtr;

protected ArrayList<? super Double> listPtr;

private int yywrap() {

	return 0;
	
	}

private int multiInput( String filename ) {

	return 0;
	
	}

private void yyerror(String string) {
	
	
	
	}
	
public void populateObjects( ) {
		
	//Fill out the Brains object fields
	fillArrays( brain.columnTypeNames , columnList , brain.columnTypes );
	
	fillArrays( brain.stimulusInjectNames , stimulusInjectList , brain.stimulusInjects );
	
	fillArrays( brain.reportNames , reportList , brain.reports );
	
	fillArrays( brain.eventNames , eventList , brain.events );
	
	//fill columns
	
	for( Column c : columnList) {
		
		fillValue( c.columnShellName, columnShellList, c.columnShell );
		
		fillArrays( c.layerNames , layerList , c.layers );
		
		}
	
	//fill layers
	
	for( Layer l : layerList ) {
		
		fillValue( l.layerShellName , layerShellList , l.layerShell );
		
		fillArrays( l.cellTypeNames , cellList, l.cellTypes );
		
		}
	
	//fill cells
	
	for( Cell c : cellList ) {
		
		fillArrays(c.compartmentNames, compartmentList, c.compartments );
		
		}
	
	//compartments
	for( Compartment c : compartmentList ) {
		
		fillValue( c.spikeshapeName , spikeshapeList, c.spikeshape );
		
		fillArrays( c.channelNames , channelList , c.channels);
		
		}
		
	
	}
	
@SuppressWarnings({ "unchecked", "rawtypes" })
public void fillArrays( ArrayList<String> typeList , ArrayList objList , ArrayList destList ) {
	
	for( int i = 0 ; i < typeList.size() ; i++ )
		for( int j = 0 ; j < objList.size() ; j ++ )
			if( typeList.get(i).equals( ( (TypedElement) objList.get(j) ).type ) ) {
				( (ArrayList) destList).add( (TypedElement) objList.get(j) );
				break;
				}
	}

@SuppressWarnings("rawtypes")
public void fillValue( String type , ArrayList objList , TypedElement dest ) {
	
	for( int j = 0 ; j < objList.size() ; j ++ )
		if( type.equals( ((TypedElement) objList.get(j) ).type ) ) {
			dest = (TypedElement) objList.get(j);
			break;
			}
	
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
	
	String fileContents = new String();
	try {
		fileContents = readFileAsString( filename );
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	scan = new Scanner( new StringReader( fileContents ) , this );
	
	
	
	}
	
private Scanner scan;
	
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
	
//#line 1727 "Parser.java"
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
//#line 96 "parse.yaccj"
{ multiInput( val_peek(0).sval ); }
break;
case 24:
//#line 101 "parse.yaccj"
{ brain = new Brain(); }
break;
case 28:
//#line 108 "parse.yaccj"
{ brain.type = val_peek(0).sval; }
break;
case 29:
//#line 109 "parse.yaccj"
{ brain.job = val_peek(0).sval; }
break;
case 30:
//#line 110 "parse.yaccj"
{  }
break;
case 31:
//#line 111 "parse.yaccj"
{  }
break;
case 32:
//#line 112 "parse.yaccj"
{  }
break;
case 33:
//#line 113 "parse.yaccj"
{ brain.FSV         = val_peek(0).dval; }
break;
case 34:
//#line 114 "parse.yaccj"
{ brain.duration    = val_peek(0).dval; }
break;
case 35:
//#line 115 "parse.yaccj"
{ brain.seed        = val_peek(0).ival; }
break;
case 36:
//#line 116 "parse.yaccj"
{ brain.columnTypeNames.add( val_peek(0).sval ); }
break;
case 37:
//#line 117 "parse.yaccj"
{ brain.stimulusInjectNames.add( val_peek(0).sval ); }
break;
case 38:
//#line 118 "parse.yaccj"
{ brain.reportNames.add( val_peek(0).sval); }
break;
case 39:
//#line 120 "parse.yaccj"
{ brain.connect.add( new Connect( val_peek(10).sval, val_peek(9).sval, val_peek(8).sval, val_peek(7).sval,
                                               val_peek(6).sval, val_peek(5).sval, val_peek(4).sval, val_peek(3).sval,
                                               val_peek(2).sval, val_peek(1).dval, val_peek(0).dval ) );
               }
break;
case 40:
//#line 125 "parse.yaccj"
{ brain.connect.add( new Connect( val_peek(11).sval, val_peek(10).sval, val_peek(9).sval, val_peek(8).sval,
                                               val_peek(7).sval, val_peek(6).sval, val_peek(5).sval, val_peek(4).sval,
                                               val_peek(3).sval, val_peek(2).dval, val_peek(1).dval, val_peek(0).dval ) );
               }
break;
case 41:
//#line 129 "parse.yaccj"
{
                 brain.recurrentConnect.add( new RecurrentConnect( val_peek(10).sval, val_peek(9).sval, val_peek(8).sval, val_peek(7).sval,
					                                               val_peek(6).sval, val_peek(5).sval, val_peek(4).sval, val_peek(3).sval,
					                                               val_peek(2).sval, val_peek(1).dval, val_peek(0).dval ) );
               }
break;
case 42:
//#line 135 "parse.yaccj"
{ brain.savefile = val_peek(1).sval; brain.saveTime = val_peek(0).dval; }
break;
case 43:
//#line 136 "parse.yaccj"
{ brain.load = val_peek(0).sval; }
break;
case 44:
//#line 137 "parse.yaccj"
{ brain.port = val_peek(0).ival; }
break;
case 45:
//#line 138 "parse.yaccj"
{ brain.port = -1; }
break;
case 46:
//#line 139 "parse.yaccj"
{ brain.port = -2; }
break;
case 47:
//#line 140 "parse.yaccj"
{ brain.server = val_peek(0).sval; }
break;
case 48:
//#line 141 "parse.yaccj"
{ brain.serverPort = val_peek(0).ival; }
break;
case 49:
//#line 142 "parse.yaccj"
{ brain.distance =  val_peek(0).ival; }
break;
case 50:
//#line 143 "parse.yaccj"
{ brain.outputCells = val_peek(0).ival;  }
break;
case 51:
//#line 144 "parse.yaccj"
{ brain.outputConnectMap = val_peek(0).ival;  }
break;
case 52:
//#line 145 "parse.yaccj"
{ brain.warningsOn = true;  }
break;
case 53:
//#line 146 "parse.yaccj"
{ brain.eventNames.add( val_peek(0).sval ); }
break;
case 54:
//#line 151 "parse.yaccj"
{

		columnShellList.add( columnShellList.size() , new ColumnShell() ); 
		
		}
break;
case 58:
//#line 162 "parse.yaccj"
{ columnShellList.get( columnShellList.size() - 1).type = val_peek(0).sval; }
break;
case 59:
//#line 163 "parse.yaccj"
{ columnShellList.get( columnShellList.size() - 1).width  = val_peek(0).dval; }
break;
case 60:
//#line 164 "parse.yaccj"
{ columnShellList.get( columnShellList.size() - 1).height = val_peek(0).dval; }
break;
case 61:
//#line 165 "parse.yaccj"
{ columnShellList.get( columnShellList.size() - 1).x = val_peek(1).dval; columnShellList.get(columnShellList.size() - 1).y = val_peek(0).dval; }
break;
case 62:
//#line 170 "parse.yaccj"
{ 
	
	columnList.add( columnList.size() , new Column() );
	
	}
break;
case 66:
//#line 181 "parse.yaccj"
{ columnList.get( columnList.size() - 1).type = val_peek(0).sval; }
break;
case 67:
//#line 182 "parse.yaccj"
{ columnList.get( columnList.size() - 1).columnShellName  = val_peek(0).sval; }
break;
case 68:
//#line 183 "parse.yaccj"
{ columnList.get( columnList.size() - 1).layerNames.add( val_peek(0).sval ); }
break;
case 69:
//#line 185 "parse.yaccj"
{ columnList.get( columnList.size() - 1).connects.add( new Connect( null, val_peek(8).sval, val_peek(7).sval, val_peek(6).sval, null, val_peek(5).sval, val_peek(4).sval, val_peek(3).sval,  val_peek(2).sval, val_peek(1).dval, val_peek(0).dval ) );
               }
break;
case 70:
//#line 188 "parse.yaccj"
{ columnList.get( columnList.size() - 1).connects.add( new Connect ( null, val_peek(9).sval, val_peek(8).sval, val_peek(7).sval, null, val_peek(6).sval, val_peek(5).sval, val_peek(4).sval, val_peek(3).sval, val_peek(2).dval, val_peek(1).dval, val_peek(0).dval ) );
               }
break;
case 71:
//#line 191 "parse.yaccj"
{
                columnList.get( columnList.size() - 1).recurrentConnects.add( new RecurrentConnect( null, val_peek(8).sval, val_peek(7).sval, val_peek(6).sval, null, val_peek(5).sval, val_peek(4).sval, val_peek(3).sval, val_peek(2).sval, val_peek(1).dval, val_peek(0).dval  ));
               }
break;
case 72:
//#line 198 "parse.yaccj"
{ layerShellList.add( layerShellList.size() , new LayerShell() ); }
break;
case 76:
//#line 205 "parse.yaccj"
{ layerShellList.get( layerShellList.size() - 1 ).type = val_peek(0).sval; }
break;
case 77:
//#line 206 "parse.yaccj"
{ layerShellList.get( layerShellList.size() - 1 ).lower  = val_peek(0).dval; }
break;
case 78:
//#line 207 "parse.yaccj"
{ layerShellList.get( layerShellList.size() - 1 ).upper  = val_peek(0).dval; }
break;
case 79:
//#line 212 "parse.yaccj"
{ layerList.add( new Layer() ); }
break;
case 83:
//#line 219 "parse.yaccj"
{ layerList.get( layerList.size() - 1 ).type = val_peek(0).sval; }
break;
case 84:
//#line 220 "parse.yaccj"
{ layerList.get( layerList.size() - 1 ).layerShellName = val_peek(0).sval; }
break;
case 85:
//#line 221 "parse.yaccj"
{ layerList.get( layerList.size() - 1 ).cellTypeNames.add( val_peek(1).sval );  layerList.get( layerList.size() - 1 ).cellTypeQuantities.add( val_peek(0).ival ); }
break;
case 86:
//#line 223 "parse.yaccj"
{ layerList.get( layerList.size() - 1 ).connects.add( new Connect( null, null, val_peek(6).sval, val_peek(5).sval, null, null, val_peek(4).sval, val_peek(3).sval, val_peek(2).sval, val_peek(1).dval, val_peek(0).dval ) );
               }
break;
case 87:
//#line 226 "parse.yaccj"
{ layerList.get( layerList.size() - 1 ).connects.add( new Connect( null, null, val_peek(7).sval, val_peek(6).sval, null, null, val_peek(5).sval, val_peek(4).sval, val_peek(3).sval, val_peek(2).dval, val_peek(1).dval, val_peek(0).dval ) );
               }
break;
case 88:
//#line 229 "parse.yaccj"
{
                layerList.get( layerList.size() - 1 ).recurrentConnects.add( new RecurrentConnect( null, null, val_peek(6).sval, val_peek(5).sval, null, null, val_peek(4).sval, val_peek(3).sval,  val_peek(2).sval, val_peek(1).dval, val_peek(0).dval ) );
               }
break;
case 89:
//#line 236 "parse.yaccj"
{ cellList.add( new Cell() ); }
break;
case 93:
//#line 243 "parse.yaccj"
{ cellList.get( cellList.size() - 1 ).type = val_peek(0).sval; }
break;
case 94:
//#line 244 "parse.yaccj"
{ 
       						 cellList.get( cellList.size() - 1 ).compartmentNames.add( val_peek(3).sval );
                             cellList.get( cellList.size() - 1 ).compartmentLabels.add( val_peek(2).sval );
                             cellList.get( cellList.size() - 1 ).xList.add( val_peek(1).dval );
                             cellList.get( cellList.size() - 1 ).xList.add( val_peek(0).dval );
                          }
break;
case 95:
//#line 250 "parse.yaccj"
{  }
break;
case 96:
//#line 251 "parse.yaccj"
{  }
break;
case 97:
//#line 256 "parse.yaccj"
{ compartmentList.add( new Compartment() ); }
break;
case 101:
//#line 263 "parse.yaccj"
{ compartmentList.get( compartmentList.size() - 1).type = val_peek(0).sval; }
break;
case 102:
//#line 264 "parse.yaccj"
{ compartmentList.get( compartmentList.size() - 1).seed = val_peek(0).ival; }
break;
case 103:
//#line 265 "parse.yaccj"
{ compartmentList.get( compartmentList.size() - 1).spikeshapeName = val_peek(0).sval; }
break;
case 104:
//#line 266 "parse.yaccj"
{ compartmentList.get( compartmentList.size() - 1).channelNames.add( val_peek(0).sval ); }
break;
case 105:
//#line 267 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).tauMembrane; }
break;
case 107:
//#line 268 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).rMembrane; }
break;
case 109:
//#line 269 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).threshold; }
break;
case 111:
//#line 270 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).leakReversal; }
break;
case 113:
//#line 271 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).leakConductance; }
break;
case 115:
//#line 272 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).vmRest; }
break;
case 117:
//#line 273 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).caInternal; }
break;
case 119:
//#line 274 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).caExternal; }
break;
case 121:
//#line 275 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).caTau; }
break;
case 123:
//#line 276 "parse.yaccj"
{ twoPtr = compartmentList.get( compartmentList.size() - 1).caSpikeIncrement; }
break;
case 125:
//#line 284 "parse.yaccj"
{ channelList.add( new Channel( val_peek(0).sval )); }
break;
case 127:
//#line 285 "parse.yaccj"
{ channelList.add( new Channel( val_peek(0).sval )); }
break;
case 129:
//#line 286 "parse.yaccj"
{ channelList.add( new Channel( val_peek(0).sval )); }
break;
case 131:
//#line 287 "parse.yaccj"
{ channelList.add( new Channel( val_peek(0).sval )); }
break;
case 133:
//#line 288 "parse.yaccj"
{ channelList.add( new Channel( val_peek(0).sval )); }
break;
case 149:
//#line 317 "parse.yaccj"
{ channelList.get( channelList.size() - 1 ).type = val_peek(0).sval; }
break;
case 150:
//#line 318 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).mPower; }
break;
case 152:
//#line 319 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).unitaryG; }
break;
case 154:
//#line 320 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).strength ; }
break;
case 156:
//#line 321 "parse.yaccj"
{ }
break;
case 158:
//#line 322 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).mInitial; }
break;
case 160:
//#line 323 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).reversalPotential; }
break;
case 162:
//#line 324 "parse.yaccj"
{ channelList.get( channelList.size() - 1 ).seed = val_peek(0).ival; }
break;
case 163:
//#line 327 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).eHalfMinM; }
break;
case 165:
//#line 328 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).tauScaleFactorM; }
break;
case 167:
//#line 329 "parse.yaccj"
{  }
break;
case 168:
//#line 330 "parse.yaccj"
{  }
break;
case 169:
//#line 333 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).caScaleFactor; }
break;
case 171:
//#line 334 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).caExpFactor; }
break;
case 173:
//#line 335 "parse.yaccj"
{  }
break;
case 175:
//#line 336 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).caHalfMin; }
break;
case 177:
//#line 337 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).caTauScaleFactor; }
break;
case 179:
//#line 340 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).hInitial; }
break;
case 181:
//#line 341 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).hPower;    }
break;
case 183:
//#line 342 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).eHalfMinMKa;  }
break;
case 185:
//#line 343 "parse.yaccj"
{ twoPtr = channelList.get( channelList.size() - 1 ).eHalfMinH;  }
break;
case 187:
//#line 344 "parse.yaccj"
{  }
break;
case 188:
//#line 345 "parse.yaccj"
{  }
break;
case 189:
//#line 346 "parse.yaccj"
{  }
break;
case 190:
//#line 347 "parse.yaccj"
{  }
break;
case 191:
//#line 349 "parse.yaccj"
{  }
break;
case 192:
//#line 350 "parse.yaccj"
{  }
break;
case 193:
//#line 351 "parse.yaccj"
{  }
break;
case 194:
//#line 352 "parse.yaccj"
{  }
break;
case 195:
//#line 354 "parse.yaccj"
{  }
break;
case 196:
//#line 354 "parse.yaccj"
{  }
break;
case 197:
//#line 355 "parse.yaccj"
{  }
break;
case 198:
//#line 355 "parse.yaccj"
{  }
break;
case 199:
//#line 356 "parse.yaccj"
{  }
break;
case 200:
//#line 356 "parse.yaccj"
{  }
break;
case 201:
//#line 357 "parse.yaccj"
{  }
break;
case 202:
//#line 357 "parse.yaccj"
{  }
break;
case 203:
//#line 360 "parse.yaccj"
{  }
break;
case 205:
//#line 361 "parse.yaccj"
{  }
break;
case 207:
//#line 362 "parse.yaccj"
{  }
break;
case 209:
//#line 363 "parse.yaccj"
{  }
break;
case 211:
//#line 364 "parse.yaccj"
{  }
break;
case 213:
//#line 365 "parse.yaccj"
{  }
break;
case 215:
//#line 366 "parse.yaccj"
{  }
break;
case 216:
//#line 367 "parse.yaccj"
{  }
break;
case 217:
//#line 368 "parse.yaccj"
{  }
break;
case 218:
//#line 369 "parse.yaccj"
{  }
break;
case 219:
//#line 370 "parse.yaccj"
{  }
break;
case 221:
//#line 371 "parse.yaccj"
{  }
break;
case 223:
//#line 372 "parse.yaccj"
{  }
break;
case 225:
//#line 373 "parse.yaccj"
{  }
break;
case 227:
//#line 378 "parse.yaccj"
{ synapseList.add( new Synapse() ); }
break;
case 231:
//#line 385 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).type = val_peek(0).sval; }
break;
case 232:
//#line 386 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).seed = val_peek(0).ival; }
break;
case 233:
//#line 387 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).sfdLabel = val_peek(0).sval; }
break;
case 234:
//#line 388 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).learnLabel = val_peek(0).sval; }
break;
case 235:
//#line 389 "parse.yaccj"
{  }
break;
case 236:
//#line 390 "parse.yaccj"
{  }
break;
case 237:
//#line 391 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).synPSG  = val_peek(0).sval; }
break;
case 238:
//#line 392 "parse.yaccj"
{ twoPtr = synapseList.get( synapseList.size() - 1).absoluteUse; }
break;
case 240:
//#line 393 "parse.yaccj"
{ twoPtr = synapseList.get( synapseList.size() - 1).delay; }
break;
case 242:
//#line 394 "parse.yaccj"
{ twoPtr = synapseList.get( synapseList.size() - 1).synReversal; }
break;
case 244:
//#line 395 "parse.yaccj"
{ twoPtr = synapseList.get( synapseList.size() - 1).maxConduct; }
break;
case 246:
//#line 396 "parse.yaccj"
{ }
break;
case 248:
//#line 397 "parse.yaccj"
{  }
break;
case 250:
//#line 398 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).hebbStart = val_peek(0).dval; }
break;
case 251:
//#line 399 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).hebbEnd = val_peek(0).dval; }
break;
case 252:
//#line 402 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).sfdStart = val_peek(0).dval; }
break;
case 253:
//#line 403 "parse.yaccj"
{ synapseList.get( synapseList.size() - 1).sfdEnd = val_peek(0).dval; }
break;
case 254:
//#line 408 "parse.yaccj"
{  }
break;
case 258:
//#line 415 "parse.yaccj"
{  }
break;
case 259:
//#line 416 "parse.yaccj"
{  }
break;
case 260:
//#line 421 "parse.yaccj"
{ synFacilDepressList.add( new SynFacilDepress() ); }
break;
case 264:
//#line 428 "parse.yaccj"
{ synFacilDepressList.get( synFacilDepressList.size() - 1 ).type = val_peek(0).sval; }
break;
case 265:
//#line 429 "parse.yaccj"
{ synFacilDepressList.get( synFacilDepressList.size() - 1 ).seed = val_peek(0).ival; }
break;
case 266:
//#line 430 "parse.yaccj"
{ synFacilDepressList.get( synFacilDepressList.size() - 1 ).SFD = val_peek(0).sval; }
break;
case 267:
//#line 431 "parse.yaccj"
{ twoPtr = synFacilDepressList.get( synFacilDepressList.size() - 1 ).facilTau; }
break;
case 269:
//#line 432 "parse.yaccj"
{ twoPtr = synFacilDepressList.get( synFacilDepressList.size() - 1 ).deprTau; }
break;
case 271:
//#line 437 "parse.yaccj"
{ synLearningList.add( new SynLearning() ); }
break;
case 275:
//#line 444 "parse.yaccj"
{ synLearningList.get( synLearningList.size() - 1 ).type = val_peek(0).sval; }
break;
case 276:
//#line 445 "parse.yaccj"
{ synLearningList.get( synLearningList.size() - 1 ).seed = val_peek(0).ival; }
break;
case 277:
//#line 446 "parse.yaccj"
{ synLearningList.get( synLearningList.size() - 1 ).learning = val_peek(0).sval; }
break;
case 278:
//#line 447 "parse.yaccj"
{ synLearningList.get( synLearningList.size() - 1 ).learningShape = val_peek(0).sval; }
break;
case 279:
//#line 448 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).negHebWindow; }
break;
case 281:
//#line 449 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).posHebWindow; }
break;
case 283:
//#line 450 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).negHebPeakDeltaUse; }
break;
case 285:
//#line 451 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).posHebPeakDeltaUse; }
break;
case 287:
//#line 452 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).posHebPeakTime; }
break;
case 289:
//#line 453 "parse.yaccj"
{ twoPtr = synLearningList.get( synLearningList.size() - 1 ).negHebPeakTime; }
break;
case 291:
//#line 458 "parse.yaccj"
{ synDataList.add( new SynData() ); }
break;
case 295:
//#line 465 "parse.yaccj"
{ synDataList.get( synDataList.size() - 1 ).type = val_peek(0).sval; }
break;
case 296:
//#line 466 "parse.yaccj"
{ synDataList.get( synDataList.size() - 1 ).seed = val_peek(0).ival; }
break;
case 297:
//#line 467 "parse.yaccj"
{ synDataList.get( synDataList.size() - 1 ).maxConduct = val_peek(0).dval; }
break;
case 298:
//#line 468 "parse.yaccj"
{ twoPtr = synDataList.get( synDataList.size() - 1 ).delay; }
break;
case 300:
//#line 469 "parse.yaccj"
{ twoPtr = synDataList.get( synDataList.size() - 1 ).synReversal; }
break;
case 302:
//#line 474 "parse.yaccj"
{ synAugList.add( new SynAugmentation() ); }
break;
case 306:
//#line 480 "parse.yaccj"
{ synAugList.get( synAugList.size() - 1 ).type = val_peek(0).sval; }
break;
case 307:
//#line 481 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).caInternal; }
break;
case 309:
//#line 482 "parse.yaccj"
{  }
break;
case 311:
//#line 483 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).caTau; }
break;
case 313:
//#line 484 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).caSpikeIncrement; }
break;
case 315:
//#line 485 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).maxAugmentation; }
break;
case 317:
//#line 486 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).alpha; }
break;
case 319:
//#line 487 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).augmentationInit; }
break;
case 321:
//#line 488 "parse.yaccj"
{ twoPtr = synAugList.get( synAugList.size() - 1 ).augmentationTau; }
break;
case 323:
//#line 489 "parse.yaccj"
{  }
break;
case 325:
//#line 494 "parse.yaccj"
{ spikeshapeList.add( new SpikeShape() ); }
break;
case 329:
//#line 501 "parse.yaccj"
{ spikeshapeList.get( spikeshapeList.size() - 1 ).type = val_peek(0).sval; }
break;
case 330:
//#line 502 "parse.yaccj"
{  }
break;
case 331:
//#line 502 "parse.yaccj"
{  }
break;
case 332:
//#line 507 "parse.yaccj"
{ stimulusList.add( new Stimulus() ); }
break;
case 336:
//#line 514 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).type = val_peek(0).sval; }
break;
case 337:
//#line 515 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).mode = val_peek(0).sval; }
break;
case 338:
//#line 516 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).pattern = val_peek(0).sval; }
break;
case 339:
//#line 517 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).vertTrans = val_peek(0).dval; }
break;
case 340:
//#line 518 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).phase = val_peek(0).dval; }
break;
case 341:
//#line 519 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).rate = val_peek(0).dval;}
break;
case 342:
//#line 520 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).tauNoise = val_peek(0).dval; }
break;
case 343:
//#line 521 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).correl = val_peek(0).dval;}
break;
case 344:
//#line 522 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).timing = val_peek(0).sval; }
break;
case 345:
//#line 523 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).filename = val_peek(0).sval; }
break;
case 346:
//#line 524 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).port = val_peek(0).ival; }
break;
case 347:
//#line 525 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).port = -1; }
break;
case 348:
//#line 526 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).sameSeed = val_peek(0).ival; }
break;
case 349:
//#line 527 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).seed = val_peek(0).ival;}
break;
case 350:
//#line 528 "parse.yaccj"
{  }
break;
case 351:
//#line 529 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).cellsPerFreq = val_peek(0).ival; }
break;
case 352:
//#line 530 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).timeIncrement = val_peek(0).dval; }
break;
case 353:
//#line 531 "parse.yaccj"
{ twoPtr = stimulusList.get( stimulusList.size() - 1 ).dynRange; }
break;
case 355:
//#line 532 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).ampStart = val_peek(0).dval; }
break;
case 356:
//#line 533 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).ampEnd = val_peek(0).dval; }
break;
case 357:
//#line 534 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).width = val_peek(0).dval; }
break;
case 358:
//#line 535 "parse.yaccj"
{ stimulusList.get( stimulusList.size() - 1 ).freqStart = val_peek(0).dval; }
break;
case 359:
//#line 536 "parse.yaccj"
{ listPtr = stimulusList.get( stimulusList.size() - 1 ).timeStart; }
break;
case 361:
//#line 537 "parse.yaccj"
{ listPtr = stimulusList.get( stimulusList.size() - 1 ).timeEnd; }
break;
case 363:
//#line 542 "parse.yaccj"
{ stimulusInjectList.add( new StimulusInject() ); }
break;
case 367:
//#line 549 "parse.yaccj"
{ stimulusInjectList.get( stimulusInjectList.size() - 1 ).type = val_peek(0).sval; }
break;
case 368:
//#line 550 "parse.yaccj"
{ stimulusInjectList.get( stimulusInjectList.size() - 1 ).stimulusName = val_peek(0).sval; }
break;
case 369:
//#line 551 "parse.yaccj"
{ 
      		stimulusInjectList.get( stimulusInjectList.size() - 1 ).columnName = val_peek(4).sval;
            stimulusInjectList.get( stimulusInjectList.size() - 1 ).layerName = val_peek(4).sval;
            stimulusInjectList.get( stimulusInjectList.size() - 1 ).cellName = val_peek(4).sval;
            stimulusInjectList.get( stimulusInjectList.size() - 1 ).compartmentName = val_peek(4).sval;
            stimulusInjectList.get( stimulusInjectList.size() - 1 ).probability = val_peek(4).dval;
            }
break;
case 370:
//#line 562 "parse.yaccj"
{ reportList.add( new Report() ); }
break;
case 374:
//#line 569 "parse.yaccj"
{ reportList.get( reportList.size() - 1 ).type = val_peek(0).sval; }
break;
case 375:
//#line 571 "parse.yaccj"
{  }
break;
case 376:
//#line 572 "parse.yaccj"
{ reportList.get( reportList.size() - 1 ).filename = val_peek(0).sval; }
break;
case 377:
//#line 573 "parse.yaccj"
{  }
break;
case 378:
//#line 574 "parse.yaccj"
{  }
break;
case 379:
//#line 575 "parse.yaccj"
{  }
break;
case 380:
//#line 576 "parse.yaccj"
{  }
break;
case 381:
//#line 577 "parse.yaccj"
{  }
break;
case 382:
//#line 578 "parse.yaccj"
{  }
break;
case 383:
//#line 579 "parse.yaccj"
{  }
break;
case 384:
//#line 580 "parse.yaccj"
{  }
break;
case 385:
//#line 581 "parse.yaccj"
{  }
break;
case 386:
//#line 582 "parse.yaccj"
{  }
break;
case 387:
//#line 582 "parse.yaccj"
{  }
break;
case 388:
//#line 583 "parse.yaccj"
{  }
break;
case 389:
//#line 583 "parse.yaccj"
{  }
break;
case 390:
//#line 584 "parse.yaccj"
{  }
break;
case 391:
//#line 585 "parse.yaccj"
{  }
break;
case 392:
//#line 586 "parse.yaccj"
{  }
break;
case 393:
//#line 587 "parse.yaccj"
{  }
break;
case 394:
//#line 588 "parse.yaccj"
{  }
break;
case 395:
//#line 589 "parse.yaccj"
{  }
break;
case 396:
//#line 590 "parse.yaccj"
{  }
break;
case 397:
//#line 591 "parse.yaccj"
{  }
break;
case 398:
//#line 592 "parse.yaccj"
{ }
break;
case 399:
//#line 595 "parse.yaccj"
{ eventList.add( new Event() ); }
break;
case 403:
//#line 602 "parse.yaccj"
{ eventList.get( eventList.size() - 1 ).type = val_peek(0).sval; }
break;
case 404:
//#line 603 "parse.yaccj"
{ eventList.get( eventList.size() - 1 ).synapseName = val_peek(0).sval; }
break;
case 405:
//#line 604 "parse.yaccj"
{
            eventList.get( eventList.size() - 1 ).columnName = val_peek(3).sval;
            eventList.get( eventList.size() - 1 ).layerName = val_peek(2).sval;
            eventList.get( eventList.size() - 1 ).cellName = val_peek(1).sval;
            eventList.get( eventList.size() - 1 ).compartmentName = val_peek(0).sval;
        }
break;
case 406:
//#line 610 "parse.yaccj"
{ 
    	eventList.get( eventList.size() - 1 ).filename = val_peek(1).sval;
    	eventList.get( eventList.size() - 1 ).value = val_peek(0).dval; 
    	}
break;
case 409:
//#line 621 "parse.yaccj"
{  }
break;
case 410:
//#line 622 "parse.yaccj"
{  }
break;
case 411:
//#line 627 "parse.yaccj"
{ listPtr.add( val_peek(0).dval ); }
break;
case 412:
//#line 628 "parse.yaccj"
{ listPtr.add( val_peek(1).dval ); }
break;
case 413:
//#line 631 "parse.yaccj"
{ twoPtr.set( val_peek(1).dval , val_peek(0).dval ); twoPtr = null; }
break;
case 414:
//#line 632 "parse.yaccj"
{ twoPtr.set( val_peek(0).dval , 0.0 ); twoPtr = null; }
break;
case 415:
//#line 635 "parse.yaccj"
{ yyval.dval = (double) val_peek(0).ival; }
break;
case 416:
//#line 636 "parse.yaccj"
{ yyval.dval = val_peek(0).dval; }
break;
//#line 2937 "Parser.java"
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
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
