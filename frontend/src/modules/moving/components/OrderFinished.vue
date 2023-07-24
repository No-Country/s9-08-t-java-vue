<template>
  <div class="grid h-96 place-items-center">
    <img class="h-64 w-64 object-cover" src="../../../assets/img/hand-done.webp" alt="" srcset="" />
  </div>
  <div class="grid h-96 place-items-center">
    <h1 class="mt-52 text-center text-5xl font-bold text-primary-orange lg:mt-24">
      Compra realizada con éxito
    </h1>
    <p class="m-auto mt-5 block w-1/3 text-center">
      Muchas gracias por tu preferencia ¡Te mandaremos un mail con la confirmación de tu compra!
    </p>
    <p class="m-auto mt-5 block w-1/3 text-center">
      Acompaña el estado de tu mudanza en
      <RouterLink class="text-base font-bold text-sky-700" to="/my-movings">
        “Mis mudanzas”
      </RouterLink>
    </p>
    <MNButton
      text="Volver al Home"
      :class="`w-36 cursor-pointer rounded-lg bg-primary-orange py-2 ${
        true ? 'hover:brightness-90' : ' cursor-default bg-zinc-600 opacity-20'
      }`"
      @click="$router.push('/')"
    ></MNButton>
  </div>
</template>

<script lang="ts" setup>
import MNButton from '@/components/common/MNButton.vue'
</script>
