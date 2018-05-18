package com.tainy.common.filter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author Tainy
 * @date 2018/5/16 14:33
 */
public class RequestWrapper extends HttpServletRequestWrapper{
    private final byte[] body; // 报文
    final static int BUFFER_SIZE = 4096;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = InputStreamTOByte(request.getInputStream());
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    // 将InputStream转换成byte数组
    public static byte[] InputStreamTOByte(InputStream in) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        byte[] data = new byte[BUFFER_SIZE];

        int count = -1;

        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1){
            outStream.write(data, 0, count);
        }

        data = null;

        return outStream.toByteArray();

    }
}
