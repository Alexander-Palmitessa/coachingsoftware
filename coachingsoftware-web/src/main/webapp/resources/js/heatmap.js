	var max;
	var min;

	//zones a1 to f2
	function setHeatmapGoals(a1, a2, b1, b2, c1, c2, d1, d2, e1, e2, f1, f2) {
		min = 0;
		max = Math.max(a1, a2, b1, b2, c1, c2, d1, d2, e1, e2, f1, f2);

		$('#a1g rect').css({fill: getHslColor(a1)}).text(a1);
		$('#a2g rect').css({fill: getHslColor(a2)}).text(a2);
		$('#b1g rect').css({fill: getHslColor(b1)}).text(b1);
		$('#b2g rect').css({fill: getHslColor(b2)}).text(b2);
		$('#c1g rect').css({fill: getHslColor(c1)}).text(c1);
		$('#c2g rect').css({fill: getHslColor(c2)}).text(c2);
		$('#d1g rect').css({fill: getHslColor(d1)}).text(d1);
		$('#d2g rect').css({fill: getHslColor(d2)}).text(d2);
		$('#e1g rect').css({fill: getHslColor(e1)}).text(e1);
		$('#e2g rect').css({fill: getHslColor(e2)}).text(e2);
		$('#f1g rect').css({fill: getHslColor(f1)}).text(f1);
		$('#f2g rect').css({fill: getHslColor(f2)}).text(f2);
		$('#Zones rect').hover(function () {
			$(this).css({opacity: 0.8});
			$(' h1').text($(this).text());
		}, function () {
			$(this).css({opacity: 1});
		});
		$('#Zones').hover(function () {
			//do nothing
		}, function () {
			$(' h1').text("-");
		});
	}

	//zones a1 to f2
	function setHeatmapAssist(a1, a2, b1, b2, c1, c2, d1, d2, e1, e2, f1, f2) {
		min = 0;
		max = Math.max(a1, a2, b1, b2, c1, c2, d1, d2, e1, e2, f1, f2);

		$('#a1a rect').css({fill: getHslColor(a1)}).text(a1);
		$('#a2a rect').css({fill: getHslColor(a2)}).text(a2);
		$('#b1a rect').css({fill: getHslColor(b1)}).text(b1);
		$('#b2a rect').css({fill: getHslColor(b2)}).text(b2);
		$('#c1a rect').css({fill: getHslColor(c1)}).text(c1);
		$('#c2a rect').css({fill: getHslColor(c2)}).text(c2);
		$('#d1a rect').css({fill: getHslColor(d1)}).text(d1);
		$('#d2a rect').css({fill: getHslColor(d2)}).text(d2);
		$('#e1a rect').css({fill: getHslColor(e1)}).text(e1);
		$('#e2a rect').css({fill: getHslColor(e2)}).text(e2);
		$('#f1a rect').css({fill: getHslColor(f1)}).text(f1);
		$('#f2a rect').css({fill: getHslColor(f2)}).text(f2);
		$('#Zones rect').hover(function () {
			$(this).css({opacity: 0.8});
			$(' h1').text($(this).text());
		}, function () {
			$(this).css({opacity: 1});
		});
		$('#Zones').hover(function () {
			//do nothing
		}, function () {
			$(' h1').text("-");
		});
	}

	//sets the HSL color from green (min) to red (max)
	function getHslColor(value) {
		var percentage = value / max;
		var hue = (percentage * (0 - 120)) + 120;
		return 'hsl(' + hue + ', 100%, 50%)';
	}
