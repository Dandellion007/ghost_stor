<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <div class="doc_field_block">
        <div class="doc_field_title">Название документа:</div>
        <div class="doc_field_value">${document.name}</div>
    </div>
    <div class="doc_field_block">
        <div class="doc_field_title">Описание документа:</div>
        <div class="doc_field_value">${document.fileDesc}</div>
    </div>
</@c.page>
