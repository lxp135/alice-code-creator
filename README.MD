# Alice CodeCreator

<p>
  <a href="#公众号"><img src="https://img.shields.io/badge/%E5%85%AC%E4%BC%97%E5%8F%B7-%E7%AB%B9%E6%A0%8F%E5%91%93%E8%AF%AD-green.svg" alt="公众号"></a>
</p>

## 前言

> 通用代码生成辅助工具
>
> 来聆听一首致爱丽丝，让我们在轻松愉悦的心情中编写代码

## 项目介绍

`Alice CodeCreator`是一个通用代码生成辅助工具，支持生成包括不限于html、js、java、xml、sql等等各种类型代码。

* 采用Velocity模板引擎构建并生成代码。
* 数据模型使用JDBC实现，提供数据类型映射配置页面。
* 支持模板分组，可以区分不同使用场景定义不同的分组。
* 灵活的全局级、分组级和模板级参数配置，适应多种场景。
* 支持网页版本的模板编辑器，可以代码高亮。
* 支持多数据源配置，可以通过页面动态接入数据源地址。
* 可以项目组级别部署，支持用户自主注册。
* 数据权限隔离，用户和用户之间的数据源配置和模板分组互不可见。
* 详细的项目文档，手把手级别。

## 项目文档

* [首页](https://creator.liuxp.me)
* [更新日志](https://creator.liuxp.me/released/)
* 用户手册
    - [快速开始](https://creator.liuxp.me/users/getting-started/)
    - [数据类型配置](https://creator.liuxp.me/users/data-type/)
    - [数据源配置](https://creator.liuxp.me/users/data-source/)
    - [模板配置](https://creator.liuxp.me/users/template/)
    - [生成代码](https://creator.liuxp.me/users/generator/)
* 开发者手册
    - [开发计划](https://creator.liuxp.me/developers/plan/)
    - [构建与运行](https://creator.liuxp.me/developers/building/)
    - [软件结构介绍](https://creator.liuxp.me/developers/framework/)
    - [贡献代码](https://creator.liuxp.me/developers/writing-code/)
    - [编写文档](https://creator.liuxp.me/developers/writing-documents/)
    - [贡献者列表](https://creator.liuxp.me/developers/contributors-of-documents/)
* 参考资料
    - [FAQ](https://creator.liuxp.me/references/FAQ/)
    
## Structure

- alice-code-creator 工程名称，端口9090。

## DataBase

- MySQL 5.7.28

## BackEnd
- SpringBoot 
- Spring Security
- SpringMVC
- Spring
- Mybatis

## FrontEnd

- jQuery 3.2.1 基础库
- director 1.2.7 前端hash路由，有改动
- jTemplates 0.8.4 前端模板引擎
- Bootstrap v4.2.1 前端UI基础库
- Beagle 1.7.0 Bootstrap4主题
- jqGrid 5.3.1 表格组件
- layer 3.1.1 Web弹层组件
- select2 4.0.5 下拉框组件
- zTree 3.5.36 树组件

## DevelopmentPlan

此处会收集好的Issues，作为下一步的开发计划。

### 新的前端界面
不再使用`Beagle`主题，该主题是`Bootstrap`收费主题，开源项目使用有一定风险，未来计划换掉它。

### 支持更多的模板引擎
目前代码生成规则使用的是`velocity`引擎，该引擎已经很古老了，估计很多年轻程序员都不太熟悉，未来计划提供多种模板引擎供选择，在配置模板时，可以选择对应的模板引擎。
预计支持以下模板引擎：

* `velocity`，当前已经支持。
* `FreeMarker`，当前还未支持。
* `Thymeleaf`，Spring Boot默认配置的模板引擎，语法简洁，功能强大。当前还未支持。

### 内置轻量级数据库
当前`Alice CodeCreator`本身的数据存储在`MySQL`数据库中，环境配置起来较为麻烦，未来计划提供一个嵌入式数据库的解决方案。
当用户只自己使用低并发时，可以通过配置切换为内置数据库方案，这样安装起来就方便很多。
目前在`SQLite`、`H2 database engine`和`MySQL Embedded`中选型，我个人倾向于`H2 database engine`。

### 多数据表一次生成并打包
目前在生成代码时，只能单表操作，每个数据表都要单独下载，当数据库存在很多表时，全部表初始化生成代码时操作较为繁琐。
计划支持数据表多选，可以一次性选择多张表进行生成并打包。

## 公众号

作者日常推送的技术&社评文章，关注公众号「**竹栏呓语**」第一时间获取。

加微信群交流，公众号后台回复「**加群**」即可。

![公众号图片](https://creator.liuxp.me/img/wechat.png)

## 许可证

[Apache License 2.0](https://github.com/lxp135/alice-code-creator/blob/master/LICENSE)

Copyright (c) 2020-2021 liuxp.me