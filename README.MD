# alice-code-creator

> 通用代码生成辅助工具
>
> 来聆听一首致爱丽丝，让我们在轻松愉悦的心情中编写代码

## 0.features

- 按照数据库表结构和模板生成代码
- 支持多用户数据隔离
- 支持模板分组，每个分组可配置独立的参数属性
- 支持分享，用户可以分享自己的模板分组给其他用户使用
- 支持多数据源配置，动态加载数据源
- 支持不同数据库（TODO，目前仅支持MySQL，未来会支持Oracle和SQLServer）

## 1.Structure

- alice-code-creator 工程名称，端口9090。

## 2.DataBase

- MySQL 5.7.28

## 3.BackEnd
- SpringBoot 
- Spring Security
- SpringMVC
- Spring
- Mybatis

## 4.FrontEnd

- jQuery 3.2.1 基础库
- director 1.2.7 前端hash路由，有改动
- jTemplates 0.8.4 前端模板引擎
- Bootstrap v4.2.1 前端UI基础库
- Beagle 1.7.0 Bootstrap4主题
- ECharts 4.1.0 图表组件
- jqGrid 5.3.1 表格组件
- layer 3.1.1 Web弹层组件
- select2 4.0.5 下拉框组件
- zTree 3.5.36 树组件