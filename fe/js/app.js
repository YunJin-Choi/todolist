(function (window) {
	'use strict';
	
	// 전체 할일 출력
	$.ajax({
	    url : "findAll",
	    type: "get",
	    success : function(todoList) {
	    	var todoListItem = $(".todo-list").first();
	    	var todoCount = todoList.length;
	    	for(var i=0; i<todoList.length; i++) {
	    		var li = "<li id='notCompleted'>";
	    		if(todoList[i].completed=='1') {
	    			li = "<li id='completed' class='completed'>";
	    			todoCount = todoCount - 1;
	    		}
		    	todoListItem.prepend(
		    			li +
		    				"<div class='view' id='" + todoList[i].id + "'>" +
		    					"<input class='toggle' type='checkbox'>" +
		    					"<label>" + todoList[i].todo + "</label>" +
		    					"<button class='destroy'></button>" +
		    				"</div>" +
		    				"<input class='edit' value='Rule the web'>" +
		    			"</li>");	
	    	}
	    	$(".todo-count-strong").text(todoCount);
	    }
	});

	// 할일 카운트
	function calTodoCount() {
	   	var todoCountStrong = $(".todo-list").children().length;
	   	todoCountStrong = todoCountStrong - $(".completed").length;
	    	
	   	$(".todo-count-strong").text(todoCountStrong);
	}
	
	// 할일 등록
	$(".new-todo").keypress(function(e){
		var value = $(".new-todo").val();
		if(e.keyCode == 13 && value != null && value != ""){
			$.ajax({
			    url : "insert/"+value,
			    type: "get",
			    success : function(todo) {
			    	var todoListItem = $(".todo-list").first();
			    	todoListItem.prepend(
			    			"<li id='notCompleted'>" +
			    				"<div class='view' id='" + todo.id + "'>" +
			    					"<input class='toggle' type='checkbox'>" +
			    					"<label>" + todo.todo + "</label>" +
			    					"<button class='destroy'></button>" +
			    				"</div>" +
			    				"<input class='edit' value='Rule the web'>" +
			    			"</li>");	

			    	$(".new-todo").val("");
			    	
			    	calTodoCount();
			    	allTodo();
				}
			});
		}
	});
	
	// 할일 삭제
	$(".todo-list").on("click", ".destroy", function(){
		$.ajax({
		    url : "delete/"+$(this).parent().attr("id"),
		    type: "delete",
		    success : function() {
		    }
		});
    	$(this).parent().parent().remove();
    	calTodoCount();
	});
	
	// All 필터
	$("#all-todo").on("click", function(){
		$("[id=completed]").show();
		$("[id=notCompleted]").show();
		
		$(".selected").removeClass("selected");
		$("#all-todo").addClass("selected");
	});

	// Active 필터
	$("#active-todo").on("click", function(){
		$("[id=completed]").hide();
		$("[id=notCompleted]").show();
		
		$(".selected").removeClass("selected");
		$("#active-todo").addClass("selected");
	});
	
	// Completed 필터 or 할일 완료
	$("#completed-todo").on("click", function(){
		var urlValue = "completed/";
		var lenChkToggle = $(".toggle:checked").length;
		if (lenChkToggle > 0) {
			$(".toggle:checked").each(function(){
				urlValue = urlValue + "$" + $(this).parent().attr("id");
				$(this).parent().parent().addClass("completed");
				$(this).parent().parent().attr("id", "completed");
				$(this).prop("checked", false);
			});
			$.ajax({
				url : urlValue,
	      	    type: "put"
			});
		}
		else {
			$("[id=completed]").show();
			$("[id=notCompleted]").hide();
	    	  
			$(".selected").removeClass("selected");
			$("#completed-todo").addClass("selected");
		}
	});
	
	// 완료된 할일 삭제
	$(".clear-completed").on("click", function(){
		var urlValue = "clearCompleted";
	    var n = $(".completed").length;
	    if (n > 0) {
	  	  $(".completed").each(function(){
	      	  $(this).remove();
	        });
	        $.ajax({
	    	    url : urlValue,
	    	    type: "delete"
	        });
	    }
	});
	
})(window);