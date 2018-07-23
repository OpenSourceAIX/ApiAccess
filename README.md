# ApiAccess

This is an *App Inventor extension*.
这是一个为App Inventor创建的拓展组件

Some work for getting access with [Baidu AI api](http://ai.baidu.com/docs#/Auth/top) and [Tencent Youtu](http://open.youtu.qq.com/#/develop/tool-authentication)

鉴于App Inventor的部分功能缺失，这里使用拓展来完成了[百度AI开放平台](http://ai.baidu.com/docs#/Auth/top)和[腾讯优图](http://open.youtu.qq.com/#/develop/tool-authentication)的鉴权工作（签名生成）

## [Release / 下载](https://github.com/OpenSourceAIX/ApiAccess/releases)

## Tutorial / 教程

### BaiduApiAccess

![](https://user-images.githubusercontent.com/22613139/43084739-dbaec7c8-8ecb-11e8-98b0-c45dc8d9e128.png)

Event / 事件

* GotAuth(String auth)
    * Called when got auth key from baidu server.
    * 百度服务器返回授权签名之后调用此事件

Functions / 函数(方法)

* GetAuth(String apiKey, String securetKey)
    * Call this to apply auth key and wait for it until receive the response.
    * 同步请求授权签名，会堵塞界面
* GetAuthAsync(String apiKey, String securetKey)
    * Call this to apply auth key, event GotAuth will be called when receive the response.
    * 异步请求授权签名，获得签名后会调用GotAuth事件

### YoutuApiAccess

![](https://user-images.githubusercontent.com/22613139/43084797-f68b4bd4-8ecb-11e8-89dc-90840f3b2dcf.png)

Functions / 函数(方法)

* Base64(String data)
* FormatString(String format, List arguments)
    * en - https://dzone.com/articles/java-string-format-examples
    * zh - https://blog.csdn.net/lonely_fireworks/article/details/7962171
    * example: This will return "Hi, Tom and Jerry"
        * ![](https://user-images.githubusercontent.com/22613139/43084831-07e68f1a-8ecc-11e8-98c2-e1548d623fb2.png)
* GetAuth(String appId, String secret_id, String secret_key)
    * Call this to calculate auth key.
    * 计算授权签名，本地操作，不会堵塞界面
* MD5(String data)