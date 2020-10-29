package cup.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java_cup.runtime.Symbol;

public class Driver {
	static HashMap<Integer, String> tokenClass = new HashMap<Integer, String> (); 
	
	public static void initHash()
	{
		tokenClass.put(symbols.AND, "Keyword");
		tokenClass.put(symbols.ARRAY, "Keyword");
		tokenClass.put(symbols.BEGIN, "Keyword");
		tokenClass.put(symbols.BY, "Keyword");
		tokenClass.put(symbols.DIV, "Keyword");
		tokenClass.put(symbols.DO, "Keyword");
		tokenClass.put(symbols.ELSE, "Keyword");
		tokenClass.put(symbols.ELSIF, "Keyword");
		tokenClass.put(symbols.END, "Keyword");
		tokenClass.put(symbols.EXIT, "Keyword");
		tokenClass.put(symbols.FOR, "Keyword");
		tokenClass.put(symbols.IF, "Keyword");
		tokenClass.put(symbols.IS, "Keyword");
		tokenClass.put(symbols.LOOP, "Keyword");
		tokenClass.put(symbols.MOD, "Keyword");
		tokenClass.put(symbols.NOT, "Keyword");
		tokenClass.put(symbols.OF, "Keyword");
		tokenClass.put(symbols.OR, "Keyword");
		tokenClass.put(symbols.PROCEDURE, "Keyword");
		tokenClass.put(symbols.PROGRAM, "Keyword");
		tokenClass.put(symbols.READ, "Keyword");
		tokenClass.put(symbols.RECORD, "Keyword");
		tokenClass.put(symbols.RETURN, "Keyword");
		tokenClass.put(symbols.THEN, "Keyword");
		tokenClass.put(symbols.TO, "Keyword");
		tokenClass.put(symbols.TYPE, "Keyword");
		tokenClass.put(symbols.VAR, "Keyword");
		tokenClass.put(symbols.WHILE, "Keyword");
		tokenClass.put(symbols.WRITE, "Keyword");
		
		tokenClass.put(symbols.MINUS, "operator");
		tokenClass.put(symbols.PLUS, "operator");
		tokenClass.put(symbols.MULTIPLICATION, "operator");
		tokenClass.put(symbols.DIVISION, "operator");
		tokenClass.put(symbols.ASSIGN, "operator");
		tokenClass.put(symbols.LESSTHAN, "operator");
		tokenClass.put(symbols.LESSTHANEQUALTO, "operator");
		tokenClass.put(symbols.GREATERTHAN, "operator");
		tokenClass.put(symbols.GREATERTHANEQUALTO, "operator");
		tokenClass.put(symbols.EQUALS, "operator");
		tokenClass.put(symbols.LESSTHANGREATERTHAN, "operator");
		
		tokenClass.put(symbols.COLON, "delimiter");
		tokenClass.put(symbols.SEMICOLON, "delimiter");
		tokenClass.put(symbols.COMMA, "delimiter");
		tokenClass.put(symbols.DOT, "delimiter");
		tokenClass.put(symbols.LEFTPARENTHESES, "delimiter");
		tokenClass.put(symbols.RIGHTPARENTHESES, "delimiter");
		tokenClass.put(symbols.LEFTSQUAREBRACKET, "delimiter");
		tokenClass.put(symbols.RIGHTSQUAREBRACKET, "delimiter");
		tokenClass.put(symbols.LEFTCURLYBRACKET, "delimiter");
		tokenClass.put(symbols.RIGHTCURLYBRACKET, "delimiter");
		tokenClass.put(symbols.ATTRIBUTESLEFT, "delimiter");
		tokenClass.put(symbols.ATTRIBUTESRIGHT, "delimiter");
		
		tokenClass.put(symbols.INTEGER, "type");
        tokenClass.put(symbols.REAL, "type");
        tokenClass.put(symbols.STRING, "type");
		
		tokenClass.put(symbols.ID, "Identifier");
		tokenClass.put(symbols.NUMERIC_CONSTANT, "Number");
	}

	public static void main (String[] args) {
		
		Driver.initHash();
		
		FileReader inputFile;
		try {
			inputFile = new FileReader("input.txt");
			BufferedReader br = new BufferedReader(inputFile);
			Lexer l = new Lexer (br);
			
			try {
				Symbol sCrt;
				do 
				{
					sCrt = l.next_token();
										
					if (sCrt.sym != symbols.EOF)
					{
						System.out.println("Symbol value: "+ l.yytext() + " Class: " + Driver.tokenClass.get(sCrt.sym) + " line: " + sCrt.left + " column: " + sCrt.right);
					}
				}while(sCrt.sym != symbols.EOF);
				System.out.println("EOF");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}