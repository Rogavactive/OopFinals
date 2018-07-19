function clickTicket(seatID) {
    // $('#')
    // if()
}

window.onload = function (ev) {
    setInterval(askForState,300);
};


function askForState(){
    $.ajax({
        url:'TicketServlet',
        type:'POST',
        data:{
            type:"getState"
        },
        success: function (data) {
            callback(data);
        }
    });
}


function callback(data){
    var colors = data.split('\n');
    // colors = colors.filter(function(elem){elem!=""});
    for(var i=0;i<colors.lenth;i++){
        $('#'+i).css({"background-color":colors[i]});
    }
}