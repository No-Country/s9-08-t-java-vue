<template>
  <main>
    <h1>Home</h1>
    <MNToggle :color="'bg-green-400'" @state="isChecked"></MNToggle>
  </main>
</template>

<script setup lang="ts">

import MNToggle from '@/components/common/MNToggle.vue';

const isChecked = (value: boolean) => console.log(value)

</script>
