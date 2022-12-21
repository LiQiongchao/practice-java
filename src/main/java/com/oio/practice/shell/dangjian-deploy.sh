
function deploy_service() {
    ## 后台部署流程
    source /etc/profile
    # 文件上传目录
    cd /opt/ftp/dangjian/dev
    unzip cmgapi.war -d cmgapi

    # 清除旧的 lib
    rm -rf /apps/dangjian/dev/tomcat-8081/webapps/bjzjzcmg/WEB-INF/lib
    # 把新的 lib 复制过去
    mv /opt/ftp/dangjian/dev/cmgapi/WEB-INF/lib /apps/dangjian/dev/tomcat-8081/webapps/bjzjzcmg/WEB-INF/
    # 删除解压包
    rm -rf /opt/ftp/dangjian/dev/cmgapi
    # 重启
    sh /apps/dangjian/dev/bin/restart.sh
}


function deploy_web() {
  source /etc/profile
  cd /opt/ftp/dangjian/dev/
  rm -rf /apps/dangjian/dev/tomcat-8081/webapps/bjzjzcmg/WEB-INF/classes/web
  tar -xf dist.tar -C /apps/dangjian/dev/tomcat-8081/webapps/bjzjzcmg/WEB-INF/classes
  mv /apps/dangjian/dev/tomcat-8081/webapps/bjzjzcmg/WEB-INF/classes/dist /apps/dangjian/dev/tomcat-8081/webapps/bjzjzcmg/WEB-INF/classes/web
  rm -f dist.tar
}

