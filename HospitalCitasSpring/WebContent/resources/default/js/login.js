/**
 * 
 */

function saveDoctor(){
			var js = $("#frmDoc").serialize();
			console.log(js);
			$.ajax({
				type: "POST",
			      url: "saveDoc",
			      data: js,
			      success: function(response){
			          //console.log(response);
			    	  window.location.href = "doctores";
			      },
			      error: function(error){
			    	  $("#info").text("No se logró gaurdar la información "+error);
			    	  $("#msgDlg").dialog("open");
			      }

			});
		}
	
	$(function() {
	    var dialog, form;
	  
	 
	    dialog = $( "#dialog-form" ).dialog({
	      autoOpen: false,
	      height: 500,
	      width: 550,
	      modal: true,
	      buttons: {
	        Registra: saveDoctor,
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

	
	   