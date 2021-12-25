<template>
    <div id = "FooterComponent">
        <footer class="ui vertical segment m-padded-tb-massive">
            <div class="ui center aligned container">
                <p id="copyright" class="m-text-thin m-text-spaced m-opacity-tiny"></p>
                <a class="m-text-thin m-text-spaced m-opacity-tiny" href="https://beian.miit.gov.cn/">蜀ICP备2020033033号-1</a>
            </div>
        </footer>
    </div>
</template>
<script>
export default {
    name  : 'FooterComponent',
    props : [],
    date() {
        return {
            date : new Date().getFullYear
        }
    },
     mounted() {
       document.getElementById("copyright").innerHTML='Copyright © 2019 - ' +  this.getYear() + ' HuangLin Designed by HuangLin'
    },
    methods : {
        getYear() {
             return new Date().getFullYear();
        }
    }
}
</script>