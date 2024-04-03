window.onload = function() {
    
    /* location.href 이동하고자 하는 곳으로 이동하는 자바스크립트 코드가 필요함 */
	const projectBlock = document.querySelectorAll(".project-block");

	projectBlock.forEach(block => {
			block.addEventListener("click", (e) => {
			const projNo = e.currentTarget.dataset.projNo;
			console.log(projNo);
			window.location.href = '/project/detail?projNo=' + projNo;
		});
	});
			
	/* ----------------------------------------------------------------------------------------- */

	
    /* 더보기 페이징 기능 */
    load('#more-projects', '8'); /* 기본 첫 화면에서 노출 될 프로젝트의 갯수 지정 */

    document.querySelector('.more-button').addEventListener('click', function() {
       load('#more-projects', '8', '#more-button');
    });

    /* more 버튼을 클릭할 때 마다 실행 될 함수 */
    function load(id, cnt, btn) {
        let viewProject = document.querySelectorAll(id + ' .loading:not(.active)'); /* loading 중 active가 아닌 것 */
        let viewLength = viewProject.length; /* 화면에 노출되지 않은 프로젝트들의 개수 */
        let viewTotalCount;
        
        if (cnt < viewLength) {
            viewTotalCount = cnt;
        } else {
           viewTotalCount = viewLength;
            document.querySelector('.more-button').style.display = 'none'; /* 더이상 로드될 프로젝트가 없는 경우 more 버튼 display:none */
        }

        for (let i = 0; i < viewTotalCount; i++) {
            viewProject[i].classList.add('active'); 
        }
    }

}