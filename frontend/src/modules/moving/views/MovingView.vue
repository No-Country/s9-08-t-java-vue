<template>
  <main class="container mx-auto min-h-screen overflow-x-hidden font-montserrat">
    <h1>Moving</h1>
    <StepComponent
      :values="[
        { editable: true, name: 'Tipo de envío', status: true, stepNumber: 1 },
        { editable: true, name: 'Especificaciones del pedido', status: true, stepNumber: 2 },
        { editable: true, name: 'Pago', status: false, stepNumber: 2 },
        { editable: true, name: 'Verificación de pedido', status: false, stepNumber: 4 },
        { editable: true, name: 'Pedido Finalizado', status: false, stepNumber: 5 }
      ]"
    ></StepComponent>
  </main>
</template>

<script setup lang="ts">
import StepComponent from '../components/StepComponent.vue'
</script>
