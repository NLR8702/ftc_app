package org.novalabs.robotics.techbytes;



/**
 * Created by tyler kim on 11/1/15.
 */
public class ColorAnalyzer{
    public float[] getPercentage(float Red, float Blue, float Green){
        float BluePercent = (Blue / (Blue + Red + Green));
        float RedPercent = ((Red / (Blue + Red + Green)));
        float GreenPercent = ((Green / (Blue + Red + Green)));
        float[] percentArray = {BluePercent, RedPercent, GreenPercent};
        return percentArray;
    }



   public ColorTypes Color_Result(float Red, float Blue, float Green, int Alpha) {

       // 1. Convert to percent
       float BluePercent = (Blue / (Blue + Red + Green) * 100);
       float RedPercent = ((Red / (Blue + Red + Green) * 100));
       float GreenPercent = ((Green / (Blue + Red + Green) * 100));
       System.out.println(BluePercent);
       System.out.println(RedPercent);
       System.out.println(GreenPercent);

       //2. compare
       // If Alpha is around 2600 or less, it does not detect any color
       if ((RedPercent > BluePercent + 10) && (RedPercent > GreenPercent + 10)) {
           return ColorTypes.BLUE;
       } else if ((BluePercent > RedPercent + 10) && (BluePercent > GreenPercent +10)) {
           // If the red percent is greater than the blue percent than the color should be red
           return ColorTypes.RED;
       } else {
           return ColorTypes.NOTHING;
       }
   }
}


