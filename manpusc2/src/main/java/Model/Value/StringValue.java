package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value{
    String str;

    public StringValue(String str) {
        this.str = str;
    }

    public String getVal() {
        return str;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public String toString() {return "\""+str+"\"";}

    @Override
    public Value deepCopy() {
        return new StringValue(str);
    }

    public boolean equals(Object obj) {return obj instanceof StringValue; }

}
