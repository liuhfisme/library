/**
 *  消息通知
 */
;
(function ($, window, document, undefined) {
    var stompClient = null;
    var headers = {
        login: 'admin',
        passcode: 'admin'
    };
    var on_connect = function(x) {
        
        // stompClient.connect(headers, function(frame) {
            // console.log("Connected: "+frame);
            stompClient.subscribe("/queue/default", function (response) {
                alert(JSON.parse(response.body).responseMessage);
            })
        // });

    };
    var on_error = function() {
        console.log("error");
    };
    var connect = function(url) {
        var ws = new SockJS(url);
        stompClient = Stomp.over(ws);
        // SockJS does not support heart-beat: disable heart-beats
        stompClient.heartbeat.outgoing = 0;
        stompClient.heartbeat.incoming = 0;
        stompClient.connect('admin','admin', on_connect, on_error, '/');
    };
    
    $(document).ready(function () {
        connect("ws://192.168.20.201:15674/ws");

    });
})(jQuery, window, document)