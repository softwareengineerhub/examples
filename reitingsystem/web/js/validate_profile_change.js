
$(document).ready(function() {
    $("#cityRow").hide();
    var clickCount=0;

    $("#addButton").click(function(){
        if(clickCount==0){
            $("#city").hide();
            $("#cityRow").show();
            $("#button").attr("src", "../images/btn_select_city.png");
            clickCount++;
        }else{
            $("#city").show();
            $("#cityRow").hide();
            $("#button").attr("src", "../images/btn_add_city.png");
            clickCount--;
        }


    });

    $("#showList").click(function(){
        $("#cityRow").hide();
        $("#city").removeAttr("disabled");
        $("#label").css("color", oldColor);
        $("#addButton").attr("id", "addButton");
        $("#button").attr("value", "добавить город");
    });





    //--------------------------
    $(function() {

        var cityList = $( "#cityList" ),
        cityField = $( "#cityField" ),
        street=$( "#street" ),
        house = $( "#house" ),
        phone = $( "#phone" ),
        work_phone = $( "#work_phone" ),
        mobile_phone = $( "#mobile_phone" ),
        email = $( "#email" ),


        allFields = $( [] ).add(street).add(email).add(cityList).add(cityField).add(house).add(phone).add(mobile_phone).add(work_phone),
        tips = $( "#errorMessage");


        function updateTips( t ) {
            tips.text(t);
        }

        function checkLength( o, n, min, max ) {
            if ( o.val().length > max || o.val().length < min ) {
                o.addClass( "error_input" );
                updateTips( "Длина " + n + " должна быть между " +
                    min + " и " + max + "." );
                return false;
            } else {
                o.removeClass( "error_input");
                return true;
            }
        }

        function checkRegexp( o, regexp, n ) {
            if ( !( regexp.test( o.val() ) ) ) {
                o.addClass( "error_input" );
                updateTips( n );
                return false;
            } else {
                o.removeClass( "error_input");
                return true;
            }
        }


        $( "#changeprofile").submit(function() {
            var bValid = true;
            allFields.removeClass("error_input");
            if(clickCount!=0){
                 bValid = bValid && checkLength( cityField, "города", 3, 50);
                 $("#cityToStoreInDB").attr("value", cityField.val());
            }else{
                 $("#cityToStoreInDB").attr("value", cityList.val());
            }
            bValid = bValid && checkLength( street, "улицы", 3, 50);
            bValid = bValid && checkLength( house, "дома", 1, 50 );
            bValid = bValid && checkLength( phone, "телефона", 5, 20);
            //bValid = bValid && checkRegexp( phone, /^([0-9])+$/i, "Телефон должен состоять из цифр" );
            bValid = bValid && checkLength( work_phone, "рабочий телефон", 5, 20);
            //bValid = bValid && checkRegexp( work_phone, /^([0-9])+$/i, "Телефон должен состоять из цифр" );
            bValid = bValid && checkLength( mobile_phone, "телефона", 5, 20);
            //bValid = bValid && checkRegexp( mobile_phone, /^([0-9])+$/i, "Телефон должен состоять из цифр" );
            bValid = bValid && checkLength( email, "e-mail", 6, 80 );
            bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "Введите корректный адрес" );
            if ( bValid ) {

                $("#errorMessage").text("");
            }
            return bValid;
        });
    });

})