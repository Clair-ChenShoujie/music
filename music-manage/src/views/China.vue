<template>
    <div style="width: 80%;height: 100vh;margin: 0 auto;">
        <div id='mapDom' style="width: 100%;height: 100%;"></div>
    </div>
  </template>
  <script setup>
    import { ref,nextTick, onMounted } from 'vue'
    import china from '@/api/china.json'
    import * as echarts from 'echarts'
    let dataList = ref([])
    const mapEcharts = () =>{
        let initMap = echarts.init(document.querySelector('#mapDom'))
        echarts.registerMap('china', china);
        let options = {
            title: {
            text: '中国地图',
            sublink:
                'http://zh.wikipedia.org/wiki/%E9%A6%99%E6%B8%AF%E8%A1%8C%E6%94%BF%E5%8D%80%E5%8A%83#cite_note-12'
            },
            tooltip: {
            trigger: 'item',
            formatter: '{b}<br/>{c} (销量)'
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
            {name:'上海市',value:220},
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
        })
    })
  </script>
  <style>
  
  </style>