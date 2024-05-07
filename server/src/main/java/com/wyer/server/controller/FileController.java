package com.wyer.server.controller;

import com.wyer.server.common.AuthAccess;
import com.wyer.server.common.Result;
import com.wyer.server.common.ShopAccess;
import com.wyer.server.common.UserAccess;
import com.wyer.server.utils.COSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Function: 文件接口类
 * Writer: wyer
 * Date: 2023/09/30 20:28
 **/
@RestController
@RequestMapping(value = "/file")
public class FileController {

    /**
     *服务器ip
     */
    @Value("${ip:localhost}")
    String ip;

    /**
     * 服务器端口
     */
    @Value("${server.port}")
    String port;

    /**
     * 服务器根路径
     */
    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";

    @Autowired
    private COSUtils cosUtils;

    private static final String COS_ROOT_PATH = "https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/";


    /**
     * 上传文件至服务器，返回url
     * @param file
     * @return url
     * @throws IOException
     */
    @AuthAccess
    @PostMapping(value = "/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename(); // 文件的原始名称
        int ptr = 0;
        for (int i = originalFilename.length() - 1; i >= 0; i--) {
            if (originalFilename.charAt(i) == '.') {
                ptr = i;
                break;
            }
        }
        String mainName = originalFilename.substring(0, ptr);
        String exName = originalFilename.substring(ptr + 1);
        File parentFile = new File(ROOT_PATH);
        if (!parentFile.exists()) { // 如果当前文件的父级目录不存在，就创建
            parentFile.mkdir();
        }
        File saveFile = new File(ROOT_PATH + File.separator + originalFilename);
        if (saveFile.exists()) { // 如果当前上传的文件已存在，重命名文件
            originalFilename = mainName + "_" + System.currentTimeMillis() + "." + exName;
            saveFile = new File(ROOT_PATH + File.separator + originalFilename);
        }
        file.transferTo(saveFile);
        String url = "http://" + ip + ":" + port + "/file/download/" + originalFilename;
        return Result.success(url); // 返回文件的下载地址
    }

    /**
     * 上传商家头像至服务器，返回url
     * @param file
     * @return url
     * @throws IOException
     */
    @ShopAccess
    @PostMapping(value = "/upload/avatar/shop")
    public Result shopAvatarUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename(); // 文件的原始名称
        int ptr = 0;
        for (int i = originalFilename.length() - 1; i >= 0; i--) {
            if (originalFilename.charAt(i) == '.') {
                ptr = i;
                break;
            }
        }
        String mainName = originalFilename.substring(0, ptr);
        String exName = originalFilename.substring(ptr + 1);
        File parentFile = new File(ROOT_PATH + File.separator + "shop_avatar");
        if (!parentFile.exists()) { // 如果当前文件的父级目录不存在，就创建
            parentFile.mkdir();
        }
        File saveFile = new File(ROOT_PATH + File.separator + "shop_avatar" + File.separator + originalFilename);
        if (saveFile.exists()) { // 如果当前上传的文件已存在，重命名文件
            originalFilename = mainName + "_" + System.currentTimeMillis() + "." + exName;
            saveFile = new File(ROOT_PATH + File.separator + "shop_avatar" + File.separator + originalFilename);
        }
        file.transferTo(saveFile);
        String path = cosUtils.uploadObject(saveFile, "shop_avatar/" + originalFilename);
        String url = COS_ROOT_PATH + path;
        return Result.success(url);
    }

    /**
     * 上传用户头像至服务器，返回url
     * @param file
     * @return url
     * @throws IOException
     */
    @UserAccess
    @PostMapping(value = "/upload/avatar/user")
    public Result userAvatarUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename(); // 文件的原始名称
        int ptr = 0;
        for (int i = originalFilename.length() - 1; i >= 0; i--) {
            if (originalFilename.charAt(i) == '.') {
                ptr = i;
                break;
            }
        }
        String mainName = originalFilename.substring(0, ptr);
        String exName = originalFilename.substring(ptr + 1);
        File parentFile = new File(ROOT_PATH + File.separator + "user_avatar");
        if (!parentFile.exists()) { // 如果当前文件的父级目录不存在，就创建
            parentFile.mkdir();
        }
        File saveFile = new File(ROOT_PATH + File.separator + "user_avatar" + File.separator + originalFilename);
        if (saveFile.exists()) { // 如果当前上传的文件已存在，重命名文件
            originalFilename = mainName + "_" + System.currentTimeMillis() + "." + exName;
            saveFile = new File(ROOT_PATH + File.separator + "user_avatar" + File.separator + originalFilename);
        }
        file.transferTo(saveFile);
        String path = cosUtils.uploadObject(saveFile, "user_avatar/" + originalFilename);
        String url = COS_ROOT_PATH + path;
        return Result.success(url);
    }

    /**
     * 上传商品图片至服务器，返回url
     * @param file
     * @return url
     * @throws IOException
     */
    @ShopAccess
    @PostMapping(value = "/upload/picture/goods")
    public Result goodsPictureUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename(); // 文件的原始名称
        int ptr = 0;
        for (int i = originalFilename.length() - 1; i >= 0; i--) {
            if (originalFilename.charAt(i) == '.') {
                ptr = i;
                break;
            }
        }
        String mainName = originalFilename.substring(0, ptr);
        String exName = originalFilename.substring(ptr + 1);
        File parentFile = new File(ROOT_PATH + File.separator + "goods_picture");
        if (!parentFile.exists()) {
            parentFile.mkdir();
        }
        File saveFile = new File(ROOT_PATH + File.separator + "goods_picture" + File.separator + originalFilename);
        if (saveFile.exists()) {
            originalFilename = mainName + "_" + System.currentTimeMillis() + "." + exName;
            saveFile = new File(ROOT_PATH + File.separator + "goods_picture" + File.separator + originalFilename);
        }
        file.transferTo(saveFile);
        String path = cosUtils.uploadObject(saveFile, "goods_picture/" + originalFilename);
        String url = COS_ROOT_PATH + path;
        return Result.success(url);
    }

    /**
     * 根据文件名从服务器下载文件
     * @param fileName
     * @param response
     * @throws IOException
     */
    @AuthAccess
    @GetMapping(value = "/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String filePath = ROOT_PATH + File.separator + fileName;
        if (!new File(filePath).exists()) {
            return;
        }
        byte[] bytes;
        File f = new File(filePath);

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length())) {
            BufferedInputStream in = null;
            in = new BufferedInputStream(Files.newInputStream(f.toPath()));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes); // 文件字节流数组
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 根据文件名从服务器下载商家头像
     * @param fileName
     * @param response
     * @throws IOException
     */
    @AuthAccess
    @GetMapping(value = "/download/avatar/shop/{fileName}")
    public void shopAvatarDownload(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String filePath = ROOT_PATH + File.separator + "shop_avatar" + File.separator + fileName;
        if (!new File(filePath).exists()) {
            return;
        }
        byte[] bytes;
        File f = new File(filePath);

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length())) {
            BufferedInputStream in = null;
            in = new BufferedInputStream(Files.newInputStream(f.toPath()));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes); // 文件字节流数组
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 根据文件名从服务器下载用户头像
     * @param fileName
     * @param response
     * @throws IOException
     */
    @AuthAccess
    @GetMapping(value = "/download/avatar/user/{fileName}")
    public void userAvatarDownload(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String filePath = ROOT_PATH + File.separator + "user_avatar" + File.separator + fileName;
        if (!new File(filePath).exists()) {
            return;
        }
        byte[] bytes;
        File f = new File(filePath);

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length())) {
            BufferedInputStream in = null;
            in = new BufferedInputStream(Files.newInputStream(f.toPath()));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes); // 文件字节流数组
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 根据文件名从服务器下载商品图片
     * @param fileName
     * @param response
     * @throws IOException
     */
    @AuthAccess
    @GetMapping(value = "/download/picture/goods/{fileName}")
    public void goodsPictureDownload(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String filePath = ROOT_PATH + File.separator + "goods_picture" + File.separator + fileName;
        if (!new File(filePath).exists()) {
            return;
        }
        byte[] bytes;
        File f = new File(filePath);

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length())) {
            BufferedInputStream in = null;
            in = new BufferedInputStream(Files.newInputStream(f.toPath()));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes); // 文件字节流数组
        outputStream.flush();
        outputStream.close();
    }
}
