package com.jt.service;

import com.jt.util.FileUtil;
import com.jt.vo.ImageVO;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@PropertySource("classpath:/properties/images.properties") //容器动态加载指定的配置文件
public class FileServiceImpl implements FileService{

    //由于属性的值后期可能会发生变化,所以应该动态的获取属性数据. 利用pro配置文件
    @Value("${image.rootDirPath}")
    private String rootDirPath;             //   = "D:/JT-SOFT/images";
    @Value("${image.urlPath}")
    private String urlPath;                 // = "http://image.jt.com";
//
//    //1.2 准备图片的集合  包含了所有的图片类型.
//    private static Set<String> imageTypeSet;
//    static {
//        imageTypeSet = new HashSet<>();
//        imageTypeSet.add(".jpg");
//        imageTypeSet.add(".png");
//        imageTypeSet.add(".gif");
//    }


    /**
     * 完善的校验的过程
     * 1. 校验是否为图片
     * 2. 校验是否为恶意程序
     * 3. 防止文件数量太多,分目录存储.
     * 4. 防止文件重名
     * 5. 实现文件上传.
     * @param uploadFile
     * @return
     */
    @Override
    public ImageVO upload(MultipartFile uploadFile) {
        //0.防止有多余的空格 所以先做去空格的处理
        rootDirPath.trim();
        urlPath.trim();

        return FileUtil.fileUpload(uploadFile, rootDirPath, urlPath);
    }

    @Override
    public ImageVO uploads(MultipartFile[] uploadFile) {
        //0.防止有多余的空格 所以先做去空格的处理
        rootDirPath.trim();
        urlPath.trim();

        ImageVO uploadResult = null;
        for (MultipartFile multipartFile : uploadFile) {
            uploadResult = FileUtil.fileUpload(multipartFile, rootDirPath, urlPath);
            // 其中有一个上传失败，就返回失败
            if (uploadResult.getError() == 1){
                return uploadResult;
            }
        }
        return uploadResult;
    }
}
