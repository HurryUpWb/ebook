<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<title>滚屏实验</title>
<style type="text/css">
#scrollDiv{width:300px;height:100px;min-height:25px;line-height:25px;border:#ccc 1px solid;overflow:hidden}
#scrollDiv li{height:25px;padding-left:10px;}
</style>
<script type="text/javascript" src="/ebook/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    (function ($) {
        $.fn.extend({
            Scroll: function (opt, callback) {
                //参数初始化
                if (!opt) var opt = {};
                var _this = this.eq(0).find("ul:first");
                var lineH = _this.find("li:first").height(), //获取行高
                        line = opt.line ? parseInt(opt.line, 10) : parseInt(this.height() / lineH, 10), //每次滚动的行数，默认为一屏，即父容器高度
                        speed = opt.speed ? parseInt(opt.speed, 10) : 500, //卷动速度，数值越大，速度越慢（毫秒）
                        timer = opt.timer ? parseInt(opt.timer, 10) : 2000; //滚动的时间间隔（毫秒）
                if (line == 0) line = 1;
                var upHeight = 0 - line * lineH;
                var downHeight=line * lineH - 0;
                //滚动函数
                scrollUp = function () {
                    _this.animate(
                        { marginTop: upHeight },
                        speed,
                        function () {
                            for (i = 1; i <= line; i++) {
                                _this.find("li:first").appendTo(_this);
                            }
                            _this.css({ marginTop: 0 });
                        }
                    );
                },
                //向下滚动函数
                scrollDown = function () {
                    _this.animate(
                        { marginTop: downHeight },//动画展示css样式
                        speed,
                        function () {
                            _this.find("li:last").prependTo(_this);
                            _this.css({ marginTop: 0 });
                        }
                        )
                }
                var timerID
                //鼠标事件绑定
                _this.hover(function () {
                    clearInterval(timerID);
                }, function () {
                    timerID = setInterval("scrollUp()", timer);//这里调用向下或者向上滚动函数
                }).mouseout();
            }
        })
    })(jQuery);

    $(document).ready(function () {
        $("#scrollDiv").Scroll({ line: 1, speed: 500, timer: 2000 });
    });
</script>
</head>

<body>
<p>多行滚动演示：</p>
<div id="scrollDiv">
  <ul>
    <li>这是公告标题的第1行</li>
    <li>这是公告标题的第2行</li>
    <li>这是公告标题的第3行</li>
    <li>这是公告标题的第4行</li>
    <li>这是公告标题的第5行</li>
    <li>这是公告标题的第6行</li>
    <li>这是公告标题的第7行</li>
    <li>这是公告标题的第8行</li>
  </ul>
</div>
</body>
</html>