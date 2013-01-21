package com.sl.xmlparser.image;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImageResizer {

    private static final Logger logger = Logger.getLogger(ImageResizer.class.getCanonicalName());

    public void resizeAndSave(URI sourceUri, File destinationFile, int scaledWidth, int scaledHeight) throws IOException {
        logger.log(Level.INFO, "Resizing image from URL {0} and saving to {1}. Resizing to {2} x {3}", new Object[]{sourceUri.toURL(), destinationFile, scaledWidth, scaledHeight});

        BufferedImage sourceImg = ImageIO.read(sourceUri.toURL());
        BufferedImage destImg = createResizedCopy(sourceImg, scaledWidth, scaledHeight, true);
        
        ImageIO.write(destImg, "JPG", destinationFile);
    }

    public BufferedImage createResizedCopy(Image originalImage,
            int scaledWidth, int scaledHeight,
            boolean preserveAlpha) {
        logger.log(Level.INFO, "Resizing image to {0} x {1}", new Object[]{scaledWidth, scaledHeight});
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}