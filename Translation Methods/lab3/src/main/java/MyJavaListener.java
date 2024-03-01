import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;

public class MyJavaListener extends JavaBaseListener {
    private final static String TAB = "\t";
    private final Obfuscator obfuscator;
    private final TokenStreamRewriter rewriter;
    public int indent = 0;

    public MyJavaListener(CommonTokenStream tokens) {
        this.obfuscator = new Obfuscator();
        this.rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public void enterClassBody(JavaParser.ClassBodyContext ctx) {
        indent++;
    }

    @Override
    public void enterConstructorBody(JavaParser.ConstructorBodyContext ctx) {
        indent++;
    }

    @Override
    public void exitConstructorBody(JavaParser.ConstructorBodyContext ctx) {
        indent--;
    }

    @Override
    public void enterMethodBody(JavaParser.MethodBodyContext ctx) {
        indent++;
    }

    @Override
    public void exitMethodBody(JavaParser.MethodBodyContext ctx) {
        for (int i = 0; i < ctx.RETURN().size(); i++) {
            insertUselessStatementBefore(ctx, ctx.RETURN(i).getSymbol());
        }
        indent--;
    }

    @Override
    public void exitField(JavaParser.FieldContext ctx) {
        Token variable = ctx.ID().getSymbol();
        obfuscate(variable);
    }

    @Override
    public void exitExpression(JavaParser.ExpressionContext ctx) {
        if (ctx.ID() != null) {
            Token variable = ctx.ID().getSymbol();
            obfuscate(variable);
        }
    }

    @Override
    public void exitMethodCall(JavaParser.MethodCallContext ctx) {
        for (int i = 0; i < ctx.ID().size() - 1; i++) {
            Token variable = ctx.ID(i).getSymbol();
            obfuscate(variable);
        }
        insertUselessStatementAfter(ctx, ctx.SEMICOLON().getSymbol());
    }

    @Override
    public void exitAssignment(JavaParser.AssignmentContext ctx) {
        Token variable = ctx.ID().getSymbol();
        obfuscate(variable);
        if (ctx.SEMICOLON() != null) {
            insertUselessStatementAfter(ctx, ctx.SEMICOLON().getSymbol());
        }
    }

    @Override
    public void exitFieldInit(JavaParser.FieldInitContext ctx) {
        Token variable = ctx.ID().getSymbol();
        obfuscate(variable);
        insertUselessStatementAfter(ctx, ctx.SEMICOLON().getSymbol());
    }

    @Override
    public void exitArg(JavaParser.ArgContext ctx) {
        Token variable = ctx.ID().getSymbol();
        obfuscate(variable);
    }

    @Override
    public void exitCallArgs(JavaParser.CallArgsContext ctx) {
        for (int i = 0; i < ctx.ID().size(); i++) {
            Token variable = ctx.ID(i).getSymbol();
            obfuscate(variable);
        }
    }

    private void insertUselessStatementBefore(JavaParser.MethodBodyContext ctx, Token token) {
        rewriter.insertBefore(token,
                obfuscator.generateUselessExpression(indent) + System.lineSeparator() + TAB.repeat(indent));
    }

    private void insertUselessStatementAfter(ParserRuleContext ctx, Token token) {
        rewriter.insertAfter(token,
                System.lineSeparator() + TAB.repeat(indent) + obfuscator.generateUselessExpression(indent));
    }
    private void obfuscate(Token variable) {
        String obfuscated = obfuscator.obfuscateVariable(variable.getText());
        rewriter.replace(variable, obfuscated);
    }

    public String getObfuscatedResult() {
        return rewriter.getText();
    }
}
