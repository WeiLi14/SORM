# SORM
该项目为简单实现ORM框架
通过实现一些简单的映射功能来进行实现ORM框架
测试devcommit
核心架构 
Query接口 ：负责查询
QueryFactory类：负责根据配置信息创建对应的类
TypeConvertor：负责类型转化
TableContext类:
DBManager
工具类：
1、jdbcUtils封装常用的JDBC操作。

核心Bean:封装相关数据
CoumnInof 封装表中的一个字段的信息
Configuration:封装配置文件信息你

注意点：1、目前只能处理只有一个主键的表
        2、定义Po包的时候尽量不要用基本数据类型，要用包装类 
        3、目前，只能处理自增主键的方式.
        
1、在SRC下建立db.properties的配置信息
2、项目编写流程
 2、1：数据库链接
 2、2：数据库表结构信息读取
 2、3：通过表信息拼装JAVA类源代码
 2、4：通过源代码创建JAVAbean源程序。
 2、5：创建基础的增删改查语句。


---2020620 优化。