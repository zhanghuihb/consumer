/**
 * Created by Tainy on 2017/11/2.
 */
function statistics_data(category) {
    var json = "";
    if(category == 5){
        json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
            "param":{category: category,queryTimeStart:$(':input[name=queryTimeStart]').val(),queryTimeEnd:$(':input[name=queryTimeEnd]').val()}};
    }else if(category == 6){
        json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
            "param":{category: category,queryTimeStart:$(':input[name=queryTimeStart-six]').val()}};
    }else{
        json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
            "param":{category: category}};
    }
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type : 'POST',
        contentType : 'application/json;charset=UTF-8',
        url : '/hui-console/v1/consoleConsumerInfo/statisticsConsumerData',
        dataType : 'json',
        data : postdata,
        success : function(data) {
            if(data.code == 0) {
                if(data.data.category == 1){
                    console.info($('#collapseOne').attributes);
                    statistics_category_data(data);
                    createStatisticsDataTable(data);
                }else if(data.data.category == 2){
                    statistics_city_data(data);
                    createStatisticsDataTable(data);
                }else if(data.data.category == 3){
                    statistics_inout_data(data);
                    createStatisticsDataTable(data);
                }else if(data.data.category == 4){
                    statistics_month_data(data);
                    createStatisticsDataTable(data);
                }else if(data.data.category == 5){
                    statistics_day_data(data);
                    createStatisticsDataTable(data);
                }else if(data.data.category == 6){
                    statistics_person_data(data);
                    createStatisticsDataTable(data);
                }
            }else{
                alert(data.msg);
            }
        },
        error : function() {
            alert('error...');
        }
    });
}
/** ===========================================按消费分类统计========================================== **/
function statistics_category_data(data){
    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('main1'));
    var names = new Array();
    var values = new Array();
    var valuesDis = new Array();
    names[0] = "总费用";
    values[0] = data.data.totalConsumer;
    valuesDis[0] = 0;

    // [0, 1700, 1400, 1200, 300, 0]
    // [2900, 1200, 300, 200, 900, 300]
    for(var i = 1; i <= data.data.accountDataVOList.length; i++){
        names[i] = data.data.accountDataVOList[i - 1].name;
        values[i] = data.data.accountDataVOList[i - 1].value;
        if(i == 1){
            valuesDis[i] = values[i - 1] - values[i];
        }else{
            valuesDis[i] = valuesDis[i - 1] -values[i];
        }
    }

    var option1 = {
        title: {
            text: '按消费分类统计（单位:元）',
            subtext: 'From ExcelHome',
            sublink: 'http://e.weibo.com/1341556070/AjQH99che'
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },
            formatter: function (params) {
                var tar = params[1];
                return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type : 'category',
            splitLine: {show:false},
            data : names
        },
        yAxis: {
            type : 'value'
        },
        series: [
            {
                name: '辅助',
                type: 'bar',
                stack:  '总量',
                itemStyle: {
                    normal: {
                        barBorderColor: 'rgba(0,0,0,0)',
                        color: 'rgba(0,0,0,0)'
                    },
                    emphasis: {
                        barBorderColor: 'rgba(0,0,0,0)',
                        color: 'rgba(0,0,0,0)'
                    }
                },
                data: valuesDis
            },
            {
                name: '消费金额',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                data:values
            }
        ]
    };

    myChart1.setOption(option1);
}

/** ===========================================南丁格尔玫瑰图=========================================== **/
function statistics_city_data(data) {
    var myChart2 = echarts.init(document.getElementById('main2'));

    var names = new Array();
    for (var i = 0; i < data.data.accountDataVOList.length; i++) {
        names[i] = data.data.accountDataVOList[i].name;
    }
    var option2 = {
        title: {
            text: '按消费城市统计',
            subtext: 'Scoring statistics',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x: 'center',
            y: '50',
            data: names
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        series: [
            {
                name: '半径模式',
                type: 'pie',
                radius: [60, 220],
                center: ['25%', '50%'],
                roseType: 'radius',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                lableLine: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: data.data.accountDataVOList
            },
            {
                name: '面积模式',
                type: 'pie',
                radius: [60, 220],
                center: ['75%', '50%'],
                roseType: 'area',
                data: data.data.accountDataVOList
            }
        ]
    };

    myChart2.setOption(option2);
}

/** ===========================================按收入支出统计=========================================== **/
function statistics_inout_data(data){
    var myChart3 = echarts.init(document.getElementById('main3'));

    var names = new Array();
    for(var i = 0; i < data.data.accountDataVOList.length; i++){
        names[i] = data.data.accountDataVOList[i].name;
    }
    var option3 = {
        title : {
            text: '按收入支出统计',
            subtext: 'Consumering statistics',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x : 'center',
            y : '50',
            data:names
        },
        series : [
            {
                name:'消费数据',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                lableLine: {
                    normal: {
                        show: false
                    }
                },
                data:data.data.accountDataVOList
            }
        ]
    };

    myChart3.setOption(option3);
}

//动态的创建一个table
function createStatisticsDataTable(data) {
    var tableStr = "<table id='consumerInfoTable' class=\"table  table-bordered\"><thead>";
    tableStr = tableStr
        + "<tr>"
        + "<th class='td-text' style='text-align: center;'>序号</th>"
        + "<th class='td-text' style='text-align: center;'>类别</th>"
        + "<th class='td-text' style='text-align: center;'>金额</th>"
        + "<th class='td-text' style='text-align: center;'>数量</th>"
        + "</tr></thead><tbody>";
    var len = data.data.accountDataVOList.length;
    for (var i = 0; i < len; i++) {
        tableStr = tableStr + "<tr>"
            + "<td class='td-text'><div>" + (i + 1) + "</div></td>"
            + "<td class='td-text'><div>" + data.data.accountDataVOList[i].name+ "</div></td>"
            + "<td class='td-text'><div>" + data.data.accountDataVOList[i].value + "</div></td>"
            + "<td class='td-text'><div>" + data.data.accountDataVOList[i].sheets + "</div></td>"
            + "</tr>";
    }
    if (len == 0) {
        tableStr = tableStr + "<tr style='text-align: center'>"
            + "<td colspan='6'><font color='red'>" + "暂无记录" + "</font></td>"
            + "</tr>";
    }
    tableStr = tableStr + "</tbody></table>";
    //添加到div中
    $("#table" + data.data.category).html(tableStr);
}

/** ===========================================按每月消费统计=========================================== **/
function statistics_month_data(data){
    // 基于准备好的dom，初始化echarts实例
    var myChart4 = echarts.init(document.getElementById('main4'));
    var names = new Array();
    var values = new Array();
    for(var i = 0; i < data.data.accountDataVOList.length; i++){
        names[i] = data.data.accountDataVOList[i].name;
        values[i] = data.data.accountDataVOList[i].value;
    }
    var yMax = 1200;
    var dataShadow = [];

    for (var i = 0; i < values.length; i++) {
        dataShadow.push(yMax);
    }

    var option4 = {
        title: {
            text: '按每月消费统计',
            subtext: 'Area statistics: Gradient Color, Shadow, Click Zoom',
            x:'center'
        },
        xAxis: {
            data: names,
            axisLabel: {
                inside: true,
                textStyle: {
                    color: '#fff'
                }
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
            z: 10
        },
        yAxis: {
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                textStyle: {
                    color: '#999'
                }
            }
        },
        dataZoom: [
            {
                type: 'inside'
            }
        ],
        series: [
            { // For shadow
                type: 'bar',
                itemStyle: {
                    normal: {color: 'rgba(0,0,0,0.05)'}
                },
                barGap:'-100%',
                barCategoryGap:'40%',
                data: dataShadow,
                animation: false
            },
            {
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#83bff6'},
                                {offset: 0.5, color: '#188df0'},
                                {offset: 1, color: '#188df0'}
                            ]
                        )
                    },
                    emphasis: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#2378f7'},
                                {offset: 0.7, color: '#2378f7'},
                                {offset: 1, color: '#83bff6'}
                            ]
                        )
                    }
                },
                data: values
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart4.setOption(option4);

    var zoomSize = 6;
    myChart4.on('click', function (params) {
        console.log(names[Math.max(params.dataIndex - zoomSize / 2, 0)]);
        myChart4.dispatchAction({
            type: 'dataZoom',
            startValue: names[Math.max(params.dataIndex - zoomSize / 2, 0)],
            endValue: names[Math.min(params.dataIndex + zoomSize / 2, values.length - 1)]
        });
    });
}

/** ===========================================按每天消费统计=========================================== **/
function statistics_day_data(data){
    // 基于准备好的dom，初始化echarts实例
    var myChart5 = echarts.init(document.getElementById('main5'));
    var names = new Array();
    var values = new Array();
    for(var i = 0; i < data.data.accountDataVOList.length; i++){
        names[i] = data.data.accountDataVOList[i].name;
        values[i] = data.data.accountDataVOList[i].value;
    }

    var option5 = {
        title: {
            text: '按每天消费统计',
            subtext: '家庭消费',
            x:'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        toolbox: {
            show: true,
            feature: {
                saveAsImage: {}
            }
        },
        xAxis:  {
            type: 'category',
            boundaryGap: false,
            data: names
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} 元'
            },
            axisPointer: {
                snap: true
            }
        },
        visualMap: {
            show: false,
            dimension: 0,
            pieces: [{
                lte: 6,
                color: 'green'
            }, {
                gt: 6,
                lte: 8,
                color: 'green'
            }, {
                gt: 8,
                lte: 14,
                color: 'green'
            }, {
                gt: 14,
                lte: 17,
                color: 'green'
            }, {
                gt: 17,
                color: 'green'
            }]
        },
        series: [
            {
                name:'当天消费总金额',
                type:'line',
                smooth: true,
                data: values
            }
        ]
    };

    myChart5.setOption(option5);
}

/** ===========================================按个人消费统计=========================================== **/
function statistics_person_data(data){
    // 基于准备好的dom，初始化echarts实例
    var myChart6 = echarts.init(document.getElementById('main6'));
    var names = new Array();
    var values = new Array();
    for(var i = 0; i < data.data.accountDataVOList.length; i++){
        names[i] = data.data.accountDataVOList[i].name;
        values[i] = data.data.accountDataVOList[i].value;
    }
    var yMax = 1200;
    var dataShadow = [];

    for (var i = 0; i < values.length; i++) {
        dataShadow.push(yMax);
    }

    var option6 = {
        title: {
            text: '按个人消费统计',
            subtext: 'Area statistics: Gradient Color, Shadow, Click Zoom',
            x:'center'
        },
        xAxis: {
            data: names,
            axisLabel: {
                inside: true,
                textStyle: {
                    color: '#fff'
                }
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
            z: 10
        },
        yAxis: {
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                textStyle: {
                    color: '#999'
                }
            }
        },
        dataZoom: [
            {
                type: 'inside'
            }
        ],
        series: [
            { // For shadow
                type: 'bar',
                itemStyle: {
                    normal: {color: 'rgba(0,0,0,0.05)'}
                },
                barGap:'-100%',
                barCategoryGap:'40%',
                data: dataShadow,
                animation: false
            },
            {
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#83bff6'},
                                {offset: 0.5, color: '#188df0'},
                                {offset: 1, color: '#188df0'}
                            ]
                        )
                    },
                    emphasis: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#2378f7'},
                                {offset: 0.7, color: '#2378f7'},
                                {offset: 1, color: '#83bff6'}
                            ]
                        )
                    }
                },
                data: values
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart6.setOption(option6);

    var zoomSize = 6;
    myChart6.on('click', function (params) {
        console.log(names[Math.max(params.dataIndex - zoomSize / 2, 0)]);
        myChart6.dispatchAction({
            type: 'dataZoom',
            startValue: names[Math.max(params.dataIndex - zoomSize / 2, 0)],
            endValue: names[Math.min(params.dataIndex + zoomSize / 2, values.length - 1)]
        });
    });
}

/** ===========================================点击查询按钮触发按每天消费统计=========================================== **/
$(function(){
    $("#query-submit").click(function(){
        statistics_data(5);
    })
})

/** ===========================================点击查询按钮触发按个人消费统计=========================================== **/
$(function(){
    $("#query-submit-six").click(function(){
        statistics_data(6);
    })
})