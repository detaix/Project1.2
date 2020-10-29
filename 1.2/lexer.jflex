package cup.example;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;
import java.lang.*;
import java.io.InputStreamReader;

%%

%unicode
%class Lexer
%public
%cup
%implements sym 
%line
%column
%{
//figuring out on which line and column each symbol is located
private Symbol symbol(int sym) {									
    return new Symbol(sym, yyline+1, yycolumn+1);
}														
private Symbol symbol(int sym, Object val) {                                            
   return new Symbol(sym, yyline+1, yycolumn+1, val);
}
private void error(String message) {
   System.out.println("Error at line "+ (yyline+1) + ", column " + (yycolumn+ 1)+ " : "+message);
}
%} 

Character = [^\r\n]

String = "\"" ~"\""

NumericConstant = [0-9]+

WhiteSpace = {LineEnd} | [ \t\f]

LineEnd = [\r\n]|\r\n
 
Comment = "(*" {Character}* "*)" {LineEnd}
 
Identifier = [:jletter:][:jletterdigit:]*
 
%%
<YYINITIAL> {
   
   
 
   /* Delimiters */
   ":" { return symbol(COLON); }
   ";" { return symbol(SEMICOLON); }
   "," { return symbol(COMMA); }
   "." { return symbol(DOT); } 
   "(" { return symbol(LEFTPARENTHESES); }
   ")" { return symbol(RIGHTPARENTHESES); }
   "[" { return symbol(LEFTSQUAREBRACKET); }
   "]" { return symbol(RIGHTSQUAREBRACKET); }
   "{" { return symbol(LEFTCURLYBRACKET); }
   "}" { return symbol(RIGHTCURLYBRACKET); }
   "[<" { return symbol(ATTRIBUTESLEFT); }
   ">]" { return symbol(ATTRIBUTESRIGHT); }
 
  /* Operators */
   ":=" { return symbol(ASSIGN); }
   "+" { return symbol(PLUS); }
   "-" { return symbol(MINUS);}
   "*" { return symbol(MULTIPLICATION); }
   "/" { return symbol(DIVISION); }
   "<" { return symbol(LESSTHAN); }
   "<=" { return symbol(LESSTHANOREQUALTO); }
   ">" { return symbol(GREATERTHAN); }
   ">=" { return symbol(GREATERTHANOREQUALTO); }
   "=" { return symbol(EQUALS); }
   "<>" { return symbol(LESSTHANGREATERTHAN); }

   
   /* Keywords */
   "AND"    { return symbol(AND); }
   "ARRAY"  { return symbol(ARRAY); }
   "BEGIN"  { return symbol(BEGIN); }
   "BY"     { return symbol(BY); }
   "DIV"    { return symbol(DIV); }
   "DO"     { return symbol(DO); }
   "ELSE"   { return symbol(ELSE); }
   "ELSIF"  { return symbol(ELSIF); }
   "END"    { return symbol(END); }
   "EXIT"   { return symbol(EXIT); }
   "FOR"    { return symbol(FOR); }
   "IF"     { return symbol(IF); }
   "IS"     { return symbol(IS); }
   "LOOP"   { return symbol(LOOP); }
   "MOD"    { return symbol(MOD); }
   "NOT"    { return symbol(NOT); }
   "OF"     { return symbol(OF); }
   "OR"     { return symbol(OR); }
   "PROCEDURE"  { return symbol(PROCEDURE); }
   "PROGRAM"  { return symbol(PROGRAM); }
   "READ"     { return symbol(READ); }
   "RECORD"   { return symbol(RECORD); }
   "RETURN"   { return symbol(RETURN); }
   "THEN"     { return symbol(THEN); }
   "TO"       { return symbol(TO); }
   "TYPE"     { return symbol(TYPE); }
   "VAR"      { return symbol(VAR); }
   "WHILE"    { return symbol(WHILE); }
   "WRITE"    { return symbol(WRITE); }
   
   "INTEGER"  { return symbol(INTEGER); }
   "REAL"     { return symbol(REAL); }
   "STRING"   { return symbol(STRING); }
 

   {Comment} {}
   {String}  { System.out.println("STRING"); }
   {Identifier} { return symbol(ID, yytext()); } 
   {NumericConstant} { return symbol(NUMERIC_CONSTANT, new Integer(Integer.parseInt(yytext()))); }
   {WhiteSpace} { /* Ignore */ }
 
 }
 
.|\n { System.out.println("ERROR");error(yytext()); }
