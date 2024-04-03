window.onload = function () {

    /* SNS 공유 */
    const shareTwitter = document.querySelector('#share-twitter')
    const shareFacebook = document.querySelector('#share-facebook')
    const shareKaStory = document.querySelector('#share-ka-story')

        /* 트위터 */
        shareTwitter.addEventListener('click', () => {
            const sendText = '트위터로 공유합니다 :)';
            const pageUrl = 'https://www.ohmycompany.com/reward/12675';
            window.open(`https://twitter.com/intent/tweet?text=${sendText}&url=${pageUrl}`);
        });
        
        /* 페이스북 */
        shareFacebook.addEventListener('click', () => {
            const pageUrl = 'https://www.ohmycompany.com/reward/12675';
            window.open(`http://www.facebook.com/sharer/sharer.php?u=${pageUrl}`);
        });

        /* 카카오톡 */
        Kakao.init('b1a06d9120f68d41cd7cd66ca64e13e8'); /* 발급받은 자바스크립트 키 */

        Kakao.Link.createCustomButton({
            container: '#share-ka-talk',
            templateId: 91364, /* 앱 Id */
        });

        /* 카카오스토리 */
        shareKaStory.addEventListener('click', () => {
            Kakao.Story.share({
                url: '카카오스토리로 공유합니다 :)',
                text: '생분해 나무로 만든 숲내음 다이어리'
            })
        });
}