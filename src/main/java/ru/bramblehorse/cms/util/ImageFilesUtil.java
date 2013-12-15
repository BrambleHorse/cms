package ru.bramblehorse.cms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: bramblehorse
 * Date: 15.12.13
 * Time: 21:31
 * To change this template use File | Settings | File Templates.
 */
public class ImageFilesUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageFilesUtil.class);

    public static void deleteOrphanImage(String imageFilePath) {

        File fileToDelete = new File(imageFilePath);
        if(fileToDelete.exists()){

            if(fileToDelete.delete()){

                logger.info("Orphan image file deleted . .");
            }  else {

                logger.info("Can't delete orphan image file . .");
            }
        }  else {

            logger.info("Can't delete - no file in given path . .");
        }
    }
}
