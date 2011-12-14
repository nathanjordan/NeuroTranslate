
/* A Bison parser, made by GNU Bison 2.4.1.  */

/* Skeleton implementation for Bison's Yacc-like parsers in C
   
      Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "2.4.1"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1

/* Using locations.  */
#define YYLSP_NEEDED 0



/* Copy the first part of user declarations.  */

/* Line 189 of yacc.c  */
#line 3 "parse.y"


#ifndef __PARSE_C__
#define __PARSE_C__

#include <string.h>
#include <math.h>
#include <sys/stat.h>

#include "input.h"
#include "proto.h"
#include "FileStack.h"
#include "defines.h"

#define TRUE  1
#define FALSE 0
#define VSIZE 100

extern TMP_INPUT *TIN;       /* This must be global so parse.y can access it */

T_BRAIN           *brain;           /* These are temporary pointers, to whatever */
T_CSHELL          *csh;             /* element the parser is working on          */
T_COLUMN          *column;
T_LSHELL          *lsh;
T_LAYER           *layer;
T_CELL            *cell;
T_CMP             *cmp;
T_CHANNEL         *chan;
T_SYNAPSE         *syn;
T_SYN_FD          *syn_fd;
T_SYNLEARN        *syn_learn;
T_SYNDATA         *syn_data;
T_SYNPSG          *syn_psg;
T_SYNAUGMENTATION *syn_augmentation;
T_SPIKE           *spike;
T_STIMULUS        *stim;
T_STINJECT        *sti;
T_REPORT          *report;
T_EVENT           *event;

FileStack fileStack;       /* incase there are multiple files being parsed */
int initFlag = 0;          /* flag to indicate if the file stack is ever used */
extern char* currentFile;  /* name of file currently being parsed */

int i, nval;               /* For reading a list of numbers of unknown length */
double vlist [VSIZE];
double *twoptr;
double *allocVlist (int, double *);
double *allocRVlist (int, double *);


/* Line 189 of yacc.c  */
#line 125 "parse.c"

/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* Enabling the token table.  */
#ifndef YYTOKEN_TABLE
# define YYTOKEN_TABLE 0
#endif


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     REAL = 258,
     INTEGER = 259,
     LOGICAL = 260,
     NAME = 261,
     TK_ABSOLUTE_USE = 262,
     TK_AMP_END = 263,
     TK_AMP_START = 264,
     TK_ASCII = 265,
     TK_BRAIN = 266,
     TK_CA_EXP = 267,
     TK_CA_EXTERNAL = 268,
     TK_CA_HALF_MIN = 269,
     TK_CA_INTERNAL = 270,
     TK_CA_SCALE = 271,
     TK_CA_SPIKE_INC = 272,
     TK_CA_TAU = 273,
     TK_CA_TAU_SCALE = 274,
     TK_CELL = 275,
     TK_CELLS = 276,
     TK_CELLS_PER_FREQ = 277,
     TK_CELL_TYPE = 278,
     TK_CHANNEL = 279,
     TK_COLUMN = 280,
     TK_CSHELL = 281,
     TK_COLUMN_TYPE = 282,
     TK_CMP = 283,
     TK_G = 284,
     TK_CONNECT = 285,
     TK_DATA_LABEL = 286,
     TK_DELAY = 287,
     TK_DEPR_TAU = 288,
     TK_DURATION = 289,
     TK_DYN_RANGE = 290,
     TK_END_BRAIN = 291,
     TK_END_COLUMN = 292,
     TK_END_CSHELL = 293,
     TK_END_CMP = 294,
     TK_END_CELL = 295,
     TK_END_CHANNEL = 296,
     TK_END_LAYER = 297,
     TK_END_LSHELL = 298,
     TK_END_REPORT = 299,
     TK_END_SPIKE = 300,
     TK_END_STIMULUS = 301,
     TK_END_ST_INJECT = 302,
     TK_END_SYNAPSE = 303,
     TK_END_SYN_DATA = 304,
     TK_END_SYN_FD = 305,
     TK_END_SYN_PSG = 306,
     TK_END_SYN_LEARN = 307,
     TK_E_HALF_MIN_H = 308,
     TK_E_HALF_MIN_M = 309,
     TK_FACIL_TAU = 310,
     TK_FILENAME = 311,
     TK_FREQUENCY = 312,
     TK_FREQ_ROWS = 313,
     TK_FREQ_START = 314,
     TK_FSV = 315,
     TK_HEIGHT = 316,
     TK_H_INITIAL = 317,
     TK_H_POWER = 318,
     TK_IGNORE_EMPTY = 319,
     TK_INJECT = 320,
     TK_INTERACTIVE = 321,
     TK_LAYER = 322,
     TK_LSHELL = 323,
     TK_LAYER_TYPE = 324,
     TK_LEAK_G = 325,
     TK_LEAK_REVERSAL = 326,
     TK_LEARN = 327,
     TK_LEARN_LABEL = 328,
     TK_LOCATION = 329,
     TK_LOWER = 330,
     TK_MAX_G = 331,
     TK_MODE = 332,
     TK_M_INITIAL = 333,
     TK_M_POWER = 334,
     TK_NEG_HEB_WINDOW = 335,
     TK_PATTERN = 336,
     TK_POS_HEB_WINDOW = 337,
     TK_PROB = 338,
     TK_PSG_FILE = 339,
     TK_RELOAD_SYN = 340,
     TK_REPORT = 341,
     TK_REPORT_ON = 342,
     TK_REVERSAL = 343,
     TK_RSE = 344,
     TK_RSE_LABEL = 345,
     TK_R_MEMBRANE = 346,
     TK_SAMESEED = 347,
     TK_SAVE_SYN = 348,
     TK_SEED = 349,
     TK_SFD = 350,
     TK_SFD_LABEL = 351,
     TK_SLOPE_H = 352,
     TK_SLOPE_M = 353,
     TK_SPIKE = 354,
     TK_STIMULUS = 355,
     TK_SPIKE_HW = 356,
     TK_ST_INJECT = 357,
     TK_STIM_TYPE = 358,
     TK_SYNAPSE = 359,
     TK_STRENGTH = 360,
     TK_SYN_DATA = 361,
     TK_SYN_FD = 362,
     TK_SYN_LEARN = 363,
     TK_SYN_PSG = 364,
     TK_SYN_REVERSAL = 365,
     TK_TAU_MEMBRANE = 366,
     TK_TAU_SCALE_M = 367,
     TK_TAU_SCALE_H = 368,
     TK_THRESHOLD = 369,
     TK_TIME_END = 370,
     TK_TIME_START = 371,
     TK_TIME_FREQ_INC = 372,
     TK_TIMING = 373,
     TK_TYPE = 374,
     TK_UPPER = 375,
     TK_UNITARY_G = 376,
     TK_VMREST = 377,
     TK_VOLTAGES = 378,
     TK_VTAU_VAL_M = 379,
     TK_VTAU_VAL_H = 380,
     TK_PORT = 381,
     TK_WIDTH = 382,
     TK_JOB = 383,
     TK_DISTRIBUTE = 384,
     TK_VAL_M_STDEV = 385,
     TK_VOLT_M_STDEV = 386,
     TK_SLOPE_M_STDEV = 387,
     TK_VAL_H_STDEV = 388,
     TK_VOLT_H_STDEV = 389,
     TK_SLOPE_H_STDEV = 390,
     TK_NEG_HEB_PEAK_DELTA_USE = 391,
     TK_NEG_HEB_PEAK_TIME = 392,
     TK_VTAU_VOLT_M = 393,
     TK_POS_HEB_PEAK_DELTA_USE = 394,
     TK_POS_HEB_PEAK_TIME = 395,
     TK_VTAU_VOLT_H = 396,
     TK_INCLUDE = 397,
     TK_RSE_INIT = 398,
     TK_VERT_TRANS = 399,
     TK_PREV_SPIKE_RANGE = 400,
     TK_CONNECT_RPT = 401,
     TK_SPIKE_RPT = 402,
     TK_SERVER = 403,
     TK_SINGLE = 404,
     TK_CA_EXP_RANGE = 405,
     TK_PHASE_SHIFT = 406,
     TK_STRENGTH_RANGE = 407,
     TK_SYNAPSE_RSE = 408,
     TK_ALPHA_SCALE_H = 409,
     TK_ALPHA_SCALE_M = 410,
     TK_BETA_SCALE_H = 411,
     TK_BETA_SCALE_M = 412,
     TK_SAVE = 413,
     TK_LOAD = 414,
     TK_DISTANCE = 415,
     TK_OUTPUT_CONNECT_MAP = 416,
     TK_OUTPUT_CELLS = 417,
     TK_AUTO = 418,
     TK_SERVER_PORT = 419,
     TK_VERSION = 420,
     TK_SYNAPSE_UF = 421,
     TK_RECURRENT_CONNECT = 422,
     TK_ALPHA = 423,
     TK_SYN_AUGMENTATION = 424,
     TK_END_SYN_AUGMENTATION = 425,
     TK_MAX_AUGMENTATION = 426,
     TK_AUGMENTATION_INIT = 427,
     TK_AUGMENTATION_TAU = 428,
     TK_SYN_CALCIUM = 429,
     TK_CA_TAU_DECAY = 430,
     TK_EXP = 431,
     TK_SELECT_FRONT = 432,
     TK_OPTION = 433,
     TK_AVERAGE_SYN = 434,
     TK_AUGMENTATION_DELAY = 435,
     TK_WARNINGS_OFF = 436,
     TK_HIDE_TIMESTEP = 437,
     TK_HEBB_START = 438,
     TK_HEBB_END = 439,
     TK_EVENT = 440,
     TK_OVERRIDE = 441,
     TK_LEARN_SHAPE = 442,
     TK_RATE = 443,
     TK_TAU_NOISE = 444,
     TK_CORREL = 445,
     TK_END_EVENT = 446,
     TK_CELL_SEQUENCE = 447,
     TK_SFD_START = 448,
     TK_SFD_END = 449,
     TK_Km = 450,
     TK_Kahp = 451,
     TK_Ka = 452,
     TK_Na = 453,
     TK_Knicd = 454
   };
#endif



#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
{

/* Line 214 of yacc.c  */
#line 55 "parse.y"

  double rval;
  int    ival;
  char   sval [STRLEN];



/* Line 214 of yacc.c  */
#line 368 "parse.c"
} YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif


/* Copy the second part of user declarations.  */


/* Line 264 of yacc.c  */
#line 380 "parse.c"

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#elif (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
typedef signed char yytype_int8;
#else
typedef short int yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(msgid) dgettext ("bison-runtime", msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(msgid) msgid
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(e) ((void) (e))
#else
# define YYUSE(e) /* empty */
#endif

/* Identity function, used to suppress warnings about constant conditions.  */
#ifndef lint
# define YYID(n) (n)
#else
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static int
YYID (int yyi)
#else
static int
YYID (yyi)
    int yyi;
#endif
{
  return yyi;
}
#endif

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#     ifndef _STDLIB_H
#      define _STDLIB_H 1
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (YYID (0))
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined _STDLIB_H \
       && ! ((defined YYMALLOC || defined malloc) \
	     && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef _STDLIB_H
#    define _STDLIB_H 1
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
	 || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

/* Copy COUNT objects from FROM to TO.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(To, From, Count) \
      __builtin_memcpy (To, From, (Count) * sizeof (*(From)))
#  else
#   define YYCOPY(To, From, Count)		\
      do					\
	{					\
	  YYSIZE_T yyi;				\
	  for (yyi = 0; yyi < (Count); yyi++)	\
	    (To)[yyi] = (From)[yyi];		\
	}					\
      while (YYID (0))
#  endif
# endif

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)				\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack_alloc, Stack, yysize);			\
	Stack = &yyptr->Stack_alloc;					\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (YYID (0))

#endif

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  66
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   1301

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  200
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  171
/* YYNRULES -- Number of rules.  */
#define YYNRULES  424
/* YYNRULES -- Number of states.  */
#define YYNSTATES  753

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   454

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50,    51,    52,    53,    54,
      55,    56,    57,    58,    59,    60,    61,    62,    63,    64,
      65,    66,    67,    68,    69,    70,    71,    72,    73,    74,
      75,    76,    77,    78,    79,    80,    81,    82,    83,    84,
      85,    86,    87,    88,    89,    90,    91,    92,    93,    94,
      95,    96,    97,    98,    99,   100,   101,   102,   103,   104,
     105,   106,   107,   108,   109,   110,   111,   112,   113,   114,
     115,   116,   117,   118,   119,   120,   121,   122,   123,   124,
     125,   126,   127,   128,   129,   130,   131,   132,   133,   134,
     135,   136,   137,   138,   139,   140,   141,   142,   143,   144,
     145,   146,   147,   148,   149,   150,   151,   152,   153,   154,
     155,   156,   157,   158,   159,   160,   161,   162,   163,   164,
     165,   166,   167,   168,   169,   170,   171,   172,   173,   174,
     175,   176,   177,   178,   179,   180,   181,   182,   183,   184,
     185,   186,   187,   188,   189,   190,   191,   192,   193,   194,
     195,   196,   197,   198,   199
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint16 yyprhs[] =
{
       0,     0,     3,     5,     8,    11,    13,    15,    17,    19,
      21,    23,    25,    27,    29,    31,    33,    35,    37,    39,
      41,    43,    45,    47,    49,    52,    53,    58,    60,    63,
      66,    69,    72,    74,    76,    79,    82,    85,    88,    91,
      94,   107,   121,   134,   137,   140,   143,   147,   150,   153,
     156,   159,   162,   165,   168,   171,   174,   177,   180,   181,
     186,   188,   191,   194,   197,   200,   204,   205,   210,   212,
     215,   218,   221,   224,   235,   247,   258,   259,   264,   266,
     269,   272,   275,   278,   279,   284,   286,   289,   292,   295,
     299,   308,   318,   327,   328,   333,   335,   338,   341,   347,
     354,   361,   362,   367,   369,   372,   375,   378,   381,   384,
     385,   389,   390,   394,   395,   399,   400,   404,   405,   409,
     410,   414,   415,   419,   420,   424,   425,   429,   430,   434,
     435,   439,   440,   446,   447,   453,   454,   460,   461,   467,
     468,   474,   476,   479,   482,   484,   487,   490,   492,   495,
     498,   500,   503,   506,   508,   511,   514,   515,   519,   520,
     524,   525,   529,   530,   534,   535,   539,   540,   544,   547,
     548,   552,   553,   557,   562,   565,   566,   570,   571,   575,
     576,   580,   581,   585,   586,   590,   591,   595,   596,   600,
     601,   605,   606,   610,   613,   616,   619,   622,   625,   628,
     631,   634,   635,   639,   640,   644,   645,   649,   650,   654,
     655,   659,   660,   664,   665,   669,   670,   674,   675,   679,
     680,   684,   688,   692,   695,   698,   699,   703,   704,   708,
     709,   713,   714,   718,   719,   724,   726,   729,   732,   735,
     738,   741,   744,   747,   750,   751,   755,   756,   760,   761,
     765,   766,   770,   771,   775,   776,   780,   783,   786,   789,
     792,   793,   798,   800,   803,   806,   809,   810,   815,   817,
     820,   823,   826,   829,   830,   834,   835,   839,   840,   845,
     847,   850,   853,   856,   859,   862,   863,   867,   868,   872,
     873,   877,   878,   882,   883,   887,   888,   892,   893,   898,
     900,   903,   906,   909,   910,   914,   915,   919,   920,   924,
     925,   930,   932,   935,   938,   939,   943,   944,   948,   949,
     953,   954,   958,   959,   963,   964,   968,   969,   973,   974,
     978,   979,   983,   984,   989,   991,   994,   997,   998,  1002,
    1003,  1008,  1010,  1013,  1016,  1019,  1022,  1025,  1028,  1031,
    1034,  1037,  1040,  1043,  1046,  1049,  1052,  1055,  1058,  1061,
    1062,  1066,  1067,  1071,  1074,  1077,  1080,  1083,  1084,  1088,
    1089,  1093,  1094,  1099,  1101,  1104,  1107,  1110,  1117,  1118,
    1123,  1125,  1128,  1131,  1137,  1140,  1143,  1146,  1148,  1151,
    1154,  1157,  1160,  1163,  1166,  1167,  1171,  1172,  1176,  1179,
    1182,  1185,  1188,  1191,  1194,  1197,  1200,  1203,  1204,  1209,
    1211,  1214,  1217,  1220,  1226,  1230,  1232,  1235,  1237,  1239,
    1241,  1244,  1247,  1249,  1251
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int16 yyrhs[] =
{
     201,     0,    -1,   202,    -1,   201,   202,    -1,   201,     1,
      -1,   203,    -1,   211,    -1,   207,    -1,   219,    -1,   215,
      -1,   223,    -1,   227,    -1,   242,    -1,   289,    -1,   299,
      -1,   303,    -1,   309,    -1,   319,    -1,   326,    -1,   339,
      -1,   344,    -1,   352,    -1,   356,    -1,   362,    -1,   142,
       6,    -1,    -1,    11,   204,   205,    36,    -1,   206,    -1,
     205,   206,    -1,   119,     6,    -1,   128,     6,    -1,   129,
       6,    -1,   146,    -1,   147,    -1,    60,   370,    -1,    34,
     370,    -1,    94,     4,    -1,    27,     6,    -1,   102,     6,
      -1,    86,     6,    -1,    30,     6,     6,     6,     6,     6,
       6,     6,     6,     6,   370,   370,    -1,    30,     6,     6,
       6,     6,     6,     6,     6,     6,     6,   370,   370,   370,
      -1,   167,     6,     6,     6,     6,     6,     6,     6,     6,
       6,   370,   370,    -1,    66,     5,    -1,    64,     5,    -1,
      93,     6,    -1,   158,     6,   370,    -1,   159,     6,    -1,
     126,     4,    -1,   126,   163,    -1,   126,   149,    -1,   148,
       6,    -1,   164,     4,    -1,   160,     5,    -1,   162,     5,
      -1,   161,     5,    -1,   178,   181,    -1,   185,     6,    -1,
      -1,    26,   208,   209,    38,    -1,   210,    -1,   209,   210,
      -1,   119,     6,    -1,   127,   370,    -1,    61,   370,    -1,
      74,   370,   370,    -1,    -1,    25,   212,   213,    37,    -1,
     214,    -1,   213,   214,    -1,   119,     6,    -1,    26,     6,
      -1,    69,     6,    -1,    30,     6,     6,     6,     6,     6,
       6,     6,   370,   370,    -1,    30,     6,     6,     6,     6,
       6,     6,     6,   370,   370,   370,    -1,   167,     6,     6,
       6,     6,     6,     6,     6,   370,   370,    -1,    -1,    68,
     216,   217,    43,    -1,   218,    -1,   217,   218,    -1,   119,
       6,    -1,    75,   370,    -1,   120,   370,    -1,    -1,    67,
     220,   221,    42,    -1,   222,    -1,   221,   222,    -1,   119,
       6,    -1,    68,     6,    -1,    23,     6,     4,    -1,    30,
       6,     6,     6,     6,     6,   370,   370,    -1,    30,     6,
       6,     6,     6,     6,   370,   370,   370,    -1,   167,     6,
       6,     6,     6,     6,   370,   370,    -1,    -1,    20,   224,
     225,    40,    -1,   226,    -1,   225,   226,    -1,   119,     6,
      -1,    28,     6,     6,   370,   370,    -1,    28,     6,     6,
     370,   370,   370,    -1,    30,     6,     6,   370,   370,   370,
      -1,    -1,    28,   228,   229,    39,    -1,   230,    -1,   229,
     230,    -1,   119,     6,    -1,    94,     4,    -1,    99,     6,
      -1,    24,     6,    -1,    -1,   101,   231,   369,    -1,    -1,
     111,   232,   369,    -1,    -1,    91,   233,   369,    -1,    -1,
     114,   234,   369,    -1,    -1,    71,   235,   369,    -1,    -1,
      70,   236,   369,    -1,    -1,   122,   237,   369,    -1,    -1,
      15,   238,   369,    -1,    -1,    13,   239,   369,    -1,    -1,
      18,   240,   369,    -1,    -1,    17,   241,   369,    -1,    -1,
      24,   195,   243,   248,    41,    -1,    -1,    24,   196,   244,
     249,    41,    -1,    -1,    24,   197,   245,   250,    41,    -1,
      -1,    24,   198,   246,   251,    41,    -1,    -1,    24,   199,
     247,   252,    41,    -1,   253,    -1,   248,   253,    -1,   248,
     260,    -1,   253,    -1,   249,   253,    -1,   249,   263,    -1,
     253,    -1,   250,   253,    -1,   250,   269,    -1,   253,    -1,
     251,   253,    -1,   251,   278,    -1,   253,    -1,   252,   253,
      -1,   119,     6,    -1,    -1,    79,   254,   369,    -1,    -1,
     121,   255,   369,    -1,    -1,   105,   256,   369,    -1,    -1,
     152,   257,   369,    -1,    -1,    78,   258,   369,    -1,    -1,
      88,   259,   369,    -1,    94,     4,    -1,    -1,    54,   261,
     369,    -1,    -1,   112,   262,   369,    -1,    98,   370,   370,
     370,    -1,   132,   370,    -1,    -1,    16,   264,   369,    -1,
      -1,    12,   265,   369,    -1,    -1,   150,   266,   369,    -1,
      -1,    14,   267,   369,    -1,    -1,    19,   268,   369,    -1,
      -1,    62,   270,   369,    -1,    -1,    63,   271,   369,    -1,
      -1,    54,   272,   369,    -1,    -1,    53,   273,   369,    -1,
      98,   370,    -1,   132,   370,    -1,    97,   370,    -1,   135,
     370,    -1,   130,   370,    -1,   131,   370,    -1,   133,   370,
      -1,   134,   370,    -1,    -1,   124,   274,   368,    -1,    -1,
     138,   275,   368,    -1,    -1,   125,   276,   368,    -1,    -1,
     141,   277,   368,    -1,    -1,    54,   279,   369,    -1,    -1,
      53,   280,   369,    -1,    -1,    62,   281,   369,    -1,    -1,
      63,   282,   369,    -1,    -1,   112,   283,   369,    -1,    -1,
     113,   284,   369,    -1,    98,   370,   370,    -1,    97,   370,
     370,    -1,   132,   370,    -1,   135,   370,    -1,    -1,   155,
     285,   369,    -1,    -1,   157,   286,   369,    -1,    -1,   154,
     287,   369,    -1,    -1,   156,   288,   369,    -1,    -1,   104,
     290,   291,    48,    -1,   292,    -1,   291,   292,    -1,   119,
       6,    -1,    94,     4,    -1,    96,     6,    -1,    73,     6,
      -1,    31,     6,    -1,   169,     6,    -1,   109,     6,    -1,
      -1,     7,   293,   369,    -1,    -1,    32,   294,   369,    -1,
      -1,   110,   295,   369,    -1,    -1,    76,   296,   369,    -1,
      -1,   143,   297,   369,    -1,    -1,   145,   298,   369,    -1,
     183,   370,    -1,   184,   370,    -1,   193,   370,    -1,   194,
     370,    -1,    -1,   109,   300,   301,    51,    -1,   302,    -1,
     301,   302,    -1,   119,     6,    -1,    84,     6,    -1,    -1,
     107,   304,   305,    50,    -1,   306,    -1,   305,   306,    -1,
     119,     6,    -1,    94,     4,    -1,    95,     6,    -1,    -1,
      55,   307,   369,    -1,    -1,    33,   308,   369,    -1,    -1,
     108,   310,   311,    52,    -1,   312,    -1,   311,   312,    -1,
     119,     6,    -1,    94,     4,    -1,    72,     6,    -1,   187,
       6,    -1,    -1,    80,   313,   369,    -1,    -1,    82,   314,
     369,    -1,    -1,   136,   315,   369,    -1,    -1,   139,   316,
     369,    -1,    -1,   137,   317,   369,    -1,    -1,   140,   318,
     369,    -1,    -1,   106,   320,   321,    49,    -1,   322,    -1,
     321,   322,    -1,   119,     6,    -1,    94,     4,    -1,    -1,
      76,   323,   369,    -1,    -1,    32,   324,   369,    -1,    -1,
     110,   325,   369,    -1,    -1,   169,   327,   328,   170,    -1,
     329,    -1,   328,   329,    -1,   119,     6,    -1,    -1,    15,
     330,   369,    -1,    -1,   175,   331,   369,    -1,    -1,    18,
     332,   369,    -1,    -1,    17,   333,   369,    -1,    -1,   171,
     334,   369,    -1,    -1,   168,   335,   369,    -1,    -1,   172,
     336,   369,    -1,    -1,   173,   337,   369,    -1,    -1,   180,
     338,   369,    -1,    -1,    99,   340,   341,    45,    -1,   342,
      -1,   341,   342,    -1,   119,     6,    -1,    -1,   123,   343,
     368,    -1,    -1,   100,   345,   346,    46,    -1,   347,    -1,
     346,   347,    -1,   119,     6,    -1,    77,     6,    -1,    81,
       6,    -1,   144,   370,    -1,   151,   370,    -1,   188,   370,
      -1,   189,   370,    -1,   190,   370,    -1,   118,     6,    -1,
      56,     6,    -1,   126,     4,    -1,   126,   163,    -1,    92,
       5,    -1,    94,     4,    -1,    58,     4,    -1,    22,     4,
      -1,    -1,   117,   348,   369,    -1,    -1,    35,   349,   369,
      -1,     9,   370,    -1,     8,   370,    -1,   127,   370,    -1,
      59,   370,    -1,    -1,   116,   350,   368,    -1,    -1,   115,
     351,   368,    -1,    -1,   102,   353,   354,    47,    -1,   355,
      -1,   354,   355,    -1,   119,     6,    -1,   103,     6,    -1,
      65,     6,     6,     6,     6,   370,    -1,    -1,    86,   357,
     358,    44,    -1,   359,    -1,   358,   359,    -1,   119,     6,
      -1,    21,     6,     6,     6,     6,    -1,    56,     6,    -1,
     126,     4,    -1,   126,   163,    -1,    10,    -1,    10,   176,
      -1,    24,     6,    -1,    87,     6,    -1,   192,     6,    -1,
      83,   370,    -1,    57,   370,    -1,    -1,   116,   360,   368,
      -1,    -1,   115,   361,   368,    -1,   104,     6,    -1,   153,
       6,    -1,   166,     6,    -1,   169,     6,    -1,   174,     6,
      -1,    94,     4,    -1,    94,   177,    -1,   165,     4,    -1,
     178,   366,    -1,    -1,   185,   363,   364,   191,    -1,   365,
      -1,   364,   365,    -1,   119,     6,    -1,   104,     6,    -1,
      21,     6,     6,     6,     6,    -1,   186,     6,   370,    -1,
     367,    -1,   366,   367,    -1,   179,    -1,   182,    -1,   370,
      -1,   368,   370,    -1,   370,   370,    -1,   370,    -1,     4,
      -1,     3,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   125,   125,   126,   127,   130,   131,   132,   133,   134,
     135,   136,   137,   138,   139,   140,   141,   142,   143,   144,
     145,   146,   147,   148,   149,   154,   154,   157,   158,   161,
     162,   163,   164,   165,   166,   167,   168,   169,   171,   173,
     175,   181,   187,   198,   199,   200,   201,   202,   203,   204,
     205,   206,   207,   208,   209,   210,   211,   212,   217,   217,
     220,   221,   224,   225,   226,   227,   232,   232,   235,   236,
     239,   240,   241,   243,   249,   255,   266,   266,   269,   270,
     273,   274,   275,   280,   280,   283,   284,   287,   288,   289,
     291,   297,   303,   314,   314,   317,   318,   321,   322,   329,
     335,   344,   344,   347,   348,   351,   352,   353,   354,   356,
     356,   357,   357,   358,   358,   359,   359,   360,   360,   361,
     361,   362,   362,   363,   363,   364,   364,   365,   365,   366,
     366,   374,   374,   375,   375,   376,   376,   377,   377,   378,
     378,   381,   382,   383,   386,   387,   388,   391,   392,   393,
     396,   397,   398,   401,   402,   407,   408,   408,   409,   409,
     410,   410,   411,   411,   412,   412,   413,   413,   414,   417,
     417,   418,   418,   419,   422,   425,   425,   426,   426,   427,
     427,   428,   428,   429,   429,   432,   432,   433,   433,   434,
     434,   435,   435,   436,   437,   438,   439,   441,   442,   443,
     444,   446,   446,   447,   447,   448,   448,   449,   449,   452,
     452,   453,   453,   454,   454,   455,   455,   456,   456,   457,
     457,   458,   460,   462,   463,   464,   464,   465,   465,   466,
     466,   467,   467,   472,   472,   475,   476,   479,   480,   481,
     482,   483,   485,   486,   487,   487,   488,   488,   489,   489,
     490,   490,   491,   491,   492,   492,   493,   502,   512,   522,
     535,   535,   538,   539,   542,   543,   548,   548,   551,   552,
     555,   556,   557,   558,   558,   559,   559,   564,   564,   567,
     568,   571,   572,   573,   574,   575,   575,   576,   576,   577,
     577,   578,   578,   579,   579,   580,   580,   585,   585,   589,
     590,   593,   594,   595,   595,   596,   596,   597,   597,   602,
     602,   605,   606,   608,   609,   609,   610,   610,   611,   611,
     612,   612,   613,   613,   614,   614,   615,   615,   616,   616,
     617,   617,   622,   622,   625,   626,   629,   630,   630,   636,
     636,   639,   640,   643,   644,   645,   646,   647,   648,   649,
     650,   651,   652,   653,   654,   655,   656,   657,   658,   659,
     659,   660,   660,   661,   662,   663,   664,   665,   665,   667,
     667,   673,   673,   676,   677,   680,   681,   682,   692,   692,
     695,   696,   699,   700,   705,   706,   707,   708,   709,   711,
     714,   715,   716,   717,   718,   718,   721,   721,   724,   726,
     730,   734,   738,   742,   743,   744,   745,   748,   748,   751,
     752,   755,   756,   757,   763,   767,   768,   771,   772,   777,
     778,   781,   782,   785,   786
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || YYTOKEN_TABLE
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "REAL", "INTEGER", "LOGICAL", "NAME",
  "TK_ABSOLUTE_USE", "TK_AMP_END", "TK_AMP_START", "TK_ASCII", "TK_BRAIN",
  "TK_CA_EXP", "TK_CA_EXTERNAL", "TK_CA_HALF_MIN", "TK_CA_INTERNAL",
  "TK_CA_SCALE", "TK_CA_SPIKE_INC", "TK_CA_TAU", "TK_CA_TAU_SCALE",
  "TK_CELL", "TK_CELLS", "TK_CELLS_PER_FREQ", "TK_CELL_TYPE", "TK_CHANNEL",
  "TK_COLUMN", "TK_CSHELL", "TK_COLUMN_TYPE", "TK_CMP", "TK_G",
  "TK_CONNECT", "TK_DATA_LABEL", "TK_DELAY", "TK_DEPR_TAU", "TK_DURATION",
  "TK_DYN_RANGE", "TK_END_BRAIN", "TK_END_COLUMN", "TK_END_CSHELL",
  "TK_END_CMP", "TK_END_CELL", "TK_END_CHANNEL", "TK_END_LAYER",
  "TK_END_LSHELL", "TK_END_REPORT", "TK_END_SPIKE", "TK_END_STIMULUS",
  "TK_END_ST_INJECT", "TK_END_SYNAPSE", "TK_END_SYN_DATA", "TK_END_SYN_FD",
  "TK_END_SYN_PSG", "TK_END_SYN_LEARN", "TK_E_HALF_MIN_H",
  "TK_E_HALF_MIN_M", "TK_FACIL_TAU", "TK_FILENAME", "TK_FREQUENCY",
  "TK_FREQ_ROWS", "TK_FREQ_START", "TK_FSV", "TK_HEIGHT", "TK_H_INITIAL",
  "TK_H_POWER", "TK_IGNORE_EMPTY", "TK_INJECT", "TK_INTERACTIVE",
  "TK_LAYER", "TK_LSHELL", "TK_LAYER_TYPE", "TK_LEAK_G",
  "TK_LEAK_REVERSAL", "TK_LEARN", "TK_LEARN_LABEL", "TK_LOCATION",
  "TK_LOWER", "TK_MAX_G", "TK_MODE", "TK_M_INITIAL", "TK_M_POWER",
  "TK_NEG_HEB_WINDOW", "TK_PATTERN", "TK_POS_HEB_WINDOW", "TK_PROB",
  "TK_PSG_FILE", "TK_RELOAD_SYN", "TK_REPORT", "TK_REPORT_ON",
  "TK_REVERSAL", "TK_RSE", "TK_RSE_LABEL", "TK_R_MEMBRANE", "TK_SAMESEED",
  "TK_SAVE_SYN", "TK_SEED", "TK_SFD", "TK_SFD_LABEL", "TK_SLOPE_H",
  "TK_SLOPE_M", "TK_SPIKE", "TK_STIMULUS", "TK_SPIKE_HW", "TK_ST_INJECT",
  "TK_STIM_TYPE", "TK_SYNAPSE", "TK_STRENGTH", "TK_SYN_DATA", "TK_SYN_FD",
  "TK_SYN_LEARN", "TK_SYN_PSG", "TK_SYN_REVERSAL", "TK_TAU_MEMBRANE",
  "TK_TAU_SCALE_M", "TK_TAU_SCALE_H", "TK_THRESHOLD", "TK_TIME_END",
  "TK_TIME_START", "TK_TIME_FREQ_INC", "TK_TIMING", "TK_TYPE", "TK_UPPER",
  "TK_UNITARY_G", "TK_VMREST", "TK_VOLTAGES", "TK_VTAU_VAL_M",
  "TK_VTAU_VAL_H", "TK_PORT", "TK_WIDTH", "TK_JOB", "TK_DISTRIBUTE",
  "TK_VAL_M_STDEV", "TK_VOLT_M_STDEV", "TK_SLOPE_M_STDEV",
  "TK_VAL_H_STDEV", "TK_VOLT_H_STDEV", "TK_SLOPE_H_STDEV",
  "TK_NEG_HEB_PEAK_DELTA_USE", "TK_NEG_HEB_PEAK_TIME", "TK_VTAU_VOLT_M",
  "TK_POS_HEB_PEAK_DELTA_USE", "TK_POS_HEB_PEAK_TIME", "TK_VTAU_VOLT_H",
  "TK_INCLUDE", "TK_RSE_INIT", "TK_VERT_TRANS", "TK_PREV_SPIKE_RANGE",
  "TK_CONNECT_RPT", "TK_SPIKE_RPT", "TK_SERVER", "TK_SINGLE",
  "TK_CA_EXP_RANGE", "TK_PHASE_SHIFT", "TK_STRENGTH_RANGE",
  "TK_SYNAPSE_RSE", "TK_ALPHA_SCALE_H", "TK_ALPHA_SCALE_M",
  "TK_BETA_SCALE_H", "TK_BETA_SCALE_M", "TK_SAVE", "TK_LOAD",
  "TK_DISTANCE", "TK_OUTPUT_CONNECT_MAP", "TK_OUTPUT_CELLS", "TK_AUTO",
  "TK_SERVER_PORT", "TK_VERSION", "TK_SYNAPSE_UF", "TK_RECURRENT_CONNECT",
  "TK_ALPHA", "TK_SYN_AUGMENTATION", "TK_END_SYN_AUGMENTATION",
  "TK_MAX_AUGMENTATION", "TK_AUGMENTATION_INIT", "TK_AUGMENTATION_TAU",
  "TK_SYN_CALCIUM", "TK_CA_TAU_DECAY", "TK_EXP", "TK_SELECT_FRONT",
  "TK_OPTION", "TK_AVERAGE_SYN", "TK_AUGMENTATION_DELAY",
  "TK_WARNINGS_OFF", "TK_HIDE_TIMESTEP", "TK_HEBB_START", "TK_HEBB_END",
  "TK_EVENT", "TK_OVERRIDE", "TK_LEARN_SHAPE", "TK_RATE", "TK_TAU_NOISE",
  "TK_CORREL", "TK_END_EVENT", "TK_CELL_SEQUENCE", "TK_SFD_START",
  "TK_SFD_END", "TK_Km", "TK_Kahp", "TK_Ka", "TK_Na", "TK_Knicd",
  "$accept", "input", "element", "brain", "$@1", "brainvars", "brainvar",
  "colshell", "$@2", "cshvars", "cshvar", "column", "$@3", "colvars",
  "colvar", "lshell", "$@4", "lsvars", "lsvar", "layer", "$@5", "lvars",
  "lvar", "cell", "$@6", "cellvars", "cellvar", "compart", "$@7",
  "cmpvars", "cmpvar", "$@8", "$@9", "$@10", "$@11", "$@12", "$@13",
  "$@14", "$@15", "$@16", "$@17", "$@18", "channel", "$@19", "$@20",
  "$@21", "$@22", "$@23", "Kmvars", "Kahpvars", "Kavars", "Navars",
  "Knicdvars", "chvar", "$@24", "$@25", "$@26", "$@27", "$@28", "$@29",
  "Kmvar", "$@30", "$@31", "Kahpvar", "$@32", "$@33", "$@34", "$@35",
  "$@36", "Kavar", "$@37", "$@38", "$@39", "$@40", "$@41", "$@42", "$@43",
  "$@44", "Navar", "$@45", "$@46", "$@47", "$@48", "$@49", "$@50", "$@51",
  "$@52", "$@53", "$@54", "synapse", "$@55", "synvars", "synvar", "$@56",
  "$@57", "$@58", "$@59", "$@60", "$@61", "syn_psg", "$@62", "spvars",
  "spvar", "syn_fd", "$@63", "sfvars", "sfvar", "$@64", "$@65",
  "syn_learn", "$@66", "slvars", "slvar", "$@67", "$@68", "$@69", "$@70",
  "$@71", "$@72", "syn_data", "$@73", "sdvars", "sdvar", "$@74", "$@75",
  "$@76", "syn_augmentation", "$@77", "savars", "savar", "$@78", "$@79",
  "$@80", "$@81", "$@82", "$@83", "$@84", "$@85", "$@86", "spikeshape",
  "$@87", "spikevars", "spikevar", "$@88", "stimulus", "$@89", "stvars",
  "stvar", "$@90", "$@91", "$@92", "$@93", "stinject", "$@94", "stivars",
  "stivar", "report", "$@95", "reportvars", "reportvar", "$@96", "$@97",
  "event", "$@98", "eventvars", "eventvar", "reportoptions",
  "singleoption", "values", "twovalue", "value", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,   298,   299,   300,   301,   302,   303,   304,
     305,   306,   307,   308,   309,   310,   311,   312,   313,   314,
     315,   316,   317,   318,   319,   320,   321,   322,   323,   324,
     325,   326,   327,   328,   329,   330,   331,   332,   333,   334,
     335,   336,   337,   338,   339,   340,   341,   342,   343,   344,
     345,   346,   347,   348,   349,   350,   351,   352,   353,   354,
     355,   356,   357,   358,   359,   360,   361,   362,   363,   364,
     365,   366,   367,   368,   369,   370,   371,   372,   373,   374,
     375,   376,   377,   378,   379,   380,   381,   382,   383,   384,
     385,   386,   387,   388,   389,   390,   391,   392,   393,   394,
     395,   396,   397,   398,   399,   400,   401,   402,   403,   404,
     405,   406,   407,   408,   409,   410,   411,   412,   413,   414,
     415,   416,   417,   418,   419,   420,   421,   422,   423,   424,
     425,   426,   427,   428,   429,   430,   431,   432,   433,   434,
     435,   436,   437,   438,   439,   440,   441,   442,   443,   444,
     445,   446,   447,   448,   449,   450,   451,   452,   453,   454
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint16 yyr1[] =
{
       0,   200,   201,   201,   201,   202,   202,   202,   202,   202,
     202,   202,   202,   202,   202,   202,   202,   202,   202,   202,
     202,   202,   202,   202,   202,   204,   203,   205,   205,   206,
     206,   206,   206,   206,   206,   206,   206,   206,   206,   206,
     206,   206,   206,   206,   206,   206,   206,   206,   206,   206,
     206,   206,   206,   206,   206,   206,   206,   206,   208,   207,
     209,   209,   210,   210,   210,   210,   212,   211,   213,   213,
     214,   214,   214,   214,   214,   214,   216,   215,   217,   217,
     218,   218,   218,   220,   219,   221,   221,   222,   222,   222,
     222,   222,   222,   224,   223,   225,   225,   226,   226,   226,
     226,   228,   227,   229,   229,   230,   230,   230,   230,   231,
     230,   232,   230,   233,   230,   234,   230,   235,   230,   236,
     230,   237,   230,   238,   230,   239,   230,   240,   230,   241,
     230,   243,   242,   244,   242,   245,   242,   246,   242,   247,
     242,   248,   248,   248,   249,   249,   249,   250,   250,   250,
     251,   251,   251,   252,   252,   253,   254,   253,   255,   253,
     256,   253,   257,   253,   258,   253,   259,   253,   253,   261,
     260,   262,   260,   260,   260,   264,   263,   265,   263,   266,
     263,   267,   263,   268,   263,   270,   269,   271,   269,   272,
     269,   273,   269,   269,   269,   269,   269,   269,   269,   269,
     269,   274,   269,   275,   269,   276,   269,   277,   269,   279,
     278,   280,   278,   281,   278,   282,   278,   283,   278,   284,
     278,   278,   278,   278,   278,   285,   278,   286,   278,   287,
     278,   288,   278,   290,   289,   291,   291,   292,   292,   292,
     292,   292,   292,   292,   293,   292,   294,   292,   295,   292,
     296,   292,   297,   292,   298,   292,   292,   292,   292,   292,
     300,   299,   301,   301,   302,   302,   304,   303,   305,   305,
     306,   306,   306,   307,   306,   308,   306,   310,   309,   311,
     311,   312,   312,   312,   312,   313,   312,   314,   312,   315,
     312,   316,   312,   317,   312,   318,   312,   320,   319,   321,
     321,   322,   322,   323,   322,   324,   322,   325,   322,   327,
     326,   328,   328,   329,   330,   329,   331,   329,   332,   329,
     333,   329,   334,   329,   335,   329,   336,   329,   337,   329,
     338,   329,   340,   339,   341,   341,   342,   343,   342,   345,
     344,   346,   346,   347,   347,   347,   347,   347,   347,   347,
     347,   347,   347,   347,   347,   347,   347,   347,   347,   348,
     347,   349,   347,   347,   347,   347,   347,   350,   347,   351,
     347,   353,   352,   354,   354,   355,   355,   355,   357,   356,
     358,   358,   359,   359,   359,   359,   359,   359,   359,   359,
     359,   359,   359,   359,   360,   359,   361,   359,   359,   359,
     359,   359,   359,   359,   359,   359,   359,   363,   362,   364,
     364,   365,   365,   365,   365,   366,   366,   367,   367,   368,
     368,   369,   369,   370,   370
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     1,     2,     2,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     2,     0,     4,     1,     2,     2,
       2,     2,     1,     1,     2,     2,     2,     2,     2,     2,
      12,    13,    12,     2,     2,     2,     3,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     0,     4,
       1,     2,     2,     2,     2,     3,     0,     4,     1,     2,
       2,     2,     2,    10,    11,    10,     0,     4,     1,     2,
       2,     2,     2,     0,     4,     1,     2,     2,     2,     3,
       8,     9,     8,     0,     4,     1,     2,     2,     5,     6,
       6,     0,     4,     1,     2,     2,     2,     2,     2,     0,
       3,     0,     3,     0,     3,     0,     3,     0,     3,     0,
       3,     0,     3,     0,     3,     0,     3,     0,     3,     0,
       3,     0,     5,     0,     5,     0,     5,     0,     5,     0,
       5,     1,     2,     2,     1,     2,     2,     1,     2,     2,
       1,     2,     2,     1,     2,     2,     0,     3,     0,     3,
       0,     3,     0,     3,     0,     3,     0,     3,     2,     0,
       3,     0,     3,     4,     2,     0,     3,     0,     3,     0,
       3,     0,     3,     0,     3,     0,     3,     0,     3,     0,
       3,     0,     3,     2,     2,     2,     2,     2,     2,     2,
       2,     0,     3,     0,     3,     0,     3,     0,     3,     0,
       3,     0,     3,     0,     3,     0,     3,     0,     3,     0,
       3,     3,     3,     2,     2,     0,     3,     0,     3,     0,
       3,     0,     3,     0,     4,     1,     2,     2,     2,     2,
       2,     2,     2,     2,     0,     3,     0,     3,     0,     3,
       0,     3,     0,     3,     0,     3,     2,     2,     2,     2,
       0,     4,     1,     2,     2,     2,     0,     4,     1,     2,
       2,     2,     2,     0,     3,     0,     3,     0,     4,     1,
       2,     2,     2,     2,     2,     0,     3,     0,     3,     0,
       3,     0,     3,     0,     3,     0,     3,     0,     4,     1,
       2,     2,     2,     0,     3,     0,     3,     0,     3,     0,
       4,     1,     2,     2,     0,     3,     0,     3,     0,     3,
       0,     3,     0,     3,     0,     3,     0,     3,     0,     3,
       0,     3,     0,     4,     1,     2,     2,     0,     3,     0,
       4,     1,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     0,
       3,     0,     3,     2,     2,     2,     2,     0,     3,     0,
       3,     0,     4,     1,     2,     2,     2,     6,     0,     4,
       1,     2,     2,     5,     2,     2,     2,     1,     2,     2,
       2,     2,     2,     2,     0,     3,     0,     3,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     0,     4,     1,
       2,     2,     2,     5,     3,     1,     2,     1,     1,     1,
       2,     2,     1,     1,     1
};

/* YYDEFACT[STATE-NAME] -- Default rule to reduce with in state
   STATE-NUM when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint16 yydefact[] =
{
       0,    25,    93,     0,    66,    58,   101,    83,    76,   378,
     332,   339,   371,   233,   297,   266,   277,   260,     0,   309,
     407,     0,     2,     5,     7,     6,     9,     8,    10,    11,
      12,    13,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,     0,     0,   131,   133,   135,   137,   139,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    24,     0,     0,     1,     4,     3,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    32,    33,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    27,     0,     0,     0,
       0,    95,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    68,     0,     0,     0,     0,     0,    60,
     125,   123,   129,   127,     0,   119,   117,   113,     0,     0,
     109,   111,   115,     0,   121,     0,   103,     0,     0,     0,
       0,     0,     0,    85,     0,     0,     0,     0,    78,   387,
       0,     0,     0,     0,     0,     0,     0,     0,   396,   394,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     380,     0,   337,     0,   334,     0,     0,     0,   361,     0,
       0,     0,     0,     0,     0,     0,   369,   367,   359,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   341,
       0,     0,     0,     0,   373,   244,     0,   246,     0,   250,
       0,     0,     0,   248,     0,   252,   254,     0,     0,     0,
       0,     0,     0,   235,   305,   303,     0,   307,     0,     0,
     299,   275,   273,     0,     0,     0,     0,   268,     0,   285,
     287,     0,     0,   289,   293,   291,   295,     0,     0,   279,
       0,     0,     0,   262,   314,   320,   318,     0,   324,   322,
     326,   328,   316,   330,     0,   311,     0,     0,     0,     0,
       0,   409,    37,     0,   424,   423,    35,    34,    44,    43,
      39,    45,    36,    38,    29,    48,    50,    49,    30,    31,
      51,     0,    47,    53,    55,    54,    52,     0,    56,    57,
      26,    28,     0,     0,    97,    94,    96,   164,   156,   166,
       0,   160,     0,   158,   162,     0,   141,     0,   144,     0,
     147,     0,   150,     0,   153,    71,     0,    72,    70,     0,
      67,    69,    64,     0,    62,    63,    59,    61,     0,     0,
       0,     0,   108,     0,     0,     0,   106,   107,     0,     0,
       0,   105,     0,   102,   104,     0,     0,    88,    87,     0,
      84,    86,    81,    80,    82,    77,    79,   388,     0,   389,
     384,   393,   392,   390,   403,   404,   398,     0,     0,   382,
     385,   386,   399,   405,   400,   401,   402,   417,   418,   406,
     415,   391,   379,   381,   336,     0,   333,   335,   364,   363,
     358,     0,   352,   357,   366,   344,   345,   355,   356,     0,
       0,     0,   351,   343,   353,   354,   365,   346,   347,   348,
     349,   350,   340,   342,     0,   376,   375,   372,   374,     0,
     241,     0,   240,     0,   238,   239,   243,     0,   237,     0,
       0,   242,   256,   257,   258,   259,   234,   236,     0,     0,
     302,     0,   301,   298,   300,     0,     0,   271,   272,   270,
     267,   269,   283,     0,     0,   282,   281,     0,     0,     0,
       0,   284,   278,   280,   265,   264,   261,   263,     0,     0,
       0,   313,     0,     0,     0,     0,     0,     0,   310,   312,
       0,   412,   411,     0,   408,   410,     0,    46,     0,     0,
       0,     0,     0,     0,   168,     0,   155,     0,     0,   132,
     169,     0,   171,     0,   142,   143,   177,   181,   175,   183,
     134,   179,   145,   146,   136,   191,   189,   185,   187,     0,
       0,   201,   205,     0,     0,     0,     0,     0,     0,   203,
     207,   148,   149,   138,   211,   209,   213,   215,     0,     0,
     217,   219,     0,     0,   229,   225,   231,   227,   151,   152,
     140,   154,     0,     0,    65,   126,   422,   124,   130,   128,
     120,   118,   114,   110,   112,   116,   122,    89,     0,     0,
       0,   397,   419,   395,   416,   338,   362,   370,   368,   360,
       0,   245,   247,   251,   249,   253,   255,   306,   304,   308,
     276,   274,   286,   288,   290,   294,   292,   296,   315,   321,
     319,   325,   323,   327,   329,   317,   331,     0,   414,     0,
       0,     0,     0,   165,   157,   167,   161,   159,   163,     0,
       0,     0,   174,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   195,   193,     0,     0,   197,   198,   194,   199,
     200,   196,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   223,   224,     0,     0,     0,     0,     0,     0,
     421,     0,     0,     0,   420,     0,     0,     0,     0,    98,
       0,   170,     0,   172,   178,   182,   176,   184,   180,   192,
     190,   186,   188,   202,   206,   204,   208,   212,   210,   214,
     216,   222,   221,   218,   220,   230,   226,   232,   228,     0,
       0,     0,     0,   383,     0,   413,     0,     0,    99,   100,
     173,     0,     0,     0,     0,   377,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    90,    92,     0,     0,
       0,     0,    91,     0,     0,    73,    75,     0,     0,    74,
      40,    42,    41
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,    21,    22,    23,    42,    95,    96,    24,    50,   118,
     119,    25,    49,   112,   113,    26,    53,   147,   148,    27,
      52,   142,   143,    28,    43,   100,   101,    29,    51,   135,
     136,   348,   349,   345,   350,   344,   343,   352,   339,   338,
     341,   340,    30,   102,   103,   104,   105,   106,   315,   317,
     319,   321,   323,   316,   502,   507,   505,   508,   501,   503,
     515,   629,   631,   523,   635,   633,   637,   634,   636,   542,
     640,   641,   639,   638,   644,   652,   645,   653,   559,   655,
     654,   656,   657,   660,   661,   665,   667,   664,   666,    31,
      58,   222,   223,   429,   431,   437,   433,   439,   440,    32,
      62,   252,   253,    33,    60,   236,   237,   456,   455,    34,
      61,   248,   249,   463,   464,   467,   469,   468,   470,    35,
      59,   229,   230,   449,   448,   451,    36,    64,   264,   265,
     478,   486,   480,   479,   483,   482,   484,   485,   487,    37,
      55,   173,   174,   395,    38,    56,   198,   199,   411,   401,
     410,   409,    39,    57,   203,   204,    40,    54,   169,   170,
     378,   377,    41,    65,   270,   271,   389,   390,   581,   565,
     566
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -377
static const yytype_int16 yypact[] =
{
     817,  -377,  -377,    76,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,    14,  -377,
    -377,   425,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,   923,   -19,  -377,  -377,  -377,  -377,  -377,    38,
     239,  1179,    35,   -69,   705,   -91,   627,    11,   250,   238,
     -17,   216,   -59,  -377,   119,    -6,  -377,  -377,  -377,    20,
      25,    33,    33,    36,    40,    34,    41,    49,    50,    56,
       6,    57,    61,  -377,  -377,    63,    64,    66,    69,   101,
     103,    62,    75,   -61,   106,   846,  -377,   121,   123,   133,
      -1,  -377,   808,   808,   808,   808,   808,   134,   137,   139,
     145,   146,    22,  -377,    33,    33,   147,    33,    54,  -377,
    -377,  -377,  -377,  -377,   153,  -377,  -377,  -377,   160,   161,
    -377,  -377,  -377,   169,  -377,  1152,  -377,   181,   184,   185,
     189,   191,    19,  -377,    33,   192,    33,   -40,  -377,    27,
     193,   204,   205,    33,    33,   212,     0,   213,  -377,  -377,
     215,     8,   220,   200,   228,   230,   234,  -165,   237,   703,
    -377,   243,  -377,    12,  -377,    33,    33,   218,  -377,   245,
     240,    33,   249,   254,   260,   262,  -377,  -377,  -377,   272,
     273,     9,    33,    33,    33,    33,    33,    33,   622,  -377,
     274,   279,   280,   198,  -377,  -377,   283,  -377,   287,  -377,
     291,   296,   299,  -377,   302,  -377,  -377,   303,    33,    33,
      33,    33,    23,  -377,  -377,  -377,   293,  -377,   306,   235,
    -377,  -377,  -377,   311,   310,   312,   209,  -377,   313,  -377,
    -377,   318,   319,  -377,  -377,  -377,  -377,   321,   336,  -377,
     325,   327,    60,  -377,  -377,  -377,  -377,   328,  -377,  -377,
    -377,  -377,  -377,  -377,   -10,  -377,   331,   334,   335,   337,
      -3,  -377,  -377,   343,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,    33,  -377,  -377,  -377,  -377,  -377,   344,  -377,  -377,
    -377,  -377,   345,   356,  -377,  -377,  -377,  -377,  -377,  -377,
     338,  -377,   358,  -377,  -377,   464,  -377,   242,  -377,  1083,
    -377,  1000,  -377,   613,  -377,  -377,   359,  -377,  -377,   361,
    -377,  -377,  -377,    33,  -377,  -377,  -377,  -377,    33,    33,
      33,    33,  -377,    33,    33,    33,  -377,  -377,    33,    33,
      33,  -377,    33,  -377,  -377,   364,   365,  -377,  -377,   366,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,   367,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,    33,    33,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -165,
    -377,  -377,  -377,  -377,  -377,    33,  -377,  -377,  -377,  -377,
    -377,    33,  -377,  -377,  -377,  -377,  -377,  -377,  -377,    33,
      33,    33,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,   369,  -377,  -377,  -377,  -377,    33,
    -377,    33,  -377,    33,  -377,  -377,  -377,    33,  -377,    33,
      33,  -377,  -377,  -377,  -377,  -377,  -377,  -377,    33,    33,
    -377,    33,  -377,  -377,  -377,    33,    33,  -377,  -377,  -377,
    -377,  -377,  -377,    33,    33,  -377,  -377,    33,    33,    33,
      33,  -377,  -377,  -377,  -377,  -377,  -377,  -377,    33,    33,
      33,  -377,    33,    33,    33,    33,    33,    33,  -377,  -377,
     371,  -377,  -377,    33,  -377,  -377,   381,  -377,   383,    33,
      33,    33,    33,    33,  -377,    33,  -377,    33,    33,  -377,
    -377,    33,  -377,    33,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,    33,
      33,  -377,  -377,    33,    33,    33,    33,    33,    33,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,    33,    33,
    -377,  -377,    33,    33,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,   384,   385,  -377,  -377,    33,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,   390,   391,
     392,    33,  -377,    33,  -377,    33,  -377,    33,    33,  -377,
     398,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,   401,  -377,   407,
     408,    33,    33,  -377,  -377,  -377,  -377,  -377,  -377,    33,
      33,    33,  -377,    33,    33,    33,    33,    33,    33,    33,
      33,    33,  -377,  -377,    33,    33,  -377,  -377,  -377,  -377,
    -377,  -377,    33,    33,    33,    33,    33,    33,    33,    33,
      33,    33,  -377,  -377,    33,    33,    33,    33,   409,   411,
    -377,   414,   415,   417,  -377,   418,   421,   426,   429,    33,
      33,  -377,    33,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,    33,    33,    33,    33,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,   431,
     432,   433,   435,  -377,    33,  -377,   440,   441,  -377,  -377,
    -377,   442,   446,    33,    33,  -377,   448,   450,   451,   454,
      33,    33,   455,   462,    33,    33,    33,  -377,   463,   465,
      33,    33,  -377,    33,    33,    33,  -377,    33,    33,  -377,
      33,  -377,  -377
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -377,  -377,   349,  -377,  -377,  -377,   375,  -377,  -377,  -377,
     362,  -377,  -377,  -377,   372,  -377,  -377,  -377,   284,  -377,
    -377,  -377,   332,  -377,  -377,  -377,   379,  -377,  -377,  -377,
     348,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,   -82,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,   263,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,   236,  -377,  -377,  -377,   251,  -377,  -377,  -377,
    -377,  -377,   241,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,   257,  -377,  -377,  -377,  -377,  -377,  -377,   226,
    -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,  -377,
    -377,  -377,   323,  -377,  -377,  -377,  -377,   300,  -377,  -377,
    -377,  -377,  -377,  -377,  -377,   288,  -377,  -377,  -377,   330,
    -377,  -377,  -377,  -377,  -377,   224,  -377,   108,  -376,  -255,
     -71
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -1
static const yytype_uint16 yytable[] =
{
     276,   277,   583,   365,   374,   254,   144,   255,   256,    97,
     285,    98,   380,   414,   387,   266,   231,   388,   266,   585,
      63,   318,   320,   322,   324,   250,   272,    97,   171,    98,
     205,   273,   172,   587,   588,   144,   274,   275,   232,   305,
     280,   278,   137,   332,   333,   279,   335,   281,   107,   138,
     145,   146,   108,   282,   206,   207,   283,   396,   137,   330,
     251,   360,   284,   288,   107,   138,   296,   289,   108,   290,
     291,   446,   292,   362,   293,   364,   200,   233,   234,   145,
     146,   297,   371,   372,   567,   568,   569,   139,   570,   571,
     572,   109,   336,   573,   574,   575,   208,   576,   267,   209,
      99,   267,   235,   139,   398,   399,   294,   109,   295,   257,
     404,   476,   299,   268,   201,   114,   268,   210,    99,   211,
     298,   416,   417,   418,   419,   420,   421,   302,   115,   303,
     202,   171,   212,   213,   254,   172,   255,   256,   140,   304,
     325,   110,   214,   326,   250,   327,   586,   442,   443,   444,
     445,   328,   329,   334,   140,   286,   589,   110,   258,   342,
     488,   259,   260,   261,   346,   262,   215,   347,   216,   287,
     263,   381,   415,   116,   591,   351,   592,   375,   593,   251,
     269,   117,   594,   269,   595,   596,   141,   355,   494,   111,
     356,   357,   217,   597,   598,   358,   599,   359,   363,   368,
     600,   601,   141,   367,   383,   111,   218,   219,   602,   603,
     369,   370,   604,   605,   606,   607,   220,   221,   373,   376,
     497,   379,   400,   608,   609,   610,   382,   611,   612,   613,
     614,   615,   616,   514,   384,   522,   385,   541,   257,   558,
     386,   561,   231,   391,   403,   427,   623,   624,   625,   394,
     626,   402,   627,   628,   516,   405,   517,   205,   518,   460,
     406,   519,   564,   200,   232,   407,   408,   224,   693,   694,
     224,    44,    45,    46,    47,    48,   695,   696,   412,   413,
     424,   206,   207,   520,   453,   425,   426,   258,   238,   430,
     259,   260,   261,   432,   262,   434,   239,   450,   240,   263,
     114,   201,   435,   233,   234,   436,   582,   582,   438,   441,
     241,   225,   452,   115,   225,   457,   458,   202,   459,   462,
     307,   308,   465,   208,   582,   466,   209,   471,   235,   226,
     309,   474,   226,   475,   481,   242,   310,   490,   582,   582,
     491,   492,   504,   493,   210,   227,   211,   311,   227,   496,
     498,   499,   243,   244,   228,   245,   246,   228,   116,   212,
     213,   312,   500,   313,   506,   562,   117,   563,   577,   214,
      68,   578,   579,   580,   681,   590,   683,   617,   684,   685,
     686,   687,   688,   689,   690,   691,   692,   619,   472,   620,
     668,   669,   521,   215,   314,   216,   671,   672,   673,   697,
     698,   699,   700,   247,   675,   703,   704,   676,   238,   705,
     706,   707,   708,   677,   678,   709,   239,   710,   240,   217,
     711,   712,   618,   713,   714,    66,    67,   715,   621,   622,
     241,   366,   716,   218,   219,   717,     1,   721,   722,   723,
     630,   724,   632,   220,   221,     2,   726,   727,   728,     3,
       4,     5,   729,     6,   732,   242,   733,   734,   642,   643,
     735,   738,   646,   647,   648,   649,   650,   651,   739,   743,
     301,   744,   243,   244,   361,   245,   246,   658,   659,   306,
     337,   662,   663,   354,   331,   447,   454,   461,   477,   473,
     489,   428,     7,     8,   495,   670,   397,   584,   423,   393,
       0,     0,     0,     0,     0,   509,     0,     0,     0,     0,
     674,     9,   674,     0,   674,     0,   674,   674,   510,     0,
       0,     0,     0,   247,    10,    11,     0,    12,     0,    13,
       0,    14,    15,    16,    17,     0,     0,     0,     0,     0,
       0,     0,   307,   308,     0,     0,     0,     0,     0,     0,
     679,   680,   309,     0,     0,     0,     0,     0,   310,   682,
       0,     0,   511,     0,     0,     0,     0,    18,     0,   311,
       0,     0,     0,   582,   582,     0,   512,     0,     0,     0,
       0,   582,   582,   312,     0,   313,     0,   701,   702,     0,
       0,     0,     0,     0,    19,     0,   513,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   718,   719,
      20,   720,     0,     0,     0,     0,   314,     0,     0,     0,
       0,     0,   674,   674,   674,   674,     0,     0,     0,     0,
     175,   176,     0,     0,     0,   175,   176,     0,     0,     0,
       0,     0,     0,   725,   177,     0,     0,     0,     0,   177,
       0,     0,   730,   731,   560,     0,     0,   178,     0,   736,
     737,     0,   178,   740,   741,   742,     0,     0,   422,   745,
     746,     0,   747,   748,   749,     0,   750,   751,   179,   752,
     180,   181,     0,   179,     0,   180,   181,     0,     0,     0,
       0,   307,   308,     0,     0,     0,     0,     0,     0,   182,
       0,   309,     0,   183,   182,     0,     0,   310,   183,     0,
       0,     0,     0,   149,   184,   149,   185,     0,   311,   184,
       0,   185,     0,     0,   150,     0,   150,   151,     0,   151,
       0,     0,   312,     0,   313,     0,     0,   186,   187,   188,
     189,   190,   186,   187,   188,   189,   190,   392,   191,   192,
       0,     0,     0,   191,   192,     0,     0,     0,     0,   152,
     153,   152,   153,     0,     0,   314,   193,     0,     0,     0,
       0,   193,     0,   194,     0,     0,     0,     0,   194,     0,
       0,     0,     0,     0,     0,     0,   154,     0,   154,     0,
     155,     0,   155,     0,     0,     0,     0,   156,     0,   156,
       0,     0,     0,     0,     0,     0,     0,   157,     0,   157,
     195,   196,   197,     0,     0,   195,   196,   197,   158,   159,
     158,   159,   160,     0,   160,     0,     0,     0,     1,   161,
       0,   161,     0,     0,     0,     0,     0,     2,     0,     0,
       0,     3,     4,     5,     0,     6,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   162,     0,   162,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   163,   164,
     163,   164,   165,    69,   165,     0,    70,   166,     0,   166,
      71,   167,   300,   167,     7,     8,   307,   308,     0,     0,
       0,     0,     0,     0,     0,   168,   309,   168,     0,     0,
       0,     0,   310,     9,     0,     0,    72,     0,     0,     0,
      73,     0,    74,   311,     0,     0,    10,    11,     0,    12,
       0,    13,     0,    14,    15,    16,    17,   312,     0,   313,
       0,     0,    75,     0,     0,     0,     0,     0,     0,    76,
      77,     0,     0,     0,     0,     0,     0,     0,    78,     0,
      69,     0,     0,    70,     0,     0,     0,    71,     0,    18,
     314,     0,     0,     0,     0,    79,     0,     0,     0,     0,
       0,     0,    80,     0,    81,    82,     0,     0,     0,     0,
       0,     0,     0,    72,     0,     0,    19,    73,     0,    74,
       0,     0,    83,    84,    85,     0,     0,     0,     0,     0,
       0,     0,    20,     0,    86,    87,    88,    89,    90,    75,
      91,     0,     0,    92,     0,     0,    76,    77,     0,     0,
       0,     0,     0,     0,    93,    78,     0,     0,     0,     0,
       0,    94,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   543,    79,     0,     0,     0,     0,     0,     0,    80,
       0,    81,    82,   544,   545,     0,     0,     0,     0,     0,
       0,     0,   546,   547,     0,     0,     0,     0,     0,    83,
      84,    85,     0,     0,     0,     0,     0,     0,   307,   308,
       0,    86,    87,    88,    89,    90,     0,    91,   309,     0,
      92,     0,     0,     0,   310,     0,     0,   548,   549,     0,
       0,    93,     0,     0,     0,   311,     0,     0,    94,     0,
       0,     0,   550,   551,     0,     0,     0,     0,     0,   312,
       0,   313,     0,     0,   524,     0,     0,     0,     0,     0,
       0,     0,   552,     0,     0,   553,   525,   526,     0,     0,
       0,     0,     0,     0,     0,   527,   528,     0,     0,     0,
       0,     0,   314,     0,   554,   555,   556,   557,     0,     0,
       0,   307,   308,     0,     0,   120,     0,   121,     0,   122,
     123,   309,     0,     0,     0,     0,   124,   310,     0,     0,
     529,   530,     0,     0,     0,     0,     0,     0,   311,     0,
       0,   353,   120,     0,   121,     0,   122,   123,     0,     0,
       0,     0,   312,   124,   313,     0,     0,   531,   532,     0,
       0,     0,     0,   533,   534,   535,   536,   537,   538,     0,
       0,   539,   125,   126,   540,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   314,     0,     0,     0,     0,
       0,     0,     0,   127,     0,     0,   128,     0,     0,   125,
     126,   129,     0,   130,     0,     0,     0,     0,     0,     0,
       0,     0,     0,   131,     0,     0,   132,     0,     0,     0,
     127,   133,     0,   128,   134,     0,     0,     0,   129,     0,
     130,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     131,     0,     0,   132,     0,     0,     0,     0,   133,     0,
       0,   134
};

static const yytype_int16 yycheck[] =
{
      71,    72,   378,    43,     4,    15,    75,    17,    18,    28,
       4,    30,     4,     4,   179,    21,    33,   182,    21,   395,
       6,   103,   104,   105,   106,    84,     6,    28,   119,    30,
       7,     6,   123,   409,   410,    75,     3,     4,    55,    40,
       6,     5,    23,   114,   115,     5,   117,     6,    26,    30,
     119,   120,    30,     4,    31,    32,     6,    45,    23,    37,
     119,    42,     6,     6,    26,    30,     4,     6,    30,     6,
       6,    48,     6,   144,     5,   146,    65,    94,    95,   119,
     120,     6,   153,   154,   339,   340,   341,    68,   343,   344,
     345,    69,    38,   348,   349,   350,    73,   352,   104,    76,
     119,   104,   119,    68,   175,   176,     5,    69,     5,   119,
     181,    51,     6,   119,   103,    61,   119,    94,   119,    96,
     181,   192,   193,   194,   195,   196,   197,     6,    74,     6,
     119,   119,   109,   110,    15,   123,    17,    18,   119,     6,
       6,   119,   119,     6,    84,     6,   401,   218,   219,   220,
     221,     6,     6,     6,   119,   149,   411,   119,   168,     6,
     170,   171,   172,   173,     4,   175,   143,     6,   145,   163,
     180,   163,   163,   119,   429,     6,   431,   177,   433,   119,
     186,   127,   437,   186,   439,   440,   167,     6,   191,   167,
       6,     6,   169,   448,   449,     6,   451,     6,     6,     6,
     455,   456,   167,   176,     4,   167,   183,   184,   463,   464,
       6,     6,   467,   468,   469,   470,   193,   194,     6,     6,
     291,     6,     4,   478,   479,   480,     6,   482,   483,   484,
     485,   486,   487,   315,     6,   317,     6,   319,   119,   321,
       6,   323,    33,     6,     4,    47,   501,   502,   503,     6,
     505,     6,   507,   508,    12,     6,    14,     7,    16,    50,
       6,    19,   333,    65,    55,     5,     4,    32,   644,   645,
      32,   195,   196,   197,   198,   199,   652,   653,     6,     6,
       6,    31,    32,    41,    49,     6,     6,   168,    72,     6,
     171,   172,   173,     6,   175,     4,    80,     4,    82,   180,
      61,   103,     6,    94,    95,     6,   377,   378,     6,     6,
      94,    76,     6,    74,    76,     4,     6,   119,     6,     6,
      78,    79,     4,    73,   395,     6,    76,     6,   119,    94,
      88,     6,    94,     6,     6,   119,    94,     6,   409,   410,
       6,     6,     4,     6,    94,   110,    96,   105,   110,     6,
       6,     6,   136,   137,   119,   139,   140,   119,   119,   109,
     110,   119,     6,   121,     6,     6,   127,     6,     4,   119,
      21,     6,     6,     6,   629,     6,   631,     6,   633,   634,
     635,   636,   637,   638,   639,   640,   641,     6,    52,     6,
       6,     6,   150,   143,   152,   145,     6,     6,     6,   654,
     655,   656,   657,   187,     6,   660,   661,     6,    72,   664,
     665,   666,   667,     6,     6,     6,    80,     6,    82,   169,
       6,     6,   493,     6,     6,     0,     1,     6,   499,   500,
      94,   147,     6,   183,   184,     6,    11,     6,     6,     6,
     511,     6,   513,   193,   194,    20,     6,     6,     6,    24,
      25,    26,     6,    28,     6,   119,     6,     6,   529,   530,
       6,     6,   533,   534,   535,   536,   537,   538,     6,     6,
      95,     6,   136,   137,   142,   139,   140,   548,   549,   100,
     118,   552,   553,   135,   112,   222,   229,   236,   252,   248,
     264,   203,    67,    68,   270,   566,   173,   389,   198,   169,
      -1,    -1,    -1,    -1,    -1,    41,    -1,    -1,    -1,    -1,
     581,    86,   583,    -1,   585,    -1,   587,   588,    54,    -1,
      -1,    -1,    -1,   187,    99,   100,    -1,   102,    -1,   104,
      -1,   106,   107,   108,   109,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    78,    79,    -1,    -1,    -1,    -1,    -1,    -1,
     621,   622,    88,    -1,    -1,    -1,    -1,    -1,    94,   630,
      -1,    -1,    98,    -1,    -1,    -1,    -1,   142,    -1,   105,
      -1,    -1,    -1,   644,   645,    -1,   112,    -1,    -1,    -1,
      -1,   652,   653,   119,    -1,   121,    -1,   658,   659,    -1,
      -1,    -1,    -1,    -1,   169,    -1,   132,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   679,   680,
     185,   682,    -1,    -1,    -1,    -1,   152,    -1,    -1,    -1,
      -1,    -1,   693,   694,   695,   696,    -1,    -1,    -1,    -1,
       8,     9,    -1,    -1,    -1,     8,     9,    -1,    -1,    -1,
      -1,    -1,    -1,   714,    22,    -1,    -1,    -1,    -1,    22,
      -1,    -1,   723,   724,    41,    -1,    -1,    35,    -1,   730,
     731,    -1,    35,   734,   735,   736,    -1,    -1,    46,   740,
     741,    -1,   743,   744,   745,    -1,   747,   748,    56,   750,
      58,    59,    -1,    56,    -1,    58,    59,    -1,    -1,    -1,
      -1,    78,    79,    -1,    -1,    -1,    -1,    -1,    -1,    77,
      -1,    88,    -1,    81,    77,    -1,    -1,    94,    81,    -1,
      -1,    -1,    -1,    10,    92,    10,    94,    -1,   105,    92,
      -1,    94,    -1,    -1,    21,    -1,    21,    24,    -1,    24,
      -1,    -1,   119,    -1,   121,    -1,    -1,   115,   116,   117,
     118,   119,   115,   116,   117,   118,   119,    44,   126,   127,
      -1,    -1,    -1,   126,   127,    -1,    -1,    -1,    -1,    56,
      57,    56,    57,    -1,    -1,   152,   144,    -1,    -1,    -1,
      -1,   144,    -1,   151,    -1,    -1,    -1,    -1,   151,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    83,    -1,    83,    -1,
      87,    -1,    87,    -1,    -1,    -1,    -1,    94,    -1,    94,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   104,    -1,   104,
     188,   189,   190,    -1,    -1,   188,   189,   190,   115,   116,
     115,   116,   119,    -1,   119,    -1,    -1,    -1,    11,   126,
      -1,   126,    -1,    -1,    -1,    -1,    -1,    20,    -1,    -1,
      -1,    24,    25,    26,    -1,    28,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   153,    -1,   153,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   165,   166,
     165,   166,   169,    27,   169,    -1,    30,   174,    -1,   174,
      34,   178,    36,   178,    67,    68,    78,    79,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   192,    88,   192,    -1,    -1,
      -1,    -1,    94,    86,    -1,    -1,    60,    -1,    -1,    -1,
      64,    -1,    66,   105,    -1,    -1,    99,   100,    -1,   102,
      -1,   104,    -1,   106,   107,   108,   109,   119,    -1,   121,
      -1,    -1,    86,    -1,    -1,    -1,    -1,    -1,    -1,    93,
      94,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   102,    -1,
      27,    -1,    -1,    30,    -1,    -1,    -1,    34,    -1,   142,
     152,    -1,    -1,    -1,    -1,   119,    -1,    -1,    -1,    -1,
      -1,    -1,   126,    -1,   128,   129,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    60,    -1,    -1,   169,    64,    -1,    66,
      -1,    -1,   146,   147,   148,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,   185,    -1,   158,   159,   160,   161,   162,    86,
     164,    -1,    -1,   167,    -1,    -1,    93,    94,    -1,    -1,
      -1,    -1,    -1,    -1,   178,   102,    -1,    -1,    -1,    -1,
      -1,   185,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    41,   119,    -1,    -1,    -1,    -1,    -1,    -1,   126,
      -1,   128,   129,    53,    54,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    62,    63,    -1,    -1,    -1,    -1,    -1,   146,
     147,   148,    -1,    -1,    -1,    -1,    -1,    -1,    78,    79,
      -1,   158,   159,   160,   161,   162,    -1,   164,    88,    -1,
     167,    -1,    -1,    -1,    94,    -1,    -1,    97,    98,    -1,
      -1,   178,    -1,    -1,    -1,   105,    -1,    -1,   185,    -1,
      -1,    -1,   112,   113,    -1,    -1,    -1,    -1,    -1,   119,
      -1,   121,    -1,    -1,    41,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,   132,    -1,    -1,   135,    53,    54,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    62,    63,    -1,    -1,    -1,
      -1,    -1,   152,    -1,   154,   155,   156,   157,    -1,    -1,
      -1,    78,    79,    -1,    -1,    13,    -1,    15,    -1,    17,
      18,    88,    -1,    -1,    -1,    -1,    24,    94,    -1,    -1,
      97,    98,    -1,    -1,    -1,    -1,    -1,    -1,   105,    -1,
      -1,    39,    13,    -1,    15,    -1,    17,    18,    -1,    -1,
      -1,    -1,   119,    24,   121,    -1,    -1,   124,   125,    -1,
      -1,    -1,    -1,   130,   131,   132,   133,   134,   135,    -1,
      -1,   138,    70,    71,   141,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   152,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    91,    -1,    -1,    94,    -1,    -1,    70,
      71,    99,    -1,   101,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   111,    -1,    -1,   114,    -1,    -1,    -1,
      91,   119,    -1,    94,   122,    -1,    -1,    -1,    99,    -1,
     101,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
     111,    -1,    -1,   114,    -1,    -1,    -1,    -1,   119,    -1,
      -1,   122
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint16 yystos[] =
{
       0,    11,    20,    24,    25,    26,    28,    67,    68,    86,
      99,   100,   102,   104,   106,   107,   108,   109,   142,   169,
     185,   201,   202,   203,   207,   211,   215,   219,   223,   227,
     242,   289,   299,   303,   309,   319,   326,   339,   344,   352,
     356,   362,   204,   224,   195,   196,   197,   198,   199,   212,
     208,   228,   220,   216,   357,   340,   345,   353,   290,   320,
     304,   310,   300,     6,   327,   363,     0,     1,   202,    27,
      30,    34,    60,    64,    66,    86,    93,    94,   102,   119,
     126,   128,   129,   146,   147,   148,   158,   159,   160,   161,
     162,   164,   167,   178,   185,   205,   206,    28,    30,   119,
     225,   226,   243,   244,   245,   246,   247,    26,    30,    69,
     119,   167,   213,   214,    61,    74,   119,   127,   209,   210,
      13,    15,    17,    18,    24,    70,    71,    91,    94,    99,
     101,   111,   114,   119,   122,   229,   230,    23,    30,    68,
     119,   167,   221,   222,    75,   119,   120,   217,   218,    10,
      21,    24,    56,    57,    83,    87,    94,   104,   115,   116,
     119,   126,   153,   165,   166,   169,   174,   178,   192,   358,
     359,   119,   123,   341,   342,     8,     9,    22,    35,    56,
      58,    59,    77,    81,    92,    94,   115,   116,   117,   118,
     119,   126,   127,   144,   151,   188,   189,   190,   346,   347,
      65,   103,   119,   354,   355,     7,    31,    32,    73,    76,
      94,    96,   109,   110,   119,   143,   145,   169,   183,   184,
     193,   194,   291,   292,    32,    76,    94,   110,   119,   321,
     322,    33,    55,    94,    95,   119,   305,   306,    72,    80,
      82,    94,   119,   136,   137,   139,   140,   187,   311,   312,
      84,   119,   301,   302,    15,    17,    18,   119,   168,   171,
     172,   173,   175,   180,   328,   329,    21,   104,   119,   186,
     364,   365,     6,     6,     3,     4,   370,   370,     5,     5,
       6,     6,     4,     6,     6,     4,   149,   163,     6,     6,
       6,     6,     6,     5,     5,     5,     4,     6,   181,     6,
      36,   206,     6,     6,     6,    40,   226,    78,    79,    88,
      94,   105,   119,   121,   152,   248,   253,   249,   253,   250,
     253,   251,   253,   252,   253,     6,     6,     6,     6,     6,
      37,   214,   370,   370,     6,   370,    38,   210,   239,   238,
     241,   240,     6,   236,   235,   233,     4,     6,   231,   232,
     234,     6,   237,    39,   230,     6,     6,     6,     6,     6,
      42,   222,   370,     6,   370,    43,   218,   176,     6,     6,
       6,   370,   370,     6,     4,   177,     6,   361,   360,     6,
       4,   163,     6,     4,     6,     6,     6,   179,   182,   366,
     367,     6,    44,   359,     6,   343,    45,   342,   370,   370,
       4,   349,     6,     4,   370,     6,     6,     5,     4,   351,
     350,   348,     6,     6,     4,   163,   370,   370,   370,   370,
     370,   370,    46,   347,     6,     6,     6,    47,   355,   293,
       6,   294,     6,   296,     4,     6,     6,   295,     6,   297,
     298,     6,   370,   370,   370,   370,    48,   292,   324,   323,
       4,   325,     6,    49,   322,   308,   307,     4,     6,     6,
      50,   306,     6,   313,   314,     4,     6,   315,   317,   316,
     318,     6,    52,   312,     6,     6,    51,   302,   330,   333,
     332,     6,   335,   334,   336,   337,   331,   338,   170,   329,
       6,     6,     6,     6,   191,   365,     6,   370,     6,     6,
       6,   258,   254,   259,     4,   256,     6,   255,   257,    41,
      54,    98,   112,   132,   253,   260,    12,    14,    16,    19,
      41,   150,   253,   263,    41,    53,    54,    62,    63,    97,
      98,   124,   125,   130,   131,   132,   133,   134,   135,   138,
     141,   253,   269,    41,    53,    54,    62,    63,    97,    98,
     112,   113,   132,   135,   154,   155,   156,   157,   253,   278,
      41,   253,     6,     6,   370,   369,   370,   369,   369,   369,
     369,   369,   369,   369,   369,   369,   369,     4,     6,     6,
       6,   368,   370,   368,   367,   368,   369,   368,   368,   369,
       6,   369,   369,   369,   369,   369,   369,   369,   369,   369,
     369,   369,   369,   369,   369,   369,   369,   369,   369,   369,
     369,   369,   369,   369,   369,   369,   369,     6,   370,     6,
       6,   370,   370,   369,   369,   369,   369,   369,   369,   261,
     370,   262,   370,   265,   267,   264,   268,   266,   273,   272,
     270,   271,   370,   370,   274,   276,   370,   370,   370,   370,
     370,   370,   275,   277,   280,   279,   281,   282,   370,   370,
     283,   284,   370,   370,   287,   285,   288,   286,     6,     6,
     370,     6,     6,     6,   370,     6,     6,     6,     6,   370,
     370,   369,   370,   369,   369,   369,   369,   369,   369,   369,
     369,   369,   369,   368,   368,   368,   368,   369,   369,   369,
     369,   370,   370,   369,   369,   369,   369,   369,   369,     6,
       6,     6,     6,     6,     6,     6,     6,     6,   370,   370,
     370,     6,     6,     6,     6,   370,     6,     6,     6,     6,
     370,   370,     6,     6,     6,     6,   370,   370,     6,     6,
     370,   370,   370,     6,     6,   370,   370,   370,   370,   370,
     370,   370,   370
};

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrorlab


/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  */

#define YYFAIL		goto yyerrlab

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)					\
do								\
  if (yychar == YYEMPTY && yylen == 1)				\
    {								\
      yychar = (Token);						\
      yylval = (Value);						\
      yytoken = YYTRANSLATE (yychar);				\
      YYPOPSTACK (1);						\
      goto yybackup;						\
    }								\
  else								\
    {								\
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;							\
    }								\
while (YYID (0))


#define YYTERROR	1
#define YYERRCODE	256


/* YYLLOC_DEFAULT -- Set CURRENT to span from RHS[1] to RHS[N].
   If N is 0, then set CURRENT to the empty location which ends
   the previous symbol: RHS[0] (always defined).  */

#define YYRHSLOC(Rhs, K) ((Rhs)[K])
#ifndef YYLLOC_DEFAULT
# define YYLLOC_DEFAULT(Current, Rhs, N)				\
    do									\
      if (YYID (N))                                                    \
	{								\
	  (Current).first_line   = YYRHSLOC (Rhs, 1).first_line;	\
	  (Current).first_column = YYRHSLOC (Rhs, 1).first_column;	\
	  (Current).last_line    = YYRHSLOC (Rhs, N).last_line;		\
	  (Current).last_column  = YYRHSLOC (Rhs, N).last_column;	\
	}								\
      else								\
	{								\
	  (Current).first_line   = (Current).last_line   =		\
	    YYRHSLOC (Rhs, 0).last_line;				\
	  (Current).first_column = (Current).last_column =		\
	    YYRHSLOC (Rhs, 0).last_column;				\
	}								\
    while (YYID (0))
#endif


/* YY_LOCATION_PRINT -- Print the location on the stream.
   This macro was not mandated originally: define only if we know
   we won't break user code: when these are the locations we know.  */

#ifndef YY_LOCATION_PRINT
# if YYLTYPE_IS_TRIVIAL
#  define YY_LOCATION_PRINT(File, Loc)			\
     fprintf (File, "%d.%d-%d.%d",			\
	      (Loc).first_line, (Loc).first_column,	\
	      (Loc).last_line,  (Loc).last_column)
# else
#  define YY_LOCATION_PRINT(File, Loc) ((void) 0)
# endif
#endif


/* YYLEX -- calling `yylex' with the right arguments.  */

#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (YYID (0))

# define YY_SYMBOL_PRINT(Title, Type, Value, Location)			  \
do {									  \
  if (yydebug)								  \
    {									  \
      YYFPRINTF (stderr, "%s ", Title);					  \
      yy_symbol_print (stderr,						  \
		  Type, Value); \
      YYFPRINTF (stderr, "\n");						  \
    }									  \
} while (YYID (0))


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_value_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# else
  YYUSE (yyoutput);
# endif
  switch (yytype)
    {
      default:
	break;
    }
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (yytype < YYNTOKENS)
    YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
#else
static void
yy_stack_print (yybottom, yytop)
    yytype_int16 *yybottom;
    yytype_int16 *yytop;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (YYID (0))


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_reduce_print (YYSTYPE *yyvsp, int yyrule)
#else
static void
yy_reduce_print (yyvsp, yyrule)
    YYSTYPE *yyvsp;
    int yyrule;
#endif
{
  int yynrhs = yyr2[yyrule];
  int yyi;
  unsigned long int yylno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
	     yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr, yyrhs[yyprhs[yyrule] + yyi],
		       &(yyvsp[(yyi + 1) - (yynrhs)])
		       		       );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (yyvsp, Rule); \
} while (YYID (0))

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif



#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static YYSIZE_T
yystrlen (const char *yystr)
#else
static YYSIZE_T
yystrlen (yystr)
    const char *yystr;
#endif
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static char *
yystpcpy (char *yydest, const char *yysrc)
#else
static char *
yystpcpy (yydest, yysrc)
    char *yydest;
    const char *yysrc;
#endif
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
	switch (*++yyp)
	  {
	  case '\'':
	  case ',':
	    goto do_not_strip_quotes;

	  case '\\':
	    if (*++yyp != '\\')
	      goto do_not_strip_quotes;
	    /* Fall through.  */
	  default:
	    if (yyres)
	      yyres[yyn] = *yyp;
	    yyn++;
	    break;

	  case '"':
	    if (yyres)
	      yyres[yyn] = '\0';
	    return yyn;
	  }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into YYRESULT an error message about the unexpected token
   YYCHAR while in state YYSTATE.  Return the number of bytes copied,
   including the terminating null byte.  If YYRESULT is null, do not
   copy anything; just return the number of bytes that would be
   copied.  As a special case, return 0 if an ordinary "syntax error"
   message will do.  Return YYSIZE_MAXIMUM if overflow occurs during
   size calculation.  */
static YYSIZE_T
yysyntax_error (char *yyresult, int yystate, int yychar)
{
  int yyn = yypact[yystate];

  if (! (YYPACT_NINF < yyn && yyn <= YYLAST))
    return 0;
  else
    {
      int yytype = YYTRANSLATE (yychar);
      YYSIZE_T yysize0 = yytnamerr (0, yytname[yytype]);
      YYSIZE_T yysize = yysize0;
      YYSIZE_T yysize1;
      int yysize_overflow = 0;
      enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
      char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
      int yyx;

# if 0
      /* This is so xgettext sees the translatable formats that are
	 constructed on the fly.  */
      YY_("syntax error, unexpected %s");
      YY_("syntax error, unexpected %s, expecting %s");
      YY_("syntax error, unexpected %s, expecting %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s");
# endif
      char *yyfmt;
      char const *yyf;
      static char const yyunexpected[] = "syntax error, unexpected %s";
      static char const yyexpecting[] = ", expecting %s";
      static char const yyor[] = " or %s";
      char yyformat[sizeof yyunexpected
		    + sizeof yyexpecting - 1
		    + ((YYERROR_VERBOSE_ARGS_MAXIMUM - 2)
		       * (sizeof yyor - 1))];
      char const *yyprefix = yyexpecting;

      /* Start YYX at -YYN if negative to avoid negative indexes in
	 YYCHECK.  */
      int yyxbegin = yyn < 0 ? -yyn : 0;

      /* Stay within bounds of both yycheck and yytname.  */
      int yychecklim = YYLAST - yyn + 1;
      int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
      int yycount = 1;

      yyarg[0] = yytname[yytype];
      yyfmt = yystpcpy (yyformat, yyunexpected);

      for (yyx = yyxbegin; yyx < yyxend; ++yyx)
	if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR)
	  {
	    if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
	      {
		yycount = 1;
		yysize = yysize0;
		yyformat[sizeof yyunexpected - 1] = '\0';
		break;
	      }
	    yyarg[yycount++] = yytname[yyx];
	    yysize1 = yysize + yytnamerr (0, yytname[yyx]);
	    yysize_overflow |= (yysize1 < yysize);
	    yysize = yysize1;
	    yyfmt = yystpcpy (yyfmt, yyprefix);
	    yyprefix = yyor;
	  }

      yyf = YY_(yyformat);
      yysize1 = yysize + yystrlen (yyf);
      yysize_overflow |= (yysize1 < yysize);
      yysize = yysize1;

      if (yysize_overflow)
	return YYSIZE_MAXIMUM;

      if (yyresult)
	{
	  /* Avoid sprintf, as that infringes on the user's name space.
	     Don't have undefined behavior even if the translation
	     produced a string with the wrong number of "%s"s.  */
	  char *yyp = yyresult;
	  int yyi = 0;
	  while ((*yyp = *yyf) != '\0')
	    {
	      if (*yyp == '%' && yyf[1] == 's' && yyi < yycount)
		{
		  yyp += yytnamerr (yyp, yyarg[yyi++]);
		  yyf += 2;
		}
	      else
		{
		  yyp++;
		  yyf++;
		}
	    }
	}
      return yysize;
    }
}
#endif /* YYERROR_VERBOSE */


/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
#else
static void
yydestruct (yymsg, yytype, yyvaluep)
    const char *yymsg;
    int yytype;
    YYSTYPE *yyvaluep;
#endif
{
  YYUSE (yyvaluep);

  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  switch (yytype)
    {

      default:
	break;
    }
}

/* Prevent warnings from -Wmissing-prototypes.  */
#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */


/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;

/* Number of syntax errors so far.  */
int yynerrs;



/*-------------------------.
| yyparse or yypush_parse.  |
`-------------------------*/

#ifdef YYPARSE_PARAM
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void *YYPARSE_PARAM)
#else
int
yyparse (YYPARSE_PARAM)
    void *YYPARSE_PARAM;
#endif
#else /* ! YYPARSE_PARAM */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{


    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       `yyss': related to states.
       `yyvs': related to semantic values.

       Refer to the stacks thru separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYSIZE_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yytoken = 0;
  yyss = yyssa;
  yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */

  /* Initialize stack pointers.
     Waste one element of value and location stack
     so that they stay on the same level as the state stack.
     The wasted elements are never initialized.  */
  yyssp = yyss;
  yyvsp = yyvs;

  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack.  Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	yytype_int16 *yyss1 = yyss;

	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow (YY_("memory exhausted"),
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),
		    &yystacksize);

	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	yytype_int16 *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyexhaustedlab;
	YYSTACK_RELOCATE (yyss_alloc, yyss);
	YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yyn == YYPACT_NINF)
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yyn == 0 || yyn == YYTABLE_NINF)
	goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token.  */
  yychar = YYEMPTY;

  yystate = yyn;
  *++yyvsp = yylval;

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 24:

/* Line 1455 of yacc.c  */
#line 149 "parse.y"
    { multiInput( (yyvsp[(2) - (2)].sval) ); ;}
    break;

  case 25:

/* Line 1455 of yacc.c  */
#line 154 "parse.y"
    { brain = makebrain (); ;}
    break;

  case 29:

/* Line 1455 of yacc.c  */
#line 161 "parse.y"
    { brain->L.name      = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 30:

/* Line 1455 of yacc.c  */
#line 162 "parse.y"
    { brain->job         = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 31:

/* Line 1455 of yacc.c  */
#line 163 "parse.y"
    { brain->distribute  = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 32:

/* Line 1455 of yacc.c  */
#line 164 "parse.y"
    { brain->ConnectRpt  = TRUE; ;}
    break;

  case 33:

/* Line 1455 of yacc.c  */
#line 165 "parse.y"
    { brain->SpikeRpt    = TRUE; ;}
    break;

  case 34:

/* Line 1455 of yacc.c  */
#line 166 "parse.y"
    { brain->FSV         = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 35:

/* Line 1455 of yacc.c  */
#line 167 "parse.y"
    { brain->Duration    = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 36:

/* Line 1455 of yacc.c  */
#line 168 "parse.y"
    { brain->Seed        = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 37:

/* Line 1455 of yacc.c  */
#line 169 "parse.y"
    { brain->ColumnNames = AddName (brain->ColumnNames, (yyvsp[(2) - (2)].sval), 0);
                                     brain->nColumns++; ;}
    break;

  case 38:

/* Line 1455 of yacc.c  */
#line 171 "parse.y"
    { brain->StInjNames  = AddName (brain->StInjNames,  (yyvsp[(2) - (2)].sval), 0);
                                     brain->nStInject++; ;}
    break;

  case 39:

/* Line 1455 of yacc.c  */
#line 173 "parse.y"
    { brain->ReportNames = AddName (brain->ReportNames, (yyvsp[(2) - (2)].sval), 0);
                                     brain->nReports++; ;}
    break;

  case 40:

/* Line 1455 of yacc.c  */
#line 176 "parse.y"
    { brain->CnList = makeConnect (brain->CnList, &(brain->nConnect),
                                               (yyvsp[(2) - (12)].sval), (yyvsp[(3) - (12)].sval), (yyvsp[(4) - (12)].sval), (yyvsp[(5) - (12)].sval),
                                               (yyvsp[(6) - (12)].sval), (yyvsp[(7) - (12)].sval), (yyvsp[(8) - (12)].sval), (yyvsp[(9) - (12)].sval),
                                               (yyvsp[(10) - (12)].sval), (yyvsp[(11) - (12)].rval), (yyvsp[(12) - (12)].rval));
               ;}
    break;

  case 41:

/* Line 1455 of yacc.c  */
#line 182 "parse.y"
    { brain->CnList = makeDecayingConnect ( brain->CnList, &(brain->nConnect),
                                               (yyvsp[(2) - (13)].sval), (yyvsp[(3) - (13)].sval), (yyvsp[(4) - (13)].sval), (yyvsp[(5) - (13)].sval),
                                               (yyvsp[(6) - (13)].sval), (yyvsp[(7) - (13)].sval), (yyvsp[(8) - (13)].sval), (yyvsp[(9) - (13)].sval),
                                               (yyvsp[(10) - (13)].sval), (yyvsp[(11) - (13)].rval), (yyvsp[(12) - (13)].rval), (yyvsp[(13) - (13)].rval) );
               ;}
    break;

  case 42:

/* Line 1455 of yacc.c  */
#line 188 "parse.y"
    {
                 brain->recurrentList = makeRecurrentConnect( brain->recurrentList, &(brain->nRecurrent),
                                               (yyvsp[(2) - (12)].sval), (yyvsp[(3) - (12)].sval), (yyvsp[(4) - (12)].sval), (yyvsp[(5) - (12)].sval),
                                               (yyvsp[(6) - (12)].sval), (yyvsp[(7) - (12)].sval), (yyvsp[(8) - (12)].sval), (yyvsp[(9) - (12)].sval),
                                               (yyvsp[(10) - (12)].sval),
 (yyvsp[(11) - (12)].rval),
 (yyvsp[(12) - (12)].rval)
 );
               ;}
    break;

  case 43:

/* Line 1455 of yacc.c  */
#line 198 "parse.y"
    { unused (TK_INTERACTIVE); ;}
    break;

  case 44:

/* Line 1455 of yacc.c  */
#line 199 "parse.y"
    { unused (TK_IGNORE_EMPTY); ;}
    break;

  case 45:

/* Line 1455 of yacc.c  */
#line 200 "parse.y"
    { unused (TK_SAVE_SYN); ;}
    break;

  case 46:

/* Line 1455 of yacc.c  */
#line 201 "parse.y"
    { brain->savefile = strdup( (yyvsp[(2) - (3)].sval) ); brain->savetime = (yyvsp[(3) - (3)].rval); ;}
    break;

  case 47:

/* Line 1455 of yacc.c  */
#line 202 "parse.y"
    { brain->loadfile = strdup( (yyvsp[(2) - (2)].sval) ); ;}
    break;

  case 48:

/* Line 1455 of yacc.c  */
#line 203 "parse.y"
    { brain->Port = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 49:

/* Line 1455 of yacc.c  */
#line 204 "parse.y"
    { brain->Port = -1; ;}
    break;

  case 50:

/* Line 1455 of yacc.c  */
#line 205 "parse.y"
    { brain->Port = -2; ;}
    break;

  case 51:

/* Line 1455 of yacc.c  */
#line 206 "parse.y"
    { brain->HostName = strdup( (yyvsp[(2) - (2)].sval) ); ;}
    break;

  case 52:

/* Line 1455 of yacc.c  */
#line 207 "parse.y"
    { brain->HostPort = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 53:

/* Line 1455 of yacc.c  */
#line 208 "parse.y"
    { if( (yyvsp[(2) - (2)].ival) ) brain->flag = setFlag( brain->flag, "USE_DISTANCE" ); ;}
    break;

  case 54:

/* Line 1455 of yacc.c  */
#line 209 "parse.y"
    { if( (yyvsp[(2) - (2)].ival) ) brain->flag = setFlag( brain->flag, "OUTPUT_CELLS" ); ;}
    break;

  case 55:

/* Line 1455 of yacc.c  */
#line 210 "parse.y"
    { if( (yyvsp[(2) - (2)].ival) ) brain->flag = setFlag( brain->flag, "OUTPUT_CONNECT_MAP" ); ;}
    break;

  case 56:

/* Line 1455 of yacc.c  */
#line 211 "parse.y"
    { brain->flag = setFlag( brain->flag, "WARNINGS_OFF" );  ;}
    break;

  case 57:

/* Line 1455 of yacc.c  */
#line 212 "parse.y"
    { brain->EventNames = AddName( brain->EventNames, (yyvsp[(2) - (2)].sval), 0 ); brain->nEvents++; ;}
    break;

  case 58:

/* Line 1455 of yacc.c  */
#line 217 "parse.y"
    { csh = makecsh (); ;}
    break;

  case 62:

/* Line 1455 of yacc.c  */
#line 224 "parse.y"
    { csh->L.name = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 63:

/* Line 1455 of yacc.c  */
#line 225 "parse.y"
    { csh->width  = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 64:

/* Line 1455 of yacc.c  */
#line 226 "parse.y"
    { csh->height = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 65:

/* Line 1455 of yacc.c  */
#line 227 "parse.y"
    { csh->x      = (yyvsp[(2) - (3)].rval); csh->y = (yyvsp[(3) - (3)].rval); ;}
    break;

  case 66:

/* Line 1455 of yacc.c  */
#line 232 "parse.y"
    { column = makecolumn (); ;}
    break;

  case 70:

/* Line 1455 of yacc.c  */
#line 239 "parse.y"
    { column->L.name     = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 71:

/* Line 1455 of yacc.c  */
#line 240 "parse.y"
    { column->shellName  = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 72:

/* Line 1455 of yacc.c  */
#line 241 "parse.y"
    { column->LayerNames = AddName (column->LayerNames, (yyvsp[(2) - (2)].sval), 0);
                             column->nLayers++; ;}
    break;

  case 73:

/* Line 1455 of yacc.c  */
#line 244 "parse.y"
    { column->CnList = makeConnect (column->CnList, &(column->nConnect),
                                                NULL, (yyvsp[(2) - (10)].sval), (yyvsp[(3) - (10)].sval), (yyvsp[(4) - (10)].sval),
                                                NULL, (yyvsp[(5) - (10)].sval), (yyvsp[(6) - (10)].sval), (yyvsp[(7) - (10)].sval),
                                                (yyvsp[(8) - (10)].sval), (yyvsp[(9) - (10)].rval), (yyvsp[(10) - (10)].rval));
               ;}
    break;

  case 74:

/* Line 1455 of yacc.c  */
#line 250 "parse.y"
    { column->CnList = makeDecayingConnect (column->CnList, &(column->nConnect),
                                                NULL, (yyvsp[(2) - (11)].sval), (yyvsp[(3) - (11)].sval), (yyvsp[(4) - (11)].sval),
                                                NULL, (yyvsp[(5) - (11)].sval), (yyvsp[(6) - (11)].sval), (yyvsp[(7) - (11)].sval),
                                                (yyvsp[(8) - (11)].sval), (yyvsp[(9) - (11)].rval), (yyvsp[(10) - (11)].rval), (yyvsp[(11) - (11)].rval));
               ;}
    break;

  case 75:

/* Line 1455 of yacc.c  */
#line 256 "parse.y"
    {
                 column->recurrentList = makeRecurrentConnect( column->recurrentList, &(column->nRecurrent),
                                               NULL, (yyvsp[(2) - (10)].sval), (yyvsp[(3) - (10)].sval), (yyvsp[(4) - (10)].sval),
                                               NULL, (yyvsp[(5) - (10)].sval), (yyvsp[(6) - (10)].sval), (yyvsp[(7) - (10)].sval),
                                               (yyvsp[(8) - (10)].sval), (yyvsp[(9) - (10)].rval), (yyvsp[(10) - (10)].rval) );
               ;}
    break;

  case 76:

/* Line 1455 of yacc.c  */
#line 266 "parse.y"
    { lsh = makelsh (); ;}
    break;

  case 80:

/* Line 1455 of yacc.c  */
#line 273 "parse.y"
    { lsh->L.name = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 81:

/* Line 1455 of yacc.c  */
#line 274 "parse.y"
    { lsh->Lower  = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 82:

/* Line 1455 of yacc.c  */
#line 275 "parse.y"
    { lsh->Upper  = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 83:

/* Line 1455 of yacc.c  */
#line 280 "parse.y"
    { layer = makelayer (); ;}
    break;

  case 87:

/* Line 1455 of yacc.c  */
#line 287 "parse.y"
    { layer->L.name    = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 88:

/* Line 1455 of yacc.c  */
#line 288 "parse.y"
    { layer->shellName = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 89:

/* Line 1455 of yacc.c  */
#line 289 "parse.y"
    { layer->CellNames = AddName (layer->CellNames, (yyvsp[(2) - (3)].sval), (int) (yyvsp[(3) - (3)].ival));
                                     layer->nCellTypes++; ;}
    break;

  case 90:

/* Line 1455 of yacc.c  */
#line 292 "parse.y"
    { layer->CnList = makeConnect (layer->CnList, &(layer->nConnect),
                                               NULL, NULL, (yyvsp[(2) - (8)].sval), (yyvsp[(3) - (8)].sval),
                                               NULL, NULL, (yyvsp[(4) - (8)].sval), (yyvsp[(5) - (8)].sval),
                                               (yyvsp[(6) - (8)].sval), (yyvsp[(7) - (8)].rval), (yyvsp[(8) - (8)].rval));
               ;}
    break;

  case 91:

/* Line 1455 of yacc.c  */
#line 298 "parse.y"
    { layer->CnList = makeDecayingConnect (layer->CnList, &(layer->nConnect),
                                               NULL, NULL, (yyvsp[(2) - (9)].sval), (yyvsp[(3) - (9)].sval),
                                               NULL, NULL, (yyvsp[(4) - (9)].sval), (yyvsp[(5) - (9)].sval),
                                               (yyvsp[(6) - (9)].sval), (yyvsp[(7) - (9)].rval), (yyvsp[(8) - (9)].rval), (yyvsp[(9) - (9)].rval));
               ;}
    break;

  case 92:

/* Line 1455 of yacc.c  */
#line 304 "parse.y"
    {
                 layer->recurrentList = makeRecurrentConnect( layer->recurrentList, &(layer->nRecurrent), 
                                               NULL, NULL, (yyvsp[(2) - (8)].sval), (yyvsp[(3) - (8)].sval),
                                               NULL, NULL, (yyvsp[(4) - (8)].sval), (yyvsp[(5) - (8)].sval),
                                               (yyvsp[(6) - (8)].sval), (yyvsp[(7) - (8)].rval), (yyvsp[(8) - (8)].rval) );
               ;}
    break;

  case 93:

/* Line 1455 of yacc.c  */
#line 314 "parse.y"
    { cell = makecell (); ;}
    break;

  case 97:

/* Line 1455 of yacc.c  */
#line 321 "parse.y"
    { cell->L.name = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 98:

/* Line 1455 of yacc.c  */
#line 323 "parse.y"
    { cell->CmpNames = AddCmp (cell->CmpNames, (yyvsp[(2) - (5)].sval), (yyvsp[(3) - (5)].sval),
                                                     (yyvsp[(4) - (5)].rval), (yyvsp[(5) - (5)].rval), 0);
                            //pass 0 for z-coord for compatibility ------^
                            //with older input files
                            cell->nCmp++;
                          ;}
    break;

  case 99:

/* Line 1455 of yacc.c  */
#line 330 "parse.y"
    { cell->CmpNames = AddCmp (cell->CmpNames, (yyvsp[(2) - (6)].sval), (yyvsp[(3) - (6)].sval),
                                                     (yyvsp[(4) - (6)].rval), (yyvsp[(5) - (6)].rval), (yyvsp[(6) - (6)].rval) );
                            cell->nCmp++;
                          ;}
    break;

  case 100:

/* Line 1455 of yacc.c  */
#line 336 "parse.y"
    { cell->CnList = makeCmpConn (cell->CnList, (yyvsp[(2) - (6)].sval), (yyvsp[(3) - (6)].sval), 
                                                         (yyvsp[(4) - (6)].rval), (yyvsp[(5) - (6)].rval), (yyvsp[(6) - (6)].rval)); 
                            cell->nConnect++;
                          ;}
    break;

  case 101:

/* Line 1455 of yacc.c  */
#line 344 "parse.y"
    { cmp = makecmp (); ;}
    break;

  case 105:

/* Line 1455 of yacc.c  */
#line 351 "parse.y"
    { cmp->L.name     = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 106:

/* Line 1455 of yacc.c  */
#line 352 "parse.y"
    { cmp->Seed       = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 107:

/* Line 1455 of yacc.c  */
#line 353 "parse.y"
    { cmp->SpikeName  = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 108:

/* Line 1455 of yacc.c  */
#line 354 "parse.y"
    { cmp->ChannelNames = AddName (cmp->ChannelNames, (yyvsp[(2) - (2)].sval), 0);
                                     cmp->nChannels++; ;}
    break;

  case 109:

/* Line 1455 of yacc.c  */
#line 356 "parse.y"
    { twoptr = cmp->Spike_HW; ;}
    break;

  case 111:

/* Line 1455 of yacc.c  */
#line 357 "parse.y"
    { twoptr = cmp->Tau_Membrane; ;}
    break;

  case 113:

/* Line 1455 of yacc.c  */
#line 358 "parse.y"
    { twoptr = cmp->R_Membrane; ;}
    break;

  case 115:

/* Line 1455 of yacc.c  */
#line 359 "parse.y"
    { twoptr = cmp->Threshold; ;}
    break;

  case 117:

/* Line 1455 of yacc.c  */
#line 360 "parse.y"
    { twoptr = cmp->Leak_Reversal; ;}
    break;

  case 119:

/* Line 1455 of yacc.c  */
#line 361 "parse.y"
    { twoptr = cmp->Leak_G; ;}
    break;

  case 121:

/* Line 1455 of yacc.c  */
#line 362 "parse.y"
    { twoptr = cmp->VMREST; ;}
    break;

  case 123:

/* Line 1455 of yacc.c  */
#line 363 "parse.y"
    { twoptr = cmp->CaInt; ;}
    break;

  case 125:

/* Line 1455 of yacc.c  */
#line 364 "parse.y"
    { twoptr = cmp->CaExt; ;}
    break;

  case 127:

/* Line 1455 of yacc.c  */
#line 365 "parse.y"
    { twoptr = cmp->CaTau; ;}
    break;

  case 129:

/* Line 1455 of yacc.c  */
#line 366 "parse.y"
    { twoptr = cmp->CaSpikeInc; ;}
    break;

  case 131:

/* Line 1455 of yacc.c  */
#line 374 "parse.y"
    { chan = makechan ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 133:

/* Line 1455 of yacc.c  */
#line 375 "parse.y"
    { chan = makechan ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 135:

/* Line 1455 of yacc.c  */
#line 376 "parse.y"
    { chan = makechan ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 137:

/* Line 1455 of yacc.c  */
#line 377 "parse.y"
    { chan = makechan ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 139:

/* Line 1455 of yacc.c  */
#line 378 "parse.y"
    { chan = makechan ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 155:

/* Line 1455 of yacc.c  */
#line 407 "parse.y"
    { chan->L.name    = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 156:

/* Line 1455 of yacc.c  */
#line 408 "parse.y"
    { twoptr = chan->mPower; ;}
    break;

  case 158:

/* Line 1455 of yacc.c  */
#line 409 "parse.y"
    { twoptr = chan->unitaryG; ;}
    break;

  case 160:

/* Line 1455 of yacc.c  */
#line 410 "parse.y"
    { twoptr = chan->strength ; ;}
    break;

  case 162:

/* Line 1455 of yacc.c  */
#line 411 "parse.y"
    { twoptr = chan->strength_range; ;}
    break;

  case 164:

/* Line 1455 of yacc.c  */
#line 412 "parse.y"
    { twoptr = chan->M_Initial; ;}
    break;

  case 166:

/* Line 1455 of yacc.c  */
#line 413 "parse.y"
    { twoptr = chan->ReversePot; ;}
    break;

  case 168:

/* Line 1455 of yacc.c  */
#line 414 "parse.y"
    { chan->Seed = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 169:

/* Line 1455 of yacc.c  */
#line 417 "parse.y"
    { twoptr = chan->eHalfMinM; ;}
    break;

  case 171:

/* Line 1455 of yacc.c  */
#line 418 "parse.y"
    { twoptr = chan->tauScaleM; ;}
    break;

  case 173:

/* Line 1455 of yacc.c  */
#line 419 "parse.y"
    { chan->slopeM [0] = (yyvsp[(2) - (4)].rval);
                                            chan->slopeM [1] = (yyvsp[(3) - (4)].rval);
                                            chan->slopeM [2] = (yyvsp[(4) - (4)].rval); ;}
    break;

  case 174:

/* Line 1455 of yacc.c  */
#line 422 "parse.y"
    { chan->slopeM_stdev = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 175:

/* Line 1455 of yacc.c  */
#line 425 "parse.y"
    { twoptr = chan->CA_SCALE; ;}
    break;

  case 177:

/* Line 1455 of yacc.c  */
#line 426 "parse.y"
    { twoptr = chan->CA_EXP; ;}
    break;

  case 179:

/* Line 1455 of yacc.c  */
#line 427 "parse.y"
    { twoptr = chan->CA_EXP_RANGE; ;}
    break;

  case 181:

/* Line 1455 of yacc.c  */
#line 428 "parse.y"
    { twoptr = chan->CA_HALF_MIN; ;}
    break;

  case 183:

/* Line 1455 of yacc.c  */
#line 429 "parse.y"
    { twoptr = chan->CA_TAU_SCALE; ;}
    break;

  case 185:

/* Line 1455 of yacc.c  */
#line 432 "parse.y"
    { twoptr = chan->H_Initial; ;}
    break;

  case 187:

/* Line 1455 of yacc.c  */
#line 433 "parse.y"
    { twoptr = chan->hPower;    ;}
    break;

  case 189:

/* Line 1455 of yacc.c  */
#line 434 "parse.y"
    { twoptr = chan->eHalfMinM;  ;}
    break;

  case 191:

/* Line 1455 of yacc.c  */
#line 435 "parse.y"
    { twoptr = chan->eHalfMinH;  ;}
    break;

  case 193:

/* Line 1455 of yacc.c  */
#line 436 "parse.y"
    { chan->slopeM [0] = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 194:

/* Line 1455 of yacc.c  */
#line 437 "parse.y"
    { chan->slopeM_stdev = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 195:

/* Line 1455 of yacc.c  */
#line 438 "parse.y"
    { chan->slopeH [0] = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 196:

/* Line 1455 of yacc.c  */
#line 439 "parse.y"
    { chan->slopeH_stdev = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 197:

/* Line 1455 of yacc.c  */
#line 441 "parse.y"
    { chan->ValM_stdev  = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 198:

/* Line 1455 of yacc.c  */
#line 442 "parse.y"
    { chan->VoltM_stdev = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 199:

/* Line 1455 of yacc.c  */
#line 443 "parse.y"
    { chan->ValH_stdev  = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 200:

/* Line 1455 of yacc.c  */
#line 444 "parse.y"
    { chan->VoltH_stdev = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 201:

/* Line 1455 of yacc.c  */
#line 446 "parse.y"
    { nval = 0; ;}
    break;

  case 202:

/* Line 1455 of yacc.c  */
#line 446 "parse.y"
    { chan->nValM  = nval; chan->TauValM  = allocVlist (nval, vlist); ;}
    break;

  case 203:

/* Line 1455 of yacc.c  */
#line 447 "parse.y"
    { nval = 0; ;}
    break;

  case 204:

/* Line 1455 of yacc.c  */
#line 447 "parse.y"
    { chan->nVoltM = nval; chan->TauVoltM = allocVlist (nval, vlist); ;}
    break;

  case 205:

/* Line 1455 of yacc.c  */
#line 448 "parse.y"
    { nval = 0; ;}
    break;

  case 206:

/* Line 1455 of yacc.c  */
#line 448 "parse.y"
    { chan->nValH  = nval; chan->TauValH  = allocVlist (nval, vlist); ;}
    break;

  case 207:

/* Line 1455 of yacc.c  */
#line 449 "parse.y"
    { nval = 0; ;}
    break;

  case 208:

/* Line 1455 of yacc.c  */
#line 449 "parse.y"
    { chan->nVoltH = nval; chan->TauVoltH = allocVlist (nval, vlist); ;}
    break;

  case 209:

/* Line 1455 of yacc.c  */
#line 452 "parse.y"
    { twoptr = chan->eHalfMinM; ;}
    break;

  case 211:

/* Line 1455 of yacc.c  */
#line 453 "parse.y"
    { twoptr = chan->eHalfMinH;  ;}
    break;

  case 213:

/* Line 1455 of yacc.c  */
#line 454 "parse.y"
    { twoptr = chan->H_Initial; ;}
    break;

  case 215:

/* Line 1455 of yacc.c  */
#line 455 "parse.y"
    { twoptr = chan->hPower;    ;}
    break;

  case 217:

/* Line 1455 of yacc.c  */
#line 456 "parse.y"
    { twoptr = chan->tauScaleM; ;}
    break;

  case 219:

/* Line 1455 of yacc.c  */
#line 457 "parse.y"
    { twoptr = chan->tauScaleH; ;}
    break;

  case 221:

/* Line 1455 of yacc.c  */
#line 458 "parse.y"
    { chan->slopeM [0] = (yyvsp[(2) - (3)].rval);
                                      chan->slopeM [1] = (yyvsp[(3) - (3)].rval); ;}
    break;

  case 222:

/* Line 1455 of yacc.c  */
#line 460 "parse.y"
    { chan->slopeH [0] = (yyvsp[(2) - (3)].rval);
                                      chan->slopeH [1] = (yyvsp[(3) - (3)].rval); ;}
    break;

  case 223:

/* Line 1455 of yacc.c  */
#line 462 "parse.y"
    { chan->slopeM_stdev = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 224:

/* Line 1455 of yacc.c  */
#line 463 "parse.y"
    { chan->slopeH_stdev = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 225:

/* Line 1455 of yacc.c  */
#line 464 "parse.y"
    { twoptr = chan->alphaScaleFactorM; ;}
    break;

  case 227:

/* Line 1455 of yacc.c  */
#line 465 "parse.y"
    { twoptr = chan->betaScaleFactorM; ;}
    break;

  case 229:

/* Line 1455 of yacc.c  */
#line 466 "parse.y"
    { twoptr = chan->alphaScaleFactorH; ;}
    break;

  case 231:

/* Line 1455 of yacc.c  */
#line 467 "parse.y"
    { twoptr = chan->betaScaleFactorH; ;}
    break;

  case 233:

/* Line 1455 of yacc.c  */
#line 472 "parse.y"
    { syn = makesynapse (); ;}
    break;

  case 237:

/* Line 1455 of yacc.c  */
#line 479 "parse.y"
    { syn->L.name    = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 238:

/* Line 1455 of yacc.c  */
#line 480 "parse.y"
    { syn->Seed      = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 239:

/* Line 1455 of yacc.c  */
#line 481 "parse.y"
    { syn->SfdName   = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 240:

/* Line 1455 of yacc.c  */
#line 482 "parse.y"
    { syn->LearnName = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 241:

/* Line 1455 of yacc.c  */
#line 483 "parse.y"
    { syn->DataName  = strdup ((yyvsp[(2) - (2)].sval)); 
                                    deprecate (TK_DATA_LABEL); ;}
    break;

  case 242:

/* Line 1455 of yacc.c  */
#line 485 "parse.y"
    { syn->AugmentationName = strdup ( (yyvsp[(2) - (2)].sval) ); ;}
    break;

  case 243:

/* Line 1455 of yacc.c  */
#line 486 "parse.y"
    { syn->PsgName   = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 244:

/* Line 1455 of yacc.c  */
#line 487 "parse.y"
    { twoptr = syn->USE; ;}
    break;

  case 246:

/* Line 1455 of yacc.c  */
#line 488 "parse.y"
    { twoptr = syn->SynDelay; ;}
    break;

  case 248:

/* Line 1455 of yacc.c  */
#line 489 "parse.y"
    { twoptr = syn->SynRever; ;}
    break;

  case 250:

/* Line 1455 of yacc.c  */
#line 490 "parse.y"
    { twoptr = syn->MaxG; ;}
    break;

  case 252:

/* Line 1455 of yacc.c  */
#line 491 "parse.y"
    { twoptr = syn->InitRSE; ;}
    break;

  case 254:

/* Line 1455 of yacc.c  */
#line 492 "parse.y"
    { twoptr = syn->InitDeltaT; ;}
    break;

  case 256:

/* Line 1455 of yacc.c  */
#line 493 "parse.y"
    {
            if( syn->nToggles % 10 == 0 )  //need to allocate more space
            {
                syn->toggleTimes = (double*) realloc( syn->toggleTimes, sizeof(double)*(syn->nToggles+10) );
                syn->toggleModes = (int*) realloc( syn->toggleModes, sizeof(int)*(syn->nToggles+10) );
            }
            syn->toggleTimes[syn->nToggles] = (yyvsp[(2) - (2)].rval);
            syn->toggleModes[syn->nToggles++] = 1;
        ;}
    break;

  case 257:

/* Line 1455 of yacc.c  */
#line 502 "parse.y"
    {
            if( syn->nToggles % 10 == 0 )  //need to allocate more space
            {
                syn->toggleTimes = (double*) realloc( syn->toggleTimes, sizeof(double)*(syn->nToggles+10) );
                syn->toggleModes = (int*) realloc( syn->toggleModes, sizeof(int)*(syn->nToggles+10) );
            }
            syn->toggleTimes[syn->nToggles] = (yyvsp[(2) - (2)].rval);
            syn->toggleModes[syn->nToggles++] = 0;
        ;}
    break;

  case 258:

/* Line 1455 of yacc.c  */
#line 512 "parse.y"
    {
            if( syn->nSfdToggles % 10 == 0 )  //need to allocate more space
            {
                syn->sfdToggleTimes = (double*) realloc( syn->sfdToggleTimes, sizeof(double)*(syn->nSfdToggles+10) );
                syn->sfdToggleModes = (int*) realloc( syn->sfdToggleModes, sizeof(int)*(syn->nSfdToggles+10) );
                printf ("Realloc worked\n");
            }
            syn->sfdToggleTimes[syn->nSfdToggles] = (yyvsp[(2) - (2)].rval);
            syn->sfdToggleModes[syn->nSfdToggles++] = 1;
        ;}
    break;

  case 259:

/* Line 1455 of yacc.c  */
#line 522 "parse.y"
    {
            if( syn->nSfdToggles % 10 == 0 )  //need to allocate more space
            {
                syn->sfdToggleTimes = (double*) realloc( syn->sfdToggleTimes, sizeof(double)*(syn->nSfdToggles+10) );
                syn->sfdToggleModes = (int*) realloc( syn->sfdToggleModes, sizeof(int)*(syn->nSfdToggles+10) );
            }
            syn->sfdToggleTimes[syn->nSfdToggles] = (yyvsp[(2) - (2)].rval);
            syn->sfdToggleModes[syn->nSfdToggles++] = 0;
        ;}
    break;

  case 260:

/* Line 1455 of yacc.c  */
#line 535 "parse.y"
    { syn_psg = makesyn_psg (); ;}
    break;

  case 264:

/* Line 1455 of yacc.c  */
#line 542 "parse.y"
    { syn_psg->L.name = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 265:

/* Line 1455 of yacc.c  */
#line 543 "parse.y"
    { syn_psg->File   = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 266:

/* Line 1455 of yacc.c  */
#line 548 "parse.y"
    { syn_fd = makesyn_fd (); ;}
    break;

  case 270:

/* Line 1455 of yacc.c  */
#line 555 "parse.y"
    { syn_fd->L.name = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 271:

/* Line 1455 of yacc.c  */
#line 556 "parse.y"
    { syn_fd->Seed   = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 272:

/* Line 1455 of yacc.c  */
#line 557 "parse.y"
    { syn_fd->SFD    = SFDCode ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 273:

/* Line 1455 of yacc.c  */
#line 558 "parse.y"
    { twoptr = syn_fd->Facil_Tau; ;}
    break;

  case 275:

/* Line 1455 of yacc.c  */
#line 559 "parse.y"
    { twoptr = syn_fd->Depr_Tau; ;}
    break;

  case 277:

/* Line 1455 of yacc.c  */
#line 564 "parse.y"
    { syn_learn = makesyn_learn (); ;}
    break;

  case 281:

/* Line 1455 of yacc.c  */
#line 571 "parse.y"
    { syn_learn->L.name = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 282:

/* Line 1455 of yacc.c  */
#line 572 "parse.y"
    { syn_learn->Seed = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 283:

/* Line 1455 of yacc.c  */
#line 573 "parse.y"
    { syn_learn->Learning = LearnCode ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 284:

/* Line 1455 of yacc.c  */
#line 574 "parse.y"
    { syn_learn->Learning_Shape = LearnShape ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 285:

/* Line 1455 of yacc.c  */
#line 575 "parse.y"
    { twoptr = syn_learn->DownWind; ;}
    break;

  case 287:

/* Line 1455 of yacc.c  */
#line 576 "parse.y"
    { twoptr = syn_learn->UpWind; ;}
    break;

  case 289:

/* Line 1455 of yacc.c  */
#line 577 "parse.y"
    { twoptr = syn_learn->Neg_Heb_Peak_Delta_Use; ;}
    break;

  case 291:

/* Line 1455 of yacc.c  */
#line 578 "parse.y"
    { twoptr = syn_learn->Pos_Heb_Peak_Delta_Use; ;}
    break;

  case 293:

/* Line 1455 of yacc.c  */
#line 579 "parse.y"
    { twoptr = syn_learn->Neg_Heb_Peak_Time; ;}
    break;

  case 295:

/* Line 1455 of yacc.c  */
#line 580 "parse.y"
    { twoptr = syn_learn->Pos_Heb_Peak_Time; ;}
    break;

  case 297:

/* Line 1455 of yacc.c  */
#line 585 "parse.y"
    { syn_data = makesyn_data (); 
                        deprecate (TK_SYN_DATA); ;}
    break;

  case 301:

/* Line 1455 of yacc.c  */
#line 593 "parse.y"
    { syn_data->L.name = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 302:

/* Line 1455 of yacc.c  */
#line 594 "parse.y"
    { syn_data->Seed   = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 303:

/* Line 1455 of yacc.c  */
#line 595 "parse.y"
    { twoptr = syn_data->MaxG; ;}
    break;

  case 305:

/* Line 1455 of yacc.c  */
#line 596 "parse.y"
    { twoptr = syn_data->SynDelay; ;}
    break;

  case 307:

/* Line 1455 of yacc.c  */
#line 597 "parse.y"
    { twoptr = syn_data->SynRever; ;}
    break;

  case 309:

/* Line 1455 of yacc.c  */
#line 602 "parse.y"
    { syn_augmentation = makesyn_augmentation (); ;}
    break;

  case 313:

/* Line 1455 of yacc.c  */
#line 608 "parse.y"
    { syn_augmentation->L.name = strdup ((yyvsp[(2) - (2)].sval) ); ;}
    break;

  case 314:

/* Line 1455 of yacc.c  */
#line 609 "parse.y"
    { twoptr = syn_augmentation->CA_init; ;}
    break;

  case 316:

/* Line 1455 of yacc.c  */
#line 610 "parse.y"
    { twoptr = syn_augmentation->CA_decay; ;}
    break;

  case 318:

/* Line 1455 of yacc.c  */
#line 611 "parse.y"
    { twoptr = syn_augmentation->CA_tau; ;}
    break;

  case 320:

/* Line 1455 of yacc.c  */
#line 612 "parse.y"
    { twoptr = syn_augmentation->CA_increment; ;}
    break;

  case 322:

/* Line 1455 of yacc.c  */
#line 613 "parse.y"
    { twoptr = syn_augmentation->MaxSA; ;}
    break;

  case 324:

/* Line 1455 of yacc.c  */
#line 614 "parse.y"
    { twoptr = syn_augmentation->Alpha; ;}
    break;

  case 326:

/* Line 1455 of yacc.c  */
#line 615 "parse.y"
    { twoptr = syn_augmentation->Augmentation_init; ;}
    break;

  case 328:

/* Line 1455 of yacc.c  */
#line 616 "parse.y"
    { twoptr = syn_augmentation->Augmentation_tau; ;}
    break;

  case 330:

/* Line 1455 of yacc.c  */
#line 617 "parse.y"
    { twoptr = syn_augmentation->SA_delay; ;}
    break;

  case 332:

/* Line 1455 of yacc.c  */
#line 622 "parse.y"
    { spike = makespike (); ;}
    break;

  case 336:

/* Line 1455 of yacc.c  */
#line 629 "parse.y"
    { spike->L.name = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 337:

/* Line 1455 of yacc.c  */
#line 630 "parse.y"
    { nval = 0; ;}
    break;

  case 338:

/* Line 1455 of yacc.c  */
#line 630 "parse.y"
    { spike->nVoltages = nval;
                                              spike->Voltages  = allocRVlist (nval, vlist); ;}
    break;

  case 339:

/* Line 1455 of yacc.c  */
#line 636 "parse.y"
    { stim = makestim (); ;}
    break;

  case 343:

/* Line 1455 of yacc.c  */
#line 643 "parse.y"
    { stim->L.name          = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 344:

/* Line 1455 of yacc.c  */
#line 644 "parse.y"
    { stim->MODE            = ModeCode ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 345:

/* Line 1455 of yacc.c  */
#line 645 "parse.y"
    { stim->PATTERN         = PatternCode ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 346:

/* Line 1455 of yacc.c  */
#line 646 "parse.y"
    { stim->VERT_TRANS      = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 347:

/* Line 1455 of yacc.c  */
#line 647 "parse.y"
    { stim->PHASE     = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 348:

/* Line 1455 of yacc.c  */
#line 648 "parse.y"
    { stim->Rate            = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 349:

/* Line 1455 of yacc.c  */
#line 649 "parse.y"
    { stim->Tau             = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 350:

/* Line 1455 of yacc.c  */
#line 650 "parse.y"
    { stim->Correl          = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 351:

/* Line 1455 of yacc.c  */
#line 651 "parse.y"
    { stim->TIMING          = TimingCode ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 352:

/* Line 1455 of yacc.c  */
#line 652 "parse.y"
    { stim->FileName        = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 353:

/* Line 1455 of yacc.c  */
#line 653 "parse.y"
    { stim->Port            = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 354:

/* Line 1455 of yacc.c  */
#line 654 "parse.y"
    { stim->Port            = -1; ;}
    break;

  case 355:

/* Line 1455 of yacc.c  */
#line 655 "parse.y"
    { stim->SAME_SEED       = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 356:

/* Line 1455 of yacc.c  */
#line 656 "parse.y"
    { stim->Seed            = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 357:

/* Line 1455 of yacc.c  */
#line 657 "parse.y"
    { stim->nFreqs          = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 358:

/* Line 1455 of yacc.c  */
#line 658 "parse.y"
    { stim->CellsPerFreq    = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 359:

/* Line 1455 of yacc.c  */
#line 659 "parse.y"
    { twoptr = stim->Time_Freq_Incr; ;}
    break;

  case 361:

/* Line 1455 of yacc.c  */
#line 660 "parse.y"
    { twoptr = stim->DynRange; ;}
    break;

  case 363:

/* Line 1455 of yacc.c  */
#line 661 "parse.y"
    { stim->AMP_Start       = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 364:

/* Line 1455 of yacc.c  */
#line 662 "parse.y"
    { stim->AMP_End         = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 365:

/* Line 1455 of yacc.c  */
#line 663 "parse.y"
    { stim->WidthSec        = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 366:

/* Line 1455 of yacc.c  */
#line 664 "parse.y"
    { stim->FREQ_Start      = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 367:

/* Line 1455 of yacc.c  */
#line 665 "parse.y"
    { nval = 0; ;}
    break;

  case 368:

/* Line 1455 of yacc.c  */
#line 665 "parse.y"
    { stim->nTStart = nval; 
                                            stim->TStart  = allocVlist (nval, vlist); ;}
    break;

  case 369:

/* Line 1455 of yacc.c  */
#line 667 "parse.y"
    { nval = 0; ;}
    break;

  case 370:

/* Line 1455 of yacc.c  */
#line 667 "parse.y"
    { stim->nTStop  = nval;
                                            stim->TStop   = allocVlist (nval, vlist); ;}
    break;

  case 371:

/* Line 1455 of yacc.c  */
#line 673 "parse.y"
    { sti = makesti (); ;}
    break;

  case 375:

/* Line 1455 of yacc.c  */
#line 680 "parse.y"
    { sti->L.name      = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 376:

/* Line 1455 of yacc.c  */
#line 681 "parse.y"
    { sti->StimName    = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 377:

/* Line 1455 of yacc.c  */
#line 683 "parse.y"
    { sti->ColName  = strdup ((yyvsp[(2) - (6)].sval));
                            sti->LayName  = strdup ((yyvsp[(3) - (6)].sval));
                            sti->CellName = strdup ((yyvsp[(4) - (6)].sval));
                            sti->CmpName  = strdup ((yyvsp[(5) - (6)].sval));
                            sti->Prob     = (yyvsp[(6) - (6)].rval); ;}
    break;

  case 378:

/* Line 1455 of yacc.c  */
#line 692 "parse.y"
    { report = makereport (); ;}
    break;

  case 382:

/* Line 1455 of yacc.c  */
#line 699 "parse.y"
    { report->L.name    = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 383:

/* Line 1455 of yacc.c  */
#line 701 "parse.y"
    { report->ColName   = strdup ((yyvsp[(2) - (5)].sval));
                                   report->LayName   = strdup ((yyvsp[(3) - (5)].sval));
                                   report->CellName  = strdup ((yyvsp[(4) - (5)].sval));
                                   report->CmpName   = strdup ((yyvsp[(5) - (5)].sval)); ;}
    break;

  case 384:

/* Line 1455 of yacc.c  */
#line 705 "parse.y"
    { report->FileName  = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 385:

/* Line 1455 of yacc.c  */
#line 706 "parse.y"
    { report->Port      = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 386:

/* Line 1455 of yacc.c  */
#line 707 "parse.y"
    { report->Port      = -1; ;}
    break;

  case 387:

/* Line 1455 of yacc.c  */
#line 708 "parse.y"
    { report->ASCII     = TRUE; ;}
    break;

  case 388:

/* Line 1455 of yacc.c  */
#line 709 "parse.y"
    { report->reportFlag |= 1;
                                   report->ASCII     = TRUE; ;}
    break;

  case 389:

/* Line 1455 of yacc.c  */
#line 711 "parse.y"
    { report->ReportOn  = ReportCode ("CHANNEL_RPT");
                                   report->Name      = strdup ((yyvsp[(2) - (2)].sval)); 
                                   printf ("Report Channel: code = %x, name = '%s'\n", report->ReportOn, report->Name); ;}
    break;

  case 390:

/* Line 1455 of yacc.c  */
#line 714 "parse.y"
    { report->ReportOn  = ReportCode ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 391:

/* Line 1455 of yacc.c  */
#line 715 "parse.y"
    { report->CellSequence = CellSequenceCode ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 392:

/* Line 1455 of yacc.c  */
#line 716 "parse.y"
    { report->Prob      = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 393:

/* Line 1455 of yacc.c  */
#line 717 "parse.y"
    { report->Frequency = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 394:

/* Line 1455 of yacc.c  */
#line 718 "parse.y"
    { nval = 0; ;}
    break;

  case 395:

/* Line 1455 of yacc.c  */
#line 719 "parse.y"
    { report->nTStart = nval;
                                   report->TStart  = allocVlist (nval, vlist); ;}
    break;

  case 396:

/* Line 1455 of yacc.c  */
#line 721 "parse.y"
    { nval = 0; ;}
    break;

  case 397:

/* Line 1455 of yacc.c  */
#line 722 "parse.y"
    { report->nTStop  = nval;
                                   report->TStop   = allocVlist (nval, vlist); ;}
    break;

  case 398:

/* Line 1455 of yacc.c  */
#line 724 "parse.y"
    { report->ReportOn = ReportCode ("SYNAPSE_USE");
                                   report->Name     = strdup ((yyvsp[(2) - (2)].sval)); ;}
    break;

  case 399:

/* Line 1455 of yacc.c  */
#line 726 "parse.y"
    {
                                   report->ReportOn = ReportCode ("SYNAPSE_RSE");
                                   report->Name     = strdup ((yyvsp[(2) - (2)].sval));
                                 ;}
    break;

  case 400:

/* Line 1455 of yacc.c  */
#line 730 "parse.y"
    {
                                   report->ReportOn = ReportCode ("SYNAPSE_UF");
                                   report->Name     = strdup ((yyvsp[(2) - (2)].sval));
                                 ;}
    break;

  case 401:

/* Line 1455 of yacc.c  */
#line 734 "parse.y"
    {
                                       report->ReportOn = ReportCode ("SYNAPSE_SA");
                                       report->Name     = strdup ((yyvsp[(2) - (2)].sval));
                                     ;}
    break;

  case 402:

/* Line 1455 of yacc.c  */
#line 738 "parse.y"
    {
                                       report->ReportOn = ReportCode ("SYNAPSE_CA");
                                       report->Name     = strdup ((yyvsp[(2) - (2)].sval));
                                     ;}
    break;

  case 403:

/* Line 1455 of yacc.c  */
#line 742 "parse.y"
    { report->Seed = (yyvsp[(2) - (2)].ival); ;}
    break;

  case 404:

/* Line 1455 of yacc.c  */
#line 743 "parse.y"
    { report->Seed = SELECT_FRONT; ;}
    break;

  case 405:

/* Line 1455 of yacc.c  */
#line 744 "parse.y"
    { report->reportFlag |= (yyvsp[(2) - (2)].ival); ;}
    break;

  case 406:

/* Line 1455 of yacc.c  */
#line 745 "parse.y"
    { ;}
    break;

  case 407:

/* Line 1455 of yacc.c  */
#line 748 "parse.y"
    { event = makeevent(); ;}
    break;

  case 411:

/* Line 1455 of yacc.c  */
#line 755 "parse.y"
    { event->L.name = strdup( (yyvsp[(2) - (2)].sval) ); ;}
    break;

  case 412:

/* Line 1455 of yacc.c  */
#line 756 "parse.y"
    { event->synapseName = strdup( (yyvsp[(2) - (2)].sval) ); ;}
    break;

  case 413:

/* Line 1455 of yacc.c  */
#line 757 "parse.y"
    { 
            event->cellGroupNames[0] = strdup( (yyvsp[(2) - (5)].sval) );
            event->cellGroupNames[1] = strdup( (yyvsp[(3) - (5)].sval) );
            event->cellGroupNames[2] = strdup( (yyvsp[(4) - (5)].sval) );
            event->cellGroupNames[3] = strdup( (yyvsp[(5) - (5)].sval) );
        ;}
    break;

  case 414:

/* Line 1455 of yacc.c  */
#line 763 "parse.y"
    { event->file = strdup( (yyvsp[(2) - (3)].sval) ); event->time = (yyvsp[(3) - (3)].rval); ;}
    break;

  case 417:

/* Line 1455 of yacc.c  */
#line 771 "parse.y"
    { report->reportFlag |= AVERAGE_SYN; ;}
    break;

  case 418:

/* Line 1455 of yacc.c  */
#line 772 "parse.y"
    { report->reportFlag |= HIDE_STEP; ;}
    break;

  case 419:

/* Line 1455 of yacc.c  */
#line 777 "parse.y"
    { if (nval < VSIZE) vlist [nval++] = (yyvsp[(1) - (1)].rval); ;}
    break;

  case 420:

/* Line 1455 of yacc.c  */
#line 778 "parse.y"
    { if (nval < VSIZE) vlist [nval++] = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 421:

/* Line 1455 of yacc.c  */
#line 781 "parse.y"
    { twoptr [0] = (yyvsp[(1) - (2)].rval); twoptr [1] = (yyvsp[(2) - (2)].rval); ;}
    break;

  case 422:

/* Line 1455 of yacc.c  */
#line 782 "parse.y"
    { twoptr [0] = (yyvsp[(1) - (1)].rval); twoptr [1] = 0.0; ;}
    break;

  case 423:

/* Line 1455 of yacc.c  */
#line 785 "parse.y"
    { (yyval.rval) = (double) (yyvsp[(1) - (1)].ival); ;}
    break;

  case 424:

/* Line 1455 of yacc.c  */
#line 786 "parse.y"
    { (yyval.rval) = (yyvsp[(1) - (1)].rval); ;}
    break;



/* Line 1455 of yacc.c  */
#line 4428 "parse.c"
      default: break;
    }
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
      {
	YYSIZE_T yysize = yysyntax_error (0, yystate, yychar);
	if (yymsg_alloc < yysize && yymsg_alloc < YYSTACK_ALLOC_MAXIMUM)
	  {
	    YYSIZE_T yyalloc = 2 * yysize;
	    if (! (yysize <= yyalloc && yyalloc <= YYSTACK_ALLOC_MAXIMUM))
	      yyalloc = YYSTACK_ALLOC_MAXIMUM;
	    if (yymsg != yymsgbuf)
	      YYSTACK_FREE (yymsg);
	    yymsg = (char *) YYSTACK_ALLOC (yyalloc);
	    if (yymsg)
	      yymsg_alloc = yyalloc;
	    else
	      {
		yymsg = yymsgbuf;
		yymsg_alloc = sizeof yymsgbuf;
	      }
	  }

	if (0 < yysize && yysize <= yymsg_alloc)
	  {
	    (void) yysyntax_error (yymsg, yystate, yychar);
	    yyerror (yymsg);
	  }
	else
	  {
	    yyerror (YY_("syntax error"));
	    if (yysize != 0)
	      goto yyexhaustedlab;
	  }
      }
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
	 error, discard it.  */

      if (yychar <= YYEOF)
	{
	  /* Return failure if at end of input.  */
	  if (yychar == YYEOF)
	    YYABORT;
	}
      else
	{
	  yydestruct ("Error: discarding",
		      yytoken, &yylval);
	  yychar = YYEMPTY;
	}
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule which action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (yyn != YYPACT_NINF)
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;


      yydestruct ("Error: popping",
		  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  *++yyvsp = yylval;


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if !defined(yyoverflow) || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
     yydestruct ("Cleanup: discarding lookahead",
		 yytoken, &yylval);
  /* Do not reclaim the symbols of the rule which action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
		  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  /* Make sure YYID is used.  */
  return YYID (yyresult);
}



/* Line 1675 of yacc.c  */
#line 789 "parse.y"


int multiInput( char *fname )
{
  INFILE latest;

  if( !initFlag )  //is this the first time we're including another input file?
  {
    initFileStack( &fileStack );
    initFlag = 1;
  }

  latest.from = NULL;
  latest.file = strdup( fname );//filename to be opened
  latest.parentFile = strdup( currentFile );
  latest.line = TIN->line;//remember where in the current input file this was included
  latest.col = 0;

  pushFileStack( &fileStack, &latest );

  if( latest.file ) free( latest.file );
}

int yywrap(void)
{
  INFILE *next = NULL;
  struct stat fs;
  int nbytes;
  FILE *in;

  if( initFlag )
  {
    next = popFileStack( &fileStack, next );
    while( next != NULL )
    {
      //bring in new info

      stat( next->file, &fs );
      nbytes = fs.st_size;
      in = fopen( next->file, "r" );

      if( !in )
      {
        fprintf( stderr, "Error: could not open file %s\n", next->file );
        fprintf( stderr, "\tdefined in file %s, line %d\n", next->parentFile, next->line );

        printf( "Error: could not open file %s\n", next->file );
        printf( "\tdefined in file %s, line %d\n", next->parentFile, next->line );

        free( next->parentFile );
        free( next->file );
        free( next );
        next = popFileStack( &fileStack, next );
        continue;
      }

      free( currentFile );
      currentFile = strdup( next->file );
      TIN->line = 0;
      yy_switch_to_buffer( yy_create_buffer( in, fs.st_size ) );  //should I store the return value somewhere?

      //these lines make it break, but why? I need to deallocate this memory, don't I?
      //free( next->parentFile );
      //free( next->file );
      //free( next );

      return 0;  //continue parsing
    }

    //next == NULL if I get here, so the fileStack is empty
    destroyFileStack( &fileStack );
    return 1;  //end parsing
  } 
  
  return 1;  //end parsing
}

yyerror (char *str)
{
  printerr ("%s:%d: %s\n", TIN->file, TIN->line, str);
  TIN->nParseErr++;
}

unused (int kind)
{
  printerr ("%s:%d: Warning: Keyword '%s' no longer used\n",
            TIN->file, TIN->line, TK2name (kind));
}

deprecate (int kind)
{
//printerr ("%s:%d: Warning: Keyword '%s' is deprecated\n",
//          TIN->file, TIN->line, TK2name (kind));
}

/*-----------------------------------------------------------------------

This is the parser for ncs input files.  It accepts tokens from the lexical
analyzer (scan.l).  Tokens may be known keywords (as listed in the %tokens)
section, or variables, which can be INTEGER, REAL, LOGICAL, or STRING.  The
parser creates input structures and assigns values to them according to the
rules of the input language.

The format for most brain elements is the same: the parser sees the token
for an element, creates a structure for that element (entering it into the
list of structures), then recognizes keyword-value sets allowed for that
element and assigns the values to the structure elements.


TODO:

  Need more informative error messages for parse errors.

  Add INCLUDE file capability.

  Someone who knows more about YACC/Bison might be able to improve on this
  set of grammar rules.
-----------------------------------------------------------------------*/
#endif

