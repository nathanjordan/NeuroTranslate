
%%         

%class Scanner
%line
%byaccj
%debug

%{

/* store a reference to the parser object */
private Parser yyparser;

/* constructor taking an additional parser object */
public Scanner(java.io.Reader r, Parser yyparser) {
	this(r);
	this.yyparser = yyparser;
	}

%}

%state STRING

DIGIT = [0-9]
NAME  = [a-zA-Z0-9_+\-\.>/]+
E     = [Ee][+-]?{DIGIT}+

%%

#.*\n               {  }

\-?{DIGIT}+            { yyparser.yylval.ival = Integer.parseInt(yytext()); return (Parser.INTEGER); }

\-?{DIGIT}+"."{DIGIT}*({E})? { yyparser.yylval.dval = new Double(yytext()); return (Parser.REAL); }    /* catches 1. */
\-?{DIGIT}*"."{DIGIT}+({E})? { yyparser.yylval.dval = new Double(yytext()); return (Parser.REAL); }    /* catches .01 */
\-?{DIGIT}+{E}         { yyparser.yylval.dval = new Double(yytext()); return (Parser.REAL); }

ABSOLUTE_USE           { return (Parser.TK_ABSOLUTE_USE); }
ALPHA                  { return (Parser.TK_ALPHA); }
ALPHA_SCALE_FACTOR_H   { return (Parser.TK_ALPHA_SCALE_H); }
ALPHA_SCALE_FACTOR_M   { return (Parser.TK_ALPHA_SCALE_M); }
AMP_END                { return (Parser.TK_AMP_END); }
AMP_START              { return (Parser.TK_AMP_START); }
ASCII                  { return (Parser.TK_ASCII); }
AUTO                   { return (Parser.TK_AUTO); }
AUGMENTATION_DELAY     { return (Parser.TK_AUGMENTATION_DELAY); }
AUGMENTATION_INIT      { return (Parser.TK_AUGMENTATION_INIT); }
AUGMENTATION_TAU       { return (Parser.TK_AUGMENTATION_TAU); }
AVERAGE_SYN            { return (Parser.TK_AVERAGE_SYN); }
BETA_SCALE_FACTOR_H    { return (Parser.TK_BETA_SCALE_H); }
BETA_SCALE_FACTOR_M    { return (Parser.TK_BETA_SCALE_M); }
BRAIN                  { return (Parser.TK_BRAIN); }
CA_EXP_FACTOR          { return (Parser.TK_CA_EXP); }
CA_EXP_GAUSS           { return (Parser.TK_CA_EXP); }
CA_EXP_RANGE           { return (Parser.TK_CA_EXP_RANGE); }
CA_EXTERNAL            { return (Parser.TK_CA_EXTERNAL); }
CA_HALF_MIN            { return (Parser.TK_CA_HALF_MIN); }
CA_INTERNAL            { return (Parser.TK_CA_INTERNAL); }
CA_SCALE_FACTOR        { return (Parser.TK_CA_SCALE); }
CA_SPIKE_INCREMENT     { return (Parser.TK_CA_SPIKE_INC ); }
CA_TAU                 { return (Parser.TK_CA_TAU); }
CA_TAU_DECAY           { return (Parser.TK_CA_TAU_DECAY); }
CA_TAU_SCALE_FACTOR    { return (Parser.TK_CA_TAU_SCALE); }
CELL                   { return (Parser.TK_CELL); }
CELL_SEQUENCE          { return (Parser.TK_CELL_SEQUENCE); }
CELLS                  { return (Parser.TK_CELLS); }
CELL_TYPE              { return (Parser.TK_CELL_TYPE); }
CHANNEL                { return (Parser.TK_CHANNEL); }
COLUMN                 { return (Parser.TK_COLUMN); }
COLUMN_SHELL           { return (Parser.TK_CSHELL); }
COLUMN_TYPE            { return (Parser.TK_COLUMN_TYPE); }
COMPARTMENT            { return (Parser.TK_CMP); }
MAX_CONDUCT            { return (Parser.TK_MAX_G); }
CONNECT                { return (Parser.TK_CONNECT); }
DATA_LABEL             { return (Parser.TK_DATA_LABEL); }
DELAY                  { return (Parser.TK_DELAY); }
DEPR_TAU               { return (Parser.TK_DEPR_TAU); }
DISTANCE               { return (Parser.TK_DISTANCE); }
DURATION               { return (Parser.TK_DURATION); }
END_BRAIN              { return (Parser.TK_END_BRAIN); }
END_CELL               { return (Parser.TK_END_CELL); }
END_CHANNEL            { return (Parser.TK_END_CHANNEL); }
END_COLUMN             { return (Parser.TK_END_COLUMN); }
END_COLUMN_SHELL       { return (Parser.TK_END_CSHELL); }
END_COMPARTMENT        { return (Parser.TK_END_CMP); }
END_EVENT              { return (Parser.TK_END_EVENT); }
END_LAYER              { return (Parser.TK_END_LAYER); }
END_LAYER_SHELL        { return (Parser.TK_END_LSHELL); }
END_REPORT             { return (Parser.TK_END_REPORT); }
END_SPIKESHAPE         { return (Parser.TK_END_SPIKE); }
END_STIMULUS           { return (Parser.TK_END_STIMULUS); }
END_STIMULUS_INJECT    { return (Parser.TK_END_ST_INJECT); }
END_SYNAPSE            { return (Parser.TK_END_SYNAPSE); }
END_SYN_AUGMENTATION   { return (Parser.TK_END_SYN_AUGMENTATION); }
END_SYN_DATA           { return (Parser.TK_END_SYN_DATA); }
END_SYN_FACIL_DEPRESS  { return (Parser.TK_END_SYN_FD); }
END_SYN_LEARNING       { return (Parser.TK_END_SYN_LEARN); }
END_SYN_PSG            { return (Parser.TK_END_SYN_PSG); }
EXP                    { return (Parser.TK_EXP); }
EVENT                  { return (Parser.TK_EVENT); }
E_HALF_MIN_H           { return (Parser.TK_E_HALF_MIN_H); }
E_HALF_MIN_M           { return (Parser.TK_E_HALF_MIN_M); }
FACIL_TAU              { return (Parser.TK_FACIL_TAU); }
FILENAME               { return (Parser.TK_FILENAME); }
FREQUENCY              { return (Parser.TK_FREQUENCY); }
FREQ_ROWS              { return (Parser.TK_FREQ_ROWS); }
FREQ_COLS              { return (Parser.TK_FREQ_ROWS); }
FREQ_START             { return (Parser.TK_FREQ_START); }
FSV                    { return (Parser.TK_FSV); }
HEBB_ON                { return (Parser.TK_HEBB_START); }
HEBB_OFF               { return (Parser.TK_HEBB_END); }
HEBB_START             { return (Parser.TK_HEBB_START); }
HEBB_END               { return (Parser.TK_HEBB_END); }
HEIGHT                 { return (Parser.TK_HEIGHT); }
HIDE_TIMESTEP          { return (Parser.TK_HIDE_TIMESTEP); }
H_INITIAL              { return (Parser.TK_H_INITIAL); }
H_POWER                { return (Parser.TK_H_POWER); }
IGNORE_EMPTY           { return (Parser.TK_IGNORE_EMPTY); }
INCLUDE                { yyparser.yylval.sval = yyparser.yytext; return (Parser.TK_INCLUDE); }
INJECT                 { return (Parser.TK_INJECT); }
INTERACTIVE            { return (Parser.TK_INTERACTIVE); }
LAYER                  { return (Parser.TK_LAYER); }
LAYER_SHELL            { return (Parser.TK_LSHELL); }
LAYER_TYPE             { return (Parser.TK_LAYER_TYPE); }
LEAK_CONDUCTANCE       { return (Parser.TK_LEAK_G ); }
LEAK_REVERSAL          { return (Parser.TK_LEAK_REVERSAL); }
LEARNING               { return (Parser.TK_LEARN); }
LEARNING_SHAPE         { return (Parser.TK_LEARN_SHAPE); }
LEARN_LABEL            { return (Parser.TK_LEARN_LABEL); }
LOAD                   { return (Parser.TK_LOAD); }
LOCATION               { return (Parser.TK_LOCATION); }
LOWER                  { return (Parser.TK_LOWER); }
MAX_AUGMENTATION       { return (Parser.TK_MAX_AUGMENTATION); }
MAX_G                  { return (Parser.TK_MAX_G); }
MODE                   { return (Parser.TK_MODE); }
M_INITIAL              { return (Parser.TK_M_INITIAL); }
M_POWER                { return (Parser.TK_M_POWER); }
NEG_HEB_PEAK_DELTA_USE { return (Parser.TK_NEG_HEB_PEAK_DELTA_USE); }
NEG_HEB_PEAK_TIME      { return (Parser.TK_NEG_HEB_PEAK_TIME); }
NEG_HEB_WINDOW         { return (Parser.TK_NEG_HEB_WINDOW); }
OUTPUT_CELLS           { return (Parser.TK_OUTPUT_CELLS); }
OUTPUT_CONNECT_MAP     { return (Parser.TK_OUTPUT_CONNECT_MAP); }
OPTION                 { return (Parser.TK_OPTION); }
PATTERN                { return (Parser.TK_PATTERN); }
PHASE_SHIFT            { return (Parser.TK_PHASE_SHIFT); }
POS_HEB_PEAK_DELTA_USE { return (Parser.TK_POS_HEB_PEAK_DELTA_USE); }
POS_HEB_PEAK_TIME      { return (Parser.TK_POS_HEB_PEAK_TIME); }
POS_HEB_WINDOW         { return (Parser.TK_POS_HEB_WINDOW); }
PREV_SPIKE_RANGE       { return (Parser.TK_PREV_SPIKE_RANGE); }
PROB                   { return (Parser.TK_PROB); }
RECURRENT_CONNECT      { return (Parser.TK_RECURRENT_CONNECT); }
RELOAD_SYN             { return (Parser.TK_RELOAD_SYN); }
REPORT                 { return (Parser.TK_REPORT); }
REPORT_ON              { return (Parser.TK_REPORT_ON); }
REVERSAL_POTENTIAL     { return (Parser.TK_REVERSAL); }
RSE                    { return (Parser.TK_RSE); }
RSE_LABEL              { return (Parser.TK_RSE_LABEL); }
RSE_INIT               { return (Parser.TK_RSE_INIT); }
R_MEMBRANE             { return (Parser.TK_R_MEMBRANE); }
SAMESEED               { return (Parser.TK_SAMESEED); }
SAVE                   { return (Parser.TK_SAVE); }
SAVE_SYN               { return (Parser.TK_SAVE_SYN); }
SEED                   { return (Parser.TK_SEED); }
SINGLE                 { return (Parser.TK_SINGLE); }
SELECT_FRONT           { return (Parser.TK_SELECT_FRONT); }
SERVER                 { return (Parser.TK_SERVER); }
SERVER_PORT            { return (Parser.TK_SERVER_PORT); }
SFD_START              { return (Parser.TK_SFD_START); }
SFD_END                { return (Parser.TK_SFD_END); }
SLOPE_FACTOR_H         { return (Parser.TK_SLOPE_H); }
SLOPE_FACTOR_H_STDEV   { return (Parser.TK_SLOPE_H_STDEV); }
SLOPE_FACTOR_M         { return (Parser.TK_SLOPE_M); }
SLOPE_FACTOR_M_STDEV   { return (Parser.TK_SLOPE_M_STDEV); }
SPIKESHAPE             { return (Parser.TK_SPIKE); }
SPIKE_HALFWIDTH        { return (Parser.TK_SPIKE_HW); }
STIMULUS               { return (Parser.TK_STIMULUS); }
STIMULUS_INJECT        { return (Parser.TK_ST_INJECT); }
STIM_TYPE              { return (Parser.TK_STIM_TYPE); }
STRENGTH               { return (Parser.TK_STRENGTH); }
STRENGTH_GAUSS         { return (Parser.TK_STRENGTH); }
STRENGTH_RANGE         { return (Parser.TK_STRENGTH_RANGE); }
SYNAPSE                { return (Parser.TK_SYNAPSE); }
SYNAPSE_RSE            { return (Parser.TK_SYNAPSE_RSE); }
SYNAPSE_UF             { return (Parser.TK_SYNAPSE_UF); }
SYN_AUGMENTATION       { return (Parser.TK_SYN_AUGMENTATION); }
SYN_CALCIUM            { return (Parser.TK_SYN_CALCIUM); }
SYN_DATA               { return (Parser.TK_SYN_DATA); }
SYN_FACIL_DEPRESS      { return (Parser.TK_SYN_FD); }
SYN_LEARNING           { return (Parser.TK_SYN_LEARN); }
SYN_PSG                { return (Parser.TK_SYN_PSG); }
SYN_REVERSAL           { return (Parser.TK_SYN_REVERSAL); }
TAU_MEMBRANE           { return (Parser.TK_TAU_MEMBRANE); }
TAU_SCALE_FACTOR_H     { return (Parser.TK_TAU_SCALE_H); }
TAU_SCALE_FACTOR_M     { return (Parser.TK_TAU_SCALE_M); }
THRESHOLD              { return (Parser.TK_THRESHOLD); }
TIME_END               { return (Parser.TK_TIME_END); }
TIME_START             { return (Parser.TK_TIME_START); }
TIMING                 { return (Parser.TK_TIMING); }
TYPE                   { return (Parser.TK_TYPE); }
UNITARY_G              { return (Parser.TK_UNITARY_G); }
UPPER                  { return (Parser.TK_UPPER); }
USE_OVERRIDE           { return (Parser.TK_OVERRIDE); }
VERT_TRANS             { return (Parser.TK_VERT_TRANS); }
VMREST                 { return (Parser.TK_VMREST); }
VOLTAGES               { return (Parser.TK_VOLTAGES); }
V_TAU_VALUE_H          { return (Parser.TK_VTAU_VAL_H); }
V_TAU_VOLTAGE_H        { return (Parser.TK_VTAU_VOLT_H); }
V_TAU_VALUE_M          { return (Parser.TK_VTAU_VAL_M); }
V_TAU_VOLTAGE_M        { return (Parser.TK_VTAU_VOLT_M); }
V_TAU_VALUE_H_STDEV    { return (Parser.TK_VAL_H_STDEV); }
V_TAU_VOLTAGE_H_STDEV  { return (Parser.TK_VOLT_H_STDEV); }
V_TAU_VALUE_M_STDEV    { return (Parser.TK_VAL_M_STDEV); }
V_TAU_VOLTAGE_M_STDEV  { return (Parser.TK_VOLT_M_STDEV); }
VERSION                { return (Parser.TK_VERSION); }
WARNINGS_OFF           { return (Parser.TK_WARNINGS_OFF); }
WIDTH                  { return (Parser.TK_WIDTH); }
SFD                    { return (Parser.TK_SFD); }
SFD_LABEL              { return (Parser.TK_SFD_LABEL); }
PSG_FILE               { return (Parser.TK_PSG_FILE); }
CELLS_PER_FREQ         { return (Parser.TK_CELLS_PER_FREQ); }
TIME_INCREMENT         { return (Parser.TK_TIME_FREQ_INC); }
DYN_RANGE              { return (Parser.TK_DYN_RANGE); }
PORT                   { return (Parser.TK_PORT); }
JOB                    { return (Parser.TK_JOB); }
DISTRIBUTE             { return (Parser.TK_DISTRIBUTE); }
CONNECT_REPORT         { return (Parser.TK_CONNECT_RPT); }
SPIKE_REPORT           { return (Parser.TK_SPIKE_RPT); }
RATE                   { return (Parser.TK_RATE); }
TAU_NOISE              { return (Parser.TK_TAU_NOISE); }
CORREL                 { return (Parser.TK_CORREL); }

Km           { yyparser.yylval.sval = yytext(); return (Parser.TK_Km); }
Kahp         { yyparser.yylval.sval = yytext(); return (Parser.TK_Kahp); }
Ka           { yyparser.yylval.sval = yytext(); return (Parser.TK_Ka); }
Na           { yyparser.yylval.sval = yytext(); return (Parser.TK_Na); }
Knicd        { yyparser.yylval.sval = yytext(); return (Parser.TK_Knicd); }

FALSE        { yyparser.yylval.ival = 0; return (Parser.LOGICAL); }
NO           { yyparser.yylval.ival = 0; return (Parser.LOGICAL); }
TRUE         { yyparser.yylval.ival = 1; return (Parser.LOGICAL); }
YES          { yyparser.yylval.ival = 1; return (Parser.LOGICAL); }


{NAME}       { yyparser.yylval.sval = yytext(); return (Parser.NAME); }

[ \t\x0d]+   { }

[\n]        { }
