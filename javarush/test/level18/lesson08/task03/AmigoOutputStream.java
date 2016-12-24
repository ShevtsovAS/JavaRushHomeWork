package com.javarush.test.level18.lesson08.task03;

import java.io.*;
import java.nio.channels.FileChannel;

/* AmigoOutputStream
1 Измените класс AmigoOutputStream так, чтобы он стал Wrapper-ом для класса FileOutputStream. Используйте наследование.
2 При вызове метода close() должны выполняться следующая последовательность действий:
2.1 вызвать метод flush()
2.2 дописать следующий текст [JavaRush © 2012-2013 All rights reserved.], используйте метод getBytes()
2.3 закрыть поток методом close()
*/

public class AmigoOutputStream extends FileOutputStream{
    public static String fileName = "C:/tmp/result.txt";
    private FileOutputStream fileOutputStream;

    public static void main(String[] args) throws IOException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

    public AmigoOutputStream(FileOutputStream fileOutputStream) throws FileNotFoundException {
        super(fileName);
        this.fileOutputStream = fileOutputStream;
    }

    @Override
    public void write(int b) throws IOException {
        this.fileOutputStream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.fileOutputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.fileOutputStream.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        this.fileOutputStream.flush();
        String data = "JavaRush © 2012-2013 All rights reserved.";
        write(data.getBytes());
        this.fileOutputStream.close();
    }

    @Override
    public FileChannel getChannel() {
        return this.fileOutputStream.getChannel();
    }

    @Override
    protected void finalize() throws IOException {
        super.finalize();
    }
}
