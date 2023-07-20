<template>
  <div class="my-4 flex h-full w-full flex-col items-center gap-8 p-8">
    <div class="flex h-max w-full items-center justify-center gap-8">
      <div class="flex w-2/4 flex-col gap-4 text-black">
        <MNDropdownInput
          :items="['1 ayudante', '2 ayudantes', '3 ayudantes']"
          :text="crewMembers"
          :text-class="'text-black text-2xl font-semibold'"
          :item-class="'text-black'"
        />
        <p class="text-base font-light">
          El servicio de mudanza incluye solo un ayudante. ¡Adicione más, ahorre horas en su
          trayecto y economice!
        </p>
      </div>
      <div class="flex w-max items-center justify-center gap-8">
        <div class="flex flex-col gap-1">
          <h5 class="text-lg font-light text-black">usd $9.99</h5>
          <span class="text-sm font-light">por ayudante</span>
        </div>
        <MNToggle :state="check" @state="(arg) => $emit('state', arg)"></MNToggle>
      </div>
    </div>
    <div class="flex h-max w-full items-center justify-center gap-8">
      <div class="flex w-2/4 flex-col gap-4 text-black">
        <h4 class="text-2xl font-semibold">Seguro contra imprevistos</h4>
        <p class="text-base font-light">
          Sabemos que hay paquetes que merecen un cuidado especial. Asegura tus pertenencias ante
          posibles daños y evita que se pierdan en el trayecto. Protección extra para objetos
          frágiles.
        </p>
      </div>
      <div class="flex w-max items-center justify-center gap-8">
        <h5 class="text-lg font-light text-black">usd $19.99</h5>
        <MNToggle :state="check" @state="(arg) => $emit('state', arg)"></MNToggle>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import MNToggle from '@/components/common/MNToggle.vue'
import MNDropdownInput from '@/components/common/MNDropdownInput.vue'
import { ref } from 'vue'

const crewMembers = ref('Ayudantes')
defineEmits(['state'])

const check = ref(false)
</script>
