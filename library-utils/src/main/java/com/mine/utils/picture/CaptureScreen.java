package com.mine.utils.picture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * ClassName: CaptureScreen
 * Description: 截屏处理awt
 * Created by feifei.liu on 2017/8/21 19:57
 **/
public class CaptureScreen {
    public static void captureScreen(String filePath) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write(image, "png", new File(filePath));
    }

    public static void main(String[] args) throws Exception {
        captureScreen("F:\\test.png");
    }
}
