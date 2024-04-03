window.onload = function() {
	    
	    
	    /* 메인 배너 슬라이드 */
	    /*const slides = document.querySelectorAll('.slide');
	    const slider = document.querySelector('.slider');
	    const prevBtn = document.querySelector('.prev');
	    const nextBtn = document.querySelector('.next');
	      
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
	      
	    function prevSlide() {
	        currentSlide--;
	        showSlide(currentSlide);
	    }
	      
	    function nextSlide() {
	        currentSlide++;
	        showSlide(currentSlide);
	    }
	
	    prevBtn.addEventListener('click', () => {
	        clearInterval(slideInterval);
	        prevSlide();
	    });
	      
	    nextBtn.addEventListener('click', () => {
	        clearInterval(slideInterval);
	        nextSlide();
	    });
	      
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
	    }, 2500);*/
	    
	    
/*	    /* 프로젝트 블럭 클릭 시 해당 프로젝트 페이지로 이동 */
	    const projectBlock = document.querySelectorAll(".project-block");

			projectBlock.forEach(block => {
			  block.addEventListener("click", (e) => {
			    const projNo = e.currentTarget.dataset.projNo;
			    console.log(projNo);
			    window.location.href = '/project/detail?projNo=' + projNo;
			  });
			});*/

}