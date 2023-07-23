<template>
  <section class="my-20 flex flex-col items-center justify-center">
    <div class="flex items-center justify-between">
      <div class="flex flex-col justify-center gap-10">
        <h1 class="text-5xl font-bold text-primary-orange">
          Selecciona nuestro servicio de empaquetamiento y seguro
        </h1>
        <h3 class="text-3xl font-light text-blue-night">
          Porque en Movenow tus pertenencias son nuestra prioridad
        </h3>
      </div>
      <img
        src="@/assets/img/options-info/boxcheck.webp"
        alt="secure box"
        class="mr-10 h-max w-max"
      />
    </div>
    <ExtrasCard />
  </section>
</template>

<script setup lang="ts">
import ExtrasCard from './ExtrasCard.vue';
</script>
