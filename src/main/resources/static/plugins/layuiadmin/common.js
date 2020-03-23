
function getRootPath() {
    return window.location.protocol + '//' + window.location.host;
}

layui.config({
    base: getRootPath()+'/plugins/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
})