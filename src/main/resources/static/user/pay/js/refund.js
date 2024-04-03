window.onload = function () {
    
    const $refund = document.getElementById('refund');
    $refund.addEventListener('click', cancel);
    // const $payNo = document.getElementById('payNo').innerText;
    // const $rePrice = document.getElementById('rePrice').innerText;
    // const $price = document.getElementById('price').innerText;

    function cancel(){
        console.log("클릭");
        console.log($orderNo);
        console.log($price);
        console.log($payNo);
        jQuery.ajax({
            "imp_uid" : 'imp63382662',
            "url": "/pay/orderCancel", 
            "type": "POST",
            "contentType": "application/json; charset = utf-8",
            "data": JSON.stringify({
                "merchant_uid": $orderNo, // 예: ORD20180131-0000011
                "cancel_request_amount": $price, // 환불금액
                "reason": "테스트 결제 환불", // 환불사유
                //   // [가상계좌 환불시 필수입력] 환불 수령계좌 예금주
                //   "refund_holder": "홍길동", 
                //   // [가상계좌 환불시 필수입력] 환불 수령계좌 은행코드(예: KG이니시스의 경우 신한은행은 88번)
                "refund_bank": "49", 
                //   // [가상계좌 환불시 필수입력] 환불 수령계좌 번호
                //   "refund_account": "56211105948400" 
                orderNo : $orderNo,
                payPrice : $price,
                payNo : $payNo
            }),
            "dataType": "json",
            
        })
        .then()
        alert('환불 완료');
        location.href='/';
    }
}    //9cbfda94434df83c9bda00060bfae001b51da7f3
