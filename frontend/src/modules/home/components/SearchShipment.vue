<template>
  <form class="w-full rounded-4xl bg-white shadow-2xl">
    <MNDropdownInput @selected="setType" :items="['Mudanza']" :text="typeSelected" />

    <label for=""></label>
    <MNInput placeholder="Origen" type="text" />
  </form>
</template>

<script setup lang="ts">
import MNDropdownInput from '@/components/common/MNDropdownInput.vue'
import MNInput from '@/components/common/MNInput.vue'
import { ref } from 'vue'

const typeSelected = ref('Tipo de envio')
const setType = (type: string) => (typeSelected.value = type)
</script>
