(function(){
	$(document).ready(function() {
		$(document).foundation();
		
		$('.js-datepicker').each(function(){
			$(this).fdatepicker({
				format: 'dd.mm.yyyy',
				closeButton: true
			});
		});
		
	});
}(jQuery));