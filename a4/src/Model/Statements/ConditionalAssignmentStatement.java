package Model.Statements;

import Model.Exceptions.EvaluationException;
import Model.Exceptions.ExecutionException;
import Model.Expressions.IExpression;
import Model.Expressions.RelationalExpression;
import Model.Expressions.VariableExpression;
import Model.State.PrgState;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.IType;
import Model.DataStructures.IDictionary;
import Model.DataStructures.IStack;

public class ConditionalAssignmentStatement implements IStatement {
    // v
    private final String variable;

    private final IExpression expression1;

    private final IExpression expression2;

    private final IExpression expression3;
    // what shall be executed


    public ConditionalAssignmentStatement(String variable, IExpression expression1, IExpression expression2, IExpression expression3) {
        this.variable = variable;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;

    }

    @Override
    public PrgState execute(PrgState state) throws EvaluationException {
        IStack<IStatement> exeStack = state.getExeStack();
        // converting
        IStatement converted = new IfStatement(expression1, new AssignStatement(variable, expression2), new AssignStatement(variable, expression3));

        exeStack.push(converted);
        state.exeStack = exeStack;
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws EvaluationException {
        IType variableType = new VariableExpression(variable).typeCheck(typeEnv);
        IType type3 = expression3.typeCheck(typeEnv);
        IType type2 = expression2.typeCheck(typeEnv);
        IType type1 = expression1.typeCheck(typeEnv);

        if (type1.equals(new BoolType()) && type2.equals(variableType) && type3.equals(variableType))
            return typeEnv;
        else
            throw new EvaluationException("conditional assignment  failed the type check!");
    }



    @Override
    public String toString() {
        return String.format("%s=(%s)? %s: %s", variable, expression1, expression2, expression3);
    }
}