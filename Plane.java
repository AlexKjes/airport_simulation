package com.company;

public class Plane {

    // removes package from object toString() to make console output prettier
    @Override
    public String toString() {
        return "Plane" + "@" + Integer.toHexString(hashCode());
    }

}
