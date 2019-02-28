<h1>
    build rebuild 就可以实现不重新启动服务器修改ftl文件后生效。
</h1>

<#if username ??>
    <h1>欢迎用户,${username}</h1>
</#if>
<h1>欢迎用户,${username!"xxx"}</h1>
<h1>欢迎用户,${username?default("xxx")}</h1>
<h1>欢迎用户,${username?if_exists}</h1>
