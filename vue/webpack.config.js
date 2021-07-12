// webpack.config.js


var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    devtool: "source-map",
    entry: [
        "webpack-dev-server/client?http://localhost:3000",
        "webpack/hot/only-dev-server",
        "./src/main/web/index.js"
    ],
    output: {
        path: "./src/main/resources/static",
        filename: "index.js",
        publicPath: 'http://localhost:8000/'
    },
    module: {
        loaders: [
            {test: /\.css$/, loader: "style!css"},
            {
                test: /\.js$/, loader: "babel-loader",
                exclude: /node_modules/,
                query: {
                    presets: ['es2015']
                }
            },
            {test: /\.(png|jpg|jpeg|gif|woff)$/, loader: 'url-loader?limit=8192'},
            {test: /\.html$/, loader: 'html'},
        ]
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NoErrorsPlugin(),
        new HtmlWebpackPlugin({
            template: './src/main/web/index.tmpl'
        }),
        new webpack.ProvidePlugin({

            $: "jquery",

            jQuery: "jquery",
            "windows.jQuery": "jquery"

        })],

    devServer: {
        port: 8080,
        proxy: {
            '**': {
                target: 'http://localhost:8081',
                secure: false,
                prependPath: false
            }
        },
        publicPath: 'http://localhost:8080/',
        historyApiFallback: true
    }
};