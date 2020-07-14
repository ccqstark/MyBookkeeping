# 个人理财本项目——MyBookkeeping

## 描述

此项目作为一个基于JavaFX开发的桌面应用程序旨在为用户提供一个方便记录收入与支出的环境，数据保存在云服务器，与账号相关联，同时简约的GUI也方便了操作。更有数据分析图像功能，让用户清楚自己的消费情况，从而更好地进行理财管理。



## 功能分析

### 注册登录

<img src="https://github.com/ccqstark/images/blob/master/README/newuser.png" alt="image-20200714143952571" style="zoom: 80%;" />

新用户在这个界面进行注册，两次密码必须一致才能成功，否则会有错误提醒，最后点击**立即注册**就可以了。注册成功后会自动跳转到登录界面，如果已有账号可以点击**前往登录**按钮。

<img src="images\login.png" alt="image-20200714144323948" style="zoom:80%;" />

* 这个是登录界面，输入用户名和密码点击**登录**即可登录

* 用户名或密码错误会有提示

### 主界面

<img src="images\main.png" alt="image-20200714144547883" style="zoom: 67%;" />

* 登录成功来到**主界面**，有4个醒目简约的大按钮分别代表4个主要功能：**立即记账**、**我的账本**、**账户信息**和**统计图表**

* 点击对应按钮就能进入对应功能了



### 立即记账

<img src="images\newbill.png" alt="image-20200714145034802" style="zoom: 80%;" />

* 点击主界面的**立即记账**可以来到新建账单功能
* 点击**日期**输入框最右边的按钮可以在日期选择视图中选中日期，在下拉框中选择**收支类别**和**项目**，最后在输入框中输入**描述**和**金额**，填写完整才能点击**完成**新建账单
* 成功或者信息不全都会有**提示框**，点击**返回**或者**关闭窗口**可以回到主界面



### 我的账本

<img src="images\tableview.png" alt="image-20200714150548594" style="zoom:80%;" />

* 点击主界面**我的账本**可以来到账本功能，这里记录了你的全部账目，点击下面的**页码**可以翻页，点击**日期**或者**金额**栏目可以进行排序

* 左上角的**搜索框**可以输入关键字进行搜索相关账单记录，对所有字段都可以进行检索

* 鼠标点击账单记录可以选中，此时点击右上角**操作**按钮可以进行**修改**或者**删除**记录的操作
* 点击**刷新**按钮可以刷新到修改后的全部记录

* 关闭窗口回到主界面



### 账户信息

![image-20200714150834269](https://github.com/ccqstark/images/blob/master/README/accout.png)

* 点击主界面**账户信息**按钮来到此窗口，显示用户、头像和开发者等信息
* 点击**编辑**按钮可以唤出**文本编辑器**

* 点击**更换头像**可以唤出系统文件管理器选择自己的图片作为新头像

* 关闭窗口回到主界面



### 统计图表

<img src="images\graph.png" alt="image-20200714151224651" style="zoom: 67%;" />

* 点击主界面**统计图表**按钮可以来到这个界面，这里显示你各个项目花费占总金额的比例的**饼状图**
* 点击对应**色块**可以显示具体百分比
* 关闭窗口回到主界面



### 数据说明

<img src="images\mysql.png" alt="image-20200714151941707" style="zoom: 80%;" />

此应用采用MySQL作为数据库来存储数据，以上是单条记录的表结构



## 系统设计

### 项目结构介绍

<img src="images\pro.png" alt="image-20200714152340138" style="zoom: 80%;" />

#### 项目基本结构

**包**：main为主界面，userbase为注册登录，bkfunc是各个模块功能，DatabaseConnect是数据库连接

**类**：各个包下的类对应的功能都如图中命名所示，重点介绍bkfunc包下的**Bill**类，是用来存储每条记录的具体信息的。还有各个包下的**Controller**类是用来进行数据库操作的。

**资源**：图片资源都存放在images文件夹下

**其他**：lib文件夹存放JDBC连接MySQL所需依赖，out为输出的.class文件或者jar包



#### 核心功能的实现说明

##### 搜索功能

<img src="images\search.png" alt="image-20200714154043966" style="zoom:67%;" />

以上为搜索功能的实现源码，主要是对数据库使用LIKE语句对各个字段进行模糊匹配，最后将符合条件的记录存放到data数据存储结构中

##### 创建账单显示页面

<img src="images\table.png" alt="image-20200714154430288" style="zoom: 80%;" />

这2个函数主要创建分页页面和表格，并将数据加载入表格显示出来。设定的字段用格式工厂与存储数据的data（ObservableList\<Bill>类型）中的字段一一对应。



### 运行结果说明

本项目用Java13.0.2与JavaFX11开发，用java8进行打包成exe可执行文件时会出现窗口与控件比原来要小的情况，导致一些内容无法正常显示。其他具体功能的运行实现结果在上面第二大部分已经介绍过。

