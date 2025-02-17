let idFlag = false;
let pwdFlag = false;
let nicknameFlag = false;

document.getElementById("signupBtn").addEventListener("click", async () => {
	// 비밀번호 일치 확인
	const pwd = document.querySelector("#pwd").value;
	const pwd2 = document.querySelector("#pwd2").value;
	console.log("pwd : ",pwd)
	console.log("pwd2 : ",pwd2)
	if (pwd == pwd2) {
		pwdFlag = true;
	}
	await checkNickname();
	console.log("idFlag",idFlag)
	console.log("pwdFlag",pwdFlag)
	console.log("nicknameFlag",nicknameFlag)
	//id Flag,pw Flag, nickname Flag == true 이면 회원가입
	if (idFlag && pwdFlag && nicknameFlag) {
		console.log("폼 전송")
		const form = document.querySelector("#joinForm");
		form.submit();
	}
	// id 중복확인
	// pw, pw2 일치 확인
	// nickname 중복확인
});
document.getElementById("checkIdBtn").addEventListener("click", () => {
	const id = document.querySelector("#userid").value;
	fetch(`/signup/checkParam?param_name=id&param_value=${id}`)
		.then(response => response.text())
		.then(result => {
			console.log("id , result : ",result)
			if (result == "true") {
				idFlag = true;
			}
			console.log("idFlag : ",idFlag)
		})
})
async function checkNickname() {
	const nickname = document.querySelector("#nickname").value;
	await fetch(`/signup/checkParam?param_name=nickname&param_value=${nickname}`)
		.then(response => response.text())
		.then(result => {
			console.log("result : ", result)
			if (result == "true") {
				nicknameFlag = true;
			}
		})
}