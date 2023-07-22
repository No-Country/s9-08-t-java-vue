<template>
  <section class="py-12">
    <div class="text-blue-night lg:w-1/2">
      <h1 class="mt-32 text-3xl font-bold text-primary-orange">Verificación del pedido</h1>
      <div class="w-1/2 border-b-2 py-5">
        <p class="mt-5 text-xl font-bold text-primary-orange">Cuenta</p>
        <p class="">{{ profile.store.profile.username }}</p>
        <p>{{ profile.store.profile.email }}</p>
      </div>
      <div class="w-1/2 border-b-2 py-5">
        <p class="mt-5 text-xl font-bold text-primary-orange">Dirección de Envío</p>
        <p class="">Nombre</p>
        <p>Numero</p>
        <p class="">Dirección</p>
        <p>Dirección</p>
      </div>
      <div class="w-1/2 border-b-2 py-5">
        <p class="mt-5 text-xl font-bold text-primary-orange">Información de pago</p>
        <p class="">Nombre</p>
        <p>Correo</p>
      </div>
    </div>
  </section>
</template>

<script lang="ts" setup>
import { useMovingStore } from '@/store/moving'
import { useAuthStore } from '@/modules/auth/store/auth'

const profile = useAuthStore()
const moving = useMovingStore()
</script>
