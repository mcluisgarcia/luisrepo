/**
 * 
 */

$(function(){
	
	$(function() {
	    var dialog, form;
	 
	   function addUser() {	      
	     
	    	//$("#agregarDr").submit();
	    	$("#agregar").click();
		   /*$.ajax({ //Comunicación jQuery hacia JSP
	           type: "POST",
	           url: "Login.jsp",
	           data: "apellido="+apellido,
	           success: function(msg){
	               $("span#ap").text(msg);
	           },
	           error: function(xml,msg){
	               $("span#ap").text(" Error");
	           }*/
	        //dialog.dialog( "close" );
	      
	    }
	 
	    dialog = $( "#dialog-form" ).dialog({
	      autoOpen: false,
	      height: 500,
	      width: 550,
	      modal: true,
	      buttons: {
	        "Aceptar": addUser,
	        Cancelar: function() {
	          dialog.dialog( "close" );
	        }
	      },
	      close: function() {
	        form[ 0 ].reset();
	        
	      }
	    });
	 
	    form = dialog.find( "form" ).on( "submit", function( event ) {
	      //event.preventDefault();
	      //addUser();
	    });
	 
	    $( "#registro" ).button().on( "click", function() {
	      dialog.dialog( "open" );
	    });
	  });
});