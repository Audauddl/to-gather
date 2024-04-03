/*우측상단 시간설정 함수 */
function updateTime() {
    const currentTime = new Date();
    const formattedTime = currentTime.toISOString().replace('T', ' ').substring(0, 19);
    document.getElementById("current-time").innerText = formattedTime;
}
updateTime();