let idFlag = false;
let pwdFlag = false;
let nicknameFlag = false;

document.getElementById("signupBtn").addEventListener("click", () => {
	// 비밀번호 일치 확인
	const pwd = document.querySelector("#pwd").value;
	const pwd2 = document.querySelector("#pwd2").value;
	if (pwd == pwd2) {
		pwdFlag = true;
	}


	//id Flag,pw Flag, nickname Flag == true 이면 회원가입
	if (idFlag && pwdFlag && nicknameFlag) {
		const form = document.querySelector("#joinForm");
		form.submit();
	}
	// id 중복확인
	// pw, pw2 일치 확인
	// nickname 중복확인
});
document.getElementById("checkIdBtn").addEventListener("click", () => {
	const id = document.querySelector("id").value;
	fetch(`/signup/checkParam?param_name=id&param_value=${id}`)
		.then(response => response.text())
		.then(result => {
			if (result == "true") {
				idFlag = true;
			}
		})
})
function checkNickname() {
	const nickname = document.querySelector("nickname").value;
	fetch(`/signup/checkParam?param_name=nickname&param_value=${nickname}`)
		.then(response => response.text())
		.then(result => {
			if (result == "true") {
				nicknameFlag = true;
			}
		})
}