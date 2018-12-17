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

//zones a1 to f2
function setTeamHeatmapShotGoals() {
	var a1 = $('#a1gs').val();
	var a2 = $('#a2gs').val();
	var b1 = $('#b1gs').val();
	var b2 = $('#b2gs').val();
	var c1 = $('#c1gs').val();
	var c2 = $('#c2gs').val();
	var d1 = $('#d1gs').val();
	var d2 = $('#d2gs').val();
	var e1 = $('#e1gs').val();
	var e2 = $('#e2gs').val();
	var f1 = $('#f1gs').val();
	var f2 = $('#f2gs').val();

	var max = Math.max(a1, a2, b1, b2, c1, c2, d1, d2, e1, e2, f1, f2);

	$('#a1gs rect').css({fill: getHslColor(a1, max)}).text(a1);
	$('#a2gs rect').css({fill: getHslColor(a2, max)}).text(a2);
	$('#b1gs rect').css({fill: getHslColor(b1, max)}).text(b1);
	$('#b2gs rect').css({fill: getHslColor(b2, max)}).text(b2);
	$('#c1gs rect').css({fill: getHslColor(c1, max)}).text(c1);
	$('#c2gs rect').css({fill: getHslColor(c2, max)}).text(c2);
	$('#d1gs rect').css({fill: getHslColor(d1, max)}).text(d1);
	$('#d2gs rect').css({fill: getHslColor(d2, max)}).text(d2);
	$('#e1gs rect').css({fill: getHslColor(e1, max)}).text(e1);
	$('#e2gs rect').css({fill: getHslColor(e2, max)}).text(e2);
	$('#f1gs rect').css({fill: getHslColor(f1, max)}).text(f1);
	$('#f2gs rect').css({fill: getHslColor(f2, max)}).text(f2);
}

//zones a1 to f2
function setTeamHeatmapTakenGoals() {
	var a1 = $('#a1gt').val();
	var a2 = $('#a2gt').val();
	var b1 = $('#b1gt').val();
	var b2 = $('#b2gt').val();
	var c1 = $('#c1gt').val();
	var c2 = $('#c2gt').val();
	var d1 = $('#d1gt').val();
	var d2 = $('#d2gt').val();
	var e1 = $('#e1gt').val();
	var e2 = $('#e2gt').val();
	var f1 = $('#f1gt').val();
	var f2 = $('#f2gt').val();

	var max = Math.max(a1, a2, b1, b2, c1, c2, d1, d2, e1, e2, f1, f2);

	$('#a1gt rect').css({fill: getHslColor(a1, max)}).text(a1);
	$('#a2gt rect').css({fill: getHslColor(a2, max)}).text(a2);
	$('#b1gt rect').css({fill: getHslColor(b1, max)}).text(b1);
	$('#b2gt rect').css({fill: getHslColor(b2, max)}).text(b2);
	$('#c1gt rect').css({fill: getHslColor(c1, max)}).text(c1);
	$('#c2gt rect').css({fill: getHslColor(c2, max)}).text(c2);
	$('#d1gt rect').css({fill: getHslColor(d1, max)}).text(d1);
	$('#d2gt rect').css({fill: getHslColor(d2, max)}).text(d2);
	$('#e1gt rect').css({fill: getHslColor(e1, max)}).text(e1);
	$('#e2gt rect').css({fill: getHslColor(e2, max)}).text(e2);
	$('#f1gt rect').css({fill: getHslColor(f1, max)}).text(f1);
	$('#f2gt rect').css({fill: getHslColor(f2, max)}).text(f2);
}

//zones a1 to f2
function setTeamHeatmapShotAssist() {
	var a1 = $('#a1as').val();
	var a2 = $('#a2as').val();
	var b1 = $('#b1as').val();
	var b2 = $('#b2as').val();
	var c1 = $('#c1as').val();
	var c2 = $('#c2as').val();
	var d1 = $('#d1as').val();
	var d2 = $('#d2as').val();
	var e1 = $('#e1as').val();
	var e2 = $('#e2as').val();
	var f1 = $('#f1as').val();
	var f2 = $('#f2as').val();

	var max = Math.max(a1, a2, b1, b2, c1, c2, d1, d2, e1, e2, f1, f2);

	$('#a1as rect').css({fill: getHslColor(a1, max)}).text(a1);
	$('#a2as rect').css({fill: getHslColor(a2, max)}).text(a2);
	$('#b1as rect').css({fill: getHslColor(b1, max)}).text(b1);
	$('#b2as rect').css({fill: getHslColor(b2, max)}).text(b2);
	$('#c1as rect').css({fill: getHslColor(c1, max)}).text(c1);
	$('#c2as rect').css({fill: getHslColor(c2, max)}).text(c2);
	$('#d1as rect').css({fill: getHslColor(d1, max)}).text(d1);
	$('#d2as rect').css({fill: getHslColor(d2, max)}).text(d2);
	$('#e1as rect').css({fill: getHslColor(e1, max)}).text(e1);
	$('#e2as rect').css({fill: getHslColor(e2, max)}).text(e2);
	$('#f1as rect').css({fill: getHslColor(f1, max)}).text(f1);
	$('#f2as rect').css({fill: getHslColor(f2, max)}).text(f2);
}

//zones a1 to f2
function setTeamHeatmapTakenAssist() {
	var a1 = $('#a1at').val();
	var a2 = $('#a2at').val();
	var b1 = $('#b1at').val();
	var b2 = $('#b2at').val();
	var c1 = $('#c1at').val();
	var c2 = $('#c2at').val();
	var d1 = $('#d1at').val();
	var d2 = $('#d2at').val();
	var e1 = $('#e1at').val();
	var e2 = $('#e2at').val();
	var f1 = $('#f1at').val();
	var f2 = $('#f2at').val();

	var max = Math.max(a1, a2, b1, b2, c1, c2, d1, d2, e1, e2, f1, f2);

	$('#a1at rect').css({fill: getHslColor(a1, max)}).text(a1);
	$('#a2at rect').css({fill: getHslColor(a2, max)}).text(a2);
	$('#b1at rect').css({fill: getHslColor(b1, max)}).text(b1);
	$('#b2at rect').css({fill: getHslColor(b2, max)}).text(b2);
	$('#c1at rect').css({fill: getHslColor(c1, max)}).text(c1);
	$('#c2at rect').css({fill: getHslColor(c2, max)}).text(c2);
	$('#d1at rect').css({fill: getHslColor(d1, max)}).text(d1);
	$('#d2at rect').css({fill: getHslColor(d2, max)}).text(d2);
	$('#e1at rect').css({fill: getHslColor(e1, max)}).text(e1);
	$('#e2at rect').css({fill: getHslColor(e2, max)}).text(e2);
	$('#f1at rect').css({fill: getHslColor(f1, max)}).text(f1);
	$('#f2at rect').css({fill: getHslColor(f2, max)}).text(f2);
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
	$(' #ZonesTeamGoalShot rect').hover(function () {
		$(this).css({opacity: 0.8});
		$('#goalShotValue').text($(this).text());
	}, function () {
		$(this).css({opacity: 1});
	});
	$(' #ZonesTeamAssistShot rect').hover(function () {
		$(this).css({opacity: 0.8});
		$('#assistShotValue').text($(this).text());
	}, function () {
		$(this).css({opacity: 1});
	});
	$(' #ZonesTeamGoalTaken rect').hover(function () {
		$(this).css({opacity: 0.8});
		$('#goalTakenValue').text($(this).text());
	}, function () {
		$(this).css({opacity: 1});
	});
	$(' #ZonesTeamAssistTaken rect').hover(function () {
		$(this).css({opacity: 0.8});
		$('#assistTakenValue').text($(this).text());
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

	$('#ZonesTeamGoalShot').hover(function () {
		//do nothing
	}, function () {
		$('#goalShotValue').text("-");
	});
	$('#ZonesTeamAssistShot').hover(function () {
		//do nothing
	}, function () {
		$('#assistShotValue').text("-");
	});
	$('#ZonesTeamGoalTaken').hover(function () {
		//do nothing
	}, function () {
		$('#goalTakenValue').text("-");
	});
	$('#ZonesTeamAssistTaken').hover(function () {
		//do nothing
	}, function () {
		$('#assistTakenValue').text("-");
	});
});
