$(function() {
    $('#btnLogin').on("click", function(){
        	var memberId = $('#memberId').val();
        	if(memberId == '') {
        	    alert('아이디를 입력해주세요.');
        	    return;
        	}
        	var password = $('#password').val();
        	if(password == '') {
        	    alert('비밀번호를 입력해주세요.');
        	    return;
        	}
            var data = {memberId : memberId, password : password};
            	$.ajax({
            		type : 'post',
            		url : '/login',
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