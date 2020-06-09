package cn.edu.usts.springbootpointmeterdetectionjavafx.tools;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.bytedeco.javacpp.BytePointer;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.IOException;

import static org.bytedeco.javacpp.opencv_core.Mat;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

public class ImageConvert {
    public static BufferedImage Mat2BufferedImage(Mat img) {
        int c = img.channels();
        int w = img.cols();
        int h = img.rows();
        int d = img.depth();

        /**
         *     -   CV_8U - 8-bit unsigned integers ( 0..255 )   0
         *     -   CV_8S - 8-bit signed integers ( -128..127 )  1
         *     -   CV_16U - 16-bit unsigned integers ( 0..65535 )   2
         *     -   CV_16S - 16-bit signed integers ( -32768..32767 )    3
         *     -   CV_32S - 32-bit signed integers ( -2147483648..2147483647 )  4
         *     -   CV_32F - 32-bit floating-point numbers ( -FLT_MAX..FLT_MAX, INF, NAN )   5
         *     -   CV_64F - 64-bit floating-point numbers ( -DBL_MAX..DBL_MAX, INF, NAN )   6
         */
        if (d == 0) {

            int tipo = 0;
            if (c == 1) {
                tipo = BufferedImage.TYPE_BYTE_GRAY;
            }

            if (c == 3) {
                tipo = BufferedImage.TYPE_3BYTE_BGR;
            }


            BufferedImage bufferImagem = new BufferedImage(w, h, tipo);
            WritableRaster raster = bufferImagem.getRaster();
            DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
            byte[] data = dataBuffer.getData();

            if (data.length == w * h * c * 1) {
                img.ptr().get(data);
                return bufferImagem;
            }
        }
        return null;
    }

    public static Image Mat2Image(Mat img) {
        BufferedImage bufferedImage = Mat2BufferedImage(img);
        return SwingFXUtils.toFXImage(bufferedImage, null);


    }

    public static void main(String[] args) throws IOException {
        Mat src = imread("F:/javafx.png");
        int c = src.channels();
        int w = src.cols();
        int h = src.rows();
        int d = src.depth();

        WritableImage image = new WritableImage(w, h);
        PixelWriter pixelWriter = image.getPixelWriter();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Color color = new Color(src.ptr(j, i, 0).get(), src.ptr(j, i, 1).get(), src.ptr(j, i, 2).get(), 1);
                pixelWriter.setColor(j, i, color);
            }
        }

        System.out.printf("c=%s, w=%s, h=%s, d=%s \n", c, w, h, d);
    }
}
