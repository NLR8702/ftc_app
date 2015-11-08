package org.novalabs.robotics.techbytes;



/**
 * Created by tyler kim on 11/1/15.
 */
public class ColorAnalyzer{



   public ColorTypes Color_Result(float Red, float Blue, float Green, int Alpha) {

       // 1. Convert to percent
       float BluePercent = (Blue / (Blue + Red + Green));
       float RedPercent = ((Red / (Blue + Red + Green)));


       //2. compare
       // If Alpha is around 2600 or less, it does not detect any color
       if ((Alpha <= 2600)) {
           return ColorTypes.NOTHING;
       } else if ((RedPercent > BluePercent)) {
           // If the red percent is greater than the blue percent than the color should be red
           return ColorTypes.RED;
       } else {
           return ColorTypes.BLUE;
       }
   }
}


