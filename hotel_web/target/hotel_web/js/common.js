var channelComboboxList = new Array();
var ChannelComboboxData = function() {
    this.channelNumProduct = "";
    this.channelName = "全部";
};
function channelComboboxBind() {
    $.get("channelList.htm", function(data){
        var channelComboboxData = new ChannelComboboxData();
        channelComboboxList.push(channelComboboxData);
        for (var i = 0; i < data.length; i++) {
            channelComboboxData = new ChannelComboboxData();
            channelComboboxData.channelNumProduct = data[i].channelNum + "," + data[i].channelProduct;
            channelComboboxData.channelName = data[i].channelName;
            channelComboboxList.push(channelComboboxData);
        }
        $("#channelNumProduct").combobox("loadData", JSON.parse(JSON.stringify(channelComboboxList)));
    }, "json");
}

function timeStamp2DateTime(timeStamp) {
    timeStamp = parseInt(timeStamp);
    if (timeStamp > 0) {
        var datetime = new Date(timeStamp);
        return dateTimeFormat(datetime);
    } else {
        return "";
    }
}

function timeStamp2Date(timeStamp) {
    if (timeStamp > 0) {
        var dateTime = timeStamp2DateTime(timeStamp);
        return dateTime.substring(0, 10);
    } else {
        return "";
    }
}

function dateTimeFormat(date) {
    var month = date.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    var day = date.getDate();
    if (day < 10) {
        day = "0" + day;
    }
    var hour = date.getHours();
    if (hour < 10) {
        hour = "0" + hour;
    }
    var minutes = date.getMinutes();
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    var second = date.getSeconds();
    if (second < 10) {
        second = "0" + second;
    }
    var datetime = date.getFullYear() + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + second;
    return datetime;
}

function dateFormat(date) {
    return dateTimeFormat(date).substring(0, 10);
}

function currFullTime() {
    var now = new Date();
    return dateTimeFormat(now);
}

function currDate() {
    var now = new Date();
    var currTime = dateTimeFormat(now);
    return currTime.substring(0, 10);
}

function decimalFormat(number) {
    if (number == null) {
        return 0.00;
    } else {
        var num = new Number(number);
        return num.toFixed(2);
    }
}

function noRepeatArray(array){
    var n = {}, r = [], val, type;
    for (var i = 0; i < array.length; i++) {
        val = array[i];
        type = typeof val;
        if (!n[val]) {
            n[val] = [type];
            r.push(val);
        } else if (n[val].indexOf(type) < 0) {
            n[val].push(type);
            r.push(val);
        }
    }
    return r;
}