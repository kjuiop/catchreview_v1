
/**
 * ${}로 감싼 변수명을 원본에서 찾아 대체하여 반환
 *
 * @param str 원본
 * @param dict {변수명:값, 변수명:값, ...}
 * @returns {*}
 */
const strformat = function(str, dict){
    const types = ["number", "string", "boolean"];
    const keys = Object.keys(dict);

    for(let i=0;i<keys.length;i++){
        let val = eval( 'dict.' + keys[i] );
        if( types.indexOf(typeof val)  > -1 ){
            str = str.split('${' + keys[i] + '}').join( eval( 'dict.' + keys[i] ) );
        }
    }
    return str;
}

/**
 * <p> form to json</p>
 * <li>예제</li>
 * <pre>
 *     ex1)
 *     const jsonObj = serializeObject( {form:$("form[name='form']")} ).json();
 *     {name:value, name:value, name:[value,...], ...}
 * </pre>
 * <pre>
 *     ex2)
 *     <form name=form>
 *          <input name="name1"...>
 *          <input name="name2"...>
 *              <input name="n1"...>
 *              <input name="n2"...>
 *              <input name="n1"...>
 *              <input name="n2"...>
 *          <input name="name3"...>
 *          <input name="name4" disabled=true...>
 *     </form>
 *     * 그룹
 *     const jsonObj = serializeObject( {form:$("form[name='form']"), format:{sub:['n1', n2]}} ).json();
 *     {name1:-, name2:-, sub:[{n1:-, n2:-}], name3:-}
 *     * 그룹, 제외
 *     const jsonObj = serializeObject( {form:$("form[name='form']"), format:{sub:['n1', n2]}}, exclude:['name3'] ).json();
 *     {name1:-, name2:-, sub:[{n1:-, n2:-}]}
 *     * 그룹, 제외, disable추가
 *     const jsonObj = serializeObject( {form:$("form[name='form']"), format:{sub:['n1', n2]}}, exclude:['name3'], disabled:['name4'] ).json();
 *     {name1:-, name2:-, sub:[{n1:-, n2:-}], name4:-}
 * </pre>
 * <pre>
 *     ex3) 확인용.
 *     serializeObject( {form:$("form[name='form']")} ).excluded(); // 제외된 이후 엘리먼트 목록.
 *     serializeObject( {form:$("form[name='form']")} ).disables(); // disabled된 엘리먼트중 추가될 항목.
 *     serializeObject( {form:$("form[name='form']")} ).formElements(); // json 변환 대상.
 * </pre>
 * <li>옵션</li>
 * <pre>
 *     options = {
 *         form: (form object),
 *         exclude : []  * 제외시킬 엘리먼트 명,
 *         disabled : [] * disabled된것중 추가할 엘리먼트 명,
 *         format : {}   * 하위 그룹으로 묶을 엘리면트명.
 *     }
 * </pre>
 */
const serializeObject = (function(factory){ return factory();})(function(){
    const defaultOptions = {
        form: null,
        exclude: [],
        disabled: [],
        format: {}
    }

    /**
     * format의 object명칭 첫벗째것 반환
     *
     * @param obj
     * @return {string|undefined}
     */
    const findFirstName = function(obj = {}) {
        if( typeof obj === "object")
            return Object.keys(obj)[0];
        return undefined;
    }

    /**
     * format에서 주어진 name을 찾음( 1단계만 )
     *
     * @param format
     * @param name
     * @return {{}|null}
     */
    const findIn = function(format, name) {
        for(let v of Object.keys(format)) {
            if( format[v].includes(name) ) {
                const ret = {};
                ret[v] = format[v];
                return ret;
            }
        }
        return null;
    }

    /**
     * 배열에서 object만 추출.
     *
     * @param format
     * @param name
     * @return {{}}
     */
    const extractObject = function(format, name) {
        const arr = format[name];
        const ret = {};
        for(let v of arr){
            if( typeof v === "object"){
                $.extend(ret, v);
            }
        }
        return ret;
    }

    /**
     * json 변환 로직
     *
     * @param eleArray
     * @param index
     * @param projection
     * @param jsonObject
     * @param matchName
     * @return {number}
     */
    const make = function(eleArray = [], index = -1, projection = {},  jsonObject = {}, matchName = '') {
        let i = index;
        const matchArray = matchName ? projection[matchName] : null;

        let prevName = null;
        let entityArray = [];
        let entity = jsonObject;

        if(matchArray){
            entity = {};
            jsonObject[matchName] = entityArray;
            jsonObject[matchName].push(entity);
            projection = extractObject(projection, matchName);
        }

        for(;i<eleArray.length;i++){
            const findObject = findIn( projection, eleArray[i].name);
            if( findObject ) {
                i = make.apply(this, [eleArray, i, findObject, entity, findFirstName(findObject)]);
                prevName = null;
                if( eleArray.length <= i )
                    return i;
            }

            const ele = eleArray[i];
            const name = ele.name;

            if( matchArray ){
                if( !matchArray.includes(name) ){
                    return i;
                }

                if( entity[name] ){
                    if( prevName == name ){
                        if( !Array.isArray(entity[name]) ){
                            const arr = [entity[name]];
                            entity[name] = arr;
                        }
                        entity[name].push(ele.value);
                    }else{
                        entity = {};
                        entityArray.push(entity);
                        entity[name] = ele.value;
                    }
                }else{
                    entity[name] = ele.value;
                }
            } else {
                if (entity[name]) {
                    if (!Array.isArray(entity[name])) {
                        const arr = [entity[name]];
                        entity[name] = arr;
                    }
                    entity[name].push(ele.value);
                } else {
                    entity[name] = ele.value;
                }
            }
            prevName = name;
        }

        return i;
    }

    const serialize = function(options) {
        return new serialize.fn.init(options);
    };

    serialize.fn = serialize.prototype = {
        constructor: serialize,
        init: function (options) {
            this.settings = $.extend(true, {}, defaultOptions, options);
            if( this.settings.form ){
                this.settings.elementArray = $(this.settings.form).serializeArray();
            }
        },
        addExclude: function(elementName ='') {
            if( elementName )
                this.settings.exclude.push(elementName);
            return this;
        },
        addDisabled: function(elementName = '') {
            if( elementName )
                this.settings.disabled.push(elementName);
            return this;
        },
        setForm: function(form){
            this.settings.form = form;
            if( this.settings.form ){
                this.settings.elementArray = $(this.settings.form).serializeArray();
            }
            return this;
        },
        setFormat: function(format = {}) {
            this.settings.format = format;
            return this;
        },
        /**
         * disabled 추가 대상.
         *
         * @return {[]}
         */
        disables: function(){
            let ret = [];
            for(let name of this.settings.disabled ) {
                const $frm = $(this.settings.form);
                const checkArray = $frm.find("*[name='" + name + "']:disabled:checked");
                if (checkArray.length > 0) {
                    ret = ret.concat( checkArray.map( (i,v) => { return {name:name,value:$(v).val()} } ).toArray() );
                } else {
                    const normalArray = $frm.find("*[name='" + name + "']:disabled");
                    if (normalArray.length > 0) {
                        ret = ret.concat(normalArray.map((i, v) => {
                            return {name: name, value: $(v).val()}
                        }).toArray());
                    }
                }
            }
            return ret;
        },
        /**
         * json 변환 대상에서 제외 시킨 결과.
         *
         * @return {*|jQuery}
         */
        excluded: function(){
            const array = this.settings.elementArray;
            if( array && this.settings.exclude.length > 0  ){
                const exclude = this.settings.exclude;
                return $(array).filter( (i,v) => !exclude.includes(v.name) ).toArray();
            }
            return array;
        },
        /**
         *  json 변환 대상
         *
         * @return {*|jQuery}
         */
        formElements: function(){
            let ret = this.excluded();

            if( this.settings.disabled.length > 0 ){
                ret = ret.concat(this.disables());
            }
            return ret;
        },
        /**
         *  json 변환
         *
         * @return {*|jQuery}
         */
        json: function() {
            const $frm = $(this.settings.form);
            if( $frm.length <= 0 )
                return null;

            const eleArray = this.formElements();
            const result = {};

            console.log('elements', eleArray);

            make.apply(this, [eleArray, 0, this.settings.format, result, null]);

            return result;
        }
    }

    serialize.fn.init.prototype = serialize.fn;
    return serialize;
})

/**
 * <p> 단일 form 유효성 체크</p>
 *
 * <li>
 * form에서 필수값 체크 대상을 가져오는 방법. <br/>
 *     : class에 required-value(기본값) 값이 있는 것들을 찾는다.
 * </li>
 * <li>예제</li>
 * <pre>
 *     ex)1
 *     const ret = validation({form:$("form[name='reg']")[0]}).check();
 *
 *     ex)2
 *     const valid = validation()
 *                      .setForm($("form[name='reg']")[0])
 *                      .setFilterSelector(".required-value")
 *                      .setPrefix("[등록]")
 *                      .addHandler( {title:function(form){ console.log(this); return {success:true} }} )
 *                      .addHandler( {num:function(form){
 *                                      const s = validation.fn.checker.numberHigher(10)( $(this).val() );
 *                                      return {success:s, message:'10이상입니다', focus:this};
 *                                  }} )
 *                      .setCallback( function(){ console.log('callback');} )
 *                      ;
 *     const success = valid.check().success;
 * </pre>
 * <li>옵션/결과</li>
 * <pre>
 *      options
 *      {
 *          form: (form 단일 object),
 *          filter:{
 *              selector: "jQuery selector string. 기본값:'required-value'"
 *          }
 *          message:{
 *              prefix: "실패 메시지 앞 문구"
 *          },
 *          handler : {
 *              (form element name) : check function
 *              input-name : function
 *          },
 *          callback : 실패시 처리. focus 이후 실행됨.
 *      }
 *
 *      handler return value = {
 *          success : true|false,
 *          message : "보여줄 실패 메시지",
 *          focus : form element object | function. 기본값을 호출될 당시 element
 *      }
 * </pre>
 * <pre>
 *
 * </pre>
 *
 * @returns {{success:boolean, form:object, element:null|object}}
 */
const validation = (function(factory){ return factory(); })(function(){
    "use strict";

    const defaultOptions = {
        form : null,
        filter : {selector:'.required-value'},
        message : {prefix:''},
        handler : {},
        callback : null
    }

    /**
     * form에서 element 추출.
     * checkbox, radio는 name이 같은 것은 중복으로 처리. 한건만 등록.
     * this : form
     *
     * @returns {[]}
     */
    const items = function(options){
        const $this = $(this);
        const arr = [];
        const selector = options.filter.selector;

        // 중복 제거. checkbox, radio이고 name이 같을 경우 하나만 등록.
        $this.find(selector).each(function(){
            const name = $(this).attr("name");
            if( name ) {
                const find = arr.filter(function (x) {
                    const type = $(x).attr("type") ? $(x).attr("type").toLowerCase():undefined;
                    if(type == "checkbox" || type == "radio" ) {
                        return $(x).attr("name") == name;
                    }
                    return false;
                });
                if( find.length == 0 ) {
                    arr.push(this);
                }
            }
        });

        return arr;
    }

    /**
     * 단순 값 체크
     * this : form element
     *
     * @param form
     * @returns {{success: boolean, focus: simpleChecker, message: string}}
     */
    const simpleChecker = function(form){
        const $frm = $(form);
        const $this = $(this);
        const name = $this.attr("name");
        const tagName = this.tagName.toLowerCase();
        const type = $this.attr("type")? $this.attr("type").toLowerCase() : "";
        let checked = true;
        if (type === "radio" || type === "checkbox") {
            checked = $frm.find("input[name='"+name+"']:checked")[0] != undefined;
        } else if (tagName === "select") {

        } else {
            checked = $this.val() != "";
        }

        return {
            success:checked,
            message:'',
            focus:this
        };
    }

    /**
     * 실패 메시지 추출
     * this : form element
     *
     * @param form
     * @param options
     * @param checkResult
     * @returns {*|string}
     */
    const getMessage = function(form, options, checkResult){
        const $this = $(this);
        const prefix = options ? options.message.prefix : '';
        const template = '[${label}] 필수값 입니다.';
        let message = checkResult ? checkResult.message : '';

        if( !message ) {
            const title = $this.data("valid-title");
            if( title ) {
                message = strformat(template, {label: title});
            }
        }
        if( !message ) {
            message = $this.data("valid-message");
        }
        if( !message ){
            message = $this.attr("placeholder");
        }
        if( !message ){
            const label = $this.closest("div.form-group").find("label.required-label:first").text().trim();
            message = strformat(template, {label:label});
        }

        if( prefix ){
            message = prefix + message;
        }

        return message;
    }

    /**
     * form check
     *
     * @param options
     * @returns {{form: formCheck, success: boolean, element: *}|{form: formCheck, success: boolean, element: null}}
     */
    const formCheck = function(options){
        const $frm = $(this);
        const arr = items.apply(this, [options]);

        // 검사. 값이 있는지 단순 비교.
        for(let v of arr){
            const $this = $(v);
            const name = $this.attr("name");
            const dataCustomValid = $this.data("custom-valid");
            const customChecker = options.handler[name];
            const failCallback = options.callback;

            const checker = dataCustomValid ? dataCustomValid : (customChecker?customChecker:simpleChecker);
            const bindChecker = $.proxy(checker, v);
            const ret = bindChecker(this);
            const checked = ret.success;

            // 체크 실패.
            if( !checked ){
                const message = getMessage.apply(v, [this, options, ret]);
                const focus = ret.focus ? ret.focus : v;
                oneBtnModal( message, function(){
                    if( typeof focus === "function" )
                        focus.apply(v);
                    else
                        focus.focus();
                });

                if( failCallback ){
                    failCallback.apply(v);
                }

                return {
                    success: false,
                    form : this,
                    element : v
                };
            }
        };

        return {success:true, form:this, element:null};
    }

    const validate = function(options){
        return new validate.fn.init(options);
    }

    validate.fn = validate.prototype = {
        constructor: validate,
        init : function(options){
            this.checkerOptions = $.extend({}, defaultOptions, options);
            this.form = this.checkerOptions.form;
            this.$frm = $(this.form);
        },
        check: function(){
            if( this.$frm.length <= 0 )
                return {success:true, form:this.form, element:null};
            return formCheck.apply(this.$frm[0], [this.checkerOptions]);
        },
        setForm: function(formElement){
            if( formElement instanceof jQuery )
                formElement = formElement[0];
            this.checkerOptions.form = formElement;
            this.form = formElement;
            this.$frm = $(formElement);
            return this;
        },
        addHandler: function(handler = {}){
            $.extend(true, this.checkerOptions.handler, handler);
            return this;
        },
        removeHandler: function(handlerName){
            delete this.checkerOptions.handler[handlerName];
            return this;
        },
        setCallback: function(callback){
            if( typeof callback === "function")
                this.checkerOptions.callback = callback;
            return this;
        },
        setPrefix: function(prefix){
            if( typeof prefix === "string")
                this.checkerOptions.message.prefix = prefix;
            return this;
        },
        setFilterSelector: function(selector = ''){
            if( typeof selector === "string")
                this.checkerOptions.filter.selector = selector;
            return this;
        },
        options: function(){ return this.checkerOptions},
        // check 함수.
        checker : {
            numberHigher : function(minimum){
                const m = isNaN(minimum) ? 0 : minimum;
                return function(value){
                    if( value && !isNaN(value) ){
                        return value >= m;
                    }
                    return false;
                }
            },
            numberLower : function(maximum){
                const m = isNaN(maximum) ? 0 : maximum;
                return function(value){
                    if( m == 0 )
                        return true;

                    if( value && !isNaN(value) ){
                        return value <= m;
                    }
                    return false;
                }
            },
            // 숫자 체크.
            numberHigherInput : function(displayName, min){
                const eleName = displayName;
                const m = min;
                const numberHigher = validate.fn.checker.numberHigher(m);
                return function(form){
                    const $this = $(this);
                    const success = numberHigher($this.val());
                    return {success:success, message: "[" + eleName+ "] " + m + "이상입니다."};
                }
            },
            // 숫자 체크.(loe, goe)
            numberBetweenInput : function(displayName, min, max){
                const eleName = displayName;
                const m = min;
                const x = max;
                const numberHigher = validate.fn.checker.numberHigher(m);
                const numberLower = validate.fn.checker.numberLower(x);
                return function(form){
                    const $this = $(this);
                    const success = numberHigher($this.val()) && numberLower($this.val());
                    return {success:success, message: "[" + eleName+ "] " + m + "~" + x + " 사이입니다." };
                }
            }
        }
    }

    validate.fn.init.prototype = validate.fn;

    return validate;
});


/**
 * /**
 * <p> 검색용 form 검색 또는 초기화</p>
 *
 * <li>예제</li>
 * <pre>
 *     ex) 1
 *     const searcher = searchReset({form:$("form[name='reg']")[0]});
 *     searcher.search();
 *     searcher.reset();
 *     ex) 2
 *     const searcher = {}; // 미리선언
 *     $(document).ready( function(){
 *          // 특정 object에 바인딩.
 *          searchReset({form:$("form[name='reg']")[0]}, searcher);
 *     });
 *     searcher.search();
 *     searcher.reset();
 *
 * </pre>
 * <li>옵션</li>
 * <pre>
 *      options
 *      {
 *          form: (form 단일 object),
 *          initValues:{
 *              (element name): "값",    특정값으로 초기화 진행.
 *              (element name): function(form, ele|ele[])   form, element 인자로 함수 호출.(여러개일경우 배열로)
 *          },
 *          initExclude: [],  초기화 제외 element name 배열
 *          initCallback : function(form),  기본값 초기화 후 호출.
 *          searchCallback : function(form)  조회 전 호출
 *      }
 * </pre>
 * <pre>
 *
 * </pre>
 *
 * @returns {}
 */
const searchReset = (function(factory){return factory();})(function(){
    const defaultOptions = {
        form:$("form[name='frmSearch']"),
        initValues:{},
        initExclude:['page', 'size', 'mainLayoutCode'],
        initCallback:null,
        searchCallback:null,
    }

    const removeElements = function(eleArray){
        const ret = [];
        if( this.searchOptions.initExclude && this.searchOptions.initExclude.length > 0 ){
            const ex = this.searchOptions.initExclude;
            for(let v of eleArray ){
                if( !ex.includes(v.name) ){
                    ret.push(v);
                }
            }
            return ret;
        }
        return eleArray;
    }

    search = function(options, warp){
        return new search.fn.init(options, warp);
    }

    search.fn = search.prototype = {
        constructor:search,
        init : function(options, warp){
            this.searchOptions = $.extend({}, defaultOptions, options);
            if( warp ) {
                warp.search = this.search.bind(this);
                warp.reset = this.reset.bind(this);
            }
        },
        search: function(){
            if( this.searchOptions.form ) {
                if (this.searchOptions.initCallback) {
                    this.searchOptions.initCallback.apply(this, [this.searchOptions.form]);
                }
                this.searchOptions.form.submit();
            }
        },
        reset : function(){
            if( this.searchOptions.form ) {
                const $frm = $(this.searchOptions.form);
                const eleArray = removeElements.apply(this, [$frm.serializeArray()]);
                const custom = this.searchOptions.initValues;

                for(let v of eleArray){
                    const initValue = custom[v.name] != undefined ? custom[v.name] : "";
                    const $ele = $frm.find("*[name='"+v.name+"']");

                    if( typeof initValue === 'function') {
                        initValue(this.searchOptions.form, $ele.length == 1 ? $ele[0] : $ele.toArray() );
                    } else {
                        const $radio = $frm.find("[name='"+v.name+"'][type='radio'], [name='"+v.name+"'][type='checkbox']");
                        if( $radio.length > 0 ){
                            if( $.fn['iCheck'] ){
                                if( initValue == null )
                                    $radio.iCheck('uncheck');
                                else
                                    $frm.find("[name='"+v.name+"'][value='"+initValue+"']").iCheck('check');
                            } else {
                                if( initValue == null )
                                    $radio.prop('checked', false);
                                else
                                    $frm.find("[name='"+v.name+"'][value='"+initValue+"']").prop('checked', true);
                            }
                        } else if( initValue == null ) {
                            $ele.val('');
                        } else {
                            $ele.val(initValue);
                        }
                    }
                }

                if( this.searchOptions.initCallback ){
                    this.searchOptions.initCallback.apply(this, [this.searchOptions.form]);
                }

                this.searchOptions.form.submit();
            }
        }
    }

    search.fn.init.prototype = search.fn;
    return search;
});