/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Mustache */

$(document).ready(// Exécuté à la fin du chargement de la page
    function () {
        showAllCode(); 
    }
);


function showAllCode() {
    $.ajax({
            url: "ShowCode",
            dataType: "json",
            success: // La fonction qui traite les résultats
                    // La fonction qui traite les résultats
                    function(result) {
                            console.log(result);

                            // Le code source du template est dans la page
                            var template = $('#showAllCode').html();
                            Mustache.parse(template);
                            // On combine le template avec le résultat de la requête
                            var processedTemplate = Mustache.render(template, {codes: result });
                            // On affiche le résultat dans la page
                            $('#showAllCodeICI').html(processedTemplate);	
                    },
            error: showError
    });				
}

function addCode(){
    var code = $("#Discount").val();
    var rate = $("#rate").val();
    
     $.ajax({
            url: "AddCodeServlet",
            data: { "code" : code,"rate":rate },
            dataType: "json",
            success: function(result){
                
                showAllCode();

               // console.log("aaaaaaaa");
                var template = $('#msg').html();
                Mustache.parse(template);
                var processedTemplate = Mustache.render(template, {msg:result });
                $('#showMsg').html(processedTemplate);	
                
            },
            error: showError
    });			
    
}

function deleteCode(code){
    console.log(code);
     $.ajax({
            url: "DeleteCodeServlet",
            data: { "code" : code},
            dataType: "json",
            success: function(result){
                
                showAllCode();

               // console.log("aaaaaaaa");
//                var template = $('#msg').html();
//                Mustache.parse(template);
//                var processedTemplate = Mustache.render(template, {msg:result });
//                $('#showMsg').html(processedTemplate);	
                
            },
            error: showError
    });			
}
// Fonction qui traite les erreurs de la requête
function showError(xhr, status, message) {
        alert("Erreur: " + status + " : " + message);
}

