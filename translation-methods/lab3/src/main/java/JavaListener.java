// Generated from .//Java.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JavaParser}.
 */
public interface JavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#modifyer}.
	 * @param ctx the parse tree
	 */
	void enterModifyer(JavaParser.ModifyerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#modifyer}.
	 * @param ctx the parse tree
	 */
	void exitModifyer(JavaParser.ModifyerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(JavaParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(JavaParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(JavaParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(JavaParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void enterConstructorBody(JavaParser.ConstructorBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#constructorBody}.
	 * @param ctx the parse tree
	 */
	void exitConstructorBody(JavaParser.ConstructorBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(JavaParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(JavaParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(JavaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(JavaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(JavaParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(JavaParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(JavaParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(JavaParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(JavaParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(JavaParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(JavaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(JavaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(JavaParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(JavaParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#fieldInit}.
	 * @param ctx the parse tree
	 */
	void enterFieldInit(JavaParser.FieldInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#fieldInit}.
	 * @param ctx the parse tree
	 */
	void exitFieldInit(JavaParser.FieldInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(JavaParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(JavaParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpression(JavaParser.BooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpression(JavaParser.BooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(JavaParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(JavaParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(JavaParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(JavaParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(JavaParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(JavaParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#callArgs}.
	 * @param ctx the parse tree
	 */
	void enterCallArgs(JavaParser.CallArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#callArgs}.
	 * @param ctx the parse tree
	 */
	void exitCallArgs(JavaParser.CallArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(JavaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(JavaParser.TypeContext ctx);
}