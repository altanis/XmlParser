package com.sl.xmlparser.com.sl.xmlparser.image;

import junit.framework.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ImageResizer {

    static class ImgHolder {
        int width;
        int height;
        long size;
        File path;
        BufferedImage img;

        private void flush() {
            if(img != null) {
                img.flush();
            }
        }

    }

    private com.sl.xmlparser.image.ImageResizer resizer = new com.sl.xmlparser.image.ImageResizer(null);

    @Test
    public void testFileExists() throws Exception {
        ImgHolder img = getTestImg(ImageResizer.class.getResource("/test_img.jpg"));
        File dest = null;
        try {
            dest = File.createTempFile("test", "jpg");
            resizer.resizeAndSave(img.path.toURI(), dest, -1, -1, -1, -1, 0.9f);

            Assert.assertTrue(img.size > dest.length());
        } finally {
            deleteSiletly(dest);
            img.flush();
        }
    }

    private void deleteSiletly(File f) {
        if(f != null && f.exists()) {
            f.deleteOnExit();
        }
    }


    private ImgHolder getTestImg(URL f) throws Exception {
        ImgHolder holder = new ImgHolder();
        holder.path = new File(f.getFile());
        holder.img = ImageIO.read(f);
        holder.width = holder.img.getWidth();
        holder.height = holder.img.getHeight();
        holder.size = holder.path.length();
        return holder;
    }

}
