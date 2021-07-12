module.exports = {
    dev: {
        assetsSubDirectory: 'static',
        assetsPublicPath: '/',
        host: 'localhost',
        port: '8081',
        proxyTable: {               //代理请求路径
            /* 这个api就是main里面设置的Axios.defaults.baseURL = '/api' */
            '/api': {
                /* 设置对象路径 */
                target: "http://localhost:8081",
                /* 开启跨域 */
                changeOrigin:true,
                pathRewrite: {
                    '/api': ' '
                }
            }
        }
    }

}