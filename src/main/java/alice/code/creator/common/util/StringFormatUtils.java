package alice.code.creator.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 * @author contact@liuxp.me
 */
public class StringFormatUtils {

    /**
     * 首字母转小写
     * @param string 字符串
     * @return 转换后的字符串
     */
    private static String toLowerCaseFirstOne(String string){
        if(Character.isLowerCase(string.charAt(0)))
            return string;
        else
            return (new StringBuilder()).append(Character.toLowerCase(string.charAt(0))).append(string.substring(1)).toString();
    }

    /**
     * 首字母大写
     * @param string 字符串
     * @return 转换后的字符串
     */
    private static String toUpperCaseFirstOne(String string){
        if(Character.isUpperCase(string.charAt(0)))
            return string;
        else
            return (new StringBuilder()).append(Character.toUpperCase(string.charAt(0))).append(string.substring(1)).toString();
    }


    /**
     * 字符串驼峰形式转换（首字母小写）
     * @param string 要转换的字符串
     * @param segmentation 分割符
     * @return 转换后的字符串
     */
    public static String humpToLowerCaseFirstOne(String string, String segmentation){
        String[] args = StringUtils.split(string, segmentation);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<args.length; i++){
            if(i==0){
                sb.append(toLowerCaseFirstOne(StringUtils.lowerCase(args[i])));
            }else{
                sb.append(toUpperCaseFirstOne(StringUtils.lowerCase(args[i])));
            }
        }
        return sb.toString();
    }

    /**
     * 字符串驼峰形式转换（首字母大写）
     * @param string 要转换的字符串
     * @param segmentation 分割符
     * @return 转换后的字符串
     */
    public static String humpToUpperCaseFirstOne(String string, String segmentation){
        String[] args = StringUtils.split(string, segmentation);
        StringBuilder sb = new StringBuilder();
        for(String arg : args){
            sb.append(toUpperCaseFirstOne(StringUtils.lowerCase(arg)));
        }
        return sb.toString();
    }

    /**
     * 取得/分割路径
     * @param string 要转换的字符串
     * @param segmentation 分割符
     * @return 转换后的字符串
     */
    public static String htmlPath(String string, String segmentation){
        String[] args = StringUtils.split(string, segmentation);
        StringBuilder sb = new StringBuilder();
        for(String arg : args){
            sb.append("/");
            sb.append(arg.toLowerCase());
        }
        return sb.toString();
    }

    /**
     * 取得html文件名称
     * @param string 要转换的字符串
     * @param segmentation 分割符
     * @return html文件名称
     */
    public static String htmlFileName(String string, String segmentation){
        String[] args = StringUtils.split(string, segmentation);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<args.length;i++){
            sb.append(args[i].toLowerCase());
            sb.append("_");
        }
        return sb.toString();
    }

    /**
     * 取得以下划线分割的首个单词（小写）
     * @param string 表名
     * @return 首个单词（小写）
     */
    public static String getFirstOne(String string){
        return string.split("_")[0].toLowerCase();
    }

}