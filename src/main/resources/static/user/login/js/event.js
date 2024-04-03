window.onload = function() {


    if(document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function() {
            location.href = "/user/login";
        }
    }

    if(document.getElementById("logout")) {
        const $logout = document.getElementById("logout");
        $logout.onclick = function() {
            location.href = "/user/logout";
        }
    }
    
   if(document.getElementById("signup")) {
        const $regist = document.getElementById("signup");
        $regist.onclick = function() {
			
  var email = document.getElementById("email");
  var pwd = document.getElementById("pwd");
  var repwd = document.getElementById("repwd");
  var userNm = document.getElementById("userNm");
  var phone = document.getElementById("phone");
  var agree = document.getElementById("agree");
 

  if (email.value == "") { //해당 입력값이 없을 경우 같은말: if(!uid.value)
    alert("아이디(이메일)를 입력하세요.");
    email.focus(); 
    return false; 
 };
  

  if (pwd.value == "") {
    alert("비밀번호를 입력하세요.");
    pwd.focus();
    return false;
  };

  //비밀번호 영문자+숫자+특수조합(8~15자리 입력) 정규식
  var pwdCheck = /^[a-zA-Z0-9]{8,15}$/;

  if (!pwdCheck.test(pwd.value)) {
    alert("비밀번호는 영문자+숫자 조합으로 8~15자리 사용해야 합니다.");
    pwd.focus();
    return false;
  };

  if (repwd.value !== pwd.value) {
    alert("비밀번호가 일치하지 않습니다..");
    repwd.focus();
    return false;
  };

  if (userNm.value == "") {
    alert("이름을 입력하세요.");
    userNm.focus();
    return false;
  };

  var reg = /^\d{2,3}-\d{3,4}-\d{4}$/;
  if (!reg.test(phone.value)) {
    alert("핸드폰 번호에 -를 포함해주세요.");
    phone.focus();
    return false;
  }
			
   if (!agree.checked) { //체크박스 미체크시
    alert("필수 항목 약관에 동의 해주세요.");
    agree.focus();
    return false;
  }			
			
            location.href = "/user/signup";
        }
    }

    if(document.getElementById("duplicationCheck")) {

        const $duplication = document.getElementById("duplicationCheck");

        $duplication.onclick = function() {
            let email = document.getElementById("email").value.trim();

            fetch("/user/idDupCheck", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({email: email})
            })
                .then(result => result.text())
                .then(result => alert(result))
                .catch((error) => error.text().then((res) => alert(res)));

        }
    }

    if(document.getElementById("updateUser")) {
        const $update = document.getElementById("updateUser");
        $update.onclick = function() {
            location.href = "/user/myInfo";
        }
    }
    
     if(document.getElementById("updatePwd")) {
        const $updatePwd = document.getElementById("updatePwd");
        $updatePwd.onclick = function() {
            location.href = "/user/myInfo.pwd";
        }
    }
    
    
    if(document.getElementById("deleteUser")) {
        const $update = document.getElementById("deleteUser");
        $update.onclick = function() {
            location.href = "/user/delete";
        }
    }
    
     
}
