package com.study.netty.iostudy.bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：songdalin
 * @date ：2021-09-22 下午 06:04
 * @description：bio测试
 * @modified By：
 * @version: 1.0
 */
public class BioTest {

    public static void main(String[] args) throws IOException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 100L, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(10));

        ServerSocket serverSocket = new ServerSocket(8081);

        while (true) {
            Socket socket = serverSocket.accept();

            executor.execute(() -> {
                try {

                    byte[] bytes = new byte[5];
                    InputStream inputStream = socket.getInputStream();
                    int read = 0;
                    while (true) {
                        read = inputStream.read(bytes, 0, bytes.length);
                        if (read == -1) {
                            System.out.println(Thread.currentThread() + "    " + new String(bytes));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }




    }
}
