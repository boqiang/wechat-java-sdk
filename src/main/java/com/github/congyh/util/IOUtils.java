package com.github.congyh.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 字节流, 字符流处理工具
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class IOUtils {
    /**
     * 字节输入流->字节数组
     *
     * @param in 字节输入流
     * @return 字节数组
     */
    public byte[] readAllBytes(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        final int BUFFER_SIZE = 1024;
        byte[] bytes = new byte[BUFFER_SIZE];
        int len = 0;
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        out.close();
        // 关闭了之后只是不能再进行写入, 仍可以进行其他操作.
        // ByteArrayOutputStream类型和其他OutputStream一样, 持有一个
        // byte[]类型的buffer, 会随着需要不断增长, toByteArray()操作实际上
        // 是对buffer调用Arrays.copyOf()方法缩减到实际大小
        return out.toByteArray();
    }
}
