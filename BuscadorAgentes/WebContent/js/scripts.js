/**
 * Scripts auto completar
 */
$(document).ready(
		function() {
			function log(message) {
				$("<div>").text(message).prependTo("#log");
				$("#log").scrollTop(0);
			}

			$("#termino").autocomplete(
					{
						source : function(d, e) {
							$("#resultados").html("");
							$.ajax({
								beforeSend : function(xhrObj) {
									xhrObj.setRequestHeader("Content-Type",
											"application/json");
									xhrObj.setRequestHeader("Accept",
											"application/json");
								},
								type : "POST",
								contentType : "application/json",
								url : "/BuscadorAgentes/servicios/buscar",
								data : JSON.stringify({
									"termino" : d.term
								}),
								dataType : "json",
								success : function(b) {
									var c = [];
									$.each(b, function(i, a) {
										console.log(a.termino);
										a.label = a.termino;
										a.value = a.idTermino;
										c.push(a);
									});
									console.log(c);
									e(c);
								}

							});
						},
						minLength : 2,
						focus : function(event, ui) {
							$("#termino").val(ui.item.termino);
							return false;
						},
						select : function(event, ui) {
							$("#termino_id").val(ui.item.idTermino);
							$.get({
								beforeSend : function(xhrObj) {
									xhrObj.setRequestHeader("Content-Type",
											"application/json");
									xhrObj.setRequestHeader("Accept",
											"application/json");
								},
								type : "GET",
								contentType : "application/json",
								url : "/BuscadorAgentes/servicios/buscar/"+ui.item.idTermino,
								dataType : "json",
								success : function(b) {
									var c = [];
									$.each(b, function(i, a) {
										var cla = "";
										if (a.nivelConfianza <40){
											cla = "danger" 
										}else if (a.nivelConfianza >80){
											cla = "success" 
										}else{
											cla = "warning" 
										}
										$("#resultados").append('<a href="'+a.url+'" class="list-group-item"> <h4 class="list-group-item-heading">'+a.url+' <span class="label label-'+cla+'">'+a.nivelConfianza+'</span></h4> <p class="list-group-item-text">'+a.descripcion+'</p> </a>');
									});
									e(c);
								}

							});						
							
							return false;
						}
					});
		});

