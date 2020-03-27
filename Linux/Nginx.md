## 一、实践环境：

系统版本：CentOS6.0 X86_64  
Nginx版本：Nginx-1.2.6  
Tomcat版本：Tomcat-6.0.18

## 二、Nginx安装：

实际环境中安装Nginx，首先需要安装pcre库，然后再安装Nginx： 
 
1. 安装pcre支持rewrite库,也可以安装源码，注*安装源码时，指定pcre路径为解压源码的路径，而不是编译后的路径，否则会报错。
    - yum install pcre-devel pcre -y
2. 下载Nginx源码包
    - cd /usr/src 
    - wget -c http://nginx.org/download/nginx-1.2.6.tar.gz
3. 解压Nginx源码包
    - tar -xzf nginx-1.2.6.tar.gz
4. 进入解压目录，然后sed修改Nginx版本信息为TDTWS
    - cd nginx-1.2.6 
    - sed -i -e 's/1.2.6//g' -e 's/nginx\//TDTWS/g' -e 's/"NGINX"/"TDTWS"/g' src/core/nginx.h
5. 预编译Nginx
    - ./configure --user=www --group=www --prefix=/usr/local/nginx --with-http_stub_status_module --with-http_ssl_module
6. configure预编译成功后，执行make命令进行编译
    - make -4j(添加-4j参数可以加快安装速度)
7. make执行成功后，执行make install 正式安装
    - make install
    
*自此Nginx安装完毕！！！*

## 三、配置Nginx：

1. 进入Nginx应用目录
   - cd /usr/local/nginx/conf
2. 备份原nginx.conf文件
   -  mv  nginx.conf  nginx.bak
3. 创建 nginx.conf ，并写入如下内容:
    - vim nginx.conf 
    ```
    #用户指定
    user www www;
    #Nginx进程数，建议按照CPU数目来制定，一般为它的倍数
    worker_processes 8; 
    #给每个进程分配CPU，上面配置为8，则有8个
    worker_cpu_affinity 00000001 00000010 00000100 00001000 00010000 00100000 01000000 10000000;
    #Nginx进程id
    pid /usr/local/nginx/nginx.pid;
    worker_rlimit_nofile 102400;
    events
    {
        #使用epoll的I/O模型，epoll是Linux内核为处理大批量文件描述符而做的改进的poll，他能显著提高程勋在在大量并发连接中只有少量活动情况下CPU的利用率
        use epoll;
        #每个进程允许的最大连接数，理论上每台Nginx服务器的最大连接数 = worker_processes * worker_connections
        worker_connections 102400;
    }
    http
    {
        include       mime.types;
        default_type  application/octet-stream;
        fastcgi_intercept_errors on;
        charset  utf-8;
        server_names_hash_bucket_size 128;
        #客户端请求头缓冲区大小
        client_header_buffer_size 4k;
        large_client_header_buffers 4 32k;
        client_max_body_size 300m;
        sendfile on;
        tcp_nopush     on;
        #超时时间
        keepalive_timeout 60;
        tcp_nodelay on;
        client_body_buffer_size  512k;
        proxy_connect_timeout    120;
        proxy_read_timeout       120;
        proxy_send_timeout       120;
        proxy_buffer_size        16k;
        proxy_buffers            4 64k;
        proxy_busy_buffers_size 128k;
        proxy_temp_file_write_size 128k;
        #静态访问必须打开，提高访问效率
        gzip on;
        gzip_min_length  1k;
        gzip_buffers     4 16k;
        gzip_http_version 1.1;
        gzip_comp_level 2;
        gzip_types       text/plain application/x-javascript text/css application/xml;
        gzip_vary on;
        ###2012-12-19 change nginx logs
        log_format  main  '$http_x_forwarded_for - $remote_user [$time_local] "$request"'
                            '$status $body_bytes_sent "$http_referer"'
                            '"$http_user_agent"  $request_time $remote_addr';
        #这里为后端服务器wugk应用集群配置，根据后端实际情况修改即可，tdt_wugk为负载均衡名称，可以任意指定
        #但必须跟vhosts.conf虚拟主机的pass段一致，否则不能转发后端的请求。
        upstream tdt_wugk {
            server   10.10.141.30:8080 weight=1max_fails=2fail_timeout=30s;
            server   10.10.141.30:8081 weight=1max_fails=2fail_timeout=30s;
            server   10.10.141.31:8080 weight=1max_fails=2fail_timeout=30s;
            server   10.10.141.31:8081 weight=1max_fails=2fail_timeout=30s;
            server   10.10.141.32:8080 weight=1max_fails=2fail_timeout=30s;
            server   10.10.141.32:8081 weight=1max_fails=2fail_timeout=30s;
        }
        #这里为后端APP应用负载均衡配置，根据后端实际情况修改即可。tdt_app为负载均衡名称，可以任意指定
        upstream tdt_app {
            server   10.10.141.40:8080 weight=1max_fails=2fail_timeout=30s;
            server   10.10.141.40:8081 weight=1max_fails=2fail_timeout=30s;
            server   10.10.141.41:8080 weight=1max_fails=2fail_timeout=30s;
            server   10.10.141.41:8081 weight=1max_fails=2fail_timeout=30s;
            server   10.10.141.42:8080 weight=1max_fails=2fail_timeout=30s;
            server   10.10.141.42:8081 weight=1max_fails=2fail_timeout=30s;
        }
        #include引用vhosts.conf，该文件主要用于配置Nginx 虚拟主机
        include vhosts.conf;
    }
    ``` 
    
 4. 至此，nginx.conf配置完毕,继续配置nginx虚拟主机，继续在当前目录创建vhosts.conf，为上面的include引用。创建并编辑：vhosts.conf文件，并加入以下内容：
    - vi vhosts.conf 
    
    ```
    ####www.wuguangke.cn
    server
    {
        listen       80;
        server_name  www.wuguangke.cn;
        index index.html index.htm;
         #配置发布目录为/data/www/wugk
        root  /data/www/wugk;
        location /
        {
            proxy_next_upstream http_502 http_504 error timeout invalid_header;
            proxy_set_header Host  $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_pass http://tdt_wugk;
            expires      3d;
        }
        #动态页面交给http://tdt_wugk，也即我们之前在nginx.conf定义的upstream tdt_wugk 均衡
        location ~ .*\.(php|jsp|cgi)?$
        {
            proxy_set_header Host  $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_pass http://tdt_wugk;
        }
        #配置Nginx动静分离，定义的静态页面直接从Nginx发布目录读取。
        location ~ .*\.(html|htm|gif|jpg|jpeg|bmp|png|ico|txt|js|css)$
        {
            root /data/www/wugk;
            #expires定义用户浏览器缓存的时间为3天，如果静态页面不常更新，可以设置更长，这样可以节省带宽和缓解服务器的压力
            expires      3d;
        }
        #定义Nginx输出日志的路径
        access_log  /data/logs/nginx_wugk/access.log main;
        error_log   /data/logs/nginx_wugk/error.log  crit;
    }
    ##########chinaapp.sinaapp.com 2012-12-19
    server
    {
        listen       80;
        server_name  chinaapp.sinaapp.com;
        index index.html index.htm;
        root  /data/www;
        location /
        {
            proxy_next_upstream http_502 http_504 error timeout invalid_header;
            proxy_set_header Host  $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_pass http://tdt_app;
            expires      3d;
        }
        location ~ .*\.(php|jsp|cgi)?$
        {
            proxy_set_header Host  $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_pass http://tdt_app;
        }
        location ~ .*\.(html|htm|gif|jpg|jpeg|bmp|png|ico|txt|js|css)$
        {
            root /data/www/app;
            expires      3d;
        }
        access_log  /data/logs/nginx_app/access.log main;
        error_log   /data/logs/nginx_app/error.log  crit;
    }
    ```


## 四、部署测试：
后端配置好Tomcat服务，并启动，发布的程序需同步到Nginx的/data/www对应的目录，因为配置动静分离后，用户请求你定义的静态页面，默认会去nginx的发布目录请求，而不会到后端请求，所以这时候你要保证后端跟前端的程序保持一致，可以使用Rsync做服务端自动同步或者使用NFS、MFS分布式共享存储。
1. 检查Nginx配置文件是否配置正确，提示Ok and successful表示正确，如下：

   - /usr/local/nginx/sbin/nginx -t  
    ```
    nginx: the configuration file /usr/local/nginx/conf/nginx.conf syntax is ok  
    nginx: configuration file /usr/local/nginx/conf/nginx.conf test is successful
    ```
        
2. 启动Nginx服务
    - /usr/local/nginx/sbin/nginx
    
3. 查看Nginx进程是否启动
    - ps -ef |grep nginx
    
    
### 附加：内核优化

1. 打开配置文件   
    - vi /etc/sysctl.conf
    
        + net.ipv4.tcp_max_tw_buckets = 10000