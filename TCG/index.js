document.addEventListener('DOMContentLoaded', () => {
  const card = document.querySelector('.card');
  const container = document.querySelector('.card-container');
  let timeoutId;
  const overlay = document.querySelector('.overlay');

  container.addEventListener('mousemove', (e) => {
    // 이전에 설정된 타이머가 있다면 취소
    if (timeoutId) {
      clearTimeout(timeoutId);
    }
    const containerRect = container.getBoundingClientRect();
    const x = e.clientX - containerRect.left;
    const y = e.clientY - containerRect.top;

    const centerX = containerRect.width / 2;
    const centerY = containerRect.height / 2;

    // X축 회전 각도: 상하 기울기
    const rotateX = ((y - centerY) / centerY) * -23;
    // Y축 회전 각도: 좌우 기울기
    const rotateY = ((x - centerX) / centerX) * 23;

    // 카드의 퍼스펙티브 효과 적용
    card.style.transform = `rotateX(${rotateX}deg) rotateY(${rotateY}deg)`;
    overlay.style.transform = `rotateX(${rotateX}deg) rotateY(${rotateY}deg)`;
  });

  container.addEventListener('mouseleave', () => {
    // n초 후에 카드의 회전을 초기 상태로 되돌리기 위한 타이머 설정
    timeoutId = setTimeout(() => {
      card.style.transform = 'rotateX(0) rotateY(0)';
    }, 200); //ms 단위
  });
});
