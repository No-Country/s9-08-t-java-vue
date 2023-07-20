<template>
  <main class="container mx-auto overflow-x-hidden p-2 font-montserrat">
    <StepComponent
      :values="[
        { editable: true, name: 'Tipo de envío', status: true, stepNumber: 1 },
        { editable: true, name: 'Especificaciones del pedido', status: true, stepNumber: 2 },
        { editable: true, name: 'Pago', status: false, stepNumber: 2 },
        { editable: true, name: 'Verificación de pedido', status: false, stepNumber: 4 },
        { editable: true, name: 'Pedido Finalizado', status: false, stepNumber: 5 }
      ]"
    ></StepComponent>
    <CardMovin @state="test" info="Persona o parejas" size="M"></CardMovin>
    <FleetWrapper />
    <ExtrasComponent />
    <TimeComponent />
  </main>
</template>

<script setup lang="ts">
import CardMovin from '../components/CardMovin.vue'
import FleetWrapper from '../components/FleetWrapper.vue'
import StepComponent from '../components/StepComponent.vue'
import ExtrasComponent from '../components/ExtrasComponent.vue'
import TimeComponent from '../components/TimeComponent.vue'

const test = (arg: boolean) => console.log(arg)
</script>
