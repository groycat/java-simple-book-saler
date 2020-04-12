package cn.mayun.book.util;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {

    public static String uploadFile(HttpServletRequest req, MultipartFile file)
            throws FileNotFoundException {
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return "";
        }
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String filepath = ResourceUtils.getURL("classpath:").getPath() + "img" +
                File.separator + uuid + suffixName;
        System.out.println("save to:" + filepath);

        File dest = new File(filepath);
        if (!dest.getParentFile().exists()) {
            if (!dest.getParentFile().mkdirs()) {
                return "";
            }
        }

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        return "/img/" + uuid + suffixName;
    }

}
