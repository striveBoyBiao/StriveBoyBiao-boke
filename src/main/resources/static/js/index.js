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
            /**生成页面数据*/
            $.each(data.pageDate,function(index,element){
                resultHtml+="<div class='blogs'>";
                resultHtml+="<figure><a  class='preview'  href='/main/findIndexDetails.do?cid="+element.cid+"'><img src='"+element.titlepicture+"' alt='"+element.title+"'></a></figure>";
                resultHtml+="<h3><a title='"+element.title+"' href='/main/findIndexDetails.do?cid="+element.cid+"'>"+element.title+"</a></h3><p>"+element.introduction+"</p>";
                resultHtml+="<ul><p class='autor'><span class='lm f_l'><a href='#'>"+element.typename+"</a></span><span class='dtime f_l'>"+element.gtmcreate+"</span> <span class='viewnum f_l'>浏览（88）</span> <span class='pingl f_'>评论（<a>0</a>）</span> </p></ul></div>";
            })
            /**生成最新文章数据*/
            $.each(data.newsDate,function(index,element){
                newsHtml += "<li>";
                newsHtml += "<a href='/main/findlifeDetails.do?cid="+element.cid+"' title='"+element.title+"' >"+element.title+"</a>";
                newsHtml += "</li>";
            })
            /**生成最新排行数据*/
            $.each(data.rankDate,function(index,element){
                rankHtml += "<li>";
                rankHtml += "<a href='/main/findlifeDetails.do?cid="+element.cid+"' title='"+element.title+"' >"+element.title+"</a>";
                rankHtml += "</li>";
            })

            $("#findIndex").html(resultHtml);
            $("#lifenewsdate").html(newsHtml);
            $("#liferankdate").html(rankHtml);
            pageInfo(data.pageNo,data.pageCount);
        }
    })
})



/**点击慢生活--栏目导航--查询相对应数据*/
function queryLanMu(type) {
    $.ajax({
        url:"/main/findLife.do" ,
        type:"post",
        dataType:"json" ,
        data:{
            "pageNo":"1",
            "type":type
        },
        success:function(data){
            var resultHtml = "";
            $.each(data.pageDate,function(index,element){
                resultHtml+="<h2><a title='"+element.title+"'href='#'>"+element.title+"</a></h2>";
                resultHtml+="<p class='dateview'><span>发布时间："+element.gtmcreate+"</span><span>作者："+element.author+"</span><span>[<a href='/main/findlifeDetails.do?cid="+element.cid+"'>"+element.typename+"</a>]</span></p>";
                resultHtml+="<figure><a title='"+element.title+"' href='/main/findlifeDetails.do?cid="+element.cid+"'><img src='"+element.titlepicture+"' alt='"+element.title+"'></a></figure>";
                resultHtml+="<ul class='nlist'><p>"+element.introduction+"</p><a href='/main/findlifeDetails.do?cid="+element.cid+"' title='"+element.title+"' class='readmore'>阅读全文&gt;&gt;</a>";
                resultHtml+="</ul><div class='line'></div>";
            })
            if(resultHtml!='' && data.pageCount!=1){
                resultHtml+="<div style='width: 600px;margin-right:50%;' id='footer'></div>";
            }
            $("#findLife").html(resultHtml);
            pageInfo(data.pageNo,data.pageCount);
        }
    })

}





/**-------------------------------------------动态创建 分页------------------------------------------------------------*/
function pageInfo(pageNo,pageCount){
    pageNo = parseInt(pageNo,"10");
    var result="";
    result+="<nav>";
    result+="<ul class='pagination'>";
    if(pageNo==1){
        result+="<li class='disabled'><a href='#'>&laquo;</a></li>";
    }else{
        result+="<li><a href='#' onclick=\"shangyiye("+(pageNo-1)+")\""+" >上一页</a></li>";
    }
    var begin=0;
    var end=0;
    if(pageCount<=5){
        begin=1;
        end=pageCount;
    }else{
        begin=pageNo-2;
        end=pageNo+3;
        if(begin<1){
            begin=1;
            end=5;
        }
        if(end>pageCount){
            begin=pageCount-4;
            end=pageCount;
        }
    }

    //<!--显示中间页数  -->
    for(var i=begin;i<=end;i++){
        if(i==pageNo){
            result+="<li class='active'><a href='#' onclick=\"zhongjianye("+i+")\""+" >"+i+"</a></li>";
        }else{
            result+="<li><a href='#' onclick=\"zhongjianye("+i+")\""+" >"+i+"</a></li>";
        }
    }

    if(end< pageCount){
        result+="<li><a href='#'>....</a></li>";
    }
    //<!--禁止  -->
    if(pageNo==pageCount){
        result+="<li class='disabled'><a href='#'>&raquo;</a></li>";
    }
    //<!--下一页  -->
    if(pageNo!=pageCount){
        result+="<li><a href='#' onclick=\"xiayiye("+(pageNo+1)+")\""+">下一页</a></li>";
    }

    result+="</ul></nav>";
    $("#footer").html(result);
}


//上一页
function shangyiye(pageNo){

    gongzong(pageNo);
}

//下一页
function xiayiye(pageNo){

    gongzong(pageNo);
}

//中间数字
function zhongjianye(pageNo){
    gongzong(pageNo);
}
/**------------------------------------------------------分页结束-------------------------------------------------------*/
/** 公共代码 */
function gongzong(pageNo){
    /**查询慢生活界面数据*/
    $.ajax({
        url:"/main/findLife.do" ,
        type:"post",
        data:{
            "pageNo":pageNo
        },
        datatype:"json",
        success:function(data){
            var resultHtml = "";
            if(data){
                update(resultHtml,data);
            }
        }
    });
}

/**获取慢生活界面json数据*/
function update(resultHtml,data){
    $.each(data.pageDate,function(index,element){
        resultHtml+="<h2><a title='"+element.title+"'href='#'>"+element.title+"</a></h2>";
        resultHtml+="<p class='dateview'><span>发布时间："+element.gtmcreate+"</span><span>作者："+element.author+"</span><span>[<a href='/main/findlifeDetails.do?cid="+element.cid+"'>"+element.typename+"</a>]</span></p>";
        resultHtml+="<figure><a title='"+element.title+"' href='/main/findlifeDetails.do?cid="+element.cid+"'><img src='"+element.titlepicture+"' alt='"+element.title+"'></a></figure>";
        resultHtml+="<ul class='nlist'><p>"+element.introduction+"</p><a href='/main/findlifeDetails.do?cid="+element.cid+"' title='"+element.title+"' class='readmore'>阅读全文&gt;&gt;</a>";
        resultHtml+="</ul><div class='line'></div>";
    });
    if(resultHtml!='' && data.pageCount!=1){
        resultHtml+="<div style='width: 600px;margin-right:50%;' id='footer'></div>";
    }
    $("#findLife").html(resultHtml);
    pageInfo(data.pageNo,data.pageCount);
}
