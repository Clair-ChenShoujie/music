<template>
  <div style="display: flex; width: 100%; height: 100vh;">
    <div style="width: 50%; height: 100%;">
      <div id='mapDom' style="width: 100%; height: 100%;"></div>
    </div>
    <div style="width: 50%; height: 100%;">
      <div id='radarChart' style="width: 100%; height: 50%;"></div>
      <div id='lineChart' style="width: 100%; height: 50%;"></div>
    </div>
  </div>
</template>

<script setup>
  import { ref,nextTick, onMounted } from 'vue'
  import china from '@/api/china.json'
  import * as echarts from 'echarts'
  let dataList = ref([])
  
  const mapEcharts = () =>{
    const mychart = document.querySelector('#mapDom');
    mychart.removeAttribute('_echarts_instance_');
    let initMap = echarts.init(mychart)
      echarts.registerMap('china', china);
      let options = {
          title: {
          text: '中国地图',
          sublink:
              'http://zh.wikipedia.org/wiki/%E9%A6%99%E6%B8%AF%E8%A1%8C%E6%94%BF%E5%8D%80%E5%8A%83#cite_note-12'
          },
          tooltip: {
          trigger: 'item',
          formatter: '{b}<br/>{c} (用户评分)'
          },
          toolbox: {
          show: true,
          orient: 'vertical',
          left: 'right',
          top: 'center',
          feature: {
              dataView: { readOnly: false },
              restore: {},
              saveAsImage: {}
          }
          },
          visualMap: {
          min: 0,
          max: 1000,
          text: ['High', 'Low'],
          realtime: false,
          calculable: true,
          inRange: {
              color: ['lightskyblue', 'yellow', 'orangered']
          }
          },
          series: [
          {
              name: '中国',
              type: 'map',
              map: 'china',
              label: {
              show: false
              },
              data: dataList.value,
          }
          ]
      }
      initMap.setOption(options)
  }

const radarChart = () => {
  const mychart = document.querySelector('#radarChart');
  mychart.removeAttribute('_echarts_instance_');
  let chart = echarts.init(mychart)

  let options = {
    title: {
      text: '北京-上海 数据雷达图',
      textStyle: {
        fontSize: 18,
        fontWeight: 'bold'
      }
    },
    legend: {
      data: ['北京市', '上海市'],
      bottom: 0
    },
    radar: {
      indicator: [
        { name: '人数', max: 1000 },
        { name: '活跃度', max: 1000 },
        { name: '消费', max: 1000 },
        { name: '评分', max: 1000 },
        { name: '听歌时长', max: 1000 }
      ],
      shape: 'circle',
      splitNumber: 5,
      axisName: {
        color: '#428BD4'
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(211, 253, 250, 0.8)'
        }
      },
      splitArea: {
        show: true,
        areaStyle: {
          color: ['rgba(255, 255, 255, 0.3)', 'rgba(200, 200, 200, 0.3)']
        }
      }
    },
    series: [{
      type: 'radar',
      data: [
        {
          value: [650, 500, 500, 600, 600],
          name: '北京市',
          lineStyle: {
            color: '#FF4500'
          },
          areaStyle: {
            color: 'rgba(255, 203, 150, 0.6)'
          }
        },
        {
          value: [900, 700, 800, 300, 500],
          name: '上海市',
          lineStyle: {
            color: '#4169E1'
          },
          areaStyle: {
            color: 'rgba(65, 105, 225, 0.4)'
          }
        }
      ]
    }]
  }
  chart.setOption(options)
}


const heatmapChart = () => {
  const myChart = document.querySelector('#lineChart');
  myChart.removeAttribute('_echarts_instance_');
  let chart = echarts.init(myChart);

  let options = {
    title: {
      text: '用户评分热力图'
    },
    tooltip: {
      position: 'top'
    },
    grid: {
      height: '50%',
      top: '10%'
    },
    xAxis: {
      type: 'category',
      data: dataList.value.slice(0, 10).map(item => item.name),
      splitArea: {
        show: true
      },
      axisLabel: {
        interval: 0, // 显示所有标签
        rotate: 0, // 标签旋转角度
        formatter: function(value) {
          // 标签换行显示
          return value.split("").join("\n");
        }
      }
    },
    yAxis: {
      type: 'category',
      data: ['消费指数'],
      splitArea: {
        show: true
      }
    },
    visualMap: {
      min: 0,
      max: Math.max(...dataList.value.map(item => item.value)),
      calculable: true,
      orient: 'horizontal',
      left: 'center',
      bottom: '15%'
    },
    series: [{
      name: '消费指数',
      type: 'heatmap',
      data: dataList.value.slice(0, 10).map((item, index) => [index, 0, item.value]),
      label: {
        show: true
      },
      itemStyle: {
        emphasis: {
          shadowBlur: 10,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      },
      
    }]
  };
  chart.setOption(options);
};



  onMounted(()=>{
      dataList.value = [
          {name:'北京市',value:290},
          {name:'天津市',value:320},
          {name:'河北省',value:240},
          {name:'山西省',value:520},
          {name:'内蒙古自治区',value:120},
          {name:'辽宁省',value:210},
          {name:'吉林省',value:620},
          {name:'黑龙江省',value:20},
          {name:'上海市',value:330},
          {name:'江苏省',value:820},
          {name:'浙江省',value:320},
          {name:'安徽省',value:520},
          {name:'福建省',value:120},
          {name:'江西省',value:620},
          {name:'山东省',value:920},
          {name:'河南省',value:220},
          {name:'湖北省',value:720},
          {name:'湖南省',value:210},
          {name:'广东省',value:20},
          {name:'广西壮族自治区',value:20},
          {name:'海南省',value:240},
          {name:'重庆市',value:20},
          {name:'四川省',value:240},
          {name:'贵州省',value:420},
          {name:'云南省',value:320},
          {name:'西藏自治区',value:20},
          {name:'陕西省',value:240},
          {name:'甘肃省',value:920},
          {name:'青海省',value:720},
          {name:'宁夏回族自治区',value:120},
          {name:'新疆维吾尔自治区',value:420},
          {name:'台湾省',value:230},
          {name:'香港特别行政区',value:420},
          {name:'澳门特别行政区',value:210},
      ]
      nextTick(()=>{
          mapEcharts()
          radarChart()
          heatmapChart()
      })
  })
</script>
<style>

</style>