package circletextimage.viviant.com.circletextimagelib.util;


import java.util.ArrayList;
import java.util.List;

public class CircleTextImageUtil {
    private static class CircleTextImageUtilHolder {
        private static final CircleTextImageUtil INSTANCE = new CircleTextImageUtil();
    }
    private CircleTextImageUtil (){}
    public static final CircleTextImageUtil getInstance() {
        return CircleTextImageUtil.CircleTextImageUtilHolder.INSTANCE;
    }

    /**
     * Get the random color.
     * @return
     */
    public static String getRandomColor() {
        List<String> colorList = new ArrayList<String>();
        colorList.add("#303F9F");
        colorList.add("#FF4081");
        colorList.add("#59dbe0");
        colorList.add("#f57f68");
        colorList.add("#87d288");
        colorList.add("#f8b552");
        colorList.add("#990099");
        colorList.add("#90a4ae");
        colorList.add("#7baaf7");
        colorList.add("#4dd0e1");
        colorList.add("#4db6ac");
        colorList.add("#aed581");
        colorList.add("#fdd835");
        colorList.add("#f2a600");
        colorList.add("#ff8a65");
        colorList.add("#f48fb1");
        colorList.add("#7986cb");
        colorList.add("#FFFFE0");
        colorList.add("#ADD8E6");
        colorList.add("#DEB887");
        colorList.add("#C0C0C0");
        colorList.add("#AFEEEE");
        colorList.add("#F0FFF0");
        colorList.add("#FF69B4");
        colorList.add("#FFE4B5");
        colorList.add("#FFE4E1");
        colorList.add("#FFEBCD");
        colorList.add("#FFEFD5");
        colorList.add("#FFF0F5");
        colorList.add("#FFF5EE");
        colorList.add("#FFF8DC");
        colorList.add("#FFFACD");

        return colorList.get((int)(Math.random() * colorList.size()));
    }


    /**
     * Interception of the first string of characters.
     * @param str
     * @return
     */
    public static String subFirstCharacter(String str) {
        if (Character.isLetter(str.charAt(0))) {
            return Character.toUpperCase(str.charAt(0))+"";
        } else {
            return str.charAt(0) +"";
        }
    }

}
