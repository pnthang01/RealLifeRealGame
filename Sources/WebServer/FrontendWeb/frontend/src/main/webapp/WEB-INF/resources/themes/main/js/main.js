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
}

function checkLength(o, min, max) {
	if (o.val().length > max || o.val().length < min) {
		return false;
	} else {
		return true;
	}
}
function checkRegexp(o, regexp) {
	if (!(regexp.test(o.val()))) {
		return false;
	} else {
		return true;
	}
}