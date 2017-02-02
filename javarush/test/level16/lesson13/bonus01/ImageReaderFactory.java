package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by admin on 07.04.16.
 */
public class ImageReaderFactory {
    public ImageReaderFactory() {
    }

    public static ImageReader getReader(ImageTypes type) throws IllegalArgumentException {
        if (type == ImageTypes.BMP) return new BmpReader();
        if (type == ImageTypes.JPG) return new JpgReader();
        if (type == ImageTypes.PNG) return new PngReader();
        throw new IllegalArgumentException();
    }
}
