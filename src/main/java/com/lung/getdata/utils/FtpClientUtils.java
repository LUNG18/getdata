package com.lung.getdata.utils;

import com.jcraft.jsch.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class FtpClientUtils {

    private static ChannelSftp sftp;

    /**
     * 上传文件到sftp服务器
     * @param host 服务器的地址
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @param target_path 目标路径，即最后上传到服务器哪里
     * @param local_path 本地路径，即原始文件所在目录
     * @param filename 文件名---我是没改动文件名，需要改名的话可以加一个参数
     */
    public static void uploadFile(String host, int port, String username, String password, String target_path, String local_path, String filename) throws JSchException, SftpException, FileNotFoundException {
        JSch jSch = new JSch();
        Session sshSession = jSch.getSession(username,host,port);
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        sftp = (ChannelSftp) channel;

        sftp.cd(target_path);
        File file = new File(local_path + filename);// 此处filename为服务器上该文件的名字，可自行更改
        sftp.put(new FileInputStream(file), filename);

    }

}
