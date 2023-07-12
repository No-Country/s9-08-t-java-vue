<template>
  <section class="flex min-h-screen w-full flex-col justify-center">
    <h1 class="text-5xl font-bold text-blue-night">Más opciones para tu mudanza</h1>
    <h3 class="text-3xl font-light text-blue-night">Más opciones para tu mudanza</h3>
    <div class="relative h-full w-full rounded-4xl bg-light-blue p-32">
      <div class="flex w-2/4 flex-col justify-center gap-4">
        <h1 class="text-5xl font-bold text-primary-orange">Experiencias con MoveNow</h1>
        <p class="text-3xl font-light">
          Cuando usted elige Move Now, esta eligiendo eficacia, comodidad y sobre todo seguridad.
        </p>
        <a
          class="w-max rounded-2xl bg-primary-orange px-4 py-2 text-2xl font-semibold text-white"
          href="#"
          >Contratar servicio</a
        >
      </div>
      <carousel-wrapper />
    </div>
  </section>
</template>

<script setup lang="ts">
import CarouselWrapper from '../components/CarouselWrapper.vue'
</script>
