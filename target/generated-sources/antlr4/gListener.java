// Generated from g.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link gParser}.
 */
public interface gListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link gParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(gParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(gParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void enterMainClass(gParser.MainClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void exitMainClass(gParser.MainClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(gParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(gParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(gParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(gParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(gParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(gParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(gParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(gParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(gParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(gParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(gParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(gParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(gParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(gParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link gParser#number_literal}.
	 * @param ctx the parse tree
	 */
	void enterNumber_literal(gParser.Number_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link gParser#number_literal}.
	 * @param ctx the parse tree
	 */
	void exitNumber_literal(gParser.Number_literalContext ctx);
}