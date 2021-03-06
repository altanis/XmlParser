package com.sl.xmlparser.image;

import com.sl.xmlparser.config.Configuration;
import com.sl.xmlparser.model.ProjectModel;
import sun.awt.X11.XKeySymConstants;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

public class ImageResizer {

    private static final Logger logger = Logger.getLogger(ImageResizer.class.getCanonicalName());
    private Configuration configuration;

    public ImageResizer(Configuration configuration) {
        this.configuration = configuration;
    }

    public void resizeImages(List<ProjectModel> listOfModels) throws IOException {
        File outputDirectory = new File(configuration.getOutputDirectory());

        if (!outputDirectory.exists()) {
            throw new FileNotFoundException("Output directory does not exist!");
        }

        for (int i = 0; i < listOfModels.size(); ++i) {
            File imgDirectory = new File(outputDirectory,
                    configuration.getOutputDirectoryPrefix() + Integer.toString(i));
            imgDirectory.mkdir();

            for (String imgMain : listOfModels.get(i).getImgMain()) {
                File src = new File(String.format(configuration.getImgMainUriTemplateContant(), imgMain));
                if (src.exists()) {
                    File dst = new File(imgDirectory, imgMain);
                    resizeAndSave(src.toURI(), dst, configuration.getOutputCropImgWidth(),
                            configuration.getOutputCropImgHeight(),
                            configuration.getOutputImgWidth(),
                            configuration.getOutputImgHeight(), configuration.getOutputImgMainCompressionRatio());
                } else {
                    logger.log(Level.WARNING, "Image {0} does not exist", src);
                }
            }

            for (String imgProjection : listOfModels.get(i).getImgProjection()) {
                File src = new File(String.format(configuration.getImgProjectionUriTemplateContant(), imgProjection));
                if (src.exists()) {
                    File dst = new File(imgDirectory, imgProjection);
                    resizeAndSave(src.toURI(), dst, configuration.getOutputCropImgWidth(),
                            configuration.getOutputCropImgHeight(),
                            configuration.getOutputImgWidth(),
                            configuration.getOutputImgHeight(), configuration.getOutputImgProjectionRatio());
                } else {
                    logger.log(Level.WARNING, "Image {0} does not exist", src);
                }
            }

            for (String imgLocation : listOfModels.get(i).getImgLocation()) {
                File src = new File(String.format(configuration.getImgLocationUriTemplateContant(), imgLocation));
                if (src.exists()) {
                    File dst = new File(imgDirectory, imgLocation);
                    resizeAndSave(src.toURI(), dst, configuration.getOutputCropImgWidth(),
                            configuration.getOutputCropImgHeight(),
                            configuration.getOutputImgWidth(),
                            configuration.getOutputImgHeight(), configuration.getOutputImgLocationCompressionRatio());
                } else {
                    logger.log(Level.WARNING, "Image {0} does not exist", src);
                }
            }

            for (String imgElevation : listOfModels.get(i).getImgElevation()) {
                File src = new File(String.format(configuration.getImgElevationUriTemplateContant(), imgElevation));
                if (src.exists()) {
                    File dst = new File(imgDirectory, imgElevation);
                    resizeAndSave(src.toURI(), dst, configuration.getOutputCropImgWidth(),
                            configuration.getOutputCropImgHeight(),
                            configuration.getOutputImgWidth(),
                            configuration.getOutputImgHeight(), configuration.getOutputImgElevationCompressionRatio());
                } else {
                    logger.log(Level.WARNING, "Image {0} does not exist", src);
                }
            }
        }
    }

    public void resizeAndSave(URI sourceUri, File destinationFile, int cropWidth,
                              int cropHeight, int scaledWidth, int scaledHeight,
                              float conpressionRatio) throws IOException {
        logger.log(Level.INFO,
                "Resizing image from URL {0} and saving to {1}. Resizing to {2} x {3}. Compression ratio: {4}",
                new Object[]{ sourceUri.toURL(), destinationFile, scaledWidth, scaledHeight, conpressionRatio });

        if (conpressionRatio >= 1.0f) {
            InputStream inStream = null;
            OutputStream outStream = null;
            try {
                inStream = sourceUri.toURL().openStream();
                outStream = new FileOutputStream(destinationFile);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);
                }
            } finally {
                if (inStream != null) {
                    inStream.close();
                }
                if (outStream != null) {
                    outStream.close();
                }
            }
        } else {

            BufferedImage sourceImg = ImageIO.read(sourceUri.toURL());

            if (scaledHeight < 0) {
                scaledHeight = sourceImg.getHeight();
            }

            if (scaledWidth < 0) {
                scaledWidth = sourceImg.getWidth();
            }

            BufferedImage destImg = createResizedCopy(sourceImg, cropWidth, cropHeight, scaledWidth, scaledHeight,
                    conpressionRatio,
                    true);

            ImageOutputStream imgOutStrm = ImageIO.createImageOutputStream(destinationFile);

            ImageWriter imgWrtr = ImageIO.getImageWritersByFormatName("jpg").next();
            imgWrtr.setOutput(imgOutStrm);
            ImageWriteParam jpgWrtPrm = imgWrtr.getDefaultWriteParam();
            jpgWrtPrm.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
            jpgWrtPrm.setCompressionQuality(conpressionRatio);
            jpgWrtPrm.setProgressiveMode(ImageWriteParam.MODE_COPY_FROM_METADATA);

            imgWrtr.write(null, new IIOImage(destImg, null, null), jpgWrtPrm);
        }
    }

    public BufferedImage createResizedCopy(BufferedImage originalImage, int cropWidth, int cropHeight,
                                           int scaledWidth, int scaledHeight, float compressionRatio,
                                           boolean preserveAlpha) {
        logger.log(Level.INFO, "Resizing image to {0} x {1}", new Object[]{ scaledWidth, scaledHeight });
        logger.log(Level.INFO, "Crop size image to {0} x {1}", new Object[]{ cropWidth, cropHeight });

        if (cropWidth > 0 || cropHeight > 0) {
            if(cropHeight > originalImage.getHeight()) {
                cropHeight = originalImage.getHeight();
            }
            if(cropWidth > originalImage.getWidth()) {
                cropWidth = originalImage.getWidth();
            }
            originalImage = originalImage.getSubimage(0, 0, cropWidth, cropHeight);
        }

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
