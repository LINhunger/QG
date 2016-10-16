package com.mediaplayer;

/** 
 *  
 * 功能说明：视频处理dao 
 * @author ljf  email: liangjf@hundsun.com   
 * @date 2014-10-14 上午10:32:31 
 * @version 2.0.0 
 * @since JDK1.6  
 * 
 */  
public interface MediaDao {  
      
    /** 
     * 视频转码 
     * @param srcFilePath  用于指定要转换格式的文件,要截图的视频源文件 
     * @param codcFilePath 格式转换后的的文件保存路径 
     * @param mediaPicPath 截图保存路径 
     * @return 
     */  
    public boolean executeCodecs(String srcFilePath, String codcFilePath, String mediaPicPath);  
      
    /** 
     * 可转换为FLV视频文件 
     * @param file 
     * @return 
     */  
    public boolean isConvertFLV(String file);  
      
    /** 
     * 可转换为AVI视频文件 
     * @param file 
     * @return 
     */  
    public boolean isConvertAVI(String file);  
      
    /** 
     * 删除中间转换视频文件 
     * @param tempFile 
     */  
    public void deleteAVIFile(String tempFile);  
}  
