package org.nta.lessons.lesson7;



public class PluginImpl1 implements Plugin {
    public void doUseful() {
        System.out.println(" ALl right  1");
    }

    public static void main(String[] args) {
        System.out.println(PluginImpl1.class.getName());
    }
}
