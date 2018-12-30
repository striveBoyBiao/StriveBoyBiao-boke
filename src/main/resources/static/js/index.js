/**
 * Created by hebiao on 2018/12/28.
 */
/**初始化查询慢生活界面数据*/
$("document").ready(function(){
    $.ajax({
        url:"/main/findIndex.do" ,
        type:"post",
        dataType:"json" ,
        data:{
            "pageNo":"1"
        },
        success:function(data){
            var resultHtml = "<h2> <b>今日</b>推荐 </h2>";
            var newsHtml = "";
            var rankHtml = "";
            var recommendHtml = "";
            /**生成页面数据*/
            $.each(data.pageData,function(index,element){
                resultHtml+="<div class='blogs'>";
                resultHtml+="<figure><a  class='preview'  href='/main/findIndexDetails.do?cid="+element.cid+"'><img src='"+element.titlepicture+"' alt='"+element.title+"'></a></figure>";
                resultHtml+="<h3><a title='"+element.title+"' href='/main/findIndexDetails.do?cid="+element.cid+"'>"+element.title+"</a></h3><p>"+element.introduction+"</p>";
                resultHtml+="<ul><p class='autor'><span class='lm f_l'><a href='#'>"+element.typename+"</a></span><span class='dtime f_l'>"+element.gtmcreate+"</span> <span class='viewnum f_l'>浏览（88）</span> <span class='pingl f_'>评论（<a>0</a>）</span> </p></ul></div>";
            })
            /**生成最新文章数据*/
            $.each(data.newsData,function(index,element){
                newsHtml += "<li>";
                newsHtml += "<a href='/main/findIndexDetails.do?cid="+element.cid+"' title='"+element.title+"' >"+element.title+"</a>";
                newsHtml += "</li>";
            })
            /**生成最新排行数据*/
            $.each(data.rankData,function(index,element){
                rankHtml += "<li>";
                rankHtml += "<a href='/main/findIndexDetails.do?cid="+element.cid+"' title='"+element.title+"' >"+element.title+"</a>";
                rankHtml += "</li>";
            })
            /**生成最新排行数据*/
            $.each(data.recommendData,function(index,element){
                recommendHtml += "<li>";
                recommendHtml += "<a href='/main/findIndexDetails.do?cid="+element.cid+"' title='"+element.title+"' >"+element.title+"</a>";
                recommendHtml += "</li>";
            })

            $("#findIndex").html(resultHtml);
            $("#rankData").html(rankHtml);
            $("#newsData").html(newsHtml);
            $("#nominateData").html(recommendHtml);
        }
    })
})
