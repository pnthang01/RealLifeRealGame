function DropDown(el) {
	this.dd = el;
	this.initEvents();
}
DropDown.prototype = {
	initEvents : function() {
		var obj = this;

		obj.dd.on('click', function(event) {
			$(this).toggleClass('active');
			event.stopPropagation();
		});
	}
};

function checkLength(o, min, max) {
	if (o.val().length > max || o.val().length < min) {
		return false;
	} else {
		return true;
	}
}
function checkRegexp(o, regexp, tip) {
	if (!(regexp.test(o.val()))) {
		o.addClass("ui-state-error");
		updateTips(tip);
		return false;
	} else {
		return true;
	}
}
function checkMatch(o1, o2, tip){
	if (o1.val() != o2.val()) {
		o.addClass("ui-state-error");
		updateTips(tip);
		return false;
	} else {
		return true;
	}
}
function checkEmail(o){
	var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
	if(pattern.test(o.val())){
		return true;
	} else {
		return false;
	}
}
function checkUsername(o){
	var usernameRegex = new RegExp("^[a-zA-Z0-9_-]{3,16}");
	if(usernameRegex.test(o.val())){
		return true;
	} else {
		return false;
	}
}
function checkPassword(o){
	var passwordRegex = new RegExp("^[a-z0-9_-]{6,18}$");
	if(passwordRegex.test(o.val())){
		return true;
	} else {
		return false;
	}
}
