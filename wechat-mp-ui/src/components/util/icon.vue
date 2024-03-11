<template>
    <svg class="icon" aria-hidden="true" :style="'width:'+size+';height:'+size">
        <use :xlink:href="'#' + icon"></use>
    </svg>
</template>

<script>
export default {
    name: "Icon",
    props: {
        icon: {
            type: String,
            default: "",
        },
        size: {
            type: String,
            default: "1.7em"
        }
    }
}
</script>

<style>
.icon {
  vertical-align: -0.15em;
  fill: "currentColor";
  overflow: hidden;
}
</style>