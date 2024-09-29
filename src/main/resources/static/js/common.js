function addNum(a,b) {
	return a+b;
}

function errorMessage(code) {
	if (code == "ex") {
		return "서버 에러";
	} else if (code == "bind") {
		return "입력(요청)값 오류";
	}
}