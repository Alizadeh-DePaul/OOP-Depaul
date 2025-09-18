/*
 *
 *  *
 *  *  * Copyright (c) 2024.
 *  *  * Vahid Alizadeh
 *  *  * Object-oriented Software Development
 *  *  * DePaul University
 *  *
 *
 */

package oopPrinciples.abstraction.discussion;

//Interface can inherit another interface
public class InterfaceInheritClassExample {
}
interface InterfaceAA {
    void showInterfaceAAMethod();
}
interface InterfaceBB {
    void showInterfaceBBMethod();
}
//Interface extending another interface
interface InterfaceCC extends InterfaceAA, InterfaceBB {

    void showInterfaceCCMethod();
}
class MySampleClass implements InterfaceCC {
// Now MySampleClass needs to implement methods from InterfaceAA,InterfaceBB, and InterfaceCC

    @Override
    public void showInterfaceAAMethod() {
        System.out.println("MySampleClass has implemented the showInterfaceAAMethod() method.");
    }
    @Override
    public void showInterfaceBBMethod() {
        System.out.println("MySampleClass has implemented the showInterfaceBBMethod() method.");
    }
    @Override
    public void showInterfaceCCMethod() {
        System.out.println("MySampleClass has implemented the showInterfaceCCMethod() method.");
    }
}
class DemoInterfaceInheritance {
    public static void main(String[] args) {
        System.out.println("***Demo Interface can extend other interfaces\n");
        //Creating a MySampleClass object
        MySampleClass mySampleClassOb = new MySampleClass();
        //Upcasting
//        Similar to: ((InterfaceAA) mySampleClassOb).showInterfaceAAMethod();

        InterfaceAA interAA = mySampleClassOb;
        interAA.showInterfaceAAMethod();

        InterfaceBB interBB = mySampleClassOb;
        interBB.showInterfaceBBMethod();

        InterfaceCC interCC = mySampleClassOb;
        interCC.showInterfaceBBMethod();

        //Calling directly through mySampleClassOb.
        System.out.println("\n**Now invoking the methods directly through a MyClass object.**\n");
        mySampleClassOb.showInterfaceAAMethod();
        mySampleClassOb.showInterfaceBBMethod();
        mySampleClassOb.showInterfaceCCMethod();
    }
}