package Model.Expression;

import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIHeap;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import Exception.MyException;

public class ArithExp implements Exp{
    Exp e1;
    Exp e2;
    int op;

    public ArithExp(int op, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer,Value>heap) throws MyException {
        Value v1,v2;
        v1=e1.eval(tbl,heap);
        if(v1.getType().equals(new IntType()))
        {
            v2=e2.eval(tbl,heap);
            if(v2.getType().equals(new IntType()))
            {
                IntValue i1= (IntValue) v1;
                IntValue i2= (IntValue) v2;
                int n1,n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op==1)  return new IntValue(n1+n2);
                if (op ==2)  return new IntValue(n1-n2);
                if(op==3)  return new IntValue(n1*n2);
                if(op==4)
                    if(n2!=0) return new IntValue(n1/n2);
                    else throw new MyException("Division by zero");
            }
            else
                throw new MyException("Second operand is not an integer");
        }
        else
            throw new MyException("First operand is not an integer");
        return null;
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typ1,typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if(typ1.equals(new IntType()))
        {
            if(typ2.equals(new IntType()))
            {
                return new IntType();
            }
            else
                throw new MyException("Second operand is not an integer");
        }
        else
            throw new MyException("First operand is not an integer");
    }


    @Override
    public String toString() {
        if (op==1) return e1.toString()+"+"+e2.toString();
        if (op==2) return e1.toString()+"-"+e2.toString();
        if (op==3) return e1.toString()+"*"+e2.toString();
        if (op==4) return e1.toString()+"/"+e2.toString();
        return null;
    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(op,e1.deepCopy(),e2.deepCopy());
    }

}
