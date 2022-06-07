<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="form-group mb-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group mb-3">
                <input class="form-control" maxlength="15" type="text" name="name" placeholder="Название документа" required
                       <#if document??> value="${document.name}" </#if>
                >
            </div>
            <div class="form-group mb-3">
                <input class="form-control" maxlength="25" type="text" name="fileDesc" placeholder="Описание документа"
                        <#if document??> value="${document.fileDesc}" </#if>
                >
            </div>
<#--            <div class="form-group mb-3">-->
<#--                <div class="custom-file">-->
<#--                    <input type="file" name="file" id="customFile" required>-->
<#--                    <label class="custom-file-label" for="customFile"></label>-->
<#--                </div>-->
<#--            </div>-->
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group mb-3">
                <button class="btn btn-primary" type="submit">Добавить</button>
            </div>
        </form>
    </div>
</@c.page>
