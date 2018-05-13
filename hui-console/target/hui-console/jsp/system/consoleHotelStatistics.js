/**
 * Created by Tainy on 2017/11/2.
 */
function fun() {
    var json = {"reqId":0,"channel":10,"os":"","ver":"","appVer":"","model":"","lng":"","lat":"","signType":"","sign":"", "token":"6ef4948ccdd74100add3564c7d20931a",
        "param":{}};
    var postdata = JSON.stringify(json);//json对象转换json字符串
    $.ajax({
        type : 'POST',
        contentType : 'application/json;charset=UTF-8',
        url : '/hui-console/v1/consoleHotel/statistics',
        dataType : 'json',
        data : postdata,
        success : function(data) {
            if(data.code == 0) {
                statistics_area_data(data);
                statistics_score_data(data);
            }
        },
        error : function() {
            alert('error...');
        }
    });
}

function statistics_area_data(data){
    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('main1'));
    var names = new Array();
    var values = new Array();
    for(var i = 0; i < data.data.areaList.length; i++){
        names[i] = data.data.areaList[i].name;
        values[i] = data.data.areaList[i].value;
    }
    var yMax = 1200;
    var dataShadow = [];

    for (var i = 0; i < values.length; i++) {
        dataShadow.push(yMax);
    }

    var option1 = {
        title: {
            text: '区域统计数据',
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
    myChart1.setOption(option1);

    var zoomSize = 6;
    myChart1.on('click', function (params) {
        console.log(names[Math.max(params.dataIndex - zoomSize / 2, 0)]);
        myChart1.dispatchAction({
            type: 'dataZoom',
            startValue: names[Math.max(params.dataIndex - zoomSize / 2, 0)],
            endValue: names[Math.min(params.dataIndex + zoomSize / 2, values.length - 1)]
        });
    });
}

/** ===========================================南丁格尔玫瑰图=========================================== **/
function statistics_score_data(data){
    var myChart2 = echarts.init(document.getElementById('main2'));

    var names = new Array();
    for(var i = 0; i < data.data.scoreList.length; i++){
        names[i] = data.data.scoreList[i].name;
    }
    var option2 = {
        title : {
            text: '评分统计数据',
            subtext: 'Scoring statistics',
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
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'半径模式',
                type:'pie',
                radius : [60, 220],
                center : ['25%', '50%'],
                roseType : 'radius',
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
                data:data.data.scoreList
            },
            {
                name:'面积模式',
                type:'pie',
                radius : [60, 220],
                center : ['75%', '50%'],
                roseType : 'area',
                data:data.data.scoreList
            }
        ]
    };

    myChart2.setOption(option2);
}