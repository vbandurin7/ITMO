// Generated from ./MyGrammar.g4 by ANTLR 4.13.1

package grammarParser;
import model.*;
import java.util.*;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.Token;
import java.util.Collections;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MyGrammarParser}.
 */
public interface MyGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MyGrammarParser#input_grammar}.
	 * @param ctx the parse tree
	 */
	void enterInput_grammar(MyGrammarParser.Input_grammarContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyGrammarParser#input_grammar}.
	 * @param ctx the parse tree
	 */
	void exitInput_grammar(MyGrammarParser.Input_grammarContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyGrammarParser#attributes_declaration}.
	 * @param ctx the parse tree
	 */
	void enterAttributes_declaration(MyGrammarParser.Attributes_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyGrammarParser#attributes_declaration}.
	 * @param ctx the parse tree
	 */
	void exitAttributes_declaration(MyGrammarParser.Attributes_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyGrammarParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(MyGrammarParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyGrammarParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(MyGrammarParser.RulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyGrammarParser#singleRule}.
	 * @param ctx the parse tree
	 */
	void enterSingleRule(MyGrammarParser.SingleRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyGrammarParser#singleRule}.
	 * @param ctx the parse tree
	 */
	void exitSingleRule(MyGrammarParser.SingleRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyGrammarParser#singleParserRule}.
	 * @param ctx the parse tree
	 */
	void enterSingleParserRule(MyGrammarParser.SingleParserRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyGrammarParser#singleParserRule}.
	 * @param ctx the parse tree
	 */
	void exitSingleParserRule(MyGrammarParser.SingleParserRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyGrammarParser#codeBlock}.
	 * @param ctx the parse tree
	 */
	void enterCodeBlock(MyGrammarParser.CodeBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyGrammarParser#codeBlock}.
	 * @param ctx the parse tree
	 */
	void exitCodeBlock(MyGrammarParser.CodeBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyGrammarParser#singleLexerRule}.
	 * @param ctx the parse tree
	 */
	void enterSingleLexerRule(MyGrammarParser.SingleLexerRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyGrammarParser#singleLexerRule}.
	 * @param ctx the parse tree
	 */
	void exitSingleLexerRule(MyGrammarParser.SingleLexerRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MyGrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MyGrammarParser.TypeContext ctx);
}