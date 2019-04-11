基于Jetty的文件服务器(Jetty内嵌的用法:http://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty)
PUT访问上传文件，GET方式获取文件，DELETE删除文件(必须是PUT方式才能上传文件，POST请求不会上传，文件名会采用URL的最后一部分，类似于Restful风格的请求)