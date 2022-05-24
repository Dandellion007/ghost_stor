<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <#include "parts/search.ftl">

    <div class = "card-group">
        <#list messages as message>
            <div class="col-md-6 col-xl-2">
                <div class="card me-3">
                    <#if message.filename??>
                        <img class="card-img-top" src="/static/doc.png">
                    </#if>
                    <div class="m-2">
                        <span><font size="2px"><strong>File name</strong> - ${message.name}</font>
                        </span><br>
                        <a href="/files/${message.filename}" download>Скачать</a><br>
                        <#if isAdmin>
                           <a href="/delete/${message.id}">Удалить</a>
                        </#if>
                    </div>
                    <div class="card-footer text-muted">
                        <strong>
                            <font size="1px">
                                Author - ${message.authorName}<br>
                                Desc - ${message.fileDesc}<br>
                            </font>
                        </strong>
                    </div>
                </div>
            </div>
        <#else>
            Не найдено файлов
        </#list>
    </div>
</@c.page>