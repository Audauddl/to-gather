			/* 체크박스 */
			$("#checkAll").click(function(){
				const check = $("#checkAll").prop("checked");
				if(check) {
					$(".checkBox").prop("checked", true);	
				} else {
					$(".checkBox").prop("checked", false);
				}
			});

			
			$(".checkBox").click(function(){
				$("#checkAll").prop("checked", false);
			});		
					
			/* 삭제 버튼 */	
		   $(".user-delete").click(function(){
		    const confirmMessage = confirm("정말 삭제하시겠습니까?");
		    
		    if(confirmMessage) {
		     var checkList = new Array();
		     
		     $("input[class='checkBox']:checked").each(function(){
		      checkList.push($(this).attr("data-userNo"));
		     });
		     
		      console.log(checkList);
		      
		     $.ajax({
		      url : "/admin/user/delete",
		      type : "post",
		      data : { checkBox : checkList },
		      success : function(){
		       location.href = "/admin/user/list";
		      }     
		     });		     
		    }  		    
		   });