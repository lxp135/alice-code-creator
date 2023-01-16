/**
 * AliceJ快速开发框架通用js方法
 */
(function($){

	/**
	 * AliceJ全局对象
     */
    var AliceJ = {

		constant:{
			jdbc:{
				'MySQL':'com.mysql.cj.jdbc.Driver',
				'Oracle':'oracle.jdbc.driver.OracleDriver',
				'SQLServer':'com.microsoft.sqlserver.jdbc.SQLServerDriver',
				'ClickHouse' : 'com.clickhouse.jdbc.ClickHouseDriver'
			}
		},

		param : {

		},

		/**
		 * json处理方法集合
		 */
		json : {

			useHasOwn : !!{}.hasOwnProperty,

			// crashes Safari in some instances
			//var validRE = /^("(\\.|[^"\\\n\r])*?"|[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t])+?$/;

			pad : function(n){
				return n < 10 ? "0" + n : n;
			},

			/**
			 * 这个方法是jquery的扩展
			 * @param {Object} v
			 */
			isDate : function(v){
				return v && typeof v.getFullYear == 'function';
			},

			m : {
				"\b": '\\b',
				"\t": '\\t',
				"\n": '\\n',
				"\f": '\\f',
				"\r": '\\r',
				'"': '\\"',
				"\\": '\\\\'
			},

			encodeString : function(s){
				if (/["\\\x00-\x1f]/.test(s)) {
					return '"' +
						s.replace(/([\x00-\x1f\\"])/g, function(a, b){
							var c = m[b];
							if (c) {
								return c;
							}
							c = b.charCodeAt();
							return "\\u00" +
								Math.floor(c / 16).toString(16) +
								(c % 16).toString(16);
						}) +
						'"';
				}
				return '"' + s + '"';
			},

			encodeArray : function(o){
				var a = ["["], b, i, l = o.length, v;
				for (i = 0; i < l; i += 1) {
					v = o[i];
					switch (typeof v) {
						case "undefined":
						case "function":
						case "unknown":
							break;
						default:
							if (b) {
								a.push(',');
							}
							a.push(v === null ? "null" : AliceJ.json.encode(v));
							b = true;
					}
				}
				a.push("]");
				return a.join("");
			},

			encodeDate : function(o){
				return '"' + o.getFullYear() + "-" +
					AliceJ.json.pad(o.getMonth() + 1) +
					"-" +
					AliceJ.json.pad(o.getDate()) +
					"T" +
					AliceJ.json.pad(o.getHours()) +
					":" +
					AliceJ.json.pad(o.getMinutes()) +
					":" +
					AliceJ.json.pad(o.getSeconds()) +
					'"';
			},

			encode : function (o) {
				if (typeof o == "undefined" || o === null) {
					return "null";
				}
				else if ($.isArray(o)) {
					return AliceJ.json.encodeArray(o);
				}
				else if (AliceJ.json.isDate(o)) {
					return $.alicej.json.encodeDate(o);
				}
				else if (typeof o == "string") {
					return AliceJ.json.encodeString(o);
				}
				else if (typeof o == "number") {
					return isFinite(o) ? String(o) : "null";
				}
				else if (typeof o == "boolean") {
					return String(o);
				}
				else {
					var a = ["{"], b, i, v;
					for (i in o) {
						if (!AliceJ.json.useHasOwn || o.hasOwnProperty(i)) {
							v = o[i];
							switch (typeof v) {
								case "undefined":
								case "function":
								case "unknown":
									break;
								default:
									if (b) {
										a.push(',');
									}
									a.push(this.encode(i), ":", v === null ? "null" : this.encode(v));
									b = true;
							}
						}
					}
					a.push("}");
					return a.join("");
				}
			},

			decode : function(json){
				return eval("(" + json + ')');
			}
		},

		/**
		 * 通用工具方法集合
         */
    	util : {

			/**
			 * 初始化三级地区联动下拉框
			 * @param level1Area 一级地区id
			 * @param level2Area 二级地区id
			 * @param level3Area 三级地区id
			 */
    		initAreaSelect:function(level1Area,level2Area,level3Area){

			},

            /**
			 * 设置定时任务
             * @param fun
             * @param timeout
             */
            setInterval : function(fun,timeout){

                $.alicej.cache.timer.push(setInterval(fun,timeout));
			},

            /**
			 * 清除定时任务
             */
			clearInterval : function(){
            	for(var key in $.alicej.cache.timer){
                    clearInterval($.alicej.cache.timer[key]);
				}
                $.alicej.cache.timer = new Array();
			},

            /**
			 * 根据出生日期计算年龄
             * @param date 出生日期
             * @returns {*} 当前年龄
             */
    		getAge : function (date) {
				if(null==date||""==date){
					return "";
				}else{
					if($.alicej.json.isDate(date)){
                        return new Date().getFullYear() - date.year;
					}else{
                        return new Date().getFullYear() - date.substring(0, 4);
					}
				}
            },

			/**
			 * 获得后台查询需要的map
			 * obj可选参数，会赋值给map.map
			 * @param obj
             * @returns {{}}
             */
			getMap : function(obj){
				var map = {};
				if (!$.isPlainObject(obj)) {
					obj = {};
				}
				map.map = obj;
				map.javaClass = "java.util.HashMap";
				return map;
			},

			/**
			 * 获得后台查询需要的list
			 * map可选参数，会添加到list数组中
			 * @param map
			 * @returns {{javaClass, list}}
             */
			getList : function(map) {
				var l = AliceJ.util.getListNull();
				if (!$.isPlainObject(map)) {
					l.list.push(AliceJ.util.getMap());
				} else {
					l.list.push(map);
				}
				return l;
			},

			/**
			 * 获得后台查询需要的空list
			 * @returns {{javaClass: string, list: Array}}
             */
			getListNull : function() {
				return {
					javaClass: "java.util.List",
					list: []
				};
			},

			/**
			 * 返回表单数据实体为json格式数据
			 * @param element
			 * @returns {{表单数据实体}}
			 */
			serialize : function (element) {
				var data = {};
				$(element).serializeArray().map(function(x){
					data[x.name] = x.value;
				});
				return data;
			},

			/**
			 * 将long型日期转换为正常日期
			 * @param longTypeDate
			 * @returns {*}
			 */
			getSmpFormatDateTime : function (longTypeDate) {
				return longTypeDate == null
					? ""
					: AliceJ.util.dateFormat("yyyy-MM-dd hh:mm:ss",new Date(longTypeDate));
			},

			/**
			 * 将long型日期转换为正常日期
			 * @param longTypeDate
			 * @returns {*}
			 */
			getSmpFormatDate : function (longTypeDate) {
				return longTypeDate == null
					? ""
					: AliceJ.util.dateFormat("yyyy-MM-dd",new Date(longTypeDate));
			},

			/**
			 * 格式化日期
			 * @param format 格式
			 * @param date 日期
             * @returns {*}
             */
			dateFormat : function (format,date) {
				var o = {
					"M+" : date.getMonth() + 1, // month
					"d+" : date.getDate(), // day
					"h+" : date.getHours(), // hour
					"m+" : date.getMinutes(), // minute
					"s+" : date.getSeconds(), // second
					"q+" : Math.floor((date.getMonth() + 3) / 3), // quarter
					"S" : date.getMilliseconds()
					// millisecond
				};

				if (/(y+)/.test(format)) {
					format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4
						- RegExp.$1.length));
				}

				for (var k in o) {
					if (new RegExp("(" + k + ")").test(format)) {
						format = format.replace(RegExp.$1, RegExp.$1.length == 1
							? o[k]
							: ("00" + o[k]).substr(("" + o[k]).length));
					}
				}
				return format;
			},

			/**
			 * 截取字符串
			 * @param str 待截取的字符串
			 * @param len 截取的长度
             * @returns {*}
             */
			substr : function (str,len) {
				if(null==str){
					return "";
				}else if(len>=str.length){
					return str;
				}else{
					return str.substring(0,len) + "...";
				}
			},

			/**
			 * get the parameter of url by name
			 * @param {Object} name  the parameter's name
			 * @param {Object} win   the target object
			 */
			getUrlParam :function (name, win) {
				var w = window;
				if (win != null) {
					w = win;
				}
				// ?在#之后的参数取得方法
				var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
				var r = w.location.hash.substr(parseInt(w.location.hash.indexOf("?"))+1).match(reg);
				if (r != null){
                    return decodeURI(r[2]);
                }else{
				    // 常规参数取得方法
                    reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
                    r = window.location.search.substr(1).match(reg);  // 匹配目标参数
                    if (r != null) return unescape(r[2]); return null; // 返回参数值
                }
			},

			/**
			 * 错误提示
			 * @param msg 提示内容
             */
			error : function (msg) {
				AliceJ.util.bootbox.showError(msg);
			},

			ajax : function (opts) {
				var ops = {};
				var status = 200;
				var rsp = null;

				var params = opts.params;

				ops.url = opts.url;
				if(opts.data != null){
					ops.data = opts.data;
				}else if(opts.params !=null){
					ops.data = {'params': AliceJ.json.encode(params)};
				}

				ops.type = (opts.type===undefined)?'post':opts.type;
				ops.async = opts.async;
				ops.success = opts.success;

				if(opts.dataType!=null){
					ops.dataType = opts.dataType;
				}

				if(ops.async===false){
					// 同步请求
					try{
						rsp = $.ajax(ops);
                        if(rsp.success === false){
                            AliceJ.util.safe.handleError(null,AliceJ.util.safe.resultCode[rsp.code]+'，'+rsp.message);
                            return null;
                        }
						return AliceJ.json.decode(rsp.responseText);
					}catch(e){
						AliceJ.util.safe.handleError(null,'系统错误，请联系管理员。错误代码：alicej.util.ajax,' + e);
						return null;
					}
				}

				try{
					// 异步请求
					ops.error = function(rsp){
						AliceJ.util.safe.handleError(rsp);
						if(undefined!==opts.error){
							opts.error(rsp);
						}
					};

					ops.success = function(rsp){
						if(rsp.success === false){
                            AliceJ.util.safe.handleError(null,AliceJ.util.safe.resultCode[rsp.code]+'，'+rsp.message);
							if(undefined!==opts.error){
								opts.error(rsp);
							}
                            return null;
						}
						opts.success(rsp);
					};

					$.ajax(ops);

				}catch(e){

					AliceJ.util.safe.handleError(null,'系统错误，请联系管理员。错误代码：alicej.util.ajax,' + e);
					return null;
				}
			},

			safe : {

                /**
				 * 返回值编码
                 */
				resultCode : {
					200 : "正常",
					500 : "服务器内部错误",
					501 : "运行时异常",
					502 : "参数异常",
					503 : "数据库异常",
					504 : "业务异常"
				},

				/**
				 * 处理系统错误
				 */
				handleError : function(rsp,msg){

					if(msg!=null){
						AliceJ.util.error(msg);
						return msg;
					}

					if(rsp.code==600){

						if(rsp.responseText.length<=0){
							AliceJ.util.error('系统错误，请联系管理员');
							return rsp;
						}
						AliceJ.util.error(rsp.responseText);
						return rsp ;
					}

					if(rsp.code==304){

						AliceJ.util.error('系统错误，相应文件缺失，请联系管理员');
						return rsp;
					}

					if(rsp.readyState==0){

						AliceJ.util.error('连接服务器超时，请查看其他同事是否能正常访问系统，如果其他同事可以正常访问，请检查您的网络；如果其他同事也不能访问，请联系管理员');
						return rsp;
					}

					if(rsp.code==601){
                        AliceJ.util.bootbox.showError("登录状态过期，请重新登录！",function () {
                            window.location = 'login.html';
                        });
					}

					AliceJ.util.error('发生系统错误，请尝试按F5刷新页面，如果一直弹出错误，请联系管理员！');

				}
			},

			/**
			 * 弹出窗口
             */
			bootbox : {

				/**
				 * 标准弹出窗口
				 *
				 * @param _context_ 内容
				 * @param _title_ 标题
				 * @param _call_back_ 回调函数
				 */
				alert : function(_context_, _title_, _call_back_) {
					if (undefined == _title_) {
						_title_ = "操作提示";
					}else{
						if ($.isFunction(_title_)) {
							_call_back_ = _title_;
							_title_ = "操作提示";

						}
					}

					layer.open(
                        {
                            title:_title_
                            ,content: _context_
                            ,yes: function(index, layero){
                                layer.close(index);
                                if(_call_back_ != undefined){
                                    _call_back_();
                                }
                            }
                        }
                    );
				},

				/**
				 * 普通提示
				 *
				 * @param _context_ 内容
				 * @param _title_ 标题
				 * @param _call_back_ 回调函数
				 */
				showInfo : function(_context_, _title_, _call_back_) {
					if (undefined == _title_) {
						_title_ = "信息提示";
					}else{
						if ($.isFunction(_title_)) {
							_call_back_ = _title_;
							_title_ = "信息提示";

						}
					}
                    _context_ = "<div class=\"text-center\">\n" +
                    "              <div class=\"text-primary\"><span class=\"modal-main-icon mdi mdi-info-outline\"></span></div>\n" +
                    "              <h4>"+_context_+"</h4>\n" +
                    "            </div>";

					AliceJ.util.bootbox.alert(_context_, _title_, _call_back_);
				},

				/**
				 * 错误提示
				 *
				 * @param _context_ 内容
				 * @param _title_ 标题
				 * @param _call_back_ 回调函数
				 */
				showError : function(_context_, _title_, _call_back_) {
					if (undefined == _title_) {
						_title_ = "错误提示";
					}else{
						if ($.isFunction(_title_)) {
							_call_back_ = _title_;
							_title_ = "错误提示";

						}
					}
                    _context_= "<div class=\"text-center\">\n" +
                    "              <div class=\"text-danger\"><span class=\"modal-main-icon mdi mdi-close-circle-o\"></span></div>\n" +
                    "              <h4>"+_context_+"</h4>\n" +
                    "            </div>";

					AliceJ.util.bootbox.alert(_context_, _title_, _call_back_);
				},

				/**
				 * 警告提示
				 *
				 * @param _context_ 内容
				 * @param _title_ 标题
				 * @param _call_back_ 回调函数
				 */
				showWarn : function(_context_, _title_, _call_back_) {
					if (undefined == _title_) {
						_title_ = "警告提示";
					}else{
						if ($.isFunction(_title_)) {
							_call_back_ = _title_;
							_title_ = "警告提示";

						}
					}
                    _context_= "<div class=\"text-center\">\n" +
                    "              <div class=\"text-warning\"><span class=\"modal-main-icon mdi mdi-alert-triangle\"></span></div>\n" +
                    "              <h4>"+_context_+"</h4>\n" +
                    "            </div>";

					AliceJ.util.bootbox.alert(_context_, _title_, _call_back_);
				},

				/**
				 * 成功提示
				 *
				 * @param _context_ 内容
				 * @param _title_ 标题
				 * @param _call_back_ 回调函数
				 */
				showSuccess : function(_context_, _title_, _call_back_) {
					if (undefined == _title_) {
						_title_ = "成功提示";
					}else{
						if ($.isFunction(_title_)) {
							_call_back_ = _title_;
							_title_ = "成功提示";

						}
					}

                    _context_= "<div class=\"text-center\">" +
                    "<div class=\"text-success\"><span class=\"modal-main-icon mdi mdi-check\"></span></div>" +
                    "<h4>"+_context_+"</h4></div>";
					AliceJ.util.bootbox.alert(_context_, _title_, _call_back_);
				},

				/**
				 * 确认提示
				 *
				 * @param _context_ 内容
				 * @param _title_ 标题
				 * @param _ok_call_back_ 确定回调函数
				 * @param _cancel_call_back_ 取消回调函数
				 */
				showConfirm : function(_context_, _title_, _ok_call_back_, _cancel_call_back_) {
					if (undefined == _title_) {
						_title_ = "<b>确认提示</b>";
					}else{
						if ($.isFunction(_title_)) {
							_cancel_call_back_ = _ok_call_back_;
							_ok_call_back_ = _title_;
							_title_ = "<b>确认提示</b>";
						}
					}
                    layer.confirm(_context_,{
                        title:_title_
                    },function (index) {
                        if(_ok_call_back_ != undefined){
                            _ok_call_back_();
                        }
                        layer.close(index);
                    },function (index) {
                        if(_cancel_call_back_ != undefined){
                            _cancel_call_back_();
                        }
                        layer.close(index);
                    });
				}
			}
		},
		/**
		 * JS全局缓存
         */
		cache : {

			timer : new Array(),

            /**
			 * 当前登录用户属性
             */
			user : {},

			/**
			 * 字典表缓存
             */
			dictionary : {},

			/**
			 * 根据字典组别批量取得字典组数据
			 * @param groupKey 字典组别
             * @returns {*} 返回结果
             */
			getDictionary : function (groupKey) {
				if($.alicej.cache.dictionary[groupKey] == null){
					$.ajax({
						type: "POST",
						url: '/web/basic/base/dictionary/selectList',
						data : {groupCode:groupKey},
						dataType:'json',
						async:false,
						success: function(rsp){
							if(null == rsp || rsp.length === 0){
								$.alicej.util.bootbox.showError("系统中不存在"+ groupKey +"字典组数据");
							}else{
								$.alicej.cache.dictionary[groupKey] = rsp;
							}
						}
					});
				}

				return $.alicej.cache.dictionary[groupKey];
			},

			/**
			 * 根据列字段名与值获取字典转义后的显示值
			 * @param cellKey 列转义前值
             * @param groupKey 列名称
             */
			getCellValueByDictionary : function (cellKey, groupKey){
				console.log();

				var css = $.alicej.cache.getDictionary(groupKey);
				var result = "";
				for(var i in css ){
					if(css[i].dictCode == cellKey){
						result = css[i].dictName;
					}
				}
				return result;
			},

            /**
             * 简单下拉菜单渲染方法
             * @param args 参数对象selectId、groupCode
             */
            renderSimpleDropdowns : function (args){
                var greaseGroups = $.alicej.cache.getDictionary(args.groupCode);
                if(greaseGroups!=null){
                    var html = "<option value=''>请选择</option>";
                    for(var i in greaseGroups){
						// 下拉框仅显示有效的数据字典
						if(greaseGroups[i].isEnable === 1){
							// 判断数据项是否选中
							if(args.key != null && args.key == greaseGroups[i].dictCode){
								html += "<option value='"+greaseGroups[i].dictCode+"' selected=\"selected\">"+greaseGroups[i].dictName+"</option>";
							}else{
								html += "<option value='"+greaseGroups[i].dictCode+"'>"+greaseGroups[i].dictName+"</option>";
							}
						}
                    }
                }else{
                    AliceJ.util.error('字典数据取得失败'+args);
                }
                $("#"+ args.selectId).html(html);
            }
		},

        /**
         * cookie工具
         */
        cookie : {
            // 存入cookie
            set : function(name,value){
                var exp = new Date();
                exp.setTime(exp.getTime() + 30*60*60*1000);
                document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
            },

            // 读取cookie
            get: function(name){
                var strCookie = document.cookie;
                var arrCookie = strCookie.split(";");
                var value;
                for(var i=0;i<arrCookie.length;i++){
                    var v = arrCookie[i].split("=");
                    if(name==$.trim(v[0])){
                        value = v[1];
                        break;
                    }
                }
                return value;
            },
            // 删除cookie
            remove : function(name){
                var exp = new Date();
                document.cookie = name + "="+ escape ("") + ";expires=" + exp.toGMTString();
            }
        }

	};

    /**
     * extend AliceJ to jquery
     */
    $.extend({
        "alicej": AliceJ
    });
    
})(jQuery);
