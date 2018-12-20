function setTimelineGoals() {
	var oneS = parseInt($('#oneS').val());
	var oneG = parseInt($('#oneG').val());
	var twoS = parseInt($('#twoS').val());
	var twoG = parseInt($('#twoG').val());
	var threeS = parseInt($('#threeS').val());
	var threeG = parseInt($('#threeG').val());
	var fourS = parseInt($('#fourS').val());
	var fourG = parseInt($('#fourG').val());
	var fiveS = parseInt($('#fiveS').val());
	var fiveG = parseInt($('#fiveG').val());
	var sixS = parseInt($('#sixS').val());
	var sixG = parseInt($('#sixG').val());

	var max;
	var min;
	var height = $('#_1_score rect').height();

	var tagGoalMap = [
		{tagS: '#_1_score rect', tagG: '#_1_get rect', goalS: oneS, goalG: oneG},
		{tagS: '#_2_score rect', tagG: '#_2_get rect', goalS: twoS, goalG: twoG},
		{tagS: '#_3_score rect', tagG: '#_3_get rect', goalS: threeS, goalG: threeG},
		{tagS: '#_4_score rect', tagG: '#_4_get rect', goalS: fourS, goalG: fourG},
		{tagS: '#_5_score rect', tagG: '#_5_get rect', goalS: fiveS, goalG: fiveG},
		{tagS: '#_6_score rect', tagG: '#_6_get rect', goalS: sixS, goalG: sixG}
	];

	min = 0;
	max = Math.max(oneS - oneG, twoS - twoG, threeS - threeG, fourS - fourG, fiveS - fiveG, sixS - sixG);

	tagGoalMap.forEach(function (map) {
		if (map.goalS - map.goalG > 0) {
			$(map.tagS).height(height * (map.goalS - map.goalG) / max);
			$(map.tagG).height(0);
		} else if (map.goalS - map.goalG < 0) {
			$(map.tagG).height(-(height * (map.goalS - map.goalG) / max));
			$(map.tagS).height(0);
		} else {
			$(map.tagS).height(0);
			$(map.tagG).height(0);
		}
		$(map.tagS).text(map.goalS + " / " + map.goalG);
		$(map.tagG).text(map.goalS + " / " + map.goalG);
	})
}

$(document).ready(function () {
	$('#Bars rect').hover(function () {
		$(this).css({opacity: 0.8});
		if ($(this).text() === "") {
			$('#timelineValue').text("-");
		} else {
			$('#timelineValue').text($(this).text());
		}
	}, function () {
		$(this).css({opacity: 1});
		$('#timelineValue').text("-");
	});
});
