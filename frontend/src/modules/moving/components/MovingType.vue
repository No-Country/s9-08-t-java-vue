<template>
  <section class="mt-20">
    <h1 class="mt-52 text-5xl font-bold text-primary-orange lg:mt-24">Elige tu tipo de mudanza</h1>
    <h3 class="text-3xl font-light text-blue-night">¿Cuál es el tamaño de tu mudanza?</h3>
    <SwiperGeneric :data="data" class="mt-10 h-96">
      <template #slide="{ item, index }">
        <CardMovin
          :info="item.info"
          @state="(arg) => setSize(arg, index)"
          :state="item.state"
          :size="item.size"
        ></CardMovin>
      </template>
    </SwiperGeneric>
  </section>
</template>

<script lang="ts" setup>
import SwiperGeneric from './SwiperGeneric.vue'
import CardMovin from './CardMovin.vue'
import { ref, toRef } from 'vue'
import { useMovingStore } from '@/store/moving'
import { storeToRefs } from 'pinia'
import { reactive } from 'vue'

const store = storeToRefs(useMovingStore())
const data = reactive([
  { info: 'Persona o parejas', size: 'S', state: false },
  { info: 'Familia o eventos reducidos', size: 'M', state: true },
  { info: 'Familia o eventos grandes', size: 'L', state: false }
])

const localCheck = ref('')

const setSize = (info: { size: string; value: boolean }, index: number) => {
  data[index].state = info.value
  if (info.value) {
    store.movingSize.value = data[index].size
  }
  data.forEach((x) => {
    if (x.size != info.size && info.value === true) {
      x.state = false
    }
  })
}

const test = (arg: boolean) => console.log(arg)
</script>
