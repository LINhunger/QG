package com.mediaplayer;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.InputStreamReader;  
import java.util.ArrayList;  
import java.util.List;  
  
public class MediaDaoImpl implements MediaDao {  
  
    public boolean isConvertFLV(String file) {  
        boolean result = false;  
        String ext = file.substring(file.lastIndexOf(".") + 1,     
                  file.length()).toLowerCase();  
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）   
        if (ext.equals("avi")) {    
            result = true;    
        } else if (ext.equals("mpg")) {    
            result = true;    
        } else if (ext.equals("wmv")) {    
            result = true;    
        } else if (ext.equals("3gp")) {    
            result = true;    
        } else if (ext.equals("mov")) {    
            result = true;    
        } else if (ext.equals("mp4")) {    
            result = true;    
        } else if (ext.equals("asf")) {    
            result = true;    
        } else if (ext.equals("asx")) {    
            result = true;     
        } else if (ext.equals("flv")) {    
            result = true;      
        }    
        return result;  
    }  
  
    public boolean isConvertAVI(String file) {  
        boolean result = false;  
        String ext = file.substring(file.lastIndexOf(".") + 1,     
                  file.length()).toLowerCase();  
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),    
        // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.    
        if (ext.equals("wmv9")) {    
            result = true;    
        } else if (ext.equals("rm")) {    
            result = true;   
        } else if (ext.equals("rmvb")) {    
            result = true;   
        }    
        return result;  
    }  
  
    public boolean executeCodecs(String srcFilePath, String codcFilePath,  
            String mediaPicPath) {  
        String basePath = System.getProperty("user.dir");  
        String mencoderPath = basePath+"ffmpeg\\mencoder.exe";  
        String ffmpegPath = basePath+"ffmpeg\\ffmpeg.exe";  
        System.out.println(mencoderPath);
        System.out.println(ffmpegPath);
        boolean mark = true;  
        String tempPath = basePath + File.separator + "temp" + File.separator + String.valueOf(System.currentTimeMillis())+ ".avi";  
        if(isConvertAVI(srcFilePath)){  
            mark = this.convertAVI(mencoderPath, srcFilePath, tempPath);  
            srcFilePath = tempPath;  
        }  
        if(isConvertFLV(srcFilePath) && mark){  
            mark = this.convertFLV(ffmpegPath, srcFilePath, codcFilePath);  
            mark = this.cutPic(ffmpegPath, srcFilePath, mediaPicPath);  
        }else{  
            System.out.println("该视频格式无法转换");  
            mark = false;  
        }  
        this.deleteAVIFile(tempPath);  
        return mark;  
    }  
      
    private boolean convertFLV(String ffmpegPath,String srcFilePath, String codcFilePath) {  
        File file = new File(ffmpegPath);  
        File srcFile = new File(srcFilePath);  
        if(file.exists()){  
            System.out.println("转换工具存在："+file);  
        }  
        if(srcFile.exists()){  
            System.out.println("源视频存在"+file);  
        }  
        // 创建一个List集合来保存转换视频文件为flv格式的命令  
        List<String> convert = new ArrayList<String>();  
        convert.add(ffmpegPath); // 添加转换工具路径  
        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件  
        convert.add(srcFilePath); // 添加要转换格式的视频文件的路径  
        convert.add("-ab");        //设置音频码率  
        convert.add("128");  
        convert.add("-ac");        //设置声道数  
        convert.add("2");  
        convert.add("-qscale");         
        convert.add("6");  
        convert.add("-ar");        //设置声音的采样频率  
        convert.add("22050");  
        convert.add("-r");        //设置帧频  
        convert.add("29.97");  
        convert.add("-b");  
        convert.add("5942.13");  
        convert.add("-s");  
        convert.add("1280x720");  
        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件  
        convert.add(codcFilePath);  
  
        boolean mark = true;  
        try {  
            Process proc = new ProcessBuilder(convert).redirectErrorStream(true).start();  
            BufferedReader stdout = new BufferedReader(    
                    new InputStreamReader(proc.getInputStream()));    
            String line;    
            while ((line = stdout.readLine()) != null) {    
                System.out.println(line);  
            }     
        } catch (Exception e) {  
            mark = false;  
            System.out.println(e);  
            e.printStackTrace();  
        }  
        return mark;  
    }  
      
    private Boolean cutPic(String ffmpegPath, String srcFilePath, String mediaPicPath) {  
        // 创建一个List集合来保存从视频中截取图片的命令  
        List<String> cutpic = new ArrayList<String>();  
        cutpic.add(ffmpegPath);  
        cutpic.add("-i");  
        cutpic.add(srcFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）  
        cutpic.add("-y");  
        cutpic.add("-f");  
        cutpic.add("image2");  
        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间  
        cutpic.add("7"); // 添加起始时间为第17秒  
        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间  
        cutpic.add("0.001"); // 添加持续时间为1毫秒  
        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小  
        cutpic.add("800*280"); // 添加截取的图片大小为350*240  
        cutpic.add(mediaPicPath); // 添加截取的图片的保存路径  
  
        boolean mark = true;  
        ProcessBuilder builder = new ProcessBuilder();  
        try {  
            builder.command(cutpic);  
            builder.redirectErrorStream(true);  
            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，  
            //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易  
            builder.start();  
        } catch (Exception e) {  
            mark = false;  
            System.out.println(e);  
            e.printStackTrace();  
        }  
        return mark;  
    }  
      
    private boolean convertAVI(String mencoderPath,String srcFilePath, String codcFilePath) {    
        List<String> commend = new ArrayList<String>();    
        commend.add(mencoderPath);    
        commend.add(srcFilePath);    
        commend.add("-oac");    
        commend.add("lavc");    
        commend.add("-lavcopts");    
        commend.add("acodec=mp3:abitrate=64");    
        commend.add("-ovc");    
        commend.add("xvid");    
        commend.add("-xvidencopts");    
        commend.add("bitrate=600");    
        commend.add("-of");    
        commend.add("avi");    
        commend.add("-o");    
        commend.add(codcFilePath);    
        try {    
            ProcessBuilder builder = new ProcessBuilder();    
            builder.command(commend);    
            builder.redirectErrorStream(true);//后续子进程错误输出与标准输出合并  
            Process p = builder.start();    
            p.getInputStream();  
            //后续进程等待Mencoder进程转换结束后才可进行  
            p.waitFor();   
            return true;  
        } catch (Exception e) {    
            e.printStackTrace();    
            return false;    
        }    
    }  
  
    public void deleteAVIFile(String tempFile) {  
        File file = new File(tempFile);  
        if(file.exists()){  
            file.delete();  
        }  
    }    
  
}  
