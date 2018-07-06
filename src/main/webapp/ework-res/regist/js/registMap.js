function mapShow(){
	$('#container').css('display','block');
	$('#myPageTop').css('display','block');
}
function mapClose(){
	$('#container').css('display','none');
	$('#myPageTop').css('display','none');
}

var map = new AMap.Map('container', {
     resizeEnable: true,
     zoom: 10,
     center: [117.214964, 31.841311]
 });

//设置DomLibrary，jQuery或者Zepto
AMapUI.setDomLibrary($);

//启动时一次加载所有依赖的组件
AMapUI.loadUI(['misc/PoiPicker'], function (PoiPicker) {
	//初始化页面
	console.log(_platCity);
	initPage(PoiPicker, _platCity);
});

 function initPage(PoiPicker, city) {
     var poiPicker = new PoiPicker({
         input: 'address', //输入框id,
         city: city
     });
     //初始化poiPicker
     poiPickerReady(poiPicker);
 }

 function poiPickerReady(poiPicker) {
     window.poiPicker = poiPicker;

     var marker = new AMap.Marker();
     var infoWindow = new AMap.InfoWindow({
         offset: new AMap.Pixel(0, -20)
     });

     //选取了某个POI
     poiPicker.on('poiPicked', function (poiResult) {
         var poi = poiResult.item,
             info = {
                 '编码': poi.id,
                 '搜索名称': poi.name,
                 '经纬度': poi.location.toString(),
                 '地址': poi.address
             };
         console.log(info);
         $('#address').val(poi.name+poi.address)

         marker.setMap(map);
         infoWindow.setMap(map);

         marker.setPosition(poi.location);
         infoWindow.setPosition(poi.location);

     //    infoWindow.setContent('搜索信息: <pre>' + JSON.stringify(info, null, 2) + '</pre>');
         infoWindow.open(map, marker.getPosition());
             
         map.setZoom(12)
         map.setCenter(marker.getPosition());
         
     });
    
 }