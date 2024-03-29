package Domain.Exp;

import Domain.*;
import Domain.Type.IntType;
import Domain.Value.IntValue;
import Domain.Value.Value;

public class ArithExp implements Exp {
    private Exp e1;
    private Exp e2;
    private int op;

    public ArithExp(int op,Exp e1, Exp e2){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public Value eval(MyIDictionary<String, Value> symTbl) throws MyException {
        Value v1, v2;
        v1 = e1.eval(symTbl);
        if(v1.getType().equals(new IntType())){
            v2 = e2.eval(symTbl);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op == '+') return new IntValue(n1 + n2);
                if(op == '-') return new IntValue(n1 - n2);
                if(op == '*') return new IntValue(n1 * n2);
                if(op == '/')
                    if(n2 == 0) throw new MyException("division by zero");
                    else return new IntValue(n1 / n2);
            }
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");
        return new IntValue(0);
    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(op,e1.deepCopy(), e2.deepCopy());
    }

    public String toString(){
        String s = "";
        if(op == 1) s = e1.toString() + " + " + e2.toString();
        if(op == 2) s = e1.toString() + " - " + e2.toString();
        if(op == 3) s = e1.toString() + " * " + e2.toString();
        if(op == 4) s = e1.toString() + " / " + e2.toString();
        return s;
    }
}
