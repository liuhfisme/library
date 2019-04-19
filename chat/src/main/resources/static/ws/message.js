;
/*消息通知*/
(function ($, window, document, undefined) {
    var QCIM = {
        host: 'http://127.0.0.1:8888',
        frameUrl: '/pub/message',
        client: null,
        init: function () {
            //创建一个iframe设置为隐藏
            var frame = document.createElement("iframe");
            frame.url = this.host+this.client;
        },
        on_receive: function (message) {
            alert(message.body);
        }
    }
})(jQuery, window, document)