/**
 * 通用js方法封装处理
 * Copyright (c) 2019 ruoyi
 */
import store from "@/store"

const baseURL = process.env.VUE_APP_BASE_API

export function friendlyDate(timestamp) {
    var formats = {
        'day': '%n% 天前',
        'hour': '%n% 小时前',
        'minute': '%n% 分钟前',
        'second': '%n% 秒前',
        'days': '%n% 天后',
        'hours': '%n% 小时后',
        'minutes': '%n% 分钟后',
        'seconds': '%n% 秒后',
    };
    var timestamp = new Date(timestamp)
    var now = Date.now();
    var seconds = Math.floor((now - timestamp) / 1000);
    var minutes = Math.floor(seconds / 60);
    var hours = Math.floor(minutes / 60);
    var days = Math.floor(hours / 24);
    var diffType = '';
    var diffValue = 0;
      if (days > 0) {
          diffType = 'day';
          diffValue = days;
      } else {
          if (hours > 0) {
              diffType = 'hour';
              diffValue = hours;
          } else {
              if (minutes > 0) {
                  diffType = 'minute';
                  diffValue = minutes;
              } else {
                if(seconds > 0){
                  diffType = 'second';
                  diffValue = seconds === 0 ? (seconds = 1) : seconds;
                }else{
                  days = -days
                  hours = -hours
                  minutes = -minutes
                  seconds = -seconds
                  if (days > 0) {
                      diffType = 'days';
                      diffValue = days;
                  }else{
                    if (hours > 0) {
                        diffType = 'hours';
                        diffValue = hours;
                    }else{
                      if (minutes > 0) {
                          diffType = 'minutes';
                          diffValue = minutes;
                      }else{
                        if(seconds > 0){
                          diffType = 'seconds';
                          diffValue = seconds === 0 ? (seconds = 1) : seconds;
                        }
                      }
                    }
                  }
                }

              }
          }
      }
    return formats[diffType].replace('%n%', diffValue);
}


export function checkImg(file){
        let img_id = file
        var index= img_id.indexOf("."); //（考虑严谨用lastIndexOf(".")得到）得到"."在第几位
         img_id=img_id.substring(index); //截断"."之前的，得到后缀
          if(img_id!=".bmp"&&img_id!=".png"&&img_id!=".gif"&&img_id!=".jpg"&&img_id!=".jpeg"){  //根据后缀，判断是否符合图片格式
              return false
            }else{
              return true
          }
       }
export function permission(file){
      let permissionFlag = "statistc:homepage:businessData"
      let permissionFlag1 = "statistc:homepage:datadisplay"
      let permissionFlag2 = "statistc:homepage:partjob"
      let permissionFlag3 = "statistc:homepage:boss"
      let hasPermissions  = null
      store.getters.permissions.forEach(permission => {
        if(permission == permissionFlag){
          hasPermissions = 1
        }else if(permission==permissionFlag1){
          hasPermissions = 2
        }else if(permission==permissionFlag2){
          hasPermissions = 3
        }else if(permission==permissionFlag3){
          hasPermissions = 4
        }
      })
      return hasPermissions
  }
// 日期格式化
export function parseTime(time, pattern) {
	if (arguments.length === 0 || !time) {
		return null
	}
	const format = pattern || '{y}-{m}-{d} {h}:{i}:{s}'
	let date
	if (typeof time === 'object') {
		date = time
	} else {
		if ((typeof time === 'string') && (/^[0-9]+$/.test(time))) {
			time = parseInt(time)
		} else if (typeof time === 'string') {
			time = time.replace(new RegExp(/-/gm), '/');
		}
		if ((typeof time === 'number') && (time.toString().length === 10)) {
			time = time * 1000
		}
		date = new Date(time)
	}
	const formatObj = {
		y: date.getFullYear(),
		m: date.getMonth() + 1,
		d: date.getDate(),
		h: date.getHours(),
		i: date.getMinutes(),
		s: date.getSeconds(),
		a: date.getDay()
	}
	const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
		let value = formatObj[key]
		// Note: getDay() returns 0 on Sunday
		if (key === 'a') { return ['日', '一', '二', '三', '四', '五', '六'][value] }
		if (result.length > 0 && value < 10) {
			value = '0' + value
		}
		return value || 0
	})
	return time_str
}

// 表单重置
export function resetForm(refName) {
	if (this.$refs[refName]) {
		this.$refs[refName].resetFields();
	}
}

export function worktime(startyear,startmonth,endyear,endmonth){
        return `${startyear}.${startmonth} — ${endyear}.${endmonth}`
}

export function debounce(fn, delay) {
  // 记录上一次的延时器
  var timer = null;
  var delay = delay || 1000;
  return function() {
    var args = arguments;
    var that = this;
    // 清除上一次延时器
    clearTimeout(timer)
    timer = setTimeout(function() {
        fn.apply(that,args)
    }, delay);
  }
}

// 添加日期范围
export function addDateRange(params, dateRange) {
	var search = params;
	search.beginTime = "";
	search.endTime = "";
	if (null != dateRange && '' != dateRange) {
		search.beginTime = dateRange[0];
		search.endTime = dateRange[1];
	}
	return search;
}

// 回显数据字典
export function selectDictLabel(datas, value) {
	var actions = [];
	Object.keys(datas).some((key) => {
		if (datas[key].dictValue == ('' + value)) {
			actions.push(datas[key].dictLabel);
			return true;
		}
	})
	return actions.join('');
}

// 回显数据字典（字符串数组）
export function selectDictLabels(datas, value, separator) {
	var actions = [];
	var currentSeparator = undefined === separator ? "," : separator;
	var temp = value.split(currentSeparator);
	Object.keys(value.split(currentSeparator)).some((val) => {
		Object.keys(datas).some((key) => {
			if (datas[key].dictValue == ('' + temp[val])) {
				actions.push(datas[key].dictLabel + currentSeparator);
			}
		})
	})
	return actions.join('').substring(0, actions.join('').length - 1);
}

// 通用下载方法
export function download(fileName) {
	window.location.href = baseURL + "/common/download?fileName=" + encodeURI(fileName) + "&delete=" + true;
}

// 字符串格式化(%s )
export function sprintf(str) {
	var args = arguments, flag = true, i = 1;
	str = str.replace(/%s/g, function () {
		var arg = args[i++];
		if (typeof arg === 'undefined') {
			flag = false;
			return '';
		}
		return arg;
	});
	return flag ? str : '';
}

// 转换字符串，undefined,null等转化为""
export function praseStrEmpty(str) {
	if (!str || str == "undefined" || str == "null") {
		return "";
	}
	return str;
}

/**
 * 构造树型结构数据
 * @param {*} data 数据源
 * @param {*} id id字段 默认 'id'
 * @param {*} parentId 父节点字段 默认 'parentId'
 * @param {*} children 孩子节点字段 默认 'children'
 * @param {*} rootId 根Id 默认 0
 */
export function handleTree(data, id, parentId, children, rootId) {
	id = id || 'id'
	parentId = parentId || 'parentId'
	children = children || 'children'
	rootId = rootId || Math.min.apply(Math, data.map(item => { return item[parentId] })) || 0
	//对源数据深度克隆
	const cloneData = JSON.parse(JSON.stringify(data))
	//循环所有项
	const treeData = cloneData.filter(father => {
		let branchArr = cloneData.filter(child => {
			//返回每一项的子级数组
			return father[id] === child[parentId]
		});
		branchArr.length > 0 ? father.children = branchArr : '';
		//返回第一层
		return father[parentId] === rootId;
	});
	return treeData != '' ? treeData : data;
}
