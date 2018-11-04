/* This method is called to compress images, if the bigImg param is set to true the image will be compressed less */

package config;

import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import javax.imageio.stream.ImageOutputStream;

import createArticle.CreateArticleStatus;

public class ImageCompression {

    public static String ImageCompressionStart(String filePath, String newName, boolean bigImg) throws IOException {
	System.out.println("Starting Image Compression");
	 System.out.println(filePath);

	float quality = 0.8f;
	boolean loopAgain = false;
	int maxSize = 256000;

	if (bigImg) {
	    maxSize = 471859;
	}

	if ((new File(filePath).length()) < maxSize) {
	    CreateArticleStatus
		    .append("      Initial Compression (0.8% quality)...\n");
	    filePath = CompressLoop(filePath, newName, quality);

	    if ((new File(filePath).length()) > maxSize)
		loopAgain = true;

	    while (loopAgain) {
		quality = (quality - 0.1f);
		CreateArticleStatus.append("      Secondary Compression(" + quality + "% quality)...\n");
		filePath = CompressLoop(filePath, newName, quality);

		if ((new File(filePath).length()) <= maxSize)
		    loopAgain = false;

		if (quality < 0.1f) {
		    CreateArticleStatus
			    .append("\n[-] "
				    + newName
				    + ".jpg is too big and cannot be compressed any further,\n"
				    + "     please visit www.inkeep.org/info/image-compression/ for help.\n");
		    throw new IOException();
		}
	    }
	}

	return General.CutStringAfterSlash(filePath) + "/output." + newName + ".jpg";
    }

    private static String CompressLoop(String filePath, String newName,
	    float quality) {
	try {
	    File input = new File(filePath);
	    BufferedImage image = ImageIO.read(input);

	    File compressedImageFile = new File(
		    General.CutStringAfterSlash(filePath) + "/output." + newName + ".jpg");
	    OutputStream os = new FileOutputStream(compressedImageFile);

	    Iterator<ImageWriter> writers = ImageIO
		    .getImageWritersByFormatName("jpg");
	    ImageWriter writer = (ImageWriter) writers.next();

	    ImageOutputStream ios = ImageIO.createImageOutputStream(os);
	    writer.setOutput(ios);

	    ImageWriteParam param = writer.getDefaultWriteParam();
	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	    param.setCompressionQuality(quality);
	    writer.write(null, new IIOImage(image, null, null), param);
	    os.close();
	    ios.close();
	    writer.dispose();

	} catch (Exception e) {

	}
	return General.CutStringAfterSlash(filePath) + "/output." + newName + ".jpg";
    }
}