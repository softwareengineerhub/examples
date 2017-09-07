
var delay=0;

$("#menu_img").hover(
    function () {
        $("#main").hover(
            function () {
                //$(this).attr('src','images/btn_main_sel.png');
                var left=$(this).position().left-5;
                $("#mouse_ind").animate({
                    left:left
                },delay);
            },
            function () {
            // $("#mouse_ind").animate({left:-5},delay);
            //$(this).attr('src','images/btn_main_def.png');
            }
            );
        $("#rules").hover(
            function () {
                // $(this).attr('src','images/btn_rules_sel.png');
                var left=$(this).position().left-5;
                $("#mouse_ind").animate({
                    left:left
                },delay);

            },
            function () {
            //   $("#mouse_ind").animate({left:-5},delay);
            //$(this).attr('src','images/btn_rules_def.png');
            }
            );
        $("#conditions").hover(
            function () {
                var left=$(this).position().left-5;
                $("#mouse_ind").animate({
                    left:left
                },delay);
            //$(this).attr('src','images/btn_conditions_sel.png');
            },
            function () {
            //$("#mouse_ind").animate({left:-5},delay);
            //$(this).attr('src','images/btn_conditions_def.png');
            }
            );
        $("#faq").hover(
            function () {
                var left=$(this).position().left-15;
                $("#mouse_ind").animate({
                    left:left
                },delay);
            // $(this).attr('src','images/btn_faq_sel.png');
            },
            function () {
            // $("#mouse_ind").animate({left:-5},delay);
            //  $(this).attr('src','images/btn_faq_def.png');
            }
            );

    },
    function () {
        $("#mouse_ind").animate({
            left:-5
        },delay);
    }
    );


