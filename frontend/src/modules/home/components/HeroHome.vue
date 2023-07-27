<template>
  <div
    class="relative mb-40 flex w-full items-center rounded-b-4xl bg-light-blue p-16 pb-40 font-montserrat"
  >
    <div class="lg:w-2/4">
      <h1 class="font-normal uppercase leading-tight text-black sm:text-5xl">
        Hacemos de tu <br /><span class="font-bold text-primary-orange">mudanza</span><br />
        una experiencia<br />
        <span class="font-bold text-primary-orange">rápida y segura</span>
      </h1>
      <p class="font-light">
        Traslada tus pertenencias con tan solo un clic.<br />
        Con Move Now podes encargar tu mudanza con nuestra flota de vehículos de la mas alta
        calidad.
      </p>
    </div>
    <SearchShipment />
  </div>
</template>

<script setup lang="ts">
import SearchShipment from './SearchShipment.vue'
</script>
