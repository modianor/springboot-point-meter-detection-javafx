package cn.edu.usts.springbootpointmeterdetectionjavafx.tools;

import cn.edu.usts.springbootpointmeterdetectionjavafx.pojo.Item;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.*;


public class Point_Meter_Detect {
    public static double calc_dist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x() - p2.x(), 2) + Math.pow(p1.y() - p2.y(), 2));
    }

    /**
     * 计算角度对应的温度
     *
     * @param degree 角度
     * @return 对应的温度
     */
    public static double calc_value(Double degree) {
        if (degree <= 225 && degree >= 0) {
            return 40 - degree * (60 / 180.0D);
        } else if (degree >= 315 && degree <= 360) {
            return 40 + (360 - degree) * (60 / 180.0D);
        } else {
            return 0;
        }
    }

    /**
     * 计算倾角功能
     *
     * @param p1     直线的一个端点
     * @param p2     直线的另一个端点
     * @param center 仪表盘的中心点
     * @return
     */
    public static double calc_angle(Point p1, Point p2, Point center) {
        // 确定指针的头部和尾部
        Point top, tail = null;
        if (calc_dist(p1, center) > calc_dist(p2, center)) {
            top = p1;
            tail = p2;
        } else {
            top = p2;
            tail = p1;
        }

        // 计算倾角
        double atan = -1;
        if (top.x() >= tail.x() && top.y() <= tail.y()) {
            atan = Math.atan((double) (tail.y() - top.y()) / (double) (top.x() - tail.x()));
            return Math.toDegrees(atan);
        } else if (top.x() < tail.x() && top.y() <= tail.y()) {
            atan = Math.atan((double) (tail.x() - top.x()) / (double) (tail.y() - top.y()));
            return 90 + Math.toDegrees(atan);
        } else if (top.x() < tail.x() && top.y() < tail.y()) {
            atan = Math.atan((double) (tail.y() - top.y()) / (double) (tail.x() - top.x()));
            return 180 + Math.toDegrees(atan);
        } else {
            atan = Math.atan((double) (top.x() - tail.x()) / (double) (tail.y() - top.y()));
            return 270 + Math.toDegrees(atan);
        }

    }

    /**
     * 开始识别图像方法
     *
     * @param filepath 图像的路径，注意：这里的路径内部不要包含中文，否则会读取失败！
     * @return 返回值为结果对象
     */
    public static Item do_detect(String filepath) {
        Mat src = imread(filepath);
        Mat gray = new Mat();
        Mat canny = new Mat();
        Mat dst = new Mat();

        // 灰度化和Canny边缘检测
        cvtColor(src, gray, COLOR_BGR2GRAY);
        Canny(gray, canny, 100, 200);

        // 二值化
        threshold(gray, dst, 125, 255, THRESH_BINARY_INV);
        // 均值滤波
        medianBlur(dst, dst, 5);

        // 霍夫圆检测
        Mat circles = new Mat();
        HoughCircles(dst, circles, HOUGH_GRADIENT, 1, 100, 440, 50, 0, 345);
        FloatPointer fp = new FloatPointer(circles.ptr());
        Point center = null;
        int radius = -1;
        // 画出圆心和圆
        for (int i = 0; i < circles.rows(); i++) {
            center = new Point((int) (fp.get(i * 3 + 0)), (int) (fp.get(i * 3 + 1)));
            radius = (int) (fp.get(i * 3 + 2));
            circle(src, center, 10, Scalar.GREEN, 2, 8, 0);
            circle(src, center, radius, Scalar.BLUE, 3, 8, 0);

        }

        // 选取ROI区域
        Rect rect = new Rect(center.x() - radius, center.y() - radius, 2 * radius, 2 * radius);
        Mat clock = src.apply(rect);
        canny = canny.apply(rect);
        dst = dst.apply(rect);

        // 霍夫直线检测
        Mat lines = new Mat();
        HoughLinesP(canny, lines, 1, CV_PI / 180, 50, 100, 10);

        IntPointer intPointer = new IntPointer(lines.ptr());
        boolean flag = true;
        // 遍历检测到的点对
        for (int i = 0; i < lines.rows(); i++) {
            float x1 = intPointer.get(i * 4 + 0);
            float y1 = intPointer.get(i * 4 + 1);
            float x2 = intPointer.get(i * 4 + 2);
            float y2 = intPointer.get(i * 4 + 3);

            Point start = new Point((int) x1, (int) y1);
            Point end = new Point((int) x2, (int) y2);
            double d1 = calc_dist(start, center);
            double d2 = calc_dist(end, center);
            // 这里设置好阈值
            if (d1 < 120 || d2 < 120) {
                if (flag) {
                    line(clock, start, end, Scalar.GREEN, 3, LINE_AA, 0);
                    flag = !flag;
                    // 画出指针的两点
                    circle(clock, start, 10, Scalar.RED, 2, 8, 0);
                    circle(clock, end, 10, Scalar.RED, 2, 8, 0);

                    // 释放内存
                    lines.release();
                    dst.release();
                    canny.release();
                    circles.release();
                    gray.release();


                    // 计算倾角
                    double angle = calc_angle(start, end, center);
                    // 计算对应的温度
                    double value = calc_value(angle);
                    // 构造结果对象，返回
                    String img_id = filepath.substring(filepath.lastIndexOf("\\") + 1);
                    // 格式化时间格式
                    Item item = Item.builder()
                            .imgId(img_id)
                            .angle(angle)
                            .value(value)
                            .date(new Date())
                            .src(src)
                            .build();
                    return item;
                }
                return null;
            }
        }
        return null;
    }

}
