package org.novalabs.robotics.techbytes.tests;

import org.novalabs.robotics.techbytes.ColorAnalyzer;

/**
 * Created by tylerkim on 11/1/15.
 */
public class SensorTest {

    public static void main(String[] test) {
        ColorAnalyzer analyzer = new ColorAnalyzer();
// color result array (Float "Red", Float "Blue", Float "Green", Float "Alpha")
       String test1  = analyzer.Color_Result(8600,5000,8500,20000); // Should be Red
        System.out.println("Test1: " + test1);
        String test2  = analyzer.Color_Result(5900,4800,6100,18000); // Should be Red
        System.out.println("Test2: " + test2);
        String test3  = analyzer.Color_Result(1500,2700,1800,6000); // Should be Blue
        System.out.println("Test3: " + test3);
        String test4  = analyzer.Color_Result(1600,2100,2000,7000); // Should be Blue
        System.out.println("Test4: " + test4);
        String test5 = analyzer.Color_Result(910,635,1000,2600); //Should be nothing
        System.out.println("Test5: " + test5);
        String test6 = analyzer.Color_Result(850,380,620,1800); //Should be nothing
        System.out.println("Test6: " + test6);

        //String test2  = analyzer.Color_Result(8600,5000,8500,2000);


    }
}
