const { defineConfig } = require('@vue/cli-service')

const port = 80

module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    proxy: 'http://localhost:80/'
  },
  pluginOptions: {
    "style-resource-loader" :{
      preProcessor: "less",
    }
  }
})
