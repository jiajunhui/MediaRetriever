package com.kk.taurus.mediadataretriever.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created by Taurus on 2017/12/7.
 */

public class MD5Util {

    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F' };

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * get string md5 message digest
     * @param str
     * @return
     */
    public static String md5(String str){
        return md5(new ByteArrayInputStream(str.getBytes()));
    }

    /**
     * get file md5 message digest.
     * @param file
     * @return
     */
    public static String md5(File file){
        try {
            InputStream inputStream = new FileInputStream(file);
            return md5(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get InputStream md5 message digest.
     * @param inputStream
     * @return
     */
    public static String md5(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        int numRead;
        MessageDigest md5;
        try{
            md5 = MessageDigest.getInstance("MD5");
            while((numRead=inputStream.read(buffer)) > 0) {
                md5.update(buffer,0,numRead);
            }
            inputStream.close();
            return toHexString(md5.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
