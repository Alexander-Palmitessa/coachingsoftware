//zones a1 to f2
function setHeatmapGoals() {
	var a1 = $('#a1g').val();
	var a2 = $('#a2g').val();
	var b1 = $('#b1g').val();
	var b2 = $('#b2g').val();
	var c1 = $('#c1g').val();
	var c2 = $('#c2g').val();
	var d1 = $('#d1g').val();
	var d2 = $('#d2g').val();
	var e1 = $('#e1g').val();
	var e2 = $('#e2g').val();
	var f1 = $('#f1g').val();
	var f2 = $('#f2g').val();

	var max = Math.max(a1, a2, b1, b2, c1, c2, d1, d2, e1, e2, f1, f2);

	$('#a1g rect').css({fill: getHslColor(a1, max)}).text(a1);
	$('#a2g rect').css({fill: getHslColor(a2, max)}).text(a2);
	$('#b1g rect').css({fill: getHslColor(b1, max)}).text(b1);
	$('#b2g rect').css({fill: getHslColor(b2, max)}).text(b2);
	$('#c1g rect').css({fill: getHslColor(c1, max)}).text(c1);
	$('#c2g rect').css({fill: getHslColor(c2, max)}).text(c2);
	$('#d1g rect').css({fill: getHslColor(d1, max)}).text(d1);
	$('#d2g rect').css({fill: getHslColor(d2, max)}).text(d2);
	$('#e1g rect').css({fill: getHslColor(e1, max)}).text(e1);
	$('#e2g rect').css({fill: getHslColor(e2, max)}).text(e2);
	$('#f1g rect').css({fill: getHslColor(f1, max)}).text(f1);
	$('#f2g rect').css({fill: getHslColor(f2, max)}).text(f2);
}

//zones a1 to f2
function setHeatmapAssist() {
	var a1 = $('#a1a').val();
	var a2 = $('#a2a').val();
	var b1 = $('#b1a').val();
	var b2 = $('#b2a').val();
	var c1 = $('#c1a').val();
	var c2 = $('#c2a').val();
	var d1 = $('#d1a').val();
	var d2 = $('#d2a').val();
	var e1 = $('#e1a').val();
	var e2 = $('#e2a').val();
	var f1 = $('#f1a').val();
	var f2 = $('#f2a').val();

	var max = Math.max(a1, a2, b1, b2, c1, c2, d1, d2, e1, e2, f1, f2);

	$('#a1a rect').css({fill: getHslColor(a1, max)}).text(a1);
	$('#a2a rect').css({fill: getHslColor(a2, max)}).text(a2);
	$('#b1a rect').css({fill: getHslColor(b1, max)}).text(b1);
	$('#b2a rect').css({fill: getHslColor(b2, max)}).text(b2);
	$('#c1a rect').css({fill: getHslColor(c1, max)}).text(c1);
	$('#c2a rect').css({fill: getHslColor(c2, max)}).text(c2);
	$('#d1a rect').css({fill: getHslColor(d1, max)}).text(d1);
	$('#d2a rect').css({fill: getHslColor(d2, max)}).text(d2);
	$('#e1a rect').css({fill: getHslColor(e1, max)}).text(e1);
	$('#e2a rect').css({fill: getHslColor(e2, max)}).text(e2);
	$('#f1a rect').css({fill: getHslColor(f1, max)}).text(f1);
	$('#f2a rect').css({fill: getHslColor(f2, max)}).text(f2);
}

//sets the HSL color from green (min) to red (max)
function getHslColor(value, max) {
	var percentage = value / max;
	var hue = (percentage * (0 - 120)) + 120;
	return 'hsl(' + hue + ', 100%, 50%)';
}

$(document).ready(function () {
	$(' #ZonesGoal rect').hover(function () {
		$(this).css({opacity: 0.8});
		$('#goalValue').text($(this).text());
	}, function () {
		$(this).css({opacity: 1});
	});
	$(' #ZonesAssist rect').hover(function () {
		$(this).css({opacity: 0.8});
		$('#assistValue').text($(this).text());
	}, function () {
		$(this).css({opacity: 1});
	});

	$('#ZonesGoal').hover(function () {
		//do nothing
	}, function () {
		$('#goalValue').text("-");
	});
	$('#ZonesAssist').hover(function () {
		//do nothing
	}, function () {
		$('#assistValue').text("-");
	});
});
