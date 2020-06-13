package com.sorm.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sorm.bean.ColumnInfo;
import com.sorm.bean.JavaFieldGetSet;
import com.sorm.bean.TableInfo;
import com.sorm.core.DBManager;
import com.sorm.core.MySqlTypeConvertor;
import com.sorm.core.TableContext;
import com.sorm.core.TypeConvertor;

/**
 * @ProjectName: SORM
 * @Package: com.sorm.utils
 * @ClassName: JAVAFileUtils
 * @Author: HAND_WEILI
 * @Description: 封装常用的java文件操作
 * @Date: 2020/6/10 12:56
 * @Version: 1.0
 */
public class JAVAFileUtils {

    // 反向生成代码

    /**
     * 根据字段信息生成JAVA属性信息。如 VAR USERNAME -- PIRVATGE sTIRNGF usename:以及相应的 getset方法
     *
     * @param columnInfo 字段信息
     * @param convertor  类型转换器
     * @return 返回java
     */
    public static JavaFieldGetSet creatFieldGetSetSRC(ColumnInfo columnInfo, TypeConvertor convertor) {
        JavaFieldGetSet jfgs = new JavaFieldGetSet();
        // 1、先生成属性。
        String javaFieldType = convertor.databaseType2JavaType(columnInfo.getDataType());

        jfgs.setFieldInfo("\tprivate " + javaFieldType + " " + columnInfo.getName() + ";\n");
        // 通过stringbuiilder进行拼接
        StringBuilder getSrc = new StringBuilder();
        // public int getName((){}
        getSrc.append("\tpublic " + javaFieldType + " get" + StringUtils.fristChar2UpperCase(columnInfo.getName()) + "(){\n");
        getSrc.append("\t\treturn " + columnInfo.getName() + ";\n");
        getSrc.append("\t}\n");
        jfgs.setGetInfo(getSrc.toString());

        StringBuilder setSrc = new StringBuilder();
        setSrc.append("\tpublic void set" + StringUtils.fristChar2UpperCase(columnInfo.getName()) + "(" + javaFieldType + " " + columnInfo.getName() + "){\n");
        setSrc.append("\t\tthis." + columnInfo.getName() + "=" + columnInfo.getName() + ";\n");
        setSrc.append("\t}\n");
        jfgs.setSetInfo(setSrc.toString());
        return jfgs;
    }

    /**
     * 根据表信息生成JAVA类的源代码
     *
     * @param tableInfo 表信息
     * @param convertor 类型转换器
     * @return 字符，到时候通过文件流的形式生成
     */
    public static String createJavaSrc(TableInfo tableInfo, TypeConvertor convertor) {
        //开始拼写源码。
        // 1、获取所有的列
        Map<String, ColumnInfo> columns = tableInfo.getColumns();
        List<JavaFieldGetSet> javaFields = new ArrayList<JavaFieldGetSet>();

        for (ColumnInfo c : columns.values()) {
            javaFields.add(creatFieldGetSetSRC(c, convertor));
        }

        StringBuilder src = new StringBuilder();

        // 2、生成 package语句
        src.append("package " + DBManager.getConf().getPoPackage() + ";\n");

        // 3、生成 import语句 ps：按道理此处应该根据有什么数据类型就加载什么包，但是为了省事就直接用sql包，和util包了。
        src.append("import java.sql.*;\n");
        src.append("import java.util.*;\n\n");

        // 4、生成类声明语句
        src.append("public class " + StringUtils.fristChar2UpperCase(tableInfo.getTname()) + " {\n");
        // 5、生成属性列表
        for (JavaFieldGetSet j : javaFields) {
            // 5、1将所有的属性拼接起来
            src.append(j.getFieldInfo());
        }
        // 6、生成get方法
        for (JavaFieldGetSet j : javaFields) {
            // 6、1将所有的get方法拼接起来
            src.append(j.getGetInfo());
        }

        // 7、生成set方法
        for (JavaFieldGetSet j : javaFields) {
            // 6、1将所有的get方法拼接起来
            src.append(j.getSetInfo());
        }

        src.append("}\n");
       // System.out.println(src);
        return src.toString();
    }

    /**
     * 通过源数据写文件到对应的包中
     *
     * @param tableInfo
     * @param convertor
     */
    public static void createJavaPOFile(TableInfo tableInfo, TypeConvertor convertor) {
        // 获取源码信息
        String src = createJavaSrc(tableInfo, new MySqlTypeConvertor());
        // 文件路径
        String srcPath = DBManager.getConf().getSrcPath() + "\\";
        // 包路径给你
        String packagePath = DBManager.getConf().getPoPackage().replace(".", "\\");
        File filedir = new File(srcPath + packagePath);
        File file = new File(srcPath + packagePath + "\\" + StringUtils.fristChar2UpperCase(tableInfo.getTname()) + ".java");

        System.out.println(file.getAbsolutePath() + "================================文件路径");

        if (!filedir.exists()) {
            // 如果不存在目录帮忙创建
            filedir.mkdir();
        }

     /*   if (!file.exists()) {
            // 如果不存在目录帮忙创建
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        // 创建单个文件 使用缓冲流加文件流按字符进行创建新的文件夹目标地址是
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(src);
            System.out.println("建立表"+tableInfo.getTname()+"映射类"+StringUtils.fristChar2UpperCase(tableInfo.getTname())+".java");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
      /* 测试单个字段的输出
        ColumnInfo columnInfo=new ColumnInfo("user","int",1);
        JavaFieldGetSet jfgs = JAVAFileUtils.creatFieldGetSetSRC(columnInfo,new MySqlTypeConvertor());
        System.out.println(jfgs.getFieldInfo());
        System.out.println(jfgs.getGetInfo());
        System.out.println(jfgs.getSetInfo());*/
        // 测试整个表字段的输出
        Map<String, TableInfo> tables = TableContext.tables;
        System.out.println(tables);
        // 获取整个表信息
        TableInfo tableInfo = tables.get("t_user");
        // 测试创建源代码
        // JAVAFileUtils.createJavaSrc(tableInfo,new MySqlTypeConvertor());
        // 测试创建文件
        //JAVAFileUtils.createJavaPOFile(tableInfo, new MySqlTypeConvertor());
        // 创建数据库的表
        MySqlTypeConvertor mySqlTypeConvertor = new MySqlTypeConvertor();

        for(TableInfo t :tables.values()){
            createJavaPOFile(t,mySqlTypeConvertor);
        }
    }
}
