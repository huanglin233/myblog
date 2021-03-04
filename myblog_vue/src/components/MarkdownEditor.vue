<template>
    <div :id="content">
        
    </div>    
</template>
<script>
import $ from "jquery";
import uuid from "uuid/v4";

export default {
    name:"MarkdownEditor",
    data:function(){
        return {
            //最终生成的编辑器
            editor:null,
            //默认选项
            defaultOptions:{
                width: "100%",
                height: 740,
                path : "../../static/lib/editormd/lib/",
                theme : "white",
                previewTheme : "white",
                editorTheme : "pastel-on-white",
                markdown : this.markdown,   //动态设置的markdown文本
                codeFold : true,
                //syncScrolling : false,
                saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
                searchReplace : true,
                //watch : false,                // 关闭实时预览
                htmlDecode : "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启    
                //toolbar  : false,             //关闭工具栏
                //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
                emoji : true,
                taskList : true,
                tocm : true,                  // Using [TOCM]
                tex : true,                   // 开启科学公式TeX语言支持，默认关闭
                flowChart : true,             // 开启流程图支持，默认关闭
                sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
                //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
                //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
                //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
                //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
                //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
                imageUpload : true,
                imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                crossDomainUpload : true,
                imageUploadURL : "https://huanglin.online/blogApi/upload",
                // imageUploadURL : "http://127.0.0.1:8081/upload",
                onload : function() {
                    //console.log('onload', this);
                    //this.fullscreen();
                    //this.unwatch();
                    //this.watch().fullscreen();

                    //this.setMarkdown("#PHP");
                    //this.width("100%");
                    //this.height(480);
                    //this.resize("100%", 640);
                }
            }
        }
    },
    props:{
        /**
         * editormd挂载元素的id
         */
        content :{
            type:String,
            default:uuid()
        },
        markdown : {
            type: String,
            default: "请输入文本"
        },
        /**
         * editormd的选项对象
         */
        options:{
            type:Object
        }
    },
    mounted(){
        let vm=this;
        //加载editormd
        this.requireEditor(function(){
            vm.editor=editormd(vm.content,vm.getOptions());
        })
        
    },
    methods:{
        /**
         * 异步加载editormd
         * callback 成功后的回调函数
         */
        requireEditor(callback){
            let vm=this;
            //设置全局变量，因为editormd依赖jquery
            window.$=window.jQuery=$;
            //异步加载并执行
            $.getScript("../../static/lib/editormd/editormd.min.js",function(script){
                callback();
            })
            //加载css
            $('head').append( $('<link rel="stylesheet" type="text/css" />').attr('href', '../../static/lib/editormd/css/editormd.min.css') );
        },
        /**
         * 得到最终的options，组件属性上获得的选项覆盖此处的默认选项
         */
        getOptions(){
            return Object.assign(this.defaultOptions,this.options);
        },

        getContent() {
            return this.editor.getMarkdown();
        },
    }
}
</script>
<style>

</style>
