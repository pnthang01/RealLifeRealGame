<%@ page contentType="text/html; charset=utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$("#feedback-submit").on("click", function() {
			var valid = '';
			if(!checkLength($("#name"), 2, 30)){
				valid += "<p>Tên phải từ 2 đến 30 ký tự.</p>";
			}
			if(!checkEmail($("#email"))){
				valid += "<p>Vui lòng sử dụng email chính xác.</p>";
			}
			if(!checkLength($("#title"), 2, 30)){
				valid += "<p>Tiêu đề phải từ 2 đến 30 ký tự.</p>";
			}
			if(!checkLength($("#message"), 5, 500)){
				valid += "<p>Nội dung phải từ 5 đến 500 ký tự.</p>";
			}	
			if(valid.length == 0){
				$.ajax({
					type : "POST",
					url : $("#feedbackForm").attr("action"),
					data: $("#feedbackForm").serialize(),
					success : function(response) {
						if(response.status  == 'success'){
							popup('Cám ơn bạn đã góp ý kiến. Xin chân thành cám ơn!');
						} else {
							errorInfo = "";
							for(i = 0 ; i < response.result.length ; i++){
								errorInfo += "<p>" + response.result[i] + "</p>";
							}
							popup(errorInfo);
						}
					}
				});
			} else {
				popup(valid);
			}
		});
		
	    $('a.btn-ok, #dialog-overlay, #dialog-box').click(function () {        
	        $('#dialog-overlay, #dialog-box').hide();        
	        return false;
	    });
	    
	    // if user resize the window, call the same function again
	    // to make sure the overlay fills the screen and dialogbox aligned to center    
	    $(window).resize(function () {
	        
	        //only do it if the dialog box is not hidden
	        if (!$('#dialog-box').is(':hidden')) popup();        
	    }); 

	    // if user resize the window, call the same function again
	    // to make sure the overlay fills the screen and dialogbox aligned to center    
	    $(window).resize(function () {	        
	        //only do it if the dialog box is not hidden
	        if (!$('#dialog-box').is(':hidden')) popup();        
	    });
	});
	
	//Popup dialog
	function popup(message) {
	        
	    // get the screen height and width
	    var maskHeight = $(document).height();
	    var maskWidth = $(window).width();
	    
	    // calculate the values for center alignment
	    var dialogTop = (maskHeight/3) - ($('#dialog-box').height());
	    var dialogLeft = (maskWidth/2) - ($('#dialog-box').width()/2);
	    
	    // assign values to the overlay and dialog box
	    $('#dialog-overlay').css({height:maskHeight, width:maskWidth}).show();
	    $('#dialog-box').css({top:dialogTop, left:dialogLeft, position:'fixed'}).show();
	    // display the message
	    $('#dialog-message').html(message);
	            
	}
</script>
    <div id="dialog-overlay"></div>
    <div id="dialog-box">
        <div class="dialog-content">
            <div id="dialog-message"></div>
            <a href="#" class="button">Đóng</a>
        </div>
    </div>

<div class="footer-bottom" id="contact">
	<div class="container contact">
		<div class="col-md-4 contact-top_left">
			<h3>thông tin liên hệ</h3>
			<p>Real Life Real Game là một nhóm khởi nghiệp còn non trẻ. Với
				mong muốn xây dựng một hệ thống tự quản lý thời gian, sử dụng Trò
				chơi hóa(Gamification) để giúp cho những thanh thiếu niên hiện có
				thêm động lực để phấn đấu, cải thiện bản thân mình.</p>
			<ul class="contact_info">
				<li><i class="mobile"> </i><span>+84-090-975-9369</span></li>
				<li><i class="message"> </i><span class="msg">gamehoa@gmail.com</span></li>
			</ul>
		</div>
		<div class="col-md-8 contact-top">
			<h3>Hãy gửi cho chúng tôi phản hồi của bạn</h3>
			<form id="feedbackForm" method="post" action="<c:url value="feedback" />">
				<div class="to">
					<input type="text" class="text" id="name" name="name" value="Tên" onfocus="this.value = '';" 
					onblur="if (this.value == '') {this.value = 'Tên';}"> 
					<input type="text" class="text" id="email" name="email" value="Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}" style="margin-left: 20px">
					<input type="text" class="text" id="title" name="title" value="Tiêu đề" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Tiêu đề';}" style="margin-left: 20px">
				</div>
				<div class="contact_bottom-textarea">
					<textarea id="message" name="message" value="Tin nhắn" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Tin nhắn';}">Tin nhắn</textarea>
				</div>
				<div class="form-submit1">
					<input name="submit" type="button" id="feedback-submit" value="Gửi phản hồi"><br>
					<p class="m_msg">Xin hãy sử dụng email chính xác.</p>
				</div>
				<div class="clear"></div>
			</form>
		</div>
	</div>
	<div class="copy">
		<p>
			2014 Template by <a href="http://w3layouts.com" target="_blank">
				w3layouts</a>
		</p>
	</div>
</div>