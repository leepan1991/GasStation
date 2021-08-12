package com.volunteer.gasstation.configuration;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.volunteer.gasstation.core.BaseController;

import java.util.Scanner;

/**
 * 自动生成mybatisplus的相关代码
 * @author huoyao
 */
public class GeneratorCodeConfig {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器

        String projectPath = System.getProperty("user.dir");

        GlobalConfig gc = new GlobalConfig.Builder()
                .outputDir(projectPath + "/src/main/java")
                .author("huoyao")
                .openDir(false)
                .fileOverride()
                .build();

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig.Builder(
                "jdbc:mysql://rm-8vb2a3m0uuv11p771.mysql.zhangbei.rds.aliyuncs.com:3306/test?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "JiaoZi123"
        ).build();

        // 包配置
//        PackageConfig pc = new PackageConfig.Builder("com.volunteer", scanner("模块名称"))
        PackageConfig pc = new PackageConfig.Builder("com.volunteer.gasstation.manager", "biz")
                .controller("controller")
                .mapper("mapper")
                .entity("entity")
                .service("service")
                .serviceImpl("service.impl")
                .build();

        //模板
        TemplateConfig tc = new TemplateConfig.Builder().disable(TemplateType.CONTROLLER).build();

        //策略
        StrategyConfig sc = new StrategyConfig.Builder()
                .addTablePrefix("biz_")
                .likeTable(new LikeTable("biz_%"))
                .entityBuilder()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .enableLombok()
                .controllerBuilder()
                .superClass(BaseController.class)
                .enableRestStyle()
                .enableHyphenStyle()
                .build();

        AutoGenerator mpg = new AutoGenerator(dsc)
                .global(gc)
                .packageInfo(pc)
                .template(tc)
                .strategy(sc);

        mpg.execute();
    }
}