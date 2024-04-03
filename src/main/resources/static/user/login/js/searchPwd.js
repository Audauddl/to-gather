
function mailsend() {
	   const phone = document.getElementById("phone").value;
	   const emailId = document.getElementById("emailId").value;
	
	   $.ajax({
	      type : "POST",
	      url : "/user/mailsend",
	      data : {
			  "phone" : phone,
			  "emailId" : emailId 
		  },
	      success : function(data) {
			  
			  
	         alert("해당 이메일로 임시 비밀번호를 발송 하였습니다. \n 확인부탁드립니다.")
	         
	         console.log("data : "+ data);
	         
	      }
	   })
	}



