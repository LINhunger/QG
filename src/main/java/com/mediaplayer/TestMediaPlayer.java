package com.mediaplayer;

public class TestMediaPlayer {
    public static void main(String[] args) {  
        //自定义方式产生文件名  
        String serialName = String.valueOf(System.currentTimeMillis());  
        String srcFilePath ="D:\\javaweb\\格式工厂\\输出文件夹\\video.mp4";  
        String codcFilePath = "D:\\javaweb\\格式工厂\\输出文件夹\\" + serialName + ".flv";  
        String mediaPicPath = "D:\\javaweb\\格式工厂\\输出文件夹\\" + serialName + ".jpg";  
        MediaDao mediaDao = new MediaDaoImpl();  
        mediaDao.executeCodecs(srcFilePath, codcFilePath, mediaPicPath);  
    } 
}
