window.onload = function() {

        const menuItems = document.querySelectorAll('.menu-item');
        menuItems.forEach(item => {
            item.addEventListener('click', () => {
                console.log(item.textContent + ' clicked');
            });
            // 아래 코드 추가
             item.addEventListener('transitionend', () => {
                 if (item.style.opacity === '0') { // opacity 값을 체크하여 요소가 보이지 않을 때에만 transform 값을 재설정
                    item.style.transform = 'translateY(10px)';
                }
            });
        });
        
        

}