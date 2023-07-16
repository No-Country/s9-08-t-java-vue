<template>
  <label ref="menu" id="menu" for="check">
    <span id="line1"></span>
    <span id="line2"></span>
    <span id="line3"></span>
  </label>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue'

const emit = defineEmits(['toggle'])

const menu = ref()
onMounted(() => {
  menu.value.addEventListener('click', () => {
    const ev = menu.value
    ev.childNodes[0].classList.toggle('line1')
    ev.childNodes[1].classList.toggle('line2')
    ev.childNodes[2].classList.toggle('line3')
    emit('toggle')
  })
})
</script>
<style scoped>
label {
  display: flex;
  flex-direction: column;
  width: 45px;
  cursor: pointer;
}
label span {
  background: #de4f0e;
  height: 3px;
  margin: 4px 0;
  transition: 0.4s cubic-bezier(0.68, -0.6, 0.32, 1.6);
}
span:nth-of-type(1) {
  width: 50%;
}
span:nth-of-type(2) {
  width: 100%;
}
span:nth-of-type(3) {
  width: 75%;
}
.line1 {
  transform-origin: bottom;
  transform: rotatez(45deg) translate(5px, -2px);
}
.line2 {
  transform-origin: top;
  transform: rotatez(-45deg);
}
.line3 {
  transform-origin: bottom;
  width: 50%;
  transform: translate(12px, -7px) rotatez(45deg);
}
</style>
