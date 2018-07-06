<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>现场视频监控</title>
<script src="https://open.ys7.com/sdk/js/1.1/ezuikit.js"></script>
<style>
body {
	margin: 50px;
	text-align: center;
}

video {
	max-width: 1200px;
	width: 100%;
}
</style>
</head>

<body>
	<video id="myPlayer" controls playsInline webkit-playsinline autoplay>
	<source
		src="http://hls.open.ys7.com/openlive/f01018a141094b7fa138b9d0b856507b.hd.m3u8"
		type="application/x-mpegURL" /> <!-- <source src="rtmp://rtmp.open.ys7.com/openlive/f01018a141094b7fa138b9d0b856507b.hd" type="" /> -->
	</video>

	<script>
		var player = new EZUIPlayer('myPlayer');
		player.on('error', function() {
			console.log('error');
		});
		player.on('play', function() {
			console.log('play');
		});
		player.on('pause', function() {
			console.log('pause');
		});
	</script>
</body>
</html>