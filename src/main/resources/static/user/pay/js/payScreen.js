var rmaksenj;
var rlqhsrkqt
var notNaN;
var countbox;
var plus
function rhqgkrl(){
    rlqhsrkqt = parseFloat(document.getElementById('rmaksenj').textContent);
    const $countbox = document.getElementById('countbox');
    countbox = $countbox.value
    console.log(rlqhsrkqt);
    if(plus !== undefined && plus != ""){
        document.getElementById('finalPrice').innerHTML = rlqhsrkqt * countbox + parseFloat(plus) + 2500;
    } else if(countbox <= 0 && plus === undefined ){
        document.getElementById('finalPrice').innerHTML = 0
    } else {
        document.getElementById('finalPrice').innerHTML = rlqhsrkqt * countbox + 2500;
    }

    // if(countbox <= 0 && plus === undefined ){
    // }
    console.log(countbox);
    console.log(plus);
}
function onlyNumber(obj) {
    //숫자 아닌 표현식 찾기
    const regexp = /\D/g;
    //숫자 아니면 공백
    event.target.value = event.target.value.replace(regexp, "");
    /* , 추가 */
    obj.value = comma(uncomma(obj.value));
    function comma(str) {
        str = String(str);
        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    }
    
    function uncomma(str) {
        str = String(str);
        return str.replace(/[^\d]+/g, '');
    }
    const rrrrr = event.target.value; 
    console.log(rrrrr);
    
    plus = rrrrr.replace(/,/g,"");
    console.log(countbox);
    // const aaaa = rrrrr+$rewardPrice;
    console.log(plus);
    // console.log( reward.rewardPrice );
    if(countbox == undefined){
        rmaksenj = parseFloat(document.getElementById('rmaksenj').textContent) + parseFloat(plus);
        notNaN = parseFloat(document.getElementById('rmaksenj').textContent)
    } else {
        rmaksenj = parseFloat(document.getElementById('rmaksenj').textContent) * countbox + parseFloat(plus);
        notNaN = parseFloat(document.getElementById('rmaksenj').textContent) * countbox
    }
    console.log(rlqhsrkqt);
    console.log(rmaksenj);
    if( rmaksenj >= 0 ){
        document.getElementById('finalPrice').innerHTML= 2500+rmaksenj;
    } else if(!(rmaksenj >= 0)){
        document.getElementById('finalPrice').innerHTML=notNaN;
    }
    

}
window.onload = function () {

    const $countbox = document.getElementById('countbox');    
    $countbox.addEventListener('input', rhqgkrl);


    const $rewradAddress = document.getElementById("rewradAddress");
    const $outer = document.getElementById("addre");
    const $new = document.createElement("p");
    $rewradAddress.onclick = function () {
        new daum.Postcode({
            oncomplete: function (data) {
                
                $wnth = data.zonecode + data.address;
                $rewradAddress.innerHTML = "[" + data.zonecode + "]" + data.address + "<input type='button' value='X' onclick='remove1()'>";
                $new.innerHTML = "<input type='text' id='detailed-address' placeholder='상세주소 입력' style='text-align: center;'>";
                $outer.appendChild($new);
                document.getElementById('detailed-address').focus();
            }
        }).open({
            // popupKey: 'popup1'
            
        });
        return remove1();
        function remove1() {
            
            $rewradAddress.innerHTML = "주소를 검색하시려면 이곳을 클릭하세요.";
            $new.remove();
        }
    };

    
    const $btn = document.querySelectorAll('.btn');
    const $img = document.querySelectorAll('.img');
    const $off = document.querySelectorAll('.off');
    const $text1 = document.getElementsByClassName('text1');
    console.log($text1);
    var state = "off";
    var state1 = "off";
    var state2 = "off";
    
    const $pay1 = document.getElementById('pay1');
    console.log($pay1);
    $btn[0].addEventListener('click', function(){    
        if(state == "off"){
            $img[0].style.display = 'none';
            $off[0].style.display = 'block';
            $btn[0].style.border = " 3px solid green";
            $text1[0].style.color = 'green';
            $off[1].style.display = 'none';
            $img[1].style.display = 'block';
            $text1[1].style.color = 'black';
            $off[2].style.display = 'none';
            $img[2].style.display = 'block';
            $text1[2].style.color = 'black';
            $pay1.style.display = 'none';
            $btn[1].style.border = " 3px solid black"
            $btn[2].style.border = " 3px solid black"
            state="on";
            state1 = "off";
    state2 = "off";
} else {
    $off[0].style.display = 'none';
    $img[0].style.display = 'block';
    $text1[0].style.color = 'black';
    $off[1].style.display = 'none';
    $img[1].style.display = 'block';
    $text1[1].style.color = 'black';
    $btn[0].style.border = " 3px solid black"
    state = "off";
}
});
$btn[1].addEventListener('click', function(){    
    if(state1 == "off"){
        $pay1.style.display = 'block';
        $img[1].style.display = 'none';
        $off[1].style.display = 'block';
        $text1[1].style.color = 'green';
        $off[0].style.display = 'none';
        $img[0].style.display = 'block';
        $text1[0].style.color = 'black';
        $off[2].style.display = 'none';
        $img[2].style.display = 'block';
        $text1[2].style.color = 'black';
        $btn[1].style.border = " 3px solid green";
        $btn[0].style.border = " 3px solid black"
        $btn[2].style.border = " 3px solid black"
        state = "off";
        state1="on";
        state2 = "off";
    } else {
        $off[1].style.display = 'none';
        $img[1].style.display = 'block';
        $text1[1].style.color = 'black';
        $off[2].style.display = 'none';
        $img[2].style.display = 'block';
        $text1[2].style.color = 'black';
        $pay1.style.display = 'none';
        $btn[1].style.border = " 3px solid black"
    state1 = "off";
    
}
});
$btn[2].addEventListener('click', function(){    
    if(state2 == "off"){
        $img[2].style.display = 'none';
        $off[2].style.display = 'block';
        $text1[2].style.color = 'green';
        $off[1].style.display = 'none';
        $img[1].style.display = 'block';
        $text1[1].style.color = 'black';
        $off[0].style.display = 'none';
        $img[0].style.display = 'block';
        $text1[0].style.color = 'black';
        $pay1.style.display = 'none';
        $btn[2].style.border = " 3px solid green";
        $btn[0].style.border = " 3px solid black"
        $btn[1].style.border = " 3px solid black"
        state = "off";
        state1="off";
        state2 = "on";
    } else {
        $off[2].style.display = 'none';
        $img[2].style.display = 'block';
        $text1[2].style.color = 'black';
        $btn[2].style.border = " 3px solid black"
    state2 = "off";
}
});

const $selectCoupon = document.getElementById('selectcoupon');

const $cutext = document.getElementsByClassName('cutext')[0];

const $cuprice = document.getElementsByClassName('cuprice')[0];







$(document).ready(function() {
    $("#selectcoupon").on('click', function() {
        if ( $(this).prop('checked') ) {
            
            $cutext.innerHTML = "회원가입 축하 쿠폰";
            $cuprice.innerHTML = "10,000원";
            
        } else {
            $cutext.innerHTML =  "";
            $cuprice.innerHTML = "";
        }
    });
});

// var token = $("meta[name='_csrf']").attr("content");
// var header = $("meta[name='_csrf_header']").attr("content");
// $(document).ajaxSend(function(e, xhr, options) { xhr.setRequestHeader(header, token); });


/* 결제자 번호 */
// const $supporterPhoneNumber = document.getElementById('supporterPhoneNumber').value;
/* 수령인 이름*/
const $pageRewardOrderFormReceiveName = document.getElementById('pageRewardOrderFormReceiveName').value; 
/* 수량 */
const countbox = document.getElementById('countbox');
/* 수령인 번호 */ 
const $pageRewardOrderFormHp = document.getElementById('pageRewardOrderFormHp').value;
/* 리워드 가격*/ 
const $rewardPrice = document.getElementById('rewardPrice').innerText;
console.log($rewardPrice);
/* 배송비 */  
const $deliveryPrice = document.getElementById('deliveryPrice').innerText;
/* 프로젝트 번호 */ 
// const $projNo = "${ payD.projNo }";
/* 리워드 번호 */
const $rewardtitle = document.getElementById('rewardtitle').innerText;
const $rewardNo = document.getElementById('rewardNo').innerText;
/* 유저 번호 임시로 패이오더 DTO에추가 */
const $projNo = document.getElementById('pjNo').innerText;
const userNo = document.getElementById('userNo').innerText;
const $card = '카드'; 
/* 추가 후원금 */
// location.href='/pay/payScreen?projNo=$projNo&rewardNo=$rewardNo';             
console.log(userNo);
const $pageRewardOrderFormAddAmt = document.getElementById('pageRewardOrderFormAddAmt').value;
var pageCheck2 = document.getElementById('pageCheck2');
var pageCheck1 = document.getElementById('pageCheck1');
$(function(){
    $('#submitPay').click(function(){
        
        if(!(pageCheck1.checked && pageCheck2.checked)){
            alert("개인정보제공 동의에 체크하세요")
        } else {

        

        console.log($pageRewardOrderFormAddAmt);
        const $rewradAddress1 = document.getElementById('detailed-address').value;
        const $finalPrice = document.getElementById('finalPrice').innerText;
        const $kakao = document.querySelectorAll('.text1')[0].innerText;
        const $title = document.getElementById('pjTitle').innerText;
        const $Date = new Date();
        const $address = $wnth + $rewradAddress1;
        const onError = xhr => console.log(xhr);
        const onSuccess = data => console.log(data);
        console.log($Date);
        
        console.log($kakao);
        console.log("하");
        console.log($finalPrice);
        IMP.init('imp63382662');
        if(state == "on"){
            IMP.request_pay({
                projNo : $projNo,
                pg : "kakaopay", 
                pay_method : 'card',
                merchant_uid : 'ka' + new Date().getTime(),
                name : '결제',
                amount : $finalPrice,
                buyer_email : '구매자이메일',
                buyer_name : $pageRewardOrderFormReceiveName,
                buyer_tel : '구매자번호',
                buyer_addr : '구매자주소',
                buyer_postcode : $address,
                // m_redirect_url : 'redirect url'
            }, function(rsp) {
                const test = { 
                    delivery : {recipientNm : rsp.buyer_name, 
                        recipientAddress : $address, 
                        recipientPhone : $pageRewardOrderFormHp }
                        ,
                    payment : {
                        payMethod : $kakao },
                        reward : {projNo : $projNo,
                            rewardNo : $rewardNo
                    }, 
                    rewardQuantity : countbox.value, 
                    rewardPrice : $rewardPrice, 
                    extraReward : $pageRewardOrderFormAddAmt,
                    deliveryFee : $deliveryPrice, 
                    dcPrice : $cuprice.innerText, 
                    payPrice : $finalPrice,
                    orderDate : $Date,
                    userNo : userNo,
                    imp_uid: rsp.imp_uid, 
                    merchant_uid: rsp.merchant_uid
                };
                if ( rsp.success ) {
                    console.log(rsp);
                    const json = JSON.stringify(test);
                    console.log(json);
                    //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                    $.ajax({
                        url: "/pay/payComplete", //cross-domain error가 발생하지 않도록 주의해주세요
                        type: 'POST',
                        data: json,
                        contentType : 'application/json; charset=UTF-8',
                        dataType : 'text',
                        error : onError,
                        success : json
                            //기타 필요한 데이터가 있으면 추가 전달
                            
                        })
                        .then(function(json) {
                            //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                            if ( json ) {
                                
                                payPrice = rsp.imp_uid;
                                console.log(payPrice);
                            // msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                            // msg += '\결제 금액 : ' + rsp.paid_amount;
                            // msg += '카드 승인번호 : ' + rsp.apply_num;
                            console.log("하");
                            // alert(msg);
                        } else {
                            //[3] 아직 제대로 결제가 되지 않았습니다.
                            //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                        }
                        location.href="http://localhost:8001/pay/payComplete";
                    });
                    //성공시 이동할 페이지
                } else {
                    msg = '결제에 실패하였습니다.';
                    msg += '에러내용 : ' + rsp.error_msg;
                    //실패시 이동할 페이지
                    console.log(rsp.error_msg);
                    alert('결제를 취소하셨습니다.');
                }
            });
            
            
        }
        if(state1 == "on"){
            IMP.request_pay({
                projNo : $projNo,
                pg : "html5_inicis.INIPayTest", 
                pay_method : 'card',
                merchant_uid : 'ka' + new Date().getTime(),
                name : $rewardtitle,
                amount : $finalPrice,
                buyer_email : $uneremail,
                buyer_name : $pageRewardOrderFormReceiveName,
                buyer_postcode : $address,
                // m_redirect_url : 'redirect url'
            }, function(rsp) {
                if ( rsp.success ) {
                    console.log(rsp);
                    const test = { 
                        delivery : {recipientNm : rsp.buyer_name, 
                            recipientAddress : $address, 
                            recipientPhone : $pageRewardOrderFormHp }
                            ,
                        payment : {
                            payMethod : $card },
                            reward : {projNo : $projNo,
                                rewardNo : $rewardNo
                        }, 
                        rewardQuantity : countbox.value, 
                        rewardPrice : $rewardPrice, 
                        extraReward : $pageRewardOrderFormAddAmt,
                        deliveryFee : $deliveryPrice, 
                        dcPrice : $cuprice.innerText, 
                        payPrice : $finalPrice,
                        orderDate : $Date,
                        userNo : userNo,
                        imp_uid: rsp.imp_uid, 
                        merchant_uid: rsp.merchant_uid
                    };
                    const json = JSON.stringify(test);
                    console.log(json);
                    //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                    $.ajax({
                        url: "/pay/payComplete", //cross-domain error가 발생하지 않도록 주의해주세요
                        type: 'POST',
                        data: json,
                        contentType : 'application/json; charset=UTF-8',
                        dataType : 'text',
                        error : onError,
                        success : json
                            //기타 필요한 데이터가 있으면 추가 전달
                            
                        })
                        .then(function(json) {
                            //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                            if ( json ) {
                                
                                payPrice = rsp.imp_uid;
                                console.log(payPrice);
                            // msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                            // msg += '\결제 금액 : ' + rsp.paid_amount;
                            // msg += '카드 승인번호 : ' + rsp.apply_num;
                            console.log("하");
                            // alert(msg);
                        } else {
                            //[3] 아직 제대로 결제가 되지 않았습니다.
                            //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                        }
                        // location.href="http://localhost:8001/pay/payComplete";
                    });
                    //성공시 이동할 페이지
                } else {
                    msg = '결제에 실패하였습니다.';
                    msg += '에러내용 : ' + rsp.error_msg;
                    //실패시 이동할 페이지
                    alert('결제를 취소하셨습니다.');
                }
            });
            
            
        }
    }
    })
    
    
});
/* 주소 + 상세주소*/ 
/* 접근 */ 

};

/* 직접 입력*/

$(function () {
    //직접입력 인풋박스 기존에는 숨어있다
    $("#selboxmemo").hide();
    $("#rewardOrderFormMemo").change(function () {
        //직접입력을 누를 때 나타남
        if ($("#rewardOrderFormMemo").val() == "직접 입력") {
            $("#selboxMemo").show();
        } else {
            $("#selboxMemo").hide();
        }
    })
});

$(function () {
    //직접입력 인풋박스 기존에는 숨어있다가
    $("#selboxAddressName").hide();
    $("#rewardOrderFormAddressName").change(function () {
        //직접입력을 누를 때 나타남
        if ($("#rewardOrderFormAddressName").val() == "직접 입력") {
            $("#selboxAddressName").show();
        } else {
            $("#selboxAddressName").hide();
        }
    })
});


