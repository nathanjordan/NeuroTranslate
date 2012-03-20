all: Scanner.java Parser.java

Scanner.java: scan.jflex
	jflex scan.jflex

Parser.java: parse.yaccj
	byaccj -Jnoconstruct -Jnorun parse.yaccj

clean:
	rm -f Scanner.java* Parser.java* ParserVal.java*
