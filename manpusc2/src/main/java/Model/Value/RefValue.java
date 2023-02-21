package Model.Value;

import Model.Type.Type;
import Model.Type.RefType;
public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int i, Type inner) {
        address = i;
        locationType = inner;
    }

    public int getAddr() {return address;}
    @Override
    public Type getType() {
    return new RefType(locationType);}

    public Type getLocationType(){
        return locationType;
    }

    @Override
    public Value deepCopy() {
        return new RefValue(address,locationType);
    }
    @Override
    public String toString() {return "("+address+","+locationType.toString()+")";}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RefValue;
    }


}