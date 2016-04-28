/**
 * 
 */

	
	$(function() {
	    var dialog, form;
	  
	 
	    dialog = $( "#dialog-form" ).dialog({
	      autoOpen: false,
	      height: 500,
	      width: 550,
	      modal: true,
	      buttons: {
	        Registra: function(){$( "#registrarDr" ).click()},
	        Cancelar: function() {
	          dialog.dialog( "close" );
	        }
	      },
	      close: function() {
	        $("#frmDoc")[0].reset();	        
	      }
	    });
	 
	 
	    $( "#registro" ).button().on( "click", function() {
	      dialog.dialog( "open" );
	    });
	    
	   // if(${msg}!="") alert("");
	    $("#msgDlg").dialog({
	        modal: true,
	        autoOpen: false,
	        dialogClass: "ui-state-error ui-corner-all",
	        buttons: {
	          Ok: function() {
	            $( this ).dialog( "close" );
	          }
	        }
		    });
	   
	  });	    
	    	
	   