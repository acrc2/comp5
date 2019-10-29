package br.ufpe.cin.if688.minijava.visitor;
import br.ufpe.cin.if688.minijava.ast.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import br.ufpe.cin.if688.minijava.ANTLR.gParser;
import br.ufpe.cin.if688.minijava.ANTLR.gParser.ClassDeclarationContext;
import br.ufpe.cin.if688.minijava.ANTLR.gParser.ExpressionContext;
import br.ufpe.cin.if688.minijava.ANTLR.gParser.GoalContext;
import br.ufpe.cin.if688.minijava.ANTLR.gParser.IdentifierContext;
import br.ufpe.cin.if688.minijava.ANTLR.gParser.MainClassContext;
import br.ufpe.cin.if688.minijava.ANTLR.gParser.MethodDeclarationContext;
import br.ufpe.cin.if688.minijava.ANTLR.gParser.Number_literalContext;
import br.ufpe.cin.if688.minijava.ANTLR.gParser.StatementContext;
import br.ufpe.cin.if688.minijava.ANTLR.gParser.TypeContext;
import br.ufpe.cin.if688.minijava.ANTLR.gParser.VarDeclarationContext;

import br.ufpe.cin.if688.minijava.ANTLR.gVisitor;
import static java.lang.Integer.parseInt;

public class MiniJVisitor implements gVisitor<Object> {

    @Override
    public Object visit(ParseTree tree) {
        // TODO Auto-generated method stub
        return tree.accept(this);
    }

    @Override
    public Object visitChildren(RuleNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitTerminal(TerminalNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visitGoal(GoalContext ctx) {
        MainClass main = (MainClass) ctx.mainClass().accept(this);
        ClassDeclList cdl = new ClassDeclList();
        for (ClassDeclarationContext c : ctx.classDeclaration()) {
            cdl.addElement((ClassDecl) c.accept(this));
        }
        return new Program(main, cdl);
    }

    @Override
    public Object visitMainClass(MainClassContext ctx) {
        // TODO Auto-generated method stub
        Identifier i1 = (Identifier) ctx.identifier(0).accept(this);
        Identifier i2 = (Identifier) ctx.identifier(0).accept(this);
        Statement stm = (Statement) ctx.statement().accept(this);
        return new MainClass(i1, i2, stm);
    }

    @Override
    public Object visitClassDeclaration(ClassDeclarationContext ctx) {
        Identifier ident1 = (Identifier) ctx.identifier(0).accept(this);
        VarDeclList vdl = new VarDeclList();
        MethodDeclList mdl = new MethodDeclList();
        for (VarDeclarationContext vr : ctx.varDeclaration()) {
            vdl.addElement((VarDecl) vr.accept(this));
        }
        for (MethodDeclarationContext m : ctx.methodDeclaration()) {
            mdl.addElement((MethodDecl) m.accept(this));
        }
        if (ctx.identifier().size() == 1) {
            return new ClassDeclSimple(ident1, vdl, mdl);
        } else {
            Identifier ident2 = (Identifier) ctx.identifier(1).accept(this);
            return new ClassDeclExtends(ident1, ident2, vdl, mdl);
        }
    }

    @Override
    public Object visitVarDeclaration(VarDeclarationContext ctx) {
        Type dale = (Type) ctx.type().accept(this);
        Identifier indentindande = (Identifier) ctx.identifier().accept(this);
        return new VarDecl(dale, indentindande);
    }

    @Override
    public Object visitMethodDeclaration(MethodDeclarationContext ctx) {
        Type tipe = (Type) ctx.type(0).accept(this);
        Identifier identificadouro = (Identifier) ctx.identifier(0).accept(this);
        FormalList fl = new FormalList();
        VarDeclList vdl = new VarDeclList();
        StatementList sl = new StatementList();
        for (int i = 1; i < ctx.type().size(); i++) {
            fl.addElement(new Formal((Type) ctx.type(i).accept(this), (Identifier) ctx.identifier(i).accept(this)));
        }
        for (VarDeclarationContext vr : ctx.varDeclaration()) {
            vdl.addElement((VarDecl) vr.accept(this));
        }
        for (StatementContext sc : ctx.statement()) {
            sl.addElement((Statement) sc.accept(this));
        }
        Object aux = ctx.expression().accept(this);
        Exp expr;
        if (aux instanceof Exp) {
            expr = (Exp) ctx.expression().accept(this);
        } else {
            expr = new IdentifierExp(ctx.expression().getText());
        }
        return new MethodDecl(tipe, identificadouro, fl, vdl, sl, expr);
    }

    @Override
    public Object visitType(TypeContext ctx) {
        String dale = ctx.getText();
        if (dale.equals("boolean")) {
            return new BooleanType();
        } else if (dale.equals("int")) {
            return new IntegerType();
        } else if (dale.equals("int[]")) {
            return new IntArrayType();
        } else {
            return new IdentifierType(dale);
        }
    }

    @Override
    public Object visitStatement(StatementContext ctx) {
        String element = ctx.getStart().getText();

        if (element.equals("if")) {
            Object ayudante = ctx.expression(0).accept(this);
            Exp exp;
            if (ayudante instanceof Exp) {
                exp = (Exp) ctx.expression(0).accept(this);
            } else {
                exp = new IdentifierExp(ctx.expression(0).getText());
            }
            Statement stm = (Statement) ctx.statement(0).accept(this);
            Statement stm1 = (Statement) ctx.statement(1).accept(this);
            return new If(exp, stm, stm1);

        } else if (element.equals("while")) {
            Object ayudante = ctx.expression(0).accept(this);

            Exp exp;
            if (ayudante instanceof Exp) {
                exp = (Exp) ctx.expression(0).accept(this);
            } else {
                exp = new IdentifierExp(ctx.expression(0).getText());
            }

            Statement stm = (Statement) ctx.statement(0).accept(this);
            return new While(exp, stm);
        } else if (element.equals("{")) {
            StatementList stmList = new StatementList();
            for (StatementContext sc : ctx.statement()) {
                stmList.addElement((Statement) sc.accept(this));
            }
            return new Block(stmList);
        } else if (element.equals("System.out.println")) {
            Exp exp = (Exp) ctx.expression(0).accept(this);
            return new Print(exp);
        } else if (ctx.expression().size() > 1) {
            Identifier id = (Identifier) ctx.identifier().accept(this);
            Object ayudante = ctx.expression(0).accept(this);
            Exp exp1;

            if (ayudante instanceof Exp) {
                exp1 = (Exp) ctx.expression(0).accept(this);
            } else {
                exp1 = new IdentifierExp(ctx.expression(0).getText());
            }

            Object ayudante2 = ctx.expression(1).accept(this);
            Exp exp2;
            if (ayudante2 instanceof Exp) {
                exp2 = (Exp) ctx.expression(1).accept(this);
            } else {
                exp2 = new IdentifierExp(ctx.expression(1).getText());
            }
            return new ArrayAssign(id, exp1, exp2);
        } else {
            Identifier id = (Identifier) ctx.identifier().accept(this);
            Object ayudante = ctx.expression(0).accept(this);
            Exp exp1;
            if (ayudante instanceof Exp) {
                exp1 = (Exp) ctx.expression(0).accept(this);
            } else {
                exp1 = new IdentifierExp(ctx.expression(0).getText());
            }
            return new Assign(id, exp1);
        }

    }

    @Override
    public Object visitExpression(ExpressionContext ctx) {
        String init = ctx.getStart().getText();
        int sizeE = ctx.expression().size();
        int childrenCount = ctx.getChildCount();
        if (childrenCount >= 5 && ctx.getChild(1).getText().equals(".")) {
            Object ayudante = ctx.expression(0).accept(this);
            Exp exp;
            if (ayudante instanceof Exp) {
                exp = (Exp) ctx.expression(0).accept(this);
            } else {
                exp = new IdentifierExp(ctx.expression(0).getText());
            }
            Identifier id = (Identifier) ctx.identifier().accept(this);

            ExpList listExp = new ExpList();
            for (int i = 1; i < sizeE; i++) {
                Object ayudante1 = ctx.expression(i).accept(this);
                if (ayudante1 instanceof Exp) {
                    listExp.addElement((Exp) ctx.expression(i).accept(this));
                } else {
                    listExp.addElement(new IdentifierExp(ctx.expression(i).getText()));
                }

            }

            return new Call(exp, id, listExp);
        } else if (sizeE == 2) { // expression ( '&&' | '<' | '+' | '-' | '*' ) expression
            Object ayudante = ctx.expression(0).accept(this);
            Exp ex1;
            if (ayudante instanceof Exp) {
                ex1 = (Exp) ctx.expression(0).accept(this);
            } else {
                ex1 = new IdentifierExp(ctx.expression(0).getText());
            }
            Object ayudante1 = ctx.expression(1).accept(this);
            Exp ex2;
            if (ayudante1 instanceof Exp) {
                ex2 = (Exp) ctx.expression(1).accept(this);
            } else {
                ex2 = new IdentifierExp(ctx.expression(1).getText());
            }
            if (ctx.getChild(1).getText().equals("&&")) {
                return new And(ex1, ex2);
            } else if (ctx.getChild(1).getText().equals("<")) {
                return new LessThan(ex1, ex2);
            } else if (ctx.getChild(1).getText().equals("+")) {
                return new Plus(ex1, ex2);
            } else if (ctx.getChild(1).getText().equals("-")) {
                return new Minus(ex1, ex2);
            } else if (ctx.getChild(1).getText().equals("*")) {
                return new Times(ex1, ex2);
            } else {
                return new ArrayLookup(ex1, ex2);
            }
        } else if (sizeE == 1) {
            Object ayudante = ctx.expression(0).accept(this);
            Exp getExp;
            if (ayudante instanceof Exp) {
                getExp = (Exp) ctx.expression(0).accept(this);
            } else {
                getExp = new IdentifierExp(ctx.expression(0).getText());
            }
            if (init.equals("!")) {
                return new Not(getExp);
            } else if (init.equals("(")) {
                return getExp;
            } else if (init.equals("new")) {
                return new NewArray(getExp);
            } else {
                return new ArrayLength(getExp);
            }
        } else {
            if (init.equals("true")) {
                return new True();
            } else if (init.equals("false")) {
                return new False();
            } else if (init.equals("this")) {
                return new This();
            } else if (init.equals("new")) {
                return new NewObject((Identifier) ctx.identifier().accept(this));
            } else {
                if (init.matches("\\d+")) {
                    return new IntegerLiteral(Integer.parseInt(ctx.getStart().getText()));
                } else {
                    return ctx.identifier().accept(this);
                }
            }
        }
    }

    @Override
    public Object visitIdentifier(IdentifierContext ctx) {
        return new Identifier(ctx.IDENTIFIER().getText());
    }

    @Override
    public Object visitNumber_literal(Number_literalContext ctx) {
        return new IntegerLiteral(Integer.parseInt(ctx.Number().getText()));

    }

    
    
	

	

}
