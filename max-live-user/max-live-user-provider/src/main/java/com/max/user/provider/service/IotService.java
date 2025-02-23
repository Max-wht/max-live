package com.max.user.provider.service;

import com.max.dto.CheckSendBinDTO;
import com.max.dto.IOTBinFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.Socket;

/**
 * @author Max
 * @description
 * @date 2025/2/23 14:53
 */
@Service
public class IotService {

    Logger log = LoggerFactory.getLogger(IotService.class);
    public CheckSendBinDTO sendBin(IOTBinFile file) {
        log.info("开始发送文件");
        //使用try with resource创建InputStream 和 OutputStream和对应的buffered
        try(Socket socket = new Socket("127.0.0.1", 8888);//需要把ip改为树莓派的公网ip
            InputStream bis = new BufferedInputStream(new ByteArrayInputStream(file.getFileContent()));
            OutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ){
            //向服务器(树莓派)发送文件
            byte[] buffer = new byte[1024];
            int len;
            while((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
            //刷新内存缓冲
            bos.flush();
            //关闭socket Output通道
            socket.shutdownOutput();

            String line = br.readLine();
            log.info("树莓派返回信息：{}" , line);

            return new CheckSendBinDTO( true, line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
