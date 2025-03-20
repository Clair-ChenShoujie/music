package com.example.yin.constant;


/**
 * 歌曲图片，歌手图片，歌曲文件，歌单图片等文件的存放路径
 */
public class Constants {

    //ASSETS_PATH存储当前 Java 程序的工作目录,其他路径都由这个路径来找到
    public static String ASSETS_PATH = System.getProperty("user.dir");
    
    public static String AVATOR_IMAGES_PATH = "file:" + ASSETS_PATH + "/img/avatorImages/";
    public static String SONGLIST_PIC_PATH = "file:" + ASSETS_PATH + "/img/songListPic/";
    public static String SONG_PIC_PATH = "file:" + ASSETS_PATH + "/img/songPic/";
    public static String SONG_PATH = "file:" + ASSETS_PATH + "/song/";
    public static String SINGER_PIC_PATH = "file:" + ASSETS_PATH + "/img/singerPic/";
    public static String BANNER_PIC_PATH = "file:" + ASSETS_PATH + "/img/swiper/";

    public static String SPACE_PIC_PATH = "file:" + ASSETS_PATH + "/img/spacePic/";

    //盐值加密 配合md5加强密码安全性
    public static String SALT = "zyt";
}
