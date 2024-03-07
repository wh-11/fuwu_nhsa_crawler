# fuwu_nhsa_crawler

1. fuwu.nhsa.gov.cn 页面内的数据爬取示例
2. 接口的入参、出参都使用了 sm2、sm4国密加密
3. 对应的加密解密都是通过js 实现，所以这里是java 调用js中的加密解密方法
4. 在jdk8中还包含由 `ScriptEngine` 的js引擎，在jdk9中需要单独引入依赖
5. java中的js引擎不支持部分的 `ES6` 语法，所以在执行过程中可能会报错。
6. 找到对应的报错信息，重写
