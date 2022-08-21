$(function() {
    $('#btnLogin').on("click", function(){
        	var userId = $('#userId').val();
        	if(userId == '') {
        	    alert('아이디를 입력해주세요.');
        	    return;
        	}
        	var password = $('#password').val();
        	if(password == '') {
        	    alert('비밀번호를 입력해주세요.');
        	    return;
        	}
            var data = {userId : userId, password : password};
            	$.ajax({
            		type : 'post',
            		url : '/user/login',
            		data : data,
            		success : function(result) {
            		    if(result == 'fail'){
                        	alert('아이디 또는 비밀번호가 일치하지 않습니다.');
                        } else {
                            var url = '/';
                            ulr = encodeURIComponent(url);
                        	location.href = url;
                        }
            		},
            		error : function() {
            		    alert('실패!');
            		}
            	});
        });
})