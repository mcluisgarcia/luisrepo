 $(document).ready(function () {
	 $.jgrid.defaults.responsive = true;
	 var mydata = [
	               { dia: "Lunes" },
	               { dia: "Martes" },
	               { dia: "Miercoles" },
	               { dia: "Jueves"},
	               { dia: "Viernes"},
	        ];
			
            $("#jqGrid").jqGrid({
                //url: '',
                //mtype: "GET",
				styleUI : 'Bootstrap',
				editurl: 'clientArray',
                datatype: "local",
                data: mydata,
                colModel: [
                           {
                        	   label: 'Dia',
                               name: 'dia',
                               editable: false,   
                           },
                    {
						label: 'Hora Inicio',
                        name: 'fechaInicio',
                        //width: 150,
                        editable: true,
                        edittype:"text",
                        editoptions: {
                            // dataInit is the client-side event that fires upon initializing the toolbar search field for a column
                            // use it to place a third party control to customize the toolbar
                            dataInit: function (element) {
                            	 $(element).datetimepicker({
                                     format: 'LT',
                                     inline: true,
                                     sideBySide: true
                                 });
                            }
                        }
                    },
                    {
						label: 'Hora Fin',
                        name: 'fechaFin',
                        //width: 150,
                        editable: true,
                        edittype:"text",
                        editoptions: {
                            // dataInit is the client-side event that fires upon initializing the toolbar search field for a column
                            // use it to place a third party control to customize the toolbar
                          
                        	dataInit: function (element){
                        	 $(element).datetimepicker({
                                 format: 'LT',
                                 inline: true,
                                 sideBySide: true
                             });
                        	}
                        }
                    }
                ],
				viewrecords: true,
				onSelectRow: editRow, // the javascript function to call on row click. will ues to to put the row in edit mode
				ondblClickRow:saveRow,
				height: "auto",
                width: 700,
                rowNum: 5,
                multiselect: true,
            	page: 1
               // pager: "#jqGridPager"
            });
            
                      
            var lastSelection;

            function editRow(id,status,e) {
            	
                if (id && id !== lastSelection) {
                    var grid = $("#jqGrid");
                    grid.jqGrid('saveRow', lastSelection);
                    //grid.jqGrid('restoreRow',lastSelection);
                   // if(grid.getGridParam("selrow")==id)
                    grid.jqGrid('editRow',id, {keys:true, focusField: 2});
                    
                    lastSelection = id;
                }
                
                //$.jgrid.saveState("jqGrid");
                //if(e.which ===1)saveRows();
            }
            
            function saveRows() {
                var grid = $("#jqGrid");
                var ids = grid.jqGrid('getDataIDs');

                for (var i = 0; i < ids.length; i++) {
                    grid.jqGrid('saveRow', ids[i]);
                }
            }
            
            function saveRow(id) {
                var grid = $("#jqGrid");
                grid.jqGrid('saveRow', id);
            }
            
            
            
            
        });
 
 function getSelectedRows() {
     var grid = $("#jqGrid");
     var rowKey = grid.getGridParam("selrow");

     if (!rowKey)
         alert("No rows are selected");
     else {
         var selectedIDs = grid.getGridParam("selarrrow");
         var rows = [];
         for (var i = 0; i < selectedIDs.length; i++) {
             rows.push(grid.getRowData(selectedIDs[i]));
         }
         saveAll(rows);
     }  
 }
 
 function saveAll(rows){
	 //var rows= jQuery("#jqGrid").jqGrid('getRowData');
	  var paras=new Array();
	  for(var i=0;i<rows.length;i++){
	      var row=rows[i];
	      //paras.push($.param(row));
	      paras.push(row);
	  }
	  var registros = paras;//paras.join('and');
	  console.log( registros);
	  //return;
	  $.ajax({
	      type: "POST",
	      url: "doctores",
	      data: JSON.stringify( 
	    	 registros
	      ),
	      datatype: "json",
	      contentType: 'application/json; charset=utf-8',
	      success: function(response){
	          alert(response.status);
	      }
	  });
}
 