package org.smart4j.framework.mvc;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.FrameworkConstant;
import org.smart4j.framework.mvc.bean.Multipart;
import org.smart4j.framework.mvc.bean.Multiparts;
import org.smart4j.framework.mvc.bean.Params;
import org.smart4j.framework.mvc.fault.UploadException;
import org.smart4j.framework.util.FileUtil;
import org.smart4j.framework.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述 文件上传相关
 * @作者 liudelin
 * @日期 2017/9/19 10:55
 */
public class UploadHelper {

    private static final Logger logger = LoggerFactory.getLogger(UploadHelper.class);

    /**
     * FileUpload 对象（用于解析所上传的文件）
     */
    private static ServletFileUpload fileUpload;


    public static void init(ServletContext servletContext) {
        // 获取一个临时目录（使用 Tomcat 的 work 目录）
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        // 创建 FileUpload 对象
        fileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
        // 设置上传限制
        int uploadLimit = FrameworkConstant.UPLOAD_LIMIT;
        if (uploadLimit != 0) {
            fileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
        }
    }

    /**
     * 判断请求是否为 multipart 类型
     */
    public static boolean isMultipart(HttpServletRequest request) {
        return ServletFileUpload.isMultipartContent(request);
    }

    /**
     * 创建 multipart 请求参数列表
     */
    public static List<Object> createMultipartParamList(HttpServletRequest request) throws Exception {
        List<Object> paramList = new ArrayList<>();
        // 创建两个对象，分别对应 普通字段 与 文件字段
        Map<String, Object> fieldMap = new HashMap<>();
        List<Multipart> multipartList = new ArrayList<>();
        // 获取并遍历表单项
        List<FileItem> fileItemList;
        try {
            fileItemList = fileUpload.parseRequest(request);
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            throw new UploadException(e);
        }

        for (FileItem fileItem : fileItemList) {
            // 分两种情况处理表单项
            String fieldName = fileItem.getFieldName();
            if (fileItem.isFormField()) {
                // 处理普通字段
                String fileValue = fileItem.getString(FrameworkConstant.UTF_8);
                fieldMap.put(fieldName, fileValue);
            } else {
                // 处理文件字段
                String fileName = FileUtil.getRealFileName(fileItem.getName());
                if (StringUtil.isNotEmpty(fileName)) {
                    long fileSize = fileItem.getSize();
                    String contentType = fileItem.getContentType();
                    InputStream inputSteam = fileItem.getInputStream();
                    // 创建 Multipart 对象，并将其添加到 multipartList 中
                    Multipart multipart = new Multipart(fieldName, fileName, fileSize, contentType, inputSteam);
                    multipartList.add(multipart);
                }
            }
        }

        // 初始化参数列表
        paramList.add(new Params(fieldMap));
        paramList.add(new Multiparts(multipartList));
        // 返回参数列表
        return paramList;
    }
}
