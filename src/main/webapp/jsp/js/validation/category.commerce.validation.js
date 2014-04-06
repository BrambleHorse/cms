function ajax() { //Ajax отправка формы
    var msg = $("#admin-catalog-category-form").serialize();
    $.ajax({
        type: "POST",
        url: "/admin.catalog.category.do",
        data: msg,
        success: function(data) {
            window.location = "/admin.do";
        },
        error:  function(xhr, str){
            alert("Возникла ошибка!");
        }
    });
}

jQuery.fn.notExists = function() { //Проверка на существование элемента
    return $(this).length==0;
}

$(document).ready(function(){ //Валидация формы
    $(".send").validation(
        $(".text-input").validate({
            test: "blank",
            invalid: function(){
                if($(this).nextAll(".error").notExists()) {
                    $(this).after('<div class="error">Данное поле должно быть заполнено</div>');
                    $(this).nextAll(".error").delay(2000).fadeOut("slow");
                    setTimeout(function () {
                        $(".text-input").next(".error").remove();
                    }, 2600);
                }
            },
            valid: function(){
                $(this).nextAll(".error").remove();
            }
        }),
        $(".number-input").validate({
            test: "blank digits",
            invalid: function(){
                if($(this).nextAll(".error").notExists()) {
                    $(this).after('<div class="error">Введите число, определяющее позицию элемента</div>');
                    $(this).nextAll(".error").delay(2000).fadeOut("slow");
                    setTimeout(function () {
                        $(".number-input").next(".error").remove();
                    }, 2600);
                }
            },
            valid: function(){
                $(this).nextAll(".error").remove();
            }
        })
    );
});