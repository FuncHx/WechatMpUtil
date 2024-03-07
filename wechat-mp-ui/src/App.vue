<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',
}
</script>

<style>
  html, body{
    width: 100%;
    height: 100%;
    padding: 0;
    margin: 0;
  }
  #app {
    width: 100%;
    height: 100%;
  }
  @font-face {
  font-family: 'iconfont';
  src: url("./assets/icon/iconfont.woff2?t=1709781280511") format('woff2'),
       url('./assets/icon/iconfont.woff?t=1709781280511') format('woff'),
       url('./assets/icon/iconfont.ttf?t=1709781280511') format('truetype'),
       url('./assets/icon/iconfont.svg?t=1709781280511#iconfont') format('svg');
  }
  .iconfont {
      font-family: "iconfont" !important;
      font-size: 1.5em;
      font-style: normal;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
    }
</style>
