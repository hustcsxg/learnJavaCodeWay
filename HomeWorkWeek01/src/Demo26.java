//26. 从网络下载一个图片到电脑的D:/ss。

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.*;


public class Demo26 {
    public static void main(String[] args) {
        DownLoadPicture d = new DownLoadPicture();
        d.saveImg("https://www.baidu.com/img/bd_logo1.png");
    }

}

class DownLoadPicture {
    final String TGT_DIR = "D:/ss";

    public void saveImg(String imgUrl) {
        try {
            //建立URL连接
            URL url = new URL(imgUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(2 * 1000);
            InputStream inStream = conn.getInputStream();
            byte[] data = readInputStream(inStream);
            File result = new File(TGT_DIR);
            if (!result.exists()) {
                result.mkdirs();
            }
            String fileName = getImgFileName(imgUrl);
            FileOutputStream outStream = new FileOutputStream(result + File.separator + fileName);
            outStream.write(data);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();

        return outStream.toByteArray();
    }


    private String getImgPath(String imgUrl) {
        return imgUrl.substring(imgUrl.indexOf("n/") + 1, imgUrl.lastIndexOf("/"));
    }

    /**
     * 根据url获取图片的名称
     */
    private String getImgFileName(String imgUrl) {
        return imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
    }
}