var owl = '';

$(document).ready(function() {
	
	$.ajax({
		url :"https://www.partainasdem.org:8443/news/home",
		processData : false,
		crossDomain: true,
		type : 'GET',
		dataType : 'jsonp',
		statusCode : {
			401 : function() {
				window.location.replace("/login?logout");
			}
		},
		success : function(response) {
			
			
		var strVar="";
			strVar += "					<div class=\"col-xs-12 col-md-4\">";
			strVar += "						<img src=\"\" alt=\"News 1\"><\/img>";
			strVar += "						<div class=\"date\"><\/div>";
			strVar += "						<a th:href=\"@{#}\">";
			strVar += "							<h1 class=\"judul\"><\/h1>";
			strVar += "						<\/a>";
			strVar += "					<\/div>";
			
//				var newsDate = new Date(response[0].createdTime);
				$('.news-main').find('div.date').html(response.berita_utama[0].date_show);				
				$('.news-main').find('a > h1').html(response.berita_utama[0].article_headline)
				$('.news-main').find('a').attr('href','https://partainasdem.org/news/'+response.berita_utama[0].category_slug+'/'+response.berita_utama[0].article_slug+'/');
				$('.news-main').find('div.headLine').html(response.berita_utama[0].article_description)
				$('.news-main').find('img').attr('src','https://partainasdem.org:8443/files/thumb/'+response.berita_utama[0].article_image+'/290/200/fit/');
				
				for (var i = 0; i < response.berita_terbaru.length; i++) {

					$('.news-home').append(strVar);
					$('.news-home  > div:last-child').find('img').attr('src','https://partainasdem.org:8443/files/thumb/'+response.berita_terbaru[i].article_image+'/290/200/fit/');
					$('.news-home  > div:last-child').find('a').attr('href','https://partainasdem.org/news/'+response.berita_terbaru[i].category_slug+'/'+response.berita_terbaru[i].article_slug+'/');
					$('.news-home  > div:last-child').find('h1.judul').html(response.berita_terbaru[i].article_headline);
	
//					var newsDate = new Date(response[i].createdTime);
					$('.news-home  > div:last-child').find('div.date').html(response.berita_terbaru[i].date_show);				
				}

		}
	}); // END $.ajax({
	
//	$.ajax({
//		url : "https://partainasdem.org:8443/front/api/news/list/page/0/rows/3",
//		processData : false,
//		type : 'GET',
//		contentType : 'application/json',
//		statusCode : {
//			401 : function() {
//				window.location.replace("/login?logout");
//			}
//		},
//		success : function(response) {
//			
//			var strVar="";
//			strVar += "					<div class=\"col-xs-12 col-md-4\">";
//			strVar += "						<img src=\"http:\/\/cdn.metrotvnews.com\/dynamic\/content\/2015\/04\/23\/390596\/WW0fAExJz6.jpg?w=668\" alt=\"News 1\"><\/img>";
//			strVar += "						<div class=\"date\">Kamis, 23 April 2015<\/div>";
//			strVar += "						<a th:href=\"@{#}\">";
//			strVar += "							<h1 class=\"judul\">Hasilkan 6 Kesimpulan, Parlemen Asia Afrika Dukung Kemerdekaan Palestina<\/h1>";
//			strVar += "						<\/a>";
//			strVar += "					<\/div>";
//			
//
//			for (var i = 0; i < response.length; i++) {
//				
//				$('.news-home').append(strVar);
//				$('.news-home  > div:last-child').find('img').attr('src','https://partainasdem.org:8443/upload/'+response[i].primaryImage.url);
//				$('.news-home  > div:last-child').find('a').attr('href','https://partainasdem.org:8443/news/'+response[i].id);
//				$('.news-home  > div:last-child').find('h1.judul').html(response[i].title);
//
//				var newsDate = new Date(response[i].createdTime);
//				$('.news-home  > div:last-child').find('div.date').html(newsDate.format('l\\, d F Y H\\:m T'));				
//
//			}
//
//		}
//	}); // END $.ajax({	

});