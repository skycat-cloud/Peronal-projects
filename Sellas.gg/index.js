import config from './myapi.js';

const API_KEY = config.API_KEY;

const headers = {
  'x-nxopen-api-key': API_KEY,
};

function getCharacterOcid() {
  // 입력된 캐릭터 식별자를 가져옴
  const characterOcid = document.getElementById('characterNameInput').value;
  const urlString = `https://open.api.nexon.com/maplestory/v1/id?character_name=${encodeURIComponent(
    characterOcid
  )}`;

  fetch(urlString, { headers })
    .then((response) => {
      if (!response.ok) {
        return response.json().then((errorData) => {
          throw new Error(
            `오류가 발생했습니다. 상태: ${response.status}, 메시지: ${
              response.statusText
            }, 상세: ${errorData.message || '오류'}`
          );
        });
      }
      return response.json();
    })
    .then((data) => {
      console.log(data); // 콘솔에 데이터 출력
      getCharacterBasic(data.ocid); // OCID를 가져온 후 캐릭터 기본 정보를 가져옴
    })
    .catch((error) => {
      console.error('오류가 발생했습니다:', error);
      document.getElementById('resultOcidHtml').innerText = error.message;
    });
}

function getCharacterBasic(ocid) {
  const urlString = `https://open.api.nexon.com/maplestory/v1/character/basic?ocid=${encodeURIComponent(
    ocid
  )}`;

  fetch(urlString, { headers })
    .then((response) => {
      if (!response.ok) {
        return response.json().then((errorData) => {
          throw new Error(
            `오류가 발생했습니다. 상태: ${response.status}, 메시지: ${
              response.statusText
            }, 상세: ${errorData.message || '오류'}`
          );
        });
      }
      return response.json();
    })
    .then((data) => {
      console.log(data); // 콘솔에 데이터 출력
      displayResultBasic(data);
    })
    .catch((error) => {
      console.error('오류가 발생했습니다:', error);
      document.getElementById('resultBasicHtml').innerText = error.message;
    });
}

function displayResultBasic(data) {
  const resultBasic = document.getElementById('resultBasicHtml');
  resultBasic.innerHTML = `
    <p>${data.character_name}</p>
    <p><strong>레벨:</strong> ${data.character_level}</p>
    <p><strong>직업:</strong> ${data.character_class}</p>
    <p><strong>월드:</strong> ${data.world_name}</p>
    <img src="${data.character_image}" alt="${data.character_name} 이미지" />
  `;
}

function handleEnterKey(event) {
  if (event.key === 'Enter') {
    getCharacterOcid();
  }
}

document
  .getElementById('characterNameInput')
  .addEventListener('keydown', handleEnterKey);

window.getCharacterOcid = getCharacterOcid;
window.getCharacterBasic = getCharacterBasic;
window.displayResultBasic = displayResultBasic;
