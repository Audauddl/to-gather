window.onload = function() {

    /* 메뉴 탭 클릭 시 스토리 or 소식&후기 화면 전환 */
    const menuList = document.querySelectorAll('.story-bar .menu li');
    const contents = document.querySelectorAll('.story-body .content');
    let activeCont = ''; // 현재 활성화된 컨텐츠 (기본으로 스토리 활성화)

    for(let i = 0; i < menuList.length; i++) {
        menuList[i].querySelector('.btn').addEventListener('click', function(e){
            e.preventDefault();
                    
             for(let j = 0; j < menuList.length; j++) {
                menuList[j].classList.remove('is_on'); /* 나머지 버튼 클래스 제거 */

                contents[j].style.display = 'none'; /* 나머지 컨텐츠 display:none 처리 */
            }

            /* 버튼 관련 이벤트 */
            this.parentNode.classList.add('is_on');

            /* 버튼 클릭 시 컨텐츠 전환 */
            activeCont = this.getAttribute('href');
            document.querySelector(activeCont).style.display = 'block';
        });
    }

    /* 프로젝트 이미지 슬라이드 */
    const slides = document.querySelectorAll('.slide');
    const slider = document.querySelector('.slider');

    let currentSlide = 0;
    let slideInterval;
      
    function showSlide(n) {
        if (n < 0) {
            currentSlide = slides.length - 1;
        } else if (n >= slides.length) {
            currentSlide = 0;
        }
        for (let i = 0; i < slides.length; i++) {
            if (i < currentSlide) {
                slides[i].classList.add('slide-left');
                slides[i].classList.remove('slide-right', 'current-slide');
            } else if (i === currentSlide) {
                slides[i].classList.add('current-slide');
                slides[i].classList.remove('slide-right', 'slide-left');
            } else {
                slides[i].classList.add('slide-right');
                slides[i].classList.remove('slide-left', 'current-slide');
            }
        }
    }
      
    function nextSlide() {
        currentSlide++;
        showSlide(currentSlide);
    }

    slider.addEventListener('mouseover', () => {
        clearInterval(slideInterval);
    });
      
    slider.addEventListener('mouseout', () => {
        slideInterval = setInterval(() => {
          nextSlide();
        }, 2500);
    });
      
    slideInterval = setInterval(() => {
        nextSlide();
    }, 2500);

	/* 공유하기 버튼 클릭 시, 팝업창 띄우기 */
	const openPopUp = document.querySelector('#share-box')
	
	openPopUp.onclick = function(){
		let option = "width=500px, height=300px, toolbars=no, scrollbars=no";
		let openUrl = '/project/share'
		window.open(openUrl, 'shareProject', option);
	}
	  

		
}

