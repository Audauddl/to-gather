window.onload = function() {

    /* 사이드 바 클릭 시, 클릭 된 step 활성화 */
    const clickSteps = document.querySelectorAll('.step-subject');

    for (let i = 0; i < clickSteps.length; i++) {
        clickSteps[i].addEventListener('click', clickedStep);
    }

    function clickedStep() {
        const activeStep = document.querySelector('.step-subject.running');

        /* 다른 요소가 activeStep일 때, 나머지 요소들은 running 클래스 제거 */
        if (activeStep) {
            activeStep.classList.remove('running');
        }
        this.classList.add('running');
    }

    /* ------------------------------------------------------------------------------------ */

    /* Maker 프로필 프리뷰 */
    (function(){
        const previewArea = document.querySelector("#preview-area");
        const fileElements = document.querySelector("#attach-profile");

        fileElements.addEventListener('change', preview);

        function preview() {
            console.log(this);
            if(this.files && this.files[0]) {
                const reader = new FileReader();
                reader.readAsDataURL(this.files[0]);
                reader.onload = function() {
                    previewArea.innerHTML = `<img src='${reader.result}' id="preview-image">`;
                }
            }
        }
    })();

    /* ------------------------------------------------------------------------------------ */

    /* 주소 검색 */
    const SearchAddress = document.getElementById("address1");
		
	SearchAddress.onclick = function() {
			
		/* 다음 우편번호 검색 창을 오픈하면서 동작할 콜백 메소드를 포함한 객체를 인자로 전달 */
		new daum.Postcode({
		    oncomplete: function(data) {
		        /* 팝업에서 검색결과 항목을 클릭 했을 시 실행 할 코드를 작성하는 부분 */
		        document.getElementById("zip-code").value = data.zonecode; /* 넘어온 우편번호를 value로 저장 */
		        document.getElementById("address1").value = data.address; /* 넘어온 주소를 value로 저장 */
		        document.getElementById("address2").focus(); /* 상세주소를 입력하라는 의미로 포커스 */
		    }
		}).open();
	};

    /* ------------------------------------------------------------------------------------ */

    /* 프로젝트 이미지들의 크기를 제한하는 alert */
    const mainImage = document.querySelector("#main-image")
    const uploadName = document.querySelector(".upload-name") /* 20MB보다 큰 이미지는 파일명 변경되지 않도록 하기 위해 추가 */

    mainImage.addEventListener('change', checkSize);

    function checkSize() {
        if(mainImage.files && mainImage.files[0].size > (20 * 1024 * 1024)) { /* 추후 (20 * 1024 * 1024) 으로 변경 */
            alert("이미지는 20MB 이하의 크기를 가지고 있어야 합니다 😰")
            mainImage.value = null;
            uploadName.value = "파일을 업로드 해주세요 :)";
        }
    };

    /* ------------------------------------------------------------------------------------ */
    
    /* 프로젝트 메인 사진 프리뷰 */
    (function(){
        const mainPreviewArea = document.querySelector("#main-preview-area");

        mainImage.addEventListener('change', mainPreview);

        function mainPreview() {
            console.log(this);
            if(this.files && this.files[0]) {
                const reader = new FileReader();
                reader.readAsDataURL(this.files[0]);
                reader.onload = function() {
                    mainPreviewArea.innerHTML = `<img src='${reader.result}' id="main-preview-image">`;
                }
            }
        }
    })();

    /* 첨부 된 메인 사진 파일명 보여주기 */
    const mainName = document.querySelector("#main-name");

    mainImage.addEventListener('change', function() {
            if (mainImage.files.length > 0) {
                mainName.value = mainImage.files[0].name;
            }
        });

    /* 프로젝트 메인 사진 첨부 시점에 프리뷰 생성 */
    const mainPreviewArea = document.querySelector("#main-preview-area");

    mainImage.addEventListener('change', () => { mainPreviewArea.style.display = 'block' });


    /* 프로젝트 서브 사진 프리뷰 */
    (function() {
        const subPreviewArea = document.querySelectorAll('.sub-preview-area');
        const subImages = document.querySelectorAll('.sub-image');

        subPreviewArea.forEach(item => item.addEventListener('click', open));

        subImages.forEach(item => item.addEventListener('change', subPreview));

        function open() {
            const index = Array.from(subPreviewArea).indexOf(this);
            subImages[index].click();
        }

        function subPreview() {
            const index = Array.from(subImages).indexOf(this);

            if(this.files && this.files[0]) {
                const reader = new FileReader();
                reader.readAsDataURL(this.files[0]);
                reader.onload =  function() {
                    subPreviewArea[index].innerHTML = `<img src='${reader.result}' class="sub-preview-image">`;
                }

            }
        }
    })();

    /* ------------------------------------------------------------------------------------ */

    /* 리워드 추가/삭제 버튼 기능 */
    const addBtn = document.querySelector('#add-reward');
    const removeBtn = document.querySelector('#remove-reward');
    const container = document.querySelector('.reward-container');
    let rewardCount = 1;
    let rewardNameCount = 0;
    let rewardPriceCount = 0;
    let rewardDueDateCount = 0;
    let rewardContentCount = 0;
    let shippingFeeName = "rewardList[0].rewardFee"; // 초기 값 설정 (리워드 배송비는 아래의 코드를 통해 추가되지 않기 때문에 리워드의 갯수 -1만큼 동적으로 증가하는 코드 작성)
                    
    addBtn.addEventListener('click', () => {
        const rewardBox = document.createElement('div');
        rewardBox.classList.add('reward-box');
        rewardBox.innerHTML = `
            <flex class="justify-between">
                <p class="title">리워드 ${++rewardCount}</p>
            </flex>
            <div class="reward-info">
                <flex>
                    <div>
                        <p class="title">* 리워드명</p>
                        <input type="text" name="rewardList[${++rewardNameCount}].rewardName" id="reward-name" class="input" maxlength="12" style="width:210px;" required>
                    </div>
                    <div>
                        <p class="title">* 리워드 금액</p>
                        <input type="number" name="rewardList[${++rewardPriceCount}].rewardPrice" id="reward-price" class="input" placeholder="단위 : 원" style="width:110px;" required>
                    </div>
                    <div>
                        <p class="title">* 리워드 예상 발송일</p>
                        <input type="date" name="rewardList[${++rewardDueDateCount}].rewardDueDate" id="expected-shipping" class="input" style="width:140px;" required>
                    </div>
                </flex>
                <div>
                    <p class="title">* 리워드 구성</p>
                    <input type="text" name="rewardList[${++rewardContentCount}].rewardContent" id="reward-contents" class="input" maxlength="55" placeholder="ex) 우드 다이어리, 산제로 샤프" style="width:670px;" required>
                </div>
            </div>
        `;
        container.appendChild(rewardBox);
        
        // shipping-fee의 name 속성값 동적으로 변경
	    shippingFeeName = `rewardList[${rewardCount - 1}].rewardFee`;
	    const shippingFee = document.querySelector('#shipping-fee');
	    shippingFee.setAttribute('name', shippingFeeName);
    });
                    
    removeBtn.addEventListener('click', () => {
        const rewardBoxes = document.querySelectorAll('.reward-box');
        if (rewardBoxes.length > 1) {
            const lastRewardBox = rewardBoxes[rewardBoxes.length - 1];
            container.removeChild(lastRewardBox);
            rewardCount--;
        } else {
            alert("리워드는 최소 1개 이상 존재 해야합니다 ☹")
        }
    });

    /* ------------------------------------------------------------------------------------ */

    /* 정산 확인 시, 세 가지의 서류가 첨부되면 파일명 보여주기 */
    const settleDoc = document.getElementById('settle-doc');
    const settleDocName = document.getElementById('settle-doc-name');
    const accountDoc = document.getElementById('account-doc');
    const accountDocName = document.getElementById('account-doc-name');
    const etcDoc = document.getElementById('etc-doc');
    const etcDocName = document.getElementById('etc-doc-name');

    settleDoc.addEventListener('change', function() {
    if (settleDoc.files.length > 0) {
        settleDocName.value = settleDoc.files[0].name;
    }
    });

    accountDoc.addEventListener('change', function() {
    if (accountDoc.files.length > 0) {
        accountDocName.value = accountDoc.files[0].name;
    }
    });

    etcDoc.addEventListener('change', function() {
    if (etcDoc.files.length > 0) {
        etcDocName.value = etcDoc.files[0].name;
    }
    });
    
    /* ------------------------------------------------------------------------------------ */
    
    /* 사용자가 입력한 목표 금액에 따른 정산 예상 금액 계산해주기 */
    const fundingGoalInput = document.getElementById('funding-goal');
	const settleGoal = document.getElementById('settle-goal');
	const settleManageFee = document.getElementById('settle-manage-fee');
	const settlePayFee = document.getElementById('settle-pay-fee');
	const settleTax = document.getElementById('settle-tax');
	const settleTotal = document.getElementById('settle-total');
	
	fundingGoalInput.addEventListener('input', (event) => {
		const value = parseInt(event.target.value);
	    const manageFee = Math.round(value * 0.07);
	    const payFee = Math.round(value * 0.03);
	    const tax = Math.round(value * 0.01);
	    const total = value - manageFee - payFee - tax;
	          
	    settleGoal.textContent = `${value.toLocaleString()}`;
	    settleManageFee.textContent = `${manageFee.toLocaleString()}`;
	    settlePayFee.textContent = `${payFee.toLocaleString()}`;
	    settleTax.textContent = `${tax.toLocaleString()}`;
	    settleTotal.textContent = `${total.toLocaleString()}`;
	});
    
    /* ------------------------------------------------------------------------------------ */

    /* 프로젝트 최종 버튼 클릭 시, 확인 confirm */
    document.querySelector(".submit").addEventListener('click', () => confirm('제출 후 수정은 불가합니다. 제출 하시겠습니까?'))


}