package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class ImageVO {

    //{"error":0,"url":"图片的保存路径","width":图片的宽度,"height":图片的高度}
    private Integer error;  //错误信息   0程序运行正常    1.文件上传有误.
    private String url;     //图片访问的虚拟路径
    private Integer width;  // >0
    private Integer height; // >0

    //设定上传失败的方法
    public static ImageVO fail(){

        return new ImageVO(1,null,null,null);
    }

    public static ImageVO success(String url,Integer width,Integer height){

        return new ImageVO(0,url,width,height);
    }

    public ImageVO() {
    }

    public ImageVO(Integer error, String url, Integer width, Integer height) {
        this.error = error;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
