<div class="form-group">
    <form class="form-inline" method="get" action=${formAction!}>
        <input class="form-control mb-2 search_field" type="text" name="nameFilter" value="${nameFilter!}" placeholder="Название">
        <input class="form-control mb-2 search_field"  type="text" name="descFilter" value="${descFilter!}" placeholder="Описание">
        <input class="form-control mb-2 search_field"  type="text" name="codeNameFilter" value="${codeNameFilter!}" placeholder="Обозначение стандарта">
        <input class="form-control mb-2 search_field"  type="text" name="okccodeFilter" value="${okccodeFilter!}" placeholder="Код ОКС">
        <input class="form-control mb-2 search_field"  type="text" name="okpdcodeFilter" value="${okpdcodeFilter!}" placeholder="Код ОКПД 2">
        <input class="form-control mb-2 search_field"  type="text" name="adoptionDateFilter" value="${adoptionDateFilter!}" placeholder="Дата принятия">
        <input class="form-control mb-2 search_field"  type="text" name="introductionDateFilter" value="${introductionDateFilter!}" placeholder="Дата введения">
        <input class="form-control mb-2 search_field"  type="text" name="developerFilter" value="${developerFilter!}" placeholder="Разработчик">
        <input class="form-control mb-2 search_field"  type="text" name="predecessorFilter" value="${predecessorFilter!}" placeholder="Принят взамен">
        <input class="form-control mb-2 search_field"  type="text" name="contentsFilter" value="${contentsFilter!}" placeholder="Текст документа">
        <input class="form-control mb-2 search_field"  type="text" name="levelOfAcceptanceFilter" value="${levelOfAcceptanceFilter!}" placeholder="Уровень принятия">
        <input class="form-control mb-2 search_field"  type="text" name="changesFilter" value="${changesFilter!}" placeholder="Изменения">
        <input class="form-control mb-2 search_field"  type="text" name="statusFilter" value="${statusFilter!}" placeholder="Статус документа">
        <input class="form-control mb-2 search_field"  type="text" name="referencesAmountFilter" value="${referencesAmountFilter!}" placeholder="Количество обращений">

        <button class="btn btn-primary mb-2" type="submit" style="min-width: 120px; margin-left: 5px;">Искать!</button>
    </form>
</div>